class SearchA2DMatrix {
    // T.C -> O(log(m*n)), S.C -> O(m)
    public boolean searchMatrix(int[][] matrix, int target) {
        int[] rowFinder = new int[matrix.length];
        for(int i=0; i<matrix.length; i++) {
            rowFinder[i] = matrix[i][0];
        }

        int rowIndex = binarySearch(rowFinder, target, true);
        if(rowIndex == -1) return false;

        int[] arr = matrix[rowIndex];
        return binarySearch(arr, target, false) > -1;
    }

    private int binarySearch(int[] arr, int target, boolean returnClosestSmaller) {
        int left = 0;
        int right = arr.length - 1;
        while(left <= right) {
            int mid = left + ((right - left)/ 2);
            if(arr[mid] == target) return mid;

            if(target > arr[mid]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return returnClosestSmaller ? right : -1;
    }
}
