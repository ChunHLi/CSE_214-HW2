import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Ritwik Banerjee
 */
public class MyLinkedList<T> implements MyList<T> {
    
    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<T> iterator() {
        return new MyLinkedListIterator();
    }
    
    protected static class Node<T> {
        public T       data;
        public Node<T> prev;
        public Node<T> next;
        
        public Node(T t, Node<T> prev, Node<T> next) {
            this.data = t;
            this.prev = prev;
            this.next = next;
        }
    
        /**
         * @return a string representation of the node
         */
        @Override
        public String toString() {
            // TODO
            return "" + data;
        }
    }
    
    /** Variable to maintain the current size of this list */
    private int size = 0;
    
    /** Reference to the first node */
    protected Node<T> head;
    
    public MyLinkedList() { clear(); }
    
    /** Removes all of the elements from this list. The list will be empty after this call returns. */
    @Override
    public void clear() {
        head = null;
        size = 0;
    }
    
    /**
     * Returns the number of elements in this list.
     *
     * @return the number of elements in this list
     */
    @Override
    public int size() {
        return size;
    }

    public void incrementSize() {
        size += 1;
    }

    public void decrementSize() {
        size -= 1;
    }
    
    @Override
    public boolean isEmpty() {
        return size == 0;
    }
    
    /**
     * Appends the specified element to the end of this list.
     *
     * @param t the element to be appended to this list.
     * @return <code>true</code>, as specified by {@link MyCollection#add}
     */
    @Override
    public boolean add(T t) {
        add(size, t);
        return true;
    }
    
    /**
     * Inserts the specified element at the specified position in this list. Shifts the element currently at that
     * position (if any) and any subsequent elements to the right (adds one to their indices).
     *
     * @param index index at which the specified element is to be inserted
     * @param t     element to be inserted
     * @throws IndexOutOfBoundsException if index is out of range, i.e., <code>(i &lt; 0 || i &gt; size())</code>
     */
    @Override
    public void add(int index, T t) {
        // TODO
        if (index < 0 || index > size()){
            throw new IndexOutOfBoundsException();
        }
        else if (isEmpty()){
            Node<T> placeHolderNode = new Node<T>(null, null, null);
            Node<T> newStart = new Node<T>(t, null, placeHolderNode);
            head = newStart;
        }
        else if (index == 0){
            Node<T> newStart = new Node<T>(t, null, head);
            head.prev = newStart;
            head = newStart;
        }
        else if (index == size()) {
            Node<T> temp = getNode(index - 1);
            Node<T> placeHolderNode = new Node<T>(null, null, null);
            getNode(index - 1).next = new Node<T>(t, temp, placeHolderNode);
        }
        else {
            Node<T> temp = getNode(index - 1);
            Node<T> temp2 = getNode(index);
            getNode(index - 1).next = new Node<T>(t, temp, temp2);
        }
        size += 1;
    }
    
    @Override
    public T get(int index) {
        return getNode(index).data;
    }
    
    /**
     * Replaces the element at the specified position in this list with the specified element.
     *
     * @param index index of the element to replace
     * @param t     element to be stored at the specified position
     * @return the element previously at the specified position
     * @throws IndexOutOfBoundsException if index is out of range, i.e., <code>(i &lt; 0 || i &gt; size())</code>
     */
    @Override
    public T set(int index, T t) {
        // TODO
        if (index < 0 || index >= size()){
            throw new IndexOutOfBoundsException();
        }
        T returnData = getNode(index).data;
        getNode(index).data = t;
        return returnData;
    }
    
    /**
     * Removes the element at the specified position in this list.  Shifts any subsequent elements to the left
     * (subtracts one from their indices). Returns the element that was removed from the list.
     *
     * @param index the index of the element to be removed
     * @return the element previously at the specified position
     * @throws IndexOutOfBoundsException if index is out of range, i.e., <code>(i &lt; 0 || i &gt; size())</code>
     */
    @Override
    public T remove(int index) {
        return remove(getNode(index));
    }
    
