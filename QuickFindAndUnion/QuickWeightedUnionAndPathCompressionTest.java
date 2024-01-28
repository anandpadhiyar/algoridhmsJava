/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

package QuickFindAndUnion;

public class QuickWeightedUnionAndPathCompressionTest {
    public static void main(String[] args) {
        QuickUnionWeightedAndPathCompression qu = new QuickUnionWeightedAndPathCompression(10);
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
