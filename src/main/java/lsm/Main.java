package lsm;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        List<String> list1 = Arrays.asList("Apfel", "Banane", "Kirsche", "Erdbeere");
        List<String> list2 = Arrays.asList("Banane", "Erdbeere", "Kiwi", "Pfirsich");
/*
        Set<String> commonElementsJDK = findCommon(list1, list2);
        System.out.println("Gemeinsame Elemente: " + commonElementsJDK);
 */
        Set<String> commonElementsNOJDK = findCommonNoJdk(list1, list2);
        System.out.println("Gemeinsame Elemente: " + commonElementsNOJDK);
/*
        List<Integer> list3 = Arrays.asList(1, 2, 4, 7, 8);
        List<Integer> list4 = Arrays.asList(2, 3, 7, 9);
        Set<Integer> commonInteger = findCommon(list3, list4);
        System.out.println("Gemeinsame Integer:" + commonInteger);

        List<Boolean> list5 = Arrays.asList(true, false, false, true, true);
        List<Boolean> list6 = Arrays.asList(true, false, false, true, true);
        Set<Boolean> commonBoolean = findCommon(list5, list6);
        System.out.println("Gemeinsame Integer:" + commonBoolean);
 */
    }

    static <T> Set<T> findCommon(final Collection<T> collection1, final Collection<T> collection2) {
        final Set<T> results = new HashSet<>(collection1);
        results.retainAll(collection2);
        return results;
    }

    static <T> Set<T> findCommonNoJdk(final Collection<T> collection1, final Collection<T> collection2) {
        final Map<T, Long> results = new HashMap<>();
        populateFromCollection1(collection1, results);
        markIfAlsoInSecond(collection2, results);
        return removeAllJustInOneCollection(results);
    }

    static <T> void populateFromCollection1(final Collection<T> collection, final Map<T, Long> results) {
        for (T element : collection) {
            results.put(element, 1L);
        }
        System.out.println(results);
    }

    static <T> void markIfAlsoInSecond(final Collection<T> collection, final Map<T, Long> results) {
        for (T element : collection) {
            results.computeIfPresent(element, (_, value) -> value + 1);
        }
        System.out.println(results);
    }

    static <T> Set<T> removeAllJustInOneCollection(final Map<T, Long> results) {
        return results.entrySet().stream()
                .filter(entry -> entry.getValue() >= 2)
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());
    }

}