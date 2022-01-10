import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.*;

/**
 * Some test cases for the Circular Linked List
 *
 * @author Blake Miller (CS 1332 TA's wrote the set up code)
 */

public class BlakeMillerCLLTest {
	private static final int TIMEOUT = 200;
	private CircularSinglyLinkedList<String> list;

	@Before
	public void setUp() {
		list = new CircularSinglyLinkedList<>();
	}

	@Test(timeout = TIMEOUT)
	public void testAddToFrontAndBack() {
		list.addToBack("2a");
		list.addToFront("0a");
		list.addAtIndex(1, "1a");

		CircularSinglyLinkedListNode<String> cur = list.getHead();
		assertEquals("0a", cur.getData());

		cur = cur.getNext();
		assertEquals("1a", cur.getData());

		cur = cur.getNext();
		assertEquals("2a", cur.getData());
	}

	@Test(timeout = TIMEOUT)
	public void testImmediateRemoveFromFront() {
		list.addToBack("2a");
		list.removeFromFront();

		assertNull(list.getHead());
		assertTrue(list.isEmpty());
	}

	@Test(timeout = TIMEOUT)
	public void testRemoveLastOccurrence() {
		list.addToBack("2a");
		String temp = "2a";
		String test = list.removeLastOccurrence(temp);

		temp = "v";

		assertEquals("2a", test);
	}

	@Test(timeout = TIMEOUT)
	public void testClear() {
		list.addToFront("e");
		list.addToBack("f");
		list.addToFront("g");
		list.addToBack("h");

		list.clear();
		assertEquals(0, list.size());
		assertNull(list.getHead());
	}

	@Test(timeout = TIMEOUT)
	public void testAddToFrontAndBack2() {
		list.addToFront("2"); // 2
		list.addToFront("1"); // 1, 2
		list.addToBack("4"); // 1, 2, 4
		list.addAtIndex(2, "3"); // 1, 2, 3, 4
		list.addAtIndex(0, "0"); // 0, 1, 2, 3, 4
		list.addAtIndex(5, "5"); // 0, 1, 2, 3, 4, 5

		String[] expected = new String[] {"0", "1", "2", "3", "4", "5"};

		assertArrayEquals(expected, list.toArray());
	}

	@Test(timeout = TIMEOUT)
	public void removeLastOccurrenceTest() {
		String temp1 = new String("a");
		String temp2 = new String("a");
		String temp3 = new String("a");

		temp1 = "1";

		list.addToFront(temp3);
		list.addToFront(temp1);
		list.addToBack(temp2);

		String removed3 = list.removeLastOccurrence("a");
		String removed2 = list.removeLastOccurrence("a");

		temp2 = "2";
		temp3 = "3";

		assertEquals("a", removed3);
		assertEquals("a", removed2);

		assertEquals(1, list.size());
	}

	@Test(timeout = TIMEOUT, expected = java.util.NoSuchElementException.class)
	public void removeLastOccurrenceTestWithFail() {
		String temp1 = new String("a");
		String temp2 = new String("a");
		String temp3 = new String("a");

		temp1 = "1";

		list.addToFront(temp3);
		list.addToFront(temp1);
		list.addToBack(temp2);

		String removed3 = list.removeLastOccurrence("a");
		String removed2 = list.removeLastOccurrence("a");

		temp2 = "2";
		temp3 = "3";

		assertEquals("a", removed3);
		assertEquals("a", removed2);
		assertNull(list.removeLastOccurrence("a"));

		assertEquals(1, list.size());
	}

	@Test(timeout = TIMEOUT)
	public void removeTest() {
		list.addToFront("a");
		list.addToBack("b");
		list.addToBack("c");
		list.addToBack("d");
		list.addToBack("e");
		list.addToFront("alphabet");
		list.addToBack("alphabet");

		list.removeLastOccurrence("alphabet"); // alphabet, a, b, c, d, e
		list.removeFromFront(); // a, b, c, d, e
		assertEquals("a", list.getHead().getData());

		list.removeFromBack(); // a, b, c, d
		list.removeFromFront(); // b, c, d

		String justRemoved = list.removeFromFront(); // c, d
		assertEquals("b", justRemoved);

		justRemoved = list.removeFromBack(); // c
		assertEquals("d", justRemoved);

		assertEquals(1, list.size());

		assertEquals(list.getHead(), list.getHead().getNext());
	}

	@Test(timeout = TIMEOUT)
	public void afterLastNodeTest() {
		list.addToFront("a");
		list.addToBack("c");
		list.addAtIndex(1, "b");

		list.removeLastOccurrence("a");

		assertEquals(list.getHead(), list.getHead().getNext().getNext());
	}
}