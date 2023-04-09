package ca.haywalk.util.collection;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * An array-based stack.
 * 
 * @author Hayden Walker
 * @version 2023-04-07
 */
public class ArrayStack<T> implements Stack<T> {
    /**
     * Default initial stack size.
     */
    public static final int DEFAULT_SIZE = 6;

    /**
     * Factor to resize by.
     */
    private static final int RESIZE_FACTOR = 2;

    /**
     * The stack's array
     */
    private Object[] array;

    /**
     * The size of the stack
     */
    private int size;

    /**
     * Create a new ArrayStack.
     */
    public ArrayStack() {
        this(DEFAULT_SIZE);
    }

    /**
     * Create a new ArrayStack with a specified initial size.
     * 
     * @param size Initial size.
     * @throws IllegalArgumentException If size is negative.
     */
    public ArrayStack(int size) throws IllegalArgumentException {
        // Throw exception if passed negative size
        if(size < 0) {
            throw new IllegalArgumentException("Illegal argument: Negative size.");
        }
        
        // Set up the stack
        array = new Object[size];
        size = 0;
    }
    

    /**
     * Return the size of the stack.
     * 
     * @return The size of the stack.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Push an item to the stack.
     * 
     * @param item Item to push.
     * @return {@code true} if successful.
     * @throws NullPointerException If item is {@code null}.
     */
    @Override
    public boolean add(T item) throws NullPointerException {
        // Throw exception if item is null.
        if(item == null) {
            throw new NullPointerException("Cannot push null to stack.");
        }

        // Push the item and return true
        push(item);
        return true;
    }

    /**
     * Push a collection of items to the stack. Will push them in
     * the order they appear in the collection's Iterator.
     * 
     * @return {@code true} if successful.
     * @throws NullPointerException If the collection or any items in it are {@code null}.
     */
    @Override
    public boolean addAll(Collection<? extends T> items) throws NullPointerException {
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
     * Return an Iterator over the elements in this ArrayStack.
     */
    @Override
    public Iterator<T> iterator() {
        return new ArrayStackIterator();
    }

    /**
     * Clear the stack.
     */
    @Override
    public void clear() {
        size = 0;
    }

    /**
     * Check if the ArrayStack is empty.
     * 
     * @return {@code true} if the stack is empty.
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Push to the stack.
     * 
     * @param item Item to add.
     * @throws NullPointerException if item is null.
     * @throws StackOverflowException if stack is full.
     */
    @Override
    public void push(T item) throws NullPointerException, StackOverflowException {
        // Throw exception if item is null.
        if(item == null) {
            throw new NullPointerException("Cannot push null.");
        }

        // Increase size if necessary
        if(size >= array.length) {
            increaseStackSize();
        }

        // Add the item to the stack
        array[size++] = item;
    }

    /**
     * Remove and return the top of the stack.
     * 
     * @return The top of the stack.
     * @throws StackUnderflowException if stack is empty.
     */
    @SuppressWarnings("unchecked")
    @Override
    public T pop() throws StackUnderflowException {
        // Throw exception if stack is empty
        if(size <= 0) {
            throw new StackUnderflowException("Cannot pop from empty stack.");
        }

        // Return top of stack and decrement size
        return (T) array[--size];
    }

    /**
     * Peek at the top of the stack.
     * 
     * @return The top of the stack.
     * @throws StackUnderflowException if stack is empty.
     */
    @SuppressWarnings("unchecked")
    @Override
    public T peek() throws StackUnderflowException {
        // Throw exception if empty
        if(isEmpty()) {
            throw new StackUnderflowException("Empty stack.");
        }

        // Return the top of the stack
        return (T) array[size - 1];
    }

    /**
     * Increase the size of the stack by the resize factor.
     */
    private void increaseStackSize() {
        // Create a new array
        Object[] newArray = new Object[RESIZE_FACTOR * array.length];

        // Copy the old array to the new one
        for(int i = 0; i < array.length; i++) {
            newArray[i] = array[i];
        }

        // Replace the array
        array = newArray;
    }

    /**
     * An Iterator over this ArrayStack.
     */
    private class ArrayStackIterator implements Iterator<T> {

        /**
         * The current index in the stack array.
         */
        private int index;

        /**
         * Create a new ArrayStackIterator object.
         */
        public ArrayStackIterator() {
            index = 0;
        }

        /**
         * Check if the stack has another item.
         * 
         * @return {@code true} if the stack has another item.
         */
        @Override
        public boolean hasNext() {
            return index < size;
        }

        /**
         * Get the next item in the stack.
         * 
         * @return The next item in the stack.
         * @throws NoSuchElementException If there is no next item.
         */
        @SuppressWarnings("unchecked")
        @Override
        public T next() throws NoSuchElementException {
            // Throw exception if out of elements
            if(!hasNext()) {
                throw new NoSuchElementException("No more elements.");
            }

            // Return next element and advance index
            return (T) array[index++];
        }

    }
    /**
     * Remove an item from the stack.
     * 
     * @param item Item to remove.
     * @return {@code true} if the item was successfully removed.
     * @throws NullPointerException If item is {@code null}.
     */
    @Override
    public boolean remove(T item) throws NullPointerException {
        // Throw exception if passed null
        if(item == null) {
            throw new NullPointerException("Cannot remove null.");
        }

        // Get the index of the item
        int index = index(item);

        // Return false if item not found
        if(index == -1) {
            return false;
        }

        // Remove by shift subsequent items back 1 index
        for(int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }

        // Decrement size
        size--;

        // Return true
        return true;
    }

    /**
     * Check if an item is in the stack.
     * 
     * @param item Item to check for.
     * @return {@code true} if the item is in the stack.
     * @throws NullPointerException If item is {@code null}.
     */
    @Override
    public boolean contains(T item) throws NullPointerException {
        // Throw exception if passed null
        if(item == null) {
            throw new NullPointerException("Cannot check for null.");
        }

        // Use index() to check if the item is present.
        return index(item) != -1;
    }

    /**
     * Look up the index of a given item in the array.
     * 
     * @param item Item to look up.
     * @return The item's index, or -1 if not found.
     */
    private int index(T item) {
        // Return index if found
        for(int i = 0; i < size; i++) {
            if(array[i].equals(item)) {
                return i;
            }
        }

        // Otherwise return -1
        return -1;
    }
}
