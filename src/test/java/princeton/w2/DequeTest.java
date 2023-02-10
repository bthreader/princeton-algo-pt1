package princeton.w2;

import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class DequeTest {
    @Test void InitDeque() {
        Deque<Integer> deque = new Deque<Integer>();
        assertThrows(NoSuchElementException.class, deque::removeFirst);
        assertThrows(NoSuchElementException.class, deque::removeLast);
    }
}
