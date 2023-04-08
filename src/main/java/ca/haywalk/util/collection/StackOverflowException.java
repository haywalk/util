package ca.haywalk.util.collection;

/**
 * An exception to be thrown in the event of a stack overflow.
 * 
 * @author Hayden Walker
 * @version 2023-04-07
 */
public class StackOverflowException extends RuntimeException {
    /**
     * Create a new StackOverflowException.
     * 
     * @param message Exception message.
     */
    public StackOverflowException(String message) {
        super(message);
    }
}