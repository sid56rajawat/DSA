class LongestConsecutiveSequence {
    // T.C -> O(n), S.C -> O(n)
    public int longestConsecutive(int[] nums) {
        Set<Integer> numSet = new HashSet<>();
        Arrays.stream(nums).forEach(numSet::add);

        int ans = 0;
        Set<Integer> visited = new HashSet<>();
        for(int num : nums) {
            if(visited.contains(num)) continue;
            int reach = findReach(num, numSet, visited);
            ans = Math.max(ans, reach);
        }
        return ans;
    }

    public int findReach(int num, Set<Integer> numSet, Set<Integer> visited) {
        if(!numSet.contains(num)) return 0;
        if(visited.contains(num)) return 0;
        visited.add(num);
        return 1 + findReach(num - 1, numSet, visited) + findReach(num + 1, numSet, visited);
    }
}
