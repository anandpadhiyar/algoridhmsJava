/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

package QuickFindAndUnion;

public class SuccessorWithDelete {
    private int[] parents;
    private int[] successors;

    public SuccessorWithDelete(int N) {
        successors = new int[N - 1];
        parents = new int[N];
        for (int i = 0; i < N; i++) {
            if (i != N - 1) {
                successors[i] = i + 1;
            }
            parents[i] = i;
        }
    }

    public Integer RemoveAndGiveSuccessor(int i) {
        if (parents[i] == -1) return null;
        Integer nextEle = i < successors.length ? successors[i] : null;
        parents[i] = -1;
        return nextEle;
    }
}

