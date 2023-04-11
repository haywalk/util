package ca.haywalk.util.collection;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the ArrayMap class.
 */
public class TestArrayMap {
    // A dictionary to test with
    private ArrayMap<String, String> dict;

    /**
     * Set up the test dictionary.
     */
    @BeforeEach
    public void setup() {
        dict = new ArrayMap<String, String>();
    }

    /**
     * Test adding and looking up keys.
     */
    @Test
    public void testAddAndLookup() {
        dict.put("Bob", "Burger");
        dict.put("Bill", "Fries");
        assertEquals("Burger", dict.get("Bob"));
        assertEquals("Fries", dict.get("Bill"));
    }

    /**
     * Test removing keys.
     */
    @Test
    public void testRemove() {
        // Add some pairs and test
        dict.put("Bob", "Burger");
        dict.put("Bill", "Fries");
        assertEquals("Burger", dict.get("Bob"));
        assertEquals("Fries", dict.get("Bill"));

        // Remove one and test
        assertTrue(dict.remove("Bob"));
        assertEquals(null, dict.get("Bob"));
        assertEquals("Fries", dict.get("Bill"));

        // Remove the other one and test
        assertFalse(dict.remove("Bob"));
        assertTrue(dict.remove("Bill"));
        assertEquals(null, dict.get("Bob"));
        assertEquals(null, dict.get("Bill"));
    }

    /**
     * Test clearing the dictionary.
     */
    @Test
    public void testClear() {
        // Add some pairs and test
        dict.put("Bob", "Burger");
        dict.put("Bill", "Fries");
        assertEquals("Burger", dict.get("Bob"));
        assertEquals("Fries", dict.get("Bill"));

        // Clear the dictionary and make sure keys are gone
        dict.clear();
        assertEquals(null, dict.get("Bob"));
        assertEquals(null, dict.get("Bill"));
    }
}
