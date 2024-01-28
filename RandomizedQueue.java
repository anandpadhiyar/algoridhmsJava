/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

// https://chat.openai.com/share/07403743-b03b-4597-997b-8ea31b04cc58
public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] items;
    private int numberOfItems = 0;
    private int overallLength = 0;
    private Integer[] nonNullIndices;

    // construct an empty randomized queue
    public RandomizedQueue() {
        items = (Item[]) new Object[1];
        nonNullIndices = new Integer[1];
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        if (numberOfItems == 0) return true;
        return false;
    }

    // return the number of items on the randomized queue
    public int size() {
        return numberOfItems;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException();
        if (items.length == overallLength) resize(2 * items.length);
        items[overallLength++] = item;
        nonNullIndices[numberOfItems] = overallLength - 1;
        numberOfItems++;
        /*
         * [1, null, 3, 4]
         * [0, 3, 2, null]
         * */
    }

    private void resize(int capacity) {
        Item[] newArr = (Item[]) new Object[capacity];
        int newArrIndex = 0;
        for (int i = 0; i < items.length; i++) {
            if (items[i] != null) {
                newArr[newArrIndex++] = items[i];
            }
        }
        overallLength = numberOfItems;
        items = newArr;
        Integer[] newNonNullArr = new Integer[capacity];
        for (int i = 0; i < numberOfItems; i++) {
            newNonNullArr[i] = i;
        }
        nonNullIndices = newNonNullArr;
    }

    // remove and return a random item
    public Item dequeue() {
        if (numberOfItems == 0) throw new NoSuchElementException();
        if (items.length == numberOfItems * 4) resize(numberOfItems * 2);
        int randomIndex = StdRandom.uniformInt(numberOfItems);
        int actualIndexOfArr = nonNullIndices[randomIndex];
        Item randomItem = items[actualIndexOfArr];
        items[actualIndexOfArr] = null;
        nonNullIndices[randomIndex] = nonNullIndices[--numberOfItems];
        nonNullIndices[numberOfItems] = null;
        return randomItem;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (numberOfItems == 0) throw new NoSuchElementException();
        int randomIndex = StdRandom.uniformInt(numberOfItems);
        int actualIndexOfArr = nonNullIndices[randomIndex];
        Item randomItem = items[actualIndexOfArr];
        return randomItem;
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        Item[] newArr = (Item[]) new Object[numberOfItems];
        int newIndex = 0;
        for (int i = 0; i < items.length; i++) {
            if (items[i] != null) {
                newArr[newIndex] = items[i];
                newIndex += 1;
            }
        }
        StdRandom.shuffle(newArr);
        return new MyIterator(newArr);
    }

    private class MyIterator implements Iterator<Item> {
        private Item[] _itemArr;

        public MyIterator(Item[] itemArr) {
            _itemArr = itemArr;
        }

        int currentIndex = 0;

        public boolean hasNext() {
            if (currentIndex < _itemArr.length) return true;
            return false;
        }

        public Item next() {
            if (currentIndex >= _itemArr.length) throw new NoSuchElementException();
            return _itemArr[currentIndex++];
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();
        rq.enqueue(1);
        rq.enqueue(2);
        rq.enqueue(3);
        rq.enqueue(4);
        // System.out.println(rq.sample());
        // System.out.println(rq.sample());
        System.out.println(rq.dequeue());
        rq.enqueue(1);
        rq.enqueue(2);
        rq.enqueue(3);
        rq.enqueue(4);
        System.out.println(rq.dequeue());
        rq.enqueue(1);
        rq.enqueue(2);
        rq.enqueue(3);
        rq.enqueue(4);
        System.out.println(rq.dequeue());
        rq.enqueue(1);
        rq.enqueue(2);
        rq.enqueue(3);
        rq.enqueue(4);
        System.out.println(rq.dequeue());
        rq.enqueue(1);
        rq.enqueue(2);
        rq.enqueue(3);
        rq.enqueue(4);
        System.out.println(rq.dequeue());
        rq.enqueue(1);
        rq.enqueue(2);
        rq.enqueue(3);
        rq.enqueue(4);
        System.out.println(rq.dequeue());
        rq.enqueue(1);
        rq.enqueue(2);
        rq.enqueue(3);
        rq.enqueue(4);
        System.out.println(rq.dequeue());
        rq.enqueue(1);
        rq.enqueue(2);
        rq.enqueue(3);
        rq.enqueue(4);
        System.out.println(rq.dequeue());
        rq.enqueue(1);
        rq.enqueue(2);
        rq.enqueue(3);
        rq.enqueue(4);
        System.out.println(rq.dequeue());

        System.out.println("________________________");
        System.out.println("Iterator");

        for (Integer item : rq) {
            System.out.println(item);
        }

        // int anandCount = 0;
        // int ronakCount = 0;
        // int riyaCount = 0;
        // int mummyCount = 0;
        // int pappaCount = 0;
        // for (int i = 0; i < 10000; i++) {
        //     String randStr = rq.sample();
        //     if (Objects.equals(randStr, "Anand")) anandCount += 1;
        //     if (Objects.equals(randStr, "Ronak")) ronakCount += 1;
        //     if (Objects.equals(randStr, "Riya")) riyaCount += 1;
        //     if (Objects.equals(randStr, "Mummy")) mummyCount += 1;
        //     if (Objects.equals(randStr, "Pappa")) pappaCount += 1;
        // }
        // System.out.println(anandCount);
        // System.out.println(ronakCount);
        // System.out.println(riyaCount);
        // System.out.println(mummyCount);
        // System.out.println(pappaCount);
    }

}