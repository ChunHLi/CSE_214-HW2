import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by shawn on 2/25/2017.
 */


public class CircularList <T> extends MyLinkedList<T>{
    public CircularList(){
        clear();
    }

    @Override
    public void add(int index, T t) {
        // TODO
        if (index < 0 || index > size()){
            throw new IndexOutOfBoundsException();
        }
        else if (isEmpty()){
            Node<T> newStart = new Node<T>(t, head, head);
            head = newStart;
            head.prev = head;
            head.next = head;
            head.prev.prev = head;
            head.prev.next = head;
            head.next.prev = head;
            head.next.next = head;
        }
        else if (size()==1 && index == 0){
            Node<T> newStart = new Node<T>(t, head, head);
            head.prev = newStart;
            head.next = newStart;
            head = newStart;
        }
        else if (index == 0){
            Node<T> newStart = new Node<T>(t, head.prev, head);
            head.prev.next = newStart;
            head.prev = newStart;
            head = newStart;
        }
        else if (index == size()) {
            Node<T> newEnd = new Node<T>(t, head.prev, head);
            head.prev.next = newEnd;
            head.prev = newEnd;
        }
        else {
            Node<T> newNode = new Node<T>(t, getNode(index - 1), getNode(index));
            getNode(index).prev = newNode;
            getNode(index - 1).next = newNode;
        }
        incrementSize();
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

    @Override
    public T remove(Node<T> node) {
        // TODO
        if (node.prev == head.prev) {
            head.next.prev = head.prev;
            head.prev.next = head.next;
            head = head.next;
        }
        else if (node.next == head) {
            node.prev.next = head;
            head.prev = node.prev;
        }
        else {
            node.next.prev = node.prev;
            node.prev.next = node.next;
        }
        decrementSize();
        return node.data;
    }

    @Override
    public boolean remove(T t) {
        for(int i = 0; i < size(); i++){
            if (get(i) == t){
                remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean contains(T t) {
        for(int i = 0; i < size(); i++){
            if (get(i) == t){
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        int i = 0;
        while (i < size() - 1){
            output.append(get(i).toString());
            output.append(" <==> ");
            i += 1;
        }
        output.append(get(i).toString());
        return output.toString();
    }
    public Node<T> getCircular(int i, Node<T> fromThis){
        int counter = 0;
        while (counter < i){
            fromThis = fromThis.next;
            counter += 1;
        }
        return fromThis;
    }
}
