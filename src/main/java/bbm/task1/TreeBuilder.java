package bbm.task1;

import java.util.Stack;

/**
 * Eine Utility-Klasse zum Bauen von Binärbäumen mit einer Fluent-API.
 */
public class TreeBuilder<T> {
    private BinaryTreeNode<T> root;
    private BinaryTreeNode<T> currentNode;
    private final Stack<BinaryTreeNode<T>> parentStack = new Stack<>();

    /**
     * Erstellt einen neuen Baum mit der gegebenen Wurzel.
     *
     * @param value Der Wert des Wurzelknotens
     * @return this für Methoden-Chaining
     */
    public TreeBuilder<T> root(T value) {
        root = new BinaryTreeNode<>(value);
        currentNode = root;
        return this;
    }

    /**
     * Fügt einen linken Kindknoten zum aktuellen Knoten hinzu.
     *
     * @param value Der Wert des linken Kindknotens
     * @return this für Methoden-Chaining
     */
    public TreeBuilder<T> left(T value) {
        BinaryTreeNode<T> node = new BinaryTreeNode<>(value);
        currentNode.setLeft(node);
        parentStack.push(currentNode);
        currentNode = node;
        return this;
    }

    /**
     * Fügt einen rechten Kindknoten zum aktuellen Knoten hinzu.
     *
     * @param value Der Wert des rechten Kindknotens
     * @return this für Methoden-Chaining
     */
    public TreeBuilder<T> right(T value) {
        BinaryTreeNode<T> node = new BinaryTreeNode<>(value);
        currentNode.setRight(node);
        parentStack.push(currentNode);
        currentNode = node;
        return this;
    }

    /**
     * Bewegt sich im Baum eine Ebene nach oben zum Elternknoten.
     *
     * @return this für Methoden-Chaining
     */
    public TreeBuilder<T> up() {
        if (!parentStack.isEmpty()) {
            currentNode = parentStack.pop();
        }
        return this;
    }

    /**
     * Beendet den Bauprozess und gibt den konstruierten Baum zurück.
     *
     * @return Der Wurzelknoten des konstruierten Baums
     */
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