class LRUCache {
    // T.C -> O(1), S.C -> O(n)
    private Map<Integer, ListNode> cache = new HashMap<>();
    private ListNode head;
    private ListNode tail;
    private int currentSize = 0;
    private int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        var node = new ListNode(-1, -1);
        this.head = node;
        this.tail = node;
    }

    public int get(int key) {
        if (!cache.containsKey(key))
            return -1;
        ListNode node = cache.get(key);
        moveNodeToLast(node);
        return node.value;
    }

    public void put(int key, int value) {

        // update if exists
        if(cache.containsKey(key)) {
            var node = cache.get(key);
            node.value = value;

            moveNodeToLast(node);
            return;
        }

        // if going over capacity
        if(currentSize == capacity) {
            // delete LRU
            var lruNode = head.next;
            head.next = lruNode.next;
            if(lruNode.next != null) {
                lruNode.next.prev = head;
            }
            cache.remove(lruNode.key);
            if(lruNode == tail) tail = head;
            currentSize--;
        }

        // put if doesn't
        var node = new ListNode(key, value, null, tail);
        tail.next = node;
        tail = node;
        cache.put(key, node);

        currentSize++;
    }

    private void moveNodeToLast(ListNode node) {
        // if already at last return
        if(node == tail) return;

        // move out of list
        node.prev.next = node.next;
        if (node.next != null) {
            node.next.prev = node.prev;
        }

        // detatch from between and add to last
        node.next = null;
        node.prev = tail;

        // replace tail
        tail.next = node;
        tail = node;
    }

    private void printList(ListNode head) {
        ListNode temp = head;
        while(temp != null) {
            System.out.print("(" + temp.key + ", " + temp.value + ")  ->");
            temp = temp.next;
        }
        System.out.println(" null");
    }
}

class ListNode {
    public int key;
    public int value;
    public ListNode next;
    public ListNode prev;

    public ListNode(int key, int value) {
        this.key = key;
        this.value = value;
        this.next = null;
        this.prev = null;
    }

    public ListNode(int key, int value, ListNode next, ListNode prev) {
        this.key = key;
        this.value = value;
        this.next = next;
        this.prev = prev;
    }
}
