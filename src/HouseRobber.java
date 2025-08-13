class HouseRobber {
    public int rob(int[] houses) {
        int[][] memo = new int[houses.length][2];
        initializeMemo(memo, houses.length, 2);
        return rob(0, false, houses, memo);
    }

    private int rob(int houseNumber, boolean previousRobbed, int[] houses, int[][] memo) {
        if(houseNumber == houses.length) return 0;
        int pr = previousRobbed ? 1 : 0;
        if(memo[houseNumber][pr] != -1) return memo[houseNumber][pr];

        int profitIfRobbed = previousRobbed ? 
            0 : 
            houses[houseNumber] + rob(houseNumber + 1, true, houses, memo);
        int profitIfLeft = rob(houseNumber + 1, false, houses, memo);

        int maxProfit = Math.max(profitIfRobbed, profitIfLeft);
        memo[houseNumber][pr] = maxProfit;
        return maxProfit;
    }

    private void initializeMemo(int[][] memo, int rows, int cols) {
        for(int i=0; i < rows; i++) {
            for(int j=0; j < cols; j++) {
                memo[i][j] = -1;
            }
        }
    }
}
