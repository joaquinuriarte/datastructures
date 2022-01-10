import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

import static org.junit.Assert.*;

public class EmmaDangQuadraticProbingHashMapTest {
    private QuadraticProbingHashMap<Integer, String> hashMap;
    private static final int TIME_OUT = 200;

    @Before
    public void setUp() {
        hashMap = new QuadraticProbingHashMap<>();
    }

    @Test(timeout = TIME_OUT)
    public void testConstructor1() {
        // test no-arg constructor
        assertEquals(13, hashMap.getTable().length);
        assertEquals(0, hashMap.size());
    }

    @Test(timeout = TIME_OUT)
    public void testConstructor2() {
        // test arg constructor
        hashMap = new QuadraticProbingHashMap<>(10);
        assertEquals(10, hashMap.getTable().length);
        assertEquals(0, hashMap.size());
    }

    @Test(timeout = TIME_OUT, expected = IllegalArgumentException.class)
    public void testPutGeneral1() {
        hashMap.put(null, "e");
    }

    @Test(timeout = TIME_OUT, expected = IllegalArgumentException.class)
    public void testPutGeneral2() {
        hashMap.put(1, null);
    }

    @Test(timeout = TIME_OUT, expected = IllegalArgumentException.class)
    public void testPutGeneral3() {
        hashMap.put(null, null);
    }

    @Test(timeout = TIME_OUT)
    public void testPut1() {
        // No resize; no collision
        assertNull(hashMap.put(23, "a")); // idx = 10
        assertNull(hashMap.put(1, "b")); // idx = 1
        assertNull(hashMap.put(13, "c")); // idx = 0
        assertEquals(3, hashMap.size());
        QuadraticProbingMapEntry<Integer, String>[] testTable = new QuadraticProbingMapEntry[13];
        testTable[10] = new QuadraticProbingMapEntry<>(23, "a");
        testTable[1] = new QuadraticProbingMapEntry<>(1, "b");
        testTable[0] = new QuadraticProbingMapEntry<>(13, "c");
        assertArrayEquals(testTable, hashMap.getTable());
    }

    @Test(timeout = TIME_OUT)
    public void testPut2() {
        // no resize; collisions
        assertNull(hashMap.put(1, "a")); // idx = 1
        assertNull(hashMap.put(14, "b")); // idx = 2
        assertNull(hashMap.put(27, "c")); // idx = 5
        assertEquals(3, hashMap.size());
        QuadraticProbingMapEntry<Integer, String>[] testTable = new QuadraticProbingMapEntry[13];
        testTable[1] = new QuadraticProbingMapEntry<>(1, "a");
        testTable[2] = new QuadraticProbingMapEntry<>(14, "b");
        testTable[5] = new QuadraticProbingMapEntry<>(27, "c");
        assertArrayEquals(testTable, hashMap.getTable());
    }

    @Test(timeout = TIME_OUT)
    public void testPut3() {
        // add a duplicate
        assertNull(hashMap.put(1, "a")); // idx = 1
        assertNull(hashMap.put(3, "b")); // idx = 3
        assertNull(hashMap.put(14, "c")); // idx = 2
        assertEquals("c", hashMap.put(14, "d")); // idx = 2
        assertEquals(3, hashMap.size());
        QuadraticProbingMapEntry<Integer, String>[] testTable = new QuadraticProbingMapEntry[13];
        testTable[1] = new QuadraticProbingMapEntry<>(1, "a");
        testTable[3] = new QuadraticProbingMapEntry<>(3, "b");
        testTable[2] = new QuadraticProbingMapEntry<>(14, "d");
        assertArrayEquals(testTable, hashMap.getTable());
    }

    @Test(timeout = TIME_OUT, expected = IllegalArgumentException.class)
    public void testRemove1() {
        testPut1();
        hashMap.remove(null);
    }

    @Test(timeout = TIME_OUT, expected = NoSuchElementException.class)
    public void testRemove2() {
        // remove key not in the map
        testPut1();
        hashMap.remove(2);
    }

    @Test(timeout = TIME_OUT)
    public void testRemove3() {
        // remove key in the map
        testPut1();
        assertEquals("b", hashMap.remove(1));
        assertEquals(2, hashMap.size());
        assertNotNull(hashMap.getTable()[1]);
        assertTrue(hashMap.getTable()[1].isRemoved());
    }

    @Test(timeout = TIME_OUT, expected = NoSuchElementException.class)
    public void testRemove4() {
        // remove key not in the map, but was in the map before and got deleted
        testRemove3();
        hashMap.remove(1);
    }

