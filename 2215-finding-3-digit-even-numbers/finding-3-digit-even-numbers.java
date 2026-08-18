class Solution {
    public int[] findEvenNumbers(int[] digits) {

        HashSet<Integer> set = new HashSet<>();

        for (int i = 0; i < digits.length; i++) {

            // First digit cannot be 0
            if (digits[i] == 0) {
                continue;
            }

            for (int j = 0; j < digits.length; j++) {

                // Same index cannot be used twice
                if (j == i) {
                    continue;
                }

                for (int k = 0; k < digits.length; k++) {

                    // Same index cannot be used twice
                    if (k == i || k == j) {
                        continue;
                    }

                    // Last digit must be even
                    if (digits[k] % 2 != 0) {
                        continue;
                    }

                    int num = digits[i] * 100
                            + digits[j] * 10
                            + digits[k];

                    set.add(num);
                }
            }
        }

        // Convert HashSet to array
        int[] ans = new int[set.size()];
        int index = 0;

        for (int num : set) {
            ans[index++] = num;
        }

        // Sort the answer
        Arrays.sort(ans);

        return ans;
    }
}