package algorithms.searchs;

import java.util.Arrays;

public class BinarySearch {

    public static int findIndex(String[] array, String element) {
        int index = Arrays.binarySearch(array, element);
        return (index < 0) ? -1 : index;
    }

}
