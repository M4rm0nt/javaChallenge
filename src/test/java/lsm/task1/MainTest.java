package lsm.task1;

import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import util.TestReporter;

import java.util.*;
import java.util.stream.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MainTest {

    @ParameterizedTest(name = "[{index}] {0}")
    @MethodSource("provideTestCases")
    <T> void testFindCommonNOJDK(String testName, Collection<T> collection1, Collection<T> collection2, Set<T> expected) {
        Set<T> result = Main.findCommonNoJdk(collection1, collection2);
        TestReporter.logTestCase(
                "NO-JDK: " + testName,
                collection1,
                collection2,
                expected,
                result
        );
        assertEquals(expected, result, "Fehler in NO-JDK Implementierung");
    }

    @ParameterizedTest(name = "[{index}] {0}")
    @MethodSource("provideTestCases")
    <T> void testFindCommonJDK(String testName, Collection<T> collection1, Collection<T> collection2, Set<T> expected) {
        Set<T> result = Main.findCommon(collection1, collection2);
        TestReporter.logTestCase(
                "JDK: " + testName,
                collection1,
                collection2,
                expected,
                result
        );
        assertEquals(expected, result, "Fehler in JDK Implementierung");
    }

    @ParameterizedTest(name = "[{index}] {0}")
    @MethodSource("provideNullTestCases")
    <T> void testFindCommonNOJDKNull(String testName, Collection<T> collection1, Collection<T> collection2) {
        assertThrows(NullPointerException.class, () -> Main.findCommonNoJdk(collection1, collection2),
                "Erwartete NullPointerException wurde nicht geworfen");
    }

    Stream<Arguments> provideTestCases() {
        return Stream.of(
                // 🔵 Grundlegende Testfälle
                Arguments.of(
                        "Strings mit Schnittmenge",
                        Arrays.asList("Apfel", "Banane", "Kirsche", "Erdbeere"),
                        Arrays.asList("Banane", "Erdbeere", "Kiwi", "Pfirsich"),
                        new HashSet<>(Arrays.asList("Banane", "Erdbeere"))
                ),
                Arguments.of(
                        "Integers mit Schnittmenge",
                        Arrays.asList(1, 2, 4, 7, 8),
                        Arrays.asList(2, 3, 7, 9),
                        new HashSet<>(Arrays.asList(2, 7))
                ),

                // 🟡 Randfälle
                Arguments.of(
                        "Null-Werte in Collections",
                        Arrays.asList("A", null, "C"),
                        Arrays.asList("B", null, "C"),
                        new HashSet<>(Arrays.asList(null, "C"))
                ),
                Arguments.of(
                        "Beide Collections leer",
                        Collections.emptyList(),
                        Collections.emptyList(),
                        Collections.emptySet()
                ),
                Arguments.of(
                        "Duplikate in Collections",
                        Arrays.asList("Apfel", "Banane", "Banane", "Kirsche"),
                        Arrays.asList("Banane", "Banane", "Kiwi"),
                        new HashSet<>(Collections.singletonList("Banane"))
                ),

                // 🟣 Technische Besonderheiten
                Arguments.of(
                        "Unterschiedliche Collection-Typen",
                        new ArrayList<>(Arrays.asList(1, 2, 3)),
                        new HashSet<>(Arrays.asList(2, 3, 4)),
                        new HashSet<>(Arrays.asList(2, 3))
                ),
                Arguments.of(
                        "Unmodifizierbare Collections",
                        List.of(10, 20, 30),
                        List.of(20, 40),
                        new HashSet<>(Collections.singletonList(20))
                ),

                // 🔴 Komplexe Objekte
                Arguments.of(
                        "Custom Objects (Person)",
                        Arrays.asList(new Person("Alice"), new Person("Bob")),
                        Arrays.asList(new Person("Bob"), new Person("Charlie")),
                        new HashSet<>(Collections.singletonList(new Person("Bob")))
                ),

                // 🟠 Performance-Test
                Arguments.of(
                        "Große Collections",
                        generateLargeCollection("A"),
                        generateLargeCollection("B"),
                        Collections.emptySet() // Keine gemeinsamen Elemente
                )
        );
    }

    // Testfälle für Null-Szenarien
    Stream<Arguments> provideNullTestCases() {
        return Stream.of(
                Arguments.of("Collection1 ist null", null, Arrays.asList("A", "B", "C")),
                Arguments.of("Collection2 ist null", Arrays.asList("A", "B", "C"), null),
                Arguments.of("Beide Collections sind null", null, null)
        );
    }

    private static List<String> generateLargeCollection(String prefix) {
        return IntStream.range(0, 100)
                .mapToObj(i -> prefix + i)
                .collect(Collectors.toList());
    }


    static class Person {
        private final String name;

        public Person(String name) {
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Person person = (Person) o;
            return Objects.equals(name, person.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name);
        }

        @Override
        public String toString() {
            return "Person{" + name + "}";
        }
    }

}