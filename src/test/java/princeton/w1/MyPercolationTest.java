package princeton;

import princeton.w1.extra.MyPercolation;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MyPercolationTest {
    @Test void initGrid() {
        MyPercolation percolation = new MyPercolation(2);
        assertFalse(percolation.isOpen(1, 1));
        assertFalse(percolation.isOpen(2, 2));
    }

    @Test void initGridFails() {
        assertThrows(IllegalArgumentException.class, () -> {
            MyPercolation percolation = new MyPercolation(-1);
        });
    }

    @Test void offGridIndexFails() {
        assertThrows(IllegalArgumentException.class, () -> {
            MyPercolation percolation = new MyPercolation(2);
            percolation.isOpen(5,5);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            MyPercolation percolation = new MyPercolation(2);
            percolation.isFull(5,5);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            MyPercolation percolation = new MyPercolation(2);
            percolation.open(5,5);
        });
    }

    @Test void openSite() {
        MyPercolation percolation = new MyPercolation(2);
        percolation.open(1,1);
        assertEquals(1, percolation.numberOfOpenSites());
        assertTrue(percolation.isOpen(1, 1));
        assertFalse(percolation.isOpen(1, 2));
        assertFalse(percolation.isOpen(2, 1));
        assertFalse(percolation.isOpen(2, 2));
    }

    @Test void isFull() {
        // [x , , ]
        // [x , , ]
        // [x , , ]
        MyPercolation percolation = new MyPercolation(3);
        percolation.open(1,1);
        assertTrue(percolation.isFull(1, 1));
        percolation.open(2,1);
        assertTrue(percolation.isFull(2, 1));
        percolation.open(3,1);
        assertTrue(percolation.isFull(3, 1));
    }

    @Test void rootAndParent() {
        MyPercolation percolation = new MyPercolation(3);
        percolation.open(1,1);
        assertEquals(9, percolation.parent(1,1));
        assertEquals(9, percolation.root(1,1));
        percolation.open(2,1);
        assertEquals(0, percolation.parent(2,1));
        assertEquals(9, percolation.root(2,1));

        // Bottom row open > set site parent = virtual bottom site index +
        // set ROOT of above site (if exists and not open) to virtual bottom
        percolation.open(3,1);
        assertEquals(10, percolation.parent(3,1));
        assertEquals(10, percolation.root(3,1));
        assertEquals(10, percolation.root(9));

        // Top row open > set site parent = virtual top site index +
        // set ROOT of below site (if exists and not open) to virtual top
        percolation = new MyPercolation(3);
        percolation.open(2,1);
        assertEquals(3, percolation.parent(2,1));
        percolation.open(1,1);
        assertEquals(9, percolation.root(2,1));
    }

    @Test void percolates() {
        MyPercolation percolation = new MyPercolation(2);
        percolation.open(1,1);
        assertFalse(percolation.percolates());
        percolation.open(2,2);
        assertFalse(percolation.percolates());
        percolation.open(2,1);
        assertTrue(percolation.percolates());
    }
}
