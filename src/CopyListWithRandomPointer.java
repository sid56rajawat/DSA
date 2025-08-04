class CopyListWithRandomPointer {
    // T.C -> O(n), S.C -> O(n)
    public Node copyRandomList(Node head) {
        Map<Node, Node> newNodeForOld = new HashMap<>();
        Node curr = head;
        while(curr != null) {
            newNodeForOld.put(curr, new Node(curr.val));
            curr = curr.next;
        }

        curr = head;
        while(curr != null) {
            Node newNode = newNodeForOld.get(curr);
            newNode.next = newNodeForOld.get(curr.next);
            newNode.random = newNodeForOld.get(curr.random);
            curr = curr.next;
        }
        return newNodeForOld.get(head);
    }
}
