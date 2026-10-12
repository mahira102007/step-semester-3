
public class week10q1 {
    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }

    Node head;

    void addFirst(int token) {
        Node node = new Node(token);
        node.next = head;
        head = node;
    }

    void addLast(int token) {
        Node node = new Node(token);

        if (head == null) {
            head = node;
            return;
        }

        Node current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = node;
    }

    void insertAt(int index, int token) {
        if (index < 0) {
            throw new IllegalArgumentException("Invalid index");
        }

        if (index == 0) {
            addFirst(token);
            return;
        }

        Node current = head;
        for (int i = 0; i < index - 1; i++) {
            if (current == null) {
                throw new IndexOutOfBoundsException();
            }
            current = current.next;
        }

        if (current == null) {
            throw new IndexOutOfBoundsException();
        }

        Node node = new Node(token);
        node.next = current.next;
        current.next = node;
    }

    void printList() {
        Node current = head;
        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }

    public static void main(String[] args) {
        week10q1 list = new week10q1();

        list.addLast(101);
        list.addLast(102);
        list.addLast(103);

        list.addFirst(100);
        list.addLast(104);
        list.insertAt(2, 150);

        list.printList();
    }
}
