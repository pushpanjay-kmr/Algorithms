package java.com.pushpanjaykumar.algo.datastructures.linkedlist;

import java.util.Iterator;

/**
 * @author pushpanjay.kumar
 */
public class DoublyLinkedList<T> implements Iterable<T> {
    private int size = 0;
    private Node<T> head = null;
    private Node<T> tail = null;

    private class Node<T> {
        private T data;
        private Node<T> next;
        private Node<T> prev;

        public Node(T data, Node<T> next, Node<T> prev) {
            this.data = data;
            this.next = next;
            this.prev = prev;
        }

        @Override
        public String toString() {
            return data.toString();
        }
    }

    public void clear() {
        Node<T> curr = head;
        while (curr != null) {
            Node<T> next = curr.next;
            curr.prev = null;
            curr.next = null;
            curr.data = null;
            curr = next;
        }
        size = 0;
        head = tail = null;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void addlast(T elem) {
        if (isEmpty()) {
            head = tail = new Node<>(elem, null, null);
        } else {
            tail.next = new Node<>(elem, null, tail);
            tail = tail.next;
        }
        size++;
    }

    public void addFirst(T elem) {
        if (isEmpty()) {
            head = tail = new Node<>(elem, null, null);
        } else {
            head.prev = new Node<>(elem, head, null);
            head = head.prev;
        }
        size++;
    }

    public void addAt(int index, T elem) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Invalid Index");
        }

        if (index == 0) {
            addFirst(elem);
            return;
        }

        if (index == size) {
            addlast(elem);
            return;
        }

        Node<T> p = head;

        for (int i = 0; i < index - 1; i++) {
            p = p.next;
        }

        Node<T> newNode = new Node<>(elem, p.next, p);
        p.next.prev = newNode;
        p.next = newNode;
        size++;
    }

    public T peekFirst() {
        if (isEmpty()) {
            throw new RuntimeException("Empty list");
        }
        return head.data;
    }

    public T peekLast() {
        if (isEmpty()) {
            throw new RuntimeException("Empty list");
        }
        return tail.data;
    }

    public T removeFirst() {
        T first = peekFirst();
        head = head.next;
        size--;

        if (isEmpty()) {
            tail = null;
        } else {
            head.prev = null;
        }
        return first;
    }

    public T removeLast() {
        T last = peekLast();
        tail = tail.prev;
        size--;

        if (isEmpty()) {
            head = null;
        } else {
            tail.next = null;
        }
        return last;
    }

    private T remove(Node<T> node) {
        if (node == null) {
            throw new IllegalArgumentException("Illegal Argument " + node.data);
        }
        if (node.prev == null) {
            return removeFirst();
        }
        if (node.next == null) {
            return removeLast();
        }

        node.prev.next = node.next;
        node.next.prev = node.prev;
        size--;

        T r = node.data;
        node = null;

        return r;
    }


    public T removeAt(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Invalid Index");
        }

        if (index == 0) {
            return removeFirst();
        }

        if (index == size - 1) {
            return removeLast();
        }

        Node<T> cur = head;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        return remove(cur);
    }

    public boolean remove(Object obj) {
        Node<T> curr = head;

        // Support searching for null
        if (obj == null) {
            while (curr != null) {
                if (curr.data == null) {
                    remove(curr);
                    return true;
                }
                curr = curr.next;
            }
        } else {
            while (curr != null) {
                if (obj.equals(curr.data)) {
                    remove(curr);
                    return true;
                }
                curr = curr.next;
            }
        }
        return false;
    }

    public int indexOf(Object obj) {
        int index = 0;
        Node<T> curr = head;

        // Support searching for null
        if (obj == null) {
            for (; curr != null; curr = curr.next, index++) {
                if (curr.data == null) {
                    return index;
                }
            }
        } else
            for (; curr != null; curr = curr.next, index++) {
                if (obj.equals(curr.data)) {
                    return index;
                }
            }

        return -1;
    }

    public boolean contains(Object obj) {
        return indexOf(obj) != -1;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node<T> curr = head;

            @Override
            public boolean hasNext() {
                return curr != null;
            }

            @Override
            public T next() {
                T r = curr.data;
                curr = curr.next;
                return r;
            }
        };
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[ ");
        Node<T> curr = head;
        while (curr != null) {
            sb.append(curr.data);
            if (curr.next != null) {
                sb.append(", ");
            }
            curr = curr.next;
        }
        sb.append(" ]");
        return sb.toString();
    }
}
