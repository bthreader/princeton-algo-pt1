package princeton.w1;

import edu.princeton.cs.algs4.StdIn;

public class Percolation {
    private int top;
    private int bottom;
    private int n;
    private int[][] id;
    private boolean[][] open;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n<=0) {
            throw new IllegalArgumentException();
        }

        this.n = n;
        this.id = new int[n][n];
        this.open = new boolean[n][n];
        this.top = n+1;
        this.bottom = n+2;

        for (int x = 0; x < n; x++) {
            for (int y = 0; y < n; y++) {
                this.id[x][y] = x+y+1;
                this.open[x][y] = false;
            }
        }
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        this.checkGridValidity(row, col);
        if (!isOpen(row, col)) return;
        open[row][col] = true;
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        this.checkGridValidity(row, col);
        return open[row][col];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        this.checkGridValidity(row, col);
        return false;
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return 1;
    }

    // does the system percolate?
    public boolean percolates() {
        return this.top == this.bottom;
    }

    // test client (optional)
    public static void main(String[] args) {
        int n = StdIn.readInt();
        Percolation percolation = new Percolation(n);
    }

    private void checkGridValidity(int row, int col) {
        if (row > this.n || row <= 0) {
            throw new IllegalArgumentException();
        }
        if (col > this.n || col <= 0) {
            throw new IllegalArgumentException();
        }
    }
}