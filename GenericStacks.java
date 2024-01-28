/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

public class GenericStacks<T> {
    private Node first = null;

    public static void main(String[] args) {
        GenericStacks<Integer> it = new GenericStacks<Integer>();
        it.push(2);
        it.push(3);
        System.out.println(it.pop());
        System.out.println(it.pop());
        it.push(5);
        it.push(1);
        System.out.println(it.pop());
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
}
