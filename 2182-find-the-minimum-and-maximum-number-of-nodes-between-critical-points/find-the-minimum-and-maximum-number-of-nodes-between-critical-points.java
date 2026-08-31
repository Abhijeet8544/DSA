class Solution {
    public int[] nodesBetweenCriticalPoints(ListNode head) {

        int firstCritical = -1;
        int prevCritical = -1;

        int minDistance = Integer.MAX_VALUE;
        int maxDistance = -1;

        ListNode prev = head;
        ListNode curr = head.next;

        int position = 1;

        while (curr.next != null) {

            ListNode next = curr.next;

            // Check if current node is a critical point
            boolean isMax = curr.val > prev.val && curr.val > next.val;
            boolean isMin = curr.val < prev.val && curr.val < next.val;

            if (isMax || isMin) {

                // First critical point
                if (firstCritical == -1) {
                    firstCritical = position;
                }

                // At least two critical points
                if (prevCritical != -1) {
                    minDistance = Math.min(
                        minDistance,
                        position - prevCritical
                    );
                }

                // Distance from first to current
                maxDistance = position - firstCritical;

                prevCritical = position;
            }

            prev = curr;
            curr = next;
            position++;
        }

        // Fewer than two critical points
        if (prevCritical == -1 || prevCritical == firstCritical) {
            return new int[]{-1, -1};
        }

        return new int[]{minDistance, maxDistance};
    }
}