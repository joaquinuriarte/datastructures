import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

/**
 * Fairly comprehensive set of tests to help with HW08.
 *
 * If anything is confusing/wrong please let me know! I want this to be as useful as possible.
 *
 * @author Liam Jones
 * @version 1.41
 */
public class LiamSortingTest {

    private static final int TIMEOUT = 200;
    private Num[] arr;
    private Num[] sortedArr;
    private Num[] arr2;
    private Num[] sortedArr2;
    private Num[] bigArr;
    private int[] bigArrInts;
    private Num[] bigArrSorted;
    private int[] bigArrSortedInts;
    private ComparatorPlus<Num> comp;

    @Before
    public void init() {
        arr = new Num[10];

        arr[0] = new Num(65);
        arr[1] = new Num(34);
        arr[2] = new Num(56);
        arr[3] = new Num(12, 1);
        arr[4] = new Num(23);
        arr[5] = new Num(24);
        arr[6] = new Num(12, 2);
        arr[7] = new Num(22, 1);
        arr[8] = new Num(22, 2);
        arr[9] = new Num(4);

        sortedArr = new Num[10];

        sortedArr[0] = arr[9];
        sortedArr[1] = arr[3];
        sortedArr[2] = arr[6];
        sortedArr[3] = arr[7];
        sortedArr[4] = arr[8];
        sortedArr[5] = arr[4];
        sortedArr[6] = arr[5];
        sortedArr[7] = arr[1];
        sortedArr[8] = arr[2];
        sortedArr[9] = arr[0];

        comp = Num.getComparator();

        /*
        Original Array:
        65
        34 (1)
        56
        12 (1)
        23
        24
        12 (2)
        22 (1)
        22 (2)
        4
        
        Sorted Array (For stable sorts):
        4
        12 (1)
        12 (2)
        22 (1)
        22 (2)
        23
        24
        34
        56
        65

        Sorted Array (For Selection Sort):
        4
        12 (2)
        12 (1)
        22 (2)
        22 (1)
        23
        24
        34
        56
        65

        Sorted Array (For Quick Sort):
        4
        12(1)
        12(2)
        22(2)
        22(1)
        23
        24
        34
        56
        65

        For both LSDRadix and Heap sorts, ints (not Nums) are used, so relative order is not verifiable

         */

        arr2 = new Num[25];
        sortedArr2 = new Num[25];

        for (int i = 0; i < arr2.length; i++) {
            if (i % 2 == 0) {
                arr2[i] = new Num(i - 10);
            } else {
                arr2[i] = new Num(i + 7);
            }
        }

        arr2[1].setOrder(1);
        arr2[3].setOrder(1);
        arr2[5].setOrder(1);
        arr2[7].setOrder(1);
        arr2[18].setOrder(2);
        arr2[20].setOrder(2);
        arr2[22].setOrder(2);
        arr2[24].setOrder(2);
        
        int val = -10;
        for (int i = 0; i < arr2.length; i++) {
            if (val >= 8 && val <= 14) {
                sortedArr2[i] = new Num(val, 1);
                i++;
                sortedArr2[i] = new Num(val, 2);
            } else {
                sortedArr2[i] = new Num(val);
            }
            val += 2;
        }


        /*
        Original Array (2):
        -10
        8(1)
        -8
        10(1)
        -6
        12(1)
        -4
        14(1)
        -2
        16
        0
        18
        2
        20
        4
        22
        6
        24
        8(2)
        26
        10(2)
        28
        12(2)
        30
        14(2)
        32

        Sorted Array (Selection Sort):
        -10
        -8
        -6
        -4
        -2
        0
        2
        4
        6
        8(2)
        8(1)
        10(2)
        10(1)
        12(2)
        12(1)
        14(2)
        14(1)
        16
        18
        20
        22
        24
        26
        28
        30
         */

        /*
        These large lists of data are intended to catch any possible bug that small lists may not

        There are no duplicates within these larger lists, so stability will not be tested

        Refer to the declaration states for the unsorted and sorted lists
         */
        bigArrInts = new int[]{-180, -286, 343, -22, 414, 217, 2, -252, -227, 32, -23, -266, -294, 467, 366, -392,
                35, -213, -343, 56, -257, 463, -98, 286, 48, 259, -156, -199, -201, 430, 85, -296, 28, 146, 115, 279,
                429, 421, 351, 344, 482, -471, -196, 245, 1, -122, -314, 219, 323, 55, -214, -216, 359, -420, -104,
                -357, 120, 256, -452, -81, -303, -367, -342, -94, 473, 255, 328, 246, -290, -328, 425, -197, -351,
                44, 261, 13, 466, -472, -32, 296, -449, -119, 449, 166, 268, 369, -288, -112, 78, 428, 330, 295,
                118, -155, 402, -433, 293, -333, 388, 386};

        bigArr = new Num[100];
        for (int i = 0; i < bigArrInts.length; i++) {
            bigArr[i] = new Num(bigArrInts[i]);
        }
        bigArrSortedInts = new int[]{-472, -471, -452, -449, -433, -420, -392, -367, -357, -351, -343, -342,
                -333, -328, -314, -303, -296, -294, -290, -288, -286, -266, -257, -252, -227, -216, -214, -213,
                -201, -199, -197, -196, -180, -156, -155, -122, -119, -112, -104, -98, -94, -81, -32, -23, -22,
                1, 2, 13, 28, 32, 35, 44, 48, 55, 56, 78, 85, 115, 118, 120, 146, 166, 217, 219, 245, 246, 255,
                256, 259, 261, 268, 279, 286, 293, 295, 296, 323, 328, 330, 343, 344, 351, 359, 366, 369, 386,
                388, 402, 414, 421, 425, 428, 429, 430, 449, 463, 466, 467, 473, 482};
        bigArrSorted = new Num[100];
        for (int i = 0; i < bigArrInts.length; i++) {
            bigArrSorted[i] = new Num(bigArrSortedInts[i]);
        }
    }

