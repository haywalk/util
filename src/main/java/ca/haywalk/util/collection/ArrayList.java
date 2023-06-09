package ca.haywalk.util.collection;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * An array-based list.
 * 
 * @author Hayden Walker
 * @version 2023-04-07
 */
public class ArrayList<T> implements List<T> {

    /**
     * The default size of the list.
     */
    public static final int DEFAULT_SIZE = 6;

    /**
     * Factor to resize by.
     */
    private static final int RESIZE_FACTOR = 2;

    /**
     * The array of items in the list.
     */
    private Object[] array;

    /**
     * The size of the list
     */
    private int size;

    /**
     * Create a new ArrayList with the default initial size.
     */
    public ArrayList() {
        this(DEFAULT_SIZE);
    }

    /**
     * Create a new ArrayList with a given initial size.
     * 
     * @param initialSize Initial size.
     * @throws IllegalArgumentException If passed a negative size.
     */
    public ArrayList(int initialSize) throws IllegalArgumentException {
        // Verify that size is non-negative
        if(initialSize < 0) {
            throw new IllegalArgumentException("Illegal argument: Negative size.");
        }

        // Create the ArrayList
        array = new Object[initialSize];
        size = 0;
    }

    /**
     * Return the size of the ArrayList.
     * 
     * @return The size of the ArrayList.
     */
    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean add(T item) throws NullPointerException {
        // Check argument integrity
        if(item == null) {
            throw new NullPointerException("Cannot add null.");
        }
        
        // Increase size if necessary
        if(size >= array.length) {
            increaseSize();
        }

        // Add the item and increase size
        array[size++] = item;

        // Return true for success
        return true;
    }

    /**
     * Add all items in a collection to this list.
     * 
     * @param items Collection of items to add.
     * @return {@code true} if all items were successfully added.
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

        // Add all items from buffer to this list
        for(T item : toAdd) {
            this.add(item);
        }

        // Return true
        return true;
    }

    /**
     * Return an iterator over this ArrayList.
     * 
     * @return An iterator over this ArrayList.
     */
    @Override
    public Iterator<T> iterator() {
        return new ArrayListIterator();
    }

    @Override
    public void clear() {
        size = 0;
    }

    /**
     * Get the item in the ArrayList at a certain index.
     * 
     * @param index Index to get item at.
     * @return The item at that index.
     * @throws IndexOutOfBoundsException If index is out of bounds.
     */
    @SuppressWarnings("unchecked")
    @Override
    public T get(int index) throws IndexOutOfBoundsException {
        // Check if index is valid
        if(index >= size) {
            throw new IndexOutOfBoundsException(String.format("Index %d out of bounds for length %d.", index, size));
        }

        // Return the item at the index.
        return (T) array[index];
    }

    /**
     * Check if the list is empty.
     * 
     * @return {@code true} if the list is empty.
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }
    
    /**
     * Increase the size of the array by the resize factor.
     */
    private void increaseSize() {
        // Create a new, bigger array
        Object[] newArray = new Object[array.length * RESIZE_FACTOR];

        // Copy items to new array
        for(int i = 0; i < array.length; i++) {
            newArray[i] = array[i];
        }

        // Replace the old array
        array = newArray;
    }

    /**
     * An iterator over the list.
     */
    private class ArrayListIterator implements Iterator<T> {
        // Keep track of the current index
        private int index;

        /**
         * Create a new ArrayListIterator.
         */
        public ArrayListIterator() {
            index = 0;
        }

        /**
         * Check that the list has another item.
         * 
         * @return {@code true} If the list has another item.
         */
        @Override
        public boolean hasNext() {
            return index < size;
        }

        /**
         * Get the next item in the list.
         * 
         * @return The next item in the list.
         */
        @Override
        public T next() throws NoSuchElementException {
            // Throw exception if there is no next element.
            if(!hasNext()) {
                throw new NoSuchElementException("No such element: End of list.");
            }

            return get(index++);
        }
    }

    /**
     * Check if an item is in the list.
     * 
     * @param item Item to check for.
     * @return {@code true} if collection contains the item.
     * @throws NullPointerException if item is {@code null}.
     */
    @Override
    public boolean contains(T item) {
        // Throw exception if item is null.
        if(item == null) {
            throw new NullPointerException("Cannot check for null.");
        }

        // Check each index, return true if item is found.
        for(int i = 0; i < size; i++) {
            if(array[i].equals(item)) {
                return true;
            }
        }

        // Otherwise return false.
        return false;
    }

    /**
     * Check if an item is in the list.
     * 
     * @param item Item to check for.
     * @return {@code true} if collection contains the item.
     * @throws NullPointerException if item is {@code null}.
     */
    @Override
    public int index(T item) {
        // Throw exception if item is null.
        if(item == null) {
            throw new NullPointerException("Cannot find index of null.");
        }

        // Check each index, return the index if item is found.
        for(int i = 0; i < size; i++) {
            if(array[i].equals(item)) {
                return i;
            }
        }

        // Otherwise return -1.
        return -1;
    }

    /**
     * Remove the item at a certain index.
     * 
     * @param index Index of item to remove.
     * @return Item removed.
     * @throws IndexOutOfBoundsException If index is out of bounds.
     */
    @Override
    public T remove(int index) throws IndexOutOfBoundsException {
        // Throw exception for invalid index
        if(index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(String.format("Index %d out of bounds for size %d.", index, size));
        }

        // Get the item at the index
        T removed = get(index);

        // Shift remaining elements back
        for(int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }

        // Decrement size and return removed item.
        size--;
        return removed;      
    }

    /**
     * Remove an item from the list. Removes first occurrence.
     * 
     * @param item Item to remove.
     * @return {@code true} if item was successfully removed.
     * @throws NullPointerException if item is {@code null}.
     */
    public boolean remove(T item) throws NullPointerException {
        // Throw exception if passed null.
        if(item == null) {
            throw new NullPointerException("Cannot remove null.");
        }

        // Look up the index of the item.
        int index = index(item);

        // Return false if not found.
        if(index == -1) {
            return false;
        }

        // Otherwise remove the item at the index and return true
        remove(index);
        return true;
    }
}
