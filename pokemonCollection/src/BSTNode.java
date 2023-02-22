/*
 * CS2050 - Computer Science II - Fall 2022
 * Instructor: Thyago Mota
 * Student(s) Name(s):
 * Description: prg_03 - BST
 */

public class BSTNode<E> {

    private E  value;
    private BSTNode<E> left, right;

    public BSTNode() {
        value = null;
        left = right = null;
    }

    public BSTNode(E value) {
        this.value = value;
        left = right = null;
    }

    public E getValue() {
        return value;
    }

    public BSTNode<E> getLeft() {
        return left;
    }

    public BSTNode<E> getRight() {
        return right;
    }

    public void setValue(E value) {
        this.value = value;
    }

    public void setLeft(BSTNode<E> left) {
        this.left = left;
    }

    public void setRight(BSTNode<E> right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
