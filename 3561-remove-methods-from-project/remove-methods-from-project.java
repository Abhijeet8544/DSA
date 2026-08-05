import java.util.*;

class Solution {
    private boolean[] suspicious;
    private boolean[] visited;
    private List<Integer>[] graph;
    private List<Integer>[] undirected;

    public List<Integer> remainingMethods(int n, int k, int[][] invocations) {
        suspicious = new boolean[n];
        visited = new boolean[n];

        graph = new ArrayList[n];
        undirected = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
            undirected[i] = new ArrayList<>();
        }

        for (int[] e : invocations) {
            int u = e[0], v = e[1];
            graph[u].add(v);
            undirected[u].add(v);
            undirected[v].add(u);
        }

        // Mark all suspicious methods
        dfs(k);

        // If any non-suspicious method can reach a suspicious one,
        // then nothing can be removed.
        for (int i = 0; i < n; i++) {
            if (!suspicious[i] && !visited[i]) {
                dfs2(i);
            }
        }

        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (!suspicious[i]) {
                ans.add(i);
            }
        }

        return ans;
    }

    private void dfs(int node) {
        suspicious[node] = true;
        for (int next : graph[node]) {
            if (!suspicious[next]) {
                dfs(next);
            }
        }
    }

    private void dfs2(int node) {
        visited[node] = true;
        for (int next : undirected[node]) {
            if (!visited[next]) {
                suspicious[next] = false;
                dfs2(next);
            }
        }
    }
}