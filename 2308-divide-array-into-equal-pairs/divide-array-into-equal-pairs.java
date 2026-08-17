class Solution {
    public boolean divideArray(int[] nums) {

        HashMap<Integer, Integer> map = new HashMap<>();

        // Count frequency
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        // Every frequency must be even
        for (int count : map.values()) {
            if (count % 2 != 0) {
                return false;
            }
        }

        return true;
    }
}