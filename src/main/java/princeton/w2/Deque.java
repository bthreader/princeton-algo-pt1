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
                        Node currentNext = next;
                        while (currentNext.next != null) {
                                currentNext = currentNext.next;
                        }
                        currentNext.next = new Node(null, value);
                }

                public Item pop() {
                        if (isEmpty()) {
                                throw new NoSuchElementException();
                        }
                        // List becomes empty
                        if (next == null) {
                                Item valueCopy = value;
                                value = null;
                                return valueCopy;
                        }
                        Node currentNext = next;
                        while (currentNext.next.next != null) {
                                currentNext = currentNext.next;
                        }
                        currentNext.next = null;
                        return currentNext.value;
                }

                public boolean isEmpty() {
                        return value == null;
                }
        }

        // construct an empty deque
        public Deque() {
                // Doubly linked list otherwise remove/add first will be O(n^2)
                // Remove/add last will be best case O(1) worst case O(n^2) (resize)
                // Can't use hashmaps so that rules out doubly linkedlist
        }

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
