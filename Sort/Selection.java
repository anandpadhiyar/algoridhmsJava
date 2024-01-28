/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

package Sort;

import edu.princeton.cs.algs4.StdIn;

public class Selection {
    public static void main(String[] args) {
        System.out.println("Please enter strings to sort:\n");
        String somethin = StdIn.readAll();
        String[] a = new String[] {
                "Ronak",
                "Anand",
                "Riya",
                "Bharat"
        };
        Selection selection = new Selection();
        selection.sort(a);
        selection.show(a);
    }

    public void sort(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            int min = i;
            for (int j = i + 1; j < a.length; j++) {
                if (less(a[j], a[min])) {
                    min = j;
                }
                exch(a, i, min);
            }
        }
    }

    public void show(Comparable[] a) {
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
