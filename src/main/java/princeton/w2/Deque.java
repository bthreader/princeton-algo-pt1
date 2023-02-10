package princeton.w2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
        private Node linkedList = new Node();
        private int linkedListSize = 0;

        class Node {
                private Node next = null;
                private Item value = null;

                public Node() {}
                public Node(Node next, Item value) {
                        this.next = next;
                        this.value = value;
                }

                public void enqueue(Item value) {
                        if (!isEmpty()) {
                                next = new Node(next, this.value);
                        }
                        this.value = value;
                }

                // Returns the item that is removed
                public Item dequeue() {
                        if (isEmpty()) {
                                throw new NoSuchElementException();
                        }
                        Item valueCopy = value;

                        // List becomes empty
                        if (next == null) {
                                value = null;
                        }
                        else {
                                value = next.value;
                                next = next.next;
                        }
                        return valueCopy;
                }

                public void push(Item value) {
                        if (isEmpty()) {
                                this.value = value;
                                return;
                        }
                        if (next == null) {
                                next = new Node(null, value);
                                return;
                        }
                        Node last = next;
                        // Get the pointer into the correct place
                        while (last.next != null) {
                                last = last.next;
                        }
                        last.next = new Node(null, value);
                }

                // Returns the item that is removed
                public Item pop() {
                        if (isEmpty()) {
                                throw new NoSuchElementException();
                        }
                        Node secondLast = new Node();
                        Node last = next;
                        // Get the pointers into the correct place
                        while (last.next != null) {
                                secondLast = last;
                                last = last.next;
                        }
                        Item valueCopy = last.value;
                        secondLast.next = null;
                        return valueCopy;
                }

                public boolean isEmpty() {
                        return value == null;
                }
        }

        // construct an empty deque
        public Deque() {}

        // is the deque empty?
        public boolean isEmpty() {return linkedList.isEmpty();}

        // return the number of items on the deque
        public int size() {return linkedListSize;}

        // add the item to the front
        public void addFirst(Item item) {
                linkedList.enqueue(item);
                linkedListSize++;
        }

        // add the item to the back
        public void addLast(Item item) {
                linkedList.push(item);
                linkedListSize++;
        }

        // remove and return the item from the front
        public Item removeFirst() {
                Item item = linkedList.dequeue();
                linkedListSize--;
                return item;
        }

        // remove and return the item from the back
        public Item removeLast() {
                Item item = linkedList.pop();
                linkedListSize--;
                return item;
        }

        // return an iterator over items in order from front to back
        public Iterator<Item> iterator() {
                return new Iterator<Item>() {
                        @Override
                        public boolean hasNext() {
                                return false;
                        }

                        @Override
                        public Item next() {
                                return null;
                        }
                };
        }

        // unit testing (required)
        public static void main(String[] args) {}
}
