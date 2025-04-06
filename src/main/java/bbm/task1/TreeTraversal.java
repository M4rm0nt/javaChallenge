package bbm.task1;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class TreeTraversal {

    public static <T> void inorder(BinaryTreeNode<T> node, Consumer<T> action) {
        if (node == null) return;
        inorder(node.left, action);
        action.accept(node.item);
        inorder(node.right, action);
    }

    public static <T> void preorder(BinaryTreeNode<T> node, Consumer<T> action) {
        if (node == null) return;
        action.accept(node.item);
        preorder(node.left, action);
        preorder(node.right, action);
    }

    public static <T> void postorder(BinaryTreeNode<T> node, Consumer<T> action) {
        if (node == null) return;
        postorder(node.left, action);
        postorder(node.right, action);
        action.accept(node.item);
    }

    public static <T> List<T> toListInorder(BinaryTreeNode<T> root) {
        List<T> result = new ArrayList<>();
        inorder(root, result::add);
        return result;
    }

    public static <T> List<T> toListPreorder(BinaryTreeNode<T> root) {
        List<T> result = new ArrayList<>();
        preorder(root, result::add);
        return result;
    }

    public static <T> List<T> toListPostorder(BinaryTreeNode<T> root) {
        List<T> result = new ArrayList<>();
        postorder(root, result::add);
        return result;
    }
}