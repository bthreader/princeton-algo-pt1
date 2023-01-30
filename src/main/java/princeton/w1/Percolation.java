package princeton.w1;

public class Percolation {
    private int top;
    private int bottom;
    private int n;
    private int[] id;
    private boolean[][] open;
    private int totalOpen = 0;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n<=0) {
            throw new IllegalArgumentException();
        }

        this.n = n;
        this.id = new int[n*n+2];
        this.open = new boolean[n][n];
        this.top = n;
        this.bottom = n+1;

        for (int x = 0; x < (n*n+2); x++) {
            this.id[x] = x;
        }
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        this.checkGridValidity(row, col);
        if (isOpen(row, col)) return;
        open[row-1][col-1] = true;
        this.totalOpen += 1;
        int adjacentSiteRoot;

        //
        // Top row and bottom row get special treatment because virtual sites
        // are not on the grid
        //
        if (row == 1) {
            this.setRoot(row, col, id[this.top]);
            // check below
            if (this.isOpen(row+1, col)) {
                adjacentSiteRoot = this.root(row+1, col);
                id[adjacentSiteRoot] = id[this.top];
            }
        }
        else if (row == this.n) {
            this.setRoot(row, col, id[this.bottom]);
            // check above
            if (this.isOpen(row-1, col)) {
                adjacentSiteRoot = this.root(row-1, col);
                id[adjacentSiteRoot] = id[this.bottom];
            }
        }

        //
        // Normal
        //
        else {
            int adjustments = 0;

            // left
            if (col > 1 && this.isOpen(row, col-1)) {
                this.setRoot(row, col, this.oneIndexedRowColToFlatIndex(row, col-1));
                adjustments += 1;
            }

            // right
            if (col < this.n && this.isOpen(row, col+1)) {
                if (adjustments == 0) {
                    this.setRoot(row, col, this.oneIndexedRowColToFlatIndex(row, col+1));
                    adjustments += 1;
                } 
                else {
                    this.mergeSiteRoots(row, col, row, col+1);
                }
            }

            // down
            if (row > 1 && this.isOpen(row-1, col)) {
                if (adjustments == 0) {
                    this.setRoot(row, col, this.oneIndexedRowColToFlatIndex(row-1, col));
                    adjustments += 1;
                } 
                else {
                    this.mergeSiteRoots(row, col, row-1, col);
                }
            }

            // up
            if (row < this.n && this.isOpen(row+1, col)) {
                if (adjustments == 0) {
                    this.setRoot(row, col, this.oneIndexedRowColToFlatIndex(row+1, col));
                } 
                else {
                    this.mergeSiteRoots(row, col, row+1, col);
                }
            }
        }
    }

    // is the site (row, col) open?
    // 1-indexed
    public boolean isOpen(int row, int col) {
        this.checkGridValidity(row, col);
        return open[row-1][col-1];
    }

    // is the site (row, col) full?
    // 1-indexed
    public boolean isFull(int row, int col) {
        this.checkGridValidity(row, col);
        return (this.root(row, col) == this.top);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return this.totalOpen;
    }

    // does the system percolate?
    public boolean percolates() {
        return id[this.top] == id[this.bottom];
    }

    // 1-indexed
    private void checkGridValidity(int row, int col) {
        if (row > this.n || row <= 0) {
            throw new IllegalArgumentException();
        }
        if (col > this.n || col <= 0) {
            throw new IllegalArgumentException();
        }
    }

    // 1-indexed
    private int root(int row, int col) {
        int index = oneIndexedRowColToFlatIndex(row, col);

        int currentParent = id[index];
        while (this.id[currentParent] != currentParent) {
            currentParent = this.id[currentParent];
        }

        return currentParent;
    }

    // Set the root of the site at (`row`, `col`) = newRoot
    // 1-indexed
    private void setRoot(int row, int col, int newRoot) {
        int index = oneIndexedRowColToFlatIndex(row, col);
        this.id[index] = newRoot;
    }

    // Merge a and b by setting aRoot = bRoot
    private void mergeSiteRoots(int arow, int acol, int brow, int bcol) {
        // Could be improved with recording tree height??
        int aRoot = this.root(arow, acol);
        id[aRoot] = id[this.root(brow, bcol)];
    }

    private int oneIndexedRowColToFlatIndex(int row, int col) {
        return (row-1) * this.n + (col-1);
    }
}