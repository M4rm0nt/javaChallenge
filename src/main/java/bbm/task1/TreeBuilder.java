package bbm.task1;

import java.util.Stack;

public class TreeBuilder<T> {
    private BinaryTreeNode<T> root;
    private BinaryTreeNode<T> currentNode;
    private final Stack<BinaryTreeNode<T>> parentStack = new Stack<>();

    public TreeBuilder<T> root(T value) {
        root = new BinaryTreeNode<>(value);
        currentNode = root;
        return this;
    }

    public TreeBuilder<T> left(T value) {
        BinaryTreeNode<T> node = new BinaryTreeNode<>(value);
        currentNode.setLeft(node);
        parentStack.push(currentNode);
        currentNode = node;
        return this;
    }

    public TreeBuilder<T> right(T value) {
        BinaryTreeNode<T> node = new BinaryTreeNode<>(value);
        currentNode.setRight(node);
        parentStack.push(currentNode);
        currentNode = node;
        return this;
    }

    public TreeBuilder<T> up() {
        if (!parentStack.isEmpty()) {
            currentNode = parentStack.pop();
        }
        return this;
    }

    public BinaryTreeNode<T> build() {
        return root;
    }

    /**
     * Erstellt einen Beispiel-Binärbaum mit String-Werten.
     *
     * @return Ein Beispielbaum mit der folgenden Struktur:
     *
     *         d4
     *       /    \
     *     b2      f6
     *    /  \    /  \
     *  a1   c3 e5   g7
     */
    public static BinaryTreeNode<String> buildExampleTree() {
        return new TreeBuilder<String>()
                .root("d4")
                .left("b2")
                .left("a1").up()
                .right("c3").up()
                .up() // Zurück zur Root
                .right("f6")
                .left("e5").up()
                .right("g7")
                .build();
    }
}

