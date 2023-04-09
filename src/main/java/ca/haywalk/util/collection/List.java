package ca.haywalk.util.collection;

/**
 * A generic list.
 * 
 * @author Hayden Walker
 * @version 2023-04-07
 */
public interface List<T> extends Collection<T> {
    /**
     * Return the item at a given index in the list.
     * 
     * @param index Index to get item at.
     * @return Item at that index.
     * @throws IndexOutOfBoundsException If index is out of bounds.
     */
    public T get(int index) throws IndexOutOfBoundsException;

    /**
     * Remove the item at a certain index.
     * 
     * @param index Index of item to remove.
     * @return Item removed.
     * @throws IndexOutOfBoundsException If index is out of bounds.
     */
    public T remove(int index) throws IndexOutOfBoundsException;

    /**
     * Get the index of an item.
     * 
     * @param item Item to get the index of.
     * @return Item's index, or -1 if not found.
     * @throws NullPointerException if item is {@code null}.
     */
    public int index(T item) throws NullPointerException;
}
