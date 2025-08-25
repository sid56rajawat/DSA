class RemoveNthNodeFromEndofList {
    // T.C -> O(n), S.C -> O(1)

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode lookahead = head;
        for(int i=0; i<n; i++) lookahead = lookahead.next;

        if(lookahead == null) return head.next; // to delete head

        ListNode curr = head;
        while(lookahead.next != null) {
            curr = curr.next;
            lookahead = lookahead.next;
        }
        curr.next = curr.next.next;

        return head;
    }

    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
