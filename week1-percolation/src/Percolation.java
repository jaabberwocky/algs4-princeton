import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private boolean[] id;
    private WeightedQuickUnionUF connectionGrid;
    private int gridSize;

    // because top-left corner of the grid is (1,1)
    private final int virtualTop = 0;
    private final int virtualBottom;

    // creates n-by-n grid with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) throw new IllegalArgumentException("n must be positive"); // illegal argument

        gridSize = n;
        virtualBottom = gridSize + 1;
        id = new boolean[n + 1]; // use default value of 0 to set all sites to blocked
        connectionGrid = new WeightedQuickUnionUF(gridSize + 1); // account for virtualBottom too
    }

    // opens the site (row, col ) if it is not open already
    public void open(int row, int col) {
        int singleArrayRep = convertToSingleArray(row, col);
        if (!id[singleArrayRep]) {
            // site is currently closed
            id[singleArrayRep] = true;
            // check all sites around it and union them

            // above
            if (row == 1) {
                connectionGrid.union(singleArrayRep, virtualTop); // union with virtualTop
            } else {
                connectionGrid.union(singleArrayRep, convertToSingleArray(row - 1, col));
            }

            // left
            if (col != 1) {
                connectionGrid.union(singleArrayRep, convertToSingleArray(row, col - 1));
            }

            // right
            if (col != gridSize) {
                connectionGrid.union(singleArrayRep, convertToSingleArray(row, col + 1));
            }

            // bottom
            if (row == gridSize) {
                connectionGrid.union(singleArrayRep, virtualBottom);
            } else {
                connectionGrid.union(singleArrayRep, convertToSingleArray(row + 1, col));
            }
        }
    }

    public int numberOfOpenSites() {
        int trueCount = 0;
        for (int i = 1; i < id.length; i++) {
            if (id[i]) trueCount++;
        }
        return trueCount;
    }

    public boolean isFull(int row, int col) {
        int singleArrayRep = convertToSingleArray(row, col);
        return connectionGrid.find(singleArrayRep) == virtualTop;
    }

    public boolean isOpen(int row, int col) {
        checkBounds(row, col);
        return id[convertToSingleArray(row, col)];
    }

    public boolean percolates() {
        return connectionGrid.find(virtualBottom) == connectionGrid.find(virtualTop);
    }

    private void checkBounds(int row, int col) {
        if (row < 1 || row > gridSize || col < 1 || col > gridSize)
            throw new IllegalArgumentException("Outside prescribed range");
    }

    private int convertToSingleArray(int row, int col) {
        return ((row - 1) * gridSize) + col;
    }
}
