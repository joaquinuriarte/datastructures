import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

/**
 * Your implementation of various sorting algorithms.
 *
 * @author Joaquin Uriarte
 * @version 1.0
 * @userid juriarte3
 * @GTID 903485636
 *
 * Collaborators: LIST ALL COLLABORATORS YOU WORKED WITH HERE
 *
 * Resources: LIST ALL NON-COURSE RESOURCES YOU CONSULTED HERE
 */
public class Sorting {

    /**
     * Implement selection sort.
     *
     * It should be:
     * in-place
     * unstable
     * not adaptive
     *
     * Have a worst case running time of:
     * O(n^2)
     *
     * And a best case running time of:
     * O(n^2)
     *
     * @param <T>        data type to sort
     * @param arr        the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     * @throws java.lang.IllegalArgumentException if the array or comparator is
     *                                            null
     */
    public static <T> void selectionSort(T[] arr, Comparator<T> comparator) {
        if (arr == null) {
            throw new IllegalArgumentException("Error. Array can't be null.");
        } else if (comparator == null) {
            throw new IllegalArgumentException("Error. Comparator can't be null.");
        } else {
            for (int i = 0; i < arr.length; i++) {
                int maxVal = i;
                for (int y = i + 1; y < arr.length; y++) {
                    if (comparator.compare(arr[y], arr[maxVal]) < 0) {
                        maxVal = y;
                    }
                }
                swap(arr, maxVal, i);
            }
        }
    }


    /**
     * Implement cocktail sort.
     *
     * It should be:
     * in-place
     * stable
     * adaptive
     *
     * Have a worst case running time of:
     * O(n^2)
     *
     * And a best case running time of:
     * O(n)
     *
     * NOTE: See pdf for last swapped optimization for cocktail sort. You
     * MUST implement cocktail sort with this optimization
     *
     * @param <T>        data type to sort
     * @param arr        the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     * @throws java.lang.IllegalArgumentException if the array or comparator is
     *                                            null
     */
    public static <T> void cocktailSort(T[] arr, Comparator<T> comparator) {
        if (arr == null) {
            throw new IllegalArgumentException("Error. Array can't be null.");
        } else if (comparator == null) {
            throw new IllegalArgumentException("Error. Comparator can't be null.");
        } else {
            boolean swapsMade = true;
            int start = 0;
            int end = arr.length - 1;
            int tempstart = start;
            int tempend = end;
            while (swapsMade) {
                swapsMade = false;
                for (int s = start; s < end; s++) {
                    if (comparator.compare(arr[s], arr[s + 1]) > 0) {
                        swap(arr, s, s + 1);
                        swapsMade = true;
                        tempend = s;
                    }
                }
                end = tempend;
                if (swapsMade) {
                    swapsMade = false;
                    for (int s = end; s > start; s--) {
                        if (comparator.compare(arr[s], arr[s - 1]) < 0) {
                            swap(arr, s, s - 1);
                            swapsMade = true;
                            tempstart = s;
                        }
                    }
                    start = tempstart;
                }
            }
        }
    }

