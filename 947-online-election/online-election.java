class TopVotedCandidate {

    int[] leader;
    int[] times;

    public TopVotedCandidate(int[] persons, int[] times) {

        this.times = times;
        leader = new int[persons.length];

        HashMap<Integer, Integer> map = new HashMap<>();

        int maxVotes = 0;
        int currentLeader = -1;

        for (int i = 0; i < persons.length; i++) {

            map.put(persons[i], map.getOrDefault(persons[i], 0) + 1);

            if (map.get(persons[i]) >= maxVotes) {
                maxVotes = map.get(persons[i]);
                currentLeader = persons[i];
            }

            leader[i] = currentLeader;
        }
    }

    public int q(int t) {

        int left = 0;
        int right = times.length - 1;

        while (left <= right) {

            int mid = left + (right - left) / 2;

            if (times[mid] <= t) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return leader[right];
    }
}
/**
 * Your TopVotedCandidate object will be instantiated and called as such:
 * TopVotedCandidate obj = new TopVotedCandidate(persons, times);
 * int param_1 = obj.q(t);
 */