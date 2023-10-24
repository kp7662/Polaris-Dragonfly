import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.Stopwatch;

public class PercolationStats {
    private final int trials; // number of trials
    private final double mean; // mean of percolation threshold
    private final double stddev; // standard deviation of percolation threshold
    private double CONFIDENCE_INTERVAL = 1.96; // 95% confidence interval constant

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {

        // throw an IllegalArgumentException in the constructor if either n ≤ 0
        // or T ≤ 0
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException("invalid input");
        }

        this.trials = trials;
        double[] threshold = new double[trials]; // array to hold threshold for each trial

        // perform independent trials on an n-by-n grid
        for (int i = 0; i < trials; i++) {
            Percolation percolation = new Percolation(n);

            // generate random site (row, col) within range [0,n)
            while (!percolation.percolates()) {
                int row = StdRandom.uniform(0, n);
                int col = StdRandom.uniform(0, n);

                // check if (row, col) has already been opened. If not, open it
                if (!percolation.isOpen(row, col)) {
                    percolation.open(row, col);
                }
            }

            // calculate threshold when the system percolates
            int num = percolation.numberOfOpenSites(); // number of open sites
            int totalNum = n * n; // total number of sites
            threshold[i] = (double) num / totalNum; // percolation threshold

        }
        // calculate sample mean and sample stddev of percolation thresholds
        mean = StdStats.mean(threshold);
        stddev = StdStats.stddev(threshold);
    }

    // return sample mean of percolation threshold
    public double mean() {
        return mean;
    }

    // return sample standard deviation of percolation threshold
    public double stddev() {
        return stddev;
    }

    // return low endpoint of 95% confidence interval
    public double confidenceLow() {
        double lowerCI = mean -
                (CONFIDENCE_INTERVAL * stddev / Math.sqrt(trials));
        return lowerCI;
    }

    // return high endpoint of 95% confidence interval
    public double confidenceHigh() {
        double higherCI = mean +
                (CONFIDENCE_INTERVAL * stddev / Math.sqrt(trials));
        return higherCI;
    }

    // test client (see below)
    public static void main(String[] args) {
        Stopwatch stopwatch = new Stopwatch();
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);

        PercolationStats percolationStats = new PercolationStats(n, trials);

        StdOut.println("mean()           = " + percolationStats.mean());
        StdOut.println("stddev()         = " + percolationStats.stddev());
        StdOut.println("confidenceLow()  = " + percolationStats.confidenceLow());
        StdOut.println("confidenceHigh() = " + percolationStats.confidenceHigh());
        StdOut.println("elapsed time     = " + stopwatch.elapsedTime());

    }
}
