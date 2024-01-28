/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

package QuickFindAndUnion;

public class QuickFindTest {
    public static void main(String[] args) {
        QuickFind qf = new QuickFind(5);
        qf.union(1, 2);
        qf.union(4, 0);
        System.out.println(qf.find(2, 3));
        System.out.println(qf.find(3, 4));
        System.out.println(qf.find(4, 0));
        qf.union(2, 4);
        System.out.println(qf.find(1, 0));
    }
}
