package princeton.w1;

import edu.princeton.cs.algs4.StdRandom;

public class PercolationStats {
    private double[] percolationThresholds;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (trials <= 0) {
            throw new IllegalArgumentException();
        }

        this.percolationThresholds = new double[n];

        for (int i = 0; i < trials; i++) {
            performTrial(i, n);
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        double x;
        x = 100.00;
        return x;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        double x;
        x = 100.00;
        return x;
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        double x;
        x = 100.00;
        return x;
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        double x;
        x = 100.00;
        return x;
    }

    private void performTrial(int trialIndex, int n) {
        Percolation percolation = new Percolation(n);

        int x;
        int y;
        boolean[] blockedSites;
        while (!percolation.percolates()) {
            // Change this to only sample blocked sites
            x = StdRandom.uniformInt(n-1)+1;
            y = StdRandom.uniformInt(n-1)+1;
            percolation.open(x, y);
        }

        percolationThresholds[trialIndex] = 2/3;
    }
}