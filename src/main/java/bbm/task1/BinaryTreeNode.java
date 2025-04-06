package bbm.task1;

public class BinaryTreeNode<T> {
    public T item;
    public BinaryTreeNode<T> left;
    public BinaryTreeNode<T> right;

    public BinaryTreeNode(T item) {
        this.item = item;
    }
}