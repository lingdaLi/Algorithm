import Models.Node;

public class CopyRandomList {
    public Node copyRandomList(Node head) {
        if(head == null) return head;

        head = this.copyNodes(head);
        head = this.copyRandomNodes(head);

        return this.extractCopy(head);
    }

    public void testCase() {
        Node n1 = new Node(1);
        Node n2 = new Node(2);

        n1.next = n2;
        n1.random = n2;

        n2.random = n2;

        this.copyRandomList(n1);
    }

    private Node copyNodes(Node head) {
        Node pointer = head;

        while(pointer != null) {
            Node temp = pointer.next;
            Node copy = new Node(pointer.val);

            pointer.next = copy;
            copy.next = temp;
            pointer = temp;
        }

        return head;
    }

    private Node copyRandomNodes(Node head) {
        Node pointer = head;

        while(pointer != null) {
            Node temp = pointer.next;

            if(pointer.random != null) {
                temp.random = pointer.random.next;
            }

            pointer = temp.next;
        }

        return head;
    }

    private Node extractCopy(Node head) {
        Node start = head.next;
        Node pointer = head;
        Node pre = null;

        while (pointer != null) {
            Node temp = pointer.next;
            pointer.next = temp.next;

            if(pre != null) pre.next = temp;
            pre = temp;
            pre.next = null;

            pointer = pointer.next;
        }

        return start;
    }
}
