/*
    Stack LIFO
    pop() - Remove top item from stack
    push(item) - Add item to top of stack
    peek() - Return top of stack
    isEmpty() - Return true iff stack empty

    Unlike array, stack does NOT have constant-time access to ith item
    Stack has constant-time adds and removes (does not require shifting elements around)

    Stack can be implemented with linked list, if items added and removed from same side

    Stacks are useful in recursive algorithms
    Push temp data onto stack as you recurse, then remove as you backtrack

*/

public class MyStack<T> {
    private static class StackNode<T> {
        private T data;
        private StackNode<T> next;

        public StackNode(T data) {
            this.data = data;
        }
    }

    private StackNode<T> top;

    public T pop() {
        if (top == null) throw new EmptyStackException();
        T item = top.data;
        top = top.next;
        return item;
    }

    public void push(T item) {
        StackNode<T> t = new StackNode<T>(item);
        t.next = top;
        top = t;
    }

    public T peek() {
        if (top == null) throw new EmptyStackException();
        return top.data;
    }

    public boolean isEmpty() {
        return top == null;
    }
}


/*
    Queue FIFO

    add(item) - Add item to end of queue
    remove() - Remove first item in queue
    peek() - Return to top of queue
    isEmpty() - Return true iff queue empty

    Queue can also be implemented with a linked list, 
    and are same if items added and removed from opposite sides

    Queues useful for breadth-first search or implementing a cache
    in BDF, queue stores list of nodes to process.
    each time we process node, add its adjecent nodes to back of queue
*/

public class MyQueue<T> {
    private static class QueueNode<T> {
        private T data;
        private QueueNode<T> next;

        public QueueNode(T data) {
            this.data = data;
        }
    }

    private QueueNode<T> first;
    private QueueNode<T> last;

    public void add(T item) {
        QueueNode<T> t = new QueueNode<T> item;
        if (last != null) {
            last.next = t;
        }
        last = t;
        if (first == null) {
            first = last;
        }
    }

    public T remove() {
        if (first == null) throw new NoSuchElementException();
        T data = first.data;
        first = first.next;
        if (first == null) {
            last = null;
        }
        return data;
    }

    public T peek() {
        if (first == null) throw new NoSuchElementException();
        return first.data;
    }

    public boolean isEmpty() {
        return first == null;
    }
}