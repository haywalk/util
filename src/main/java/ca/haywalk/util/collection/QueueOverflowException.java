package ca.haywalk.util.collection;

/**
 * An exception to be thrown in the event of a queue overflow.
 * 
 * @author Hayden Walker
 * @version 2023-04-07
 */
public class QueueOverflowException extends RuntimeException {
    /**
     * Create a new QueueOverflowException.
     * 
     * @param message Exception message.
     */
    public QueueOverflowException(String message) {
        super(message);
    }
}