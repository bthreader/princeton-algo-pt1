package princeton.w2;

import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class DequeTest {
    @Test void initDeque() {
        Deque<Integer> deque = new Deque<Integer>();
        assertThrows(NoSuchElementException.class, deque::removeFirst);
        assertThrows(NoSuchElementException.class, deque::removeLast);
    }

    @Test void addFirst() {
        Deque<Integer> deque = new Deque<Integer>();

        deque.addFirst(1);
        assertFalse(deque.isEmpty());
        assertEquals(1, deque.size());

        deque.addFirst(1);
        assertFalse(deque.isEmpty());
        assertEquals(2, deque.size());
    }

    @Test void removeFirst() {
        Deque<Integer> deque = new Deque<Integer>();
        // [2,1,0]
        for (int i = 0; i < 3; i++) {
            deque.addFirst(i);
        }
        assertEquals(3, deque.size());

        assertEquals(2, deque.removeFirst());
        assertEquals(2, deque.size());

        assertEquals(1, deque.removeFirst());
        assertEquals(1, deque.size());
    }

    @Test void addLast() {
        Deque<Integer> deque = new Deque<Integer>();

        deque.addLast(1);
        assertFalse(deque.isEmpty());
        assertEquals(1, deque.size());

        deque.addLast(1);
        assertFalse(deque.isEmpty());
        assertEquals(2, deque.size());
    }

    @Test void removeLast() {
        Deque<Integer> deque = new Deque<Integer>();
        // [0,1,2]
        for (int i = 0; i < 3; i++) {
            deque.addLast(i);
        }
        assertEquals(3, deque.size());

        assertEquals(2, deque.removeLast());
        assertEquals(2, deque.size());

        assertEquals(1, deque.removeLast());
        assertEquals(1, deque.size());
    }

    @Test void iterator() {
        Deque<Integer> deque = new Deque<Integer>();
        // [0,1,2]
        for (int i = 0; i < 3; i++) {
            deque.addLast(i);
        }

        Iterator<Integer> iterator = deque.iterator();
        assertTrue(iterator.hasNext());
        assertEquals(0, iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(1, iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(2, iterator.next());
        assertFalse(iterator.hasNext());
    }
}
