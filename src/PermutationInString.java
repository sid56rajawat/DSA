class PermutationInString {
    // T.C -> O(len(given)), S.C -> O(1)
    public boolean checkInclusion(String target, String given) {
        if(target.length() > given.length()) return false;

        int[] targetFreq = new int[26];
        target.chars().forEach(c -> targetFreq[c - 'a'] += 1);

        int[] windowFreq = new int[26];
        int left = 0;
        for(int right = 0; right < given.length(); right++) {
            windowFreq[given.charAt(right) - 'a']++;

            int windowSize = right - left + 1;
            if(windowSize > target.length()) {
                windowFreq[given.charAt(left) - 'a']--;
                left++;
            }

            if(Arrays.equals(targetFreq, windowFreq)) return true;
        }
        
        return false;
    }
}
