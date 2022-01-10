import java.util.ArrayList;
import java.util.List;
import java.util.Collection;
import java.util.Queue;
import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * Your implementation of a BST.
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
public class BST<T extends Comparable<? super T>> {

    // Do not add new instance variables or modify existing ones.
    private BSTNode<T> root;
    private int size;

    /**
     * Constructs a new BST.
     *
     * This constructor should initialize an empty BST.
     *
     * Since instance variables are initialized to their default values, there
     * is no need to do anything for this constructor.
     */
    public BST() {
        // DO NOT IMPLEMENT THIS CONSTRUCTOR!
    }

    /**
     * Constructs a new BST.
     *
     * This constructor should initialize the BST with the data in the
     * Collection. The data should be added in the same order it is in the
     * Collection.
     *
     * Hint: Not all Collections are indexable like Lists, so a regular for loop
     * will not work here. However, all Collections are Iterable, so what type
     * of loop would work?
     *
     * @param data the data to add to the tree
     * @throws java.lang.IllegalArgumentException if data or any element in data
     *                                            is null
     */
    public BST(Collection<T> data) {
        if (data == null) {
            throw new IllegalArgumentException("Data entered is null. Can't construct BST with null data.");
        } else {
            for (T datas: data) {
                add(datas);
            }
        }
    }

