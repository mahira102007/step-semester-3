
public class week10q2 {
    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }

    static Node removeAll(Node head, int code) {
        while (head != null && head.data == code) {
            head = head.next;
        }

        Node current = head;

        while (current != null && current.next != null) {
            if (current.next.data == code) {
                current.next = current.next.next;
            } else {
                current = current.next;
            }
        }

        return head;
    }

    static Node createList(int[] values) {
        Node head = null;
        Node tail = null;

        for (int value : values) {
            Node node = new Node(value);

            if (head == null) {
                head = node;
                tail = node;
            } else {
                tail.next = node;
                tail = node;
            }
        }
        return head;
    }

    static void printList(Node head) {
        while (head != null) {
            System.out.print(head.data + " -> ");
            head = head.next;
        }
        System.out.println("null");
    }

    public static void main(String[] args) {
        Node head = createList(new int[]{5, 3, 5, 8, 5});
        head = removeAll(head, 5);
        printList(head);

        Node second = createList(new int[]{5, 5, 5});
        second = removeAll(second, 5);
        printList(second);
    }
}
