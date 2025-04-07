package bbm.task1;

public class BinaryTreeNode<T> {
    private final T value;
    private BinaryTreeNode<T> left;
    private BinaryTreeNode<T> right;

    public BinaryTreeNode(T value) {
        this.value = value;
    }

    public BinaryTreeNode(T value, BinaryTreeNode<T> left, BinaryTreeNode<T> right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    public T getValue() { return value; }
    public BinaryTreeNode<T> getLeft() { return left; }
    public BinaryTreeNode<T> getRight() { return right; }

    public void setLeft(BinaryTreeNode<T> left) {
        this.left = left;
    }
    public void setRight(BinaryTreeNode<T> right) {
        this.right = right;
    }
}