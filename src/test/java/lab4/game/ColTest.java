package lab4.game;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ColTest {
    @Test
    public void leftInputsWork() {
        //Test valid Left inputs
        assertEquals(Col.Left, Col.from("1"));
        assertEquals(Col.Left, Col.from("l"));
        assertEquals(Col.Left, Col.from("L"));
    }

    @Test
    public void MiddleInputsWork() {
        //Test valid Middle inputs
        assertEquals(Col.Middle, Col.from("2"));
        assertEquals(Col.Middle, Col.from("m"));
        assertEquals(Col.Middle, Col.from("M"));
        assertEquals(Col.Middle, Col.from("c"));
        assertEquals(Col.Middle, Col.from("C"));
    }

    @Test
    public void RightInputsWork() {
        //Test valid Middle inputs
        assertEquals(Col.Right, Col.from("3"));
        assertEquals(Col.Right, Col.from("r"));
        assertEquals(Col.Right, Col.from("R"));
    }

    @Test
    public void invalidInputFails() {
        // Test various invalid inputs
        String[] invalidInputs = {"", " ", "0", "4", "5", "B", "a", "z", "middle", "left", "right", "Right", "LEFT"};
        for (String invalidInput : invalidInputs) {
            assertThrows(IllegalArgumentException.class, () -> Col.from(invalidInput), "Input failed: " + invalidInput);
        }
    }

    @Test
    public void extraSpacesInInputFail() {
        // Inputs with extra whitespace cause an exception ( can fix it if we add trim)
        String[] spaceInputs = {"l  ", " R", "1 ", " 2 "};
        for (String spaceInput : spaceInputs) {
            assertThrows(IllegalArgumentException.class, () -> Col.from(spaceInput), "Input failed: " + spaceInput);
        }
    }
}
