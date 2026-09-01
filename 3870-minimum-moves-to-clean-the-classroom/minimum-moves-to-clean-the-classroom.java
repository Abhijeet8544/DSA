import java.util.*;

class Solution {

    static class State {
        int row;
        int col;
        int mask;
        int energy;

        State(int row, int col, int mask, int energy) {
            this.row = row;
            this.col = col;
            this.mask = mask;
            this.energy = energy;
        }
    }

    public int minMoves(String[] classroom, int energy) {

        int m = classroom.length;
        int n = classroom[0].length();

        int startRow = 0;
        int startCol = 0;

        // Store litter index for every cell
        int[][] litterIndex = new int[m][n];

        for (int i = 0; i < m; i++) {
            Arrays.fill(litterIndex[i], -1);
        }

        int litterCount = 0;

        // Find S and assign index to every L
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                char ch = classroom[i].charAt(j);

                if (ch == 'S') {
                    startRow = i;
                    startCol = j;
                }

                if (ch == 'L') {
                    litterIndex[i][j] = litterCount;
                    litterCount++;
                }
            }
        }

        // No litter to collect
        if (litterCount == 0) {
            return 0;
        }

        // All litter collected mask
        int fullMask = (1 << litterCount) - 1;

        /*
         * visited[row][col][mask][energy]
         *
         * true = this state has already been visited
         */
        boolean[][][][] visited =
            new boolean[m][n][1 << litterCount][energy + 1];

        Queue<State> queue = new LinkedList<>();

        // Starting state
        queue.offer(
            new State(startRow, startCol, 0, energy)
        );

        visited[startRow][startCol][0][energy] = true;

        int[][] directions = {
            {1, 0},
            {-1, 0},
            {0, 1},
            {0, -1}
        };

        int moves = 0;

        while (!queue.isEmpty()) {

            int size = queue.size();

            // Process all states at current distance
            while (size-- > 0) {

                State current = queue.poll();

                int row = current.row;
                int col = current.col;
                int mask = current.mask;
                int currentEnergy = current.energy;

                // All litter collected
                if (mask == fullMask) {
                    return moves;
                }

                /*
                 * If energy is 0, we cannot make another move
                 * unless we are standing on R.
                 */
                if (currentEnergy == 0 &&
                    classroom[row].charAt(col) != 'R') {
                    continue;
                }

                for (int[] direction : directions) {

                    int newRow = row + direction[0];
                    int newCol = col + direction[1];

                    // Outside grid
                    if (newRow < 0 || newRow >= m ||
                        newCol < 0 || newCol >= n) {
                        continue;
                    }

                    // Cannot move through obstacle
                    if (classroom[newRow].charAt(newCol) == 'X') {
                        continue;
                    }

                    // One move costs one energy
                    int newEnergy = currentEnergy - 1;

                    if (newEnergy < 0) {
                        continue;
                    }

                    int newMask = mask;

                    // If we reach litter, collect it
                    if (classroom[newRow].charAt(newCol) == 'L') {

                        int index = litterIndex[newRow][newCol];

                        newMask = mask | (1 << index);
                    }

                    // If we reach reset area, restore energy
                    if (classroom[newRow].charAt(newCol) == 'R') {
                        newEnergy = energy;
                    }

                    // Avoid revisiting the same state
                    if (!visited[newRow][newCol][newMask][newEnergy]) {

                        visited[newRow][newCol][newMask][newEnergy] = true;

                        queue.offer(
                            new State(
                                newRow,
                                newCol,
                                newMask,
                                newEnergy
                            )
                        );
                    }
                }
            }

            moves++;
        }

        // Impossible to collect all litter
        return -1;
    }
}