    /**
     * Implement merge sort.
     *
     * It should be:
     * out-of-place
     * stable
     * not adaptive
     *
     * Have a worst case running time of:
     * O(n log n)
     *
     * And a best case running time of:
     * O(n log n)
     *
     * You can create more arrays to run merge sort, but at the end, everything
     * should be merged back into the original T[] which was passed in.
     *
     * When splitting the array, if there is an odd number of elements, put the
     * extra data on the right side.
     *
     * Hint: If two data are equal when merging, think about which subarray
     * you should pull from first
     *
     * @param <T>        data type to sort
     * @param arr        the array to be sorted
     * @param comparator the Comparator used to compare the data in arr
     * @throws java.lang.IllegalArgumentException if the array or comparator is
     *                                            null
     */
    public static <T> void mergeSort(T[] arr, Comparator<T> comparator) {
        if (arr == null) {
            throw new IllegalArgumentException("Error. Array can't be null.");
        } else if (comparator == null) {
            throw new IllegalArgumentException("Error. Comparator can't be null.");
        } else {
            if (arr.length == 1) {
                return;
            }
            int length = arr.length;
            int midIndex = arr.length / 2;
            T[] left = (T[]) new Object[midIndex];
            T[] right = (T[]) new Object[length - midIndex];
            for (int i = 0; i < midIndex; i++) {
                left[i] = arr[i];
            }
            for (int i = 0; i < length - midIndex; i++) {
                right[i] = arr[i + midIndex];
            }
            mergeSort(left, comparator);
            mergeSort(right, comparator);
            int startLeft = 0;
            int startRight = 0;
            while (startLeft != (left.length) && startRight != (right.length)) {
                if (comparator.compare(left[startLeft], right[startRight]) <= 0) {
                    arr[startLeft + startRight] = left[startLeft];
                    startLeft++;
                } else {
                    arr[startLeft + startRight] = right[startRight];
                    startRight++;
                }
            }
            while (startLeft < left.length) {
                arr[startLeft + startRight] = left[startLeft];
                startLeft++;
            }
            while (startRight < right.length) {
                arr[startLeft + startRight] = right[startRight];
                startRight++;
            }
        }
    }

    /**
     * Implement quick sort.
     *
     * Use the provided random object to select your pivots. For example if you
     * need a pivot between a (inclusive) and b (exclusive) where b > a, use
     * the following code:
     *
     * int pivotIndex = rand.nextInt(b - a) + a;
     *
     * If your recursion uses an inclusive b instead of an exclusive one,
     * the formula changes by adding 1 to the nextInt() call:
     *
     * int pivotIndex = rand.nextInt(b - a + 1) + a;
     *
     * It should be:
     * in-place
     * unstable
     * not adaptive
     *
     * Have a worst case running time of:
     * O(n^2)
     *
     * And a best case running time of:
     * O(n log n)
     *
     * Make sure you code the algorithm as you have been taught it in class.
     * There are several versions of this algorithm and you may not receive
     * credit if you do not use the one we have taught you!
     *
     * @param <T>        data type to sort
     * @param arr        the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     * @param rand       the Random object used to select pivots
     * @throws java.lang.IllegalArgumentException if the array or comparator or
     *                                            rand is null
     */
    public static <T> void quickSort(T[] arr, Comparator<T> comparator,
                                     Random rand) {
        if (arr == null) {
            throw new IllegalArgumentException("Error. Array can't be null.");
        } else if (comparator == null) {
            throw new IllegalArgumentException("Error. Comparator can't be null.");
        } else if (rand == null) {
            throw new IllegalArgumentException("Error. Rand can't be null.");
        } else {
            quickSortHelper(arr, 0, arr.length - 1, comparator, rand);
        }
    }

    /**
     * Helper method for quicksort.
     *
     * @param arr the array to be sorted
     * @param comparator the Comparator used to compare the data in arr
     * @param rand the Random object used to select pivots
     * @param start start index
     * @param end end index
     * @param <T> data type to sort
     */
    private static <T> void quickSortHelper(T[] arr, int start, int end, Comparator<T> comparator, Random rand) {
        if (end - start < 1) {
            return;
        }
        int pivotIndex = Math.abs(rand.nextInt(end - start + 1) + start);
        T pivotValue = arr[pivotIndex];
        swap(arr, start, pivotIndex);
        int i = start + 1;
        int j = end;
        while (i <= j) {
            while (i <= j && comparator.compare(arr[i], pivotValue) <= 0) {
                i++;
            }
            while (i <= j && comparator.compare(arr[j], pivotValue) >= 0) {
                j--;
            }
            if (i <= j) {
                swap(arr, i, j);
                i++;
                j--;
            }
        }
        swap(arr, start, j);
        quickSortHelper(arr, start, j - 1, comparator, rand);
        quickSortHelper(arr, j + 1, end, comparator, rand);
    }

