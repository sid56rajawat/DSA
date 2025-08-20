class LargestRectangleInHistogram {
    // T.C -> O(n), S.C -> O(n)
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        int maxArea = 0;

        int[] leftSmallerIndices = getLeftSmallerIndices(heights);
        int[] rightSmallerIndices = getRightSmallerIndices(heights);

        for (int i = 0; i < n; i++) {
            int leftIndex = leftSmallerIndices[i];
            int rightIndex = rightSmallerIndices[i];
            int currentArea = heights[i] * (rightIndex - leftIndex - 1);
            maxArea = Math.max(maxArea, currentArea);
        }

        return maxArea;
    }

    private int[] getLeftSmallerIndices(int[] heights) {
        int n = heights.length;
        int[] leftSmallerIndices = new int[n];
        for (int i = 0; i < n; i++) {
            int prevIndex = i - 1;
            while (prevIndex >= 0 && heights[prevIndex] >= heights[i]) {
                prevIndex = leftSmallerIndices[prevIndex];
            }
            leftSmallerIndices[i] = prevIndex;
        }
        return leftSmallerIndices;
    }

    private int[] getRightSmallerIndices(int[] heights) {
        int n = heights.length;
        int[] rightSmallerIndices = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            int nextIndex = i + 1;
            while (nextIndex < n && heights[nextIndex] >= heights[i]) {
                nextIndex = rightSmallerIndices[nextIndex];
            }
            rightSmallerIndices[i] = nextIndex;
        }
        return rightSmallerIndices;
    }
}
