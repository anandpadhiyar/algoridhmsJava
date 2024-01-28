/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */


public class Percolation {
    private int gridSize;
    private int openedSites = 0;
    private int[] id;
    private int[] sz;
    private boolean[][] isOpen;

    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }
        gridSize = n;
        id = new int[(n + 2) * n];
        sz = new int[(n + 2) * n];
        isOpen = new boolean[n + 2][n];
        for (int i = 0; i <= n + 1; i++) {
            for (int j = 0; j < n; j++) {
                id[calculatePos(i, j)] = calculatePos(i, j);
                sz[calculatePos(i, j)] = 1;
                isOpen[i][j] = false;
            }
        }
        unionTopVirtualWithTopRow();
        // unionBottomVirtualWithBottomRow();
    }

    private int calculatePos(int i, int j) {
        if (i == 0) return 0;
        if (i == (gridSize + 1)) return (gridSize * (gridSize + 1));
        return (gridSize * (i)) + j;
    }

    private void unionTopVirtualWithTopRow() {
        int startingPoint = calculatePos(1, 0);
        int endingPoint = calculatePos(1, gridSize - 1);
        for (int i = startingPoint; i <= endingPoint; i++) {
            id[i] = 0;
            sz[i] = gridSize + 1;
        }
        sz[0] = gridSize + 1;
    }

    private void unionBottomVirtualWithBottomRow() {
        int startingPoint = calculatePos(gridSize, 0);
        int bottomVirtualPointPos = calculatePos(gridSize + 1, 0);
        int endingPoint = calculatePos(gridSize, gridSize - 1);
        for (int i = startingPoint; i <= endingPoint; i++) {
            id[i] = bottomVirtualPointPos;
            sz[i] = gridSize + 1;
        }
        sz[bottomVirtualPointPos] = gridSize + 1;
    }

    public void open(int row, int col) {
        boolean isValid = validateRowAndColumn(row, col);
        if (!isValid) {
            throw new IllegalArgumentException();
        }
        int currentI = row;
        int currentJ = col - 1;
        if (!isOpen[currentI][currentJ]) {
            isOpen[currentI][currentJ] = true;
            openedSites += 1;
        }
        // openLeft
        joinSite(currentI, currentJ - 1, currentI, currentJ);
        // openRight
        joinSite(currentI, currentJ + 1, currentI, currentJ);
        // openBottom
        joinSite(currentI + 1, currentJ, currentI, currentJ);
        // openTop
        joinSite(currentI - 1, currentJ, currentI, currentJ);
    }

    private void joinSite(int i1, int j1, int i2, int j2) {
        boolean isValid = validateIAndJ(i1, j1);
        if (!isValid) return;
        if (isOpen[i1][j1]) {
            union(i1, j1, i2, j2);
        }
    }

    public boolean isOpen(int row, int col) {
        boolean isValid = validateRowAndColumn(row, col);
        if (!isValid) {
            throw new IllegalArgumentException();
        }
        return isOpen[row][col - 1];
    }

    public boolean isFull(int row, int col) {
        boolean isValid = validateRowAndColumn(row, col);
        if (!isValid) {
            throw new IllegalArgumentException();
        }
        boolean io = isOpen(row, col);
        if (!io) return false;
        int currentRoot = findRootId(calculatePos(row, col - 1));
        int virtualTopRoot = findRootId(calculatePos(0, 0));
        if (currentRoot == virtualTopRoot) return true;
        return false;
    }

    public int numberOfOpenSites() {
        return openedSites;
    }

    public boolean percolates() {
        for (int i = 0; i < gridSize; i++) {
            if (isFull(gridSize, i + 1)) return true;
        }
        return false;
    }

    private void union(int i1, int j1, int i2, int j2) {
        int pPos = calculatePos(i1, j1);
        int qPos = calculatePos(i2, j2);
        int pRoot = findRootId(pPos);
        int qRoot = findRootId(qPos);
        if (pRoot == qRoot) return;
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

    private int findRootId(int pos) {
        while (id[pos] != pos) {
            id[pos] = id[id[pos]];
            pos = id[pos];
        }
        return pos;
    }

    private boolean validateRowAndColumn(int row, int col) {
        if (row <= 0 || row > gridSize || col <= 0 || col > gridSize) {
            return false;
        }
        return true;
    }

    private boolean validateIAndJ(int i, int j) {
        if (i <= 0 || i > gridSize || j < 0 || j >= gridSize) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Percolation perc = new Percolation(2);
        perc.open(1, 1);
        perc.open(2, 2);
        perc.open(2, 1);
        System.out.printf("Is Full - %B\n", perc.isFull(1, 1));
        System.out.printf("Is Full - %B\n", perc.isFull(2, 2));
        System.out.printf("Is Percolate - %B\n", perc.percolates());
    }
}
