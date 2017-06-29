import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by admin on 25.06.2017.
 */
public class PercolationStats {
    private int N;
    private int T;
    private double[] statsArray;
    private Percolation percolation;

    public PercolationStats(int N, int T) {
        this.N = N;
        this.T = T;
        statsArray = new double[T];
    }

    public void experiment() {
        for (int i = 0; i < T; i++) {
            percolation = new Percolation(N);
            while (!percolation.percolates()) {
                percolation.open(StdRandom.uniform(N), StdRandom.uniform(N));
            }
            statsArray[i] = (double)percolation.numberOfOpenSites()/N/N;
            StdRandom.setSeed(System.currentTimeMillis());
        }
    }

    public void arrayOut() {
        System.out.println(StdStats.mean(statsArray));
        System.out.println(StdStats.stddev(statsArray));
    }

    public static void main(String[] args) {
        Stopwatch stopwatch = new Stopwatch();
        PercolationStats stats = new PercolationStats(200, 100);
        stats.experiment();
        stats.arrayOut();
        System.out.println("Time: " + stopwatch.elapsedTime());
    }
}
