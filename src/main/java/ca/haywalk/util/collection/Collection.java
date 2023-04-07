package ca.haywalk.util.collection;

import java.util.Iterator;

/**
 * A generic abstract collection.
 * 
 * @author Hayden Walker
 * @version 2023-04-07
 */
public interface Collection<T> extends Iterable<T> {
    /**
     * Return the size of the Collection.
     * 
     * @return The size of the Collection.
     */
    public abstract int size();

    /**
     * Add an item to the Collection.
     * 
     * @param item Item to add to the Collection.
     * @return {@code true} if item was successfully added.
     * @throws NullPointerException If passed {@code null}.
     */
    public abstract boolean add(T item) throws NullPointerException;

    /**
     * Add more than one item to the Collection at once.
     * 
     * @param items Collection of items to add.
     * @return {@code true} if all items were successfully added.
     */
    public abstract boolean addAll(Collection<? extends T> items);

    /**
     * Return an iterator over the collection.
     * 
     * @return An iterator over the collection.
     */
    public abstract Iterator<T> iterator();

    /**
     * Clear the collection.
     */
    public abstract void clear();
}
