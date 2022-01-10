import java.util.NoSuchElementException;

/**
 * Your implementation of an ArrayQueue.
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
public class ArrayQueue<T> {

    /**
     * The initial capacity of the ArrayQueue.
     *
     * DO NOT MODIFY THIS VARIABLE.
     */
    public static final int INITIAL_CAPACITY = 9;

    // Do not add new instance variables or modify existing ones.
    private T[] backingArray;
    private int front;
    private int size;

    /**
     * Constructs a new ArrayQueue.
     */
    public ArrayQueue() {
        backingArray = (T[]) new Object[INITIAL_CAPACITY];
    }

    /**
     * Adds the data to the back of the queue.
     *
     * If sufficient space is not available in the backing array, resize it to
     * double the current length. When resizing, copy elements to the
     * beginning of the new array and reset front to 0.
     *
     * Must be amortized O(1).
     *
     * @param data the data to add to the back of the queue
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void enqueue(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data inserted is null. Can't add null data to queue.");
        } else {
            if (backingArray.length == size) {
                T[] temporaryArray = this.backingArray;
                this.backingArray = (T[]) new Object[backingArray.length * 2];
                for (int x = 0; x < this.size; x++) {
                    this.backingArray[x] = temporaryArray[front];
                    if (front + 1 == backingArray.length) {
                        front = 0;
                    } else {
                        front++;
                    }
                }
                backingArray[size] = data;
                front = 0;
                size++;
            } else if (backingArray.length > size) {
                if (front + size < backingArray.length) {
                    backingArray[size + front] = data;
                    size++;
                } else if (backingArray.length - 1 < front + size) {
                    backingArray[(size + front) % backingArray.length] = data;
                    size++;
                }
            }
        }
    }

    /**
     * Removes and returns the data from the front of the queue.
     *
     * Do not shrink the backing array.
     *
     * Replace any spots that you dequeue from with null.
     *
     * If the queue becomes empty as a result of this call, do not reset
     * front to 0.
     *
     * Must be O(1).
     *
     * @return the data formerly located at the front of the queue
     * @throws java.util.NoSuchElementException if the queue is empty
     */
    public T dequeue() {
        if (backingArray[front] == null) {
            throw new NoSuchElementException("Queue is currently empty. Can't remove data from empty queue.");
        } else {
            T returnData = backingArray[front];
            backingArray[front] = null;
            if (front + 1 < backingArray.length) {
                front++;
            } else {
                front = 0;
            }
            size--;
            return returnData;
        }
    }

    /**
     * Returns the data from the front of the queue without removing it.
     *
     * Must be O(1).
     *
     * @return the data located at the front of the queue
     * @throws java.util.NoSuchElementException if the queue is empty
     */
    public T peek() {
        if (backingArray[front] == null) {
            throw new NoSuchElementException("Queue is currently empty. Can't remove data from empty queue.");
        } else {
            return backingArray[front];
        }
    }

    /**
     * Returns the backing array of the queue.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the backing array of the queue
     */
    public T[] getBackingArray() {
        // DO NOT MODIFY THIS METHOD!
        return backingArray;
    }

    /**
     * Returns the size of the queue.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the size of the queue
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }
}
