package ca.haywalk.util.collection;

/**
 * An exception to be thrown in the event of a stack underflow.
 * 
 * @author Hayden Walker
 * @version 2023-04-07
 */
public class StackUnderflowException extends RuntimeException {
    /**
     * Create a new StackUnderflowException.
     * 
     * @param message Exception message.
     */
    public StackUnderflowException(String message) {
        super(message);
    }
}
