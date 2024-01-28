package QuickFindAndUnion;

public class QuickFind {
    private int[] id;

    public QuickFind(int N) {
        id = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
        }
    }

    public boolean find(int p, int q) {
        return id[p] == id[q];
    }

    public void union(int p, int q) {
        int qId = id[q];
        int pId = id[p];
        for (int i = 0; i < id.length; i++) {
            if (id[i] == pId) {
                id[i] = qId;
            }
        }
    }
}

