package dynamic.programming;

public class Fibonacci {
    //https://en.wikipedia.org/wiki/Fibonacci_number
    public static int computeFibonacci(int number) {
        int[] fibonacci = new int[number + 1];

        fibonacci[0] = 0;
        fibonacci[1] = 1;

        for (int i = 2; i <= number; i++) {
            fibonacci[i] = fibonacci[i - 1] + fibonacci[i - 2];
        }

        return fibonacci[number];

    }

}
