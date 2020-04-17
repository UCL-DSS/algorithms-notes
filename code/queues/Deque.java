import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node head;
    private Node tail;
    private int dequeSize;

    private class Node {
        Item val;
        Node prev;
        Node next;
    }

    // construct an empty deque
    public Deque() {
        dequeSize = 0;
        head = null;
        tail = null;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return dequeSize == 0;
    }

    // return the number of items on the deque
    public int size() {
        return dequeSize;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("The argument cannot be null");
        }

        Node newHead = new Node();
        newHead.val = item;
        ++dequeSize;

        if (size() == 1) {
            head = newHead;
            tail = newHead;
        } else {
            Node tempHead = head;
            head = newHead;
            newHead.next = tempHead;
            tempHead.prev = newHead;
        }

    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("The argument cannot be null");
        }

        Node newTail = new Node();
        newTail.val = item;
        ++dequeSize;

        if (size() == 1) {
            head = newTail;
            tail = newTail;
        } else {
            Node tempTail = tail;
            tail = newTail;
            newTail.prev = tempTail;
            tempTail.next = newTail;
        }


    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("The deque is empty");
        }

        Item result = head.val;
        head.val = null;
        head = head.next;

        --dequeSize;

        if (isEmpty()) {
            tail = null;
        } else {
            head.prev = null;
        }
        return result;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("The deque is empty");
        }

        Item result = tail.val;
        tail.val = null;
        tail = tail.prev;

        --dequeSize;

        if (isEmpty()) {
            head = null;
        } else {
            tail.next = null;
        }
        return result;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = head;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException("Method no longer supported");
        }

        public Item next() {
            if (current == null) {
                throw new java.util.NoSuchElementException("No more item to return");
            }

            Item item = current.val;
            current = current.next;
            return item;
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<String> testDeque = new Deque<String>();
        testDeque.addFirst("Hi");
        testDeque.addLast("This");
        testDeque.addLast("is");
        testDeque.addLast("Tony");
        testDeque.addLast("bro");
        testDeque.addFirst("hiya");
        testDeque.removeFirst();
        testDeque.removeLast();

        Iterator<String> i = testDeque.iterator();
        while (i.hasNext()) {
            String s = i.next();
            StdOut.println(s);
        }
    }

}
