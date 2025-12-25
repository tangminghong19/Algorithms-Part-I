import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import edu.princeton.cs.algs4.StdRandom;

public class Percolation {

    private final boolean[][] sites;
    private final int size;
    private final int virtualTop;
    private final int virtualBottom;
    private final WeightedQuickUnionUF uf;
    private final WeightedQuickUnionUF ufFull;
    private int openSitesCount;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) throw new IllegalArgumentException();
        this.size = n;
        this.sites = new boolean[n][n];
        this.uf = new WeightedQuickUnionUF(n * n + 2);
        this.ufFull = new WeightedQuickUnionUF(n * n + 1);
        this.virtualTop = 0;
        this.virtualBottom = n * n + 1;
        this.openSitesCount = 0;
    }

    private int getIndex(int row, int col) {
        return (row - 1) * size + col;
    }

    private void validate(int row, int col) {
        if (row < 1 || row > size || col < 1 || col > size)
            throw new IllegalArgumentException();
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        validate(row, col);
        if (isOpen(row, col)) return;

        sites[row - 1][col - 1] = true;
        openSitesCount++;
        int currIdx = getIndex(row, col);

        // Connect to virtual top/bottom
        if (row == 1) {
            uf.union(currIdx, virtualTop);
            ufFull.union(currIdx, virtualTop);
        }
        if (row == size) {
            uf.union(currIdx, virtualBottom);
        }

        // Connect to neighbours
        // Always runs exactly 4 times, regardless of n (O(1))
        int[][] neighbours = {{row-1, col}, {row+1, col}, {row, col-1}, {row, col+1}};
        for (int[] neighbour : neighbours) {
            int r = neighbour[0];
            int c = neighbour[1];
            if (r >= 1 && r <= size && c >= 1 && c <= size && isOpen(r, c)) {
                uf.union(currIdx, getIndex(r, c));
                ufFull.union(currIdx, getIndex(r, c));
            }
        }
    }

    public boolean isOpen(int row, int col) {
        validate(row, col);
        return sites[row - 1][col - 1];
    }

    public boolean isFull(int row, int col) {
        validate(row, col);
        return ufFull.find(getIndex(row, col)) == ufFull.find(virtualTop);
    }

    public int numberOfOpenSites() {
        return openSitesCount;
    }

    // does the system percolate?
    public boolean percolates() {
        if (size == 1) return isOpen(1, 1);
        return uf.find(virtualTop) == uf.find(virtualBottom);
    }

    // test client
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        Percolation perc = new Percolation(n);
        while (!perc.percolates()) {
            int r = StdRandom.uniformInt(1, n + 1);
            int c = StdRandom.uniformInt(1, n + 1);
            perc.open(r, c);
        }
        System.out.println("Percolated at " + perc.numberOfOpenSites() + " open sites.");
    }
}
