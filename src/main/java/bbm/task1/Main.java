package bbm.task1;

public class Main {
    public static void main(String[] args) {
        BinaryTreeNode<String> root = ExampleTrees.createExampleTree();
    /*
        d4
      /    \
    b2      f6
   /  \    /  \
 a1   c3 e5   g7

     */

        System.out.println("Inorder Traversal:");
        /*
    → a1
  b2
    → c3
d4
    → e5
  f6
    → g7
         */
        TreeTraversal.inorder(root, System.out::println);

        System.out.println("\n");
        System.out.println("Preorder Traversal:");
        /*
d4 (Start)
│
├── b2
│   ├── a1
│   └── c3
│
└── f6
    ├── e5
    └── g7
         */
        TreeTraversal.preorder(root, System.out::println);

        System.out.println("\n");
        System.out.println("Postorder Traversal:");
        /*
     a1 → c3
  b2
e5 → g7
  f6
    d4 (Ende)
        */
        TreeTraversal.postorder(root, System.out::println);

    }
}