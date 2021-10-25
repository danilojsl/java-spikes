package problems;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class ProblemsTest {

    @Test
    public void shouldRemoveLoopInLinkedList() {

        Node node1 = new Node();
        Node node3 = new Node();
        Node node4 = new Node();

        node1.data = 1;
        node1.next = node3;
        node3.data = 3;
        node3.next = node4;
        node4.data = 4;
        node4.next = node1;

        Problems.removeLoop(node1);

    }

    @Test
    public void shouldPrintNumbers() {
        Problems.printFizzBuzz();
    }

    @Test
    public void anyTest() {
        int sum = 5/2;
        System.out.println("Done");

//        HashMap<Integer, Integer> lookupTable = new HashMap<>();
//        Integer.valueOf()
        
    }

    public String isSubset( long a1[], long a2[], long n, long m) {

        HashMap<Long, Integer> lookupTable = new HashMap<>();

        for (int i=0; i < a1.length; i++) {
            long element = Long.valueOf(a1[i]);
            if (lookupTable.get(element) == null) {
                lookupTable.put(element, i);
            }
        }

        for (int i=0; i < a2.length; i++) {
            long element = Long.valueOf(a2[i]);
            if (lookupTable.get(element) == null) {
                return "No";
            }
        }

        return "Yes";
    }

    public String isSubsetV2( long a1[], long a2[], long n, long m) {

        HashSet<Long> hset = new HashSet<>();

        // hset stores all the values of arr1
        for (int i = 0; i < m; i++) {
            long key = a1[i];
            hset.add(key);
        }

        // loop to check if all elements
        //  of arr2 also lies in arr1
        for (int i = 0; i < n; i++)
        {
            long key = a2[i];
            if (!hset.contains(key))
                return "No";
        }

        return "Yes";
    }

}