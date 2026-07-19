import java.util.*;

class Solution {

    public int numMatchingSubseq(String s, String[] words) {

        HashMap<Character, List<Integer>> map = new HashMap<>();

        // Store all indices of every character
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            map.putIfAbsent(ch, new ArrayList<>());
            map.get(ch).add(i);
        }

        int count = 0;

        // Check every word
        for (String word : words) {

            int prevIndex = -1;
            boolean found = true;

            for (char ch : word.toCharArray()) {

                if (!map.containsKey(ch)) {
                    found = false;
                    break;
                }

                List<Integer> list = map.get(ch);

                int next = upperBound(list, prevIndex);

                if (next == -1) {
                    found = false;
                    break;
                }

                prevIndex = next;
            }

            if (found) count++;
        }

        return count;
    }

    // Returns first index greater than target
    private int upperBound(List<Integer> list, int target) {

        int low = 0;
        int high = list.size() - 1;
        int ans = -1;

        while (low <= high) {

            int mid = low + (high - low) / 2;

            if (list.get(mid) > target) {
                ans = list.get(mid);
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return ans;
    }
}