package princeton.w2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
        private int size = 0;
        private Node first = null;
        private Node last = null;

        private class Node {
            private Node next;
            private Node prev;
            private final Item item;

            public Node(Node next, Node prev, Item item) {
                this.next = next;
                this.prev = prev;
                this.item = item;
            }

            public void setNext(Node next) {
                this.next = next;
            }

            public Node getNext() {
                return this.next;
            }

            public void setPrev(Node prev) {
                this.prev = prev;
            }

            public Node getPrev() {
                return this.prev;
            }

            public Item getItem() {
                return this.item;
            }
        }

        // iterates front to back
        private class LinkedListIterator implements Iterator<Item> {
            private Node ll;

            public LinkedListIterator() {
                this.ll = first;
            }

            @Override
            public boolean hasNext() { return this.ll != null; };

            public Item next() {
                Item itemCopy = ll.getItem();
                ll = ll.getNext();
                return itemCopy;
            }
        }

        // construct an empty deque
        public Deque() {}

        // is the deque empty?
        public boolean isEmpty() {return size == 0;}

        // return the number of items on the deque
        public int size() {return size;}

        // add the item to the front
        public void addFirst(Item item) {
            if (isEmpty()) {addToEmpty(item);}
            else if (size == 1) {
                first = new Node(last, null, item);
                last.setPrev(first);
            }
            else {
                Node oldFirst = first;
                first = new Node(oldFirst, null, item);
                oldFirst.setPrev(first);
            }
            size++;
        }

        // add the item to the back
        public void addLast(Item item) {
            if (isEmpty()) {addToEmpty(item);}
            else if (size == 1) {
                last = new Node(null, first, item);
                first.setNext(last);
            }
            else {
                Node oldLast = last;
                last = new Node(null, oldLast, item);
                oldLast.setNext(last);
            }
            size++;
        }

        private void addToEmpty(Item item) {
            first = last = new Node(null, null, item);
        }

        // remove and return the item from the front
        public Item removeFirst() {
            if (this.isEmpty()) {
                throw new NoSuchElementException();
            }
            Item oldFirstItem = first.getItem();
            first = first.getNext();
            size--;
            return oldFirstItem;
        }

        // remove and return the item from the back
        public Item removeLast() {
            if (this.isEmpty()) {
                throw new NoSuchElementException();
            }
            Item oldLastItem = last.getItem();
            last = last.getPrev();
            size--;
            return oldLastItem;
        }

        // return an iterator over items in order from front to back
        public Iterator<Item> iterator() {
            return new LinkedListIterator();
        }

        // unit testing (required)
        public static void main(String[] args) {}
}