    /**
     * Removes the specified node from the list, and returns the data in that node.
     *
     * @param node the specified node
     * @return the data of the specified node
     */
    public T remove(Node<T> node) {
        // TODO
        if (node.prev == null) {
            head = node.next;
            node.next.prev = null;
        }
        else {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }
        size -= 1;
        return node.data;
    }
    
    /**
     * Removes the first occurrence of the specified element from this list, if it is present. If this list does not
     * contain the element, it is unchanged.  More formally, removes the element with the lowest index <code>i</code>
     * such that <code>(o==null&nbsp;?&nbsp;get(i)==null&nbsp;:&nbsp;o.equals(get(i)))</code> (if such an element
     * exists).  Returns {@code true} if this list contained the specified element (or equivalently, if this list
     * changed as a result of the call).
     *
     * @param t element to be removed from this list, if present
     * @return <code>true</code> if this list contained the specified element
     */
    @Override
    public boolean remove(T t) {
        // TODO
        Iterator<T> removeIterator = iterator();
        while (removeIterator.hasNext()){
            T current = removeIterator.next();
            if (t.equals(current)) {
                removeIterator.remove();
                return true;
            }
        }
        return false;
    }
    
    /**
     * Returns {@code true} if this list contains the specified element.
     * More formally, returns {@code true} if and only if this list contains
     * at least one element {@code e} such that
     * <code>(t == null ? e == null : t.equals(e))</code>.
     *
     * @param t element whose presence in this list is to be tested
     * @return {@code true} if this list contains the specified element
     */
    @Override
    public boolean contains(T t) {
        // TODO
        Iterator<T> containsIterator = iterator();
        while (containsIterator.hasNext()){
            T current = containsIterator.next();
            if (t.equals(current)) {
                return true;
            }
        }
        return false;
    }
    
    private Node<T> getNode(int index) {
        // TODO
        if ((index < 0 || index >= size()) || isEmpty()) {
            throw new IndexOutOfBoundsException();
        }
        if (index == 0){
            return head;
        }
        Node<T> current = head;
        for (int i = 0; i < index; i++){
            current = current.next;
        }
        return current;
    }
    
    /**
     * @return a string representation of the list, with some nice connector showing the links and
     *         the data. For example, a node of integers consisting of 1, 3, 5, and 7 could be
     *         returned as 1 <==> 3 <==> 5 <==> 7
     */
    @Override
    public String toString() {
        // TODO
        StringBuilder output = new StringBuilder();
        Iterator toStringIterator = iterator();
        if (toStringIterator.hasNext()){
            Object current = toStringIterator.next();
            output.append(current.toString());
        }
        while (toStringIterator.hasNext()){
            output.append(" <==> ");
            Object current = toStringIterator.next();
            output.append(current.toString());
        }
        return output.toString();
    }
    
    private class MyLinkedListIterator implements Iterator<T> {
        
        private Node<T> current      = head;
        private boolean removable    = false;
        
        /**
         * Returns {@code true} if the iteration has more elements.
         * (In other words, returns {@code true} if {@link #next} would
         * return an element rather than throwing an exception.)
         *
         * @return {@code true} if the iteration has more elements
         */
        @Override
        public boolean hasNext() {
            return current.next != null;
        }
        
        /**
         * Returns the next element in the iteration.
         *
         * @return the next element in the iteration
         * @throws NoSuchElementException if the iteration has no more elements
         */
        @Override
        public T next() {
            if (!hasNext())
                throw new NoSuchElementException();
            
            T nextElement = current.data;
            current = current.next;
            removable = true;
            return nextElement;
        }
        
        /**
         * Removes from the underlying collection the last element returned by this iterator. This
         * method can be called only once per call to {@link #next}.  The behavior of an iterator
         * is unspecified if the underlying collection is modified while the iteration is in
         * progress in any way other than by calling this method.
         *
         * @throws IllegalStateException if the {@code next} method has not yet been called, or the
         *                               {@code remove} method has already been called after the
         *                               last call to the {@code next} method
         */
        @Override
        public void remove() {
            if (!removable)
                throw new IllegalStateException();
            MyLinkedList.this.remove(current.prev);
            removable = false;
        }
    }
    
}
