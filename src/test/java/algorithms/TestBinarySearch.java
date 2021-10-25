package algorithms;

import algorithms.searchs.BinarySearch;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestBinarySearch {

    @Test
    public void shouldMakeABinarySearchOfStringArray() {
        String[] stringArray = {"A", "B", "C", "D", "E", "F", "G", "K", "M", "N", "O", "P", "Q", "R", "S", "T", "U"};

        int index = BinarySearch.findIndex(stringArray, "K");

        assertEquals(index, 7);
    }

}