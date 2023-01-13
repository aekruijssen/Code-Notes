/* Represents a sequence of nodes.
    Singly linked list - each nose points to next node
    Doubly linked list - each node points to both next and previous nodes

    Unlike array, LL does NOT provide constant time access to a particular "index" in list.
    To find Kth element, must iterate through K elements

    Can add and remove items from the beginning of list in constant time
*/


// Singly Linked List:
class Node {
    Node next = null;
    int data;

    public Node(int d) {
        data = d;
    }

    void appendToTail(int d) {
        Node end = new Node(d);
        Node n = this;
        while (n.next != null) {
            n = n.next;
        }
        n.next = end;
    }
}

/* Need to be careful with this implementation
    Access linked list through reference to head Node
    If multiple objects need a reference to list, and head changes 
    -> some objects may still point to old head
   Can instead implement LinkedList class that wraps Node class, with member var = head Node
*/


/* Deleting a node
    - find previous node prev, set prev.next to n.next
    - if doubly linked, also update n.next: n.next.prev = n.prev
    - Check for null pointer and update head/tail pointer
    - in language without garbage collector (C, C++), also deallocate removed node
*/
Node deleteNode(Node head, int d) {
    if (head == null) return null;
    Node n = head;

    if (n.data == d) {
        if (n.next.data == d) {
            n.next = n.next.next;
            return head; // head did not change
        }
        n = n.next;
    }
    return head;
}


/* Runner Technique
    - Iterate through LL with 2 pointers, one ahead of other
    - Fast node may be ahead by fixed amount or hop multiple nodes for each 1 slow node iterated through

    a1 -> a2 -> ... -> an -> b1 -> b2 -> ... -> bn
    a1 -> b1 -> a2 -> b2 -> ... -> an -> bn

    - do not know length, but know it is even

    1) Fast pointer p1 moves 2 elements for every 1 move p2 makes
    2) when p1 hits end, p2 at midpoint
    3) move p1 back to front and "weave" elements
        - on each iteration, p2 selects an element and inserts it after p1

    Steps:
    S1) P1 = a1, P2 = a1
    S2) P1 = an, P2 = a2
    S3) P1 = b2, P2 = an
    S4) P1 hits end, P2 = b1
     - P1 = a1, P2 = b1, list = [a1, a2, an, b1, b2, bn]
    S5) P1 = a1, P2 = b1, list ->[a1, b1, a2, an, b2, bn]
    S6) P1 = a2, P2 = a2, list -> [a1, b1, a2, an, b2, bn]
    S7) P1 = b2, P2 = an, list -> [a1, b1, a2, b2, an, bn]
    S8) P1 hits end, P2 = bn (tail), list sorted
*/

/* Recursive Problems
    - LL problems often rely on recursion
    - Any recursive problem takes at least O(n) space, n = depth
    - All recursive algorithms are possible to implement iteratively
*/