import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;

/*
1, 2,7, 8, 9
3, 4
5, 6, 0
 * */
public class RandomWord {
    public static void main(String[] args) {
        String champion = "";
        int index = 0;
        while (!StdIn.isEmpty()) {
            String current = StdIn.readString();
            double p = ((double) 1 / (index + 1));
            boolean isChampion = StdRandom.bernoulli(p);
            if (isChampion) {
                champion = current;
            }
            index++;
        }
        System.out.println(champion);
    }
}
