/*
@citation Adapted from:
https://docs.google.com/document/d/10xPow14DxD6ZzllBY12SPfWzp43tBMqPjeGDa96krnE/edit#
COS226 Percolation Assignment Page

@citation Adapted from:
https://docs.google.com/document/d/1EKLidIRwLLcIXzxqQ0eN-ahw_vPqYS1algQnwPAiIZU/edit#
COS226 Percolation Checklist

@citation Adapted from:
https://edstem.org/us/courses/27701/lessons/43776/slides/252423
COS226 Ed Lessons - Percolation section

@citation Adapted from:
https://drive.google.com/file/d/1wBeUD6ajCGh65PqECRqh9Hg1_XnTGZrJ/view
COS226 Union-Find Lecture Slides

@citation Adapted from:
https://drive.google.com/file/d/1j_apY0BOzQapk5ejvhrPqfVLt98z2R87/view
COS226 Analysis Techniques Lecture Slides
 */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    
    private final int n; // input n to generate a n-by-n grid
    private final int virtualTop; // virtual top site
    private final int virtualBottom; // virtual bottom site
    private final WeightedQuickUnionUF site; // UF of sites within n-by-n grid

    private boolean[] isOpen; // array to check if a particular site is open

    private int numberOfOpenSites; // number of open sites

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("invalid indices");
        }

        this.n = n;

        site = new WeightedQuickUnionUF(n * n + 2);

        virtualTop = 0; // index of virtual top site
        virtualBottom = n * n + 1; // index of virtual bottom site

        isOpen = new boolean[n * n + 2]; // create an array to check if a site is open

        numberOfOpenSites = 0;
    }

    // convert 2-D coordinates to 1-D coordinates with union-find elements
    // starting with top left corner being (0,0) and is assigned with index 1
    private int index(int row, int col) {
        return n * row + col + 1;
    }

    // validate the indices received
    private void validate(int row, int col) {
        if (row < 0 || row > n - 1 || col < 0 || col > n - 1) {
            throw new IllegalArgumentException("invalid indices");
        }
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        validate(row, col); // validate the indices received
        int index = index(row, col);
        if (!isOpen[index]) {
            isOpen[index] = true;
            numberOfOpenSites += 1;
        }

        // connect sites on first row to virtual top site
        if (row == 0) {
            site.union(index, virtualTop);
        }

        // connect sites on last row to virtual bottom site
        if (row == n - 1) {
            site.union(index, virtualBottom);
        }

        // connect site to open top neighbor
        int topNeighbor = index(row - 1, col); // index of top neighbor
        if (row > 0 && isOpen[topNeighbor]) {
            site.union(index, topNeighbor);
        }

        // connect site to open bottom neighbor
        int bottomNeighbor = index(row + 1, col); // index of bottom neighbor
        if (row < n - 1 && isOpen[bottomNeighbor]) {
            site.union(index, bottomNeighbor);
        }

        // connect site to open left neighbor
        int leftNeighbor = index(row, col - 1); // index of left neighbor
        if (col > 0 && isOpen[leftNeighbor]) {
            site.union(index, leftNeighbor);
        }

        // connect site to open right neighbor
        int rightNeighbor = index(row, col + 1); // index of right neighbor
        if (col < n - 1 && isOpen[rightNeighbor]) {
            site.union(index, rightNeighbor);
        }
    }

    // check if the site (row, col) is open
    public boolean isOpen(int row, int col) {
        validate(row, col); // validate the indices received
        int index = index(row, col);
        return isOpen[index];
    }

    // check if the site (row, col) is full
    public boolean isFull(int row, int col) {
        validate(row, col); // validate the indices received
        int index = index(row, col);
        if (site.find(index) == site.find(virtualTop)) {
            return true;
        }
        return false;
    }

    // return the number of open sites
    public int numberOfOpenSites() {
        return numberOfOpenSites;
    }

    // check if the system percolates
    public boolean percolates() {
        return site.find(virtualTop) == site.find(virtualBottom);
    }

    // unit testing (required)
    public static void main(String[] args) {
        Percolation percolation = new Percolation(5);
        percolation.open(0, 1);
        percolation.open(1, 1);
        percolation.open(2, 1);
        percolation.open(3, 1);
        percolation.open(4, 1);
        percolation.open(3, 3);

        StdOut.println("---TEST FOR isOpen---");
        StdOut.println(percolation.isOpen(0, 1));
        StdOut.println(percolation.isOpen(0, 4));

        StdOut.println("---TEST FOR isFull---");
        StdOut.println(percolation.isFull(4, 1));
        StdOut.println(percolation.isFull(3, 3));

        StdOut.println("---TEST FOR numberOfSites---");
        StdOut.println(percolation.numberOfOpenSites());

        StdOut.println("---TEST FOR percolates---");
        StdOut.println(percolation.percolates());

    }
}

