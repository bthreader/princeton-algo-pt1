package princeton;

import princeton.w1.PercolationStats;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PercolationStatsTest {
    @Test void mean() {
        PercolationStats percolationStats = new PercolationStats(200, 100);
        assertTrue(percolationStats.mean() >= 0);
    }
}
