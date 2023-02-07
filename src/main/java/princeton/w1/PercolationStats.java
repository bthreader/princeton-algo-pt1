package princeton.w1;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private final double[] percolationThresholds;
    private final int n;
    private final int trials;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (trials <= 0) {
            throw new IllegalArgumentException();
        }

        this.percolationThresholds = new double[trials];
        this.n = n;
        this.trials = trials;

        for (int i = 0; i < trials; i++) {
            Percolation p = new Percolation(n);
            int totalOpenSites = 0;

            while (!p.percolates()) {
                randomnlyOpenSite(p);
                totalOpenSites++;
            }

            percolationThresholds[i] = (double) totalOpenSites / (n *n);
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(this.percolationThresholds);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(this.percolationThresholds);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return this.mean()-1.96/Math.sqrt(this.trials);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return this.mean()+1.96/Math.sqrt(this.trials);
    }

    // test client (see below)
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int t = Integer.parseInt(args[1]);
        PercolationStats percolationStats = new PercolationStats(n, t);
        StdOut.println("mean                    = ".concat(String.valueOf(percolationStats.mean())));
        StdOut.println("stddev                  = ".concat(String.valueOf(percolationStats.stddev())));
        StdOut.println("95% confidence interval = ["
                .concat(String.valueOf(percolationStats.confidenceLo()))
                .concat(", ")
                .concat(String.valueOf(percolationStats.confidenceHi()))
                .concat("]")
        );
    }

    private void randomnlyOpenSite(Percolation p) {
        boolean openSite = true;
        int row = 0;
        int col = 0;

        // Sample until we find a blocked site
        while (openSite) {
            row = StdRandom.uniformInt(1, n + 1);
            col = StdRandom.uniformInt(1, n + 1);

            openSite = p.isOpen(row, col);
        }

        p.open(row, col);
    }
}