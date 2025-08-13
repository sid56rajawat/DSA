class JumpGame {
    // T.C -> O(n), S.C -> O(1)

    public boolean canJump(int[] nums) {
        return canJump(nums, 0);
    }

    private boolean canJump(int[] stairs, int currPos) {
        if(currPos == stairs.length - 1) return true;
        int nextPos = nextBestPos(stairs, currPos);
        if(nextPos == currPos) return false;
        return canJump(stairs, nextPos);
    }

    private int nextBestPos(int[] stairs, int currPos) {
        if(stairs[currPos] == 0) return currPos;

        int maxReachability = 0;
        int bestNextPos = currPos;
        
        for(int lookahead = currPos + 1; lookahead <= Math.min(stairs.length - 1, currPos + stairs[currPos]); lookahead++) {
            int possibleReachability = lookahead + stairs[lookahead];
            if(possibleReachability > maxReachability){ 
                maxReachability = possibleReachability; 
                bestNextPos = lookahead;
            }
        }
        return bestNextPos;
    }
}
