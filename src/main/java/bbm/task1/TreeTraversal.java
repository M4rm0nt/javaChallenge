package bbm.task1;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class TreeTraversal {

    /*
    public static <T> List<T> toListInorder(BinaryTreeNode<T> root) {

        List<T> result = new ArrayList<>();
        Stack<BinaryTreeNode<T>> stack = new Stack<>();
        BinaryTreeNode<T> node = root;

        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                stack.push(node);
                node = node.getLeft();
            }
            node = stack.pop();
            result.add(node.getValue());
            node = node.getRight();
        }
        return result;
    }
     */

    public static <T> List<T> toListInorder(BinaryTreeNode<T> root) {
        List<T> result = new ArrayList<>();
        inorderRecursive(root, result);
        return result;
    }

    private static <T> void inorderRecursive(BinaryTreeNode<T> node, List<T> result) {
        if (node == null) {
            return;
        }
        inorderRecursive(node.getLeft(), result);
        result.add(node.getValue());
        inorderRecursive(node.getRight(), result);
    }












    public static <T> List<T> toListPreorder(BinaryTreeNode<T> root) {
        List<T> result = new ArrayList<>();
        if (root == null) return result;

        Stack<BinaryTreeNode<T>> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            BinaryTreeNode<T> current = stack.pop();
            result.add(current.getValue());

            if (current.getRight() != null) {
                stack.push(current.getRight());
            }
            if (current.getLeft() != null) {
                stack.push(current.getLeft());
            }
        }
        return result;
    }

    public static <T> List<T> toListPostorder(BinaryTreeNode<T> root) {
        List<T> result = new ArrayList<>();
        if (root == null) return result;

        Stack<BinaryTreeNode<T>> stack = new Stack<>();
        BinaryTreeNode<T> current = root;
        BinaryTreeNode<T> lastVisited = null;

        while (!stack.isEmpty() || current != null) {
            if (current != null) {
                stack.push(current);
                current = current.getLeft();
            } else {
                BinaryTreeNode<T> peekNode = stack.peek();
                if (peekNode.getRight() != null && lastVisited != peekNode.getRight()) {
                    current = peekNode.getRight();
                } else {
                    result.add(peekNode.getValue());
                    lastVisited = stack.pop();
                }
            }
        }
        return result;
    }
}