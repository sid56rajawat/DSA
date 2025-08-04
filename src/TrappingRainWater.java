public class TrappingRainWater {
    private enum Direction {
        RTL,
        LTR
    }

    public int trap(int[] height) {
        // go ltr ->
        // set pointer to current and move till
        // next pointer's height is greater
        // then add the water up in between and move the
        // first pointer in place of second and repeat till end
        // do the same for rtl

        return trapRainWater(height, Direction.LTR, true) + trapRainWater(height, Direction.RTL, false);
    }

    private int trapRainWater(int[] height, Direction direction, boolean countEqual) {
        int len = height.length;
        int step = direction == Direction.LTR ? 1 : -1;

        int pivot = direction == Direction.LTR ? 0 : len - 1;
        int edge = direction == Direction.LTR ? len : -1;

        int bucket = 0;
        int tumbler = 0;

        for (int lookahead = pivot; lookahead != edge; lookahead += step) {
            int pivotElevation = height[pivot];
            int lookaheadElevation = height[lookahead];

            if (isTrappedByFirstHeight(pivotElevation, lookaheadElevation, countEqual)) {
                bucket += tumbler;
                pivot = lookahead;
                tumbler = 0;
                continue;
            }

            tumbler += pivotElevation - lookaheadElevation;
        }

        return bucket;
    }

    private boolean isTrappedByFirstHeight(int firstHeight, int secondHeight, boolean countEqual) {
        return secondHeight > firstHeight
                || (countEqual && secondHeight == firstHeight);
    }
}