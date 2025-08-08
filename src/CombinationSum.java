import java.util.LinkedList;
import java.util.List;

class CombinationSum {
    // T.C -> O(2^n), S.C -> O(n)
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> allCombinations = new LinkedList<>();
        List<Integer> currentCombination = new LinkedList<>();
        combinations(candidates, target, 0, 0, currentCombination, allCombinations, false);
        return allCombinations;
    }

    private void combinations(int[] candidates, int target, int index, int sum, List<Integer> currentCombination, List<List<Integer>> allCombinations, boolean sameIndex) {

        if(index == candidates.length) return;
        int candidate = candidates[index];

        if(!sameIndex) {
            combinations(candidates, target, index + 1, sum, currentCombination, allCombinations, false);
        }

        sum += candidate;
        currentCombination.add(candidate);

        if(sum > target) {
            currentCombination.removeLast();
            return;
        }

        if(sum == target) {
            List<Integer> copy = new LinkedList<>(currentCombination);
            allCombinations.add(copy);
            currentCombination.removeLast();
            return;
        }

        combinations(candidates, target, index, sum, currentCombination, allCombinations, true);
        combinations(candidates, target, index + 1, sum, currentCombination, allCombinations, false);
        currentCombination.removeLast();
    }
}
