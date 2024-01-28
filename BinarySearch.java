/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

public class BinarySearch {
    private Integer[] SortedArr;

    public BinarySearch(Integer[] sortedArr) {
        SortedArr = sortedArr;
    }

    public static void main(String[] args) {
    }

    public int findPosition(Integer number) {
        int leftBoundary = 0;
        int rightBoundary = SortedArr.length - 1;
        while (leftBoundary <= rightBoundary) {
            int middle = (leftBoundary + rightBoundary) / 2;
            if (SortedArr[middle] > number) {
                rightBoundary = middle - 1;
                continue;
            }
            if (SortedArr[middle] < number) {
                leftBoundary = middle + 1;
                continue;
            }
            return middle;
        }
        return -1;
    }
}
