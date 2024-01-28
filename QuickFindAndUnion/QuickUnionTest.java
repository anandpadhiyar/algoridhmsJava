/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

package QuickFindAndUnion;

public class QuickUnionTest {
    public static void main(String[] args) {
        QuickUnion qu = new QuickUnion(10);
        qu.union(1, 3);
        qu.union(2, 5);
        qu.union(7, 9);
        qu.union(2, 0);
        System.out.println(qu.find(5, 0));
        qu.union(1, 2);
        System.out.println(qu.find(3, 0));
        System.out.println(qu.find(7, 0));
    }
}
