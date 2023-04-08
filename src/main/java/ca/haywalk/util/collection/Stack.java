package ca.haywalk.util.collection;

/**
 * A generic stack.
 * 
 * @author Hayden Walker
 * @version 2023-04-07
 */
public interface Stack<T> extends Collection<T> {
    /**
     * Push to the stack.
     * 
     * @param item Item to add.
     * @throws NullPointerException if item is null.
     * @throws StackOverflowException if stack is full.
     */
    public void push(T item) throws NullPointerException, StackOverflowException;

    /**
     * Remove and return the top of the stack.
     * 
     * @return The top of the stack.
     * @throws StackUnderflowException if stack is empty.
     */
    public T pop() throws StackUnderflowException;

    /**
     * Peek at the top of the stack.
     * 
     * @return The top of the stack.
     * @throws StackUnderflowException if stack is empty.
     */
    public T peek() throws StackUnderflowException;
}
