import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class EmmaDangArrayQueueTest {

    private static final int TIME_OUT = 200;
    private ArrayQueue<String> queue;

    @org.junit.Before
    public void setUp() {
        this.queue = new ArrayQueue<>();
    }

    @org.junit.Test(timeout = TIME_OUT)
    public void initialization() {
        assertEquals(0, this.queue.size());
        assertArrayEquals(new Object[9], this.queue.getBackingArray());
    }

    @org.junit.Test
    public void testEnqueue1() {
        // queue not reach full capacity
        this.queue.enqueue("0a"); // 0a
        this.queue.enqueue("1a"); // 0a, 1a
        this.queue.enqueue("2a"); // 0a, 1a, 2a
        this.queue.enqueue("3a"); // 0a, 1a, 2a, 3a
        assertEquals(4, this.queue.size());
        String[] testArray = {"0a", "1a", "2a", "3a", null, null, null, null, null};
        assertArrayEquals(testArray, this.queue.getBackingArray());
    }

    @org.junit.Test
    public void testEnqueue2() {
        // queue reaches full cacpacity
        this.queue.enqueue("0a"); // 0a
        this.queue.enqueue("1a"); // 0a, 1a
        this.queue.enqueue("2a"); // 0a, 1a, 2a
        this.queue.enqueue("3a"); // 0a, 1a, 2a, 3a
        this.queue.enqueue("4a"); // 0a, 1a, 2a, 3a, 4a
        this.queue.enqueue("5a"); // 0a, 1a, 2a, 3a, 4a, 5a
        this.queue.enqueue("6a"); // 0a, 1a, 2a, 3a, 4a, 5a, 6a
        this.queue.enqueue("7a"); // 0a, 1a, 2a, 3a, 4a, 5a, 6a, 7a
        this.queue.enqueue("8a"); // 0a, 1a, 2a, 3a, 4a, 5a, 6a, 7a, 8a
        assertEquals(9, this.queue.size());
        String[] testArray = {"0a", "1a", "2a", "3a", "4a", "5a", "6a", "7a", "8a"};
        assertArrayEquals(testArray, this.queue.getBackingArray());
    }

    @org.junit.Test
    public void testEnqueue3() {
        // queue exceed capacity => resize
        this.queue.enqueue("0a"); // 0a
        this.queue.enqueue("1a"); // 0a, 1a
        this.queue.enqueue("2a"); // 0a, 1a, 2a
        this.queue.enqueue("3a"); // 0a, 1a, 2a, 3a
        this.queue.enqueue("4a"); // 0a, 1a, 2a, 3a, 4a
        this.queue.enqueue("5a"); // 0a, 1a, 2a, 3a, 4a, 5a
        this.queue.enqueue("6a"); // 0a, 1a, 2a, 3a, 4a, 5a, 6a
        this.queue.enqueue("7a"); // 0a, 1a, 2a, 3a, 4a, 5a, 6a, 7a
        this.queue.enqueue("8a"); // 0a, 1a, 2a, 3a, 4a, 5a, 6a, 7a, 8a
        this.queue.enqueue("9a"); // 0a, 1a, 2a, 3a, 4a, 5a, 6a, 7a, 8a, 9a
        this.queue.enqueue("10a"); // 0a, 1a, 2a, 3a, 4a, 5a, 6a, 7a, 8a, 9a, 10a
        this.queue.enqueue("11a"); // 0a, 1a, 2a, 3a, 4a, 5a, 6a, 7a, 8a, 9a, 10a, 11a
        assertEquals(12, this.queue.size());
        assertEquals(18, ((Object[]) this.queue.getBackingArray()).length);
        String[] bigTestArray = {"0a", "1a", "2a", "3a", "4a", "5a", "6a", "7a", "8a", "9a", "10a", "11a", null, null, null, null, null, null};
        assertArrayEquals(bigTestArray, this.queue.getBackingArray());
    }

    @org.junit.Test
    public void dequeue1() {
        // queue not reach full capacity
        this.queue.enqueue("0a"); // 0a
        this.queue.enqueue("1a"); // 0a, 1a
        this.queue.enqueue("2a"); // 0a, 1a, 2a
        this.queue.enqueue("3a"); // 0a, 1a, 2a, 3a
        this.queue.enqueue("4a"); // 0a, 1a, 2a, 3a, 4a
        assertEquals(5, this.queue.size());
        assertEquals("0a", this.queue.dequeue());
        assertEquals(4, this.queue.size());
        assertEquals("1a", this.queue.dequeue());
        assertEquals(3, this.queue.size());
        assertEquals("2a", this.queue.dequeue());
        assertEquals(2, this.queue.size());
        assertEquals("3a", this.queue.dequeue());
        assertEquals(1, this.queue.size());
        assertEquals("4a", this.queue.dequeue());
        assertEquals(0, this.queue.size());
    }

    @org.junit.Test
    public void dequeue2() {
        // queue reach full capacity
        this.queue.enqueue("0a"); // 0a
        this.queue.enqueue("1a"); // 0a, 1a
        this.queue.enqueue("2a"); // 0a, 1a, 2a
        this.queue.enqueue("3a"); // 0a, 1a, 2a, 3a
        this.queue.enqueue("4a"); // 0a, 1a, 2a, 3a, 4a
        this.queue.enqueue("5a"); // 0a, 1a, 2a, 3a, 4a, 5a
        this.queue.enqueue("6a"); // 0a, 1a, 2a, 3a, 4a, 5a, 6a
        this.queue.enqueue("7a"); // 0a, 1a, 2a, 3a, 4a, 5a, 6a, 7a
        this.queue.enqueue("8a"); // 0a, 1a, 2a, 3a, 4a, 5a, 6a, 7a, 8a
        assertEquals(9, this.queue.size());
        assertEquals("0a", this.queue.dequeue());
        assertEquals(8, this.queue.size());
        assertEquals("1a", this.queue.dequeue());
        assertEquals(7, this.queue.size());
        assertEquals("2a", this.queue.dequeue());
        assertEquals(6, this.queue.size());
        assertEquals("3a", this.queue.dequeue());
        assertEquals(5, this.queue.size());
        assertEquals("4a", this.queue.dequeue());
        assertEquals(4, this.queue.size());
        assertEquals("5a", this.queue.dequeue());
        assertEquals(3, this.queue.size());
        assertEquals("6a", this.queue.dequeue());
        assertEquals(2, this.queue.size());
        assertEquals("7a", this.queue.dequeue());
        assertEquals(1, this.queue.size());
        assertEquals("8a", this.queue.dequeue());
        assertEquals(0, this.queue.size());
    }

    @org.junit.Test
    public void dequeue3() {
        // queue exceed capacity
        this.queue.enqueue("0a"); // 0a
        this.queue.enqueue("1a"); // 0a, 1a
        this.queue.enqueue("2a"); // 0a, 1a, 2a
        this.queue.enqueue("3a"); // 0a, 1a, 2a, 3a
        this.queue.enqueue("4a"); // 0a, 1a, 2a, 3a, 4a
        this.queue.enqueue("5a"); // 0a, 1a, 2a, 3a, 4a, 5a
        this.queue.enqueue("6a"); // 0a, 1a, 2a, 3a, 4a, 5a, 6a
        this.queue.enqueue("7a"); // 0a, 1a, 2a, 3a, 4a, 5a, 6a, 7a
        this.queue.enqueue("8a"); // 0a, 1a, 2a, 3a, 4a, 5a, 6a, 7a, 8a
        this.queue.enqueue("9a"); // 0a, 1a, 2a, 3a, 4a, 5a, 6a, 7a, 8a, 9a
        this.queue.enqueue("10a"); // 0a, 1a, 2a, 3a, 4a, 5a, 6a, 7a, 8a, 9a, 10a
        this.queue.enqueue("11a"); // 0a, 1a, 2a, 3a, 4a, 5a, 6a, 7a, 8a, 9a, 10a, 11a
        assertEquals(12, this.queue.size());
        assertEquals("0a", this.queue.dequeue());
        assertEquals(11, this.queue.size());
        assertEquals("1a", this.queue.dequeue());
        assertEquals(10, this.queue.size());
        assertEquals("2a", this.queue.dequeue());
        assertEquals(9, this.queue.size());
        assertEquals("3a", this.queue.dequeue());
        assertEquals(8, this.queue.size());
        assertEquals("4a", this.queue.dequeue());
        assertEquals(7, this.queue.size());
        assertEquals("5a", this.queue.dequeue());
        assertEquals(6, this.queue.size());
        assertEquals("6a", this.queue.dequeue());
        assertEquals(5, this.queue.size());
        assertEquals("7a", this.queue.dequeue());
        assertEquals(4, this.queue.size());
        assertEquals("8a", this.queue.dequeue());
        assertEquals(3, this.queue.size());
        assertEquals("9a", this.queue.dequeue());
        assertEquals(2, this.queue.size());
        assertEquals("10a", this.queue.dequeue());
        assertEquals(1, this.queue.size());
        assertEquals("11a", this.queue.dequeue());
        assertEquals(0, this.queue.size());
    }

    @org.junit.Test
    public void peek() {
        this.queue.enqueue("0a"); // 0a
        this.queue.enqueue("1a"); // 0a, 1a
        this.queue.enqueue("2a"); // 0a, 1a, 2a
        this.queue.enqueue("3a"); // 0a, 1a, 2a, 3a
        this.queue.enqueue("4a"); // 0a, 1a, 2a, 3a, 4a
        this.queue.enqueue("5a"); // 0a, 1a, 2a, 3a, 4a, 5a
        this.queue.enqueue("6a"); // 0a, 1a, 2a, 3a, 4a, 5a, 6a
        this.queue.enqueue("7a"); // 0a, 1a, 2a, 3a, 4a, 5a, 6a, 7a
        this.queue.enqueue("8a"); // 0a, 1a, 2a, 3a, 4a, 5a, 6a, 7a, 8a
        assertEquals("0a", this.queue.peek());
        this.queue.dequeue();
        assertEquals("1a", this.queue.peek());
        this.queue.dequeue();
        assertEquals("2a", this.queue.peek());
        this.queue.dequeue();
        assertEquals("3a", this.queue.peek());
        this.queue.dequeue();
        assertEquals("4a", this.queue.peek());
        this.queue.dequeue();
        assertEquals("5a", this.queue.peek());
        this.queue.dequeue();
        assertEquals("6a", this.queue.peek());
        this.queue.dequeue();
        assertEquals("7a", this.queue.peek());
        this.queue.dequeue();
        assertEquals("8a", this.queue.peek());
        this.queue.dequeue();
    }

    @org.junit.Test
    public void testWrapAround1() {
        // size does not reach full capacity (size=5). Dequeue everything (size = 0), and test if front = 5
        // front is 0
        this.queue.enqueue("0a"); // 0a, null, null, null, null, null, null, null, null
        this.queue.enqueue("1a"); // 0a, 1a, null, null, null, null, null, null, null
        this.queue.enqueue("2a"); // 0a, 1a, 2a, null, null, null, null, null, null
        this.queue.enqueue("3a"); // 0a, 1a, 2a, 3a, null, null, null, null, null
        this.queue.enqueue("4a"); // 0a, 1a, 2a, 3a, 4a, null, null, null, null

        this.queue.dequeue(); // null, 1a, 2a, 3a, 4a, null, null, null, null // front = 1
        this.queue.dequeue(); // null, null, 2a, 3a, 4a, null, null, null, null // front = 2
        this.queue.dequeue(); // null, null, null, 3a, 4a, null, null, null, null // front = 3
        this.queue.dequeue(); // null, null, null, null, 4a, null, null, null, null // front = 4
        this.queue.dequeue(); // null, null, null, nul, null, null, null, null, null // front = 5

        assertEquals(0, this.queue.size());
        String[] testArray = new String[9];
        assertArrayEquals(testArray, this.queue.getBackingArray());

        this.queue.enqueue("10a");
        testArray[5] = "10a";
        assertArrayEquals(testArray, this.queue.getBackingArray());
    }

    @org.junit.Test
    public void testWrapAround2() {
        // queue reaches full capacity. Dequeue everything (size = 0), and test if front = 0 (wrap around)
        this.queue.enqueue("0a"); // 0a
        this.queue.enqueue("1a"); // 0a, 1a
        this.queue.enqueue("2a"); // 0a, 1a, 2a
        this.queue.enqueue("3a"); // 0a, 1a, 2a, 3a
        this.queue.enqueue("4a"); // 0a, 1a, 2a, 3a, 4a
        this.queue.enqueue("5a"); // 0a, 1a, 2a, 3a, 4a, 5a
        this.queue.enqueue("6a"); // 0a, 1a, 2a, 3a, 4a, 5a, 6a
        this.queue.enqueue("7a"); // 0a, 1a, 2a, 3a, 4a, 5a, 6a, 7a
        this.queue.enqueue("8a"); // 0a, 1a, 2a, 3a, 4a, 5a, 6a, 7a, 8a

        this.queue.dequeue(); // null, 1a, 2a, 3a, 4a, 5a, 6a, 7a, 8a // front = 1
        this.queue.dequeue(); // null, null, 2a, 3a, 4a, 5a, 6a, 7a, 8a // front = 2
        this.queue.dequeue(); // null, null, null, 3a, 4a, 5a, 6a, 7a, 8a // front = 3
        this.queue.dequeue(); // null, null, null, null, 4a, 5a, 6a, 7a, 8a // front = 4
        this.queue.dequeue(); // null, null, null, null, null, 5a, 6a, 7a, 8a // front = 5
        this.queue.dequeue(); // null, null, null, null, null, null, 6a, 7a, 8a // front = 6
        this.queue.dequeue(); // null, null, null, null, null, null, null, 7a, 8a  // front = 7
        this.queue.dequeue(); // null, null, null, null, null, null, null, null, 8a // front = 8
        this.queue.dequeue(); // null, null, null, null, null, null, null, null, null // front = 0

        assertEquals(0, this.queue.size());

        this.queue.enqueue("9a"); // 9a, null, null, null, null, null, null, null, null
        assertEquals("9a", this.queue.peek());

        String[] testArray = new String[9];
        testArray[0] = "9a";
        assertArrayEquals(testArray, this.queue.getBackingArray());
    }

    @org.junit.Test
    public void testWrapAround3() {
        // queue reaches full capacity. Dequeue the first 4 (size=5). Enqueue 2 elements (size=7). Test if front=4 and back=2
        this.queue.enqueue("0a"); // 0a // front=0, back=1
        this.queue.enqueue("1a"); // 0a, 1a // front=0, back=2
        this.queue.enqueue("2a"); // 0a, 1a, 2a // front=0, back=3
        this.queue.enqueue("3a"); // 0a, 1a, 2a, 3a // front=0, back=4
        this.queue.enqueue("4a"); // 0a, 1a, 2a, 3a, 4a // front=0, back=5
        this.queue.enqueue("5a"); // 0a, 1a, 2a, 3a, 4a, 5a // front=0, back=6
        this.queue.enqueue("6a"); // 0a, 1a, 2a, 3a, 4a, 5a, 6a // front=0, back=7
        this.queue.enqueue("7a"); // 0a, 1a, 2a, 3a, 4a, 5a, 6a, 7a // front=0, back=8
        this.queue.enqueue("8a"); // 0a, 1a, 2a, 3a, 4a, 5a, 6a, 7a, 8a // front=0, back=0
        this.queue.dequeue(); // null, 1a, 2a, 3a, 4a, 5a, 6a, 7a, 8a // front = 1, back=0
        this.queue.dequeue(); // null, null, 2a, 3a, 4a, 5a, 6a, 7a, 8a // front = 2, back=0
        this.queue.dequeue(); // null, null, null, 3a, 4a, 5a, 6a, 7a, 8a // front = 3, back=0
        this.queue.dequeue(); // null, null, null, null, 4a, 5a, 6a, 7a, 8a // front = 4, back=0
        this.queue.enqueue("9a"); // 9a, null, null, null, 4a, 5a, 6a, 7a, 8a // front = 4, back=1
        this.queue.enqueue("10a"); // 9a, 10a, null, null, 4a, 5a, 6a, 7a, 8a // front = 4, back=2

        assertEquals(7, this.queue.size());
        assertEquals("4a", this.queue.peek());
        String[] testArray = {"9a", "10a", null, null, "4a", "5a", "6a", "7a", "8a"};
        assertArrayEquals(testArray, this.queue.getBackingArray());
        this.queue.enqueue("11a");
        testArray[2] = "11a";
        assertArrayEquals(testArray, this.queue.getBackingArray());
    }

    @org.junit.Test
    public void testWrapAround4() {
        // A series of enqueue and dequeue with wrapping around
        this.queue.enqueue("0a"); // 0a
        this.queue.enqueue("1a"); // 0a, 1a
        this.queue.enqueue("2a"); // 0a, 1a, 2a
        this.queue.enqueue("3a"); // 0a, 1a, 2a, 3a
        this.queue.enqueue("4a"); // 0a, 1a, 2a, 3a, 4a
        this.queue.enqueue("5a"); // 0a, 1a, 2a, 3a, 4a, 5a,

        String[] testArray = {"0a", "1a", "2a", "3a", "4a", "5a", null, null, null};
        assertArrayEquals(testArray, this.queue.getBackingArray());

        this.queue.dequeue();
        this.queue.dequeue();
        this.queue.dequeue(); // null, null, null, 3a, 4a, 5a, null ,null, null

        testArray[0] = null;
        testArray[1] = null;
        testArray[2] = null;
        assertArrayEquals(testArray, this.queue.getBackingArray());
        assertEquals(3, this.queue.size());

        this.queue.enqueue("6a");
        this.queue.enqueue("7a");
        this.queue.enqueue("8a");
        this.queue.enqueue("9a"); // 9a, null, null, 3a, 4a, 5a, 6a ,7a, 8a
        this.queue.dequeue(); // 9a, null, null, null, 4a, 5a, 6a ,7a, 8a
        this.queue.enqueue("10a"); // 9a, 10a, null, null, 4a, 5a, 6a, 7a, 8a
        testArray[3] = null;
        testArray[6] = "6a";
        testArray[7] = "7a";
        testArray[8] = "8a";
        testArray[0] = "9a";
        testArray[1] = "10a";
        assertArrayEquals(testArray, this.queue.getBackingArray());
    }

    @org.junit.Test
    public void testWrapAround5() {
        // enqueue 12 elements (array resize). A series of enqueues and dequeues.
        this.queue.enqueue("0a"); // 0a
        this.queue.enqueue("1a"); // 0a, 1a
        this.queue.enqueue("2a"); // 0a, 1a, 2a
        this.queue.enqueue("3a"); // 0a, 1a, 2a, 3a
        this.queue.enqueue("4a"); // 0a, 1a, 2a, 3a, 4a
        this.queue.enqueue("5a"); // 0a, 1a, 2a, 3a, 4a, 5a
        this.queue.enqueue("6a"); // 0a, 1a, 2a, 3a, 4a, 5a, 6a
        this.queue.enqueue("7a"); // 0a, 1a, 2a, 3a, 4a, 5a, 6a, 7a
        this.queue.enqueue("8a"); // 0a, 1a, 2a, 3a, 4a, 5a, 6a, 7a, 8a
        this.queue.enqueue("9a"); // 0a, 1a, 2a, 3a, 4a, 5a, 6a, 7a, 8a, 9a
        this.queue.enqueue("10a"); // 0a, 1a, 2a, 3a, 4a, 5a, 6a, 7a, 8a, 9a, 10a
        this.queue.enqueue("11a"); // 0a, 1a, 2a, 3a, 4a, 5a, 6a, 7a, 8a, 9a, 10a, 11a, null, null, null, null, null, null

        this.queue.dequeue();
        this.queue.dequeue();
        this.queue.dequeue();
        this.queue.dequeue();
        this.queue.dequeue();
        this.queue.dequeue();
        this.queue.dequeue();
        this.queue.dequeue();
        this.queue.dequeue();
        this.queue.dequeue();
        this.queue.dequeue();
        this.queue.dequeue();

        assertEquals(0, this.queue.size());

        this.queue.enqueue("12a"); // null, null, null, null, null, null, null, null, null, null, null, null, 12a, null, null, null, null, null
        String[] testArray = new String[18];
        testArray[12] = "12a";
        assertArrayEquals(testArray, this.queue.getBackingArray());

        this.queue.enqueue("13a"); // null, null, null, null, null, null, null, null, null, null, null, null, 12a, 13a, null, null, null, null
        this.queue.enqueue("14a"); // null, null, null, null, null, null, null, null, null, null, null, null, 12a, 13a, 14a, null, null, null
        this.queue.enqueue("15a"); // null, null, null, null, null, null, null, null, null, null, null, null, 12a, 13a, 14a, 15a, null, null
        this.queue.enqueue("16a"); // null, null, null, null, null, null, null, null, null, null, null, null, 12a, 13a, 14a, 15a, 16a, null
        this.queue.enqueue("17a"); // null, null, null, null, null, null, null, null, null, null, null, null, 12a, 13a, 14a, 15a, 16a, 17a

        assertEquals(6, this.queue.size());

        this.queue.enqueue("18a"); // 18a, null, null, null, null, null, null, null, null, null, null, null, 12a, 13a, 14a, 15a, 16a, 17a
        this.queue.dequeue();
        this.queue.dequeue();
        this.queue.dequeue();
        this.queue.dequeue();
        this.queue.dequeue();
        this.queue.dequeue();
        this.queue.dequeue();
        assertEquals(0, this.queue.size());

        this.queue.enqueue("19a"); // null, 19a, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null
        testArray[12] = null;
        testArray[1] = "19a";
        assertArrayEquals(testArray, this.queue.getBackingArray());
    }

    @org.junit.Test
    public void testWrapAround6() {
        // resize when front != 0
        this.queue.enqueue("0a"); // 0a
        this.queue.enqueue("1a"); // 0a, 1a
        this.queue.enqueue("2a"); // 0a, 1a, 2a
        this.queue.enqueue("3a"); // 0a, 1a, 2a, 3a
        this.queue.enqueue("4a"); // 0a, 1a, 2a, 3a, 4a
        this.queue.enqueue("5a"); // 0a, 1a, 2a, 3a, 4a, 5a
        this.queue.enqueue("6a"); // 0a, 1a, 2a, 3a, 4a, 5a, 6a
        this.queue.enqueue("7a"); // 0a, 1a, 2a, 3a, 4a, 5a, 6a, 7a
        this.queue.enqueue("8a"); // 0a, 1a, 2a, 3a, 4a, 5a, 6a, 7a, 8a // front=0, back=0
        this.queue.dequeue(); // null, 1a, 2a, 3a, 4a, 5a, 6a, 7a, 8a // front=1, back=0
        this.queue.dequeue(); // null, null, 2a, 3a, 4a, 5a, 6a, 7a, 8a // front=2, back=0
        this.queue.dequeue(); // null, null, null, 3a, 4a, 5a, 6a, 7a, 8a // front=3, back=0
        this.queue.enqueue("9a"); // 9a, null, null, 3a, 4a, 5a, 6a, 7a, 8a // front=3, back=1
        this.queue.enqueue("10a"); // 9a, 10a, null, 3a, 4a, 5a, 6a, 7a, 8a // front=3, back=2
        this.queue.enqueue("11a"); // 9a, 10a, 11a, 3a, 4a, 5a, 6a, 7a, 8a // front=3, back=3
        this.queue.enqueue("12a"); // 3a, 4a, 5a, 6a, 7a, 8a, 9a, 10a, 11a, 12a // front=0, back=10
        assertEquals(10, this.queue.size());
        String[] testArray = new String[18];
        testArray[0] = "3a";
        testArray[1] = "4a";
        testArray[2] = "5a";
        testArray[3] = "6a";
        testArray[4] = "7a";
        testArray[5] = "8a";
        testArray[6] = "9a";
        testArray[7] = "10a";
        testArray[8] = "11a";
        testArray[9] = "12a";
        assertArrayEquals(testArray, this.queue.getBackingArray());
    }

    @org.junit.Test
    public void getBackingArray() {
        this.queue.enqueue("0a"); // 0a
        this.queue.enqueue("1a"); // 0a, 1a
        this.queue.enqueue("2a"); // 0a, 1a, 2a
        this.queue.enqueue("3a"); // 0a, 1a, 2a, 3a
        this.queue.enqueue("4a"); // 0a, 1a, 2a, 3a, 4a
        this.queue.enqueue("5a"); // 0a, 1a, 2a, 3a, 4a, 5a
        this.queue.enqueue("6a"); // 0a, 1a, 2a, 3a, 4a, 5a, 6a
        this.queue.enqueue("7a"); // 0a, 1a, 2a, 3a, 4a, 5a, 6a, 7a
        this.queue.enqueue("8a"); // 0a, 1a, 2a, 3a, 4a, 5a, 6a, 7a, 8a

        String[] testArray = {"0a", "1a", "2a", "3a", "4a", "5a", "6a", "7a", "8a"};

        assertArrayEquals(testArray, this.queue.getBackingArray());
    }

    @org.junit.Test
    public void size() {
        this.queue.enqueue("0a"); // 0a
        this.queue.enqueue("1a"); // 0a, 1a
        this.queue.enqueue("2a"); // 0a, 1a, 2a
        this.queue.enqueue("3a"); // 0a, 1a, 2a, 3a
        this.queue.enqueue("4a"); // 0a, 1a, 2a, 3a, 4a
        this.queue.enqueue("5a"); // 0a, 1a, 2a, 3a, 4a, 5a
        assertEquals(6, this.queue.size());

        this.queue.enqueue("6a"); // 0a, 1a, 2a, 3a, 4a, 5a, 6a
        this.queue.enqueue("7a"); // 0a, 1a, 2a, 3a, 4a, 5a, 6a, 7a
        this.queue.enqueue("8a"); // 0a, 1a, 2a, 3a, 4a, 5a, 6a, 7a, 8a
        this.queue.enqueue("9a"); // 0a, 1a, 2a, 3a, 4a, 5a, 6a, 7a, 8a, 9a
        this.queue.enqueue("10a"); // 0a, 1a, 2a, 3a, 4a, 5a, 6a, 7a, 8a, 9a, 10a
        this.queue.enqueue("11a");
        assertEquals(12, this.queue.size());
    }
}