package algorithms.searchs;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BinarySearchImpTest {

    @Test
    public void shouldFindElement() {
        int[] elements = {2, 5, 8, 12, 16, 23, 38, 56, 72, 91};

        int actualPosition = BinarySearchImp.searchInSorted(elements, elements.length - 1, 23);

        assertEquals(actualPosition, 5);
    }

}