    /**
     * Tests the selection sort algorithm
     * 
     * An array of length 10 should use 45 comparisons
     */
    @Test (timeout = TIMEOUT)
    public void testSelectionSort1() {
        Sorting.selectionSort(arr, comp);
        assertArrayEquals(sortedArr, arr);
        assertTrue("Number of comparisons: " + comp.getCount(),
                comp.getCount() <= 45 && comp.getCount() != 0);
    }

    /**
     * Tests the selection sort algorithm
     *
     * An array of length 25 should use 300 comparisons
     */
    @Test (timeout = TIMEOUT)
    public void testSelectionSort2() {
        Sorting.selectionSort(arr2, comp);
        assertArrayEquals(sortedArr2, arr2);
        assertTrue("Number of comparisons: " + comp.getCount(),
                comp.getCount() <= 300 && comp.getCount() != 0);
    }

    /**
     * Tests the selection sort algorithm
     *
     * An array of length 100 should use 4950 comparisons
     */
    @Test (timeout = TIMEOUT)
    public void testSelectionSort3() {
        Sorting.selectionSort(bigArr, comp);
        assertArrayEquals(bigArrSorted, bigArr);
        assertTrue("Number of comparisons: " + comp.getCount(),
                comp.getCount() <= 4950 && comp.getCount() != 0);
    }

