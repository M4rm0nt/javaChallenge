package lsm.task2;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        List<Integer> c1 = Arrays.asList(1,2,3,4,5);
        List<Integer> c2 = Arrays.asList(2,4,6,7);

        Set<Integer> resultD = difference(c1,c2);
        //  Set<Integer> resultD = difference(c2,c1);
        System.out.println(resultD);

        // JDK implementierung
        Set<Integer> resultU = union(c1,c2);
        System.out.println(resultU);

        Set<Integer> resultI = intersection(c1,c2);
        System.out.println(resultI);

        // Selbst implementiert
        Set<Integer> resultDNOJDK = differenceNOJDK(c1,c2);
        //  Set<Integer> resultDNOJDK = differenceNOJDK(c2,c1);
        System.out.println(resultDNOJDK);

        Set<Integer> resultUNOJDK = unionNOJDK(c1,c2);
        System.out.println(resultUNOJDK);

        Set<Integer> resultINOJDK = intersectionNOJDK(c1,c2);
        System.out.println(resultINOJDK);
    }

    // JDK implementierung von Differenzmengen
    static <T> Set<T> difference(final Collection<T> c1, final Collection<T> c2) {
        final Set<T> results = new HashSet<>(c1);
        results.removeAll(c2);
        return  results;
    }

    // JDK implementierung von Vereinigungsmenge
    static <T> Set<T> union(final Collection<T> c1, final Collection<T> c2) {
        final Set<T> result = new HashSet<>(c1);
        result.addAll(c2);
        return  result;
    }

    // JDK implementierung von Schnittmenge
    static <T> Set<T> intersection(final Collection<T> c1, final Collection<T> c2) {
        final Set<T> results = new HashSet<>(c1);
        results.retainAll(c2);
        return  results;
    }

    //-------------------------------------------------
    //-------   Differenzmenge von zwei Mengen
    //-------------------------------------------------
    static <T> Set<T> differenceNOJDK(final Collection<T> c1, final Collection<T> c2) {
        final Map<T, Long> result = new HashMap<>();
        populateFromCollection1(c1, result);
        markIfAlsoSecond(c2, result);
        return removeDuplicatesInCollection(result);
    }

    //-------------------------------------------------
    //-------   Vereinigungsmenge von zwei Mengen
    //-------------------------------------------------
    static <T> Set<T> unionNOJDK(final Collection<T> c1, final Collection<T> c2) {
        final Set<T> result = new HashSet<>();

        for(T element : c1) {
            result.add(element);
        }

        for(T element : c2) {
            result.add(element);
        }

        return result;
    }

    //-------------------------------------------------
    //-------   Schnittmenge von zwei Mengen
    //-------------------------------------------------
    static <T> Set<T> intersectionNOJDK(final Collection<T> c1, final Collection<T> c2) {
        final Map<T, Long> results = new HashMap<>();
        populateFromCollection1(c1, results);
        markIfAlsoSecond(c2, results);
        return removeUniquesInCollection(results);
    }

    static <T> void populateFromCollection1(final Collection<T> collection, final Map<T, Long> results) {
        for (T element : collection) {
            results.put(element, 1L);
        }
    }

    static <T> void markIfAlsoSecond(final Collection<T> collection, final Map<T, Long> results) {
        for(T element : collection) {
            results.computeIfPresent(element, (_, oldValue) -> oldValue + 1);
        }
    }

    static <T> Set<T> filterByCount(final Map<T, Long> results, Predicate<Map.Entry<T, Long>> condition) {
        return results.entrySet().stream()
                .filter(condition)
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());
    }

    static <T> Set<T> removeUniquesInCollection(final Map<T, Long> results) {
        return filterByCount(results, entry -> entry.getValue() >= 2);
    }

    static <T> Set<T> removeDuplicatesInCollection(final Map<T, Long> results) {
        return filterByCount(results, entry -> entry.getValue() == 1);
    }
}
