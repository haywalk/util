package ca.haywalk.util.collection;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;

import org.junit.jupiter.api.*;

public class TestArrayList {
    // List to test with.
    private ArrayList<Integer> list;

    /**
     * Set up the test list.
     */
    @BeforeEach
    public void setUp() {
        list = new ArrayList<Integer>();
    }
    
    /**
     * Test the size() method.
     */
    @Test
    public void testSize() {
        assertEquals(0, list.size());
        list.add(42);
        assertEquals(1, list.size());
    }

    /**
     * Test the get() method.
     */
    @Test
    public void testGet() {
        // Add some items to the list
        list.add(0);
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);

        // Get each item
        for(int i = 0; i < list.size(); i++) {
            assertEquals(i, list.get(i));
        }
    }

    /**
     * Test the addAll() method.
     */
    @Test
    public void testAddAll() {
        // Create a second list
        ArrayList<Integer> listB = new ArrayList<Integer>();

        // Add some items to it
        listB.add(123);
        listB.add(456);
        listB.add(789);

        // Add listB to list
        list.addAll(listB);

        // Check that they have the same size
        assertEquals(listB.size(), list.size());

        // Check that they're the same
        for(int i = 0; i < list.size(); i++) {
            assertEquals(listB.get(i), list.get(i));
        }
    }

    /**
     * Test the list iterator.
     */
    @Test
    public void testIterator() {
        // Add items to the list
        for(int i = 0; i < 10; i++) {
            list.add(i);
        }

        // Get an iterator
        Iterator<Integer> iterator = list.iterator();

        // Make sure the iterator can traverse the list
        for(int i = 0; i < 10; i++) {
            assertTrue(iterator.hasNext());
            iterator.next();
        }
    }

    /**
     * Test the clear() method.
     */
    @Test
    public void testClear() {
        list.add(1);
        list.clear();
        assertEquals(0, list.size());
    }

    /**
     * Test the contains() method
     */
    @Test
    public void testContains() {
        assertFalse(list.contains(1));
        list.add(1);
        assertTrue(list.contains(1));
    }

    /**
     * Test the remove(object) method
     */
    @Test
    public void testRemoveObject() {
        // Add an item
        list.add(1);
        assertEquals(1, list.size());
        assertTrue(list.contains(1));

        // Remove the item
        Integer number = 1;
        assertEquals(true, list.remove(number));
        assertEquals(0, list.size());
        assertFalse(list.contains(1));
    }

    /**
     * Test the remove(index) method
     */
    @Test
    public void testRemoveIndex() {
        // Add an item
        list.add(1);
        assertEquals(1, list.size());
        assertTrue(list.contains(1));

        // Remove the item
        assertEquals(1, list.remove(0));
        assertEquals(0, list.size());
        assertFalse(list.contains(1));
    }
}
