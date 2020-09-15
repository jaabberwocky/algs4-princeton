/**
 * The PercolationStats class implements a statistical testing suite for the Percolation class.
 * <p>
 * Main method provided: PercolationStats n trials
 *
 * @author Tobias Leong
 */


import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private double[] thresholds;
    private int trials;
    private int n;
    private double mean;
    private double sd;
    private static final double c = 1.96;


    public PercolationStats(int inputN, int inputTrials) {
        if (inputN <= 0 || inputTrials <= 0) {
            throw new IllegalArgumentException();
        }
        n = inputN;
        trials = inputTrials;
        thresholds = new double[trials];


        for (int i = 0; i < trials; i++) {
            Percolation p = new Percolation(n);
            while (!p.percolates()) {
                // choose site at random
                int row = StdRandom.uniform(1, n + 1);
                int col = StdRandom.uniform(1, n + 1);

                p.open(row, col);
            }

            double threshold = ((double) p.numberOfOpenSites()) / (n * n);
            thresholds[i] = threshold;
        }
    }

    public double mean() {
        mean = StdStats.mean(thresholds);
        return mean;
    }

    public double stddev() {
        sd = StdStats.stddev(thresholds);
        return sd;
    }

    public double confidenceLo() {

        return mean - (1.96 * sd) / Math.sqrt((double) trials);
    }

    public double confidenceHi() {
        return mean + (c * sd) / Math.sqrt((double) trials);

    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);

        PercolationStats ps = new PercolationStats(n, trials);

        System.out.printf("mean%21s= %f\n", " ", ps.mean());
        System.out.printf("stddev%19s= %f\n", " ", ps.stddev());
        System.out.printf("95%% confidence interval%2s=[%f,%f]\n", " ", ps.confidenceLo(), ps.confidenceHi());

    }
}

