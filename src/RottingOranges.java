
class RottingOranges {
    // T.C -> O(m^2 + n^2), S.C -> O(1)
    private static final int FRESH = 1;
    private static final int ROTTEN = 2;
    private static final int ABOUT_TO_ROT = 3;

    public int orangesRotting(int[][] grid) {
        int minutes = 0;
        int prevOranges = Integer.MAX_VALUE;
        int remainingOranges = countOranges(grid, FRESH);

        while(remainingOranges > 0 && remainingOranges < prevOranges) {
            prevOranges = remainingOranges;
            remainingOranges -= spreadRot(grid);
            minutes++;
        }

        return remainingOranges == 0 ? minutes : -1;
    }

    private int countOranges(int[][] grid, int type) {
        int count = 0;
        for(int[] row : grid) {
            for(int cell : row) {
                count += cell == type ? 1 : 0;
            }
        }
        return count;
    }

    private int spreadRot(int[][] grid) {
        for(int i=0; i < grid.length; i++) {
            for(int j=0; j < grid[i].length; j++) {
                if(grid[i][j] == ROTTEN) {
                    rotAdjacent(grid, i, j);
                }
            }
        }
        return actuallyRot(grid);
    }

    private void rotAdjacent(int[][] grid, int i, int j) {
        if(i+1 < grid.length && grid[i+1][j] == FRESH) grid[i+1][j] = ABOUT_TO_ROT; // down
        if(i-1 >= 0 && grid[i-1][j] == FRESH) grid[i-1][j] = ABOUT_TO_ROT; // up
        if(j+1 < grid[i].length && grid[i][j+1] == FRESH) grid[i][j+1] = ABOUT_TO_ROT; // right
        if(j-1 >= 0 && grid[i][j-1] == FRESH) grid[i][j-1] = ABOUT_TO_ROT; // left
    }

    private int actuallyRot(int[][] grid) {
        int newRotten = 0;
        for(int i=0; i < grid.length; i++) {
            for(int j=0; j < grid[i].length; j++) {
                if(grid[i][j] == ABOUT_TO_ROT) {
                    grid[i][j] = ROTTEN;
                    newRotten++;
                }
            }
        }
        return newRotten;
    }
}
