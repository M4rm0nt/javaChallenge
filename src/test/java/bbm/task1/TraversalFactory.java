package bbm.task1;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class TraversalFactory {

    private static final BinaryTreeNode<String> tree = BinaryTreeSupplyService.createExampleTree();

    private static final Map<String, Supplier<List<String>>> traversalMap = new HashMap<>();

    static {
        traversalMap.put("inorder", () -> TreeTraversal.toListInorder(tree));
        traversalMap.put("preorder", () -> TreeTraversal.toListPreorder(tree));
        traversalMap.put("postorder", () -> TreeTraversal.toListPostorder(tree));
    }

    public static Supplier<List<String>> getTraversal(String type) {
        Supplier<List<String>> supplier = traversalMap.get(type);
        if (supplier == null) {
            throw new IllegalArgumentException("Unbekannte Traversierungsart: " + type);
        }
        return supplier;
    }
}
