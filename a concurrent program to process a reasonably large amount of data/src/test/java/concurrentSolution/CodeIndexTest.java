package concurrentSolution;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CodeIndexTest {


    @Test
    void getIndex() {
        assertEquals(1, CodeIndex.CODE_PRESENTATION_INDEX.getIndex());
    }

    @Test
    void values() {
        assertEquals(7, CodeIndex.values().length);
    }

    @Test
    void valueOf() {
        assertEquals(CodeIndex.CODE_PRESENTATION_INDEX, CodeIndex.valueOf("CODE_PRESENTATION_INDEX"));
    }
}