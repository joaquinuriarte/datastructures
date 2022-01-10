import java.util.NoSuchElementException;

/**
 * Your implementation of a CircularSinglyLinkedList without a tail pointer.
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
public class CircularSinglyLinkedList<T> {


    // Do not add new instance variables or modify existing ones.
    private CircularSinglyLinkedListNode<T> head;
    private int size;

    // Do not add a constructor.

    /**
     * Adds the data to the specified index.
     *
     * Must be O(1) for indices 0 and size and O(n) for all other cases.
     *
     * @param index the index at which to add the new data
     * @param data  the data to add at the specified index
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index > size
     * @throws java.lang.IllegalArgumentException  if data is null
     */
    public void addAtIndex(int index, T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data inputted is null. Can't add null data to list.");
        } else if (index < 0) {
            throw new IndexOutOfBoundsException("List contains no negative indexes. Indexes range from 0 to " + size);
        } else if (index > size) {
            throw new IndexOutOfBoundsException("Index specified is out of the list's range. Current range of list"
                    + " is " + size);
        } else {
            if (index == 0) {
                addToFront(data);
            } else if (index == size) {
                addToBack(data);
            } else {
                CircularSinglyLinkedListNode<T> current = head;
                for (int i = 0; i < index; i++) {
                    current = current.getNext();
                }
                CircularSinglyLinkedListNode<T> addNode = new CircularSinglyLinkedListNode<T>(current.getData(),
                        null);
                addNode.setNext(current.getNext());
                current.setNext(addNode);
                current.setData(data);
                size++;
            }
        }
    }

    /**
     * Adds the data to the front of the list.
     *
     * Must be O(1).
     *
     * @param data the data to add to the front of the list
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToFront(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data inputted is null. Can't add null data to list.");
        } else {
            if (isEmpty()) {
                CircularSinglyLinkedListNode<T> firstNode = new CircularSinglyLinkedListNode<T>(data, null);
                head = firstNode;
                head.setNext(head);
                size++;
            } else {
                CircularSinglyLinkedListNode<T> temporary = new CircularSinglyLinkedListNode<T>(data, null);
                temporary.setNext(head.getNext());
                head.setNext(temporary);
                temporary.setData(head.getData());
                head.setData(data);
                size++;
            }
        }
    }

    /**
     * Adds the data to the back of the list.
     *
     * Must be O(1).
     *
     * @param data the data to add to the back of the list
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToBack(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data inputted is null. Can't add null data to list.");
        } else {
            if (isEmpty()) {
                CircularSinglyLinkedListNode<T> firstNode = new CircularSinglyLinkedListNode<T>(data, null);
                head = firstNode;
                head.setNext(head);
                size++;
            } else {
                CircularSinglyLinkedListNode<T> temporary = new CircularSinglyLinkedListNode<T>(data, null);
                temporary.setNext(head.getNext());
                head.setNext(temporary);
                temporary.setData(head.getData());
                head.setData(data);
                head = temporary;
                size++;
            }
        }
    }

    /**
     * Removes and returns the data at the specified index.
     *
     * Must be O(1) for index 0 and O(n) for all other cases.
     *
     * @param index the index of the data to remove
     * @return the data formerly located at the specified index
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index >= size
     */
    public T removeAtIndex(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("List contains no negative indexes. Indexes range from 0 to " + size);
        } else if (index >= size) {
            throw new IndexOutOfBoundsException("Index specified is out of the list's range. Current range of list"
                    + " is " + size);
        } else {
            if (index == 0) {
                return removeFromFront();
            } else {
                CircularSinglyLinkedListNode<T> current = head;
                for (int i = 0; i < index; i++) {
                    current = current.getNext();
                }
                T dataReturn = current.getData();
                current.setData(current.getNext().getData());
                current.setNext(current.getNext().getNext());
                size--;
                return dataReturn;
            }
        }
    }

    /**
     * Removes and returns the first data of the list.
     *
     * Must be O(1).
     *
     * @return the data formerly located at the front of the list
     * @throws java.util.NoSuchElementException if the list is empty
     */
    public T removeFromFront() {
        if (isEmpty()) {
            throw new NoSuchElementException("List is empty. No data to be removed");
        } else {
            if (size == 1) {
                T returnData = head.getData();
                clear();
                return returnData;
            } else {
                T returnData = head.getData();
                head.setData(head.getNext().getData());
                head.setNext(head.getNext().getNext());
                size--;
                return returnData;
            }
        }
    }

    /**
     * Removes and returns the last data of the list.
     *
     * Must be O(n).
     *
     * @return the data formerly located at the back of the list
     * @throws java.util.NoSuchElementException if the list is empty
     */
    public T removeFromBack() {
        if (isEmpty()) {
            throw new NoSuchElementException("List is empty. No data to be removed");
        } else {
            if (size == 1) {
                T returnData = head.getData();
                clear();
                return returnData;
            } else if (size == 2) {
                T dataReturn = head.getNext().getData();
                head.setNext(head);
                size--;
                return dataReturn;
            } else {
                CircularSinglyLinkedListNode<T> current = head.getNext();
                while (current.getNext().getNext() != head) {
                    current = current.getNext();
                }
                T dataReturn = current.getNext().getData();
                current.setNext(current.getNext().getNext());
                size--;
                return dataReturn;
            }
        }
    }

    /**
     * Returns the data at the specified index.
     *
     * Should be O(1) for index 0 and O(n) for all other cases.
     *
     * @param index the index of the data to get
     * @return the data stored at the index in the list
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index >= size
     */
    public T get(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("List contains no negative indexes. Indexes range from 0 to " + size);
        } else if (index >= size) {
            throw new IndexOutOfBoundsException("Index specified is out of the list's range. Current range of list"
                    + " is " + size);
        } else {
            if (index == 0) {
                return head.getData();
            } else {
                CircularSinglyLinkedListNode<T> current = head;
                for (int i = 0; i < index; i++) {
                    current = current.getNext();
                }
                return current.getData();
            }
        }
    }

    /**
     * Returns whether or not the list is empty.
     *
     * Must be O(1).
     *
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Clears the list.
     *
     * Clears all data and resets the size.
     *
     * Must be O(1).
     */
    public void clear() {
        head = null;
        size = 0;
    }

    /**
     * Removes and returns the last copy of the given data from the list.
     *
     * Do not return the same data that was passed in. Return the data that
     * was stored in the list.
     *
     * Must be O(n).
     *
     * @param data the data to be removed from the list
     * @return the data that was removed
     * @throws java.lang.IllegalArgumentException if data is null
     * @throws java.util.NoSuchElementException   if data is not found
     */
    public T removeLastOccurrence(T data) {
        if (isEmpty()) {
            throw new NoSuchElementException("The list searched is empty and contains no data.");
        } else if (data == null) {
            throw new IllegalArgumentException("Data inputted is null. Can't add null data to list.");
        } else {
            CircularSinglyLinkedListNode<T> current = head;
            int index = 0;
            int indexPlace = 0;
            boolean checkForExistance = true;
            for (int i = 0; i < size; i++) {
                if (current.getData().equals(data)) {
                    indexPlace = index;
                    checkForExistance = false;
                }
                current = current.getNext();
                index++;
            }
            if (checkForExistance) {
                throw new NoSuchElementException("Data entered was not found inside of list.");
            } else {
                return removeAtIndex(indexPlace);
            }
        }
    }

    /**
     * Returns an array representation of the linked list.
     *
     * Must be O(n) for all cases.
     *
     * @return the array of length size holding all of the data (not the
     * nodes) in the list in the same order
     */
    public T[] toArray() {
        T[] returnArray = (T[]) new Object[size];
        CircularSinglyLinkedListNode<T> carrier = head;
        for (int i = 0; i < returnArray.length; i++) {
            returnArray[i] = carrier.getData();
            carrier = carrier.getNext();
        }
        return returnArray;
    }

    /**
     * Returns the head node of the list.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the node at the head of the list
     */
    public CircularSinglyLinkedListNode<T> getHead() {
        // DO NOT MODIFY!
        return head;
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
        // DO NOT MODIFY!
        return size;
    }
}
