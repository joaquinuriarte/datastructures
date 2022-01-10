import java.util.NoSuchElementException;

/**
 * Your implementation of an ArrayList.
 *
 * @author Joaquin Uriarte
 * @version 1.0
 * @userid juriarte3
 * @GTID 903485636
 *
 * Collaborators: None
 *
 * Resources: None
 */
public class ArrayList<T> {

    /**
     * The initial capacity of the ArrayList.
     *
     * DO NOT MODIFY THIS VARIABLE!
     */
    public static final int INITIAL_CAPACITY = 9;

    // Do not add new instance variables or modify existing ones.
    private T[] backingArray;
    private int size;

    /**
     * Constructs a new ArrayList.
     *
     * Java does not allow for regular generic array creation, so you will have
     * to cast an Object[] to a T[] to get the generic typing.
     */
    public ArrayList() {
        backingArray = (T[]) new Object[INITIAL_CAPACITY];
    }

    /**
     * Adds the element to the specified index.
     *
     * Remember that this add may require elements to be shifted.
     *
     * Must be amortized O(1) for index size and O(n) for all other cases.
     *
     * @param index the index at which to add the new element
     * @param data  the data to add at the specified index
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index > size
     * @throws java.lang.IllegalArgumentException  if data is null
     */
    public void addAtIndex(int index, T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot insert null data into data structure.");
        } else if (index < 0) {
            throw new IndexOutOfBoundsException("Cannot remove index that doesn't exist. Index range starts at 0 and"
                    + " ends at ArrayList's size");
        } else if (index > this.size) {
            throw new IndexOutOfBoundsException("Cannot remove index that doesn't exist. Index inputted is outside of"
                    + " ArrayList's size.");
        } else {
            if (this.backingArray.length - this.size > 0) {
                if (index == this.size) {
                    addToBack(data);
                    return;
                } else {
                    for (int x = this.size - 1; x >= index; x--) {
                        this.backingArray[x + 1] = this.backingArray[x];
                    }
                    backingArray[index] = data;
                }
            } else {
                T[] temporaryArray = this.backingArray;
                this.backingArray = (T[]) new Object[backingArray.length * 2];
                for (int x = 0; x <= this.size - 1; x++) {
                    this.backingArray[x] = temporaryArray[x];
                }
                for (int x = size - 1; x <= index; x--) {
                    backingArray[x + 1] = backingArray[x];
                }
                backingArray[index] = data;
            }
            this.size++;
        }
    }

    /**
     * Adds the element to the front of the list.
     *
     * Remember that this add may require elements to be shifted.
     *
     * Must be O(n).
     *
     * @param data the data to add to the front of the list
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToFront(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot insert null data into data structure.");
        } else {
            if (this.backingArray.length - this.size > 0) {
                for (int x = this.size - 1; x >= 0; x--) {
                    this.backingArray[x + 1] = this.backingArray[x];
                }
                this.backingArray[0] = data;
                this.size++;
            } else {
                T[] temporaryArray = this.backingArray;
                this.backingArray = (T[]) new Object[backingArray.length * 2];
                for (int x = 0; x <= this.size - 1; x++) {
                    this.backingArray[x] = temporaryArray[x];
                }
                for (int x = this.size - 1; x >= 0; x--) {
                    this.backingArray[x + 1] = this.backingArray[x];
                }
                this.backingArray[0] = data;
                this.size++;
            }
        }
    }

    /**
     * Adds the element to the back of the list.
     *
     * Must be amortized O(1).
     *
     * @param data the data to add to the back of the list
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToBack(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot insert null data into data structure.");
        } else {
            if (this.backingArray.length - this.size > 0) {
                this.backingArray[size] = data;
                this.size++;
            } else {
                T[] temporaryArray = this.backingArray;
                this.backingArray = (T[]) new Object[backingArray.length * 2];
                for (int x = 0; x <= this.size - 1; x++) {
                    this.backingArray[x] = temporaryArray[x];
                }
                this.backingArray[this.size] = data;
                this.size++;
            }
        }
    }

    /**
     * Removes and returns the element at the specified index.
     *
     * Remember that this remove may require elements to be shifted.
     *
     * Must be O(1) for index size - 1 and O(n) for all other cases.
     *
     * @param index the index of the element to remove
     * @return the data formerly located at the specified index
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index >= size
     */
    public T removeAtIndex(int index) {
        T data;
        if (index < 0) {
            throw new IndexOutOfBoundsException("Cannot remove index that doesn't exist. Index range starts at 0 and"
                    + " ends at ArrayList's size");
        } else if (index >= this.size) {
            throw new IndexOutOfBoundsException("Cannot remove index that doesn't exist. Index inputted is outside of"
                    + " ArrayList's size range.");
        } else {
            if (index == this.size - 1) {
                data = this.backingArray[index];
                this.backingArray[index] = null;
                this.size--;
            } else {
                data = this.backingArray[index];
                for (int x = index + 1; x <= this.size - 1; x++) {
                    this.backingArray[x - 1] = this.backingArray[x];
                }
                this.backingArray[size - 1] = null;
                this.size--;
            }
        }
        return data;
    }

    /**
     * Removes and returns the first element of the list.
     *
     * Remember that this remove may require elements to be shifted.
     *
     * Must be O(n).
     *
     * @return the data formerly located at the front of the list
     * @throws java.util.NoSuchElementException if the list is empty
     */
    public T removeFromFront() {
        T data;
        if (isEmpty()) {
            throw new NoSuchElementException("Cannot preform operation. This Arraylist is already empty.");
        } else {
            data = this.backingArray[0];
            for (int x = 1; x <= this.size - 1; x++) {
                this.backingArray[x - 1] = this.backingArray[x];
            }
            this.backingArray[size - 1] = null;
            this.size--;
        }
        return data;
    }

    /**
     * Removes and returns the last element of the list.
     *
     * Must be O(1).
     *
     * @return the data formerly located at the back of the list
     * @throws java.util.NoSuchElementException if the list is empty
     */
    public T removeFromBack() {
        T data;
        if (isEmpty()) {
            throw new NoSuchElementException("Cannot preform operation. This Arraylist is already empty.");
        } else {
            data = this.backingArray[this.size - 1];
            this.backingArray[this.size - 1] = null;
            this.size--;
        }
        return data;
    }

    /**
     * Returns the element at the specified index.
     *
     * Must be O(1).
     *
     * @param index the index of the element to get
     * @return the data stored at the index in the list
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index >= size
     */
    public T get(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Cannot get index that doesn't exist. Index range starts at 0 and "
                    + "ends at ArrayList's size");
        } else if (index >= this.size) {
            throw new IndexOutOfBoundsException("Cannot remove index that doesn't exist. Index inputted is outside of"
                    + " ArrayList's size.");
        }
        return this.backingArray[index];
    }

    /**
     * Returns whether or not the list is empty.
     *
     * Must be O(1).
     *
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        return this.size == 0;
    }

    /**
     * Clears the list.
     *
     * Resets the backing array to a new array of the initial capacity and
     * resets the size.
     *
     * Must be O(1).
     */
    public void clear() {
        this.backingArray = (T[]) new Object[INITIAL_CAPACITY];
        this.size = 0;
    }

    /**
     * Returns the backing array of the list.
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
     * Returns the size of the list.
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
