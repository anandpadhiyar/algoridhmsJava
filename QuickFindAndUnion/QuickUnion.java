/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

package QuickFindAndUnion;

public class QuickUnion {
    private int[] id;

    public QuickUnion(int N) {
        id = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
        }
    }

    private int findRoot(int i) {
        while (id[i] != i) i = id[i];
        return i;
    }

    public boolean find(int p, int q) {
        int pRoot = findRoot(p);
        int qRoot = findRoot(q);
        if (pRoot == qRoot) return true;
        return false;
    }

    public void union(int p, int q) {
        int pRoot = findRoot(p);
        int qRoot = findRoot(q);
        id[pRoot] = qRoot;
    }
}
