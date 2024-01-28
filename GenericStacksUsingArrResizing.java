/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

public class GenericStacksUsingArrResizing<T> {
    public static void main(String[] args) {
        GenericStacksUsingArrResizing<String> stacks = new GenericStacksUsingArrResizing<String>();
        stacks.push("Anand");
        stacks.push("Ronak");
        System.out.println(stacks.pop());
        stacks.push("Riya");
        stacks.push("Mummy");
        System.out.println(stacks.pop());
        System.out.println(stacks.pop());
        stacks.push("Pappa");
        System.out.println(stacks.pop());
    }

    private T[] Arr;
    private int N = 0;

    public GenericStacksUsingArrResizing() {
        Arr = (T[]) new Object[1];
    }

    public boolean isEmpty() {
        if (Arr.length == 0) return true;
        return false;
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

    public T pop() {
        T lastItem = Arr[--N];
        Arr[N] = null;
        if (N > 0 && (N * 4) == Arr.length) resize(Arr.length / 2);
        return lastItem;
    }
}
