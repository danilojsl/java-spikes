package dynamic.programming;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestFibonacci {

    @Test
    public void shouldComputeFibonacci() {
       int result = Fibonacci.computeFibonacci(10);

        assertEquals(result, 55);
    }

}