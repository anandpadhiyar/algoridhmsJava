/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

public class ThreeSumArr {
    private Integer[] Arr;

    public ThreeSumArr(Integer[] arr) {
        Arr = arr;
    }

    public static void main(String[] args) {
        Integer[] numbers = new Integer[] { -1, 1, -3, 3, 0, -2, 2 };
        ThreeSumArr ts = new ThreeSumArr(numbers);
        int totalSum = ts.totalSums();
        System.out.println(totalSum);
    }

    public int totalSums() {
        // MergeSort ms = new MergeSort(Arr);
        // Arr = ms.sortArr();
        BinarySearch bs = new BinarySearch(Arr);
        int allTotalMatches = 0;

        for (int i = 0; i < Arr.length; i++) {
            for (int j = i; j < Arr.length; j++) {
                int numberToFind = -(Arr[i] + Arr[j]);
                int flagMatch = bs.findPosition(numberToFind);
                if (flagMatch != -1) {
                    allTotalMatches += 1;
                }
            }
        }
        return allTotalMatches / 2;
    }
}
