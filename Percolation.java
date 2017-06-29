import edu.princeton.cs.algs4.WeightedQuickUnionUF;

import java.util.Arrays;

/**
 * Created by admin on 24.06.2017.
 */
public class Percolation {
    private boolean[] grid;
    private int numberOfOpenSites;
    private WeightedQuickUnionUF weightedQuickUnionUF;
    private int N;

    public Percolation(int N) {
        if (N <= 0) throw new IllegalArgumentException();
        this.N = N;
        grid = new boolean[N * N];
        weightedQuickUnionUF = new WeightedQuickUnionUF(N * N);
        numberOfOpenSites = 0;
        Arrays.fill(grid, false);
    }

    private int changeDimension(int i, int j) {
        return i * N + j;
    }

    public void open(int i, int j) {
        if (i < 0 || i > N - 1 || j < 0 || i > N - 1) throw new IndexOutOfBoundsException();
        if (i == 0) weightedQuickUnionUF.union(changeDimension(0, j), 0);
        if (grid[changeDimension(i, j)]) return;
        else  {
            grid[changeDimension(i, j)] = true;
            if (i > 0)
                if (isOpen(i - 1, j))
                    weightedQuickUnionUF.union(changeDimension(i, j), changeDimension(i - 1, j));
            if (j > 0)
                if (isOpen(i, j - 1))
                    weightedQuickUnionUF.union(changeDimension(i, j), changeDimension(i, j - 1));
            if (i < N - 1)
                if (isOpen(i + 1, j))
                    weightedQuickUnionUF.union(changeDimension(i, j), changeDimension(i + 1, j));
            if (j < N - 1)
                if (isOpen(i, j + 1))
                    weightedQuickUnionUF.union(changeDimension(i, j), changeDimension(i, j + 1));
        }
        numberOfOpenSites++;
    }

    public boolean isOpen(int i, int j) {
        return grid[changeDimension(i, j)];
    }

    public boolean isFull(int i, int j) {
        return weightedQuickUnionUF.connected(changeDimension(i, j), 0);
    }

    public boolean percolates() {
        for (int j = 0; j < N; j++)
            if (isFull(N - 1, j)) return true;
        return false;
    }

    public int numberOfOpenSites() {
        return numberOfOpenSites;
    }

    public static void main(String[] args) {

    }
}
