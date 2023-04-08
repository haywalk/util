package ca.haywalk.util.collection;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

/**
 * Tests for the LinkedQueue class.
 * 
 * @author Hayden Walker
 * @version 2023-04-08
 */
public class TestLinkedQueue {
    
    // A queue to test with
    private LinkedQueue<Integer> queue;

    /**
     * Set up the test queue.
     */
    @BeforeEach
    public void setup() {
        queue = new LinkedQueue<Integer>();
    }

    /**
     * Test the size() method.
     */
    @Test
    public void testSize() {
        // Assert size 0 when empty
        assertEquals(0, queue.size());

        // Add items and make sure size is right
        for(int i = 1; i <= 10; i++) {
            queue.enqueue(i);
            assertEquals(i, queue.size());
        }

        // Remove items and make sure size is right
        for(int i = 9; i >= 0; i--) {
            queue.dequeue();
            assertEquals(i, queue.size());
        }
    }

    /**
     * Test enqueueing and dequeueing
     */
    @Test
    public void testEnqueueDequeue() {
        // Add items
        for(int i = 1; i <= 10; i++) {
            queue.enqueue(i);
        }

        // Remove items and check order
        for(int i = 1; i <= 10; i++) {
            assertEquals(i, queue.dequeue());
        }
    }
}
