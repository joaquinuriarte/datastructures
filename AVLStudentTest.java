import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * This is a basic set of unit tests for AVL.
 * Some of the tests included here are from the file provided by CS 1332 TAs.
 *
 * @author Adavya Bhutani
 * @version 1.0
 */
public class AVLStudentTest {

    private static final int TIMEOUT = 200;
    private AVL<Integer> tree;

    @Before
    public void setup() {
        tree = new AVL<>();
    }

    @Test(timeout = TIMEOUT)
    public void testInitialization() {
        assertEquals(0, tree.size());
        assertNull(tree.getRoot());
    }

    @Test(timeout = TIMEOUT)
    public void testConstructor() {
        /*
              1
             / \
            0   2
        */

        List<Integer> toAdd = new ArrayList<>();
        toAdd.add(1);
        toAdd.add(0);
        toAdd.add(2);
        tree = new AVL<>(toAdd);

        assertEquals(3, tree.size());

        AVLNode<Integer> root = tree.getRoot();
        assertEquals((Integer) 1, root.getData());
        assertEquals(1, root.getHeight());
        assertEquals(0, root.getBalanceFactor());
        AVLNode<Integer> left = root.getLeft();
        assertEquals((Integer) 0, left.getData());
        assertEquals(0, left.getHeight());
        assertEquals(0, left.getBalanceFactor());
        AVLNode<Integer> right = root.getRight();
        assertEquals((Integer) 2, right.getData());
        assertEquals(0, right.getHeight());
        assertEquals(0, right.getBalanceFactor());
    }

    @Test(timeout = TIMEOUT)
    public void testAdd() {
        // Right rotate
        /*
                2
               /
              1
             /
            0

            ->

              1
             / \
            0   2
         */

        tree.add(2);
        tree.add(1);
        tree.add(0);

        assertEquals(3, tree.size());

        AVLNode<Integer> root = tree.getRoot();
        assertEquals((Integer) 1, root.getData());
        assertEquals(1, root.getHeight());
        assertEquals(0, root.getBalanceFactor());
        AVLNode<Integer> left = root.getLeft();
        assertEquals((Integer) 0, left.getData());
        assertEquals(0, left.getHeight());
        assertEquals(0, left.getBalanceFactor());
        AVLNode<Integer> right = root.getRight();
        assertEquals((Integer) 2, right.getData());
        assertEquals(0, right.getHeight());
        assertEquals(0, right.getBalanceFactor());


        // Right left rotate
        /*
            0
             \
              2
             /
            1

            ->

              1
             / \
            0   2
         */

        tree = new AVL<>();
        tree.add(0);
        tree.add(2);
        tree.add(1);

        assertEquals(3, tree.size());

        assertEquals(3, tree.size());
        root = tree.getRoot();
        assertEquals((Integer) 1, root.getData());
        assertEquals(1, root.getHeight());
        assertEquals(0, root.getBalanceFactor());
        left = root.getLeft();
        assertEquals((Integer) 0, left.getData());
        assertEquals(0, left.getHeight());
        assertEquals(0, left.getBalanceFactor());
        right = root.getRight();
        assertEquals((Integer) 2, right.getData());
        assertEquals(0, right.getHeight());
        assertEquals(0, right.getBalanceFactor());
    }

