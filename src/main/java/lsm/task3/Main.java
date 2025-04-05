package lsm.task3;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<Integer> l1 = List.of(1,2,3,4);
        System.out.println(l1);
        List<Integer> result1 = reverseList(l1);
        System.out.println(result1);

        System.out.println("\n");

        List<String> l2 = List.of("A","BB","CCC","DDDD");
        System.out.println(l2);
        List<String> result2 = reverseList(l2);
        System.out.println(result2);
    }

    static <T> List<T> reverseList(List<T> collection) {
        List<T> result = new ArrayList<>();
        /*
        T[] elements = (T[]) collection.toArray();

        for (int i = elements.length - 1; i >= 0; i--) {
            result.add(elements[i]);
        }
         */

        for(int i = collection.size() -1; i >= 0; i--) {
            result.add(collection.get(i));
        }

        return result;
    }
}
