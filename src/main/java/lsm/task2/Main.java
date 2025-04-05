package lsm.task2;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {

        List<Integer> c1 = Arrays.asList(1,2,3,4,5);
        List<Integer> c2 = Arrays.asList(2,4,6,7);

        // JDK implementierung
        System.out.println("JDK:");
        Set<Integer> resultD = difference(c1,c2);
        Set<Integer> resultU = union(c1,c2);
        Set<Integer> resultI = intersection(c1,c2);
        System.out.println(resultD);
        System.out.println(resultU);
        System.out.println(resultI);

        // JDK implementierung Stream-API
        System.out.println("\nJDK STREAM-API:");
        Set<Integer> resultDStream = differenceStream(c1,c2);
        Set<Integer> resultUstream = unionStream(c1,c2);
        System.out.println(resultDStream);
        System.out.println(resultUstream);

        // Selbst implementiert
        System.out.println("\nSELF IMPL:");
        Set<Integer> resultDNOJDK = differenceNOJDK(c1,c2);
        Set<Integer> resultUNOJDK = unionNOJDK(c1,c2);
        Set<Integer> resultINOJDK = intersectionNOJDK(c1,c2);
        System.out.println(resultDNOJDK);
        System.out.println(resultUNOJDK);
        System.out.println(resultINOJDK);

    }

    // JDK implementierung von Differenzmengen, Vereinigungsmenge, Schnittmenge
    static <T> Set<T> difference(final Collection<T> c1, final Collection<T> c2) {
        final Set<T> results = new HashSet<>(c1);
        results.removeAll(c2);
        return  results;
    }

    static <T> Set<T> union(final Collection<T> c1, final Collection<T> c2) {
        final Set<T> result = new HashSet<>(c1);
        result.addAll(c2);
        return  result;
    }

    static <T> Set<T> intersection(final Collection<T> c1, final Collection<T> c2) {
        final Set<T> results = new HashSet<>(c1);
        results.retainAll(c2);
        return  results;
    }

    // JDK implementierung mit Stream-API
    static <T> Set<T> differenceStream(final Collection<T> c1, final Collection<T> c2) {
        return c1.stream().filter(e -> !c2.contains(e)).collect(Collectors.toSet());
    }

    static <T> Set<T> unionStream(final Collection<T> c1, final Collection<T> c2) {
        return Stream.concat(c1.stream(), c2.stream()).collect(Collectors.toSet());
    }

    static <T> Set<T> intersectionStream(final Collection<T> c1, final Collection<T> c2) {
        return c1.stream().filter(c2::contains).collect(Collectors.toSet());
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

    static <T> Set<T> removeDuplicatesInCollection(final Map<T, Long> results) {
        return filterByCount(results, entry -> entry.getValue() == 1);
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

    static <T> Set<T> removeUniquesInCollection(final Map<T, Long> results) {
        return filterByCount(results, entry -> entry.getValue() >= 2);
    }

    // Wird von Difference und Intersection benutzt
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

}
