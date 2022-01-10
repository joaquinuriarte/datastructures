import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class MaxHeapTestLiam {

    private static final int TIMEOUT = 200;
    private MaxHeap<Integer> heap;

    @Before
    public void setUp() {
        heap = new MaxHeap<>();
    }

    @Test(timeout = TIMEOUT)
    public void testInitialization() {
        assertEquals(0, heap.size());
        assertArrayEquals(new Comparable[MaxHeap.INITIAL_CAPACITY],
                heap.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testBuildHeap() {
        ArrayList<Integer> data = new ArrayList<>();
        data.add(1);
        data.add(9);
        data.add(5);
        data.add(3);
        data.add(4);
        data.add(8);
        data.add(10);
        data.add(7);
        data.add(2);
        data.add(6);


        heap = new MaxHeap<>(data);

        assertEquals(10, heap.size());

        Integer[] expected = new Integer[21];
        expected[1] = 10;
        expected[2] = 9;
        expected[3] = 8;
        expected[4] = 7;
        expected[5] = 6;
        expected[6] = 1;
        expected[7] = 5;
        expected[8] = 3;
        expected[9] = 2;
        expected[10] = 4;

        assertArrayEquals(expected, heap.getBackingArray());

    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testBuildHeapNullList() {
        heap = new MaxHeap<>(null);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testBuildHeapAddNull() {
        ArrayList<Integer> data = new ArrayList<>();
        data.add(1);
        data.add(9);
        data.add(5);
        data.add(3);
        data.add(4);
        data.add(8);
        data.add(null);
        data.add(7);
        data.add(2);
        data.add(6);

        heap = new MaxHeap<>(data);
    }

    @Test (timeout = TIMEOUT)
    public void testBuildHeapResize() {
        ArrayList<Integer> data = new ArrayList<>();
        data.add(1);
        data.add(2);

        heap = new MaxHeap<>(data);
        Comparable[] back = heap.getBackingArray();
        assertEquals(5, back.length);

        heap.add(3);
        heap.add(4);
        heap.add(5);
        assertEquals(5, heap.size());
        heap.add(6);
        assertEquals(6, heap.size());
        Comparable[] back2 = heap.getBackingArray();
        assertEquals(10, back2.length);

    }

    @Test (timeout = TIMEOUT)
    public void testAdd() {
        heap.add(10);
        heap.add(9);
        heap.add(8);
        heap.add(7);
        heap.add(6);
        heap.add(1);
        heap.add(5);
        heap.add(3);
        heap.add(2);
        heap.add(4);

        Integer[] expected = new Integer[13];
        expected[1] = 10;
        expected[2] = 9;
        expected[3] = 8;
        expected[4] = 7;
        expected[5] = 6;
        expected[6] = 1;
        expected[7] = 5;
        expected[8] = 3;
        expected[9] = 2;
        expected[10] = 4;

        assertArrayEquals(expected, heap.getBackingArray());
    }

    @Test (timeout = TIMEOUT)
    public void testAdd2() {
        heap.add(10);
        heap.add(100);
        heap.add(101);
        heap.add(102);
        heap.add(103);
        heap.add(104);
        heap.add(105);
        heap.add(107);
        heap.add(106);
        heap.add(1000);
        heap.add(1011);
        heap.add(109);
        heap.add(108);
        heap.add(110);
        heap.add(120);
        heap.add(130);
        heap.add(140);
        heap.add(10000);

        assertEquals(18, heap.size());

        Integer[] expected = new Integer[26];
        expected[1] = 10000;
        expected[2] = 1011;
        expected[3] = 120;
        expected[4] = 1000;
        expected[5] = 107;
        expected[6] = 108;
        expected[7] = 110;
        expected[8] = 130;
        expected[9] = 140;
        expected[10] = 101;
        expected[11] = 106;
        expected[12] = 100;
        expected[13] = 104;
        expected[14] = 103;
        expected[15] = 109;
        expected[16] = 10;
        expected[17] = 105;
        expected[18] = 102;

        assertArrayEquals(expected, heap.getBackingArray());

    }

    @Test (timeout = TIMEOUT)
    public void testMultiResize() {
        for (int i = 0; i < 50; i++) {
            heap.add(i);
        }
        assertEquals(50, heap.size());
        Comparable[] backingArray = heap.getBackingArray();
        assertEquals(52, backingArray.length);
    }

    @Test (timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testAddNull() {
        heap.add(null);
    }

    @Test (timeout = TIMEOUT)
    public void testAddRemove() {
        heap.add(10);
        assertEquals(1, heap.size());
        assertEquals(10, (int) heap.remove());
        assertEquals(0, heap.size());
        heap.add(10);
        assertEquals(1, heap.size());
        assertEquals(10, (int) heap.remove());
    }

    @Test (timeout = TIMEOUT)
    public void testRemove() {
        heap.add(10);
        heap.add(9);
        heap.add(8);
        heap.add(7);
        heap.add(6);
        heap.add(1);
        heap.add(5);
        heap.add(3);
        heap.add(2);
        heap.add(4);

        Comparable[] backingArray = heap.getBackingArray();

        assertEquals(10, heap.size());

        assertSame(backingArray[1], heap.remove());

        Integer[] expected = new Integer[13];
        expected[1] = 9;
        expected[2] = 7;
        expected[3] = 8;
        expected[4] = 4;
        expected[5] = 6;
        expected[6] = 1;
        expected[7] = 5;
        expected[8] = 3;
        expected[9] = 2;

        assertArrayEquals(expected, heap.getBackingArray());
        assertEquals(9, heap.size());

        assertEquals(9, (int) heap.remove());

        Integer[] expected2 = new Integer[13];
        expected2[1] = 8;
        expected2[2] = 7;
        expected2[3] = 5;
        expected2[4] = 4;
        expected2[5] = 6;
        expected2[6] = 1;
        expected2[7] = 2;
        expected2[8] = 3;

        assertArrayEquals(expected2, heap.getBackingArray());

        heap.remove();
        heap.remove();
        heap.remove();
        heap.remove();
        heap.remove();
        heap.remove();
        heap.remove();

        assertEquals(1, heap.size());

        Integer[] expected3 = new Integer[13];
        expected3[1] = 1;

        assertArrayEquals(expected3, heap.getBackingArray());

        assertEquals(1, (int) heap.remove());

        Integer[] empty = new Integer[13];
        assertArrayEquals(empty, heap.getBackingArray());
    }

    @Test (timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testRemoveEmpty() {
        assertEquals(0, heap.size());
        heap.remove();
    }

    @Test (timeout = TIMEOUT)
    public void testRemoveResize() {
        for (int i = 0; i < 14; i++) {
            heap.add(i);
        }

        Comparable[] backing = heap.getBackingArray();

        assertEquals(26, backing.length);

        heap.remove();

        assertEquals(26, backing.length);

    }

    @Test (timeout = TIMEOUT)
    public void testGetMax() {
        heap.add(10);
        heap.add(9);
        heap.add(8);
        heap.add(7);
        heap.add(6);
        heap.add(1);
        heap.add(5);
        heap.add(3);
        heap.add(2);
        heap.add(4);

        assertEquals(10, (int) heap.getMax());

        heap.remove();
        heap.remove();

        assertEquals(8, (int) heap.getMax());

        Comparable[] backingArray = heap.getBackingArray();

        assertSame(backingArray[1], heap.getMax());
    }

    @Test (timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testGetMaxEmpty() {
        heap.getMax();
    }

    @Test (timeout = TIMEOUT)
    public void testIsEmpty() {
        heap.add(1);
        assertFalse(heap.isEmpty());
        heap.remove();
        assertTrue(heap.isEmpty());
    }

    @Test (timeout = TIMEOUT)
    public void testClear() {
        heap.add(10);
        heap.add(9);
        heap.add(8);
        heap.add(7);
        heap.add(6);
        heap.add(1);
        heap.add(5);
        heap.add(3);
        heap.add(2);
        heap.add(4);

        assertEquals(10, heap.size());

        heap.clear();

        assertEquals(0, heap.size());

        Integer[] empty = new Integer[13];
        assertArrayEquals(empty, heap.getBackingArray());

    }

}
