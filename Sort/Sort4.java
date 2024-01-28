/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

package Sort;
// 10 8 2 7
// 2 7 10 8

// a b c d e f

// ab
// cd
// ef
// ce
// ac
// df
// bd
// bc
// de
// bd
// ce
// cd

// a b c d e
// 3 12 14 10 15
// ab
// de
// ac
// bc
// ad - 10 12 14 8 9
// cd - 2 12 8 14 9

// 3 12 10 14 15

// a b c d e f g
// ab
// de
// fg
// ac
// bc
// df
// eg
// ef
// ad

public class Sort4 {
    public static void main(String[] args) {

    }

    public static void sort(int no1, int no2, int no3, int no4) {
        int temp;
        if (no1 > no2) {
            temp = no1;
            no1 = no2;
            no2 = temp;
        }
        if (no3 > no4) {
            temp = no3;
            no3 = no4;
            no4 = temp;
        }
        if (no1 > no3) {
            temp = no1;
            no1 = no3;
            no3 = temp;
        }
        if (no2 > no4) {
            temp = no2;
            no2 = no4;
            no4 = temp;
        }
        if (no2 > no3) {
            temp = no2;
            no2 = no3;
            no3 = temp;
        }
        System.out.println(no1 + " " + no2 + " " + no3 + " " + no4);
    }
}
