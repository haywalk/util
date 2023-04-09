package ca.haywalk.util.collection;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A queue based on a linked list.
 * 
 * @author Hayden Walker
 * @version 2023-04-07
 */
public class LinkedQueue<T> implements Queue<T> {

    /**
     * The size of the queue.
     */
    private int size = 0;

    /**
     * The front of the queue.
     */
    private Node<T> head;

    /**
     * The back of the queue.
     */
    private Node<T> tail;

    /**
     * Create a new LinkedQueue object.
     */
    public LinkedQueue() {
        head = null;
        tail = null;
        size = 0;
    }

    /**
     * Return the size of the queue.
     * 
     * @return the size of the queue.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Add an item to the queue.
     * 
     * @param item Item to add.
     * @throws NullPointerException if item is null.
     */
    @Override
    public boolean add(T item) throws NullPointerException {
        enqueue(item);
        return true;
    }

    /**
     * Enqueue a collection of items. Will enqueue them in
     * the order they appear in the collection's Iterator.
     * 
     * @return {@code true} if successful.
     * @throws NullPointerException If the collection or any items in it are {@code null}.
     */
    @Override
    public boolean addAll(Collection<? extends T> items) {
        // Throw exception if collection is null
        if(items == null) {
            throw new NullPointerException("Cannot add null collection.");
        }

        // Create a buffer collection
        Collection<T> toAdd = new ArrayList<T>();

        // Add each item to the buffer
        for(T item : items) {
            // Check for nullity
            if(item == null) {
                throw new NullPointerException("Collection has null element.");
            }

            // Add to buffer
            toAdd.add(item);
        }

        // Add all items from buffer to this stack
        for(T item : toAdd) {
            this.add(item);
        }

        // Return true
        return true;
    }

    /**
     * Return an iterator over this LinkedQueue.
     * 
     * @return An iterator over this LinkedQueue.
     */
    @Override
    public Iterator<T> iterator() {
        return new LinkedQueueIterator();
    }

    /**
     * Empty the queue.
     */
    @Override
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    /**
     * Check if the queue is empty.
     * 
     * @return {@code true} if the queue is empty.
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Add an item to the queue.
     * 
     * @param item Item to add.
     * @throws NullPointerException if item is null.
     */
    @Override
    public void enqueue(T item) throws NullPointerException, QueueOverflowException {
        // Throw exception if item is null
        if(item == null) {
            throw new NullPointerException("Cannot add null to queue.");
        }

        // Create a new node to store the new item
        Node<T> newNode = new Node<T>(item);

        // If the queue is empty, the new node is the head
        if(isEmpty()) {
            head = newNode;
        } 
        
        // If the queue is non-empty, add new node onto the end
        else {
            tail.setNext(newNode);
        }

        // The new node is now the end
        tail = newNode;

        // Increment size
        size++;
    }

    /**
     * Remove the next item from the queue.
     * 
     * @return The next item in the queue.
     * @throws QueueUnderflowException If queue is empty.
     */
    @Override
    public T dequeue() throws QueueUnderflowException {
        // Throw exception if empty
        if(isEmpty()) {
            throw new QueueOverflowException("Cannot dequeue from empty queue.");
        }

        // Remove and return front of queue
        T removed = head.getData();
        head = head.getNext();
        size--;
        return removed;
    }

    /**
     * Remove a specific item from the queue.
     * 
     * @param item Item to remove.
     * @return {@code true} if item is successfully removed.
     * @throws NullPointerException If item is {@code null}.
     */
    @Override
    public boolean remove(T item) throws NullPointerException {
        // Throw exception if item is null
        if(item == null) {
            throw new NullPointerException("Cannot remove null.");
        }

        // Check if empty
        if(isEmpty()) {
            return false;
        }

        // Check if head contains the item to remove
        if(head.getData().equals(item)) {
            head = head.getNext();
            size--;
            return true;
        }

        // Otherwise traverse the list until the item is found
        Node<T> current = head;

        while(current.getNext() != null) {
            // If the next node has the data, link this node to the next-next node
            if(current.getNext().getData().equals(item)) {
                current.setNext(current.getNext().getNext());
                size--;
                return true;
            }
        }

        // Return false if not found
        return false;
    }

    /**
     * Check whether the queue contains a certain item.
     * 
     * @param item Item to check for.
     * @return {@code true} if item is in the queue.
     * @throws NullPointerException If item is {@code null}.
     */
    @Override
    public boolean contains(T item) throws NullPointerException {
        // Throw exception if item is null
        if(item == null) {
            throw new NullPointerException("Cannot check for null.");
        }

        // Traverse the linked list
        Node<T> current = head;
        
        while(current != null) {
            // Return true if found
            if(current.getData().equals(item)) {
                return true;
            }

            // Move onto next node
            current = current.getNext();
        }

        // Return false if not found.
        return false;
    }
    
    /**
     * A linked list node.
     */
    private static class Node<T> {
        // Instance variables for data and next node
        private T data;
        private Node<T> next;

        /**
         * Create a new Node object.
         * 
         * @param data Data to store in the node.
         */
        public Node(T data) {
            this.data = data;
            this.next = null;
        }

        /**
         * Get the data stored in the node.
         * 
         * @return The data stored in the node.
         */
        public T getData() {
            return data;
        }

        /**
         * Get the next node.
         * 
         * @return The next node.
         */
        public Node<T> getNext() {
            return next;
        }

        /**
         * Set the next node.
         * 
         * @param next New next node.
         */
        public void setNext(Node<T> next) {
            this.next = next;
        }
    }

    private class LinkedQueueIterator implements Iterator<T> {
        // Current node
        private Node<T> current;

        /**
         * Create a new LinkedQueueIterator
         */
        public LinkedQueueIterator() {
            current = head;
        }

        /**
         * Check if the queue has another item.
         * 
         * @return {@code true} if the queue has another item.
         */
        @Override
        public boolean hasNext() {
            return current != null;
        }

        /**
         * Get the next item.
         * 
         * @return The next item in the queue.
         * @throws NoSuchElementException If there is no next item.
         */
        @Override
        public T next() {
            // Throw exception if there's no next item
            if(!hasNext()) {
                throw new NoSuchElementException("No next element.");
            }

            // Get the data out 
            T data = current.getData();
            current = current.getNext();
            return data;
        }

    }
}
