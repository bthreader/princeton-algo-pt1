package princeton.w2;

import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] deque;

    // construct an empty randomized queue
    public RandomizedQueue() {}

    // is the randomized queue empty?
    public boolean isEmpty() {return true;}

    // return the number of items on the randomized queue
    public int size() {return 10;}

    // add the item
    public void enqueue(Item item) {}

    // remove and return a random item
    public Item dequeue() {return deque[0];}

    // return a random item (but do not remove it)
    public Item sample() {return deque[0];}

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {return new Iterator<Item>() {
        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public Item next() {
            return null;
        }
    };}

    // unit testing (required)
    public static void main(String[] args) {}
}