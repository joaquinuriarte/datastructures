import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

/**
 *
 * @author Rudresh Patel
 * @version 1.1
 *
 * NOTE: Base template for these tests was taken from the "ArrayListStudentTest.java" file provided by the TAs.
 *       Some tests maybe similar if not same to the provided student tests by the TAs.
 */

public class RudreshPatelAdditionalTests {

    private static final int TIMEOUT = 200;
    private ArrayList<String> list;

    @Before
    public void setUp() {
        list = new ArrayList<>();
    }

    @Test(timeout = TIMEOUT)
    public void testInitialization() {
        assertEquals(0, list.size());
        assertArrayEquals(new Object[ArrayList.INITIAL_CAPACITY],
                list.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testAddAtIndex() {

        list.addAtIndex(0, "2a");   // 2a
        list.addAtIndex(0, "1a");   // 1a, 2a
        list.addAtIndex(2, "4a");   // 1a, 2a, 4a
        list.addAtIndex(2, "3a");   // 1a, 2a, 3a, 4a
        list.addAtIndex(0, "0a");   // 0a, 1a, 2a, 3a, 4a
        list.addAtIndex(5, "6a");   // 0a, 1a, 2a, 3a, 4a, 6a
        list.addAtIndex(6, "7a");   // 0a, 1a, 2a, 3a, 4a, 6a, 7a
        list.addAtIndex(7, "8a");   // 0a, 1a, 2a, 3a, 4a, 6a, 7a, 8a
        list.addAtIndex(8, "9a");   // 0a, 1a, 2a, 3a, 4a, 6a, 7a, 8a, 9a

        //Edge Case: adding in middle after full
        list.addAtIndex(5, "5a");   // 0a, 1a, 2a, 3a, 4a, 5a, 6a, 7a, 8a, 9a


        assertEquals(10, list.size());

        list.addAtIndex(0, "-1a");   // -1a, 0a, 1a, 2a, 3a, 4a, 5a, 6a, 7a, 8a, 9a
        list.addAtIndex(1, "-0.5a");  // -1a, -0.5a, 0a, 1a, 2a, 3a, 4a, 5a, 6a, 7a, 8a, 9a
        list.addAtIndex(12, "10a");  // -1a, -0.5a, 0a, 1a, 2a, 3a, 4a, 5a, 6a, 7a, 8a, 9a, 10a
        list.addAtIndex(6, "3.5a");  // -1a, -0.5a, 0a, 1a, 2a, 3a, 3.5a, 4a, 5a, 6a, 7a, 8a, 9a, 10a
        list.addAtIndex(0, "-2a");   // -2a, -1a, -0.5a, 0a, 1a, 2a, 3a, 3.5a 4a, 5a, 6a, 7a, 8a, 9a, 10a
        list.addAtIndex(5, "1.5a");  // -2a, -1a, -0.5a, 0a, 1a, 1.5a, 2a, 3a, 3.5a, 4a, 5a, 6a, 7a, 8a, 9a, 10a
        list.addAtIndex(13, "7.5a"); // -2a, -1a, -0.5a, 0a, 1a, 1.5a, 2a, 3a, 3.5a, 4a, 5a, 6a, 7a, 7.5a, 8a, 9a, 10a
        list.addAtIndex(17, "11a");  // -2a, -1a, -0.5a, 0a, 1a, 1.5a, 2a, 3a, 3.5a, 4a, 5a, 6a, 7a, 7.5a, 8a, 9a, 10a, 11a

        assertEquals(18, list.size());

        //Edge Case: adding to back after full
        list.addAtIndex(18, "12a");   // -2a, -1a, -2a, 0a, 1a, 1.5a, 2a, 3a, 3.5a, 4a, 5a, 6a, 7a, 7.5a, 8a, 9a, 10a, 11a, 12a

        assertEquals(19, list.size());

        list.addAtIndex(19, "13a");
        list.addAtIndex(20, "14a");
        list.addAtIndex(21, "15a");
        list.addAtIndex(22, "16a");
        list.addAtIndex(23, "17a");
        list.addAtIndex(24, "18a");
        list.addAtIndex(25, "19a");
        list.addAtIndex(26, "20a");
        list.addAtIndex(27, "21a");
        list.addAtIndex(28, "22a");
        list.addAtIndex(29, "23a");
        list.addAtIndex(30, "24a");
        list.addAtIndex(31, "25a");
        list.addAtIndex(32, "26a");
        list.addAtIndex(33, "27a");
        list.addAtIndex(34, "28a");
        list.addAtIndex(35, "29a");

        //Edge Cas: adding to front after full
        list.addAtIndex(0, "-3a");
        
        assertEquals(37, list.size());
        
        Object[] expected = new Object[ArrayList.INITIAL_CAPACITY * 2 * 2 * 2];
        expected[0] = "-3a";
        expected[1] = "-2a";
        expected[2] = "-1a";
        expected[3] = "-0.5a";
        expected[4] = "0a";
        expected[5] = "1a";
        expected[6] = "1.5a";
        expected[7] = "2a";
        expected[8] = "3a";
        expected[9] = "3.5a";
        expected[10] = "4a";
        expected[11] = "5a";
        expected[12] = "6a";
        expected[13] = "7a";
        expected[14] = "7.5a";
        expected[15] = "8a";
        expected[16] = "9a";
        expected[17] = "10a";
        expected[18] = "11a";
        expected[19] = "12a";
        expected[20] = "13a";
        expected[21] = "14a";
        expected[22] = "15a";
        expected[23] = "16a";
        expected[24] = "17a";
        expected[25] = "18a";
        expected[26] = "19a";
        expected[27] = "20a";
        expected[28] = "21a";
        expected[29] = "22a";
        expected[30] = "23a";
        expected[31] = "24a";
        expected[32] = "25a";
        expected[33] = "26a";
        expected[34] = "27a";
        expected[35] = "28a";
        expected[36] = "29a";
        assertArrayEquals(expected, list.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testAddToFront() {
        list.addToFront("9a");
        list.addToFront("8a");
        list.addToFront("7a");
        list.addToFront("6a");
        list.addToFront("5a");
        list.addToFront("4a");
        list.addToFront("3a");
        list.addToFront("2a");
        list.addToFront("1a");

        assertEquals(9, list.size());

        // Adding in front after full
        list.addToFront("0a");

        assertEquals(10, list.size());

        Object[] expected = new Object[ArrayList.INITIAL_CAPACITY * 2];
        expected[0] = "0a";
        expected[1] = "1a";
        expected[2] = "2a";
        expected[3] = "3a";
        expected[4] = "4a";
        expected[5] = "5a";
        expected[6] = "6a";
        expected[7] = "7a";
        expected[8] = "8a";
        expected[9] = "9a";
        assertArrayEquals(expected, list.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testAddToBack() {
        list.addToBack("0a");
        list.addToBack("1a");
        list.addToBack("2a");
        list.addToBack("3a");
        list.addToBack("4a");
        list.addToBack("5a");
        list.addToBack("6a");
        list.addToBack("7a");
        list.addToBack("8a");

        assertEquals(9, list.size());

        //adding to back after full
        list.addToBack("9a");

        assertEquals(10, list.size());

        Object[] expected = new Object[ArrayList.INITIAL_CAPACITY * 2];
        expected[0] = "0a";
        expected[1] = "1a";
        expected[2] = "2a";
        expected[3] = "3a";
        expected[4] = "4a";
        expected[5] = "5a";
        expected[6] = "6a";
        expected[7] = "7a";
        expected[8] = "8a";
        expected[9] = "9a";
        assertArrayEquals(expected, list.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveAtIndex() {
        list.addAtIndex(0, "0a");
        list.addAtIndex(1, "1a");
        list.addAtIndex(2, "3a" );
        list.addAtIndex(2, "2a");
        list.addAtIndex(4, "5a");
        list.addAtIndex(4, "4a");
        list.addAtIndex(6, "7a");
        list.addAtIndex(6, "6a");
        list.addAtIndex(8, "9a");

        list.addAtIndex(8, "8a");
        list.addAtIndex(4, "3.5a"); //  0a, 1a, 2a, 3a, 3.5a, 4a, 5a, 6a, 7a, 8a, 9a
        
        assertEquals(11, list.size());

        //Remove from front
        assertSame("0a", list.removeAtIndex(0)); // 1a, 2a, 3a, 3.5a, 4a, 5a, 6a, 7a, 8a, 9a
        assertEquals(10, list.size());

        //Remove from middle
        assertSame("3.5a", list.removeAtIndex(3)); // 1a, 2a, 3a, 4a, 5a, 6a, 7a, 8a, 9a
        assertEquals(9, list.size());

        //Remove from back
        assertSame("9a", list.removeAtIndex(8)); // 1a, 2a, 3a, 4a, 5a, 6a, 7a, 8a
        assertEquals(8, list.size());
        
        Object[] expected = new Object[ArrayList.INITIAL_CAPACITY * 2];
        expected[0] = "1a";
        expected[1] = "2a";
        expected[2] = "3a";
        expected[3] = "4a";
        expected[4] = "5a";
        expected[5] = "6a";
        expected[6] = "7a";
        expected[7] = "8a";
        expected[8] = null;

        assertArrayEquals(expected, list.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveFromFront() {

        list.addAtIndex(0, "0a");
        list.addAtIndex(1, "1a");
        list.addAtIndex(2, "3a" );
        list.addAtIndex(2, "2a");
        list.addAtIndex(4, "5a");
        list.addAtIndex(4, "4a");
        list.addAtIndex(6, "7a");
        list.addAtIndex(6, "6a");
        list.addAtIndex(8, "9a");

        list.addAtIndex(8, "8a");
        list.addAtIndex(4, "3.5a"); //  0a, 1a, 2a, 3a, 3.5a, 4a, 5a, 6a, 7a, 8a, 9a

        assertEquals(11, list.size());

        assertSame("0a", list.removeFromFront());   // 1a, 2a, 3a, 3.5a, 4a, 5a, 6a, 7a, 8a, 9a
        assertEquals(10, list.size());

        assertSame("1a", list.removeFromFront());   // 2a, 3a, 3.5a, 4a, 5a, 6a, 7a, 8a, 9a
        assertEquals(9, list.size());

        assertSame("2a", list.removeFromFront());   // 3a, 3.5a, 4a, 5a, 6a, 7a, 8a, 9a
        assertEquals(8, list.size());

        assertSame("3a", list.removeFromFront());   // 3.5a, 4a, 5a, 6a, 7a, 8a, 9a
        assertEquals(7, list.size());

        assertSame("3.5a", list.removeFromFront()); // 4a, 5a, 6a, 7a, 8a, 9a
        assertEquals(6, list.size());

        Object[] expected = new Object[ArrayList.INITIAL_CAPACITY * 2];
        expected[0] = "4a";
        expected[1] = "5a";
        expected[2] = "6a";
        expected[3] = "7a";
        expected[4] = "8a";
        expected[5] = "9a";
        assertArrayEquals(expected, list.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveFromBack() {

        list.addAtIndex(0, "0a");
        list.addAtIndex(1, "1a");
        list.addAtIndex(2, "3a" );
        list.addAtIndex(2, "2a");
        list.addAtIndex(4, "5a");
        list.addAtIndex(4, "4a");
        list.addAtIndex(6, "7a");
        list.addAtIndex(6, "6a");
        list.addAtIndex(8, "9a");

        list.addAtIndex(8, "8a");
        list.addAtIndex(4, "3.5a"); //  0a, 1a, 2a, 3a, 3.5a, 4a, 5a, 6a, 7a, 8a, 9a

        assertEquals(11, list.size());

        assertSame("9a", list.removeFromBack()); //  0a, 1a, 2a, 3a, 3.5a, 4a, 5a, 6a, 7a
        assertEquals(10, list.size());

        assertSame("8a", list.removeFromBack()); //  0a, 1a, 2a, 3a, 3.5a, 4a, 5a, 6a
        assertEquals(9, list.size());

        assertSame("7a", list.removeFromBack()); //  0a, 1a, 2a, 3a, 3.5a, 4a, 5a
        assertEquals(8, list.size());

        assertSame("6a", list.removeFromBack()); //  0a, 1a, 2a, 3a, 3.5a, 4a
        assertEquals(7, list.size());

        assertSame("5a", list.removeFromBack()); //  0a, 1a, 2a, 3a, 3.5a
        assertEquals(6, list.size());

        assertSame("4a", list.removeFromBack()); //  0a, 1a, 2a, 3a, 3.5a
        assertEquals(5, list.size());

        assertSame("3.5a", list.removeFromBack()); //  0a, 1a, 2a, 3a
        assertEquals(4, list.size());

        Object[] expected = new Object[ArrayList.INITIAL_CAPACITY * 2];
        expected[0] = "0a";
        expected[1] = "1a";
        expected[2] = "2a";
        expected[3] = "3a";
        assertArrayEquals(expected, list.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testGet() {
        list.addAtIndex(0, "0a");   // 0a
        list.addAtIndex(1, "1a");   // 0a, 1a
        list.addAtIndex(2, "2a");   // 0a, 1a, 2a
        list.addAtIndex(3, "3a");   // 0a, 1a, 2a, 3a
        list.addAtIndex(4, "4a");   // 0a, 1a, 2a, 3a, 4a
        assertEquals(5, list.size());

        assertEquals("0a", list.get(0));
        assertEquals("1a", list.get(1));
        assertEquals("2a", list.get(2));
        assertEquals("3a", list.get(3));
        assertEquals("4a", list.get(4));
    }

    @Test(timeout = TIMEOUT)
    public void testIsEmpty() {
        // Should be empty at initialization
        assertTrue(list.isEmpty());

        // Should not be empty after adding elements
        list.addAtIndex(0, "0a");   // 0a
        list.addAtIndex(1, "1a");   // 0a, 1a
        list.addAtIndex(2, "2a");   // 0a, 1a, 2a
        list.addAtIndex(3, "3a");   // 0a, 1a, 2a, 3a
        list.addAtIndex(4, "4a");   // 0a, 1a, 2a, 3a, 4a
        assertEquals(5, list.size());

        assertFalse(list.isEmpty());

        list.clear();

        assertTrue(list.isEmpty());
    }

    @Test(timeout = TIMEOUT)
    public void testClear() {
        list.addAtIndex(0, "0a");   // 0a
        list.addAtIndex(1, "1a");   // 0a, 1a
        list.addAtIndex(2, "2a");   // 0a, 1a, 2a
        list.addAtIndex(3, "3a");   // 0a, 1a, 2a, 3a
        list.addAtIndex(4, "4a");   // 0a, 1a, 2a, 3a, 4a
        assertEquals(5, list.size());

        // Clearing the list should empty the array and reset size
        list.clear();

        assertEquals(0, list.size());

        assertArrayEquals(new Object[ArrayList.INITIAL_CAPACITY],
                list.getBackingArray());
    }

    
    //||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||--------
    //||||||||||||||||||||||||||  EXCEPTION TESTS   ||||||||||||||||||||||||||--------
    //||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||--------

    @Test(timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void addAtIndexLessThanZeroTest() {
        list.addAtIndex(0, "0a");   // 0a
        list.addAtIndex(1, "1a");   // 0a, 1a
        list.addAtIndex(-1, "-1a");
    }

    @Test(timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void addAtIndexGreaterThanSizeTest() {
        list.addAtIndex(0, "0a");   // 0a
        list.addAtIndex(1, "1a");   // 0a, 1a
        list.addAtIndex(3, "3a");
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void addAtIndexNullDataTest() {
        list.addAtIndex(0, "0a");   // 0a
        list.addAtIndex(1, "1a");   // 0a, 1a
        list.addAtIndex(1, null);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void addToFrontNullDataTest() {
        list.addToFront("1a"); // 0a
        list.addToFront("0a"); // 0a, 1a
        list.addToFront(null);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void addToBackNullDataTest() {
        list.addToBack("0a"); // 0a
        list.addToBack("1a"); // 0a, 1a
        list.addToBack(null);
    }

    @Test(timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void removeAtIndexLessThanZeroTest() {
        list.addToBack("0a"); // 0a
        list.addToBack("1a"); // 0a, 1a
        list.removeAtIndex(-1);
    }

    @Test(timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void removeAtIndexGreaterOrEqualToSizeTest() {
        list.addToBack("0a"); // 0a
        list.addToBack("1a"); // 0a, 1a
        list.removeAtIndex(2);
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void removeFromFrontIsEmptyTest() {
        list.removeFromFront();
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void removeFromBackIsEmptyTest() {
        list.removeFromBack();
    }

    @Test(timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void getLessThanZeroTest() {
        list.addToBack("0a"); // 0a
        list.addToBack("1a"); // 0a, 1a
        list.get(-1);
    }

    @Test(timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void getGreaterOrEqualToSizeTest() {
        list.addToBack("0a"); // 0a
        list.addToBack("1a"); // 0a, 1a
        list.get(2);
    }
}