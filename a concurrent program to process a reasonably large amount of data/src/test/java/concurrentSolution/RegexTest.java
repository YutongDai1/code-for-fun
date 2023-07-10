package concurrentSolution;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RegexTest {
    @Test
    void getRegex() {
        assertEquals("_", Regex.UNDERSCORE.getRegex());
    }

    @Test
    void values() {
        assertEquals(Regex.values().length, Regex.values().length);
    }

    @Test
    void valueOf() {
        assertEquals(Regex.UNDERSCORE, Regex.valueOf("UNDERSCORE"));
    }
}