package bbm.task1;

public class Main {
    public static void main(String[] args) {
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
        System.out.println("Postorder Traversal:");
        /*
     a1 → c3
  b2
e5 → g7
  f6
    d4 (Ende)
        */

    }
}