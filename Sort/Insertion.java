/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

package Sort;

public class Insertion {
    public static void main(String[] args) {
        String[] input = new String[] {
                "Ronak", "Riya", "Anand", "Bharat", "Magan"
        };
        sort(input);
        show(input);
    }

    public static void sort(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            for (int j = i; j > 0; j--) {
                if (less(a[j], a[j - 1]))
                    exch(a, j, j - 1);
                else break;
            }
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
