package lsm.task1;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        List<String> list1 = Arrays.asList("Apfel", "Banane", "Kirsche", "Erdbeere");
        List<String> list2 = Arrays.asList("Banane", "Erdbeere", "Kiwi", "Pfirsich");
        Set<String> commonElementsNOJDK = findCommonNoJdk(list1, list2);
        System.out.println("Gemeinsame Elemente: " + commonElementsNOJDK);
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
    }

    static <T> void markIfAlsoInSecond(final Collection<T> collection, final Map<T, Long> results) {
        for (T element : collection) {
            results.computeIfPresent(element, (_, oldValue) -> oldValue + 1);
        }
    }

    static <T> boolean isEntryRelevant(Map.Entry<T, Long> entry) {
        return entry.getValue() >= 2;
    }

    static <T> Set<T> removeAllJustInOneCollection(final Map<T, Long> results) {
        return results.entrySet().stream()
                .filter(Main::isEntryRelevant)
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());
    }

}