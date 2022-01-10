import org.hamcrest.MatcherAssert;
import org.hamcrest.core.AnyOf;
import org.hamcrest.core.IsEqual;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class AVLTestLeo {
    public static final int adds = 10000;
    public static final int removes = 5000;
    public static final int reps = 30;

    private AVL<Integer> tree;
    private Set<Integer> set;

    @Before
    public void init() {
        tree = new AVL<>();
        set = new TreeSet<>();
    }

    private List<Integer> inOrder() {
        List<Integer> list = new ArrayList<>();
        rInOrder(list, tree.getRoot());
        return list;
    }

    private void rInOrder(List<Integer> list, AVLNode<Integer> current) {
        if (current != null) {
            rInOrder(list, current.getLeft());
            MatcherAssert.assertThat(current.getBalanceFactor(), AnyOf.anyOf(IsEqual.equalTo(-1),
                                                                             IsEqual.equalTo(0),
                                                                             IsEqual.equalTo(1)));
            int leftHeight = current.getLeft() != null ? current.getLeft().getHeight() : -1;
            int rightHeight = current.getRight() != null ? current.getRight().getHeight() : -1;
            assertEquals(current.getHeight(), Math.max(leftHeight, rightHeight) + 1);
            assertEquals(current.getBalanceFactor(), leftHeight - rightHeight);
            list.add(current.getData());
            rInOrder(list, current.getRight());
        }
    }

    private Object[] sortedSetArray() {
        return set.stream().sorted().toArray();
    }

    private void compare() {
        assertArrayEquals(sortedSetArray(), inOrder().toArray());
    }

    private void add(int n) {
        Random rng = new Random();

        for (int i = 0; i < n; i++) {
            Integer element = rng.nextInt();
            if (set.add(element)) {
                tree.add(element);
            } else {
                i--;
            }
        }
    }

    private void remove(int n) {
        Iterator<Integer> it = set.iterator();
        for (int i = 0; i < n; i++) {
            tree.remove(it.next());
            it.remove();
        }
    }

    @Test
    public void testDefaultConstructor() {
        assertEquals(0, tree.size());
        assertNull(tree.getRoot());
    }

    @Test
    public void testCollectionConstructor() {
        for (int count = 0; count < reps; count++) {
            Random rng = new Random();

            for (int i = 0; i < adds; i++) {
                Integer element = rng.nextInt();
                if (!set.add(element)) {
                    i--;
                }
            }

            tree = new AVL<>(set);

            compare();

            init();
        }
    }

    @Test
    public void testAdd() {
        for (int count = 0; count < reps; count++) {
            add(adds);

            compare();

            init();
        }
    }

    @Test
    public void testRemove() {
        for (int count = 0; count < reps; count++) {
            add(adds);
            remove(removes);

            compare();

            init();
        }
    }

    @Test
    public void testGet() {
        for (int count = 0; count < reps; count++) {
            add(adds);
            remove(removes);

            for (Integer i : set) {
                assertEquals(i, tree.get(i));
            }

            init();
        }
    }

    @Test
    public void testContains() {
        for (int count = 0; count < reps; count++) {
            add(adds);
            remove(removes);

            for (Integer i : set) {
                assertTrue(tree.contains(i));
            }

            init();
        }
    }

    @Test
    public void testHeight() {
        for (int count = 0; count < reps; count++) {
            assertSame(-1, tree.height());

            int previousHeight = tree.height();

            Random rng = new Random();

            for (int i = 0; i < adds; i++) {
                Integer element = rng.nextInt();
                if (set.add(element)) {
                    tree.add(element);
                    MatcherAssert.assertThat(tree.height() - previousHeight, AnyOf.anyOf(IsEqual.equalTo(1),
                            IsEqual.equalTo(0)));
                    previousHeight = tree.height();
                } else {
                    i--;
                }
            }

            init();
        }
    }

    @Test
    public void testClear() {
        for (int count = 0; count < reps; count++) {
            add(adds);
            remove(removes);

            tree.clear();
            set.clear();

            assertEquals(0, tree.size());
            assertNull(tree.getRoot());

            init();
        }
    }

    @Test
    public void testMaxDeepestNode() {
        for (int count = 0; count < reps; count++) {
            add(adds);
            remove(removes);

            List<AVLNode<Integer>> fringe = new ArrayList<>(1);
            fringe.add(tree.getRoot());

            Integer max = null;
            while (!fringe.isEmpty()) {
                List<AVLNode<Integer>> next = new ArrayList<>(fringe.size() * 2);
                max = null;
                for (AVLNode<Integer> node : fringe) {
                    if (node != null) {
                        if (max == null || max.compareTo(node.getData()) < 0) {
                            max = node.getData();
                        }
                        if (node.getLeft() != null) next.add(node.getLeft());
                        if (node.getRight() != null) next.add(node.getRight());
                    }
                }
                fringe = next;
            }

            assertEquals(max, tree.maxDeepestNode());

            init();
        }
    }

    @Test
    public void testSuccessor() {
        for (int count = 0; count < reps; count++) {
            add(adds);
            remove(removes);

            Object[] sortedSetArray = sortedSetArray();
            int index;
            for (index = 0; index < sortedSetArray.length - 1; index++) {
                Integer i = (Integer) sortedSetArray[index];
                Integer j = (Integer) sortedSetArray[index + 1];
                assertEquals(j, tree.successor(i));
            }
            assertNull(tree.successor((Integer) sortedSetArray[index]));

            init();
        }
    }
}
