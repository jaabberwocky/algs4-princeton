import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private int[] id;
    private int virtualTop = 0; // set this to 0 because top-left corner of the grid is (1,1)
    private int virtualBottom = -1;

    // creates n-by-n grid with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) throw new IllegalArgumentException("n must be positive"); // illegal argument

        id = new int[n];
        for (int i = 0; i < n; i++) {
            id[i] = i; // set id of each obj to itself
        }
    }

    // opens the site (row, col ) if it is not open already
    public void open(int row, int col) {

    }
}