    /**
     * Implement LSD (least significant digit) radix sort.
     *
     * Make sure you code the algorithm as you have been taught it in class.
     * There are several versions of this algorithm and you may not get full
     * credit if you do not implement the one we have taught you!
     *
     * Remember you CANNOT convert the ints to strings at any point in your
     * code! Doing so may result in a 0 for the implementation.
     *
     * It should be:
     * out-of-place
     * stable
     * not adaptive
     *
     * Have a worst case running time of:
     * O(kn)
     *
     * And a best case running time of:
     * O(kn)
     *
     * You are allowed to make an initial O(n) passthrough of the array to
     * determine the number of iterations you need. The number of iterations
     * can be determined using the number with the largest magnitude.
     *
     * At no point should you find yourself needing a way to exponentiate a
     * number; any such method would be non-O(1). Think about how how you can
     * get each power of BASE naturally and efficiently as the algorithm
     * progresses through each digit.
     *
     * Refer to the PDF for more information on LSD Radix Sort.
     *
     * You may use ArrayList or LinkedList if you wish, but it may only be
     * used inside radix sort and any radix sort helpers. Do NOT use these
     * classes with other sorts. However, be sure the List implementation you
     * choose allows for stability while being as efficient as possible.
     *
     * Do NOT use anything from the Math class except Math.abs().
     *
     * @param arr the array to be sorted
     * @throws java.lang.IllegalArgumentException if the array is null
     */
    public static void lsdRadixSort(int[] arr) {
        if (arr == null) {
            throw new IllegalArgumentException("Error. Array can't be null.");
        } else {
            List<Integer>[] buckets = new ArrayList[19];
            for (int i = 0; i < 19; i++) {
                buckets[i] = new ArrayList<Integer>();
            }
            int maxNumber = 0;
            for (int i = 0; i < arr.length; i++) {
                if (Math.abs(arr[i]) > maxNumber) {
                    maxNumber = Math.abs(arr[i]);
                }
            }
            int k = 0;
            while (maxNumber != 0) {
                maxNumber = maxNumber / 10;
                k++;
            }
            int m = 1;
            for (int i = 0; i <= k; i++) {
                for (int j = 0; j < arr.length; j++) {
                    buckets[((arr[j] / m) % 10) + 9].add(arr[j]);
                }
                int index = 0;
                for (int x = 0; x < buckets.length; x++) {
                    for (Integer y: buckets[x]) {
                        arr[index] = y;
                        index++;
                    }
                    buckets[x].clear();
                }
                m *= 10;
            }
        }
    }

    /**
     * Implement heap sort.
     *
     * It should be:
     * out-of-place
     * unstable
     * not adaptive
     *
     * Have a worst case running time of:
     * O(n log n)
     *
     * And a best case running time of:
     * O(n log n)
     *
     * Use java.util.PriorityQueue as the heap. Note that in this
     * PriorityQueue implementation, elements are removed from smallest
     * element to largest element.
     *
     * Initialize the PriorityQueue using its build heap constructor (look at
     * the different constructors of java.util.PriorityQueue).
     *
     * Return an int array with a capacity equal to the size of the list. The
     * returned array should have the elements in the list in sorted order.
     *
     * @param data the data to sort
     * @return the array with length equal to the size of the input list that
     * holds the elements from the list is sorted order
     * @throws java.lang.IllegalArgumentException if the data is null
     */
    public static int[] heapSort(List<Integer> data) {
        if (data == null) {
            throw new IllegalArgumentException("Data can't be null");
        } else {
            PriorityQueue<Integer> queue = new PriorityQueue<>(data);
            int[] arr = new int[data.size()];
            for (int i = 0; i < data.size(); i++) {
                arr[i] = queue.remove();
            }
            return arr;
        }
    }

    /**
     * Helper method for swapping two values in an array
     * @param arr the array being sorted
     * @param maxVal the index of one of the values to be swapped
     * @param i the index of the other value to bw swapped
     * @param <T> data type to sort
     */
    private static <T> void swap(T[] arr, int maxVal, int i) {
        T carrier = arr[maxVal];
        arr[maxVal] = arr[i];
        arr[i] = carrier;
    }
}
