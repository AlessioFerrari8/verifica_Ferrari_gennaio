public class DoublyLinkedList {
    
    private Node head, tail;

    /**
     * empty constructor
     */
    public DoublyLinkedList() {
        this.head = this.tail = null;
    }

    // don't need this since the add is gonna do the work
    // /**
    //  * Constructor with two params, new head and new tail 
    //  */
    // public DoublyLinkedList(Node newHead, Node newTail) {
    //     this.head = newHead;
    //     this.tail = newTail;
    //     this.head.setNext(this.tail);
    //     this.tail.setPrev(this.head);
    // }

    /**
     * Adds a node at the end of the list
     * @param newNode new node to be added
     */
    public void add(Node newNode) {
        if (head == null && tail == null) {
            head = tail = newNode;
        }
        else {
            newNode.setPrev(tail);
            tail.setNext(newNode);
            tail = newNode;
        }
    }

    // wrapper
    private Node getRec(Node cursor, int index) {

        if (index == 0)
            return cursor;

        return getRec(cursor.getNext(), index-1);
    }

    /**
     * Gets the element in the specified position
     * @param index position to read
     * @return the element identified by the index
     * @throws IndexOutOfBoundsException
     */
    public Node get(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size())
            throw new IndexOutOfBoundsException("Invalid index");

        return getRec(head, index);
    }

    // ------------ INSERT YOUR CODE HERE ----------------

    /**
     * print(boolean forward), prints every single node in a specific direction: 
     * starting from head if the parameter is true (meaning that the scan is made forward), 
     * starting from tail if the parameter is false (meaning that the scan is made backward)
     */
    public void print(boolean forward) {
        Node current;
        if (this.head == null && forward) { // if the head doesn't exist and the direction is forward
            return;
        }
        // if the tail doesn't exist and the direction is backward
        if (this.tail == null && forward == false) {
            return;
        }   
        
        if (forward) {
            current = this.head;
            System.out.print("[");
            while (current != null) {
                if (current != this.tail) {
                    System.out.print(current.getLetter() + ", ");
                } else {
                    System.out.print(current.getLetter());
                }
                // update
                current = current.getNext();
            }
            System.out.println("]");
        } else if (forward == false) {
            current = this.tail;
            System.out.print("[");
            while (current != null) { // till null
                if (current != this.head) {
                    System.out.print(current.getLetter() + ", ");
                } else {
                    System.out.print(current.getLetter());
                }
                current = current.getPrev();
            }
            System.out.println("]");
        }
    }

    /**
     * counts how many nodes are present in the list, no matter the direction
     * @return the size
     */
    public int size() {
        if (this.head == null) {
            return 0;
        }
        int c = 0;
        Node cursor = this.head; // i prefer to start from the head
        while (cursor != null) { // till null
            cursor = cursor.getNext();
            c++;
        }
        return c;
    }

    /**
     * merge(DoublyLinkedList otherList), receives another doubly-linked list as an 
     * input parameter and appends it at the end of the current list
     * @param otherList
     * @return if it's done or not
     */
    public boolean merge(DoublyLinkedList otherList) {
        // TODO: controllare se ha senso o no
        if (otherList.head == null && this.tail == null) { // 
            return false;
        }
        if (otherList.size() == 0) {
            return false;
        }
        // to the old tail I add the head of the new list
        this.tail.setNext(otherList.head);
        // I set the prev
        otherList.head.setPrev(this.tail);
        // update the tail
        this.tail = otherList.tail;
        return true;
    }

    public String slice(int start, int end) {
        String result = "";
        if (start == end) {
            return "";
        }
        if (size() - 1 < end) {
            return "";
        }
        if (start < end) { // forward
            // references to nodes
            Node first = get(start);
            Node last = get(end);
            while (first != last) {
                // update the string
                result += first.getLetter();
                // update the reference
                first = first.getNext();
            }
            return result;
        }
        if (start > end) { // backward
            // references to nodes
            Node first = get(start);
            Node last = get(end);
            while (last != first) {
                // update the string
                result += first.getLetter();
                // update the reference
                first = first.getPrev();
            }
            return result;
        }
        return "";
    }

    // I'm recursive!
    /**
     * boolean palindrome( ), checks whether the data stored 
     * in the nodes form a palindrome string, 
     * meaning that the nodes are symmetric (only considering the value inside!)
     * @return
     */
    public boolean palindrome() {
        String total = "";
        Node current = this.head;
        // build the string
        while (current != null) {
            total += current.getLetter();
            current = current.getNext();
        }
        for (int i = 0; i < total.length() / 2; i++) {
            if(total.charAt(i) != total.charAt(total.length() - 1 - i)) {
                return false;
            }
        }
        return true;
    }

    public boolean palindromeRec(Node first, Node last, int index) {
        first = this.head;
        last = this.tail;
        if (first.getLetter() != last.getLetter()) { // two chars are different
            return false;
        }
        if (index == size() % 2) {
            return true;
        }

        return palindromeRec(first.getNext(), last.getPrev(), --index);
    }


    /**
     * shift(int amount), shifts many elements in the list 
     * (equal to "amount" parameter) from the last position to the first position
     * @param amount
     */
    public void shift(int amount) {
        // // mi creo una lista con i nodi da spostare
        // DoublyLinkedList toShift = new DoublyLinkedList();
        // int c = 0;
        // int ind = size() - 1;
        // while (c < amount) {
        //     toShift.add(get(ind));
        //     c++;
        //     ind--;
        // }

        // // tolgo i riferimenti che collegano questi alla lista
        // get(ind).setPrev(null);
        // get(--ind).setNext(null);

        // // faccio la merge
        // toShift.merge(this);
        // this.head = toShift.head;
        // this.tail = get(ind);
        int c = 0;

        // shift porto ogni volta in testa
        while (c < amount) {
            this.head.setPrev(this.tail);
            this.tail.setPrev(null);
            this.tail.setNext(this.head);
            this.head = this.head.getPrev();
            this.tail = get(size() - 1);
            this.tail.setNext(null);
            c++;
        }
    }

    
    /**
     * trim(int newSize), sets the new length of the list to the input parameter:
    a. if newSize is less than the actual length, the list is trimmed making 
    its size equal to it, removing some nodes
    b. if newSize is greater than the actual length, 
    new nodes are added to the tail in order to make its size equal to it. 
    Every node added needs to have a specified content, which will be the 
    next ASCII characters starting from the tail
    Example:
    ABCDEFG -> (newSize is 4) -> ABCD (the list is trimmed, now its length is 4) 
    FWIQ -> (newSize is 7) -> FWIQRST (the list is now made of 7 nodes, 
    the added ones are following the ASCII rule)
     * @param newSize
     */
    public void trim(int newSize) {
        if (newSize == size()) {
            return;
        }
        if (newSize < size()) {
            // i get the last element
            Node newTail = get(newSize - 1);
            // cut the list
            newTail.setNext(null);
            this.tail = newTail; // update the tail
        } else {
            // size is automatic so i don't have to update anything
            while (size() < newSize) {
                // i add the next ascii one
                this.add(new Node((char)(((int)this.tail.getLetter()) + 1)));
                this.tail = get(size() - 1); // update the tail
            }
        }
    }
}
