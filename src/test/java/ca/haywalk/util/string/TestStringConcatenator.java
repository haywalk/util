package ca.haywalk.util.string;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

//import ca.haywalk.util.string.StringConcatenator;

/**
 * Tests for {@link ca.haywalk.util.string.StringConcatenator}.
 * 
 * @author Hayden Walker
 * @version 2023-04-05
 */
public class TestStringConcatenator {
    // Store a StringConcatenator object to test with
    private StringConcatenator builder;

    /**
     * Reset the StringConcatenator
     */
    @BeforeEach
    public void setUpBuilder() {
        builder = new StringConcatenator();
    }
    
    /**
     * Test that an empty StringConcatenator yields an
     * empty String.
     */
    @Test
    public void testEmpty() {
        assertEquals("", builder.build());
    }

    /**
     * Test adding a String.
     */
    @Test
    public void testOneString() {
        String stringIn = "Hello, World!";
        String stringOut = builder.concatenate(stringIn).build();
        assertEquals(stringIn, stringOut);
    }

    /**
     * Test concatenating multiple Strings.
     */
    @Test
    public void testChaining() {
        // Start with some input strings
        String stringA = "Hello";
        String stringB = ", ";
        String stringC = "World";
        String stringD = "!";

        // Build the output string
        String stringOut = builder.concatenate(stringA)
            .concatenate(stringB)
            .concatenate(stringC)
            .concatenate(stringD)
            .build();

        // Check if it concatenated correctly
        assertEquals(stringA + stringB + stringC + stringD, stringOut);
    }

    /**
     * Test flushing the buffer.
     */
    @Test
    public void testFlush() {
        // Build a String
        String stringIn = builder.concatenate("Hello")
            .concatenate(", ")
            .concatenate("World!")
            .build();

        // Make sure flush() returns the built string
        assertEquals(stringIn, builder.flush());

        // Assert that the buffer is now empty
        assertEquals("", builder.build()); 
    }
}