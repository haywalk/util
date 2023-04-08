package ca.haywalk.util.collection;

/**
 * An exception to be thrown in the event of a queue underflow.
 * 
 * @author Hayden Walker
 * @version 2023-04-07
 */
public class QueueUnderflowException extends RuntimeException {
    /**
     * Create a new QueueUnderflowException.
     * 
     * @param message Exception message.
     */
    public QueueUnderflowException(String message) {
        super(message);
    }
}