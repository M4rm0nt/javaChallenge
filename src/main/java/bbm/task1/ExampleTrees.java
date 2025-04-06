package bbm.task1;

public class ExampleTrees {
    public static BinaryTreeNode<String> createExampleTree() {
        BinaryTreeNode<String> d4 = new BinaryTreeNode<>("d4");
        d4.left = new BinaryTreeNode<>("b2");
        d4.right = new BinaryTreeNode<>("f6");

        d4.left.left = new BinaryTreeNode<>("a1");
        d4.left.right = new BinaryTreeNode<>("c3");

        d4.right.left = new BinaryTreeNode<>("e5");
        d4.right.right = new BinaryTreeNode<>("g7");

        return d4;
    }
}