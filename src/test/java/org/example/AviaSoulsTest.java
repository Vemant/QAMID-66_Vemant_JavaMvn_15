package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AviaSoulsTest {
    // Тесты search
    // Совпадений 0 фром и ту
    @Test
    public void searchNoMatchesNeitherFromNorTo() {
        AviaSouls avia = new AviaSouls();
        Ticket t1 = new Ticket("A", "B", 100, 1, 10);
        Ticket t2 = new Ticket("C", "D", 100, 1, 10);
        Ticket t3 = new Ticket("E", "F", 100, 1, 10);
        Ticket t4 = new Ticket("G", "H", 100, 1, 10);
        Ticket t5 = new Ticket("I", "J", 100, 1, 10);
        Ticket t6 = new Ticket("K", "L", 100, 1, 10);

        avia.add(t1);
        avia.add(t2);
        avia.add(t3);
        avia.add(t4);
        avia.add(t5);
        avia.add(t6);

        Ticket[] expected = {};
        Ticket[] actual = avia.search("X", "Y");
        Assertions.assertArrayEquals(expected, actual);
    }

    // Совпадения у всех, но ни у кого оба
    @Test
    public void searchNoMatchesFromAndToBoth() {
        AviaSouls avia = new AviaSouls();
        Ticket t1 = new Ticket("X", "B", 100, 1, 10);
        Ticket t2 = new Ticket("X", "D", 100, 1, 10);
        Ticket t3 = new Ticket("X", "F", 100, 1, 10);
        Ticket t4 = new Ticket("G", "Y", 100, 1, 10);
        Ticket t5 = new Ticket("I", "Y", 100, 1, 10);
        Ticket t6 = new Ticket("K", "Y", 100, 1, 10);

        avia.add(t1);
        avia.add(t2);
        avia.add(t3);
        avia.add(t4);
        avia.add(t5);
        avia.add(t6);

        Ticket[] expected = {};
        Ticket[] actual = avia.search("X", "Y");
        Assertions.assertArrayEquals(expected, actual);
    }

    // Совпадений 1 фром и ту
    @Test
    public void searchOneMatchFromAndTo() {
        AviaSouls avia = new AviaSouls();
        Ticket t1 = new Ticket("A", "B", 100, 1, 10);
        Ticket t2 = new Ticket("C", "D", 100, 1, 10);
        Ticket t3 = new Ticket("X", "Y", 1000, 1, 10);
        Ticket t4 = new Ticket("G", "H", 100, 1, 10);
        Ticket t5 = new Ticket("I", "J", 100, 1, 10);
        Ticket t6 = new Ticket("K", "L", 100, 1, 10);

        avia.add(t1);
        avia.add(t2);
        avia.add(t3);
        avia.add(t4);
        avia.add(t5);
        avia.add(t6);

        Ticket[] expected = {t3};
        Ticket[] actual = avia.search("X", "Y");
        Assertions.assertArrayEquals(expected, actual);
    }

    // Совпадения 3 фром и ту; 1-2-3 по цене
    @Test
    public void searchSomeMatchesFromAndToDirectOrder() {
        AviaSouls avia = new AviaSouls();
        Ticket t1 = new Ticket("A", "B", 100, 1, 10);
        Ticket t2 = new Ticket("X", "Y", 1000, 1, 10);
        Ticket t3 = new Ticket("X", "Y", 2000, 1, 10);
        Ticket t4 = new Ticket("G", "H", 100, 1, 10);
        Ticket t5 = new Ticket("X", "Y", 3000, 1, 10);
        Ticket t6 = new Ticket("K", "L", 100, 1, 10);

        avia.add(t1);
        avia.add(t2);
        avia.add(t3);
        avia.add(t4);
        avia.add(t5);
        avia.add(t6);

        Ticket[] expected = {t2, t3, t5};
        Ticket[] actual = avia.search("X", "Y");
        Assertions.assertArrayEquals(expected, actual);
    }

    // Совпадений 3 фром и ту; 3-2-1 по цене, в середине подряд
    @Test
    public void searchSomeMatchesFromAndToInverseOrder() {
        AviaSouls avia = new AviaSouls();
        Ticket t1 = new Ticket("A", "B", 100, 1, 10);
        Ticket t2 = new Ticket("C", "D", 100, 1, 10);
        Ticket t3 = new Ticket("X", "Y", 3000, 1, 10);
        Ticket t4 = new Ticket("X", "Y", 2000, 1, 10);
        Ticket t5 = new Ticket("X", "Y", 1000, 1, 10);
        Ticket t6 = new Ticket("K", "L", 100, 1, 10);

        avia.add(t1);
        avia.add(t2);
        avia.add(t3);
        avia.add(t4);
        avia.add(t5);
        avia.add(t6);

        Ticket[] expected = {t5, t4, t3};
        Ticket[] actual = avia.search("X", "Y");
        Assertions.assertArrayEquals(expected, actual);
    }

    // Совпадений 3 фром и ту: 2-1-3 по цене 1-й и последний сработали
    @Test
    public void searchSomeMatchesFromAndToRandomOrder() {
        AviaSouls avia = new AviaSouls();
        Ticket t1 = new Ticket("X", "Y", 2000, 1, 10);
        Ticket t2 = new Ticket("C", "D", 100, 1, 10);
        Ticket t3 = new Ticket("E", "F", 100, 1, 10);
        Ticket t4 = new Ticket("X", "Y", 1000, 1, 10);
        Ticket t5 = new Ticket("I", "J", 100, 1, 10);
        Ticket t6 = new Ticket("X", "Y", 3000, 1, 10);

        avia.add(t1);
        avia.add(t2);
        avia.add(t3);
        avia.add(t4);
        avia.add(t5);
        avia.add(t6);

        Ticket[] expected = {t4, t1, t6};
        Ticket[] actual = avia.search("X", "Y");
        Assertions.assertArrayEquals(expected, actual);
    }

    // Совпали все, цены у всех равны
    @Test
    public void searchAllMatchesFromAndToAllPricesAreEqual() {
        AviaSouls avia = new AviaSouls();
        Ticket t1 = new Ticket("X", "Y", 100, 1, 10);
        Ticket t2 = new Ticket("X", "Y", 100, 1, 10);
        Ticket t3 = new Ticket("X", "Y", 100, 1, 10);
        Ticket t4 = new Ticket("X", "Y", 100, 1, 10);
        Ticket t5 = new Ticket("X", "Y", 100, 1, 10);
        Ticket t6 = new Ticket("X", "Y", 100, 1, 10);

        avia.add(t1);
        avia.add(t2);
        avia.add(t3);
        avia.add(t4);
        avia.add(t5);
        avia.add(t6);

        Ticket[] expected = {t1, t2, t3, t4, t5, t6};
        Ticket[] actual = avia.search("X", "Y");
        Assertions.assertArrayEquals(expected, actual);
    }

    // Совпали все, цены у некоторых равны
    @Test
    public void searchAllMatchesFromAndToSomePricesAreEqual() {
        AviaSouls avia = new AviaSouls();
        Ticket t1 = new Ticket("X", "Y", 200, 1, 10);
        Ticket t2 = new Ticket("X", "Y", 100, 1, 10);
        Ticket t3 = new Ticket("X", "Y", 3000, 1, 10);
        Ticket t4 = new Ticket("X", "Y", 3000, 1, 10);
        Ticket t5 = new Ticket("X", "Y", 40000, 1, 10);
        Ticket t6 = new Ticket("X", "Y", 3000, 1, 10);

        avia.add(t1);
        avia.add(t2);
        avia.add(t3);
        avia.add(t4);
        avia.add(t5);
        avia.add(t6);

        Ticket[] expected = {t2, t1, t3, t4, t6, t5};
        Ticket[] actual = avia.search("X", "Y");
        Assertions.assertArrayEquals(expected, actual);
    }

    // Тесты searchAndSortBy
    // Совпадения 3 фром и ту; 1-2-3, в середине
    @Test
    public void searchAndSortBySomeMatchesFromAndToDirectOrder() {
        AviaSouls avia = new AviaSouls();
        TicketTimeComparator comparator = new TicketTimeComparator();
        Ticket t1 = new Ticket("A", "B", 100, 1, 2);
        Ticket t2 = new Ticket("X", "Y", 100, 10, 20);
        Ticket t3 = new Ticket("X", "Y", 100, 10, 21);
        Ticket t4 = new Ticket("G", "H", 100, 1, 2);
        Ticket t5 = new Ticket("X", "J", 100, 1, 2);
        Ticket t6 = new Ticket("X", "Y", 100, 10, 22);

        avia.add(t1);
        avia.add(t2);
        avia.add(t3);
        avia.add(t4);
        avia.add(t5);
        avia.add(t6);

        Ticket[] expected = {t2, t3, t6};
        Ticket[] actual = avia.searchAndSortBy("X", "Y", comparator);
        Assertions.assertArrayEquals(expected, actual);
    }

    // Совпадений 3 фром и ту; 2-1-3, в середине
    @Test
    public void searchAndSortBySomeMatchesFromAndToRandomOrder() {
        AviaSouls avia = new AviaSouls();
        TicketTimeComparator comparator = new TicketTimeComparator();
        Ticket t1 = new Ticket("A", "B", 100, 1, 2);
        Ticket t2 = new Ticket("X", "Y", 100, 10, 16);
        Ticket t3 = new Ticket("X", "Y", 100, 10, 15);
        Ticket t4 = new Ticket("G", "H", 100, 1, 2);
        Ticket t5 = new Ticket("I", "J", 100, 1, 2);
        Ticket t6 = new Ticket("X", "Y", 100, 1, 17);

        avia.add(t1);
        avia.add(t2);
        avia.add(t3);
        avia.add(t4);
        avia.add(t5);
        avia.add(t6);

        Ticket[] expected = {t3, t2, t6};
        Ticket[] actual = avia.searchAndSortBy("X", "Y", comparator);
        Assertions.assertArrayEquals(expected, actual);
    }

    // Совпадений 3 фром и ту: 3-2-1  в середине подряд
    @Test
    public void searchAndSortBySomeMatchesFromAndToInverseOrder() {
        AviaSouls avia = new AviaSouls();
        TicketTimeComparator comparator = new TicketTimeComparator();
        Ticket t1 = new Ticket("A", "B", 100, 1, 2);
        Ticket t2 = new Ticket("X", "Y", 100, 1, 22);
        Ticket t3 = new Ticket("X", "Y", 100, 1, 21);
        Ticket t4 = new Ticket("G", "H", 100, 1, 2);
        Ticket t5 = new Ticket("I", "J", 100, 1, 2);
        Ticket t6 = new Ticket("X", "Y", 100, 1, 20);

        avia.add(t1);
        avia.add(t2);
        avia.add(t3);
        avia.add(t4);
        avia.add(t5);
        avia.add(t6);

        Ticket[] expected = {t6, t3, t2};
        Ticket[] actual = avia.searchAndSortBy("X", "Y", comparator);
        Assertions.assertArrayEquals(expected, actual);
    }

    // Совпали все, время полёта у всех одинаковое
    @Test
    public void searchAndSortByAllMatchesFromAndToAllFlyingTimesAreEqual() {
        AviaSouls avia = new AviaSouls();
        TicketTimeComparator comparator = new TicketTimeComparator();
        Ticket t1 = new Ticket("X", "Y", 100, 1, 2);
        Ticket t2 = new Ticket("X", "Y", 100, 1, 2);
        Ticket t3 = new Ticket("X", "Y", 100, 1, 2);
        Ticket t4 = new Ticket("X", "Y", 100, 1, 2);
        Ticket t5 = new Ticket("X", "Y", 100, 1, 2);
        Ticket t6 = new Ticket("X", "Y", 100, 1, 2);

        avia.add(t1);
        avia.add(t2);
        avia.add(t3);
        avia.add(t4);
        avia.add(t5);
        avia.add(t6);

        Ticket[] expected = {t1, t2, t3, t4, t5, t6};
        Ticket[] actual = avia.searchAndSortBy("X", "Y", comparator);
        Assertions.assertArrayEquals(expected, actual);
    }

    // Совпали все, время полёта у некоторых одинаковое
    @Test
    public void searchAndSortByAllMatchesFromAndToSomeFlyingTimesAreEqual() {
        AviaSouls avia = new AviaSouls();
        TicketTimeComparator comparator = new TicketTimeComparator();
        Ticket t1 = new Ticket("X", "Y", 100, 1, 20);
        Ticket t2 = new Ticket("X", "Y", 100, 1, 10);
        Ticket t3 = new Ticket("X", "Y", 100, 1, 2);
        Ticket t4 = new Ticket("X", "Y", 100, 1, 10);
        Ticket t5 = new Ticket("X", "Y", 100, 1, 5);
        Ticket t6 = new Ticket("X", "Y", 100, 1, 10);

        avia.add(t1);
        avia.add(t2);
        avia.add(t3);
        avia.add(t4);
        avia.add(t5);
        avia.add(t6);

        Ticket[] expected = {t3, t5, t2, t4, t6, t1};
        Ticket[] actual = avia.searchAndSortBy("X", "Y", comparator);
        Assertions.assertArrayEquals(expected, actual);
    }

    // Тесты гетов
    // findAll()
    @Test
    public void shouldFindAll() {
        AviaSouls avia = new AviaSouls();
        Ticket t1 = new Ticket("A", "B", 100, 1, 10);
        Ticket t2 = new Ticket("C", "D", 100, 1, 10);
        Ticket t3 = new Ticket("E", "F", 100, 1, 10);
        Ticket t4 = new Ticket("G", "H", 100, 1, 10);
        Ticket t5 = new Ticket("I", "J", 100, 1, 10);
        Ticket t6 = new Ticket("K", "L", 100, 1, 10);

        avia.add(t1);
        avia.add(t2);
        avia.add(t3);
        avia.add(t4);
        avia.add(t5);
        avia.add(t6);

        Ticket[] expected = {t1, t2, t3, t4, t5, t6};
        Ticket[] actual = avia.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }

    // Гет Фром
    @Test
    public void shouldGetFrom() {
        Ticket t1 = new Ticket("A", "B", 100, 1, 20);

        String expected = "A";
        String actual = t1.getFrom();
        Assertions.assertEquals(expected, actual);
    }

    // Гет Ту
    @Test
    public void shouldGetPriceTo() {
        Ticket t1 = new Ticket("A", "B", 100, 1, 20);

        String expected = "B";
        String actual = t1.getTo();
        Assertions.assertEquals(expected, actual);
    }

    // Тест ГетПрайс
    @Test
    public void shouldGetPrice() {
        Ticket t1 = new Ticket("A", "B", 100, 1, 20);

        int expected = 100;
        int actual = t1.getPrice();
        Assertions.assertEquals(expected, actual);
    }

    // Гет Тайм Фром
    @Test
    public void shouldGetPriceTimeFrom() {
        Ticket t1 = new Ticket("A", "B", 100, 1, 20);

        int expected = 1;
        int actual = t1.getTimeFrom();
        Assertions.assertEquals(expected, actual);
    }

    // Гет Тайм Ту
    @Test
    public void shouldGetPriceTimeTo() {
        Ticket t1 = new Ticket("A", "B", 100, 1, 20);

        int expected = 20;
        int actual = t1.getTimeTo();
        Assertions.assertEquals(expected, actual);
    }

    // Тесты компейр ту
    // Возврат -1 (этот меньше о)
    @Test
    public void thisPriceIsLessThanThat() {
        Ticket t1 = new Ticket("A", "B", 100, 1, 10);
        Ticket t2 = new Ticket("C", "D", 200, 1, 10);

        int expected = -1;
        int actual = t1.compareTo(t2);
        Assertions.assertEquals(expected, actual);
    }

    // Возврат 0 (этот равен о)
    @Test
    public void thisPriceIsEqualThat() {
        Ticket t1 = new Ticket("A", "B", 100, 1, 10);
        Ticket t2 = new Ticket("C", "D", 100, 1, 10);

        int expected = 0;
        int actual = t1.compareTo(t2);
        Assertions.assertEquals(expected, actual);
    }

    // Возврат 1 (этот больше о)
    @Test
    public void thisPriceIsMoreThanThat() {
        Ticket t1 = new Ticket("A", "B", 200, 1, 10);
        Ticket t2 = new Ticket("C", "D", 100, 1, 10);

        int expected = 1;
        int actual = t1.compareTo(t2);
        Assertions.assertEquals(expected, actual);
    }

    // Тесты компейр
    // Возврат -1 (время полёта т1 меньше т2)
    @Test
    public void FlyingTimeOneIsLessThanFlyingTimeTwo() {
        Ticket t1 = new Ticket("A", "B", 100, 1, 10);
        Ticket t2 = new Ticket("C", "D", 100, 1, 20);
        TicketTimeComparator comparator = new TicketTimeComparator();

        int expected = -1;
        int actual = comparator.compare(t1, t2);
        Assertions.assertEquals(expected, actual);
    }

    // Возврат 0 (время полёта т1 равно т2)
    @Test
    public void FlyingTimeOneIsEqualFlyingTimeTwo() {
        Ticket t1 = new Ticket("A", "B", 100, 1, 10);
        Ticket t2 = new Ticket("C", "D", 100, 1, 10);
        TicketTimeComparator comparator = new TicketTimeComparator();

        int expected = 0;
        int actual = comparator.compare(t1, t2);
        Assertions.assertEquals(expected, actual);
    }

    // Возврат 1 (время полёта т1 больше т2)
    @Test
    public void FlyingTimeOneIsMoreThanFlyingTimeTwo() {
        Ticket t1 = new Ticket("A", "B", 100, 1, 20);
        Ticket t2 = new Ticket("C", "D", 100, 1, 10);
        TicketTimeComparator comparator = new TicketTimeComparator();

        int expected = 1;
        int actual = comparator.compare(t1, t2);
        Assertions.assertEquals(expected, actual);
    }

    // Тесты equals
    @Test
    public void objectIsEqualItself() {
        Ticket t1 = new Ticket("A", "B", 100, 1, 10);

        boolean expected = true;
        boolean actual = t1.equals(t1);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void equalityOfIdenticalObjects() {
        Ticket t1 = new Ticket("A", "B", 100, 1, 10);
        Ticket t2 = new Ticket("A", "B", 100, 1, 10);

        boolean expected = true;
        boolean actual = t1.equals(t2);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void objectIsEqualNull() {
        Ticket t1 = new Ticket("A", "B", 100, 1, 10);
        Ticket t2 = null;

        boolean expected = false;
        boolean actual = t1.equals(t2);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void equalsNotEqualGetClasses() {
        Ticket t1 = new Ticket("A", "B", 100, 1, 10);
        int t2 = 100;

        boolean expected = false;
        boolean actual = t1.equals(t2);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void notEqualObjectsFrom() {
        Ticket t1 = new Ticket("A", "B", 100, 1, 10);
        Ticket t2 = new Ticket("C", "B", 100, 1, 10);

        boolean expected = false;
        boolean actual = t1.equals(t2);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void notEqualObjectsTo() {
        Ticket t1 = new Ticket("A", "B", 100, 1, 10);
        Ticket t2 = new Ticket("A", "C", 100, 1, 10);

        boolean expected = false;
        boolean actual = t1.equals(t2);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void notEqualObjectsPrice() {
        Ticket t1 = new Ticket("A", "B", 100, 1, 10);
        Ticket t2 = new Ticket("C", "B", 200, 1, 10);

        boolean expected = false;
        boolean actual = t1.equals(t2);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void notEqualObjectsTimeFrom() {
        Ticket t1 = new Ticket("A", "B", 100, 1, 10);
        Ticket t2 = new Ticket("C", "B", 100, 2, 10);

        boolean expected = false;
        boolean actual = t1.equals(t2);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void notEqualObjectsTimeTo() {
        Ticket t1 = new Ticket("A", "B", 100, 1, 10);
        Ticket t2 = new Ticket("C", "B", 100, 1, 20);

        boolean expected = false;
        boolean actual = t1.equals(t2);
        Assertions.assertEquals(expected, actual);
    }

    // Тесты хэшкод
    @Test
    public void equalityOfHashCodesOfTwoEqualityObjects() {
        Ticket t1 = new Ticket("A", "B", 100, 1, 10);
        Ticket t2 = new Ticket("A", "B", 100, 1, 10);

        Assertions.assertEquals(t1.hashCode(), t2.hashCode());
    }
}
