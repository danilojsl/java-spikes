package problems;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RemovingStringsTest {

    @Test
    public void shouldRemoveStrings() {
        String result = RemovingStrings.removeChars("computer", "cat");

        assertEquals(result, "ompuer");
    }

}