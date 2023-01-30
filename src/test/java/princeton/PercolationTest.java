package princeton;

import princeton.w1.Percolation;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PercolationTest {
    @Test void initGrid() {
        Percolation percolation = new Percolation(5);
        assertEquals(percolation.isOpen(1,1), false);
    }
}
