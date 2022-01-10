import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

public class EmmaDangLinkedQueueTest {
    private LinkedQueue<String> queue;
    private static final int TIME_OUT = 200;

    @Before
    public void setUp() {
        this.queue = new LinkedQueue<>();
    }

    @Test(timeout = TIME_OUT)
    public void testInitialization() {
        assertEquals(0, this.queue.size());
        assertNull(this.queue.getHead());
        assertNull(this.queue.getTail());
    }

    @Test(timeout = TIME_OUT)
    public void testEnqueue1() {
        // add 1 element
        this.queue.enqueue("0a"); // 0a
        assertSame(this.queue.getHead(), this.queue.getTail());
        assertNull(this.queue.getTail().getNext());
        assertNull(this.queue.getHead().getNext());
        assertEquals(1, this.queue.size());
    }

    @Test(timeout = TIME_OUT)
    public void testEnqueue2() {
        // add 2 element
        this.queue.enqueue("0a"); // 0a
        this.queue.enqueue("1a"); // 0a, 1a
        assertEquals("0a", this.queue.getHead().getData());
        assertEquals("1a", this.queue.getTail().getData());
        assertSame(this.queue.getHead().getNext(), this.queue.getTail());
        assertNull(this.queue.getTail().getNext());
    }

    @Test(timeout = TIME_OUT)
    public void testEnqueue3() {
        // add 2 element
        this.queue.enqueue("0a"); // 0a
        this.queue.enqueue("1a"); // 0a, 1a
        this.queue.enqueue("2a"); // 0a, 1a, 2a
        this.queue.enqueue("3a"); // 0a, 1a, 2a, 3a
        this.queue.enqueue("4a"); // 0a, 1a, 2a, 3a, 4a
        this.queue.enqueue("5a"); // 0a, 1a, 2a, 3a, 4a, 5a

        assertSame(6, this.queue.size());

        LinkedNode<String> curr = this.queue.getHead();
        assertEquals("0a", curr.getData());
        curr = curr.getNext();
        assertEquals("1a", curr.getData());
        curr = curr.getNext();
        assertEquals("2a", curr.getData());
        curr = curr.getNext();
        assertEquals("3a", curr.getData());
        curr = curr.getNext();
        assertEquals("4a", curr.getData());
        curr = curr.getNext();
        assertSame(this.queue.getTail(), curr);
        curr = curr.getNext();
        assertNull(curr);
    }

    @Test(timeout = TIME_OUT)
    public void testDequeue1() {
        // dequeue from a queue of 1 element
        this.queue.enqueue("0a"); // 0a
        assertEquals("0a", this.queue.dequeue());
        assertNull(this.queue.getHead());
        assertNull(this.queue.getTail());
        assertEquals(0, this.queue.size());
    }

    @Test(timeout = TIME_OUT)
    public void testDequeue2() {
        // dequeue from a queue of 2 element
        this.queue.enqueue("0a"); // 0a
        this.queue.enqueue("1a"); // 1a
        assertEquals("0a", this.queue.dequeue());
        assertSame(this.queue.getHead(), this.queue.getTail());
        assertNull(this.queue.getTail().getNext());
        assertNull(this.queue.getHead().getNext());
        assertEquals("1a", this.queue.getHead().getData());
        assertEquals("1a", this.queue.getTail().getData());
        assertEquals(1, this.queue.size());
    }

    @Test(timeout = TIME_OUT)
    public void testDequeue3() {
        // dequeue from a queue of 3 elements
        this.queue.enqueue("0a");
        this.queue.enqueue("1a");
        this.queue.enqueue("2a");
        assertEquals("0a", this.queue.dequeue());
        assertEquals(2, this.queue.size());
        assertEquals("1a", this.queue.getHead().getData());
        assertSame(this.queue.getTail(), this.queue.getHead().getNext());
        assertEquals("2a", this.queue.getTail().getData());
        assertNull(this.queue.getTail().getNext());
    }

