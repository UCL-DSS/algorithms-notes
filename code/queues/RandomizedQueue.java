import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Node head, tail;
    private int queueSize;

    private class Node {
        Item val;
        Node prev;
        Node next;
    }

    // construct an empty randomized queue
    public RandomizedQueue() {
        queueSize = 0;
        head = null;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return queueSize == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return queueSize;
    }

    // add the item
    public void enqueue(Item item) {

        if (item == null) {
            throw new IllegalArgumentException("The argument cannot be null");
        }

        Node newTail = new Node();
        newTail.val = item;
        ++queueSize;

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

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("The queue is empty");
        }

        final int RANDOM_ITEM  = StdRandom.uniform(1, queueSize+1);

        Node item = head;
        Item result = item.val;

        if (RANDOM_ITEM == 1) {
            head = item.next;
        } else if (RANDOM_ITEM == queueSize) {
            result = tail.val;
            tail = tail.prev;
        } else {

            for (int i = 1; i < RANDOM_ITEM; i++) {
                item = item.next;
                result = item.val;
            }
            item.prev.next = item.next;
            item.next.prev = item.prev;
        }

        --queueSize;

        return result;
    }

    // return a random item (but do not remove it)
    public Item sample() {

        if (isEmpty()) {
            throw new NoSuchElementException("The queue is empty");
        }

        final int RANDOM_ITEM  = StdRandom.uniform(1, queueSize + 1);

        Iterator<Item> item = iterator();
        Item result = null;

        for (int i = 1; i <= RANDOM_ITEM && item.hasNext(); i++) {
            result = item.next();

        }

        return result;
    }

    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    // return an independent iterator over items in random order
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

    }

}
