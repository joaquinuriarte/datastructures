import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class HashMapTestLiam {
    private static final int TIMEOUT = 200;
    private QuadraticProbingHashMap<Integer, String> map;

    @Before
    public void setUp() {
        map = new QuadraticProbingHashMap<>();

    }

    @Test(timeout = TIMEOUT)
    public void testInitialization() {
        assertEquals(0, map.size());
        assertArrayEquals(new QuadraticProbingMapEntry[
                QuadraticProbingHashMap.INITIAL_CAPACITY], map.getTable());
    }

    @Test(timeout = TIMEOUT)
    public void testPutBasic() {
        assertNull(map.put(1, "A"));
        assertNull(map.put(2, "B"));
        assertNull(map.put(3, "C"));
        assertNull(map.put(4, "D"));
        assertNull(map.put(5, "E"));

        assertEquals(5, map.size());
        QuadraticProbingMapEntry[] expected = new QuadraticProbingMapEntry[QuadraticProbingHashMap.INITIAL_CAPACITY];
        expected[1] = new QuadraticProbingMapEntry<>(1, "A");
        expected[2] = new QuadraticProbingMapEntry<>(2, "B");
        expected[3] = new QuadraticProbingMapEntry<>(3, "C");
        expected[4] = new QuadraticProbingMapEntry<>(4, "D");
        expected[5] = new QuadraticProbingMapEntry<>(5, "E");
        assertArrayEquals(expected, map.getTable());
    }

    @Test(timeout = TIMEOUT)
    public void testPutCompression() {
        assertNull(map.put(1, "A"));
        assertNull(map.put(2, "B"));
        assertNull(map.put(16, "C"));
        assertNull(map.put(4, "D"));
        assertNull(map.put(5, "E"));

        assertEquals(5, map.size());
        QuadraticProbingMapEntry[] expected = new QuadraticProbingMapEntry[QuadraticProbingHashMap.INITIAL_CAPACITY];
        expected[1] = new QuadraticProbingMapEntry<>(1, "A");
        expected[2] = new QuadraticProbingMapEntry<>(2, "B");
        expected[3] = new QuadraticProbingMapEntry<>(16, "C");
        expected[4] = new QuadraticProbingMapEntry<>(4, "D");
        expected[5] = new QuadraticProbingMapEntry<>(5, "E");
        assertArrayEquals(expected, map.getTable());
    }

    @Test(timeout = TIMEOUT)
    public void testPutDuplicate() {
        assertNull(map.put(1, "A"));
        assertNull(map.put(2, "B"));
        assertNull(map.put(3, "C"));
        assertNull(map.put(4, "D"));
        assertNull(map.put(5, "E"));
        assertEquals("E", map.put(5, "F"));

        assertEquals(5, map.size());

        QuadraticProbingMapEntry[] expected = new QuadraticProbingMapEntry[QuadraticProbingHashMap.INITIAL_CAPACITY];
        expected[1] = new QuadraticProbingMapEntry<>(1, "A");
        expected[2] = new QuadraticProbingMapEntry<>(2, "B");
        expected[3] = new QuadraticProbingMapEntry<>(3, "C");
        expected[4] = new QuadraticProbingMapEntry<>(4, "D");
        expected[5] = new QuadraticProbingMapEntry<>(5, "F");
        assertArrayEquals(expected, map.getTable());
    }

    @Test(timeout = TIMEOUT)
    public void testPutResize() {
        String alpha = "ABCDEFGHIJKLMNO";
        for (int i = 0; i < alpha.length(); i++) {
            map.put(i, String.valueOf(alpha.charAt(i)));
        }

        assertEquals(15, map.size());

        QuadraticProbingMapEntry[] expected = new QuadraticProbingMapEntry[27];
        for (int i = 0; i < alpha.length(); i++) {
            expected[i] = new QuadraticProbingMapEntry<>(i, String.valueOf(alpha.charAt(i)));
        }
        assertArrayEquals(expected, map.getTable());
    }

    @Test(timeout = TIMEOUT)
    public void testPutOneRemoved() {
        assertNull(map.put(1, "A"));
        assertNull(map.put(2, "B"));
        assertNull(map.put(3, "C"));
        assertNull(map.put(4, "D"));
        assertNull(map.put(5, "E"));

        assertEquals(5, map.size());

        assertEquals("C", map.remove(3));

        assertNull(map.put(3, "C"));

        QuadraticProbingMapEntry[] expected = new QuadraticProbingMapEntry[QuadraticProbingHashMap.INITIAL_CAPACITY];
        expected[1] = new QuadraticProbingMapEntry<>(1, "A");
        expected[2] = new QuadraticProbingMapEntry<>(2, "B");
        expected[3] = new QuadraticProbingMapEntry<>(3, "C");
        expected[4] = new QuadraticProbingMapEntry<>(4, "D");
        expected[5] = new QuadraticProbingMapEntry<>(5, "E");
        assertArrayEquals(expected, map.getTable());
    }

    @Test(timeout = TIMEOUT)
    public void testPutFirstRemoved() {
        String alpha = "ABCDEFGHIJKLMNO";
        for (int i = 0; i < alpha.length(); i++) {
            map.put(i, String.valueOf(alpha.charAt(i)));
        }
        assertEquals(15, map.size());

        map.remove(3);
        map.remove(4);
        map.remove(7);

        map.put(3, "D");

        QuadraticProbingMapEntry[] expected = new QuadraticProbingMapEntry[27];
        for (int i = 0; i < alpha.length(); i++) {
            expected[i] = new QuadraticProbingMapEntry<>(i, String.valueOf(alpha.charAt(i)));
        }
        expected[4].setRemoved(true);
        expected[7].setRemoved(true);
        assertArrayEquals(expected, map.getTable());
    }

    @Test(timeout = TIMEOUT)
    public void testPutNextRemoved() {
        String alpha = "ABCDEFGHIJKLMNO";
        for (int i = 0; i < alpha.length(); i++) {
            map.put(i, String.valueOf(alpha.charAt(i)));
        }
        assertEquals(15, map.size());

        map.remove(3);
        map.remove(4);
        map.remove(7);

        map.put(30, "D");
        map.put(3, "E");

        QuadraticProbingMapEntry[] expected = new QuadraticProbingMapEntry[27];
        for (int i = 0; i < alpha.length(); i++) {
            expected[i] = new QuadraticProbingMapEntry<>(i, String.valueOf(alpha.charAt(i)));
        }
        expected[3].setKey(30);
        expected[4].setKey(3);
        expected[7].setRemoved(true);
        assertArrayEquals(expected, map.getTable());
    }

    @Test(timeout = TIMEOUT)
    public void testPutAllRemoved() {
        assertNull(map.put(0, "0"));
        assertNull(map.put(1, "A"));
        assertNull(map.put(2, "B"));
        assertNull(map.put(3, "C"));
        assertNull(map.put(4, "D"));
        assertNull(map.put(5, "E"));

        map.resizeBackingTable(6);

        assertEquals(6, map.size());
        assertEquals(6, map.getTable().length);

        for (int i = 0; i <= 5; i++) {
            map.remove(i);
        }

        assertEquals(0, map.size());

        assertNull(map.put(0, "A"));

        assertEquals(1, map.size());

        QuadraticProbingMapEntry[] expected = new QuadraticProbingMapEntry[6];
        expected[0] = new QuadraticProbingMapEntry(0, "A");

        assertEquals(expected[0], map.getTable()[0]);

    }

    @Test(timeout = TIMEOUT)
    public void testPutMultiProbe() {
        String alpha = "ABCDEFGHIJKLMNO";
        for (int i = 0; i < alpha.length(); i++) {
            map.put(i, String.valueOf(alpha.charAt(i)));
        }
        assertEquals(15, map.size());
        map.put(28, "Z");

        QuadraticProbingMapEntry[] expected = new QuadraticProbingMapEntry[27];
        for (int i = 0; i < alpha.length(); i++) {
            expected[i] = new QuadraticProbingMapEntry<>(i, String.valueOf(alpha.charAt(i)));
        }
        expected[17] = new QuadraticProbingMapEntry(28, "Z");
        assertArrayEquals(expected, map.getTable());
    }

    @Test(timeout = TIMEOUT)
    public void testPutNullAfterRemoved() {
        assertNull(map.put(1, "A"));
        //2 will be null
        assertNull(map.put(3, "C"));
        assertNull(map.put(4, "D"));
        assertNull(map.put(5, "E"));

        map.remove(1);

        map.put(14, "A");

        QuadraticProbingMapEntry[] expected = new QuadraticProbingMapEntry[QuadraticProbingHashMap.INITIAL_CAPACITY];
        expected[1] = new QuadraticProbingMapEntry<>(14, "A");

        expected[3] = new QuadraticProbingMapEntry<>(3, "C");
        expected[4] = new QuadraticProbingMapEntry<>(4, "D");
        expected[5] = new QuadraticProbingMapEntry<>(5, "E");
        assertArrayEquals(expected, map.getTable());
    }

    @Test(timeout = TIMEOUT)
    public void testPutNowhereToGo() {
        map.resizeBackingTable(3);
        map.put(3, "A");
        map.put(1, "B");
        assertEquals(2, map.size());
        assertEquals(3, map.getTable().length);

        map.put(0, "E");

        QuadraticProbingMapEntry[] expected = new QuadraticProbingMapEntry[7];
        expected[0] = new QuadraticProbingMapEntry(3, "A");
        expected[1] = new QuadraticProbingMapEntry(1, "B");
        expected[4] = new QuadraticProbingMapEntry(0, "E");
        assertEquals(expected.length, map.getTable().length);

    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testPutNullKey() {
        map.put(null, "A");
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testPutNullValue() {
        map.put(1, null);
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveBasic() {
        assertNull(map.put(1, "A"));
        assertNull(map.put(2, "B"));
        assertNull(map.put(3, "C"));
        assertNull(map.put(4, "D"));
        assertNull(map.put(5, "E"));

        assertEquals("A", map.remove(1));
        assertEquals("B", map.remove(2));

        QuadraticProbingMapEntry[] expected =
                new QuadraticProbingMapEntry[
                        QuadraticProbingHashMap.INITIAL_CAPACITY];
        expected[1] = new QuadraticProbingMapEntry<>(1, "A");
        expected[2] = new QuadraticProbingMapEntry<>(2, "B");
        expected[3] = new QuadraticProbingMapEntry<>(3, "C");
        expected[4] = new QuadraticProbingMapEntry<>(4, "D");
        expected[5] = new QuadraticProbingMapEntry<>(5, "E");

        expected[1].setRemoved(true);
        expected[2].setRemoved(true);
        assertArrayEquals(expected, map.getTable());
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testRemoveNullKey() {
        map.put(1, "A");
        map.remove(null);
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testRemoveNotFound() {
        map.put(1, "A");
        map.put(13, "B");

        map.remove(0);
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testRemoveRemovedElement() {
        map.put(1, "A");
        assertEquals("A", map.remove(1));
        map.remove(1);
    }

    @Test(timeout = TIMEOUT)
    public void testGet() {
        String alpha = "ABCDEFGHIJKLMNO";
        for (int i = 0; i < alpha.length(); i++) {
            map.put(i, String.valueOf(alpha.charAt(i)));
        }

        assertEquals("D", map.get(3));
        assertEquals("O", map.get(14));
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testGetRemovedElement() {
        map.put(0, "A");
        map.remove(0);
        map.get(0);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testNullGet() {
        map.get(null);
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testGetNotFound() {
        map.get(0);
    }

    @Test(timeout = TIMEOUT)
    public void testContains() {
        String alpha = "ABCDEFGHIJKLMNO";
        for (int i = 0; i < alpha.length(); i++) {
            map.put(i, String.valueOf(alpha.charAt(i)));
        }

        assertTrue(map.containsKey(0));
        assertTrue(map.containsKey(5));
        assertTrue(map.containsKey(14));

        assertFalse(map.containsKey(15));

        assertFalse(map.containsKey(50));

        map.remove(0);
        assertFalse(map.containsKey(0));
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testContainsNull() {
        map.containsKey(null);
    }

    @Test(timeout = TIMEOUT)
    public void testKeySet() {
        String alpha = "ABCDEFGHIJKLMNO";
        for (int i = 0; i < alpha.length(); i++) {
            map.put(i, String.valueOf(alpha.charAt(i)));
        }

        Set<Integer> expected = new HashSet<>();
        for (int i = 0; i < alpha.length(); i++) {
            expected.add(i);
        }
        assertEquals(expected, map.keySet());
    }

    @Test(timeout = TIMEOUT)
    public void testKeySetRemovals() {
        String alpha = "ABCDEFGHIJKLMNO";
        for (int i = 0; i < alpha.length(); i++) {
            map.put(i, String.valueOf(alpha.charAt(i)));
        }
        map.remove(2);
        map.remove(10);

        Set<Integer> expected = new HashSet<>();
        for (int i = 0; i < alpha.length(); i++) {
            if (i != 2 && i != 10) {
                expected.add(i);
            }
        }
        assertEquals(expected, map.keySet());
    }

    @Test(timeout = TIMEOUT)
    public void testKeySetNulls() {
        String alpha = "ABCDEFGHIJKLMNO";
        for (int i = 0; i < alpha.length(); i++) {
            if (i != 2 && i != 10) {
                map.put(i, String.valueOf(alpha.charAt(i)));
            }
        }

        Set<Integer> expected = new HashSet<>();
        for (int i = 0; i < alpha.length(); i++) {
            if (i != 2 && i != 10) {
                expected.add(i);
            }
        }
        assertEquals(expected, map.keySet());
    }

    @Test(timeout = TIMEOUT)
    public void testValues() {
        String alpha = "ABCDEFGHIJKLMNO";
        for (int i = 0; i < alpha.length(); i++) {
            map.put(i, String.valueOf(alpha.charAt(i)));
        }

        ArrayList<String> expected = new ArrayList<>();
        for (int i = 0; i < alpha.length(); i++) {
            expected.add(String.valueOf(alpha.charAt(i)));
        }
        assertEquals(expected, map.values());
    }

    @Test(timeout = TIMEOUT)
    public void testValuesNulls() {
        String alpha = "ABCDEFGHIJKLMNO";
        for (int i = 0; i < alpha.length(); i++) {
            if (i != 2 && i != 10) {
                map.put(i, String.valueOf(alpha.charAt(i)));
            }
        }

        ArrayList<String> expected = new ArrayList<>();
        for (int i = 0; i < alpha.length(); i++) {
            if (i != 2 && i != 10) {
                expected.add(String.valueOf(alpha.charAt(i)));
            }
        }
        assertEquals(expected, map.values());
    }

    @Test(timeout = TIMEOUT)
    public void testValuesRemovals() {
        String alpha = "ABCDEFGHIJKLMNO";
        for (int i = 0; i < alpha.length(); i++) {
            map.put(i, String.valueOf(alpha.charAt(i)));
        }
        map.remove(2);
        map.remove(10);

        ArrayList<String> expected = new ArrayList<>();
        for (int i = 0; i < alpha.length(); i++) {
            if (i != 2 && i != 10) {
                expected.add(String.valueOf(alpha.charAt(i)));
            }
        }
        assertEquals(expected, map.values());
    }

    @Test(timeout = TIMEOUT)
    public void testResizeBackingTable() {
        map.put(0, "A");
        map.put(5, "F");
        map.put(10, "K");

        assertEquals(3, map.size());
        assertEquals(13, map.getTable().length);

        map.resizeBackingTable(4);

        assertEquals(3, map.size());
        assertEquals(4, map.getTable().length);

        QuadraticProbingMapEntry[] expected = new QuadraticProbingMapEntry[4];
        expected[0] = new QuadraticProbingMapEntry(0, "A");
        expected[1] = new QuadraticProbingMapEntry(5, "F");
        expected[2] = new QuadraticProbingMapEntry(10, "K");

        assertArrayEquals(expected, map.getTable());
    }

    @Test(timeout = TIMEOUT)
    public void testResizeBackingTable2() {
        map.put(15, "E");

        map.resizeBackingTable(20);

        QuadraticProbingMapEntry[] expected = new QuadraticProbingMapEntry[20];
        expected[15] = new QuadraticProbingMapEntry(15, "E");
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testResizeInvalidLength() {
        map.put(1, "A");
        map.put(2, "B");
        map.put(3, "C");
        map.resizeBackingTable(2);
    }


}