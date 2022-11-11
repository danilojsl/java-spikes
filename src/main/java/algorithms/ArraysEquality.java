package algorithms;

import java.util.Arrays;

public class ArraysEquality {

    // Function to check if array B
// can be made equal to array A
    public static boolean canMadeEqual(int[] A,
                                       int[] B,
                                       int n)
    {

        // Sort both the arrays
        Arrays.sort(A);
        Arrays.sort(B);

        // Check if both the arrays
        // are equal or not
        for(int i = 0; i < n; i++)
        {
            if (A[i] != B[i])
            {
                return false;
            }
        }
        return true;
    }

    // Driver code
    public static void main(String[] args)
    {
        int A[] = { 11, 20, 7, 31, 82, 10, 4, 75, 63, 15 };
        int B[] = { 82, 11, 15, 7, 10, 75, 63, 4, 20, 31 };
        int n = A.length;

        if (canMadeEqual(A, B, n))
            System.out.print("Yes");
        else
            System.out.print("No");
    }
}