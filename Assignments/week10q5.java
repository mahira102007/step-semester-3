
public class week10q5 {
    static class Node {
        String name;
        Node next;

        Node(String name) {
            this.name = name;
        }
    }

    Node tail;

    void addLast(String name) {
        Node node = new Node(name);

        if (tail == null) {
            tail = node;
            tail.next = tail;
        } else {
            node.next = tail.next;
            tail.next = node;
            tail = node;
        }
    }

    void printTurns(int turns) {
        if (tail == null) {
            return;
        }

        Node current = tail.next;

        for (int i = 0; i < turns; i++) {
            System.out.print(current.name);

            if (i < turns - 1) {
                System.out.print(" ");
            }

            current = current.next;
        }

        System.out.println();
    }

    public static void main(String[] args) {
        week10q5 game = new week10q5();

        game.addLast("Asha");
        game.addLast("Ravi");
        game.addLast("Neha");

        game.printTurns(7);
    }
}
