package ca.haywalk.util.collection;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

/**
 * Tests for ArrayStack.
 * 
 * @author Hayden Walker
 * @version 2023-04-07
 */
public class TestArrayStack {
    
    // A stack to test with
    private ArrayStack<Integer> stack;

    /**
     * Set up the stack before each test.
     */
    @BeforeEach
    public void setup() {
        stack = new ArrayStack<Integer>();
    }

    /**
     * Test that the size() method works correctly.
     */
    @Test
    public void testSize() {
        assertEquals(0, stack.size());
        stack.push(1);
        assertEquals(1, stack.size());
    }

    /**
     * Test adding and removing items.
     */
    @Test
    public void testPushPop() {
        // Push items
        for(int i = 0; i < 25; i++) {
            stack.push(i);
        }

        // Pop items, make sure they're correct
        for(int i = 24; i >= 0; i--) {
            assertEquals(i, stack.pop());
        }
    }
}
