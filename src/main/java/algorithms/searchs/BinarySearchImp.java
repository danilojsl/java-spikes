package algorithms.searchs;

public class BinarySearchImp {

    static int searchInSorted(int arr[], int N, int K)
    {

        int result = binarySearch(arr, 0, N, K);
        return result;
//        if (result > -1) {
//            return 1;
//        } else return result;
    }

    private static int binarySearch(int array[], int lowerBoundIndex, int upperBoundIndex, int element) {

        if (upperBoundIndex >= lowerBoundIndex) {

            int middleIndex = lowerBoundIndex + (upperBoundIndex - lowerBoundIndex) / 2; //Left position moves on each iteration

            if (array[middleIndex] == element) {
                return middleIndex;
            }

            if (array[middleIndex] > element) {
                return binarySearch(array, lowerBoundIndex, middleIndex - 1, element); //Left subarray
            } else {
                return binarySearch(array, middleIndex + 1, upperBoundIndex, element); //Right subarray
            }

        }

        return -1;

    }

}
