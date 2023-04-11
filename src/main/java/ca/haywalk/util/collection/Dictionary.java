package ca.haywalk.util.collection;

/**
 * A generic dictionary.
 * 
 * @author Hayden Walker
 * @version 2023-04-10
 */
public interface Dictionary<K, V> extends Iterable<K> {
    /**
     * Add a key-value pair to the dictionary.
     * 
     * @param key Key to add or overwrite.
     * @param value Value to associate to the key.
     * @return {@code true} if successful.
     * @throws NullPointerException If key or value are {@code null}.
     */
    public boolean put(K key, V value) throws NullPointerException;

    /**
     * Get the value associated with a key.
     * 
     * @param key Key.
     * @return Value associated with the key.
     * @throws NullPointerException If key is {@code null}.
     */
    public V get(K key) throws NullPointerException;

    /**
     * Clear the dictionary.
     */
    public void clear();

    /**
     * Return a collection of the keys in the dictionary.
     * 
     * @return A collection of the keys in the dictionary.
     */
    public Collection<K> keys();

    /**
     * Return a collection of the values in the dictionary.
     * 
     * @return A collection of the values in the dictionary.
     */
    public Collection<V> values();

    /**
     * Remove a key-value pair from the dictionary.
     * 
     * @param key Key to remove.
     * @return {@code true} if successful.
     * @throws NullPointerException If key is {@code null}.
     */
    public boolean remove(K key) throws NullPointerException;
}
