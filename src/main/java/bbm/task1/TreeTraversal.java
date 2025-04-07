package bbm.task1;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class TreeTraversal {

    /**
     * Iterative Inorder-Traversierung
     */
    public static <T> List<T> toListInorder(BinaryTreeNode<T> root) {
        List<T> result = new ArrayList<>();
        Stack<BinaryTreeNode<T>> stack = new Stack<>();
        BinaryTreeNode<T> current = root;

        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.getLeft();
            }
            current = stack.pop();
            result.add(current.getValue());
            current = current.getRight();
        }
        return result;
    }

    /**
     * Iterative Preorder-Traversierung
     */
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

    /**
     * Iterative Postorder-Traversierung (mit zwei Stacks)
     */
    /**
     * Iterative Postorder-Traversierung (mit einem Stack)
     */
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