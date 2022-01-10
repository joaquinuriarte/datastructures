import java.util.Collection;
import java.util.HashSet;
import java.util.NoSuchElementException;

/**
 * Your implementation of an AVL.
 *
 * @author Joaquin Uriarte
 * @version 1.0
 * @userid juriarte3
 * @GTID 903485636
 *
 * Collaborators: LIST ALL COLLABORATORS YOU WORKED WITH HERE
 *
 * Resources: LIST ALL NON-COURSE RESOURCES YOU CONSULTED HERE
 */
public class AVL<T extends Comparable<? super T>> {

    // Do not add new instance variables or modify existing ones.
    private AVLNode<T> root;
    private int size;

    /**
     * Constructs a new AVL.
     *
     * This constructor should initialize an empty AVL.
     *
     * Since instance variables are initialized to their default values, there
     * is no need to do anything for this constructor.
     */
    public AVL() {
        // DO NOT IMPLEMENT THIS CONSTRUCTOR!
    }

    /**
     * Constructs a new AVL.
     *
     * This constructor should initialize the AVL with the data in the
     * Collection. The data should be added in the same order it is in the
     * Collection.
     *
     * @param data the data to add to the tree
     * @throws java.lang.IllegalArgumentException if data or any element in data
     *                                            is null
     */
    public AVL(Collection<T> data) {
        if (data == null) {
            throw new IllegalArgumentException("Data entered is null. Can't construct AVL with null data.");
        } else {
            this.size = 0;
            for (T datas: data) {
                add(datas);
            }
        }
    }

    /**
     * Adds the element to the tree.
     *
     * Start by adding it as a leaf like in a regular BST and then rotate the
     * tree as necessary.
     *
     * If the data is already in the tree, then nothing should be done (the
     * duplicate shouldn't get added, and size should not be incremented).
     *
     * Remember to recalculate heights and balance factors while going back
     * up the tree after adding the element, making sure to rebalance if
     * necessary.
     *
     * @param data the data to add
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data entered is null. Can't add null data to AVL.");
        } else {
            this.root = addHelp(root, data);
        }
    }

    /**
     * Add helper method. Uses pointer reinforcement to fulfill task.
     *
     * @param curr the current node of the tree
     * @param data the data to be added to the tree
     * @return the node of the tree in which the method was called.
     */
    private AVLNode<T> addHelp(AVLNode<T> curr, T data) {
        if (curr == null) {
            this.size++;
            return new AVLNode<>(data);
        } else if (data.compareTo(curr.getData()) < 0) {
            curr.setLeft(addHelp(curr.getLeft(), data));
        } else if (data.compareTo(curr.getData()) > 0) {
            curr.setRight(addHelp(curr.getRight(), data));
        } else {
            return curr;
        }
        heightAndBF(curr);
        return balanceMethod(curr);
    }

    /**
     * Method for calculating height and BF of a node
     *
     * @param curr the node that will be calculated
     */
    private void heightAndBF(AVLNode<T> curr) {
        if (height(curr.getLeft()) >= height(curr.getRight())) {
            curr.setHeight(height(curr.getLeft()) + 1);
        } else {
            curr.setHeight(height(curr.getRight()) + 1);
        }
        curr.setBalanceFactor(height(curr.getLeft()) - height(curr.getRight()));
    }

    /**
     * Method for balancing AVL tree.
     *
     * @param curr the current node to balance
     * @return the new balanced node
     */
    private AVLNode<T> balanceMethod(AVLNode<T> curr) {
        if (curr.getBalanceFactor() < -1) {
            if (curr.getRight().getBalanceFactor() > 0) {
                curr.setRight(rRotate(curr.getRight()));
            }
            curr = lRotate(curr);
        } else if (curr.getBalanceFactor() > 1) {
            if (curr.getLeft().getBalanceFactor() < 0) {
                curr.setLeft(lRotate(curr.getLeft()));
            }
            curr = rRotate(curr);
        }
        return curr;
    }

    /**
     * Method for rotating right
     *
     * @param curr current node to perform rotation
     * @return node after right rotation preformed
     */
    private AVLNode<T> rRotate(AVLNode<T> curr) {
        AVLNode<T> helpNode = curr.getLeft();
        curr.setLeft(helpNode.getRight());
        helpNode.setRight(curr);
        heightAndBF(curr);
        heightAndBF(helpNode);
        return helpNode;
    }

    /**
     * Method for rotating left
     *
     * @param curr current node to perform rotation
     * @return node after left rotation preformed
     */
    private AVLNode<T> lRotate(AVLNode<T> curr) {
        AVLNode<T> helpNode = curr.getRight();
        curr.setRight(helpNode.getLeft());
        helpNode.setLeft(curr);
        heightAndBF(curr);
        heightAndBF(helpNode);
        return helpNode;
    }

