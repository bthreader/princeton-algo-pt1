package princeton.w1;

import edu.princeton.cs.algs4.StdRandom;
import java.util.List;
import java.util.ArrayList;

public class PercolationStats {
    private double[] percolationThresholds;
    private final int n;
    private final int trials;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (trials <= 0) {
            throw new IllegalArgumentException();
        }

        this.percolationThresholds = new double[trials];
        this.trials = trials;
        this.n = n;

        for (int i = 0; i < trials; i++) {
            performTrial(i);
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        double sum = 0;
        for (int i = 0; i < this.trials; i++) {
            sum += this.percolationThresholds[i];
        }
        return sum/this.trials;
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

    private void performTrial(int trialIndex) {
        Percolation percolation = new Percolation(this.n);

        int x;
        int y;
        int index;
        List<int[]> blockedSites = this.createInitialCoordsArray();
        while (!percolation.percolates() && blockedSites.size() > 0) {
            index = StdRandom.uniformInt(blockedSites.size()-1);
            int[] blockedSite = blockedSites.get(index);
            x = blockedSite[0];
            y = blockedSite[1];
            percolation.open(x, y);
            blockedSites.remove(index);
        }

        int totalSites = n*n;
        int fullSites = totalSites-blockedSites.size();
        percolationThresholds[trialIndex] = (double) fullSites/totalSites;
    }

    private List<int[]> createInitialCoordsArray() {
        List<int[]> coords = new ArrayList<>();

        for (int f = 1; f < this.n+1; f++) {
            for (int g = 1; g < this.n+1; g++) {
                int[] coord = new int[2];
                coord[0] = f;
                coord[1] = g;
                coords.add(coord);
            }
        }

        return coords;
    }
}