    @Test(timeout = TIMEOUT)
    public void testAdd2() {
        // Left rotate
        /*
              1
               \
                2
                 \
                  3
                   \
                    4

            ->

              2
             / \
            1   3
                 \
                  4
         */

        tree.add(1);
        tree.add(2);
        tree.add(3);
        tree.add(4);

        assertEquals(4, tree.size());

        AVLNode<Integer> root = tree.getRoot();
        assertEquals((Integer) 2, root.getData());
        assertEquals(2, root.getHeight());
        assertEquals(-1, root.getBalanceFactor());
        AVLNode<Integer> left = root.getLeft();
        assertEquals((Integer) 1, left.getData());
        assertEquals(0, left.getHeight());
        assertEquals(0, left.getBalanceFactor());
        AVLNode<Integer> right = root.getRight();
        assertEquals((Integer) 3, right.getData());
        assertEquals(1, right.getHeight());
        assertEquals(-1, right.getBalanceFactor());
        AVLNode<Integer> rightRight = root.getRight().getRight();
        assertEquals((Integer) 4, rightRight.getData());
        assertEquals(0, rightRight.getHeight());
        assertEquals(0, rightRight.getBalanceFactor());


        // Left right rotate
        /*
            4
           /
          1
           \
            2
             \
              3

            ->

              2
             / \
            1   4
               /
              3
         */

        tree = new AVL<>();
        tree.add(4);
        tree.add(1);
        tree.add(2);
        tree.add(3);

        assertEquals(4, tree.size());

        root = tree.getRoot();
        assertEquals((Integer) 2, root.getData());
        assertEquals(2, root.getHeight());
        assertEquals(-1, root.getBalanceFactor());
        left = root.getLeft();
        assertEquals((Integer) 1, left.getData());
        assertEquals(0, left.getHeight());
        assertEquals(0, left.getBalanceFactor());
        right = root.getRight();
        assertEquals((Integer) 4, right.getData());
        assertEquals(1, right.getHeight());
        assertEquals(1, right.getBalanceFactor());
        AVLNode<Integer> rightLeft = root.getRight().getLeft();
        assertEquals((Integer) 3, rightLeft.getData());
        assertEquals(0, rightLeft.getHeight());
        assertEquals(0, rightLeft.getBalanceFactor());
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testAdd3() {
        tree = new AVL<>();
        tree.add(1);
        tree.add(null);
    }

    @Test(timeout = TIMEOUT)
    public void testRemove() {
        Integer temp = 1;

        /*
                  3
                /   \
              1      4
             / \
            0   2

            ->

                  3
                /   \
              2      4
             /
            0   
         */


        tree.add(3);
        tree.add(temp);
        tree.add(4);
        tree.add(0);
        tree.add(2);
        assertEquals(5, tree.size());

        assertSame(temp, tree.remove(1));
        assertEquals(4, tree.size());
        AVLNode<Integer> root = tree.getRoot();
        assertEquals((Integer) 3, root.getData());
        assertEquals(2, root.getHeight());
        assertEquals(1, root.getBalanceFactor());
        AVLNode<Integer> left = root.getLeft();
        assertEquals((Integer) 2, left.getData());
        assertEquals(1, left.getHeight());
        assertEquals(1, left.getBalanceFactor());
        AVLNode<Integer> right = root.getRight();
        assertEquals((Integer) 4, right.getData());
        assertEquals(0, right.getHeight());
        assertEquals(0, right.getBalanceFactor());
        AVLNode<Integer> leftLeft = left.getLeft();
        assertEquals((Integer) 0, leftLeft.getData());
        assertEquals(0, leftLeft.getHeight());
        assertEquals(0, leftLeft.getBalanceFactor());
    }

    @Test(timeout = TIMEOUT)
    public void testRemove2() {
        Integer temp = 3;

        /*
                  3
                /   \
              1      4
             / \
            0   2

            ->

                  1
                /   \
               0     4
                    /
                   2
         */


        tree.add(temp);
        tree.add(4);
        tree.add(1);
        tree.add(0);
        tree.add(2);
        assertEquals(5, tree.size());

        assertSame(temp, tree.remove(3));
        assertEquals(4, tree.size());
        AVLNode<Integer> root = tree.getRoot();
        assertEquals((Integer) 1, root.getData());
        assertEquals(2, root.getHeight());
        assertEquals(-1, root.getBalanceFactor());
        AVLNode<Integer> left = root.getLeft();
        assertEquals((Integer) 0, left.getData());
        assertEquals(0, left.getHeight());
        assertEquals(0, left.getBalanceFactor());
        AVLNode<Integer> right = root.getRight();
        assertEquals((Integer) 4, right.getData());
        assertEquals(1, right.getHeight());
        assertEquals(1, right.getBalanceFactor());
        AVLNode<Integer> rightLeft = right.getLeft();
        assertEquals((Integer) 2, rightLeft.getData());
        assertEquals(0, rightLeft.getHeight());
        assertEquals(0, rightLeft.getBalanceFactor());
    }

    @Test(timeout = TIMEOUT)
    public void testRemove3() {
        Integer temp = 4;

        /*
                  3
                /   \
              1      4
             / \
            0   2

            ->

                  1
                /   \
               0     3
                    /
                   2
         */


        tree.add(3);
        tree.add(temp);
        tree.add(1);
        tree.add(0);
        tree.add(2);
        assertEquals(5, tree.size());

        assertSame(temp, tree.remove(4));
        assertEquals(4, tree.size());
        AVLNode<Integer> root = tree.getRoot();
        assertEquals((Integer) 1, root.getData());
        assertEquals(2, root.getHeight());
        assertEquals(-1, root.getBalanceFactor());
        AVLNode<Integer> left = root.getLeft();
        assertEquals((Integer) 0, left.getData());
        assertEquals(0, left.getHeight());
        assertEquals(0, left.getBalanceFactor());
        AVLNode<Integer> right = root.getRight();
        assertEquals((Integer) 3, right.getData());
        assertEquals(1, right.getHeight());
        assertEquals(1, right.getBalanceFactor());
        AVLNode<Integer> rightLeft = right.getLeft();
        assertEquals((Integer) 2, rightLeft.getData());
        assertEquals(0, rightLeft.getHeight());
        assertEquals(0, rightLeft.getBalanceFactor());
    }

    @Test(timeout = TIMEOUT)
    public void testRemove4() {
        Integer temp = 0;

        /*
                  3
                /   \
              1      4
             / \
            0   2

            ->

                  3
                /   \
               1     4
                \
                 2
         */


        tree.add(3);
        tree.add(4);
        tree.add(1);
        tree.add(temp);
        tree.add(2);
        assertEquals(5, tree.size());

        assertSame(temp, tree.remove(0));
        assertEquals(4, tree.size());
        AVLNode<Integer> root = tree.getRoot();
        assertEquals((Integer) 3, root.getData());
        assertEquals(2, root.getHeight());
        assertEquals(1, root.getBalanceFactor());
        AVLNode<Integer> left = root.getLeft();
        assertEquals((Integer) 1, left.getData());
        assertEquals(1, left.getHeight());
        assertEquals(-1, left.getBalanceFactor());
        AVLNode<Integer> right = root.getRight();
        assertEquals((Integer) 4, right.getData());
        assertEquals(0, right.getHeight());
        assertEquals(0, right.getBalanceFactor());
        AVLNode<Integer> leftRight = left.getRight();
        assertEquals((Integer) 2, leftRight.getData());
        assertEquals(0, leftRight.getHeight());
        assertEquals(0, leftRight.getBalanceFactor());
    }

    @Test(timeout = TIMEOUT)
    public void testRemove5() {
        Integer temp = 5;

        /*
                  4
                /   \
              1      5
             / \      \
            0   2      6
                 \
                  3

            ->

                  2
                /   \
               1     4
              /     / \
             0     3   6
         */


        tree.add(4);
        tree.add(1);
        tree.add(temp);
        tree.add(6);
        tree.add(0);
        tree.add(2);
        tree.add(3);
        assertEquals(7, tree.size());

        assertSame(temp, tree.remove(5));
        assertEquals(6, tree.size());
        AVLNode<Integer> root = tree.getRoot();
        assertEquals((Integer) 2, root.getData());
        assertEquals(2, root.getHeight());
        assertEquals(0, root.getBalanceFactor());
        AVLNode<Integer> left = root.getLeft();
        assertEquals((Integer) 1, left.getData());
        assertEquals(1, left.getHeight());
        assertEquals(1, left.getBalanceFactor());
        AVLNode<Integer> right = root.getRight();
        assertEquals((Integer) 4, right.getData());
        assertEquals(1, right.getHeight());
        assertEquals(0, right.getBalanceFactor());
        AVLNode<Integer> rightLeft = right.getLeft();
        assertEquals((Integer) 3, rightLeft.getData());
        assertEquals(0, rightLeft.getHeight());
        assertEquals(0, rightLeft.getBalanceFactor());
        AVLNode<Integer> rightRight = root.getRight().getRight();
        assertEquals((Integer) 6, rightRight.getData());
        assertEquals(0, rightRight.getHeight());
        assertEquals(0, rightRight.getBalanceFactor());
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testRemove6() {
        tree = new AVL<>();
        Integer temp = 1;
        tree.add(temp);
        assertSame((Integer) temp, tree.remove(1));
        assertEquals(0, tree.size());
        tree.remove(null);
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testRemove7() {
        tree = new AVL<>();
        Integer temp = 1;
        tree.add(temp);
        tree.remove(2);
    }

    @Test(timeout = TIMEOUT)
    public void testGet() {
        Integer temp1 = 1;
        Integer temp0 = 0;
        Integer temp2 = 2;
        Integer temp3 = 3;

        /*
               1
             /   \
            0     2
                    \
                     3
         */

        tree.add(temp1);
        tree.add(temp0);
        tree.add(temp2);
        tree.add(temp3);
        assertEquals(4, tree.size());

        // We want to make sure the data we retrieve is the one from the tree,
        // not the data that was passed in.
        assertSame(temp0, tree.get(0));
        assertSame(temp1, tree.get(1));
        assertSame(temp2, tree.get(2));
        assertSame(temp3, tree.get(3));
    }

    @Test(timeout = TIMEOUT)
    public void testGet2() {
        Integer temp1 = 1;
        Integer temp0 = 0;
        Integer temp2 = 2;
        Integer temp3 = 3;
        Integer temp4 = 4;

        /*
               1
             /   \
            0     2
                    \
                     3
                      \
                       4

         ->

               1
             /   \
            0     3
                 / \
                2   4
         */

        tree.add(temp1);
        tree.add(temp0);
        tree.add(temp2);
        tree.add(temp3);
        tree.add(temp4);
        assertEquals(5, tree.size());

        // We want to make sure the data we retrieve is the one from the tree,
        // not the data that was passed in.
        assertSame(temp0, tree.get(0));
        assertSame(temp1, tree.get(1));
        assertSame(temp2, tree.get(2));
        assertSame(temp3, tree.get(3));
        assertSame(temp4, tree.get(4));
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testGet3() {
        tree.add(1);
        tree.get(null);
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testGet4() {
        tree.add(1);
        tree.get(2);
    }

    @Test(timeout = TIMEOUT)
    public void testContains() {
        /*
               1
             /   \
            0     2
                    \
                     3
         */

        tree.add(1);
        tree.add(0);
        tree.add(2);
        tree.add(3);
        assertEquals(4, tree.size());

        assertTrue(tree.contains(0));
        assertTrue(tree.contains(1));
        assertTrue(tree.contains(2));
        assertTrue(tree.contains(3));
        assertFalse(tree.contains(5));
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testContains2() {
        tree.add(1);
        tree.contains(null);
    }

    @Test(timeout = TIMEOUT)
    public void testHeight() {
        /*
                     3
                   /   \
                 1      4
                / \
               0   2
         */

        tree.add(3);
        tree.add(1);
        tree.add(4);
        tree.add(0);
        tree.add(2);

        assertEquals(2, tree.height());
    }

    @Test(timeout = TIMEOUT)
    public void testHeight2() {
        /*
            4
           /
          1
           \
            2
             \
              3

            ->

              2
             / \
            1   4
               /
              3
         */

        tree = new AVL<>();
        tree.add(4);
        tree.add(1);
        tree.add(2);
        tree.add(3);

        assertEquals(4, tree.size());
        assertEquals(2, tree.height());
    }

    @Test(timeout = TIMEOUT)
    public void testHeight3() {
        /*
               1
             /   \
            0     2
                    \
                     3
                      \
                       4

         ->

               1
             /   \
            0     3
                 / \
                2   4
         */

        tree.add(1);
        tree.add(0);
        tree.add(2);
        tree.add(3);
        tree.add(4);
        assertEquals(5, tree.size());

        assertEquals(2, tree.height());
    }

    @Test(timeout = TIMEOUT)
    public void testClear() {
        /*
              1
             / \
            0   2
        */

        List<Integer> toAdd = new ArrayList<>();
        toAdd.add(1);
        toAdd.add(0);
        toAdd.add(2);
        tree = new AVL<>(toAdd);

        tree.clear();
        assertEquals(0, tree.size());
        assertNull(tree.getRoot());
    }

    @Test(timeout = TIMEOUT)
    public void testMaxDeepestNode() {
        /*
                     2
                  /    \
                 0      3
                  \
                   1
         */
        Integer deepest = 1;

        tree.add(2);
        tree.add(0);
        tree.add(3);
        tree.add(deepest);

        assertSame(deepest, tree.maxDeepestNode());
    }

    @Test(timeout = TIMEOUT)
    public void testMaxDeepestNode2() {
        /*
                  4
                /   \
              1      5
             / \      \
            0   2      6

         */

        Integer deepest = 6;
        tree.add(4);
        tree.add(1);
        tree.add(5);
        tree.add(deepest);
        tree.add(0);
        tree.add(2);
        assertEquals(6, tree.size());
        assertSame(deepest, tree.maxDeepestNode());
    }

    @Test(timeout = TIMEOUT)
    public void testMaxDeepestNode3() {
        /*
               1
             /   \
            0     2
                    \
                     3
                      \
                       4

         ->

               1
             /   \
            0     3
                 / \
                2   4
         */

        Integer deepest = 4;
        tree.add(1);
        tree.add(0);
        tree.add(2);
        tree.add(3);
        tree.add(deepest);
        assertEquals(5, tree.size());
        assertSame(deepest, tree.maxDeepestNode());
    }

    @Test(timeout = TIMEOUT)
    public void testMaxDeepestNode4() {
        /*
            4
           /
          1
           \
            2
             \
              3

            ->

              2
             / \
            1   4
               /
              3
         */

        Integer deepest = 3;
        tree = new AVL<>();
        tree.add(4);
        tree.add(1);
        tree.add(2);
        tree.add(deepest);

        assertEquals(4, tree.size());
        assertSame(deepest, tree.maxDeepestNode());
    }

    @Test(timeout = TIMEOUT)
    public void testSuccessor() {
        /*
                76
              /    \
            34      90
             \      /
              40  81
         */

        tree.add(76);
        tree.add(34);
        tree.add(90);
        tree.add(40);
        tree.add(81);

        assertEquals((Integer) 81, tree.successor(76));
        assertEquals((Integer) 76, tree.successor(40));
        assertEquals((Integer) 90, tree.successor(81));
    }

    @Test(timeout = TIMEOUT)
    public void testSuccessor2() {
        /*
               1
             /   \
            0     2
                    \
                     3
                      \
                       4

         ->

               1
             /   \
            0     3
                 / \
                2   4
         */

        tree.add(1);
        tree.add(0);
        tree.add(2);
        tree.add(3);
        tree.add(4);
        assertEquals(5, tree.size());

        assertEquals((Integer) 1, tree.successor(0));
        assertEquals((Integer) 2, tree.successor(1));
        assertEquals((Integer) 3, tree.successor(2));
        assertEquals((Integer) 4, tree.successor(3));
        assertEquals(null, tree.successor(4));
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testSuccessor3() {
        tree.add(1);
        tree.successor(null);
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testSuccessor4() {
        tree.add(1);
        tree.successor(2);
    }
}
