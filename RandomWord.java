import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;

public class RandomWord {
    public static void main(String[] args) {
        String champion = "";
        int index = 0;
        while (!StdIn.isEmpty()) {
            String current = StdIn.readString();
            boolean isChampion = StdRandom.bernoulli(1 / (index + 1));
            if (isChampion) {
                champion = current;
            }
            index++;
        }
        System.out.println(champion);
    }
}
