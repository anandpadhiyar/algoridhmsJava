/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Node first = null;
    private Node last = null;
    private int length = 0;

    private class Node {
        Item item;
        Node next;
        Node prev;
    }

    // construct an empty deque
    public Deque() {
    }

    // is the deque empty?
    public boolean isEmpty() {
        if (first == null) return true;
        return false;
    }

    // return the number of items on the deque
    public int size() {
        return length;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException();
        Node oldFirst = first;
        Node newNode = new Node();
        newNode.item = item;
        newNode.next = oldFirst;
        if (oldFirst == null) {
            last = newNode;
        }
        else {
            oldFirst.prev = newNode;
        }
        first = newNode;
        length += 1;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException();
        Node newNode = new Node();
        newNode.item = item;
        if (last == null) {
            last = newNode;
            first = newNode;
        }
        else {
            Node oldLast = last;
            oldLast.next = newNode;
            newNode.prev = oldLast;
            last = newNode;
        }
        length += 1;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (length == 0) throw new NoSuchElementException();
        Node oldFirst = first;
        first = oldFirst.next;
        if (first == null) {
            last = null;
        }
        else {
            first.prev = null;
        }
        length -= 1;
        return oldFirst.item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (length == 0) throw new NoSuchElementException();
        Node oldLast = last;
        last = oldLast.prev;
        if (last == null) {
            first = null;
        }
        else {
            last.next = null;
        }
        length -= 1;
        return oldLast.item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        Node current = first;

        public boolean hasNext() {
            if (current == null) return false;
            return true;
        }

        public Item next() {
            if (current == null) throw new NoSuchElementException();
            Item currentItem = current.item;
            current = current.next;
            return currentItem;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<Integer> dq = new Deque<Integer>();
        System.out.printf("is Empty %b\n", dq.isEmpty());
        dq.addFirst(1);
        System.out.printf("is Empty %b\n", dq.isEmpty());
        dq.addFirst(2);
        dq.addLast(3);
        dq.addLast(4);
        dq.addFirst(5);
        dq.addLast(6);
        System.out.println("\n\n");
        System.out.println("Iteration started");
        for (int i : dq) {
            System.out.println(i);
        }
        System.out.println("Iteration ended");
        System.out.println("\n\n");
        System.out.printf("size is %d\n", dq.size());
        System.out.println(dq.removeFirst());
        System.out.println(dq.removeFirst());
        System.out.println(dq.removeLast());
        System.out.printf("size is %d\n", dq.size());
        System.out.println(dq.removeLast());
        System.out.printf("is Empty %b\n", dq.isEmpty());
        System.out.println(dq.removeFirst());
        System.out.printf("size is %d\n", dq.size());
        System.out.println(dq.removeLast());
        System.out.printf("size is %d\n", dq.size());
        System.out.printf("is Empty %b\n", dq.isEmpty());
    }
}
