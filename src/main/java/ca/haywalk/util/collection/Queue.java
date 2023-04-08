package ca.haywalk.util.collection;

/**
 * A generic queue.
 * 
 * @author Hayden Walker
 * @version 2023-04-07
 */
public interface Queue<T> extends Collection<T> {
    
    /**
     * Add an item to the back of the queue.
     * 
     * @param item Item to add to the queue.
     * @throws NullPointerException If item is {@code null}.
     * @throws QueueOverflowException If queue is full.
     */
    public void enqueue(T item) throws NullPointerException, QueueOverflowException;

    /**
     * Remove the item at the front of the queue.
     * 
     * @return The item at the front of the queue.
     * @throws QueueUnderflowException if queue is empty.
     */
    public T dequeue() throws QueueUnderflowException;
}
