package algorithms.sort;

public class MergeSort {

    // Driver code
    public static void main(String[] args)
    {
//        int[] arr = { 12, 11, 13, 5, 6, 7 };
        int[] arr = { 38, 27, 43, 3, 9, 82, 10 };

        System.out.println("Given Array");
        printArray(arr);

        MergeSort mergeSort = new MergeSort();
        mergeSort.sort(arr, 0, arr.length - 1);

        System.out.println("\nSorted array");
        printArray(arr);
    }

    // Main function that sorts arr[l..r] using
    // merge()
    void sort(int[] arr, int leftIndex, int rightIndex)
    {
        if (leftIndex < rightIndex) {
            // Find the middle point
            int midIndex = leftIndex + (rightIndex - leftIndex) / 2;

            // Sort first and second halves
            sort(arr, leftIndex, midIndex);
            sort(arr, midIndex + 1, rightIndex);

            // Merge the sorted halves
            merge(arr, leftIndex, midIndex, rightIndex);
        }
    }

    // Merges two subarrays of arr[].
    // First subarray is arr[l..m]
    // Second subarray is arr[m+1..r]
    void merge(int[] mainArray, int leftIndex, int midIndex, int rightIndex)
    {
        // Find sizes of two sub-arrays to be merged
        int leftSubarraySize = midIndex - leftIndex + 1;
        int rightSubarraySize = rightIndex - midIndex;

        int[] leftSubarray = createSubarray(mainArray, leftIndex, leftSubarraySize);
        int[] rightSubarray = createSubarray(mainArray,  midIndex + 1, rightSubarraySize);

        sortArray(mainArray, leftIndex, leftSubarray, rightSubarray);

    }

    int[] createSubarray(int[] mainArray, int starIndex, int subArraySize) {

        int[] subarray = new int[subArraySize];

        for (int i = 0; i < subArraySize; ++i)
            subarray[i] = mainArray[starIndex + i];

        return subarray;
    }

    void sortArray(int[] mainArray, int indexMainArray, int[] leftSubarray, int[] rightSubarray) {
        int leftSubarraySize = leftSubarray.length;
        int rightSubarraySize = rightSubarray.length;

        int leftIndexSubarray = 0, rightIndexSubarray = 0;

        while (leftIndexSubarray < leftSubarraySize && rightIndexSubarray < rightSubarraySize) {

            int leftElement = leftSubarray[leftIndexSubarray];
            int rightElement = rightSubarray[rightIndexSubarray];

            if (leftElement <= rightElement) {
                mainArray[indexMainArray] = leftElement;
                leftIndexSubarray++;
            }
            else {
                mainArray[indexMainArray] = rightElement;
                rightIndexSubarray++;
            }
            indexMainArray++;
        }

        copyRemainingElements(mainArray, indexMainArray, leftSubarray, leftIndexSubarray);
        copyRemainingElements(mainArray, indexMainArray, rightSubarray, rightIndexSubarray);
    }

    void copyRemainingElements(int[] mainArray, int indexMainArray, int[] subArray, int indexSubarray) {
        int subarraySize = subArray.length;
        while (indexSubarray < subarraySize) {
            mainArray[indexMainArray] = subArray[indexSubarray];
            indexSubarray++;
            indexMainArray++;
        }
    }

    /* A utility function to print array of size n */
    static void printArray(int arr[])
    {
        int n = arr.length;
        for (int i = 0; i < n; ++i)
            System.out.print(arr[i] + " ");
        System.out.println();
    }

}
