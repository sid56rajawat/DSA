class WordDictionary {
    private TrieNode head;

    public WordDictionary() {
        head = new TrieNode('.', false);
    }
    
    public void addWord(String word) {
        TrieNode curr = head;
        char[] arr = word.toCharArray();
        for(int i=0; i< arr.length; i++) {
            char c = arr[i];
            if(curr.nexts.get(c) == null) {
                curr.nexts.put(c, new TrieNode(c, false));
            }
            curr = curr.nexts.get(c);
            if(i == arr.length - 1) curr.isTerminal = true;
        }
    }
    
    public boolean search(String word) {
        return dfs(word, head);
    }

    private boolean dfs(String word, TrieNode curr) {
        if(word.isEmpty()) {
            return curr.isTerminal;
        }

        char c = word.charAt(0);
        if(c == '.') {
            boolean ans = false;
            for(var node : curr.nexts.values()) {
                ans = ans || dfs(word.substring(1), node);
            }
            return ans;
        }

        if(curr.nexts.get(c) == null) return false;
        return dfs(word.substring(1), curr.nexts.get(c));
    }
}

class TrieNode {
    char value;
    boolean isTerminal;
    Map<Character, TrieNode> nexts;

    public TrieNode(char value, boolean isTerminal) {
        this.value = value;
        this.isTerminal = isTerminal;
        this.nexts = new HashMap<>();
    }
}
