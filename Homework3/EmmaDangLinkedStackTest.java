import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertEquals;

public class EmmaDangLinkedStackTest {
    private LinkedStack<String> stack;
    private static final int TIME_OUT = 200;

    @Before
    public void setUp() {
        stack = new LinkedStack<>();
    }

    @Test(timeout = TIME_OUT)
    public void testInitialization() {
        assertEquals(0, stack.size());
        assertNull(stack.getHead());
    }

    @Test(timeout = TIME_OUT)
    public void testPush1() {
        // add 1 element
        stack.push("0a");
        assertEquals(1, stack.size());
        assertEquals("0a", stack.getHead().getData());
        assertNull(stack.getHead().getNext());
    }

    @Test(timeout = TIME_OUT)
    public void testPush2() {
        // add 2 element
        stack.push("0a"); // 0a
        stack.push("1a"); // 1a, 0a
        assertEquals(2, stack.size());
        LinkedNode<String> curr = stack.getHead();
        assertEquals("1a", curr.getData());
        curr = curr.getNext();
        assertEquals("0a", curr.getData());
        curr = curr.getNext();
        assertNull(curr);
    }

    @Test(timeout = TIME_OUT)
    public void testPush3() {
        // add 20 elements
        for (int i = 0; i < 20; i++) {
            stack.push(i + "a");
        } // 19a, 18a, 17a, ..., 1a, 0a
        assertEquals(20, stack.size());
        LinkedNode<String> curr = stack.getHead();
        for (int i = 19; i >= 0; i--) {
            assertEquals(i + "a", curr.getData());
            curr = curr.getNext();
        }
        assertNull(curr);
    }

    @Test(timeout = TIME_OUT)
    public void testPush4() {
        // add 100 elements
        for (int i = 0; i < 100; i++) {
            stack.push(i + "a");
        } // 99a, 98a, 97a, ..., 1a, 0a
        assertEquals(100, stack.size());
        LinkedNode<String> curr = stack.getHead();
        for (int i = 99; i >= 0; i--) {
            assertEquals(i + "a", curr.getData());
            curr = curr.getNext();
        }
        assertNull(curr);
    }

    @Test(timeout = TIME_OUT)
    public void testPop1() {
        // pop 1 element stack
        stack.push("0a");
        assertEquals("0a", stack.pop());
        assertEquals(0, stack.size());
        assertNull(stack.getHead());
    }

    @Test(timeout = TIME_OUT)
    public void testPop2() {
        // pop 2 element stack
        stack.push("0a");
        stack.push("1a"); // 1a, 0a
        LinkedNode<String> curr = stack.getHead();
        assertEquals(curr.getData(), stack.pop());
        assertEquals(1, stack.size());
        curr = curr.getNext();
        assertSame(curr, stack.getHead());
        assertEquals(curr.getData(), stack.pop());
        assertEquals(0, stack.size());
        assertNull(stack.getHead());
    }

    @Test(timeout = TIME_OUT)
    public void testPop3() {
        // pop 100-element stack
        for (int i = 0; i < 100; i++) {
            stack.push(i + "a");
        } // 99a, 98a, 97a, ..., 1a, 0a
        LinkedNode<String> curr = stack.getHead();
        for (int i = 99; i >= 0; i--) {
            assertSame(curr, stack.getHead());
            assertEquals(i + "a", curr.getData());
            assertEquals(curr.getData(), stack.pop());
            assertEquals(i, stack.size());
            curr = curr.getNext();
        }
        assertNull(curr);
        assertNull(stack.getHead());
        assertEquals(0, stack.size());
    }

    @Test(timeout = TIME_OUT)
    public void testMixUp1() {
        // push, pop, push
        stack.push("0a");
        stack.pop();
        stack.push("10a");
        assertEquals(1, stack.size());
        assertEquals("10a", stack.getHead().getData());
        assertNull(stack.getHead().getNext());
    }

    @Test(timeout = TIME_OUT)
    public void testMixUp2() {
        // random order of push and pop
        stack.push("0a"); // 0a
        stack.push("5a"); // 5a, 0a
        stack.push("10a"); // 10a, 5a, 0a
        stack.pop(); // 5a, 0a
        stack.push("100a"); // 100a, 5a, 0a
        stack.push("1a"); // 1a, 100a, 5a, 0a
        stack.pop();  // 100a, 5a, 0a
        assertEquals(3, stack.size());
        LinkedNode<String> curr = stack.getHead();
        assertEquals("100a", curr.getData());
        curr = curr.getNext();
        assertEquals("5a", curr.getData());
        curr = curr.getNext();
        assertEquals("0a", curr.getData());
        curr = curr.getNext();
        assertNull(curr);
    }

    @Test(timeout = TIME_OUT)
    public void testPeek1() {
        // 1 element stack
        stack.push("10a");
        assertEquals("10a", stack.peek());
        assertEquals(stack.getHead().getData(), stack.peek());
        assertEquals(stack.peek(), stack.pop());
    }

    @Test(timeout = TIME_OUT)
    public void testPeek2() {
        // 1-element stack. Peek after 2 push and 1 pop
        stack.push("10a"); // 10a
        stack.push("0a"); // 0a, 10a
        stack.pop(); // 10a;
        assertEquals("10a", stack.peek());
        assertEquals(stack.getHead().getData(), stack.peek());
        assertEquals(stack.peek(), stack.pop());
    }

    @Test(timeout = TIME_OUT)
    public void testPeek3() {
        // peek after a series of push and pop in random order
        stack.push("5a"); // 5a
        stack.push("45a"); // 45a, 5a
        stack.push("0a"); // 0a, 45a, 5a
        stack.pop(); // 45a, 5a
        stack.pop(); // 5a
        stack.push("100a"); // 100a, 5a
        stack.push("10a"); // 10a, 100a, 5a
        stack.push("1a"); // 1a, 10a, 100a, 5a
        stack.pop(); // 10a, 100a, 5a
        assertEquals(3, stack.size());
        assertEquals("10a", stack.peek());
        assertEquals(stack.getHead().getData(), stack.peek());
        assertEquals(stack.peek(), stack.pop());
    }
}