    /**
     * Tests to ensure an illegal argument exception is thrown when it should be
     */
    @Test (timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testSelectionSortException1() {
        Sorting.selectionSort(arr, null);
    }

    /**
     * Tests to ensure an illegal argument exception is thrown when it should be
     */
    @Test (timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testSelectionSortException2() {
        Sorting.selectionSort(null, comp);
    }

    /**
     * Tests the Cocktail Shaker Sort algorithm
     *
     * For this specific array, 42 comparisons should be made
     */
    @Test (timeout = TIMEOUT)
    public void testCocktailShakerSort1() {
        Sorting.cocktailSort(arr, comp);
        assertArrayEquals(sortedArr, arr);
        assertTrue("Number of comparisons: " + comp.getCount(),
                comp.getCount() <= 42 && comp.getCount() != 0);
        assertEquals(1, arr[1].getOrder());
        assertEquals(2, arr[2].getOrder());
        assertEquals(1, arr[3].getOrder());
        assertEquals(2, arr[4].getOrder());
    }

    /**
     * Tests the Cocktail Shaker Sort algorithm
     *
     * For this specific array, 145 comparisons should be made
     */
    @Test (timeout = TIMEOUT)
    public void testCocktailShakerSort2() {
        Sorting.cocktailSort(arr2, comp);
        assertArrayEquals(sortedArr2, arr2);
        assertTrue("Number of comparisons: " + comp.getCount(),
                comp.getCount() <= 145 && comp.getCount() != 0);

        assertEquals(1, arr2[9].getOrder());
        assertEquals(1, arr2[11].getOrder());
        assertEquals(1, arr2[13].getOrder());
        assertEquals(1, arr2[15].getOrder());
        assertEquals(2, arr2[10].getOrder());
        assertEquals(2, arr2[12].getOrder());
        assertEquals(2, arr2[14].getOrder());
        assertEquals(2, arr2[16].getOrder());

    }

    /**
     * Tests the Cocktail Shaker Sort algorithm
     *
     * For this specific array, 3200 comparisons should be made
     */
    @Test (timeout = TIMEOUT)
    public void testCocktailShakerSort3() {
        Sorting.cocktailSort(bigArr, comp);
        assertArrayEquals(bigArrSorted, bigArr);
        assertTrue("Number of comparisons: " + comp.getCount(),
                comp.getCount() <= 3200 && comp.getCount() != 0);
    }

    /**
     * Tests to ensure an illegal argument exception is thrown when it should be
     */
    @Test (timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testCocktailSortException1() {
        Sorting.cocktailSort(arr, null);
    }

    /**
     * Tests to ensure an illegal argument exception is thrown when it should be
     */
    @Test (timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testCocktailSortException2() {
        Sorting.cocktailSort(null, comp);
    }

    /**
     * Tests the Merge Sort algorithm
     *
     * For this specific array, 23 comparisons should be made
     */
    @Test (timeout = TIMEOUT)
    public void testMergeSort1() {
        Sorting.mergeSort(arr, comp);
        assertArrayEquals(sortedArr, arr);
        assertTrue("Number of comparisons: " + comp.getCount(),
                comp.getCount() <= 23 && comp.getCount() != 0);
        assertEquals(1, arr[1].getOrder());
        assertEquals(2, arr[2].getOrder());
        assertEquals(1, arr[3].getOrder());
        assertEquals(2, arr[4].getOrder());
    }

    /**
     * Tests the Merge Sort algorithm
     *
     * For this specific array, 77 comparisons should be made
     */
    @Test (timeout = TIMEOUT)
    public void testMergeSort2() {
        Sorting.mergeSort(arr2, comp);
        assertArrayEquals(sortedArr2, arr2);
        assertTrue("Number of comparisons: " + comp.getCount(),
                comp.getCount() <= 77 && comp.getCount() != 0);

        assertEquals(1, arr2[9].getOrder());
        assertEquals(1, arr2[11].getOrder());
        assertEquals(1, arr2[13].getOrder());
        assertEquals(1, arr2[15].getOrder());
        assertEquals(2, arr2[10].getOrder());
        assertEquals(2, arr2[12].getOrder());
        assertEquals(2, arr2[14].getOrder());
        assertEquals(2, arr2[16].getOrder());
    }

    /**
     * Tests the Merge Sort algorithm
     *
     * For this specific array, 548 comparisons should be made
     */
    @Test (timeout = TIMEOUT)
    public void testMergeSort3() {
        Sorting.mergeSort(bigArr, comp);
        assertArrayEquals(bigArrSorted, bigArr);
        assertTrue("Number of comparisons: " + comp.getCount(),
                comp.getCount() <= 548 & comp.getCount() != 0);
    }

    /**
     * Tests to ensure an illegal argument exception is thrown when it should be
     */
    @Test (timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testMergeSortException1() {
        Sorting.mergeSort(arr, null);
    }

    /**
     * Tests to ensure an illegal argument exception is thrown when it should be
     */
    @Test (timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testMergeSortException2() {
        Sorting.mergeSort(null, comp);
    }

    /**
     * Tests the Quick Sort algorithm
     *
     * For this specific array, 25 comparisons should be made.
     */
    @Test (timeout = TIMEOUT)
    public void testQuickSort1() {
        Sorting.quickSort(arr, comp, new Random(133221));
        assertTrue("Number of comparisons: " + comp.getCount(),
                comp.getCount() <= 25 && comp.getCount() != 0);
        assertArrayEquals(sortedArr, arr);
    }

    /**
     * Tests the Quick Sort algorithm
     *
     * For this specific array, 92 comparisons should be made.
     */
    @Test (timeout = TIMEOUT)
    public void testQuickSort2() {
        Sorting.quickSort(arr2, comp, new Random(133221));
        assertArrayEquals(sortedArr2, arr2);
        assertTrue("Number of comparisons: " + comp.getCount(),
                comp.getCount() <= 92 && comp.getCount() != 0);
    }

    /**
     * Tests the Quick Sort algorithm
     *
     * For this specific array, 696 comparisons should be made.
     */
    @Test (timeout = TIMEOUT)
    public void testQuickSort3() {
        Sorting.quickSort(bigArr, comp, new Random(133221));
        assertArrayEquals(bigArrSorted, bigArr);
        assertTrue("Number of comparisons: " + comp.getCount(),
                comp.getCount() <= 696 && comp.getCount() != 0);
    }

    /**
     * Tests to ensure an illegal argument exception is thrown when it should be
     */
    @Test (timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testQuickSortException1() {
        Sorting.quickSort(arr, null, new Random(133221));
    }

    /**
     * Tests to ensure an illegal argument exception is thrown when it should be
     */
    @Test (timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testQuickSortException2() {
        Sorting.quickSort(null, comp, new Random(133221));
    }

    /**
     * Tests to ensure an illegal argument exception is thrown when it should be
     */
    @Test (timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testQuickSortException3() {
        Sorting.quickSort(arr, comp, null);
    }

    /**
     * Tests the LSD Radix Sort algorithm
     *
     * The comparator object is not used for this algorithm
     *
     * Since the method requires an array of ints, the relative order (stability) of this algorithm is hard to test
     */
    @Test (timeout = TIMEOUT)
    public void testLSDRadixSort1() {
        int[] nums = new int[10];
        int[] numsSorted = new int[10];
        for (int i = 0; i < arr.length; i++) {
            nums[i] = arr[i].getVal();
        }
        for (int i = 0; i < arr.length; i++) {
            numsSorted[i] = sortedArr[i].getVal();
        }

        Sorting.lsdRadixSort(nums);
        assertArrayEquals(numsSorted, nums);

    }

    /**
     * Tests the LSD Radix Sort algorithm
     *
     * The comparator object is not used for this algorithm
     *
     * Since the method requires an array of ints, the relative order (stability) of this algorithm is hard to test
     */
    @Test (timeout = TIMEOUT)
    public void testLSDRadixSort2() {
        int[] nums = new int[25];
        int[] numsSorted = new int[25];
        for (int i = 0; i < arr2.length; i++) {
            nums[i] = arr2[i].getVal();
        }
        for (int i = 0; i < arr2.length; i++) {
            numsSorted[i] = sortedArr2[i].getVal();
        }

        Sorting.lsdRadixSort(nums);
        assertArrayEquals(numsSorted, nums);

    }

    /**
     * Tests the LSD Radix Sort algorithm
     *
     * The comparator object is not used for this algorithm
     *
     * This just presents a small challenge to the algorithm considering the large difference in digits
     */
    @Test (timeout = TIMEOUT)
    public void testLSDRadixSort3() {
        int[] nums = new int[]{1, 5, 2, 4, Integer.MIN_VALUE, 6, Integer.MAX_VALUE, 3, -1, 0};
        int[] numsSorted = new int[]{Integer.MIN_VALUE, -1, 0, 1, 2, 3, 4, 5, 6, Integer.MAX_VALUE};
        Sorting.lsdRadixSort(nums);

        assertArrayEquals(numsSorted, nums);
    }

    /**
     * Tests the LSD Radix Sort algorithm
     *
     * The comparator object is not used for this algorithm
     *
     * These values cause integer overflow, which should be automatically handled in the background
     */
    @Test (timeout = TIMEOUT)
    public void testLSDRadixSort4() {
        int[] nums = new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE + 5, Integer.MIN_VALUE, Integer.MIN_VALUE - 2};
        int[] numsSorted = new int[]{Integer.MIN_VALUE, Integer.MAX_VALUE + 5,
                Integer.MIN_VALUE - 2, Integer.MAX_VALUE};
        Sorting.lsdRadixSort(nums);

        assertArrayEquals(numsSorted, nums);
    }

    @Test (timeout = TIMEOUT)
    public void testLSDRadixSort5() {
        Sorting.lsdRadixSort(bigArrInts);
        assertArrayEquals(bigArrSortedInts, bigArrInts);
    }

    /**
     * Tests to ensure an illegal argument exception is thrown when it should be
     */
    @Test (timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testLSDRadixSortException() {
        Sorting.lsdRadixSort(null);
    }

    /**
     * Tests the Heap Sort algorithm
     *
     * Since it is unstable, relative order is unimportant.
     *
     * Comparisons are made within the Priority Queue constructor (Build Heap algorithm), so our comparator is not used
     */
    @Test (timeout = TIMEOUT)
    public void testHeapSort1() {
        LinkedList<Integer> nums = new LinkedList<>();
        for (Num num : arr) {
            nums.add(num.getVal());
        }
        int[] sorted = new int[10];
        for (int i = 0; i < arr.length; i++) {
            sorted[i] = sortedArr[i].getVal();
        }
        int[] arr1 = Sorting.heapSort(nums);
        assertArrayEquals(arr1, sorted);
    }

    /**
     * Tests the Heap Sort algorithm
     *
     * Since it is unstable, relative order is unimportant.
     *
     * Comparisons are made within the Priority Queue constructor (Build Heap algorithm), so our comparator is not used
     */
    @Test (timeout = TIMEOUT)
    public void testHeapSort2() {
        LinkedList<Integer> nums = new LinkedList<>();
        for (Num num : arr2) {
            nums.add(num.getVal());
        }
        int[] sorted = new int[25];
        for (int i = 0; i < arr2.length; i++) {
            sorted[i] = sortedArr2[i].getVal();
        }
        int[] arr1 = Sorting.heapSort(nums);
        assertArrayEquals(arr1, sorted);
    }

    /**
     * Tests the Heap Sort algorithm
     *
     * Since it is unstable, relative order is unimportant.
     *
     * Comparisons are made within the Priority Queue constructor (Build Heap algorithm), so our comparator is not used
     */
    @Test (timeout = TIMEOUT)
    public void testHeapSort3() {
        int[] nums = new int[]{1, 5, 2, 4, Integer.MIN_VALUE, 6, Integer.MAX_VALUE, 3, -1, 0};
        int[] numsSorted = new int[]{Integer.MIN_VALUE, -1, 0, 1, 2, 3, 4, 5, 6, Integer.MAX_VALUE};
        List<Integer> list = new ArrayList<>();
        for (int num : nums) {
            list.add(num);
        }
        int[] arr1 = Sorting.heapSort(list);
        assertArrayEquals(numsSorted, arr1);
    }

    /**
     * Tests the Heap Sort algorithm
     *
     * Since it is unstable, relative order is unimportant.
     *
     * Comparisons are made within the Priority Queue constructor (Build Heap algorithm), so our comparator is not used
     */
    @Test (timeout = TIMEOUT)
    public void testHeapSort4() {
        List<Integer> bigList = new ArrayList<>();

        for (int num : bigArrInts) {
            bigList.add(num);
        }
        assertArrayEquals(bigArrSortedInts, Sorting.heapSort(bigList));
    }

    /**
     * Tests the Heap Sort algorithm to ensure an Illegal Argument Exception is thrown
     */
    @Test (timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testHeapSortException() {
        Sorting.heapSort(null);
    }


    /**
     * Simple class to help monitor order in stable sorting algorithms.
     *
     *
     */
    private static class Num {
        
        private int val;
        private int order;

        /**
         * Initializes a Num object with both a val and an order.
         *
         * @param val The int value used to sort Nums
         * @param order The relative order of equivalent Nums.
         */
        public Num(int val, int order) {
            this.val = val;
            this.order = order;
        }

        /**
         * Initializes a Num with only a val.
         *
         * @param val The int value used to sort Nums
         */
        public Num(int val) {
            this.val = val;
        }

        /**
         * Getter method for val.
         *
         * @return val
         */
        public int getVal() {
            return val;
        }

        /**
         * Setter method for val
         * @param val New value of val
         */
        public void setVal(int val) {
            this.val = val;
        }

        /**
         * Getter method for order
         * @return int that indicates the relative order of a num
         */
        public int getOrder() {
            return order;
        }

        /**
         * Setter method for order
         * @param order New value of order
         */
        public void setOrder(int order) {
            this.order = order;
        }

        @Override
        public String toString() {
            if (order == 0) {
                return val + " ";
            } else {
                return val + "(" + order + ") ";
            }
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Num num = (Num) o;
            return val == num.val;
        }

        @Override
        public int hashCode() {
            return Objects.hash(val);
        }

        /**
         * Creates a comparator to be used in sorting the Nums.
         *
         * Sorts exclusively by the value of val, which demonstrates particular method's stability/instability
         *
         * @return The comparator object
         */
        public static LiamSortingTest.ComparatorPlus<LiamSortingTest.Num> getComparator() {
            return new LiamSortingTest.ComparatorPlus<LiamSortingTest.Num>() {
                @Override
                public int compare(LiamSortingTest.Num num1,
                                   LiamSortingTest.Num num2) {
                    incrementCount();
                    return num1.getVal() - num2.getVal();
                }
            };
        }
        /**
         * Inner class that allows counting how many comparisons were made.
         */

    }

    /**
     * Definition for the comparator, borrowed from TA written JUnits
     * @param <T> The type of object we are comparing.
     */
    private abstract static class ComparatorPlus<T> implements Comparator<T> {
        private int count;

        /**
         * Get the number of comparisons made.
         *
         * @return number of comparisons made
         */
        public int getCount() {
            return count;
        }

        /**
         * Increment the number of comparisons made by one. Call this method in
         * your compare() implementation.
         */
        public void incrementCount() {
            count++;
        }
    }

}
