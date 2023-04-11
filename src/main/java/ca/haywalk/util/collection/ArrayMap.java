package ca.haywalk.util.collection;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A slow but simple implementation of the Dictionary interface.
 * 
 * @author Hayden Walker
 * @version 2023-04-10
 */
public class ArrayMap<K, V> implements Dictionary<K, V> {

    /**
     * Default initial array size.
     */
    public static final int DEFAULT_SIZE = 6;

    /**
     * Factor to resize by.
     */
    private static final int RESIZE_FACTOR = 2;

    /**
     * Size of the dictionary.
     */
    private int size;

    /**
     * Key array.
     */
    private Object[] keys;

    /**
     * Value array.
     */
    private Object[] values;
    

    /**
     * Create a new ArrayMap.
     */
    public ArrayMap() {
        this(DEFAULT_SIZE);
    }

    /**
     * Create an ArrayMap with a given initial size.
     * 
     * @param size Initial array size.
     * @throws IllegalArgumentException if size is negative.
     */
    public ArrayMap(int size) throws IllegalArgumentException {
        this.size = 0;
        keys = new Object[size];
        values = new Object[size];
    }

    @Override
    public boolean put(K key, V value) throws NullPointerException {
        // Look up the key's index
        int keyIndex = indexOfKey(key);

        // If the key is in the dictionary already, store the value with it
        if(keyIndex != -1) {
            values[keyIndex] = value;
            return true;
        }

        // Increase size if neccessary
        if(size >= keys.length) {
            increaseArraySize();
        }

        // Store the new pair
        keys[size] = key;
        values[size] = value;
        size++;

        // Return true
        return true;
    }

    /**
     * Return the value associated to a key.
     * 
     * @param key Key to get value associated with.
     * @return Value associated with the key, or {@code null} if no such value exists.
     * @throws NullPointerException If key is {@code null}
     */
    @SuppressWarnings("unchecked")
    @Override
    public V get(K key) {
        // Attempt to find the index of the key
        int index = indexOfKey(key);

        // If found, return the value
        if(index != -1) {
            return (V) values[index];
        }

        // Otherwise return null
        return null;
    }

    @Override
    public void clear() {
        size = 0;
    }

    /**
     * Return a collection of the keys in the dictionary.
     * 
     * @return A collection of the keys in the dictionary.
     */
    @SuppressWarnings("unchecked")
    @Override
    public Collection<K> keys() {
        // Make a new list
        ArrayList<K> keyList = new ArrayList<K>();
        
        // Add keys to the list
        for(int i = 0; i < size; i++) {
            keyList.add((K) keys[i]);
        }

        // Return the list
        return keyList;
    }

    /**
     * Return a collection of the values in the dictionary.
     * 
     * @return A collection of the values in the dictionary.
     */
    @SuppressWarnings("unchecked")
    @Override
    public Collection<V> values() {
        // Make a new list
        ArrayList<V> keyList = new ArrayList<V>();
        
        // Add keys to the list
        for(int i = 0; i < size; i++) {
            keyList.add((V) values[i]);
        }

        // Return the list
        return keyList;
    }

    /**
     * Remove a key-value pair from the dictionary.
     * 
     * @param key Key to remove.
     * @return {@code true} if successful.
     * @throws NullPointerException If key is {@code null}.
     */
    @Override
    public boolean remove(K key) {
        // Look up the key's index
        int keyIndex = indexOfKey(key);

        // Return false if not found
        if(keyIndex == -1) {
            return false;
        }

        // Shift remaining elements back an index
        for(int i = keyIndex; i < size - 1; i++) {
            keys[i] = keys[i + 1];
            values[i] = values[i + 1];
        }

        // Decrement size and return true
        size--;
        return true;
    }

    /**
     * Return an Iterator over the dictionary's keys.
     * 
     * @return An Iterator over the dictionary's keys.
     */
    @Override
    public Iterator<K> iterator() {
        return new ArrayMapIterator();    
    }

    /**
     * Given a key, find its index in the key array.
     * 
     * @param key Key to find.
     * @return The key's index, or -1 if not found.
     */
    @SuppressWarnings("unchecked")
    private int indexOfKey(K key) {
        // Find the index of the key
        for(int i = 0; i < size; i++) {
            if(((K) keys[i]).equals(key)) {
                return i;
            }
        }

        // Return -1 if not found
        return -1;
    }

    /**
     * Increase the array size by the resize factor.
     */
    private void increaseArraySize() {
        // Create new arrays
        Object[] newKeyArray = new Object[RESIZE_FACTOR * keys.length];
        Object[] newValueArray = new Object[RESIZE_FACTOR * values.length];

        // Copy the old arrays into the new ones
        for(int i = 0; i < keys.length; i++) {
            newKeyArray[i] = keys[i];
            newValueArray[i] = values[i];
        }

        // Replace the old arrays
        keys = newKeyArray;
        values = newValueArray;
    }
    
    /**
     * An Iterator over the dictionary's keys.
     */
    private class ArrayMapIterator implements Iterator<K> {

        /**
         * The current index in the key array.
         */
        private int index;

        /**
         * Create a new ArrayMapIterator
         */
        public ArrayMapIterator() {
            index = 0;
        }

        /**
         * Check if the Iterator has a next element.
         * 
         * @return {@code true} if there is a next element.
         */
        @Override
        public boolean hasNext() {
            return index < size;
        }

        /**
         * Get the next key.
         * 
         * @return The next key.
         * @throws NoSuchElementException If there is no next key.
         */
        @SuppressWarnings("unchecked")
        @Override
        public K next() {
            // Throw exception if there's no next key
            if(!hasNext()) {
                throw new NoSuchElementException("No next key.");
            }

            // Return the key and increment the index
            return (K) keys[index++];
        }
        
    }
}
