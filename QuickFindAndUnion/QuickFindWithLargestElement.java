/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

package QuickFindAndUnion;

public class QuickFindWithLargestElement {
    private int[] id;
    private int[] sz;
    private int[] big;

    public QuickFindWithLargestElement(int N) {
        id = new int[N];
        sz = new int[N];
        big = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
            sz[i] = 1;
            big[i] = i;
        }
    }

    private int findRoot(int i) {
        while (id[i] != i) {
            id[i] = id[id[i]];
            i = id[i];
        }
        return i;
    }

    public int find(int p) {
        int pRoot = findRoot(p);
        int max = pRoot;
        for (int i = 0; i < id.length; i++) {
            if (id[i] == pRoot && id[i] > max) {
                max = id[i];
            }
        }
        return max;
    }

    public boolean connected(int p, int q) {
        int pRoot = findRoot(p);
        int qRoot = findRoot(q);
        if (pRoot == qRoot) return true;
        return false;
    }

    public void union(int p, int q) {
        int pRoot = findRoot(p);
        int qRoot = findRoot(q);
        int pRootSize = sz[pRoot];
        int qRootSize = sz[qRoot];
        int pBig = big[pRoot];
        int qBig = big[qRoot];
        if (pRootSize > qRootSize) {
            id[qRoot] = pRoot;
            sz[pRoot] += sz[qRoot];
            big[pRoot] = findBiggerNo(pBig, qBig);
        }
        else {
            id[pRoot] = qRoot;
            sz[qRoot] += sz[pRoot];
            big[qRoot] = findBiggerNo(pBig, qBig);
        }
    }

    private int findBiggerNo(int p, int q) {
        if (p > q) return p;
        return q;
    }
}
