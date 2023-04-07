package ca.haywalk.util.string;

/**
 * Allows high-performance {@link java.lang.String} concatenation.
 * 
 * @author Hayden Walker
 * @version 2023-04-05
 */
public class StringConcatenator {
    /**
     * The default initial buffer size.
     */
    public static final int DEFAULT_INITIAL_BUFFER_SIZE = 6;
    
    /**
     * The factor to resize by.
     */
    private static final int RESIZE_FACTOR = 2;

    /**
     * The character buffer.
     */
    private char[] buffer;

    /**
     * The size of the String being built.
     */
    private int size;

    /**
     * Create a new StringConcatenator.
     * 
     * Creates a StringConcatenator with the default initial buffer size.
     */
    public StringConcatenator() {
        this(DEFAULT_INITIAL_BUFFER_SIZE);
    }

    /**
     * Create a new StringConcatenator with a given initial size.
     * 
     * @param bufferSize Initial buffer size.
     * @throws IllegalArgumentException If given negative size.
     */
    public StringConcatenator(int bufferSize) throws IllegalArgumentException {
        // Throw exception if size is negative
        if(bufferSize < 0) {
            throw new IllegalArgumentException("Illegal argument: Negative size.");
        }

        // Create the buffer
        buffer = new char[bufferSize];

        // Set size to 0
        size = 0;
    }

    /**
     * Add a {@link java.lang.String} to the StringConcatenator.
     * 
     * @param string String to concatenate.
     * @return The StringConcatenator.
     */
    public StringConcatenator concatenate(String string) {
        // Add each character to the buffer
        for(char character : string.toCharArray()) {
            // Increase size if necessary            
            if(size >= buffer.length) {
                increaseBufferSize();
            }
            
            // Store character and increase size
            buffer[size++] = character;
        }

        // Return this StringConcatenator
        return this;
    }

    /**
     * Return the size of this StringConcatenator.
     * 
     * @return The size of this StringConcatenator.
     */
    public int size() {
        return size;
    }

    /**
     * Build the String.
     * 
     * @return A {@link java.lang.String} object made up of all characters added.
     */
    public String build() {
        return new String(buffer, 0, size);
    }

    /**
     * Return the built String.
     * 
     * @return A {@link java.lang.String} object made up of all characters added.
     */
    @Override
    public String toString() {
        return build();
    }

    /**
     * Flush the buffer contents.
     * 
     * Clear the buffer and return its contents.
     * 
     * @return Buffer contents.
     */
    public String flush() {
        String contents = build();
        size = 0;
        return contents;
    }

    /**
     * Increase the buffer size by the resize factor.
     */
    private void increaseBufferSize() {
        // Create a new buffer
        char[] newBuffer = new char[RESIZE_FACTOR * buffer.length];
        
        // Copy contents of old buffer to new buffer
        for(int i = 0; i < buffer.length; i++) {
            newBuffer[i] = buffer[i];
        }

        // Make the new buffer the StringConcatenator's buffer
        buffer = newBuffer;
    }
}