    /**
     * Adds the element to the tree.
     *
     * The data becomes a leaf in the tree.
     *
     * Traverse the tree to find the appropriate location. If the data is
     * already in the tree, then nothing should be done (the duplicate
     * shouldn't get added, and size should not be incremented).
     *
     * Must be O(log n) for a balanced tree and O(n) for worst case.
     * 
     * This method must be implemented recursively.
     *
     * @param data the data to add
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data entered is null. Can't add null data to BST.");
        } else {
            this.root = addHelper(root, data);
        }
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
     * 3: The node containing the data has 2 children. Use the predecessor to
     * replace the data. You MUST use recursion to find and remove the
     * predecessor (you will likely need an additional helper method to
     * handle this case efficiently).
     *
     * Do not return the same data that was passed in. Return the data that
     * was stored in the tree.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * Must be O(log n) for a balanced tree and O(n) for worst case.
     * 
     * This method must be implemented recursively.
     *
     * @param data the data to remove
     * @return the data that was removed
     * @throws java.lang.IllegalArgumentException if data is null
     * @throws java.util.NoSuchElementException   if the data is not in the tree
     */
    public T remove(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data entered is null. BST doesn't contain null data.");
        } else {
            BSTNode<T> dummy = new BSTNode(-1);
            this.root = removeHelper(this.root, data, dummy);
            return dummy.getData();
        }
    }

    /**
     * Returns the element from the tree matching the given parameter.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * Must be O(log n) for a balanced tree and O(n) for worst case.
     * 
     * This method must be implemented recursively.
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
            throw new IllegalArgumentException("Data requested is null. BST doesn't contain null data.");
        } else {
            return getHelper(this.root, data);
        }
    }

    /**
     * Returns whether or not data matching the given parameter is contained
     * within the tree.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * Must be O(log n) for a balanced tree and O(n) for worst case.
     * 
     * This method must be implemented recursively.
     *
     * @param data the data to search for in the tree.
     * @return true if the parameter is contained within the tree, false
     * otherwise
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public boolean contains(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data requested is null. BST doesn't contain null data.");
        } else {
            return containsHelp(data, this.root);
        }
    }

    /**
     * Generate a pre-order traversal of the tree.
     *
     * Must be O(n).
     *
     * This method must be implemented recursively.
     *
     * @return the preorder traversal of the tree
     */
    public List<T> preorder() {
        ArrayList<T> preorderList = new ArrayList<>();
        preorderHelper(this.root, preorderList);
        return preorderList;
    }

    /**
     * Generate a in-order traversal of the tree.
     *
     * Must be O(n).
     * 
     * This method must be implemented recursively.
     *
     * @return the inorder traversal of the tree
     */
    public List<T> inorder() {
        ArrayList<T> inorderList = new ArrayList<>();
        inorderHelper(this.root, inorderList);
        return inorderList;
    }

    /**
     * Generate a post-order traversal of the tree.
     *
     * Must be O(n).
     * 
     * This method must be implemented recursively.
     *
     * @return the postorder traversal of the tree
     */
    public List<T> postorder() {
        ArrayList<T> postorderList = new ArrayList<>();
        postorderHelper(this.root, postorderList);
        return postorderList;
    }

    /**
     * Generate a level-order traversal of the tree.
     *
     * This does not need to be done recursively.
     *
     * Hint: You will need to use a queue of nodes. Think about what initial
     * node you should add to the queue and what loop / loop conditions you
     * should use.
     *
     * Must be O(n).
     *
     * @return the level order traversal of the tree
     */
    public List<T> levelorder() {
        ArrayList<T> levelorderList = new ArrayList<>();
        levelorderHelper(this.root, levelorderList);
        return levelorderList;
    }

    /**
     * Returns the height of the root of the tree.
     *
     * A node's height is defined as max(left.height, right.height) + 1. A
     * leaf node has a height of 0 and a null child should be -1.
     *
     * Must be O(n).
     * 
     * This method must be implemented recursively.
     *
     * @return the height of the root of the tree, -1 if the tree is empty
     */
    public int height() {
        BSTNode<T> curr = this.root;
        return heightHelper(curr);
    }

    /**
     * Clears the tree.
     *
     * Clears all data and resets the size.
     *
     * Must be O(1).
     */
    public void clear() {
        this.root = null;
        this.size = 0;
    }

    /**
     * This method checks whether a binary tree meets the criteria for being
     * a binary search tree.
     *
     * This method is a static method that takes in a BSTNode called
     * {@code treeRoot}, which is the root of the tree that you should check.
     *
     * You may assume that the tree passed in is a proper binary tree; that is,
     * there are no loops in the tree, the parent-child relationship is
     * correct, that there are no duplicates, and that every parent has at
     * most 2 children. So, what you will have to check is that the order
     * property of a BST is still satisfied.
     *
     * Should run in O(n). However, you should stop the check as soon as you
     * find evidence that the tree is not a BST rather than checking the rest
     * of the tree.
     * 
     * This method must be implemented recursively.
     *
     * @param <T> the generic typing
     * @param treeRoot the root of the binary tree to check
     * @return true if the binary tree is a BST, false otherwise
     */
    public static <T extends Comparable<? super T>> boolean isBST(
            BSTNode<T> treeRoot) {
        return isBSTHelper(treeRoot, false);
    }

    /**
     * Returns the root of the tree.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the root of the tree
     */
    public BSTNode<T> getRoot() {
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

    /**
     * Helper method for preorder method.
     *
     * @param root the root of the current BST.
     * @param preorderList List containing all nodes of BST
     */
    private void preorderHelper(BSTNode<T> root, List<T> preorderList) {
        BSTNode<T> curr = root;
        if (curr != null) {
            preorderList.add(curr.getData());
            preorderHelper(curr.getLeft(), preorderList);
            preorderHelper(curr.getRight(), preorderList);
        }
    }

    /**
     * Helper method for levelorder method.
     *
     * @param root the root of the current BST.
     * @param levelorderList List containing all nodes of BST
     */
    private void levelorderHelper(BSTNode<T> root, List<T> levelorderList) {
        Queue<BSTNode<T>> currQueue = new LinkedList<>();
        currQueue.add(root);
        while (!currQueue.isEmpty()) {
            BSTNode<T> curr = currQueue.remove();
            if (curr != null) {
                currQueue.add(curr.getLeft());
                currQueue.add(curr.getRight());
                levelorderList.add(curr.getData());
            }
        }
    }

    /**
     * Helper method for inorder method.
     *
     * @param root the root of the current BST.
     * @param inorderList List containing all nodes of BST
     */
    private void inorderHelper(BSTNode<T> root, List<T> inorderList) {
        BSTNode<T> curr = root;
        if (curr != null) {
            inorderHelper(curr.getLeft(), inorderList);
            inorderList.add(curr.getData());
            inorderHelper(curr.getRight(), inorderList);
        }
    }

    /**
     * Helper method for postorder method.
     *
     * @param root the root of the current BST.
     * @param postorderList List containing all nodes of BST
     */
    private void postorderHelper(BSTNode<T> root, List<T> postorderList) {
        BSTNode<T> curr = root;
        if (curr != null) {
            postorderHelper(curr.getLeft(), postorderList);
            postorderHelper(curr.getRight(), postorderList);
            postorderList.add(curr.getData());
        }
    }


    /**
     * Helper Method for isBST method. Using recursion determines if a tree is a BST
     *
     * @param treeRoot the current root of the tree to be checked
     * @param <T> the generic typing
     * @param x boolean expression checking if error found
     * @return a boolean value of weather or not the parameter is a BST or not
     */
    private static <T extends Comparable<? super T>> boolean isBSTHelper(BSTNode<T> treeRoot, boolean x) {
        if (!x) {
            BSTNode<T> curr = treeRoot;
            if (curr.getLeft() != null) {
                if (curr.getData().compareTo(curr.getLeft().getData()) < 0) {
                    x = true;
                    return isBSTHelper(curr.getLeft(), x);
                } else {
                    return isBSTHelper(curr.getLeft(), x);
                }
            } else if (curr.getRight() != null) {
                if (curr.getData().compareTo(curr.getRight().getData()) > 0) {
                    x = true;
                    return isBSTHelper(curr.getRight(), x);
                } else {
                    return isBSTHelper(curr.getRight(), x);
                }
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    /**
     * Helper method for Height method. Using recursion finds the height of the root of the current BST.
     *
     * @param curr the current Node being recurse
     * @return int representing the height of the current BST
     */
    private int heightHelper(BSTNode<T> curr) {
        int leftHeight = 0;
        int rightHeight = 0;
        if (this.root == null) {
            return -1;
        } else {
            if (curr.getLeft() != null) {
                leftHeight++;
                return heightHelper(curr.getLeft());
            } else if (curr.getRight() != null) {
                rightHeight++;
                return heightHelper(curr.getRight());
            }
            rightHeight = rightHeight + 2;
            leftHeight = leftHeight + 2;
            if (leftHeight > rightHeight) {
                return leftHeight;
            } else {
                return rightHeight;
            }
        }
    }

    /**
     * Add method helper. Adds a Node to the BST
     *
     * @param curr the current root of the BST
     * @param data the data to be inserted to the BST
     * @return the new root of the BST
     */
    private BSTNode<T> addHelper(BSTNode<T> curr, T data) {
        if (curr == null) {
            size++;
            return new BSTNode(data);
        } else if (data.compareTo(curr.getData()) < 0) {
            curr.setLeft(addHelper(curr.getLeft(), data));
        } else if (data.compareTo(curr.getData()) > 0) {
            curr.setRight(addHelper(curr.getRight(), data));
        } else if (data.compareTo(curr.getData()) == 0) {
            return curr;
        }
        return curr;
    }

    /**
     * Helper method for contains method. Using recursion determines if the data passed is contained
     * in the BST
     *
     * @param data data to be searched for
     * @param curr the root of the current BST
     * @return a boolean value determining weather the data is in the BST or not
     */
    private boolean containsHelp(T data, BSTNode<T> curr) {
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
     * Helper method for get method. Using recursion locates and returns data in BST.
     *
     * @param curr the current root of the BST
     * @param data the data to be found
     * @return the Node containing the data provided
     */
    private T getHelper(BSTNode<T> curr, T data) {
        if (curr == null) {
            throw new NoSuchElementException("Data requested was not found in the BST.");
        } else if (data.compareTo(curr.getData()) < 0) {
            return getHelper(curr.getLeft(), data);
        } else if (data.compareTo(curr.getData()) > 0) {
            return getHelper(curr.getRight(), data);
        } else {
            return curr.getData();
        }
    }

    /**
     * Removes node from the BST and returns it
     *
     * @param curr current root of the BST
     * @param data the data to be removed from the BST
     * @param dummy a dummy node to hold the removed data
     * @return the removed data of the BST
     */
    private BSTNode<T> removeHelper(BSTNode<T> curr, T data, BSTNode<T> dummy) {
        if (curr == null) {
            throw new NoSuchElementException("Data requested for removal was not found in the BST.");
        } else if (data.compareTo(curr.getData()) < 0) {
            curr.setLeft(removeHelper(curr.getLeft(), data, dummy));
        } else if (data.compareTo(curr.getData()) > 0) {
            curr.setRight(removeHelper(curr.getRight(), data, dummy));
        } else {
            dummy.setData(curr.getData());
            size--;
            if (curr.getRight() == null & curr.getLeft() == null) {
                return null;
            } else if (curr.getRight() != null & curr.getLeft() != null) {
                BSTNode<T> dummy2 = new BSTNode(-1);
                curr.setLeft(removePredecessor(curr.getLeft(), dummy2));
                curr.setData(dummy2.getData());
            } else if (curr.getLeft() != null) {
                return curr.getLeft();
            } else if (curr.getRight() != null) {
                return curr.getRight();
            }
        }
        return curr;
    }

    /**
     * Removes Predecessor of given node and returns it
     *
     * @param curr the current node to find its predecessor
     * @param dummy2 a dummy node to hold the removed data
     * @return the predecessor of the given parameter curr
     */
    private BSTNode<T> removePredecessor(BSTNode<T> curr, BSTNode<T> dummy2) {
        if (curr.getRight() == null) {
            dummy2.setData(curr.getData());
            return curr.getLeft();
        } else {
            curr.setRight(removePredecessor(curr.getRight(), dummy2));
        }
        return null;
    }
}