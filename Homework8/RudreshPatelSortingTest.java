import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author Rudresh Patel
 * @version 1.4
 *
 * This JUnit file maybe updated to include String Test conditions to test the Sorting algorithms.
 *
 * NOTE: Base template for these tests was taken from the "SortingStudentTest.java" file provided by the TAs.
 *       Some tests maybe similar if not same to the provided student tests by the TAs.
 */
public class RudreshPatelSortingTest {

    private static final int TIMEOUT = 1000;
    private ArrayStrings[] pioneers;
    private ArrayStrings[] pioneersSorted;
    private ComparatorPlus<ArrayStrings> compName;
    private ArrayIntegers[] element;
    private ArrayIntegers[] elementSorted;
    private ComparatorPlus<ArrayIntegers> compInt;

    @Before
    public void setUp() {
        /*
            Unsorted Names:
                index 0: Henrietta Leavitt
                index 1: Grace Hopper
                index 2: Ada Lovelace
                index 3: Hedy Lamarr
                index 4: Fran Bilas
                index 5: Karen Spärck Jones
                index 6: Katherine Johnson
                index 7: Betty Jennings
                index 8: Ruth Lichterman
                index 9: Kay McNulty
                index 10: Betty Snyder
                index 11: Marlyn Wescoff
                index 12: Mary Jackson
         */

        /*
            Sorted Names:
                index 0: Ada Lovelace
                index 1: Betty Jennings
                index 2: Betty Snyder
                index 3: Fran Bilas
                index 4: Grace Hopper
                index 5: Hedy Lamarr
                index 6: Henrietta Leavitt
                index 7: Karen Spärck Jones
                index 8: Katherine Johnson
                index 9: Kay McNulty
                index 10: Marlyn Wescoff
                index 11: Mary Jackson
                index 12: Ruth Lichterman
         */

        pioneers = new ArrayStrings[13];
        pioneers[0] = new ArrayStrings("Henrietta Leavitt");
        pioneers[1] = new ArrayStrings("Grace Hopper");
        pioneers[2] = new ArrayStrings("Ada Lovelace");
        pioneers[3] = new ArrayStrings("Hedy Lamarr");
        pioneers[4] = new ArrayStrings("Fran Bilas");
        pioneers[5] = new ArrayStrings("Karen Spärck Jones");
        pioneers[6] = new ArrayStrings("Katherine Johnson");
        pioneers[7] = new ArrayStrings("Betty Jennings");
        pioneers[8] = new ArrayStrings("Ruth Lichterman");
        pioneers[9] = new ArrayStrings("Kay McNulty");
        pioneers[10] = new ArrayStrings("Betty Snyder");
        pioneers[11] = new ArrayStrings("Marlyn Wescoff");
        pioneers[12] = new ArrayStrings("Mary Jackson");

        pioneersSorted = new ArrayStrings[13];
        pioneersSorted[0] = pioneers[2];
        pioneersSorted[1] = pioneers[7];
        pioneersSorted[2] = pioneers[10];
        pioneersSorted[3] = pioneers[4];
        pioneersSorted[4] = pioneers[1];
        pioneersSorted[5] = pioneers[3];
        pioneersSorted[6] = pioneers[0];
        pioneersSorted[7] = pioneers[5];
        pioneersSorted[8] = pioneers[6];
        pioneersSorted[9] = pioneers[9];
        pioneersSorted[10] = pioneers[11];
        pioneersSorted[11] = pioneers[12];
        pioneersSorted[12] = pioneers[8];
        compName = ArrayStrings.getNameComparator();
    }





    //--------------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------------
    // |||||||||||||||||||||||        SELECTION SORT TESTS      ||||||||||||||||||||||||||||
    //--------------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------------

    @Test(timeout = TIMEOUT)
    public void selectionSortStringTest00() {
        Sorting.selectionSort(pioneers, compName);
        assertArrayEquals(pioneersSorted, pioneers);
        System.out.println("Number of comparisons: " + compName.getCount());
        assertTrue("Number of comparisons: " + compName.getCount(),
                compName.getCount() <= 78 && compName.getCount() != 0);
    }

    @Test(timeout = TIMEOUT)
    public void selectionSortIntegersTest01() {
        // single element test
        ArrayIntegers[] singleElement = new ArrayIntegers[1];
        singleElement[0] = new ArrayIntegers(0);  // single value
        compInt = ArrayIntegers.getValueComparator();

        ArrayIntegers[] singleElementSorted = new ArrayIntegers[1];
        singleElementSorted[0] = new ArrayIntegers(0);

        Sorting.selectionSort(singleElement, compInt);
        assertArrayEquals(singleElementSorted, singleElement);
        //System.out.println("Number of comparisons: " + compInt.getCount());
        assertTrue("Number of comparisons: " + compInt.getCount(),
                compInt.getCount() == 0);
    }

    @Test(timeout = TIMEOUT)
    public void selectionSortIntegersTest02() {
        // two element test
        element = new ArrayIntegers[2];
        element[0] = new ArrayIntegers(2);  //  value @ index 0
        element[1] = new ArrayIntegers(1); //   value @ index 1

        compInt = ArrayIntegers.getValueComparator();

        elementSorted = new ArrayIntegers[2];
        elementSorted[0] = new ArrayIntegers(1);
        elementSorted[1] = new ArrayIntegers(2);


        Sorting.selectionSort(element, compInt);
        assertArrayEquals(elementSorted, element);
        //System.out.println("Number of comparisons: " + compInt.getCount());
        assertTrue("Number of comparisons: " + compInt.getCount(),
                compInt.getCount() == 1);
    }


    @Test(timeout = TIMEOUT)
    public void selectionSortIntegersTest03() {
        // Multiple postive elements test
        element = new ArrayIntegers[7];
        element[0] = new ArrayIntegers(2);  //  value @ index 0
        element[1] = new ArrayIntegers(1); //   value @ index 1
        element[2] = new ArrayIntegers(3);
        element[3] = new ArrayIntegers(6);
        element[4] = new ArrayIntegers(9);
        element[5] = new ArrayIntegers(7);
        element[6] = new ArrayIntegers(12);

        compInt = ArrayIntegers.getValueComparator();

        elementSorted = new ArrayIntegers[7];
        elementSorted[0] = new ArrayIntegers(1);
        elementSorted[1] = new ArrayIntegers(2);
        elementSorted[2] = new ArrayIntegers(3);
        elementSorted[3] = new ArrayIntegers(6);
        elementSorted[4] = new ArrayIntegers(7);
        elementSorted[5] = new ArrayIntegers(9);
        elementSorted[6] = new ArrayIntegers(12);

        Sorting.selectionSort(element, compInt);
        assertArrayEquals(elementSorted, element);
        //System.out.println("Number of comparisons: " + compInt.getCount());
        //System.out.println(elementSorted[0]);
        assertTrue("Number of comparisons: " + compInt.getCount(),
                compInt.getCount() == 21);
    }


    @Test(timeout = TIMEOUT)
    public void selectionSortIntegersTest04() {
        // Multiple negative elements test
        element = new ArrayIntegers[7];
        element[0] = new ArrayIntegers(-2);  //  value @ index 0
        element[1] = new ArrayIntegers(-1); //   value @ index 1
        element[2] = new ArrayIntegers(-3);
        element[3] = new ArrayIntegers(-6);
        element[4] = new ArrayIntegers(-9);
        element[5] = new ArrayIntegers(-7);
        element[6] = new ArrayIntegers(-12);

        compInt = ArrayIntegers.getValueComparator();

        elementSorted = new ArrayIntegers[7];
        elementSorted[0] = new ArrayIntegers(-12);
        elementSorted[1] = new ArrayIntegers(-9);
        elementSorted[2] = new ArrayIntegers(-7);
        elementSorted[3] = new ArrayIntegers(-6);
        elementSorted[4] = new ArrayIntegers(-3);
        elementSorted[5] = new ArrayIntegers(-2);
        elementSorted[6] = new ArrayIntegers(-1);

        Sorting.selectionSort(element, compInt);
        assertArrayEquals(elementSorted, element);
        //System.out.println("Number of comparisons: " + compInt.getCount());
        assertTrue("Number of comparisons: " + compInt.getCount(),
                compInt.getCount() == 21);
    }

    @Test(timeout = TIMEOUT)
    public void selectionSortIntegersTest05() {
        // Already sorted elements test
        element = new ArrayIntegers[7];
        element[0] = new ArrayIntegers(1);  //  value @ index 0
        element[1] = new ArrayIntegers(2); //   value @ index 1
        element[2] = new ArrayIntegers(3);
        element[3] = new ArrayIntegers(6);
        element[4] = new ArrayIntegers(7);
        element[5] = new ArrayIntegers(9);
        element[6] = new ArrayIntegers(12);

        compInt = ArrayIntegers.getValueComparator();

        elementSorted = new ArrayIntegers[7];
        elementSorted[0] = new ArrayIntegers(1);
        elementSorted[1] = new ArrayIntegers(2);
        elementSorted[2] = new ArrayIntegers(3);
        elementSorted[3] = new ArrayIntegers(6);
        elementSorted[4] = new ArrayIntegers(7);
        elementSorted[5] = new ArrayIntegers(9);
        elementSorted[6] = new ArrayIntegers(12);

        Sorting.selectionSort(element, compInt);
        assertArrayEquals(elementSorted, element);
        //System.out.println("Number of comparisons: " + compInt.getCount());
        assertTrue("Number of comparisons: " + compInt.getCount(),
                compInt.getCount() == 21);
    }

    @Test(timeout = TIMEOUT)
    public void selectionSortIntegersTest06() {
        // reverse elements test
        element = new ArrayIntegers[7];
        element[0] = new ArrayIntegers(-1);  //  value @ index 0
        element[1] = new ArrayIntegers(-2); //   value @ index 1
        element[2] = new ArrayIntegers(-3);
        element[3] = new ArrayIntegers(-6);
        element[4] = new ArrayIntegers(7);
        element[5] = new ArrayIntegers(9);
        element[6] = new ArrayIntegers(12);

        compInt = ArrayIntegers.getValueComparator();

        elementSorted = new ArrayIntegers[7];
        elementSorted[0] = new ArrayIntegers(-6);
        elementSorted[1] = new ArrayIntegers(-3);
        elementSorted[2] = new ArrayIntegers(-2);
        elementSorted[3] = new ArrayIntegers(-1);
        elementSorted[4] = new ArrayIntegers(7);
        elementSorted[5] = new ArrayIntegers(9);
        elementSorted[6] = new ArrayIntegers(12);

        Sorting.selectionSort(element, compInt);
        assertArrayEquals(elementSorted, element);
        //System.out.println("Number of comparisons: " + compInt.getCount());
        assertTrue("Number of comparisons: " + compInt.getCount(),
                compInt.getCount() == 21);
    }

    @Test(timeout = TIMEOUT)
    public void selectionSortIntegersTest07() {
        // mixed elements test
        element = new ArrayIntegers[8];
        element[0] = new ArrayIntegers(1);  //  value @ index 0
        element[1] = new ArrayIntegers(-2); //   value @ index 1
        element[2] = new ArrayIntegers(3);
        element[3] = new ArrayIntegers(-6);
        element[4] = new ArrayIntegers(7);
        element[5] = new ArrayIntegers(-9);
        element[6] = new ArrayIntegers(12);
        element[7] = new ArrayIntegers(-15);
        //element[8] = new ArrayIntegers(15);

        compInt = ArrayIntegers.getValueComparator();

        elementSorted = new ArrayIntegers[8];
        elementSorted[0] = new ArrayIntegers(-15);
        elementSorted[1] = new ArrayIntegers(-9);
        elementSorted[2] = new ArrayIntegers(-6);
        elementSorted[3] = new ArrayIntegers(-2);
        elementSorted[4] = new ArrayIntegers(1);
        elementSorted[5] = new ArrayIntegers(3);
        elementSorted[6] = new ArrayIntegers(7);
        elementSorted[7] = new ArrayIntegers(12);
        //elementSorted[8] = new ArrayIntegers(15);

        Sorting.selectionSort(element, compInt);
        assertArrayEquals(elementSorted, element);
        //System.out.println("Number of comparisons: " + compInt.getCount());
        assertTrue("Number of comparisons: " + compInt.getCount(),
                compInt.getCount() == 28);
    }