    /**
     * Removes and returns the element from the tree matching the given
     * parameter.
     *
     * There are 3 cases to consider:
     * 1: The node containing the data is a leaf (no children). In this case,
     * simply remove it.
     * 2: The node containing the data has one child. In this case, simply
     * replace it with its child.
     * 3: The node containing the data has 2 children. Use the successor to
     * replace the data, NOT predecessor. As a reminder, rotations can occur
     * after removing the successor node.
     *
     * Remember to recalculate heights and balance factors while going back
     * up the tree after removing the element, making sure to rebalance if
     * necessary.
     *
     * Do not return the same data that was passed in. Return the data that
     * was stored in the tree.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * @param data the data to remove
     * @return the data that was removed
     * @throws java.lang.IllegalArgumentException if data is null
     * @throws java.util.NoSuchElementException   if the data is not found
     */
    public T remove(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data entered is null. AVL doesn't contain null data.");
        } else {
            AVLNode<T> dummy = new AVLNode(null);
            this.root = removeHelper(this.root, data, dummy);
            return dummy.getData();
        }
    }

    /**
     * Removes node from the AVL and returns it
     *
     * @param curr current root of the AVL
     * @param data the data to be removed from the AVL
     * @param dummy a dummy node to hold the removed data
     * @return the removed data of the AVL
     */
    private AVLNode<T> removeHelper(AVLNode<T> curr, T data, AVLNode<T> dummy) {
        if (curr == null) {
            throw new NoSuchElementException("Data requested for removal was not found in the AVL.");
        } else if (data.compareTo(curr.getData()) < 0) {
            curr.setLeft(removeHelper(curr.getLeft(), data, dummy));
        } else if (data.compareTo(curr.getData()) > 0) {
            curr.setRight(removeHelper(curr.getRight(), data, dummy));
        } else {
            dummy.setData(curr.getData());
            size--;
            if (curr.getLeft() == null) {
                return curr.getRight();
            } else if (curr.getRight() == null) {
                return curr.getLeft();
            } else {
                AVLNode<T> helper = new AVLNode<>(null);
                curr.setRight(successorFinder(helper, curr.getRight()));
                curr.setData(helper.getData());
            }
        }
        heightAndBF(curr);
        return balanceMethod(curr);
    }

    /**
     * Removes Successor of given node and returns it
     *
     * @param curr the current node to find its successor
     * @param dummy2 a dummy node to hold the removed data
     * @return the successor of the given parameter curr
     */
    private AVLNode<T> successorFinder(AVLNode<T> dummy2, AVLNode<T> curr) {
        if (curr.getLeft() == null) {
            dummy2.setData(curr.getData());
            return curr.getRight();
        }
        curr.setLeft(successorFinder(dummy2, curr.getRight()));
        heightAndBF(curr);
        return balanceMethod(curr);
    }

    /**
     * Returns the element from the tree matching the given parameter.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * Do not return the same data that was passed in. Return the data that
     * was stored in the tree.
     *
     * @param data the data to search for in the tree
     * @return the data in the tree equal to the parameter
     * @throws java.lang.IllegalArgumentException if data is null
     * @throws java.util.NoSuchElementException   if the data is not in the tree
     */
    public T get(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data requested is null. AVL doesn't contain null data.");
        } else {
            return getHelper(this.root, data);
        }
    }

    /**
     * Helper method for get method. Using recursion locates and returns data in AVL.
     *
     * @param curr the current root of the AVL
     * @param data the data to be found
     * @return the data of the node that matched the provided data
     */
    private T getHelper(AVLNode<T> curr, T data) {
        if (curr == null) {
            throw new NoSuchElementException("Data requested was not found in the AVL.");
        } else if (data.compareTo(curr.getData()) < 0) {
            return getHelper(curr.getLeft(), data);
        } else if (data.compareTo(curr.getData()) > 0) {
            return getHelper(curr.getRight(), data);
        } else {
            return curr.getData();
        }
    }

    /**
     * Returns whether or not data matching the given parameter is contained
     * within the tree.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * @param data the data to search for in the tree.
     * @return true if the parameter is contained within the tree, false
     * otherwise
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public boolean contains(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data requested is null. AVL doesn't contain null data.");
        } else {
            return containsHelp(data, this.root);
        }
    }

    /**
     * Helper method for contains method. Using recursion determines if the data passed is contained
     * in the AVL
     *
     * @param data data to be searched for
     * @param curr the root of the current BST
     * @return a boolean value determining weather the data is in the AVL or not
     */
    private boolean containsHelp(T data, AVLNode<T> curr) {
        if (curr == null) {
            return false;
        } else if (data.compareTo(curr.getData()) < 0) {
            return containsHelp(data, curr.getLeft());
        } else if (data.compareTo(curr.getData()) > 0) {
            return containsHelp(data, curr.getRight());
        } else {
            return true;
        }
    }

    /**
     * Returns the height of the root of the tree.
     *
     * @return the height of the root of the tree, -1 if the tree is empty
     */
    public int height() {
        return height(this.root);
    }

    /**
     * Helper method for height method. Used to find height of specific node
     *
     * @param curr specific node to find it's height
     * @return the height of the passed node
     */
    private int height(AVLNode<T> curr) {
        if (curr == null) {
            return -1;
        } else {
            return curr.getHeight();
        }
    }

    /**
     * Clears the tree.
     *
     * Clears all data and resets the size.
     */
    public void clear() {
        this.root = null;
        this.size = 0;
    }

    /**
     * Returns the data in the deepest node. If there is more than one node
     * with the same deepest depth, return the rightmost (i.e. largest) node with 
     * the deepest depth. 
     *
     * Must run in O(log n) for all cases.
     *
     * Example
     * Tree:
     *           2
     *        /    \
     *       0      3
     *        \
     *         1
     * Max Deepest Node:
     * 1 because it is the deepest node
     *
     * Example
     * Tree:
     *           2
     *        /    \
     *       0      4
     *        \    /
     *         1  3
     * Max Deepest Node:
     * 3 because it is the maximum deepest node (1 has the same depth but 3 > 1)
     *
     * @return the data in the maximum deepest node or null if the tree is empty
     */
    public T maxDeepestNode() {
        if (this.root == null) {
            return null;
        } else {
            return maxDeepestNodeHelper(this.root);
        }
    }

    /**
     * maxDeepestNode method helper
     *
     * @param curr the current node being traversed
     * @return the data in the deepest node
     */
    private T maxDeepestNodeHelper(AVLNode<T> curr) {
        if (curr.getLeft() == null & curr.getRight() == null) {
            return curr.getData();
        } else if (curr.getBalanceFactor() > 0) {
            return maxDeepestNodeHelper(curr.getLeft());
        } else {
            return maxDeepestNodeHelper(curr.getRight());
        }
    }

    /**
     * In BSTs, you learned about the concept of the successor: the
     * smallest data that is larger than the current data. However, you only
     * saw it in the context of the 2-child remove case.
     *
     * This method should retrieve (but not remove) the successor of the data
     * passed in. There are 2 cases to consider:
     * 1: The right subtree is non-empty. In this case, the successor is the
     * leftmost node of the right subtree.
     * 2: The right subtree is empty. In this case, the successor is the lowest
     * ancestor of the node containing data whose left child is also
     * an ancestor of data.
     * 
     * The second case means the successor node will be one of the node(s) we 
     * traversed left from to find data. Since the successor is the SMALLEST element 
     * greater than data, the successor node is the lowest/last node 
     * we traversed left from on the path to the data node.
     *
     * This should NOT be used in the remove method.
     *
     * Ex:
     * Given the following AVL composed of Integers
     *                    76
     *                  /    \
     *                34      90
     *                  \    /
     *                  40  81
     * successor(76) should return 81
     * successor(81) should return 90
     * successor(40) should return 76
     *
     * @param data the data to find the successor of
     * @return the successor of data. If there is no larger data than the
     * one given, return null.
     * @throws java.lang.IllegalArgumentException if data is null
     * @throws java.util.NoSuchElementException   if the data is not in the tree
     */
    public T successor(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data entered is null. No null data in AVL");
        } else {
            AVLNode<T> carrier = new AVLNode<>(null);
            return successorTwoHelper(this.root, data, carrier);
        }
    }

    /**
     * Helper method for successor method.
     *
     * @param curr the current node being traversed
     * @param data the data passed in to search for
     * @param dummy the dummy node to carry data out when found
     * @return the data found equivalent to data passed in
     */
    private T successorTwoHelper(AVLNode<T> curr, T data, AVLNode<T> dummy) {
        if (curr == null) {
            throw new NoSuchElementException("Data passed is not in AVL.");
        } else if (curr.getData().compareTo(data) < 0) {
            return successorTwoHelper(curr.getRight(), data, dummy);
        } else if (curr.getData().compareTo(data) > 0) {
            dummy.setData(curr.getData());
            return successorTwoHelper(curr.getLeft(), data, dummy);
        } else {
            if (curr.getRight() != null) {
                return findRightSuccessor(curr.getRight());
            } else {
                return dummy.getData();
            }
        }
    }

    /**
     * Helper method for successorTwoHelper
     *
     * @param curr the parent node of right subtree
     * @return the value of successor
     */
    private T findRightSuccessor(AVLNode<T> curr) {
        if (curr.getLeft() == null) {
            return curr.getData();
        } else {
            return findRightSuccessor(curr.getLeft());
        }
    }

    /**
     * Returns the root of the tree.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the root of the tree
     */
    public AVLNode<T> getRoot() {
        // DO NOT MODIFY THIS METHOD!
        return root;
    }

    /**
     * Returns the size of the tree.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the size of the tree
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }


}
