/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import java.util.Iterator;

public class IterableStacksUsingLinqList<T> implements Iterable<T> {

    private Node first = null;

    public static void main(String[] args) {
        IterableStacksUsingLinqList<Integer> it = new IterableStacksUsingLinqList<Integer>();
        it.push(2);
        it.push(3);
        it.push(5);
        it.push(1);
        for (int s : it) {
            System.out.println(s);
        }
    }

    private class Node {
        T item;
        Node next;
    }

    public boolean isEmpty() {
        if (first == null) return true;
        return false;
    }

    public void push(T newItem) {
        Node oldFirst = first;
        first = new Node();
        first.item = newItem;
        first.next = oldFirst;
    }

    public T pop() {
        Node oldFirst = first;
        if (oldFirst == null) return null;
        first = first.next;
        return oldFirst.item;
    }

    public Iterator<T> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<T> {
        private Node current = first;

        public boolean hasNext() {
            if (current == null) return false;
            return true;
        }

        public T next() {
            T currentItem = current.item;
            current = current.next;
            return currentItem;
        }
    }
}
