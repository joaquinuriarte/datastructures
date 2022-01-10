import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;

public class EmmaDangArrayStackTest {
    private ArrayStack<String> stack;
    private static final int TIME_OUT = 200;

    @Before
    public void setUp() {
        stack = new ArrayStack<>();
    }

    @Test(timeout = TIME_OUT)
    public void testInitialization() {
        assertEquals(0, stack.size());
        String[] testArray = new String[9];
        assertArrayEquals(testArray, stack.getBackingArray());
    }

    @Test(timeout = TIME_OUT)
    public void testPush1() {
        // stack does not reach full capacity
        stack.push("0a"); // 0a
        stack.push("1a"); // 0a, 1a
        stack.push("2a"); // 0a, 1a, 2a
        stack.push("3a"); // 0a, 1a, 2a, 3a
        stack.push("4a"); // 0a, 1a, 2a, 3a, 4a
        assertEquals(5, stack.size());
        String[] testArray = new String[9];
        testArray[0] = "0a";
        testArray[1] = "1a";
        testArray[2] = "2a";
        testArray[3] = "3a";
        testArray[4] = "4a";
        assertArrayEquals(testArray, stack.getBackingArray());
    }

    @Test(timeout = TIME_OUT)
    public void testPush2() {
        // stack reach full capacity
        stack.push("0a"); // 0a
        stack.push("1a"); // 0a, 1a
        stack.push("2a"); // 0a, 1a, 2a
        stack.push("3a"); // 0a, 1a, 2a, 3a
        stack.push("4a"); // 0a, 1a, 2a, 3a, 4a
        stack.push("5a"); // 0a, 1a, 2a, 3a, 4a, 5a
        stack.push("6a"); // 0a, 1a, 2a, 3a, 4a, 5a, 6s
        stack.push("7a"); // 0a, 1a, 2a, 3a, 4a, 5a, 6s, 7a
        stack.push("8a"); // 0a, 1a, 2a, 3a, 4a, 5a, 6s, 7a, 8a
        assertEquals(9, stack.size());
        String[] testArray = new String[9];
        testArray[0] = "0a";
        testArray[1] = "1a";
        testArray[2] = "2a";
        testArray[3] = "3a";
        testArray[4] = "4a";
        testArray[5] = "5a";
        testArray[6] = "6a";
        testArray[7] = "7a";
        testArray[8] = "8a";
        assertArrayEquals(testArray, stack.getBackingArray());
    }

    @Test(timeout = TIME_OUT)
    public void testPush3() {
        // stack exceeds full capacity
        for (int i = 0; i < 12; i++) {
            stack.push(i + "a");
        }
        assertEquals(12, stack.size());
        String[] testArray = new String[18];
        for (int i = 0; i < 12; i++) {
            testArray[i] = i + "a";
        }
        assertArrayEquals(testArray, stack.getBackingArray());
    }

    @Test(timeout = TIME_OUT)
    public void testPush4() {
        for (int i = 0; i < 25; i++) {
            stack.push(i + "a");
        }
        assertEquals(25, stack.size());
        String[] testArray = new String[36];
        for (int i = 0; i < 25; i++) {
            testArray[i] = i + "a";
        }
        assertArrayEquals(testArray, stack.getBackingArray());
    }

    @Test(timeout = TIME_OUT)
    public void testPop1() {
        // stack does not reach full capacity
        stack.push("0a"); // 0a
        stack.push("1a"); // 0a, 1a
        stack.push("2a"); // 0a, 1a, 2a
        stack.push("3a"); // 0a, 1a, 2a, 3a
        stack.push("4a"); // 0a, 1a, 2a, 3a, 4a
        assertEquals("4a", stack.pop());
        assertEquals(4, stack.size());
        assertEquals("3a", stack.pop());
        assertEquals(3, stack.size());
        assertEquals("2a", stack.pop());
        assertEquals(2, stack.size());
        assertEquals("1a", stack.pop());
        assertEquals(1, stack.size());
        assertEquals("0a", stack.pop());
        assertEquals(0, stack.size());
        String[] testArray = new String[9];
        assertArrayEquals(testArray, stack.getBackingArray());
    }

    @Test(timeout = TIME_OUT)
    public void testPop2() {
        // stack reach full capacity
        for (int i = 0; i < 9; i++) {
            stack.push(i + "a");
        }
        for (int i = 0; i < 9; i++) {
            assertEquals(9 - i, stack.size());
            assertEquals((8 - i) + "a", stack.pop());
        }
        assertEquals(0, stack.size());
        String[] testArray = new String[9];
        assertArrayEquals(testArray, stack.getBackingArray());
    }

    @Test(timeout = TIME_OUT)
    public void testPop3() {
        // stack exceed full capacity
        for (int i = 0; i < 12; i++) {
            stack.push(i + "a");
        }
        for (int i = 0; i < 12; i++) {
            assertEquals(12 - i, stack.size());
            assertEquals((11 - i) + "a", stack.pop());
        }
        assertEquals(0, stack.size());
        String[] testArray = new String[18];
        assertArrayEquals(testArray, stack.getBackingArray());
    }

    @Test(timeout = TIME_OUT)
    public void testPop4() {
        // stack exceed full capacity
        for (int i = 0; i < 21; i++) {
            stack.push(i + "a");
        }
        for (int i = 0; i < 21; i++) {
            assertEquals(21 - i, stack.size());
            assertEquals((20 - i) + "a", stack.pop());
        }
        assertEquals(0, stack.size());
        String[] testArray = new String[36];
        assertArrayEquals(testArray, stack.getBackingArray());
    }

    @Test(timeout = TIME_OUT)
    public void testPeek1() {
        // stack does not reach full capacity
        for (int i = 0; i < 5; i++) {
            stack.push(i + "a");
            assertEquals(i + "a", stack.peek());
        }
        for (int i = 0; i < 5; i++) {
            assertEquals((4 - i) + "a", stack.peek());
            assertEquals(stack.peek(), stack.pop());
        }
    }

    @Test(timeout = TIME_OUT)
    public void testPeek2() {
        // stack reach full capacity
        for (int i = 0; i < 9; i++) {
            stack.push(i + "a");
            assertEquals(i + "a", stack.peek());
        }
        for (int i = 0; i < 9; i++) {
            assertEquals((8 - i) + "a", stack.peek());
            assertEquals(stack.peek(), stack.pop());
        }
    }

    @Test(timeout = TIME_OUT)
    public void testPeek3() {
        // stack exceed full capacity
        for (int i = 0; i < 12; i++) {
            stack.push(i + "a");
            assertEquals(i + "a", stack.peek());
        }
        for (int i = 0; i < 12; i++) {
            assertEquals((11 - i) + "a", stack.peek());
            assertEquals(stack.peek(), stack.pop());
        }
    }
}
