/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

package QuickFindAndUnion;

public class QuickWeightedUnion {
    private int[] id;
    private int[] sz;

    public QuickWeightedUnion(int N) {
        id = new int[N];
        sz = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
            sz[i] = 1;
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
        int pSize = sz[pRoot];
        int qSize = sz[qRoot];
        if (pSize < qSize) {
            id[pRoot] = qRoot;
            sz[qRoot] += sz[pRoot];
        }
        else {
            id[qRoot] = pRoot;
            sz[pRoot] += sz[qRoot];
        }
    }
}