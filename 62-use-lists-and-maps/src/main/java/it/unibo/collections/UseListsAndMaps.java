package it.unibo.collections;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Example class using {@link List} and {@link Map}.
 *
 */
public final class UseListsAndMaps {

    private static final int N_ELEMS = 100_000;

    private UseListsAndMaps() {
    }

    /**
     * @param s
     *            unused
     */
    public static void main(final String... s) {
        /*
         * 1) Create a new ArrayList<Integer>, and populate it with the numbers
         * from 1000 (included) to 2000 (excluded).
         */

        List<Integer> al = new ArrayList<>();
        for (int i = 1000; i < 2000; i++) {
            al.add(i);
        }

        /*
         * 2) Create a new LinkedList<Integer> and, in a single line of code
         * without using any looping construct (for, while), populate it with
         * the same contents of the list of point 1.
         */

        List<Integer> ll = new LinkedList<>(al);

        /*
         * 3) Using "set" and "get" and "size" methods, swap the first and last
         * element of the first list. You can not use any "magic number".
         * (Suggestion: use a temporary variable)
         */

        Integer swap = al.get(0);
        al.set(0, al.get(al.size()-1));
        al.set(al.size()-1, swap);

        /*
         * 4) Using a single for-each, print the contents of the arraylist.
         */

        System.out.println("");
        for (final Integer tmp : al) {
            System.out.print(tmp + " ");
        }
        System.out.println("");

        /*
         * 5) Measure the performance of inserting new elements in the head of
         * the collection: measure the time required to add 100.000 elements as
         * first element of the collection for both ArrayList and LinkedList,
         * using the previous lists. In order to measure times, use as example
         * TestPerformance.java.
         */

        long time = System.nanoTime();
        for (int i = 0; i < N_ELEMS; i++) {
            al.addFirst(i);
        }
        time = System.nanoTime() - time;
        var millis = TimeUnit.NANOSECONDS.toMillis(time);
        System.out.println("\nAdding 100_000 elems in head took " + time + "ns (" + millis + "ms) for ArrayList");

        time = System.nanoTime();
        for (int i = 0; i < N_ELEMS; i++) {
            ll.addFirst(i);
        }
        time = System.nanoTime() - time;
        millis = TimeUnit.NANOSECONDS.toMillis(time);
        System.out.println("Adding 100_000 elems in head took " + time + "ns (" + millis + "ms) for LinkedList");

        /*
         * 6) Measure the performance of reading 1000 times an element whose
         * position is in the middle of the collection for both ArrayList and
         * LinkedList, using the collections of point 5. In order to measure
         * times, use as example TestPerformance.java.
         */

        time = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            al.get(al.size()/2);
        }
        time = System.nanoTime() - time;
        millis = TimeUnit.NANOSECONDS.toMillis(time);
        System.out.println("Reading mid element of ArrayList took " + time + "ns (" + millis + "ms)");

        time = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            ll.get(al.size()/2);
        }
        time = System.nanoTime() - time;
        millis = TimeUnit.NANOSECONDS.toMillis(time);
        var seconds = TimeUnit.NANOSECONDS.toSeconds(time);
        System.out.println("Reading mid element of LinkedList took " + time + "ns (" + millis + "ms, " + seconds + "s)");

        /*
         * 7) Build a new Map that associates to each continent's name its
         * population:
         *
         * Africa -> 1,110,635,000
         *
         * Americas -> 972,005,000
         *
         * Antarctica -> 0
         *
         * Asia -> 4,298,723,000
         *
         * Europe -> 742,452,000
         *
         * Oceania -> 38,304,000
         */

        Map<String, Long> map = new HashMap<>();
        map.put("Africa", 1_110_635_000l);
        map.put("Americas", 972_005_000l);
        map.put("Antarctica", 0l);
        map.put("Asia", 4_298_723_000l);
        map.put("Europe", 742_452_000l);
        map.put("Oceania", 38_304_000l);

        /*
         * 8) Compute the population of the world
         */

        long worldPopulation = 0;
        for (Long longTmp : map.values()) {
            worldPopulation += longTmp;
        }
        System.out.println("\nWorld population calculated: " + String.format(Locale.ITALY, "%,d", worldPopulation) + "\n");
    }
}
