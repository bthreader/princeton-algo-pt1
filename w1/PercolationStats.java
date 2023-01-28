package w1;

public class PercolationStats {

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n >= 0 || trials <= 0) {
            throw new IllegalArgumentException();
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

   // test client (see below)
   public static void main(String[] args) {}

}