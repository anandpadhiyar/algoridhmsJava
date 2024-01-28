/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

package Sort;

public class BottomUpMergeSort {
    public static void main(String[] args) {
        Integer[] numbers = new Integer[] { 4, 1, 2, 4, 5, 1, 3 };
        sort(numbers);
        show(numbers);
    }

    public static void sort(Comparable[] a) {
        int n = a.length;
        Comparable[] aux = new Comparable[a.length];
        for (int halfSz = 1; halfSz < n; halfSz = (2 * halfSz)) {
            for (int lo = 0; lo < n; lo += (2 * halfSz)) {
                int hi = Math.min(lo + (2 * halfSz) - 1, n - 1);
                int mid = lo + (hi - lo) / 2;
                merge(a, aux, lo, mid, hi);
            }
        }
    }

    private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }

        int i = lo;
        int j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) a[k] = aux[j++];
            else if (j > hi) a[k] = aux[i++];
            else if (less(aux[j], aux[i])) a[k] = aux[j++];
            else a[k] = aux[i++];
        }
    }

    public static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
        }
    }

    private static boolean less(Comparable v, Comparable w) {
        return (v.compareTo(w) < 0);
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable swp = a[i];
        a[i] = a[j];
        a[j] = swp;
    }
}