    @Test(timeout = TIMEOUT)
    public void selectionSortIntegersTest08() {
        // Another test to check something else (This is one of those tests I created to check for just peace of mind)

        element = new ArrayIntegers[9];
        element[0] = new ArrayIntegers(1);  //  value @ index 0
        element[1] = new ArrayIntegers(-2); //   value @ index 1
        element[2] = new ArrayIntegers(3);
        element[3] = new ArrayIntegers(-6);
        element[4] = new ArrayIntegers(7);
        element[5] = new ArrayIntegers(-9);
        element[6] = new ArrayIntegers(12);
        element[7] = new ArrayIntegers(-15);
        element[8] = new ArrayIntegers(15);

        compInt = ArrayIntegers.getValueComparator();

        elementSorted = new ArrayIntegers[9];
        elementSorted[0] = new ArrayIntegers(-15);
        elementSorted[1] = new ArrayIntegers(-9);
        elementSorted[2] = new ArrayIntegers(-6);
        elementSorted[3] = new ArrayIntegers(-2);
        elementSorted[4] = new ArrayIntegers(1);
        elementSorted[5] = new ArrayIntegers(3);
        elementSorted[6] = new ArrayIntegers(7);
        elementSorted[7] = new ArrayIntegers(12);
        elementSorted[8] = new ArrayIntegers(15);

        Sorting.selectionSort(element, compInt);
        assertArrayEquals(elementSorted, element);
        //System.out.println("Number of comparisons: " + compInt.getCount());
        assertTrue("Number of comparisons: " + compInt.getCount(),
                compInt.getCount() == 36);
    }

    @Test(timeout = TIMEOUT)
    public void selectionSortIntegersTest09() {
        // empty elements test
        element = new ArrayIntegers[0];
        compInt = ArrayIntegers.getValueComparator();

        elementSorted = new ArrayIntegers[0];

        Sorting.selectionSort(element, compInt);
        assertArrayEquals(elementSorted, element);
        //System.out.println("Number of comparisons: " + compInt.getCount());
        assertTrue("Number of comparisons: " + compInt.getCount(),
                compInt.getCount() == 0);
    }

