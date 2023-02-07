package princeton;

import org.junit.jupiter.api.Test;
import princeton.w1.Percolation;

import static org.junit.jupiter.api.Assertions.*;

class PercolationTest {
    @Test void initGrid() {
        Percolation percolation = new Percolation(2);
        assertFalse(percolation.isOpen(1,1));
        assertFalse(percolation.isOpen(2,2));
    }

    @Test void initGridFails() {
        assertThrows(IllegalArgumentException.class, () -> {
            Percolation percolation = new Percolation(-1);
        });
    }

    @Test void offGridIndexFails() {
        assertThrows(IllegalArgumentException.class, () -> {
            Percolation percolation = new Percolation(2);
            percolation.isOpen(5,5);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            Percolation percolation = new Percolation(2);
            percolation.isFull(5,5);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            Percolation percolation = new Percolation(2);
            percolation.open(5,5);
        });
    }

    @Test void openSite() {
        Percolation percolation = new Percolation(2);
        percolation.open(1,1);
        assertEquals(1, percolation.numberOfOpenSites());
        assertTrue(percolation.isOpen(1,1));
        assertFalse(percolation.isOpen(1,2));
        assertFalse(percolation.isOpen(2,1));
        assertFalse(percolation.isOpen(2,2));
    }

    @Test void isFull() {
        // [x , , ]
        // [x , , ]
        // [x , , ]
        Percolation percolation = new Percolation(3);

        assertFalse(percolation.isFull(1,1));
        percolation.open(1,1);
        assertTrue(percolation.isFull(1,1));

        assertFalse(percolation.isFull(2,1));
        percolation.open(2,1);
        assertTrue(percolation.isFull(2,1));

        assertFalse(percolation.isFull(3,1));
        percolation.open(3,1);
        assertTrue(percolation.isFull(3,1));
    }

    @Test void percolates() {
        Percolation percolation = new Percolation(2);
        percolation.open(1,1);
        assertFalse(percolation.percolates());
        percolation.open(2,2);
        assertFalse(percolation.percolates());
        percolation.open(2,1);
        assertTrue(percolation.percolates());
    }
}
