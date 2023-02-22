/*
 * CS2050 - Computer Science II - Fall 2022
 * Instructor: Thyago Mota
 * Student(s) Name(s):
 * Description: prg_03 - Queue
 */

public class Queue<E> extends LinkedList<E> {

    public void push(E value) {
        append(value);
    }

    public E pop() throws Exception {
        if (isEmpty())
            throw new Exception("Queue is empty!");
        Node<E> toBeReturned = head;
        head = head.getNext();
        toBeReturned.setNext(null);
        return toBeReturned.getValue();
    }

}