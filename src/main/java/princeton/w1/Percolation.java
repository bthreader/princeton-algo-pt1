package princeton.w1;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private final int n;
    private final int top; // virtual top site index
    private final int bottom; // virtual bottom site index
    private boolean[][] open;
    private int totalOpen = 0;
    private WeightedQuickUnionUF qf;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n<=0) {
            throw new IllegalArgumentException();
        }

        this.n = n;
        this.top = n * n;
        this.bottom = top + 1;
        this.qf = new WeightedQuickUnionUF(n * n + 2);
        this.open = new boolean[n][n];
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        this.checkGridValidity(row, col);
        if (isOpen(row, col)) return;
        this.open[row-1][col-1] = true;
        this.totalOpen += 1;
        int flatIndex = oneIndexedRowColToFlatIndex(row,col);

        if (row == 1) {
            qf.union(flatIndex, top);
        }
        if (row == this.n) {
            qf.union(flatIndex, bottom);
        }
        // left
        if (col > 1 && this.isOpen(row, col-1)) {
            qf.union(flatIndex, this.oneIndexedRowColToFlatIndex(row, col-1));
        }
        // right
        if (col < this.n && this.isOpen(row, col+1)) {
                qf.union(flatIndex, this.oneIndexedRowColToFlatIndex(row, col+1));
        }
        // up
        if (row > 1 && this.isOpen(row-1, col)) {
            qf.union(flatIndex, this.oneIndexedRowColToFlatIndex(row-1, col));
        }
        // down
        if (row < this.n && this.isOpen(row+1, col)) {
            qf.union(flatIndex, this.oneIndexedRowColToFlatIndex(row+1, col));
        }
    }

    public boolean isOpen(int row, int col) {
        this.checkGridValidity(row, col);
        return open[row-1][col-1];
    }

    public boolean isFull(int row, int col) {
        this.checkGridValidity(row, col);
        return qf.find(top) == qf.find(this.oneIndexedRowColToFlatIndex(row , col));
    }

    public int numberOfOpenSites() {
        return totalOpen;
    }

    public boolean percolates() {
        return qf.find(top) == qf.find(bottom);
    }

    private void checkGridValidity(int row, int col) {
        if (row > this.n || row <= 0) {
            throw new IllegalArgumentException();
        }
        if (col > this.n || col <= 0) {
            throw new IllegalArgumentException();
        }
    }

    private int oneIndexedRowColToFlatIndex(int row, int col) {
        return (row-1) * this.n + (col-1);
    }
}