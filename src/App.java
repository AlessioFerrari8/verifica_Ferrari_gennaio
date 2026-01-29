public class App {
    public static void main(String[] args) throws Exception {
        
        DoublyLinkedList dl1 = new DoublyLinkedList();
        DoublyLinkedList dl2 = new DoublyLinkedList();
        DoublyLinkedList dl3 = new DoublyLinkedList();

        Node n1 = new Node('A');
        Node n2 = new Node('B');
        Node n3 = new Node('C');
        Node n4 = new Node('D');
        Node n5 = new Node('E');
        Node n6 = new Node('F');
        Node n7 = new Node('F');
        Node n8 = new Node('E');
        Node n9 = new Node('E');
        
        dl1.add(n1);
        dl1.add(n2);
        dl1.add(n3);
        dl1.add(n4);
        dl1.add(n5);
        dl1.print(true);
        dl1.print(false);
        System.out.println(dl1.size());
        

        // dl2.add(n4);
        // dl2.add(n5);
        // dl1.merge(dl2);
        // dl1.print(true);
        // dl1.print(false);
        // System.out.println(dl1.size());
        // dl3.add(n6);
        // dl3.add(n7);
        // dl3.add(n8);
        // dl3.add(n9);
        // dl3.print(true);
        // dl3.print(false);
        // dl1.merge(dl3);
        // dl1.print(true);
        // System.out.println(dl1.size());

        System.out.println(dl1.slice(3, 1));
        dl3.add(n5);
        dl3.add(n6);
        dl3.add(n8);
        dl3.print(true);
        System.out.println(dl3.palindrome());

        dl3.shift(1);
        dl3.print(true);



        // and so on...
    }
}
