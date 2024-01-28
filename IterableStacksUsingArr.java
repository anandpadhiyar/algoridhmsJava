/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

public class IterableStacksUsingArr<T> implements Iterable<T> {
    public static void main(String[] args) {
        IterableStacksUsingArr<Integer> it = new IterableStacksUsingArr<Integer>();
        it.push(2);
        it.push(3);
        it.push(5);
        it.push(1);
        for (int s : it) {
            System.out.println(s);
        }
    }

    public Iterator<T> iterator() {
        return new MyIterator();
    }

    private class MyIterator implements Iterator<T> {
        private int i = N;

        public boolean hasNext() {
            if (i > 0) return true;
            return false;
        }

        public T next() {
            return Arr[--i];
        }
    }

    private T[] Arr;
    private int N = 0;

    public IterableStacksUsingArr() {
        Arr = (T[]) new Object[1];
    }

    public void push(T item) {
        if (Arr.length == N) resize(N * 2);
        Arr[N++] = item;
    }

    private void resize(int capacity) {
        T[] newArr = (T[]) new Object[capacity];
        for (int i = 0; i < N; i++) {
            newArr[i] = Arr[i];
        }
        Arr = newArr;
    }

    public void forEach(Consumer<? super T> action) {
        Iterable.super.forEach(action);
    }

    public Spliterator<T> spliterator() {
        return Iterable.super.spliterator();
    }
}
