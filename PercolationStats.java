/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private int gridSize;
    private int noOfTrials;
    private double[] trialsResult;

    public PercolationStats(int n, int trials) {
        gridSize = n;
        noOfTrials = trials;
        trialsResult = new double[trials];
        for (int i = 0; i < trials; i++) {
            performPercolation(i);
        }
    }

    private void performPercolation(int trialNo) {
        Percolation perc = new Percolation(gridSize);
        while (!perc.percolates()) {
            int rowInt = StdRandom.uniformInt(gridSize) + 1;
            int colInt = StdRandom.uniformInt(gridSize) + 1;
            if (!perc.isOpen(rowInt, colInt)) {
                perc.open(rowInt, colInt);
            }
        }
        int noOfOpenSites = perc.numberOfOpenSites();
        double thresold = (double) noOfOpenSites / (gridSize * gridSize);
        trialsResult[trialNo] = thresold;
    }

    public double mean() {
        double mean = StdStats.mean(trialsResult);
        return mean;
    }

    public double stddev() {
        double stdVar = StdStats.stddev(trialsResult);
        return stdVar;
    }

    public double confidenceLo() {
        double mean = mean();
        double confidenceFactor = confidenceFactor();
        double loConfidence = mean - confidenceFactor;
        return loConfidence;
    }

    public double confidenceHi() {
        double mean = mean();
        double confidenceFactor = confidenceFactor();
        double loConfidence = mean + confidenceFactor;
        return loConfidence;
    }

    private double confidenceFactor() {
        double stdDev = stddev();
        double result = (1.96 * stdDev) / (Math.sqrt(noOfTrials));
        return result;
    }

    public static void main(String[] args) {
        int[] gridSizeAndTrials = parseAndGetCommandParams(args);
        PercolationStats ps = new PercolationStats(gridSizeAndTrials[0], gridSizeAndTrials[1]);
        double mean = ps.mean();
        double stddev = ps.stddev();
        double loConf = ps.confidenceLo();
        double hiConf = ps.confidenceHi();
        System.out.printf("mean                    = %f\n", mean);
        System.out.printf("stddev                  = %f\n", stddev);
        System.out.printf("95%% confidence interval = [%f, %f]\n\n", loConf, hiConf);
    }

    private static int[] parseAndGetCommandParams(String[] args) {
        String gridSizeStr = args[0];
        String noOfTrialsStr = args[1];
        int gridSize = Integer.parseInt(gridSizeStr);
        int t = Integer.parseInt(noOfTrialsStr);
        if (gridSize <= 0 || t <= 0) {
            throw new IllegalArgumentException();
        }
        return new int[] { gridSize, t };
    }
}