    @Test(timeout = TIME_OUT)
    public void testPut4() {
        // no resize; Add new <K, V> pair to an entry with a deleted tag
        hashMap.put(2, "a");
        hashMap.put(3, "b");
        hashMap.put(4, "c");
        hashMap.put(7, "d");
        hashMap.put(8, "e");
        hashMap.remove(3);
        hashMap.remove(8);
        hashMap.put(16, "f");
        assertEquals(4, hashMap.size());
        QuadraticProbingMapEntry<Integer, String>[] testTable = new QuadraticProbingMapEntry[13];
        testTable[2] = new QuadraticProbingMapEntry<>(2, "a");
        testTable[3] = new QuadraticProbingMapEntry<>(3, "b");
        testTable[4] = new QuadraticProbingMapEntry<>(4, "c");
        testTable[7] = new QuadraticProbingMapEntry<>(7, "d");
        testTable[8] = new QuadraticProbingMapEntry<>(8, "e");
        testTable[8].setRemoved(true);
        testTable[3].setKey(16);
        testTable[3].setValue("f");
        assertArrayEquals(testTable, hashMap.getTable());
    }

    @Test(timeout = TIME_OUT)
    public void testPut5() {
        // No resize; Add new <K, V> pair to a null position after encountering no removed entry
        testPut4();
        hashMap.put(5, "5");
        hashMap.put(6, "6");
        hashMap.put(18, "18");
        assertEquals(7, hashMap.size());
        QuadraticProbingMapEntry<Integer, String>[] testTable = new QuadraticProbingMapEntry[13];
        testTable[2] = new QuadraticProbingMapEntry<>(2, "a");
        testTable[3] = new QuadraticProbingMapEntry<>(16, "f");
        testTable[4] = new QuadraticProbingMapEntry<>(4, "c");
        testTable[5] = new QuadraticProbingMapEntry<>(5, "5");
        testTable[6] = new QuadraticProbingMapEntry<>(6, "6");
        testTable[7] = new QuadraticProbingMapEntry<>(7, "d");
        testTable[8] = new QuadraticProbingMapEntry<>(8, "e");
        testTable[8].setRemoved(true);
        testTable[9] = new QuadraticProbingMapEntry<>(18, "18");
        assertArrayEquals(testTable, hashMap.getTable());
    }

    @Test(timeout = TIME_OUT, expected = IllegalArgumentException.class)
    public void testResizeBackingArray1() {
        // resize when new capacity < size, expect exception
        hashMap.put(0, "0");
        hashMap.put(1, "1");
        hashMap.put(2, "2");
        hashMap.put(3, "3");
        hashMap.resizeBackingTable(3);
    }

    @Test(timeout = TIME_OUT)
    public void testResizeBackingArray2() {
        // resize when new length = size; all entries are hashed into new array
        hashMap.put(8, "8");
        hashMap.put(15, "15");
        hashMap.put(16, "16");
        hashMap.put(19, "19");
        hashMap.put(17, "17");
        hashMap.resizeBackingTable(5);
        QuadraticProbingMapEntry<Integer, String>[] testTable = new QuadraticProbingMapEntry[5];
        testTable[0] = new QuadraticProbingMapEntry<>(15, "15");
        testTable[1] = new QuadraticProbingMapEntry<>(16, "16");
        testTable[2] = new QuadraticProbingMapEntry<>(17, "17");
        testTable[3] = new QuadraticProbingMapEntry<>(8, "8");
        testTable[4] = new QuadraticProbingMapEntry<>(19, "19");
        assertEquals(5, hashMap.size());
        assertEquals(5, hashMap.getTable().length);
        assertArrayEquals(testTable, hashMap.getTable());
    }

    @Test(timeout = TIME_OUT)
    public void testResizeBackingArray4() {
        // resize when new capacity = 2N + 1; all entries are hashed into new array
        hashMap.put(23, "23");
        hashMap.put(15, "15");
        hashMap.put(9, "9");
        hashMap.put(37, "37");
        hashMap.put(24, "24");
        hashMap.put(17, "17");
        hashMap.put(21, "21");
        hashMap.put(8, "8");
        hashMap.resizeBackingTable(2 * hashMap.getTable().length + 1);
        assertEquals(27, hashMap.getTable().length);
        assertEquals(8, hashMap.size());
        QuadraticProbingMapEntry<Integer, String>[] testArray = new QuadraticProbingMapEntry[27];
        testArray[15] = new QuadraticProbingMapEntry<>(15, "15");
        testArray[17] = new QuadraticProbingMapEntry<>(17, "17");
        testArray[8] = new QuadraticProbingMapEntry<>(8, "8");
        testArray[21] = new QuadraticProbingMapEntry<>(21, "21");
        testArray[9] = new QuadraticProbingMapEntry<>(9, "9");
        testArray[23] = new QuadraticProbingMapEntry<>(23, "23");
        testArray[10] = new QuadraticProbingMapEntry<>(37, "37");
        testArray[24] = new QuadraticProbingMapEntry<>(24, "24");
        assertArrayEquals(testArray, hashMap.getTable());
    }

