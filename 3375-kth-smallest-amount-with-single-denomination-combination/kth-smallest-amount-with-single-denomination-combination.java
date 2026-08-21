class Solution {
    long gcd(long a, long b) {
        while (b != 0) {
            long temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }

    long lcm(long a, long b) {
        return a / gcd(a, b) * b;
    }

    long count(long x, int[] coins) {
        int n = coins.length;
        long result = 0;

        // Inclusion-Exclusion
        for (int mask = 1; mask < (1 << n); mask++) {

            long common = 1;
            int bits = 0;
            boolean valid = true;

            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    bits++;

                    common = lcm(common, coins[i]);

                    if (common > x) {
                        valid = false;
                        break;
                    }
                }
            }

            if (!valid) continue;

            if (bits % 2 == 1) {
                result += x / common;
            } else {
                result -= x / common;
            }
        }

        return result;
    }

    public long findKthSmallest(int[] coins, int k) {

        long low = 1;
        long high = (long) coins[0] * k;

        for (int coin : coins) {
            high = Math.min(high, (long) coin * k);
        }

        while (low < high) {

            long mid = low + (high - low) / 2;

            if (count(mid, coins) >= k) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        return low;
    }
}