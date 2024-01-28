import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class PercolateSlnFromGit {

    private boolean[][] opened;
    private int top = 0;
    private int bottom;
    private int size;
    private WeightedQuickUnionUF qf;

    /**
     * Creates N-by-N grid, with all sites blocked.
     */
    public PercolateSlnFromGit(int N) {
        size = N;
        bottom = size * size + 1;
        qf = new WeightedQuickUnionUF(size * size + 2);
        opened = new boolean[size][size];
    }

    public int numberOfOpenSites() {
        int total = 0;
        for (int i = 0; i < opened.length; i++) {
            for (int j = 0; j < opened[i].length; j++) {
                if (opened[i][j]) {
                    total += 1;
                }
            }
        }
        return total;
    }

    /**
     * Opens site (row i, column j) if it is not already.
     */
    public void open(int i, int j) {
        opened[i - 1][j - 1] = true;
        if (i == 1) {
            qf.union(getQFIndex(i, j), top);
        }
        if (i == size) {
            qf.union(getQFIndex(i, j), bottom);
        }

        if (j > 1 && isOpen(i, j - 1)) {
            qf.union(getQFIndex(i, j), getQFIndex(i, j - 1));
        }
        if (j < size && isOpen(i, j + 1)) {
            qf.union(getQFIndex(i, j), getQFIndex(i, j + 1));
        }
        if (i > 1 && isOpen(i - 1, j)) {
            qf.union(getQFIndex(i, j), getQFIndex(i - 1, j));
        }
        if (i < size && isOpen(i + 1, j)) {
            qf.union(getQFIndex(i, j), getQFIndex(i + 1, j));
        }
    }

    /**
     * Is site (row i, column j) open?
     */
    public boolean isOpen(int i, int j) {
        return opened[i - 1][j - 1];
    }

    /**
     * Is site (row i, column j) full?
     */
    public boolean isFull(int i, int j) {
        if (0 < i && i <= size && 0 < j && j <= size) {
            return qf.connected(top, getQFIndex(i, j));
        }
        else {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * Does the system percolate?
     */
    public boolean percolates() {
        return qf.connected(top, bottom);
    }

    private int getQFIndex(int i, int j) {
        return size * (i - 1) + j;
    }


    public static void main(String[] args) {
        PercolateSlnFromGit perc = new PercolateSlnFromGit(3);
        // perc.open(1, 4);
        // perc.open(2, 2);
        // perc.open(3, 4);
        // perc.open(4, 1);
        // boolean isPerc1 = perc.percolates();
        // System.out.println(isPerc1);
        // perc.open(3, 2);
        // boolean isPerc2 = perc.percolates();
        // System.out.println(isPerc2);
        // perc.open(2, 3);
        // perc.open(1, 2);
        perc.open(1, 3);
        perc.open(2, 3);
        perc.open(3, 3);
        perc.open(3, 1);
        System.out.printf("Is Full - %B\n", perc.isFull(3, 1));
        // boolean isPerc3 = perc.percolates();
        // System.out.println(isPerc3);
    }
}