    @Test(timeout = TIME_OUT)
    public void testPut6() {
        // resize because LOAD_FACTOR is reached
        hashMap = new QuadraticProbingHashMap<>(7);
        hashMap.put(99, "99");
        hashMap.put(14, "14");
        hashMap.put(9, "9");
        hashMap.put(12, "12");
        hashMap.put(35, "35");
        assertEquals(5, hashMap.size());
        assertEquals(15, hashMap.getTable().length);
        QuadraticProbingMapEntry<Integer, String>[] testTable = new QuadraticProbingMapEntry[15];
        testTable[5] = new QuadraticProbingMapEntry<>(35, "35");
        testTable[9] = new QuadraticProbingMapEntry<>(99, "99");
        testTable[10] = new QuadraticProbingMapEntry<>(9, "9");
        testTable[12] = new QuadraticProbingMapEntry<>(12, "12");
        testTable[14] = new QuadraticProbingMapEntry<>(14, "14");
        assertArrayEquals(testTable, hashMap.getTable());
    }

    @Test(timeout = TIME_OUT)
    public void testPut7() {
        // resize because cyclic probing occurs
        hashMap = new QuadraticProbingHashMap<>(8);
        hashMap.put(2, "2");
        hashMap.put(11, "11");
        hashMap.put(10, "10");
        hashMap.put(18, "18");
        assertEquals(4, hashMap.size());
        assertEquals(17, hashMap.getTable().length);
        QuadraticProbingMapEntry<Integer, String>[] testArray = new QuadraticProbingMapEntry[17];
        testArray[1] = new QuadraticProbingMapEntry<>(18, "18");
        testArray[2] = new QuadraticProbingMapEntry<>(2, "2");
        testArray[10] = new QuadraticProbingMapEntry<>(10, "10");
        testArray[11] = new QuadraticProbingMapEntry<>(11, "11");
        assertArrayEquals(testArray, hashMap.getTable());
    }

    @Test(timeout = TIME_OUT, expected = IllegalArgumentException.class)
    public void testGet1() {
        hashMap.get(null);
    }

    @Test(timeout = TIME_OUT, expected = NoSuchElementException.class)
    public void testGet2() {
        testPut6();
        hashMap.get(10);
    }

    @Test(timeout = TIME_OUT)
    public void testGet3() {
        testPut7();
        assertEquals("18", hashMap.get(18));
    }

    @Test(timeout = TIME_OUT, expected = NoSuchElementException.class)
    public void testGet4() {
        testPut7();
        assertEquals("10", hashMap.remove(10));
        hashMap.get(10);
    }

    @Test(timeout = TIME_OUT)
    public void testContainsKey1() {
        testPut6();
        assertFalse(hashMap.containsKey(10));
    }

    @Test(timeout = TIME_OUT)
    public void testContainsKey2() {
        testPut6();
        hashMap.remove(99);
        assertFalse(hashMap.containsKey(99));
    }

    @Test(timeout = TIME_OUT)
    public void testContainsKey3() {
        testPut7();
        assertTrue(hashMap.containsKey(18));
    }

    @Test(timeout = TIME_OUT)
    public void testKeySet1() {
        testPut4();
        Set<Integer> testSet = new HashSet<>();
        testSet.add(2);
        testSet.add(4);
        testSet.add(7);
        testSet.add(16);
        assertTrue(testSet.equals(hashMap.keySet()));
    }

    @Test(timeout = TIME_OUT)
    public void testKeySet2() {
        testPut2();
        Set<Integer> testSet = new HashSet<>();
        testSet.add(1);
        testSet.add(14);
        testSet.add(27);
        assertTrue(testSet.equals(hashMap.keySet()));
    }

    @Test(timeout = TIME_OUT)
    public void testValues1() {
        testPut4();
        ArrayList<String> testValues = new ArrayList<>();
        testValues.add("a");
        testValues.add("f");
        testValues.add("c");
        testValues.add("d");
        assertTrue(testValues.equals(hashMap.values()));
    }

    @Test(timeout = TIME_OUT)
    public void testValues2() {
        testPut2();
        ArrayList<String> testValues = new ArrayList<>();
        testValues.add("a");
        testValues.add("b");
        testValues.add("c");
        assertTrue(testValues.equals(hashMap.values()));
    }

    @Test(timeout = TIME_OUT)
    public void testClear1() {
        hashMap = new QuadraticProbingHashMap<>(0);
        hashMap.clear();
        assertEquals(0, hashMap.size());
        assertEquals(13, hashMap.getTable().length);
    }

    @Test(timeout = TIME_OUT)
    public void testClear2() {
        testPut6();
        hashMap.clear();
        assertEquals(0, hashMap.size());
        assertEquals(13, hashMap.getTable().length);
    }
}
