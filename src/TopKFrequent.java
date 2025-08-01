import java.util.*;

class TopKFrequent {
    public int[] topKFrequent(int[] nums, int k) {
        // T.C -> O(n), S.C -> O(n)
        Map<Integer, Integer> freq = new HashMap<>();
        Arrays.stream(nums).forEach((num) -> {
            Integer oldFreq = freq.getOrDefault(num, 0);
            freq.put(num, oldFreq + 1);
        });

        ArrayList<LinkedList<Integer>> numsWithFreq = new ArrayList<>(Collections.nCopies(nums.length + 1, null));
        freq.forEach((num, f) -> {
            if(numsWithFreq.get(f) == null) numsWithFreq.set(f, new LinkedList<>());
            numsWithFreq.get(f).addFirst(num);
        });

        int[] ans = new int[k];
        int ansPos = 0;
        int f = nums.length;
        while (ansPos < k && f > 0) {
            if(numsWithFreq.get(f) == null) {
                f--;
                continue;
            }

            var list = numsWithFreq.get(f);
            while(!list.isEmpty() && ansPos < k) {
                ans[ansPos] = list.getFirst();
                ansPos++;
                list.removeFirst();
            }
            f--;
        }

        return ans;
    }
}
