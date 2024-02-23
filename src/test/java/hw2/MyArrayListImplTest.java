package hw2;

import org.example.hw2.MyArrayList;
import org.example.hw2.MyArrayListImpl;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MyArrayListImplTest {


    @Order(1)
    @ParameterizedTest
    @MethodSource("provideIntsWithEmptyListAndNulls")
    void test_add(MyArrayList<Integer> list) {
        int before = list.size();
        list.add(3);
        int after = list.size();

        Assertions.assertNotEquals(before, after);
    }

    @Order(2)
    @ParameterizedTest
    @MethodSource("provideIntsWithoutEmptyListAndNulls")
    void test_add_by_index(MyArrayList<Integer> list) {
        int before = list.size();
        list.add(0, 3);
        int after = list.size();

        Assertions.assertEquals(before, after);
    }

    @Test
    @Order(3)
    void test_add_by_index_empty_list() {
        MyArrayList<Integer> list = new MyArrayListImpl<>();
        int before = list.size();

        list.add(2, 1);

        int after = list.size();
        Assertions.assertNotEquals(before, after);
    }

    @Order(4)
    @ParameterizedTest
    @MethodSource("provideIntsWithEmptyListAndNulls")
    void test_addAll(MyArrayList<Integer> list) {
        int before = list.size();
        Collection<Integer> c = Arrays.asList(1, 2, 3, 4, 5);

        list.addAll(c);

        Assertions.assertNotEquals(before, before + c.size());
    }

    @Order(5)
    @ParameterizedTest
    @MethodSource("provideIntsWithoutEmptyListAndNulls")
    void test_clear(MyArrayList<Integer> list) {
        int before = list.size();
        list.clear();
        int after = list.size();

        Assertions.assertNotEquals(before, after);
        Assertions.assertEquals(after, 0);
    }

    @Order(6)
    @ParameterizedTest
    @MethodSource("provideIntsWithoutEmptyListAndNulls")
    void test_remove_by_index(MyArrayList<Integer> list) {
        Integer del = list.get(0);
        int before = list.size();

        list.remove(0);

        Integer newEl = list.get(0);
        int after = list.size();

        Assertions.assertNotEquals(del, newEl);
        Assertions.assertNotEquals(before, after);
    }

    @Order(7)
    @ParameterizedTest
    @MethodSource("provideIntsWithoutEmptyListAndNulls")
    void test_remove_by_object(MyArrayList<Integer> list) {
        Integer del = list.get(0);
        int before = list.size();

        list.remove(del);

        Integer newEl = list.get(0);
        int after = list.size();

        Assertions.assertNotEquals(del, newEl);
        Assertions.assertNotEquals(before, after);
    }

    @Test
    @Order(8)
    void test_sort() {
        MyArrayList<Integer> list = new MyArrayListImpl<>(Arrays.asList(2, 4, 1, 3, 6, 5, 8, 7));
        MyArrayList<Integer> listSorted = new MyArrayListImpl<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8));

        list.sort(Comparator.naturalOrder());

        Assertions.assertEquals(list, listSorted);
    }

    @Test
    @Order(9)
    void test_sort2() {
        MyArrayList<Integer> list = new MyArrayListImpl<>(Arrays.asList(21, 4, 51, 3, 6, 8, 7, 36, 6, 5, 8, 77, 2, 56, 123, 34));
        MyArrayList<Integer> listSorted = new MyArrayListImpl<>(Arrays.asList(2, 3, 4, 5, 6, 6, 7, 8, 8, 21, 34, 36, 51, 56, 77, 123));

        list.sort(Comparator.naturalOrder());

        Assertions.assertEquals(list, listSorted);
    }

    @Test
    @Order(10)
    void test_sort_throws_NullPointEx() {
        MyArrayList<Integer> list = new MyArrayListImpl<>(Arrays.asList(2, 4, 1, 3, 6, null, 5, 8, null, 7));

        Assertions.assertThrows(NullPointerException.class, () -> list.sort(Comparator.naturalOrder()));
    }

    @Test
    @Order(11)
    void test_reverse_order() {
        MyArrayList<Integer> list = new MyArrayListImpl<>(Arrays.asList(56, 41, 1, 13, 6, 43, 8, 89));
        MyArrayList<Integer> listSorted = new MyArrayListImpl<>(Arrays.asList(89, 56, 43, 41, 13, 8, 6, 1));

        list.sort(Comparator.reverseOrder());

        Assertions.assertEquals(list, listSorted);
    }


    private Stream<Arguments> provideIntsWithEmptyListAndNulls() {
        return Stream.of(

                Arguments.of(
                        new MyArrayListImpl<>(Arrays.asList(2, 4, 1, 3, 6, 5, 8, 7))
                ),
                Arguments.of(
                        new MyArrayListImpl<>(Arrays.asList(2, 4, 1, 3, 6, null, 5, 8, null, 7))
                ),
                Arguments.of(
                        new MyArrayListImpl<>(Arrays.asList(null, 4, 1, 3, 6, 5, 8, 7, 3, 6, 5, 8, 7, 2, 56, 12, 34))
                ),
                Arguments.of(
                        new MyArrayListImpl<>(List.of())
                ),
                Arguments.of(
                        new MyArrayListImpl<>(List.of(1))
                )
        );
    }

    private Stream<Arguments> provideIntsWithoutEmptyListAndNulls() {
        return Stream.of(

                Arguments.of(
                        new MyArrayListImpl<>(Arrays.asList(56, 41, 1, 13, 6, 43, 8, 89))
                ),
                Arguments.of(
                        new MyArrayListImpl<>(Arrays.asList(2, 42, 13, 3, 6, 5, 8, 7))
                ),
                Arguments.of(
                        new MyArrayListImpl<>(Arrays.asList(21, 4, 51, 3, 6, 8, 7, 36, 6, 5, 8, 77, 2, 56, 123, 34))
                ),
                Arguments.of(
                        new MyArrayListImpl<>(List.of(999))
                )
        );
    }
}