    @Test (timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void selectionSortArrayException() {
        element = new ArrayIntegers[8];
        element[0] = new ArrayIntegers(1);
        compInt = ArrayIntegers.getValueComparator();

        Sorting.selectionSort(null, compInt);
    }

    @Test (timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void selectionSortComparatorException() {
        element = new ArrayIntegers[8];
        element[0] = new ArrayIntegers(1);
        compInt = ArrayIntegers.getValueComparator();

        Sorting.selectionSort(element, null);
    }






    //--------------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------------
    // |||||||||||||||||||||||        CockTail SORT TESTS      ||||||||||||||||||||||||||||
    //--------------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------------


    @Test(timeout = TIMEOUT)
    public void cocktailSortString00() {
        Sorting.cocktailSort(pioneers, compName);
        assertArrayEquals(pioneersSorted, pioneers);
        System.out.println("Number of comparisons: " + compName.getCount());
        assertTrue("Number of comparisons: " + compName.getCount(),
                compName.getCount() <= 41 && compName.getCount() != 0);
    }


    @Test(timeout = TIMEOUT)
    public void cocktailSortIntegersTest01() {
        // single element test
        ArrayIntegers[] singleElement = new ArrayIntegers[1];
        singleElement[0] = new ArrayIntegers(0);  // single value
        compInt = ArrayIntegers.getValueComparator();

        ArrayIntegers[] singleElementSorted = new ArrayIntegers[1];
        singleElementSorted[0] = new ArrayIntegers(0);

        Sorting.cocktailSort(singleElement, compInt);
        assertArrayEquals(singleElementSorted, singleElement);
        //System.out.println("Number of comparisons: " + compInt.getCount());
        assertTrue("Number of comparisons: " + compInt.getCount(),
                compInt.getCount() == 0);
    }

    @Test(timeout = TIMEOUT)
    public void cocktailSortIntegersTest02() {
        // two element test
        element = new ArrayIntegers[2];
        element[0] = new ArrayIntegers(2);  //  value @ index 0
        element[1] = new ArrayIntegers(1); //   value @ index 1

        compInt = ArrayIntegers.getValueComparator();

        elementSorted = new ArrayIntegers[2];
        elementSorted[0] = new ArrayIntegers(1);
        elementSorted[1] = new ArrayIntegers(2);


        Sorting.cocktailSort(element, compInt);
        assertArrayEquals(elementSorted, element);
        //System.out.println("Number of comparisons: " + compInt.getCount());
        assertTrue("Number of comparisons: " + compInt.getCount(),
                compInt.getCount() == 1);
    }


    @Test(timeout = TIMEOUT)
    public void cocktailSortIntegersTest03() {
        // Multiple positive elements test
        element = new ArrayIntegers[7];
        element[0] = new ArrayIntegers(2);  //  value @ index 0
        element[1] = new ArrayIntegers(1); //   value @ index 1
        element[2] = new ArrayIntegers(3);
        element[3] = new ArrayIntegers(6);
        element[4] = new ArrayIntegers(9);
        element[5] = new ArrayIntegers(7);
        element[6] = new ArrayIntegers(12);

        compInt = ArrayIntegers.getValueComparator();

        elementSorted = new ArrayIntegers[7];
        elementSorted[0] = new ArrayIntegers(1);
        elementSorted[1] = new ArrayIntegers(2);
        elementSorted[2] = new ArrayIntegers(3);
        elementSorted[3] = new ArrayIntegers(6);
        elementSorted[4] = new ArrayIntegers(7);
        elementSorted[5] = new ArrayIntegers(9);
        elementSorted[6] = new ArrayIntegers(12);

        Sorting.cocktailSort(element, compInt);
        assertArrayEquals(elementSorted, element);
        //System.out.println("Number of comparisons: " + compInt.getCount());
        assertTrue("Number of comparisons: " + compInt.getCount(),
                compInt.getCount() == 10);
    }


    @Test(timeout = TIMEOUT)
    public void cocktailSortIntegersTest04() {
        // Multiple negative elements test
        element = new ArrayIntegers[7];
        element[0] = new ArrayIntegers(-2);  //  value @ index 0
        element[1] = new ArrayIntegers(-1); //   value @ index 1
        element[2] = new ArrayIntegers(-3);
        element[3] = new ArrayIntegers(-6);
        element[4] = new ArrayIntegers(-9);
        element[5] = new ArrayIntegers(-7);
        element[6] = new ArrayIntegers(-12);

        compInt = ArrayIntegers.getValueComparator();

        elementSorted = new ArrayIntegers[7];
        elementSorted[0] = new ArrayIntegers(-12);
        elementSorted[1] = new ArrayIntegers(-9);
        elementSorted[2] = new ArrayIntegers(-7);
        elementSorted[3] = new ArrayIntegers(-6);
        elementSorted[4] = new ArrayIntegers(-3);
        elementSorted[5] = new ArrayIntegers(-2);
        elementSorted[6] = new ArrayIntegers(-1);

        Sorting.cocktailSort(element, compInt);
        assertArrayEquals(elementSorted, element);
        //System.out.println("Number of comparisons: " + compInt.getCount());
        assertTrue("Number of comparisons: " + compInt.getCount(),
                compInt.getCount() == 21);
    }

    @Test(timeout = TIMEOUT)
    public void cocktailSortIntegersTest05() {
        // Already sorted elements test
        element = new ArrayIntegers[7];
        element[0] = new ArrayIntegers(1);  //  value @ index 0
        element[1] = new ArrayIntegers(2); //   value @ index 1
        element[2] = new ArrayIntegers(3);
        element[3] = new ArrayIntegers(6);
        element[4] = new ArrayIntegers(7);
        element[5] = new ArrayIntegers(9);
        element[6] = new ArrayIntegers(12);

        compInt = ArrayIntegers.getValueComparator();

        elementSorted = new ArrayIntegers[7];
        elementSorted[0] = new ArrayIntegers(1);
        elementSorted[1] = new ArrayIntegers(2);
        elementSorted[2] = new ArrayIntegers(3);
        elementSorted[3] = new ArrayIntegers(6);
        elementSorted[4] = new ArrayIntegers(7);
        elementSorted[5] = new ArrayIntegers(9);
        elementSorted[6] = new ArrayIntegers(12);

        Sorting.cocktailSort(element, compInt);
        assertArrayEquals(elementSorted, element);
        //System.out.println("Number of comparisons: " + compInt.getCount());
        assertTrue("Number of comparisons: " + compInt.getCount(),
                compInt.getCount() == 6);
    }

    @Test(timeout = TIMEOUT)
    public void cocktailSortIntegersTest06() {
        // reverse elements test
        element = new ArrayIntegers[7];
        element[0] = new ArrayIntegers(-1);  //  value @ index 0
        element[1] = new ArrayIntegers(-2); //   value @ index 1
        element[2] = new ArrayIntegers(-3);
        element[3] = new ArrayIntegers(-6);
        element[4] = new ArrayIntegers(7);
        element[5] = new ArrayIntegers(9);
        element[6] = new ArrayIntegers(12);

        compInt = ArrayIntegers.getValueComparator();

        elementSorted = new ArrayIntegers[7];
        elementSorted[0] = new ArrayIntegers(-6);
        elementSorted[1] = new ArrayIntegers(-3);
        elementSorted[2] = new ArrayIntegers(-2);
        elementSorted[3] = new ArrayIntegers(-1);
        elementSorted[4] = new ArrayIntegers(7);
        elementSorted[5] = new ArrayIntegers(9);
        elementSorted[6] = new ArrayIntegers(12);

        Sorting.cocktailSort(element, compInt);
        assertArrayEquals(elementSorted, element);
        //System.out.println("Number of comparisons: " + compInt.getCount());
        assertTrue("Number of comparisons: " + compInt.getCount(),
                compInt.getCount() == 9);
    }

    @Test(timeout = TIMEOUT)
    public void cocktailSortIntegersTest07() {
        // mixed elements test
        element = new ArrayIntegers[8];
        element[0] = new ArrayIntegers(1);  //  value @ index 0
        element[1] = new ArrayIntegers(-2); //   value @ index 1
        element[2] = new ArrayIntegers(3);
        element[3] = new ArrayIntegers(-6);
        element[4] = new ArrayIntegers(7);
        element[5] = new ArrayIntegers(-9);
        element[6] = new ArrayIntegers(12);
        element[7] = new ArrayIntegers(-15);
        //element[8] = new ArrayIntegers(15);

        compInt = ArrayIntegers.getValueComparator();

        elementSorted = new ArrayIntegers[8];
        elementSorted[0] = new ArrayIntegers(-15);
        elementSorted[1] = new ArrayIntegers(-9);
        elementSorted[2] = new ArrayIntegers(-6);
        elementSorted[3] = new ArrayIntegers(-2);
        elementSorted[4] = new ArrayIntegers(1);
        elementSorted[5] = new ArrayIntegers(3);
        elementSorted[6] = new ArrayIntegers(7);
        elementSorted[7] = new ArrayIntegers(12);
        //elementSorted[8] = new ArrayIntegers(15);

        Sorting.cocktailSort(element, compInt);
        assertArrayEquals(elementSorted, element);
        //System.out.println("Number of comparisons: " + compInt.getCount());
        assertTrue("Number of comparisons: " + compInt.getCount(),
                compInt.getCount() == 23);
    }

    @Test(timeout = TIMEOUT)
    public void cocktailSortIntegersTest08() {
        // Another test to check something else (This is one of those tests I created to check sorting algorithm
        // for just peace of mind)

        element = new ArrayIntegers[9];
        element[0] = new ArrayIntegers(1);  //  value @ index 0
        element[1] = new ArrayIntegers(-2); //   value @ index 1
        element[2] = new ArrayIntegers(3);
        element[3] = new ArrayIntegers(-6);
        element[4] = new ArrayIntegers(7);
        element[5] = new ArrayIntegers(-9);
        element[6] = new ArrayIntegers(12);
        element[7] = new ArrayIntegers(-15);
        element[8] = new ArrayIntegers(15);

        compInt = ArrayIntegers.getValueComparator();

        elementSorted = new ArrayIntegers[9];
        elementSorted[0] = new ArrayIntegers(-15);
        elementSorted[1] = new ArrayIntegers(-9);
        elementSorted[2] = new ArrayIntegers(-6);
        elementSorted[3] = new ArrayIntegers(-2);
        elementSorted[4] = new ArrayIntegers(1);
        elementSorted[5] = new ArrayIntegers(3);
        elementSorted[6] = new ArrayIntegers(7);
        elementSorted[7] = new ArrayIntegers(12);
        elementSorted[8] = new ArrayIntegers(15);

        Sorting.cocktailSort(element, compInt);
        assertArrayEquals(elementSorted, element);
        //System.out.println("Number of comparisons: " + compInt.getCount());
        assertTrue("Number of comparisons: " + compInt.getCount(),
                compInt.getCount() == 24);
    }

    @Test(timeout = TIMEOUT)
    public void cocktailSortIntegersTest09() {
        // empty elements test
        element = new ArrayIntegers[0];
        compInt = ArrayIntegers.getValueComparator();

        elementSorted = new ArrayIntegers[0];

        Sorting.cocktailSort(element, compInt);
        assertArrayEquals(elementSorted, element);
        //System.out.println("Number of comparisons: " + compInt.getCount());
        assertTrue("Number of comparisons: " + compInt.getCount(),
                compInt.getCount() == 0);
    }

    // ------------------------------- Order Tests for CockTail sort -----------------------------------------


    @Test(timeout = TIMEOUT)
    public void cocktailSortIntegersTest10() {
        // two element order test
        element = new ArrayIntegers[2];
        element[0] = new ArrayIntegers(1, 'a');  //  value @ index 0
        element[1] = new ArrayIntegers(1, 'b'); //   value @ index 1

        compInt = ArrayIntegers.getValueComparator();

        elementSorted = new ArrayIntegers[2];
        elementSorted[0] = new ArrayIntegers(1);
        elementSorted[0].setOrder('a');
        elementSorted[1] = new ArrayIntegers(1);
        elementSorted[1].setOrder('b');

        Sorting.cocktailSort(element, compInt);
        assertArrayEquals(elementSorted, element);
        //System.out.println("Number of comparisons: " + compInt.getCount());
        assertTrue("Number of comparisons: " + compInt.getCount(),
                compInt.getCount() == 1);


        // reverse two elements
        element = new ArrayIntegers[2];
        element[0] = new ArrayIntegers(1, 'b');  //  value @ index 0
        element[1] = new ArrayIntegers(1, 'a'); //   value @ index 1

        compInt = ArrayIntegers.getValueComparator();

        elementSorted = new ArrayIntegers[2];
        elementSorted[0] = new ArrayIntegers(1);
        elementSorted[0].setOrder('b');
        elementSorted[1] = new ArrayIntegers(1);
        elementSorted[1].setOrder('a');

        Sorting.cocktailSort(element, compInt);
        assertArrayEquals(elementSorted, element);
        //System.out.println("Number of comparisons: " + compInt.getCount());
        assertTrue("Number of comparisons: " + compInt.getCount(),
                compInt.getCount() == 1);

    }

    @Test(timeout = TIMEOUT)
    public void cocktailSortIntegersTest11() {
        // Multiple positive elements order test
        element = new ArrayIntegers[7];
        element[0] = new ArrayIntegers(2);  //  value @ index 0
        element[1] = new ArrayIntegers(1); //   value @ index 1
        element[2] = new ArrayIntegers(3, 'a');
        element[3] = new ArrayIntegers(3, 'b');
        element[4] = new ArrayIntegers(12, 'a');
        element[5] = new ArrayIntegers(3, 'c');
        element[6] = new ArrayIntegers(12, 'b');

        compInt = ArrayIntegers.getValueComparator();

        elementSorted = new ArrayIntegers[7];
        elementSorted[0] = new ArrayIntegers(1);
        elementSorted[1] = new ArrayIntegers(2);
        elementSorted[2] = new ArrayIntegers(3, 'a');
        elementSorted[3] = new ArrayIntegers(3, 'b');
        elementSorted[4] = new ArrayIntegers(3, 'c');
        elementSorted[5] = new ArrayIntegers(12, 'a');
        elementSorted[6] = new ArrayIntegers(12, 'b');

        Sorting.cocktailSort(element, compInt);
        assertArrayEquals(elementSorted, element);
        //System.out.println("Number of comparisons: " + compInt.getCount());
        assertTrue("Number of comparisons: " + compInt.getCount(),
                compInt.getCount() == 10);

        // mix the order letters. The letter that comes first should be first in the sorted order

        element = new ArrayIntegers[7];
        element[0] = new ArrayIntegers(2);  //  value @ index 0
        element[1] = new ArrayIntegers(1); //   value @ index 1
        element[2] = new ArrayIntegers(3, 'b');
        element[3] = new ArrayIntegers(3, 'c');
        element[4] = new ArrayIntegers(12, 'b');
        element[5] = new ArrayIntegers(3, 'a');
        element[6] = new ArrayIntegers(12, 'a');

        compInt = ArrayIntegers.getValueComparator();

        elementSorted = new ArrayIntegers[7];
        elementSorted[0] = new ArrayIntegers(1);
        elementSorted[1] = new ArrayIntegers(2);
        elementSorted[2] = new ArrayIntegers(3, 'b');
        elementSorted[3] = new ArrayIntegers(3, 'c');
        elementSorted[4] = new ArrayIntegers(3, 'a');
        elementSorted[5] = new ArrayIntegers(12, 'b');
        elementSorted[6] = new ArrayIntegers(12, 'a');

        Sorting.cocktailSort(element, compInt);
        assertArrayEquals(elementSorted, element);
        //System.out.println("Number of comparisons: " + compInt.getCount());
        assertTrue("Number of comparisons: " + compInt.getCount(),
                compInt.getCount() == 10);
    }

    @Test(timeout = TIMEOUT)
    public void cocktailSortIntegersTest12() {
        // Multiple negative elements test
        element = new ArrayIntegers[7];
        element[0] = new ArrayIntegers(-2, 'a');  //  value @ index 0
        element[1] = new ArrayIntegers(-2, 'b'); //   value @ index 1
        element[2] = new ArrayIntegers(-3, 'a');
        element[3] = new ArrayIntegers(-6, 'a');
        element[4] = new ArrayIntegers(-6, 'b');
        element[5] = new ArrayIntegers(-6, 'c');
        element[6] = new ArrayIntegers(-3, 'b');

        compInt = ArrayIntegers.getValueComparator();

        elementSorted = new ArrayIntegers[7];
        elementSorted[0] = new ArrayIntegers(-6, 'a');
        elementSorted[1] = new ArrayIntegers(-6, 'b');
        elementSorted[2] = new ArrayIntegers(-6, 'c');
        elementSorted[3] = new ArrayIntegers(-3, 'a');
        elementSorted[4] = new ArrayIntegers(-3, 'b');
        elementSorted[5] = new ArrayIntegers(-2, 'a');
        elementSorted[6] = new ArrayIntegers(-2, 'b');

        Sorting.cocktailSort(element, compInt);
        assertArrayEquals(elementSorted, element);
        //System.out.println("Number of comparisons: " + compInt.getCount());
        assertTrue("Number of comparisons: " + compInt.getCount(),
                compInt.getCount() == 20);
    }


    @Test(timeout = TIMEOUT)
    public void cocktailSortIntegersTest13() {
        // Already sorted elements order test
        element = new ArrayIntegers[7];
        element[0] = new ArrayIntegers(1);  //  value @ index 0
        element[1] = new ArrayIntegers(2); //   value @ index 1
        element[2] = new ArrayIntegers(3, 'a');
        element[3] = new ArrayIntegers(6);
        element[4] = new ArrayIntegers(3, 'b');
        element[5] = new ArrayIntegers(9);
        element[6] = new ArrayIntegers(12);

        compInt = ArrayIntegers.getValueComparator();

        elementSorted = new ArrayIntegers[7];
        elementSorted[0] = new ArrayIntegers(1);
        elementSorted[1] = new ArrayIntegers(2);
        elementSorted[2] = new ArrayIntegers(3, 'a');
        elementSorted[3] = new ArrayIntegers(3, 'b');
        elementSorted[4] = new ArrayIntegers(6);
        elementSorted[5] = new ArrayIntegers(9);
        elementSorted[6] = new ArrayIntegers(12);

        Sorting.cocktailSort(element, compInt);
        assertArrayEquals(elementSorted, element);
        //System.out.println("Number of comparisons: " + compInt.getCount());
        assertTrue("Number of comparisons: " + compInt.getCount(),
                compInt.getCount() == 9);
    }

    @Test(timeout = TIMEOUT)
    public void cocktailSortIntegersTest14() {
        // reverse elements order test
        element = new ArrayIntegers[7];
        element[0] = new ArrayIntegers(-1);  //  value @ index 0
        element[1] = new ArrayIntegers(-2); //   value @ index 1
        element[2] = new ArrayIntegers(-3);
        element[3] = new ArrayIntegers(-6);
        element[4] = new ArrayIntegers(7);
        element[5] = new ArrayIntegers(12, 'a');
        element[6] = new ArrayIntegers(12, 'b');

        compInt = ArrayIntegers.getValueComparator();

        elementSorted = new ArrayIntegers[7];
        elementSorted[0] = new ArrayIntegers(-6);
        elementSorted[1] = new ArrayIntegers(-3);
        elementSorted[2] = new ArrayIntegers(-2);
        elementSorted[3] = new ArrayIntegers(-1);
        elementSorted[4] = new ArrayIntegers(7);
        elementSorted[5] = new ArrayIntegers(12, 'a');
        elementSorted[6] = new ArrayIntegers(12, 'b');

        Sorting.cocktailSort(element, compInt);
        assertArrayEquals(elementSorted, element);
        //System.out.println("Number of comparisons: " + compInt.getCount());
        assertTrue("Number of comparisons: " + compInt.getCount(),
                compInt.getCount() == 9);
    }


    @Test(timeout = TIMEOUT)
    public void cocktailSortIntegersTest15() {
        // mixed elements order test
        element = new ArrayIntegers[8];
        element[0] = new ArrayIntegers(-2, 'b');  //  value @ index 0
        element[1] = new ArrayIntegers(-2, 'a'); //   value @ index 1
        element[2] = new ArrayIntegers(3);
        element[3] = new ArrayIntegers(-6);
        element[4] = new ArrayIntegers(7);
        element[5] = new ArrayIntegers(-9);
        element[6] = new ArrayIntegers(12);
        element[7] = new ArrayIntegers(-15);
        //element[8] = new ArrayIntegers(15);

        compInt = ArrayIntegers.getValueComparator();

        elementSorted = new ArrayIntegers[8];
        elementSorted[0] = new ArrayIntegers(-15);
        elementSorted[1] = new ArrayIntegers(-9);
        elementSorted[2] = new ArrayIntegers(-6);
        elementSorted[3] = new ArrayIntegers(-2, 'b');
        elementSorted[4] = new ArrayIntegers(-2, 'a');
        elementSorted[5] = new ArrayIntegers(3);
        elementSorted[6] = new ArrayIntegers(7);
        elementSorted[7] = new ArrayIntegers(12);
        //elementSorted[8] = new ArrayIntegers(15);

        Sorting.cocktailSort(element, compInt);
        assertArrayEquals(elementSorted, element);
        //System.out.println("Number of comparisons: " + compInt.getCount());
        assertTrue("Number of comparisons: " + compInt.getCount(),
                compInt.getCount() == 23);
    }

    @Test(timeout = TIMEOUT)
    public void cocktailSortIntegersTest16() {
        // Another test to check something else (This is one of those tests I created to check for just peace of mind)

        element = new ArrayIntegers[9];
        element[0] = new ArrayIntegers(1, 'a');  //  value @ index 0
        element[1] = new ArrayIntegers(1, 'b'); //   value @ index 1
        element[2] = new ArrayIntegers(3, 'a');
        element[3] = new ArrayIntegers(-6);
        element[4] = new ArrayIntegers(7);
        element[5] = new ArrayIntegers(-15, 'a');
        element[6] = new ArrayIntegers(3, 'b');
        element[7] = new ArrayIntegers(-15, 'b');
        element[8] = new ArrayIntegers(15);

        compInt = ArrayIntegers.getValueComparator();

        Sorting.cocktailSort(element, compInt);

        elementSorted = new ArrayIntegers[9];
        elementSorted[0] = new ArrayIntegers(-15, 'a');
        elementSorted[1] = new ArrayIntegers(-15, 'b');
        elementSorted[2] = new ArrayIntegers(-6);
        elementSorted[3] = new ArrayIntegers(1, 'a');
        elementSorted[4] = new ArrayIntegers(1, 'b');
        elementSorted[5] = new ArrayIntegers(3, 'a');
        elementSorted[6] = new ArrayIntegers(3, 'b');
        elementSorted[7] = new ArrayIntegers(7);
        elementSorted[8] = new ArrayIntegers(15);

        assertArrayEquals(elementSorted, element);
        //System.out.println("Number of comparisons: " + compInt.getCount());
        assertTrue("Number of comparisons: " + compInt.getCount(),
                compInt.getCount() == 24);
    }


    @Test (timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void cocktailSortArrayException() {
        element = new ArrayIntegers[8];
        element[0] = new ArrayIntegers(1);
        compInt = ArrayIntegers.getValueComparator();

        Sorting.cocktailSort(null, compInt);
    }

    @Test (timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void cocktailSortComparatorException() {
        element = new ArrayIntegers[8];
        element[0] = new ArrayIntegers(1);
        compInt = ArrayIntegers.getValueComparator();

        Sorting.cocktailSort(element, null);
    }






    //--------------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------------
    // |||||||||||||||||||||||        MergeSort SORT TESTS      ||||||||||||||||||||||||||||
    //--------------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------------




    @Test(timeout = TIMEOUT)
    public void mergeSortString() {
        Sorting.mergeSort(pioneers, compName);
        assertArrayEquals(pioneersSorted, pioneers);
        System.out.println("Number of comparisons: " + compName.getCount());
        assertTrue("Number of comparisons: " + compName.getCount(),
                compName.getCount() <= 32 && compName.getCount() != 0);
    }


    @Test(timeout = TIMEOUT)
    public void mergeSortIntegersTest01() {
        // single element test
        ArrayIntegers[] singleElement = new ArrayIntegers[1];
        singleElement[0] = new ArrayIntegers(0);  // single value
        compInt = ArrayIntegers.getValueComparator();

        ArrayIntegers[] singleElementSorted = new ArrayIntegers[1];
        singleElementSorted[0] = new ArrayIntegers(0);

        Sorting.mergeSort(singleElement, compInt);
        assertArrayEquals(singleElementSorted, singleElement);
        //System.out.println("Number of comparisons: " + compInt.getCount());
        assertTrue("Number of comparisons: " + compInt.getCount(),
                compInt.getCount() == 0);
    }

    @Test(timeout = TIMEOUT)
    public void mergeSortIntegersTest02() {
        // two element test
        element = new ArrayIntegers[2];
        element[0] = new ArrayIntegers(2);  //  value @ index 0
        element[1] = new ArrayIntegers(1); //   value @ index 1

        compInt = ArrayIntegers.getValueComparator();

        elementSorted = new ArrayIntegers[2];
        elementSorted[0] = new ArrayIntegers(1);
        elementSorted[1] = new ArrayIntegers(2);


        Sorting.mergeSort(element, compInt);
        assertArrayEquals(elementSorted, element);
        //System.out.println("Number of comparisons: " + compInt.getCount());
        assertTrue("Number of comparisons: " + compInt.getCount(),
                compInt.getCount() == 1);
    }


    @Test(timeout = TIMEOUT)
    public void mergeSortIntegersTest03() {
        // Multiple positive elements test
        element = new ArrayIntegers[7];
        element[0] = new ArrayIntegers(2);  //  value @ index 0
        element[1] = new ArrayIntegers(1); //   value @ index 1
        element[2] = new ArrayIntegers(3);
        element[3] = new ArrayIntegers(6);
        element[4] = new ArrayIntegers(9); // 2, 1, 3, 6, 9, 7, 12
        element[5] = new ArrayIntegers(7);
        element[6] = new ArrayIntegers(12);

        compInt = ArrayIntegers.getValueComparator();

        elementSorted = new ArrayIntegers[7];
        elementSorted[0] = new ArrayIntegers(1);
        elementSorted[1] = new ArrayIntegers(2);
        elementSorted[2] = new ArrayIntegers(3);
        elementSorted[3] = new ArrayIntegers(6);
        elementSorted[4] = new ArrayIntegers(7);
        elementSorted[5] = new ArrayIntegers(9);
        elementSorted[6] = new ArrayIntegers(12);

        Sorting.mergeSort(element, compInt);
        assertArrayEquals(elementSorted, element);
        //System.out.println("Number of comparisons: " + compInt.getCount());
        assertTrue("Number of comparisons: " + compInt.getCount(),
                compInt.getCount() == 11);
    }


    @Test(timeout = TIMEOUT)
    public void mergeSortIntegersTest04() {
        // Multiple negative elements test
        element = new ArrayIntegers[7];
        element[0] = new ArrayIntegers(-2);  //  value @ index 0
        element[1] = new ArrayIntegers(-1); //   value @ index 1
        element[2] = new ArrayIntegers(-3);
        element[3] = new ArrayIntegers(-6);
        element[4] = new ArrayIntegers(-9);
        element[5] = new ArrayIntegers(-7);
        element[6] = new ArrayIntegers(-12);

        compInt = ArrayIntegers.getValueComparator();

        elementSorted = new ArrayIntegers[7];
        elementSorted[0] = new ArrayIntegers(-12);
        elementSorted[1] = new ArrayIntegers(-9);
        elementSorted[2] = new ArrayIntegers(-7);
        elementSorted[3] = new ArrayIntegers(-6);
        elementSorted[4] = new ArrayIntegers(-3);
        elementSorted[5] = new ArrayIntegers(-2);
        elementSorted[6] = new ArrayIntegers(-1);

        Sorting.mergeSort(element, compInt);
        assertArrayEquals(elementSorted, element);
        //System.out.println("Number of comparisons: " + compInt.getCount());
        assertTrue("Number of comparisons: " + compInt.getCount(),
                compInt.getCount() == 12);
    }

    @Test(timeout = TIMEOUT)
    public void mergeSortIntegersTest05() {
        // Already sorted elements test
        element = new ArrayIntegers[7];
        element[0] = new ArrayIntegers(1);  //  value @ index 0
        element[1] = new ArrayIntegers(2); //   value @ index 1
        element[2] = new ArrayIntegers(3);
        element[3] = new ArrayIntegers(6);
        element[4] = new ArrayIntegers(7); // 1, 2, 3, 6, 7, 9, 12
        element[5] = new ArrayIntegers(9);
        element[6] = new ArrayIntegers(12);

        compInt = ArrayIntegers.getValueComparator();

        elementSorted = new ArrayIntegers[7];
        elementSorted[0] = new ArrayIntegers(1);
        elementSorted[1] = new ArrayIntegers(2);
        elementSorted[2] = new ArrayIntegers(3);
        elementSorted[3] = new ArrayIntegers(6);
        elementSorted[4] = new ArrayIntegers(7);
        elementSorted[5] = new ArrayIntegers(9);
        elementSorted[6] = new ArrayIntegers(12);

        Sorting.mergeSort(element, compInt);
        assertArrayEquals(elementSorted, element);
        //System.out.println("Number of comparisons: " + compInt.getCount());
        assertTrue("Number of comparisons: " + compInt.getCount(),
                compInt.getCount() == 9);
    }

    @Test(timeout = TIMEOUT)
    public void mergeSortIntegersTest06() {
        // reverse elements test
        element = new ArrayIntegers[7];
        element[0] = new ArrayIntegers(-1);  //  value @ index 0
        element[1] = new ArrayIntegers(-2); //   value @ index 1
        element[2] = new ArrayIntegers(-3);
        element[3] = new ArrayIntegers(-6);
        element[4] = new ArrayIntegers(7);
        element[5] = new ArrayIntegers(9);
        element[6] = new ArrayIntegers(12);

        compInt = ArrayIntegers.getValueComparator();

        elementSorted = new ArrayIntegers[7];
        elementSorted[0] = new ArrayIntegers(-6);
        elementSorted[1] = new ArrayIntegers(-3);
        elementSorted[2] = new ArrayIntegers(-2);
        elementSorted[3] = new ArrayIntegers(-1);
        elementSorted[4] = new ArrayIntegers(7);
        elementSorted[5] = new ArrayIntegers(9);
        elementSorted[6] = new ArrayIntegers(12);

        Sorting.mergeSort(element, compInt);
        assertArrayEquals(elementSorted, element);
        //System.out.println("Number of comparisons: " + compInt.getCount());
        assertTrue("Number of comparisons: " + compInt.getCount(),
                compInt.getCount() == 11);
    }

    @Test(timeout = TIMEOUT)
    public void mergeSortIntegersTest07() {
        // mixed elements test
        element = new ArrayIntegers[8];
        element[0] = new ArrayIntegers(1);  //  value @ index 0
        element[1] = new ArrayIntegers(-2); //   value @ index 1
        element[2] = new ArrayIntegers(3);
        element[3] = new ArrayIntegers(-6);
        element[4] = new ArrayIntegers(7);
        element[5] = new ArrayIntegers(-9);
        element[6] = new ArrayIntegers(12);
        element[7] = new ArrayIntegers(-15);
        //element[8] = new ArrayIntegers(15);

        compInt = ArrayIntegers.getValueComparator();

        elementSorted = new ArrayIntegers[8];
        elementSorted[0] = new ArrayIntegers(-15);
        elementSorted[1] = new ArrayIntegers(-9);
        elementSorted[2] = new ArrayIntegers(-6);
        elementSorted[3] = new ArrayIntegers(-2);
        elementSorted[4] = new ArrayIntegers(1);
        elementSorted[5] = new ArrayIntegers(3);
        elementSorted[6] = new ArrayIntegers(7);
        elementSorted[7] = new ArrayIntegers(12);
        //elementSorted[8] = new ArrayIntegers(15);

        Sorting.mergeSort(element, compInt);
        assertArrayEquals(elementSorted, element);
        //System.out.println("Number of comparisons: " + compInt.getCount());
        assertTrue("Number of comparisons: " + compInt.getCount(),
                compInt.getCount() == 16);
    }

    @Test(timeout = TIMEOUT)
    public void mergeSortIntegersTest08() {
        // Another test to check something else (This is one of those tests I created to check sorting algorithm
        // for just peace of mind)

        element = new ArrayIntegers[9];
        element[0] = new ArrayIntegers(1);  //  value @ index 0
        element[1] = new ArrayIntegers(-2); //   value @ index 1
        element[2] = new ArrayIntegers(3);
        element[3] = new ArrayIntegers(-6);
        element[4] = new ArrayIntegers(7);
        element[5] = new ArrayIntegers(-9);
        element[6] = new ArrayIntegers(12);
        element[7] = new ArrayIntegers(-15);
        element[8] = new ArrayIntegers(15);

        compInt = ArrayIntegers.getValueComparator();

        elementSorted = new ArrayIntegers[9];
        elementSorted[0] = new ArrayIntegers(-15);
        elementSorted[1] = new ArrayIntegers(-9);
        elementSorted[2] = new ArrayIntegers(-6);
        elementSorted[3] = new ArrayIntegers(-2);
        elementSorted[4] = new ArrayIntegers(1);
        elementSorted[5] = new ArrayIntegers(3);
        elementSorted[6] = new ArrayIntegers(7);
        elementSorted[7] = new ArrayIntegers(12);
        elementSorted[8] = new ArrayIntegers(15);

        Sorting.mergeSort(element, compInt);
        assertArrayEquals(elementSorted, element);
        //System.out.println("Number of comparisons: " + compInt.getCount());
        assertTrue("Number of comparisons: " + compInt.getCount(),
                compInt.getCount() == 18);
    }

    @Test(timeout = TIMEOUT)
    public void mergeSortIntegersTest09() {
        // empty elements test
        element = new ArrayIntegers[0];
        compInt = ArrayIntegers.getValueComparator();

        elementSorted = new ArrayIntegers[0];

        Sorting.mergeSort(element, compInt);
        assertArrayEquals(elementSorted, element);
        //System.out.println("Number of comparisons: " + compInt.getCount());
        assertTrue("Number of comparisons: " + compInt.getCount(),
                compInt.getCount() == 0);
    }

    // ------------------------------- Order Tests for Merge sort -----------------------------------------


    @Test(timeout = TIMEOUT)
    public void mergeSortIntegersTest10() {
        // two element order test
        element = new ArrayIntegers[2];
        element[0] = new ArrayIntegers(1, 'a');  //  value @ index 0
        element[1] = new ArrayIntegers(1, 'b'); //   value @ index 1

        compInt = ArrayIntegers.getValueComparator();

        elementSorted = new ArrayIntegers[2];
        elementSorted[0] = new ArrayIntegers(1);
        elementSorted[0].setOrder('a');
        elementSorted[1] = new ArrayIntegers(1);
        elementSorted[1].setOrder('b');

        Sorting.mergeSort(element, compInt);
        assertArrayEquals(elementSorted, element);
        //System.out.println("Number of comparisons: " + compInt.getCount());
        assertTrue("Number of comparisons: " + compInt.getCount(),
                compInt.getCount() == 1);


        // reverse two elements
        element = new ArrayIntegers[2];
        element[0] = new ArrayIntegers(1, 'b');  //  value @ index 0
        element[1] = new ArrayIntegers(1, 'a'); //   value @ index 1

        compInt = ArrayIntegers.getValueComparator();

        elementSorted = new ArrayIntegers[2];
        elementSorted[0] = new ArrayIntegers(1);
        elementSorted[0].setOrder('b');
        elementSorted[1] = new ArrayIntegers(1);
        elementSorted[1].setOrder('a');

        Sorting.mergeSort(element, compInt);
        assertArrayEquals(elementSorted, element);
        //System.out.println("Number of comparisons: " + compInt.getCount());
        assertTrue("Number of comparisons: " + compInt.getCount(),
                compInt.getCount() == 1);

    }

    @Test(timeout = TIMEOUT)
    public void mergeSortIntegersTest11() {
        // Multiple positive elements order test
        element = new ArrayIntegers[7];
        element[0] = new ArrayIntegers(2);  //  value @ index 0
        element[1] = new ArrayIntegers(1); //   value @ index 1
        element[2] = new ArrayIntegers(3, 'a');
        element[3] = new ArrayIntegers(3, 'b');
        element[4] = new ArrayIntegers(12, 'a');
        element[5] = new ArrayIntegers(3, 'c');
        element[6] = new ArrayIntegers(12, 'b');

        compInt = ArrayIntegers.getValueComparator();

        elementSorted = new ArrayIntegers[7];
        elementSorted[0] = new ArrayIntegers(1);
        elementSorted[1] = new ArrayIntegers(2);
        elementSorted[2] = new ArrayIntegers(3, 'a');
        elementSorted[3] = new ArrayIntegers(3, 'b');
        elementSorted[4] = new ArrayIntegers(3, 'c');
        elementSorted[5] = new ArrayIntegers(12, 'a');
        elementSorted[6] = new ArrayIntegers(12, 'b');

        Sorting.mergeSort(element, compInt);
        assertArrayEquals(elementSorted, element);
        //System.out.println("Number of comparisons: " + compInt.getCount());
        assertTrue("Number of comparisons: " + compInt.getCount(),
                compInt.getCount() == 11);

        // jumble the order letters. The letter that comes first should be first in the sorted order

        element = new ArrayIntegers[7];
        element[0] = new ArrayIntegers(2);  //  value @ index 0
        element[1] = new ArrayIntegers(1); //   value @ index 1
        element[2] = new ArrayIntegers(3, 'b');
        element[3] = new ArrayIntegers(3, 'c');
        element[4] = new ArrayIntegers(12, 'b');
        element[5] = new ArrayIntegers(3, 'a');
        element[6] = new ArrayIntegers(12, 'a');

        compInt = ArrayIntegers.getValueComparator();

        elementSorted = new ArrayIntegers[7];
        elementSorted[0] = new ArrayIntegers(1);
        elementSorted[1] = new ArrayIntegers(2);
        elementSorted[2] = new ArrayIntegers(3, 'b');
        elementSorted[3] = new ArrayIntegers(3, 'c');
        elementSorted[4] = new ArrayIntegers(3, 'a');
        elementSorted[5] = new ArrayIntegers(12, 'b');
        elementSorted[6] = new ArrayIntegers(12, 'a');

        Sorting.mergeSort(element, compInt);
        assertArrayEquals(elementSorted, element);
        //System.out.println("Number of comparisons: " + compInt.getCount());
        assertTrue("Number of comparisons: " + compInt.getCount(),
                compInt.getCount() == 11);
    }

    @Test(timeout = TIMEOUT)
    public void mergeSortIntegersTest12() {
        // Multiple negative elements order test
        element = new ArrayIntegers[7];
        element[0] = new ArrayIntegers(-2, 'a');  //  value @ index 0
        element[1] = new ArrayIntegers(-2, 'b'); //   value @ index 1
        element[2] = new ArrayIntegers(-3, 'a');
        element[3] = new ArrayIntegers(-6, 'a');
        element[4] = new ArrayIntegers(-6, 'b');
        element[5] = new ArrayIntegers(-6, 'c');
        element[6] = new ArrayIntegers(-3, 'b');

        compInt = ArrayIntegers.getValueComparator();

        elementSorted = new ArrayIntegers[7];
        elementSorted[0] = new ArrayIntegers(-6, 'a');
        elementSorted[1] = new ArrayIntegers(-6, 'b');
        elementSorted[2] = new ArrayIntegers(-6, 'c');
        elementSorted[3] = new ArrayIntegers(-3, 'a');
        elementSorted[4] = new ArrayIntegers(-3, 'b');
        elementSorted[5] = new ArrayIntegers(-2, 'a');
        elementSorted[6] = new ArrayIntegers(-2, 'b');

        Sorting.mergeSort(element, compInt);
        assertArrayEquals(elementSorted, element);
        //System.out.println("Number of comparisons: " + compInt.getCount());
        assertTrue("Number of comparisons: " + compInt.getCount(),
                compInt.getCount() == 12);
    }


    @Test(timeout = TIMEOUT)
    public void mergeSortIntegersTest13() {
        // Already sorted elements order test
        element = new ArrayIntegers[7];
        element[0] = new ArrayIntegers(1);  //  value @ index 0
        element[1] = new ArrayIntegers(2); //   value @ index 1
        element[2] = new ArrayIntegers(3, 'a');
        element[3] = new ArrayIntegers(6);
        element[4] = new ArrayIntegers(3, 'b');
        element[5] = new ArrayIntegers(9);
        element[6] = new ArrayIntegers(12);

        compInt = ArrayIntegers.getValueComparator();

        elementSorted = new ArrayIntegers[7];
        elementSorted[0] = new ArrayIntegers(1);
        elementSorted[1] = new ArrayIntegers(2);
        elementSorted[2] = new ArrayIntegers(3, 'a');
        elementSorted[3] = new ArrayIntegers(3, 'b');
        elementSorted[4] = new ArrayIntegers(6);
        elementSorted[5] = new ArrayIntegers(9);
        elementSorted[6] = new ArrayIntegers(12);

        Sorting.mergeSort(element, compInt);
        assertArrayEquals(elementSorted, element);
        //System.out.println("Number of comparisons: " + compInt.getCount());
        assertTrue("Number of comparisons: " + compInt.getCount(),
                compInt.getCount() == 9);
    }

    @Test(timeout = TIMEOUT)
    public void mergeSortIntegersTest14() {
        // reverse elements order test
        element = new ArrayIntegers[7];
        element[0] = new ArrayIntegers(-1);  //  value @ index 0
        element[1] = new ArrayIntegers(-2); //   value @ index 1
        element[2] = new ArrayIntegers(-3);
        element[3] = new ArrayIntegers(-6);
        element[4] = new ArrayIntegers(7);
        element[5] = new ArrayIntegers(12, 'a');
        element[6] = new ArrayIntegers(12, 'b');

        compInt = ArrayIntegers.getValueComparator();

        elementSorted = new ArrayIntegers[7];
        elementSorted[0] = new ArrayIntegers(-6);
        elementSorted[1] = new ArrayIntegers(-3);
        elementSorted[2] = new ArrayIntegers(-2);
        elementSorted[3] = new ArrayIntegers(-1);
        elementSorted[4] = new ArrayIntegers(7);
        elementSorted[5] = new ArrayIntegers(12, 'a');
        elementSorted[6] = new ArrayIntegers(12, 'b');

        Sorting.mergeSort(element, compInt);
        assertArrayEquals(elementSorted, element);
        //System.out.println("Number of comparisons: " + compInt.getCount());
        assertTrue("Number of comparisons: " + compInt.getCount(),
                compInt.getCount() == 11);
    }


    @Test(timeout = TIMEOUT)
    public void mergeSortIntegersTest15() {
        // mixed elements order test
        element = new ArrayIntegers[8];
        element[0] = new ArrayIntegers(-2, 'b');  //  value @ index 0
        element[1] = new ArrayIntegers(-2, 'a'); //   value @ index 1
        element[2] = new ArrayIntegers(3);
        element[3] = new ArrayIntegers(-6);
        element[4] = new ArrayIntegers(7);
        element[5] = new ArrayIntegers(-9);
        element[6] = new ArrayIntegers(12);
        element[7] = new ArrayIntegers(-15);
        //element[8] = new ArrayIntegers(15);

        compInt = ArrayIntegers.getValueComparator();

        elementSorted = new ArrayIntegers[8];
        elementSorted[0] = new ArrayIntegers(-15);
        elementSorted[1] = new ArrayIntegers(-9);
        elementSorted[2] = new ArrayIntegers(-6);
        elementSorted[3] = new ArrayIntegers(-2, 'b');
        elementSorted[4] = new ArrayIntegers(-2, 'a');
        elementSorted[5] = new ArrayIntegers(3);
        elementSorted[6] = new ArrayIntegers(7);
        elementSorted[7] = new ArrayIntegers(12);
        //elementSorted[8] = new ArrayIntegers(15);

        Sorting.mergeSort(element, compInt);
        assertArrayEquals(elementSorted, element);
        //System.out.println("Number of comparisons: " + compInt.getCount());
        assertTrue("Number of comparisons: " + compInt.getCount(),
                compInt.getCount() == 16);
    }

    @Test(timeout = TIMEOUT)
    public void mergeSortIntegersTest16() {
        // Another test to check something else (This is one of those tests I created to check for just peace of mind)

        element = new ArrayIntegers[9];
        element[0] = new ArrayIntegers(1, 'a');  //  value @ index 0
        element[1] = new ArrayIntegers(1, 'b'); //   value @ index 1
        element[2] = new ArrayIntegers(3, 'a');
        element[3] = new ArrayIntegers(-6);
        element[4] = new ArrayIntegers(7);
        element[5] = new ArrayIntegers(-15, 'a');
        element[6] = new ArrayIntegers(3, 'b');
        element[7] = new ArrayIntegers(-15, 'b');
        element[8] = new ArrayIntegers(15);

        compInt = ArrayIntegers.getValueComparator();

        Sorting.mergeSort(element, compInt);

        elementSorted = new ArrayIntegers[9];
        elementSorted[0] = new ArrayIntegers(-15, 'a');
        elementSorted[1] = new ArrayIntegers(-15, 'b');
        elementSorted[2] = new ArrayIntegers(-6);
        elementSorted[3] = new ArrayIntegers(1, 'a');
        elementSorted[4] = new ArrayIntegers(1, 'b');
        elementSorted[5] = new ArrayIntegers(3, 'a');
        elementSorted[6] = new ArrayIntegers(3, 'b');
        elementSorted[7] = new ArrayIntegers(7);
        elementSorted[8] = new ArrayIntegers(15);

        assertArrayEquals(elementSorted, element);
        //System.out.println("Number of comparisons: " + compInt.getCount());
        assertTrue("Number of comparisons: " + compInt.getCount(),
                compInt.getCount() == 19);
    }


    @Test (timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void mergeSortArrayException() {
        element = new ArrayIntegers[8];
        element[0] = new ArrayIntegers(1);
        compInt = ArrayIntegers.getValueComparator();

        Sorting.mergeSort(null, compInt);
    }

    @Test (timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void mergeSortComparatorException() {
        element = new ArrayIntegers[8];
        element[0] = new ArrayIntegers(1);
        compInt = ArrayIntegers.getValueComparator();

        Sorting.mergeSort(element, null);
    }




    //--------------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------------
    // |||||||||||||||||||||||        QuickSort SORT TESTS      ||||||||||||||||||||||||||||
    //--------------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------------

    @Test(timeout = TIMEOUT)
    public void quickSortStringTest00() {
        Sorting.quickSort(pioneers, compName, new Random(0b1010010001010101));
        assertArrayEquals(pioneersSorted, pioneers);
        System.out.println("Number of comparisons: " + compName.getCount());
        assertTrue("Number of comparisons: " + compName.getCount(),
                compName.getCount() <= 37 && compName.getCount() != 0);
    }

    @Test(timeout = TIMEOUT)
    public void quickSortIntegersTest01() {
        // single element test
        ArrayIntegers[] singleElement = new ArrayIntegers[1];
        singleElement[0] = new ArrayIntegers(0);  // single value
        compInt = ArrayIntegers.getValueComparator();

        ArrayIntegers[] singleElementSorted = new ArrayIntegers[1];
        singleElementSorted[0] = new ArrayIntegers(0);

        Sorting.quickSort(singleElement, compInt, new Random(0b1010010001010101));
        assertArrayEquals(singleElementSorted, singleElement);
        //System.out.println("Number of comparisons: " + compInt.getCount());
        assertTrue("Number of comparisons: " + compInt.getCount(),
                compInt.getCount() == 0);
    }

    @Test(timeout = TIMEOUT)
    public void quickSortIntegersTest02() {
        // two element test
        element = new ArrayIntegers[2];
        element[0] = new ArrayIntegers(2);  //  value @ index 0
        element[1] = new ArrayIntegers(1); //   value @ index 1

        compInt = ArrayIntegers.getValueComparator();

        elementSorted = new ArrayIntegers[2];
        elementSorted[0] = new ArrayIntegers(1);
        elementSorted[1] = new ArrayIntegers(2);


        Sorting.quickSort(element, compInt, new Random(0b1010010001010101));
        assertArrayEquals(elementSorted, element);
        //System.out.println("Number of comparisons: " + compInt.getCount());
        assertTrue("Number of comparisons: " + compInt.getCount(),
                compInt.getCount() <= 2 && compInt.getCount() != 0);
    }


    @Test(timeout = TIMEOUT)
    public void quickSortIntegersTest03() {
        // Multiple positive elements test
        element = new ArrayIntegers[7];
        element[0] = new ArrayIntegers(2);  //  value @ index 0
        element[1] = new ArrayIntegers(1); //   value @ index 1
        element[2] = new ArrayIntegers(3);
        element[3] = new ArrayIntegers(6);
        element[4] = new ArrayIntegers(9);
        element[5] = new ArrayIntegers(7);
        element[6] = new ArrayIntegers(12);

        compInt = ArrayIntegers.getValueComparator();

        elementSorted = new ArrayIntegers[7];
        elementSorted[0] = new ArrayIntegers(1);
        elementSorted[1] = new ArrayIntegers(2);
        elementSorted[2] = new ArrayIntegers(3);
        elementSorted[3] = new ArrayIntegers(6);
        elementSorted[4] = new ArrayIntegers(7);
        elementSorted[5] = new ArrayIntegers(9);
        elementSorted[6] = new ArrayIntegers(12);

        Sorting.quickSort(element, compInt, new Random(0b1010010001010101));
        assertArrayEquals(elementSorted, element);
        //System.out.println("Number of comparisons: " + compInt.getCount());
        //System.out.println(elementSorted[0]);
        assertTrue("Number of comparisons: " + compInt.getCount(),
                compInt.getCount() <= 19 && compInt.getCount() != 0);
    }


    @Test(timeout = TIMEOUT)
    public void quickSortIntegersTest04() {
        // Multiple negative elements test
        element = new ArrayIntegers[7];
        element[0] = new ArrayIntegers(-2);  //  value @ index 0
        element[1] = new ArrayIntegers(-1); //   value @ index 1
        element[2] = new ArrayIntegers(-3);
        element[3] = new ArrayIntegers(-6);
        element[4] = new ArrayIntegers(-9);
        element[5] = new ArrayIntegers(-7);
        element[6] = new ArrayIntegers(-12);

        compInt = ArrayIntegers.getValueComparator();

        elementSorted = new ArrayIntegers[7];
        elementSorted[0] = new ArrayIntegers(-12);
        elementSorted[1] = new ArrayIntegers(-9);
        elementSorted[2] = new ArrayIntegers(-7);
        elementSorted[3] = new ArrayIntegers(-6);
        elementSorted[4] = new ArrayIntegers(-3);
        elementSorted[5] = new ArrayIntegers(-2);
        elementSorted[6] = new ArrayIntegers(-1);

        Sorting.quickSort(element, compInt, new Random(0b1010010001010101));
        assertArrayEquals(elementSorted, element);
        //System.out.println("Number of comparisons: " + compInt.getCount());
        assertTrue("Number of comparisons: " + compInt.getCount(),
                compInt.getCount() <= 17 && compInt.getCount() != 0);
    }

    @Test(timeout = TIMEOUT)
    public void quickSortIntegersTest05() {
        // Already sorted elements test
        element = new ArrayIntegers[7];
        element[0] = new ArrayIntegers(1);  //  value @ index 0
        element[1] = new ArrayIntegers(2); //   value @ index 1
        element[2] = new ArrayIntegers(3);
        element[3] = new ArrayIntegers(6);
        element[4] = new ArrayIntegers(7);
        element[5] = new ArrayIntegers(9);
        element[6] = new ArrayIntegers(12);

        compInt = ArrayIntegers.getValueComparator();

        elementSorted = new ArrayIntegers[7];
        elementSorted[0] = new ArrayIntegers(1);
        elementSorted[1] = new ArrayIntegers(2);
        elementSorted[2] = new ArrayIntegers(3);
        elementSorted[3] = new ArrayIntegers(6);
        elementSorted[4] = new ArrayIntegers(7);
        elementSorted[5] = new ArrayIntegers(9);
        elementSorted[6] = new ArrayIntegers(12);

        Sorting.quickSort(element, compInt, new Random(0b1010010001010101));
        assertArrayEquals(elementSorted, element);
        //System.out.println("Number of comparisons: " + compInt.getCount());
        assertTrue("Number of comparisons: " + compInt.getCount(),
                compInt.getCount() <= 17 && compInt.getCount() != 0);
    }

    @Test(timeout = TIMEOUT)
    public void quickSortIntegersTest06() {
        // reverse elements test
        element = new ArrayIntegers[7];
        element[0] = new ArrayIntegers(-1);  //  value @ index 0
        element[1] = new ArrayIntegers(-2); //   value @ index 1
        element[2] = new ArrayIntegers(-3);
        element[3] = new ArrayIntegers(-6);
        element[4] = new ArrayIntegers(7);
        element[5] = new ArrayIntegers(9);
        element[6] = new ArrayIntegers(12);

        compInt = ArrayIntegers.getValueComparator();

        elementSorted = new ArrayIntegers[7];
        elementSorted[0] = new ArrayIntegers(-6);
        elementSorted[1] = new ArrayIntegers(-3);
        elementSorted[2] = new ArrayIntegers(-2);
        elementSorted[3] = new ArrayIntegers(-1);
        elementSorted[4] = new ArrayIntegers(7);
        elementSorted[5] = new ArrayIntegers(9);
        elementSorted[6] = new ArrayIntegers(12);

        Sorting.quickSort(element, compInt, new Random(0b1010010001010101));
        assertArrayEquals(elementSorted, element);
        //System.out.println("Number of comparisons: " + compInt.getCount());
        assertTrue("Number of comparisons: " + compInt.getCount(),
                compInt.getCount() <= 13 && compInt.getCount() != 0);
    }

    @Test(timeout = TIMEOUT)
    public void quickSortIntegersTest07() {
        // mixed elements test
        element = new ArrayIntegers[8];
        element[0] = new ArrayIntegers(1);  //  value @ index 0
        element[1] = new ArrayIntegers(-2); //   value @ index 1
        element[2] = new ArrayIntegers(3);
        element[3] = new ArrayIntegers(-6);
        element[4] = new ArrayIntegers(7);
        element[5] = new ArrayIntegers(-9);
        element[6] = new ArrayIntegers(12);
        element[7] = new ArrayIntegers(-15);
        //element[8] = new ArrayIntegers(15);

        compInt = ArrayIntegers.getValueComparator();

        elementSorted = new ArrayIntegers[8];
        elementSorted[0] = new ArrayIntegers(-15);
        elementSorted[1] = new ArrayIntegers(-9);
        elementSorted[2] = new ArrayIntegers(-6);
        elementSorted[3] = new ArrayIntegers(-2);
        elementSorted[4] = new ArrayIntegers(1);
        elementSorted[5] = new ArrayIntegers(3);
        elementSorted[6] = new ArrayIntegers(7);
        elementSorted[7] = new ArrayIntegers(12);
        //elementSorted[8] = new ArrayIntegers(15);

        Sorting.quickSort(element, compInt, new Random(0b1010010001010101));
        assertArrayEquals(elementSorted, element);
        //System.out.println("Number of comparisons: " + compInt.getCount());
        assertTrue("Number of comparisons: " + compInt.getCount(),
                compInt.getCount() <= 23 && compInt.getCount() != 0);
    }

    @Test(timeout = TIMEOUT)
    public void quickSortIntegersTest08() {
        // Another test to check something else (This is one of those tests I created to check for just peace of mind)

        element = new ArrayIntegers[9];
        element[0] = new ArrayIntegers(1);  //  value @ index 0
        element[1] = new ArrayIntegers(-2); //   value @ index 1
        element[2] = new ArrayIntegers(3);
        element[3] = new ArrayIntegers(-6);
        element[4] = new ArrayIntegers(7);
        element[5] = new ArrayIntegers(-9);
        element[6] = new ArrayIntegers(12);
        element[7] = new ArrayIntegers(-15);
        element[8] = new ArrayIntegers(15);

        compInt = ArrayIntegers.getValueComparator();

        elementSorted = new ArrayIntegers[9];
        elementSorted[0] = new ArrayIntegers(-15);
        elementSorted[1] = new ArrayIntegers(-9);
        elementSorted[2] = new ArrayIntegers(-6);
        elementSorted[3] = new ArrayIntegers(-2);
        elementSorted[4] = new ArrayIntegers(1);
        elementSorted[5] = new ArrayIntegers(3);
        elementSorted[6] = new ArrayIntegers(7);
        elementSorted[7] = new ArrayIntegers(12);
        elementSorted[8] = new ArrayIntegers(15);

        Sorting.quickSort(element, compInt, new Random(0b1010010001010101));
        assertArrayEquals(elementSorted, element);
        //System.out.println("Number of comparisons: " + compInt.getCount());
        assertTrue("Number of comparisons: " + compInt.getCount(),
                compInt.getCount() <= 26);
    }

    //------------------------------

    @Test(timeout = TIMEOUT)
    public void quickSortIntegersTest09() {
        // mixed elements test with start pivot
        element = new ArrayIntegers[8];
        element[0] = new ArrayIntegers(1);  //  value @ index 0
        element[1] = new ArrayIntegers(-2); //   value @ index 1
        element[2] = new ArrayIntegers(3);
        element[3] = new ArrayIntegers(-6);
        element[4] = new ArrayIntegers(7);
        element[5] = new ArrayIntegers(-9);
        element[6] = new ArrayIntegers(12);
        element[7] = new ArrayIntegers(-15);
        //element[8] = new ArrayIntegers(15);

        compInt = ArrayIntegers.getValueComparator();

        elementSorted = new ArrayIntegers[8];
        elementSorted[0] = new ArrayIntegers(-15);
        elementSorted[1] = new ArrayIntegers(-9);
        elementSorted[2] = new ArrayIntegers(-6);
        elementSorted[3] = new ArrayIntegers(-2);
        elementSorted[4] = new ArrayIntegers(1);
        elementSorted[5] = new ArrayIntegers(3);
        elementSorted[6] = new ArrayIntegers(7);
        elementSorted[7] = new ArrayIntegers(12);
        //elementSorted[8] = new ArrayIntegers(15);

        Sorting.quickSort(element, compInt, new RandomStartPivot());
        assertArrayEquals(elementSorted, element);
        //System.out.println("Number of comparisons: " + compInt.getCount());
        assertTrue("Number of comparisons: " + compInt.getCount(),
                compInt.getCount() == 13);
    }

    @Test(timeout = TIMEOUT)
    public void quickSortIntegersTest10() {
        // Another test to check something else (This is one of those tests I created to check for just peace of mind)
        // Start index pivot
        element = new ArrayIntegers[9];
        element[0] = new ArrayIntegers(1);  //  value @ index 0
        element[1] = new ArrayIntegers(-2); //   value @ index 1
        element[2] = new ArrayIntegers(3);
        element[3] = new ArrayIntegers(-6);
        element[4] = new ArrayIntegers(7);
        element[5] = new ArrayIntegers(-9);
        element[6] = new ArrayIntegers(12);
        element[7] = new ArrayIntegers(-15);
        element[8] = new ArrayIntegers(15);

        compInt = ArrayIntegers.getValueComparator();

        elementSorted = new ArrayIntegers[9];
        elementSorted[0] = new ArrayIntegers(-15);
        elementSorted[1] = new ArrayIntegers(-9);
        elementSorted[2] = new ArrayIntegers(-6);
        elementSorted[3] = new ArrayIntegers(-2);
        elementSorted[4] = new ArrayIntegers(1);
        elementSorted[5] = new ArrayIntegers(3);
        elementSorted[6] = new ArrayIntegers(7);
        elementSorted[7] = new ArrayIntegers(12);
        elementSorted[8] = new ArrayIntegers(15);

        Sorting.quickSort(element, compInt, new RandomStartPivot());
        assertArrayEquals(elementSorted, element);
        //System.out.println("Number of comparisons: " + compInt.getCount());
        assertTrue("Number of comparisons: " + compInt.getCount(),
                compInt.getCount() <= 17 && compInt.getCount() != 0);
    }



    @Test(timeout = TIMEOUT)
    public void quickSortIntegersTest11() {
        // mixed elements test with middle pivot
        element = new ArrayIntegers[8];
        element[0] = new ArrayIntegers(1);  //  value @ index 0
        element[1] = new ArrayIntegers(-2); //   value @ index 1
        element[2] = new ArrayIntegers(3);
        element[3] = new ArrayIntegers(-6);
        element[4] = new ArrayIntegers(7);
        element[5] = new ArrayIntegers(-9);
        element[6] = new ArrayIntegers(12);
        element[7] = new ArrayIntegers(-15);
        //element[8] = new ArrayIntegers(15);

        compInt = ArrayIntegers.getValueComparator();

        elementSorted = new ArrayIntegers[8];
        elementSorted[0] = new ArrayIntegers(-15);
        elementSorted[1] = new ArrayIntegers(-9);
        elementSorted[2] = new ArrayIntegers(-6);
        elementSorted[3] = new ArrayIntegers(-2);
        elementSorted[4] = new ArrayIntegers(1);
        elementSorted[5] = new ArrayIntegers(3);
        elementSorted[6] = new ArrayIntegers(7);
        elementSorted[7] = new ArrayIntegers(12);
        //elementSorted[8] = new ArrayIntegers(15);

        Sorting.quickSort(element, compInt, new RandomMiddlePivot());
        assertArrayEquals(elementSorted, element);
        //System.out.println("Number of comparisons: " + compInt.getCount());
        assertTrue("Number of comparisons: " + compInt.getCount(),
                compInt.getCount() <= 16 && compInt.getCount() != 0);
    }

    @Test(timeout = TIMEOUT)
    public void quickSortIntegersTest12() {
        // Another test to check something else (This is one of those tests I created to check for just peace of mind)
        // middle index pivot
        element = new ArrayIntegers[9];
        element[0] = new ArrayIntegers(1);  //  value @ index 0
        element[1] = new ArrayIntegers(-2); //   value @ index 1
        element[2] = new ArrayIntegers(3);
        element[3] = new ArrayIntegers(-6);
        element[4] = new ArrayIntegers(7);
        element[5] = new ArrayIntegers(-9);
        element[6] = new ArrayIntegers(12);
        element[7] = new ArrayIntegers(-15);
        element[8] = new ArrayIntegers(15);

        compInt = ArrayIntegers.getValueComparator();

        elementSorted = new ArrayIntegers[9];
        elementSorted[0] = new ArrayIntegers(-15);
        elementSorted[1] = new ArrayIntegers(-9);
        elementSorted[2] = new ArrayIntegers(-6);
        elementSorted[3] = new ArrayIntegers(-2);
        elementSorted[4] = new ArrayIntegers(1);
        elementSorted[5] = new ArrayIntegers(3);
        elementSorted[6] = new ArrayIntegers(7);
        elementSorted[7] = new ArrayIntegers(12);
        elementSorted[8] = new ArrayIntegers(15);

        Sorting.quickSort(element, compInt, new RandomMiddlePivot());
        assertArrayEquals(elementSorted, element);
        //System.out.println("Number of comparisons: " + compInt.getCount());
        assertTrue("Number of comparisons: " + compInt.getCount(),
                compInt.getCount() <= 29 && compInt.getCount() != 0);
    }

    @Test(timeout = TIMEOUT)
    public void quickSortIntegersTest13() {
        // mixed elements test with end pivot
        element = new ArrayIntegers[8];
        element[0] = new ArrayIntegers(1);  //  value @ index 0
        element[1] = new ArrayIntegers(-2); //   value @ index 1
        element[2] = new ArrayIntegers(3);
        element[3] = new ArrayIntegers(-6);
        element[4] = new ArrayIntegers(7);
        element[5] = new ArrayIntegers(-9);
        element[6] = new ArrayIntegers(12);
        element[7] = new ArrayIntegers(-15);
        //element[8] = new ArrayIntegers(15);

        compInt = ArrayIntegers.getValueComparator();

        elementSorted = new ArrayIntegers[8];
        elementSorted[0] = new ArrayIntegers(-15);
        elementSorted[1] = new ArrayIntegers(-9);
        elementSorted[2] = new ArrayIntegers(-6);
        elementSorted[3] = new ArrayIntegers(-2);
        elementSorted[4] = new ArrayIntegers(1);
        elementSorted[5] = new ArrayIntegers(3);
        elementSorted[6] = new ArrayIntegers(7);
        elementSorted[7] = new ArrayIntegers(12);
        //elementSorted[8] = new ArrayIntegers(15);

        Sorting.quickSort(element, compInt, new RandomEndPivot());
        assertArrayEquals(elementSorted, element);
        //System.out.println("Number of comparisons: " + compInt.getCount());
        assertTrue("Number of comparisons: " + compInt.getCount(),
                compInt.getCount() <= 21 && compInt.getCount() != 0);
    }

    @Test(timeout = TIMEOUT)
    public void quickSortIntegersTest14() {
        // Another test to check something else (This is one of those tests I created to check for just peace of mind)
        // end index pivot
        element = new ArrayIntegers[9];
        element[0] = new ArrayIntegers(1);  //  value @ index 0
        element[1] = new ArrayIntegers(-2); //   value @ index 1
        element[2] = new ArrayIntegers(3);
        element[3] = new ArrayIntegers(-6);
        element[4] = new ArrayIntegers(7);
        element[5] = new ArrayIntegers(-9);
        element[6] = new ArrayIntegers(12);
        element[7] = new ArrayIntegers(-15);
        element[8] = new ArrayIntegers(15);

        compInt = ArrayIntegers.getValueComparator();

        elementSorted = new ArrayIntegers[9];
        elementSorted[0] = new ArrayIntegers(-15);
        elementSorted[1] = new ArrayIntegers(-9);
        elementSorted[2] = new ArrayIntegers(-6);
        elementSorted[3] = new ArrayIntegers(-2);
        elementSorted[4] = new ArrayIntegers(1);
        elementSorted[5] = new ArrayIntegers(3);
        elementSorted[6] = new ArrayIntegers(7);
        elementSorted[7] = new ArrayIntegers(12);
        elementSorted[8] = new ArrayIntegers(15);

        Sorting.quickSort(element, compInt, new RandomEndPivot());
        assertArrayEquals(elementSorted, element);
        //System.out.println("Number of comparisons: " + compInt.getCount());
        assertTrue("Number of comparisons: " + compInt.getCount(),
                compInt.getCount() <= 29);
    }

    @Test(timeout = TIMEOUT)
    public void quickSortIntegersTest15() {
        // empty elements test
        element = new ArrayIntegers[0];
        compInt = ArrayIntegers.getValueComparator();

        elementSorted = new ArrayIntegers[0];

        Sorting.quickSort(element, compInt, new Random(0b1010010001010101));
        assertArrayEquals(elementSorted, element);
        //System.out.println("Number of comparisons: " + compInt.getCount());
        assertTrue("Number of comparisons: " + compInt.getCount(),
                compInt.getCount() == 0);
    }


    // ------------------------------------------------------------------------------------------------------------
    //                                     QuickSort Tests on Piazza Test cases
    // ------------------------------------------------------------------------------------------------------------



    @Test(timeout = TIMEOUT)
    public void quickSortIntegersTestLastModuleExample() {

        element = new ArrayIntegers[9];
        element[0] = new ArrayIntegers(6, 'a');  //  value @ index 0
        element[1] = new ArrayIntegers(8); //   value @ index 1
        element[2] = new ArrayIntegers(4);
        element[3] = new ArrayIntegers(2);
        element[4] = new ArrayIntegers(3);
        element[5] = new ArrayIntegers(7);
        element[6] = new ArrayIntegers(9);
        element[7] = new ArrayIntegers(6, 'b');
        element[8] = new ArrayIntegers(5);

        compInt = ArrayIntegers.getValueComparator();

        elementSorted = new ArrayIntegers[9];
        elementSorted[0] = new ArrayIntegers(2);
        elementSorted[1] = new ArrayIntegers(3);
        elementSorted[2] = new ArrayIntegers(4);
        elementSorted[3] = new ArrayIntegers(5);
        elementSorted[4] = new ArrayIntegers(6, 'b');
        elementSorted[5] = new ArrayIntegers(6, 'a');
        elementSorted[6] = new ArrayIntegers(7);
        elementSorted[7] = new ArrayIntegers(8);
        elementSorted[8] = new ArrayIntegers(9);

        Sorting.quickSort(element, compInt, new RandomLastSecondPivot());
        assertArrayEquals(elementSorted, element);
        //System.out.println("Number of comparisons: " + compInt.getCount());
        assertTrue("Number of comparisons: " + compInt.getCount(),
                compInt.getCount() <= 21 && compInt.getCount() != 0);
    }



    @Test(timeout = TIMEOUT)
    public void quickSortIntegersTestPiazza00() {

        element = new ArrayIntegers[8];
        element[0] = new ArrayIntegers(5);  //  value @ index 0
        element[1] = new ArrayIntegers(4); //   value @ index 1
        element[2] = new ArrayIntegers(6, 'a');
        element[3] = new ArrayIntegers(9);
        element[4] = new ArrayIntegers(8);
        element[5] = new ArrayIntegers(7);
        element[6] = new ArrayIntegers(6, 'b');
        element[7] = new ArrayIntegers(2);


        compInt = ArrayIntegers.getValueComparator();

        elementSorted = new ArrayIntegers[8];
        elementSorted[0] = new ArrayIntegers(2);  // 5, 4, 6a, 9, 8, 7, 6b, 2
        elementSorted[1] = new ArrayIntegers(4);
        elementSorted[2] = new ArrayIntegers(5);
        elementSorted[3] = new ArrayIntegers(6, 'a');
        elementSorted[4] = new ArrayIntegers(6, 'b');
        elementSorted[5] = new ArrayIntegers(7);
        elementSorted[6] = new ArrayIntegers(8);
        elementSorted[7] = new ArrayIntegers(9);

        Sorting.quickSort(element, compInt, new RandomLastSecondPivot());
        assertArrayEquals(elementSorted, element);
        System.out.println("Number of comparisons: " + compInt.getCount());
        assertTrue("Number of comparisons: " + compInt.getCount(),
                compInt.getCount() <= 17 && compInt.getCount() != 0);
    }



    // ------------------------------------------------------------------------------------------------------------
    // ------------------------------------------------------------------------------------------------------------


    @Test (timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void quickSortArrayException() {
        element = new ArrayIntegers[8];
        element[0] = new ArrayIntegers(1);
        compInt = ArrayIntegers.getValueComparator();

        Sorting.quickSort(null, compInt, new Random(0b1010010001010101));
    }

    @Test (timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void quickSortComparatorException() {
        element = new ArrayIntegers[8];
        element[0] = new ArrayIntegers(1);
        compInt = ArrayIntegers.getValueComparator();

        Sorting.quickSort(element, null, new Random(0b1010010001010101));
    }


    @Test (timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void quickSortRandomObjectException() {
        element = new ArrayIntegers[8];
        element[0] = new ArrayIntegers(1);
        compInt = ArrayIntegers.getValueComparator();

        Sorting.quickSort(element, compInt, null);
    }


    //--------------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------------
    // |||||||||||||||||||||||        lsdRadix SORT TESTS      ||||||||||||||||||||||||||||
    //--------------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------------

    @Test(timeout = TIMEOUT)
    public void lsdRadixSortTest00() {
        int[] unsortedArray = new int[]{54, 28, 58, 84, 20, 122, -85, 3};
        int[] sortedArray = new int[]{-85, 3, 20, 28, 54, 58, 84, 122};
        Sorting.lsdRadixSort(unsortedArray);
        assertArrayEquals(sortedArray, unsortedArray);
    }

    @Test(timeout = TIMEOUT)
    public void lsdRadixSort01() {
        //zero elements test
        int[] unsortedArray = new int[]{};
        int[] sortedArray = new int[]{};
        Sorting.lsdRadixSort(unsortedArray);
        assertArrayEquals(sortedArray, unsortedArray);
    }

    @Test(timeout = TIMEOUT)
    public void lsdRadixSort02() {
        //single element test
        int[] unsortedArray = new int[]{1};
        int[] sortedArray = new int[]{1};
        Sorting.lsdRadixSort(unsortedArray);
        assertArrayEquals(sortedArray, unsortedArray);
    }

    @Test(timeout = TIMEOUT)
    public void lsdRadixSort03() {
        //two elements test
        int[] unsortedArray = new int[]{2, 1};
        int[] sortedArray = new int[]{1, 2};
        Sorting.lsdRadixSort(unsortedArray);
        assertArrayEquals(sortedArray, unsortedArray);
    }

    @Test(timeout = TIMEOUT)
    public void lsdRadixSort04() {
        //already sorted elements test
        int[] unsortedArray = new int[]{-5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5};
        int[] sortedArray = new int[]{-5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5};
        Sorting.lsdRadixSort(unsortedArray);
        assertArrayEquals(sortedArray, unsortedArray);
    }

    @Test(timeout = TIMEOUT)
    public void lsdRadixSort05() {
        //reverse elements test
        int[] unsortedArray = new int[]{5, 4, 3, 2, 1, 0, -1, -2, -3, -4, -5};
        int[] sortedArray = new int[]{-5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5};
        Sorting.lsdRadixSort(unsortedArray);
        assertArrayEquals(sortedArray, unsortedArray);
    }

    @Test(timeout = TIMEOUT)
    public void lsdRadixSort06() {
        //negative elements test
        int[] unsortedArray = new int[]{-54, -28, -58, -65, -20, -122, -85, -3};
        int[] sortedArray = new int[]{-122, -85, -65, -58, -54, -28, -20, -3};
        Sorting.lsdRadixSort(unsortedArray);
        assertArrayEquals(sortedArray, unsortedArray);
    }

    @Test(timeout = TIMEOUT)
    public void lsdRadixSort07() {
        //negative elements test
        int[] unsortedArray = new int[]{540, -2893, 1341, 4145, -20, -12422, -855, -3};
        int[] sortedArray = new int[]{-12422, -2893, -855, -20, -3, 540, 1341, 4145};
        Sorting.lsdRadixSort(unsortedArray);
        assertArrayEquals(sortedArray, unsortedArray);
    }


    @Test (timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void lsdRadixSortArrayException() {
        element = new ArrayIntegers[8];
        element[0] = new ArrayIntegers(1);
        compInt = ArrayIntegers.getValueComparator();

        Sorting.lsdRadixSort(null);
    }

    @Test(timeout = TIMEOUT)
    public void lsdRadixSort09() {
        // It checks if your loop continues when the left most digits are zero
        int[] unsortedArray = new int[]{5000, 2000, 1000, 4000, 3000, 1000, 8000, 9000};
        int[] sortedArray = new int[]{1000, 1000, 2000, 3000, 4000, 5000, 8000, 9000};
        Sorting.lsdRadixSort(unsortedArray);
        assertArrayEquals(sortedArray, unsortedArray);
    }

    @Test(timeout = TIMEOUT)
    public void lsdRadixSort10() {
        // It checks if your loop continues when the left most digits are zero with mixed signed digits
        int[] unsortedArray = new int[]{5000, -2000, 1000, 4000, 3000, -1000, 8000, 9000};
        int[] sortedArray = new int[]{-2000, -1000, 1000, 3000, 4000, 5000, 8000, 9000};
        Sorting.lsdRadixSort(unsortedArray);
        assertArrayEquals(sortedArray, unsortedArray);
    }

    @Test(timeout = TIMEOUT)
    public void lsdRadixSort11() {
        // min value edge case
        int[] unsortedArray = new int[]{-9000, 1000, 4000, 3000, (Integer.MIN_VALUE * -1), -1000, 8000, 9000};
        int[] sortedArray = new int[]{(Integer.MIN_VALUE * -1), -9000, -1000, 1000, 3000, 4000, 8000, 9000};
        Sorting.lsdRadixSort(unsortedArray);
        assertArrayEquals(sortedArray, unsortedArray);
    }

    @Test (timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void lsdArrayException() {
        Sorting.lsdRadixSort(null);
    }

    //--------------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------------
    // |||||||||||||||||||||||        Heap SORT TESTS      ||||||||||||||||||||||||||||
    //--------------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------------
    @Test(timeout = TIMEOUT)
    public void testHeapSort00() {
        int[] unsortedArray = new int[]{54, 28, 58, 84, 20, 122, -85, 3};
        List<Integer> unsortedList = new ArrayList<>();
        for (int i : unsortedArray) {
            unsortedList.add(i);
        }
        int[] sortedArray = new int[]{-85, 3, 20, 28, 54, 58, 84, 122};
        int[] actualArray = Sorting.heapSort(unsortedList);
        assertArrayEquals(sortedArray, actualArray);
    }


    @Test(timeout = TIMEOUT)
    public void heapSort01() {
        //zero elements test
        int[] unsortedArray = new int[]{};
        int[] sortedArray = new int[]{};
        List<Integer> unsortedList = new ArrayList<>();
        for (int i : unsortedArray) {
            unsortedList.add(i);
        }
        int[] actualArray = Sorting.heapSort(unsortedList);
        assertArrayEquals(sortedArray, actualArray);
    }

    @Test(timeout = TIMEOUT)
    public void heapSort02() {
        //single element test
        int[] unsortedArray = new int[]{1};
        int[] sortedArray = new int[]{1};
        List<Integer> unsortedList = new ArrayList<>();
        for (int i : unsortedArray) {
            unsortedList.add(i);
        }
        int[] actualArray = Sorting.heapSort(unsortedList);
        assertArrayEquals(sortedArray, actualArray);
    }

    @Test(timeout = TIMEOUT)
    public void heapSort03() {
        //two elements test
        int[] unsortedArray = new int[]{2, 1};
        int[] sortedArray = new int[]{1, 2};
        List<Integer> unsortedList = new ArrayList<>();
        for (int i : unsortedArray) {
            unsortedList.add(i);
        }
        int[] actualArray = Sorting.heapSort(unsortedList);
        assertArrayEquals(sortedArray, actualArray);
    }

    @Test(timeout = TIMEOUT)
    public void heapSort04() {
        //already sorted elements test
        int[] unsortedArray = new int[]{-5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5};
        int[] sortedArray = new int[]{-5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5};
        List<Integer> unsortedList = new ArrayList<>();
        for (int i : unsortedArray) {
            unsortedList.add(i);
        }
        int[] actualArray = Sorting.heapSort(unsortedList);
        assertArrayEquals(sortedArray, actualArray);    }

    @Test(timeout = TIMEOUT)
    public void heapSort05() {
        //reverse elements test
        int[] unsortedArray = new int[]{5, 4, 3, 2, 1, 0, -1, -2, -3, -4, -5};
        int[] sortedArray = new int[]{-5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5};
        List<Integer> unsortedList = new ArrayList<>();
        for (int i : unsortedArray) {
            unsortedList.add(i);
        }
        int[] actualArray = Sorting.heapSort(unsortedList);
        assertArrayEquals(sortedArray, actualArray);
    }

    @Test(timeout = TIMEOUT)
    public void heapSort06() {
        //negative elements test
        int[] unsortedArray = new int[]{-54, -28, -58, -65, -20, -122, -85, -3};
        int[] sortedArray = new int[]{-122, -85, -65, -58, -54, -28, -20, -3};
        List<Integer> unsortedList = new ArrayList<>();
        for (int i : unsortedArray) {
            unsortedList.add(i);
        }
        int[] actualArray = Sorting.heapSort(unsortedList);
        assertArrayEquals(sortedArray, actualArray);
    }

    @Test(timeout = TIMEOUT)
    public void heapSort07() {
        //negative elements test
        int[] unsortedArray = new int[]{540, -2893, 1341, 4145, -20, -12422, -855, -3};
        int[] sortedArray = new int[]{-12422, -2893, -855, -20, -3, 540, 1341, 4145};
        List<Integer> unsortedList = new ArrayList<>();
        for (int i : unsortedArray) {
            unsortedList.add(i);
        }
        int[] actualArray = Sorting.heapSort(unsortedList);
        assertArrayEquals(sortedArray, actualArray);
    }


    @Test (timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void heapSortArrayException() {
        element = new ArrayIntegers[8];
        element[0] = new ArrayIntegers(1);
        compInt = ArrayIntegers.getValueComparator();

        Sorting.heapSort(null);
    }








    //----------------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------------
    //------------------- BEWARE FELLOW PROGRAMMERS, DO NOT WANDER BEYOND THIS POINT ---------------------
    //---------------LOL, who am I kidding. Its like telling people to not press the red button-----------
    //------------------------------------------Private Classes below-------------------------------------
    //----------------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------------





    private class RandomStartPivot extends Random {
        @Override
        public int nextInt(int bound) {
            return 0;
        }
    }

    private class RandomMiddlePivot extends Random {
        @Override
        public int nextInt(int bound) {
            return ((bound - 1) / 2);
        }
    }

    private class RandomEndPivot extends Random {
        @Override
        public int nextInt(int bound) {
            return bound - 1;
        }
    }

    private class RandomLastSecondPivot extends Random {
        @Override
        public int nextInt(int bound) {
            return bound - 2;
        }
    }

    /**
     * This is a private class for testing the sorting methods where the values in the array are integers
     */
    private static class ArrayIntegers {
        private int value;
        private char order;

        /**
         * Create an arrayInteger object with its order 0
         *
         * @param value integer value
         */
        public ArrayIntegers(int value) {
            this(value, '\0');
        }

        /**
         * This constructor takes in the value and its order
         *
         * @param value the value to store
         * @param order the order of the value to test stability
         */
        public ArrayIntegers(int value, char order) {
            this.value = value;
            this.order = order;
        }

        /**
         * getter for value
         *
         * @return value
         */
        public int getValue() {
            return value;
        }

        /**
         * setter for value
         *
         * @param value value
         */
        public void setValue(int value) {
            this.value = value;
        }

        /**
         * getter for the order
         *
         * @return order number
         */
        public char getOrder() {
            return order;
        }

        /**
         * setter for the order
         *
         * @param order order char
         */
        public void setOrder(char order) {
            this.order = order;
        }

        @Override
        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            ArrayIntegers integer = (ArrayIntegers) other;
            return value == integer.value && order == integer.order;
        }

        /**
         * Comparator for ArrayIntegers
         *
         * @return the compared value to determine if the first argument is less than,
         * equal to, or greator than second argument
         */
        public static ComparatorPlus<ArrayIntegers> getValueComparator() {
            return new ComparatorPlus<ArrayIntegers>() {
                @Override
                public int compare(ArrayIntegers integer1,
                                   ArrayIntegers integer2) {
                    incrementCount();
                    return integer1.getValue() - integer2.getValue();
                }
            };
        }

        @Override
        public String toString() {
            return "Value: " + value + "  ||   " + " Order: " + order;
        }
    }


    ////=---------------------------------


    /**
     * Class for testing proper sorting.
     */
    private static class ArrayStrings {
        private String name;

        /**
         * Create a teaching assistant.
         *
         * @param name name of a pioneer
         */
        public ArrayStrings(String name) {
            this.name = name;
        }

        /**
         * Get the name of the pioneer.
         *
         * @return name of pioneer
         */
        public String getName() {
            return name;
        }

        /**
         * Set the name of the pioneer.
         *
         * @param name name of the pioneer
         */
        public void setName(String name) {
            this.name = name;
        }


        /**
         * Create a comparator that compares the names of the pioneer
         *
         * @return comparator that compares the names of the pioneer
         */
        public static ComparatorPlus<ArrayStrings> getNameComparator() {
            return new ComparatorPlus<ArrayStrings>() {
                @Override
                public int compare(ArrayStrings pioneer1,
                                   ArrayStrings pioneer2) {
                    incrementCount();
                    return pioneer1.getName().compareTo(pioneer2.getName());
                }
            };
        }

        @Override
        public String toString() {
            return "Name: " + name;
        }

        @Override
        public boolean equals(Object other) {
            if (other == null) {
                return false;
            }
            if (this == other) {
                return true;
            }
            return other instanceof ArrayStrings
                    && ((ArrayStrings) other).name.equals(this.name);
        }
    }

    /**
     * Inner class that allows counting how many comparisons were made.
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
