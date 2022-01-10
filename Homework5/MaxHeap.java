import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * Your implementation of a MaxHeap.
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
public class MaxHeap<T extends Comparable<? super T>> {

    /*
     * The initial capacity of the MaxHeap when created with the default
     * constructor.
     *
     * DO NOT MODIFY THIS VARIABLE!
     */
    public static final int INITIAL_CAPACITY = 13;

    /*
     * Do not add new instance variables or modify existing ones.
     */
    private T[] backingArray;
    private int size;

    /**
     * Constructs a new MaxHeap.
     *
     * The backing array should have an initial capacity of INITIAL_CAPACITY.
     */
    public MaxHeap() {
        this.backingArray = (T[]) new Comparable[INITIAL_CAPACITY];
        this.size = 0;
    }

    /**
     * Creates a properly ordered heap from a set of initial values.
     *
     * You must use the BuildHeap algorithm that was taught in lecture! Simply
     * adding the data one by one using the add method will not get any credit.
     * As a reminder, this is the algorithm that involves building the heap
     * from the bottom up by repeated use of downHeap operations.
     *
     * Before doing the algorithm, first copy over the data from the
     * ArrayList to the backingArray (leaving index 0 of the backingArray
     * empty). The data in the backingArray should be in the same order as it
     * appears in the passed in ArrayList before you start the BuildHeap
     * algorithm.
     *
     * The backingArray should have capacity 2n + 1 where n is the
     * number of data in the passed in ArrayList (not INITIAL_CAPACITY).
     * Index 0 should remain empty, indices 1 to n should contain the data in
     * proper order, and the rest of the indices should be empty.
     *
     * @param data a list of data to initialize the heap with
     * @throws java.lang.IllegalArgumentException if data or any element in data
     *                                            is null
     */
    public MaxHeap(ArrayList<T> data) {
        if (data == null) {
            throw new IllegalArgumentException("List passed is empty. Can't create MaxHeap from empty list");
        } else {
            this.size = data.size();
            this.backingArray = (T[]) new Comparable[data.size() * 2 + 1];
            for (int i = 1; i <= this.size; i++) {
                if (data.get(i - 1) == null) {
                    throw new IllegalArgumentException("Data inside list is empty. Can't create heap with empty data.");
                }
                this.backingArray[i] = data.get(i - 1);
            }
            downHeap(this.size / 2);
        }
    }

    /**
     * Down heap operation. Serves as a helper method for remove method and BuildHeap
     *
     * @param i the current index of the data in the array
     */
    private void downHeap(int i) {
        if (i != 0 && backingArray[i * 2] != null) {
            if (backingArray[i * 2 + 1] == null) {
                if (backingArray[i].compareTo(backingArray[i * 2]) < 0) {
                    T current = backingArray[i];
                    backingArray[i] = backingArray[i * 2];
                    backingArray[i * 2] = current;
                }
            } else {
                if (backingArray[i].compareTo(backingArray[i * 2]) < 0 && backingArray[i * 2
                        + 1].compareTo(backingArray[i * 2]) < 0) {
                    T current = backingArray[i];
                    backingArray[i] = backingArray[i * 2];
                    backingArray[i * 2] = current;
                    if (i * 4 <= backingArray.length) {
                        downHeap(i * 2);
                    }
                } else if (backingArray[i].compareTo(backingArray[i * 2 + 1]) < 0 && backingArray[i
                        * 2].compareTo(backingArray[(i * 2) + 1]) < 0) {
                    T current = backingArray[i];
                    backingArray[i] = backingArray[i * 2 + 1];
                    backingArray[i * 2 + 1] = current;
                    if (i * 4 + 2 <= backingArray.length) {
                        downHeap(i * 2 + 1);
                    }
                }
            }
            downHeap(i - 1);
        } else {
            return;
        }
    }

    /**
     * Adds the data to the heap.
     *
     * If sufficient space is not available in the backing array (the backing
     * array is full except for index 0), resize it to double the current
     * length.
     *
     * @param data the data to add
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data provided is null. Heap cannot contain null data.");
        } else {
            if (this.size == this.backingArray.length - 1) {
                T[] temporary = this.backingArray;
                this.backingArray = (T[]) new Comparable[this.backingArray.length * 2];
                for (int i = 1; i <= this.size; i++) {
                    this.backingArray[i] = temporary[i];
                }
                backingArray[this.size + 1] = data;
                size++;
                upHeap(this.size);
            } else {
                backingArray[this.size + 1] = data;
                size++;
                upHeap(this.size);
            }
        }
    }

    /**
     * Helper method for add method. Uses recursion to complete task.
     *
     * @param i the current index of data in heap
     */
    private void upHeap(int i) {
        while (i / 2 != 0) {
            if (backingArray[i / 2].compareTo(backingArray[i]) < 0) {
                T current = backingArray[i / 2];
                backingArray[i / 2] = backingArray[i];
                backingArray[i] = current;
                i = i / 2;
            } else {
                break;
            }
        }
    }

    /**
     * Removes and returns the root of the heap.
     *
     * Do not shrink the backing array.
     *
     * Replace any unused spots in the array with null.
     *
     * @return the data that was removed
     * @throws java.util.NoSuchElementException if the heap is empty
     */
    public T remove() {
        if (isEmpty()) {
            throw new NoSuchElementException("Heap is currently empty. Can't remove element from empty heap.");
        } else {
            T removed = this.backingArray[1];
            if (this.size == 1) {
                clear();
            } else {
                this.backingArray[1] = this.backingArray[this.size];
                this.backingArray[this.size] = null;
                this.size--;
                downHeap(1);
            }
            return removed;
        }
    }

    /**
     * Returns the maximum element in the heap.
     *
     * @return the maximum element
     * @throws java.util.NoSuchElementException if the heap is empty
     */
    public T getMax() {
        if (isEmpty()) {
            throw new NoSuchElementException("Heap is currently empty. No max element can me selected.");
        } else {
            return backingArray[1];
        }
    }

    /**
     * Returns whether or not the heap is empty.
     *
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Clears the heap.
     *
     * Resets the backing array to a new array of the initial capacity and
     * resets the size.
     */
    public void clear() {
        this.backingArray = (T[]) new Comparable[INITIAL_CAPACITY];
        this.size = 0;
    }

    /**
     * Returns the backing array of the heap.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the backing array of the list
     */
    public T[] getBackingArray() {
        // DO NOT MODIFY THIS METHOD!
        return backingArray;
    }

    /**
     * Returns the size of the heap.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the size of the list
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }
}
