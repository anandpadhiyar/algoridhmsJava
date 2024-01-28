/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

package Sort;

import edu.princeton.cs.algs4.StdIn;

public class hello {
    public static void main(String[] args) {
        System.out.println("What is your name? \n");
        String name = StdIn.readString();
        AnotherHello anotherHello = new AnotherHello();
        String result = anotherHello.sayHello(name);
        System.out.println(result);
    }
}
