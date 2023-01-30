package princeton;

import princeton.w1.Percolation;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PercolationTest {
    @Test void initGrid() {
        Percolation percolation = new Percolation(2);
        assertEquals(percolation.isOpen(1,1), false);
        assertEquals(percolation.isOpen(2,2), false);
    }

    @Test void openSite() {
        Percolation percolation = new Percolation(2);
        percolation.open(1,1);
        assertEquals(percolation.numberOfOpenSites(), 1);
        assertEquals(percolation.isOpen(1,1), true);
        percolation.open(2,2);
        assertEquals(percolation.numberOfOpenSites(), 2);
        assertEquals(percolation.isOpen(2,2), true);
    }

    @Test void percolateTwo() {
        // [x, ]
        // [x, ]
        Percolation percolation = new Percolation(2);
        percolation.open(1,1);
        percolation.open(2,1);
        assertEquals(percolation.percolates(), true);
    }

    @Test void percolateThree() {
        // [x, x,  ]
        // [ , x, x]
        // [ ,  , x]
        Percolation percolation = new Percolation(3);
        percolation.open(1,1);
        percolation.open(1,2);
        percolation.open(2,2);
        assertEquals(percolation.isFull(2,2), true);
        percolation.open(2,3);
        assertEquals(percolation.percolates(), false);
        percolation.open(3,3);
        assertEquals(percolation.percolates(), true);
    }

    @Test void percolateFour() {
        // [x, x,  ,  ]
        // [ , x, x,  ]
        // [ ,  , x, x]
        // [ ,  ,  . x]
        Percolation percolation = new Percolation(4);
        percolation.open(4,4);
        assertEquals(percolation.isFull(4,4), false);
        percolation.open(3,4);
        percolation.open(2,2);
        percolation.open(2,3);
        percolation.open(3,3);
        assertEquals(percolation.percolates(), false);
        percolation.open(1,2);
        percolation.open(1,1);
        assertEquals(percolation.percolates(), true);
    }
}
