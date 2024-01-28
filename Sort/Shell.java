/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

package Sort;
// 3 1 2 3 2 3 1 2 3 1 1 2 2 3 2

public class Shell {
    public static void main(String[] args) {
        Double[] numbers = new Double[] {
                1.2, 1.1, 4.5, 3.2, 1.6, 1.9, 0.3, 0.6
        };
        sort(numbers);
        show(numbers);
    }

    public static void sort(Comparable[] a) {
        // Using 3n + 1 algorithm
        int h = 1;
        int n = a.length;
        while (h < n / 3) h = (3 * h + 1);
        while (h >= 1) {
            System.out.println("Inside loop");
            for (int i = h; i < n; i++) {
                for (int j = i; j >= h; j -= h) {
                    if (less(a[j], a[j - h])) exch(a, j, j - h);
                    else break;
                }
            }
            h = h / 3;
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
