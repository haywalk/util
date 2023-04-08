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
    public abstract T get(int index) throws IndexOutOfBoundsException;
}