    @Test(timeout = TIME_OUT)
    public void testDequeue4() {
        // dequeue from a queue of more than 3 elements
        this.queue.enqueue("0a"); // 0a
        this.queue.enqueue("1a"); // 0a, 1a
        this.queue.enqueue("2a"); // 0a, 1a, 2a
        this.queue.enqueue("3a"); // 0a, 1a, 2a, 3a
        this.queue.enqueue("4a"); // 0a, 1a, 2a, 3a, 4a
        this.queue.enqueue("5a"); // 0a, 1a, 2a, 3a, 4a, 5a

        assertSame(6, this.queue.size());
        LinkedNode<String> curr = this.queue.getHead(); // 0a
        assertEquals(curr.getData(), this.queue.dequeue()); // 1a, 2a, 3a, 4a, 5a
        assertEquals(5, this.queue.size());
        curr = curr.getNext(); // 1a
        assertSame(curr, this.queue.getHead());
        assertEquals(curr.getData(), this.queue.dequeue()); // 2a, 3a, 4a, 5a
        assertEquals(4, this.queue.size());
        curr = curr.getNext(); // 2a
        assertSame(curr, this.queue.getHead());
        assertEquals(curr.getData(), this.queue.dequeue()); // 3a, 4a, 5a
        assertEquals(3, this.queue.size());
        curr = curr.getNext(); // 3a
        assertSame(curr, this.queue.getHead());
        assertEquals(curr.getData(), this.queue.dequeue()); // 4a, 5a
        assertEquals(2, this.queue.size());
        curr = curr.getNext(); // 4a
        assertSame(curr, this.queue.getHead());
        assertEquals(curr.getData(), this.queue.dequeue()); // 5a
        assertEquals(1, this.queue.size());
        curr = curr.getNext(); // 5a
        assertSame(curr, this.queue.getHead());
        assertSame(curr, this.queue.getTail());
        assertEquals(curr.getData(), this.queue.dequeue());
        assertEquals(0, this.queue.size());
        curr = curr.getNext();
        assertSame(curr, this.queue.getHead());
        assertNull(this.queue.getHead());
        assertNull(this.queue.getTail());
    }

    @Test(timeout = TIME_OUT)
    public void testMixUp() {
        // enqueue and dequeue in random order
        this.queue.enqueue("0a"); // 0a
        this.queue.enqueue("1a"); // 0a, 1a
        this.queue.dequeue(); // 1a
        this.queue.enqueue("2a"); // 1a, 2a
        this.queue.enqueue("3a"); // 1a, 2a, 3a
        this.queue.enqueue("4a"); // 1a, 2a, 3a, 4a
        this.queue.enqueue("5a"); // 1a, 2a, 3a, 4a, 5a
        this.queue.dequeue(); // 2a, 3a, 4a, 5a
        this.queue.dequeue(); // 3a, 4a, 5a
        this.queue.enqueue("9a"); // 3a, 4a, 5a, 9a
        this.queue.dequeue(); // 4a, 5a, 9a
        this.queue.enqueue("12a"); // 4a, 5a, 9a, 12a
        this.queue.dequeue(); // 5a, 9a, 12a
        this.queue.enqueue("20a"); // 5a, 9a, 12a, 20a

        assertEquals(4, this.queue.size());

        LinkedNode<String> curr = this.queue.getHead();
        assertEquals("5a", curr.getData());
        curr = curr.getNext(); // 9a
        assertEquals("9a", curr.getData());
        curr = curr.getNext(); // 12a
        assertEquals("12a", curr.getData());
        curr = curr.getNext(); // 20a
        assertEquals("20a", curr.getData());
        assertSame(curr, this.queue.getTail());
        assertNull(curr.getNext());
        assertNull(this.queue.getTail().getNext());
    }

    @Test(timeout = TIME_OUT)
    public void testPeek() {
        // enqueue and dequeue in random order
        this.queue.enqueue("0a"); // 0a
        this.queue.enqueue("2a"); // 0a, 1a
        this.queue.enqueue("5a"); // 0a, 1a, 5a
        this.queue.dequeue(); // 1a, 5a
        this.queue.enqueue("12a"); // 1a, 5a, 12a
        this.queue.enqueue("3a"); // 1a, 5a, 12a, 3a;
        this.queue.dequeue(); // 5a, 12a, 3a
        assertSame("5a", this.queue.peek());
        assertSame(this.queue.getHead().getData(), this.queue.peek());
    }
}
