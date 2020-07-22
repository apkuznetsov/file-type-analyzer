package analyzer.algs;

public class MergeSort {

    public static void sortByMerge(int[] array, int leftIncl, int rightExcl) {
        // the base case: if subarray contains <= 1 items, stop dividing because it's sorted
        final boolean stopDividing = rightExcl <= leftIncl + 1;
        if (stopDividing) {
            return;
        }

        // divide: calculate the index of the middle element
        final int middle = leftIncl + (rightExcl - leftIncl) / 2;

        sortByMerge(array, leftIncl, middle);  // conquer: sort the left subarray
        sortByMerge(array, middle, rightExcl); // conquer: sort the right subarray

        // combine: merge both sorted subarrays into sorted one
        merge(array, leftIncl, middle, rightExcl);
    }

    private static void merge(int[] array, int left, int middle, int right) {
        int leftSubarrIndex = left;         // index for the left subarray
        int rightSubarrIndex = middle;      // index for the right subarray

        int tempSubarrIndex = 0;            // index for the temp subarray
        int[] temp = new int[right - left]; // temporary array for merging

        /* get the next lesser element from one of two subarrays
         * and then insert it in the array until one of the subarrays is empty */
        while (leftSubarrIndex < middle
                && rightSubarrIndex < right) {

            if (array[leftSubarrIndex] <= array[rightSubarrIndex]) {
                temp[tempSubarrIndex] = array[leftSubarrIndex];
                leftSubarrIndex++;
            } else {
                temp[tempSubarrIndex] = array[rightSubarrIndex];
                rightSubarrIndex++;
            }

            tempSubarrIndex++;
        }

        // insert all the remaining elements of the left subarray in the array
        for (; leftSubarrIndex < middle; leftSubarrIndex++, tempSubarrIndex++) {
            temp[tempSubarrIndex] = array[leftSubarrIndex];
        }

        // insert all the remaining elements of the right subarray in the array
        for (; rightSubarrIndex < right; rightSubarrIndex++, tempSubarrIndex++) {
            temp[tempSubarrIndex] = array[rightSubarrIndex];
        }

        // effective copying elements from temp to array
        System.arraycopy(temp, 0, array, left, temp.length);
    }
}