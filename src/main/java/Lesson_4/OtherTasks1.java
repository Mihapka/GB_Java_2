package Lesson_4;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class OtherTasks1 {

    public static void main(String[] args) {

//        taskTwo();
//        taskThree();
//        taskFour();
//        taskFive();
        taskSix();
    }

    public static Integer[] createList() {

        Integer[] list = new Integer[10];
        for (int i = 0; i < list.length; i++) {
            list[i] = (int) (Math.random() * 50);
        }
        for (int a : list) {
            System.out.print(a + " ");

        }
        System.out.println("\n");
        return list;

    }

//  task 2

    public static void taskTwo() {

        IntSearchable searchable = (k, lst) -> IntStream
                .range(0, lst.length)        /*задаем диапозон поиска*/
                .filter(i -> lst[i] == k)       /*задаем условие поиска*/
                .findFirst()                /*задаем поиск первого вхождения*/
                .orElse(-1);           /*задаем else*/

        System.out.println(searchable.search(3, createList()) + "\n");

    }

    @FunctionalInterface
    public interface IntSearchable {
        int search(Integer n, Integer[] list);
    }

//    task 3

    public static void taskThree() {

        String word = "java interview";
        StringReversable reversable = (a) -> new StringBuffer(a).reverse().toString();
        System.out.println(reversable.revers(word) + "\n");
    }

    @FunctionalInterface
    public interface StringReversable {
        String revers(String word);
    }

//    task 4

    public static void taskFour() {

        List<Integer> list = Arrays.asList(createList());
        Integer maxInt = list.stream()
                .max(Comparator.comparing(i -> i))
                .get();
        System.out.println("Максимальное значение в массиве: " + maxInt + "\n");

        /*ещё один вариант*/
        System.out.println("Максимальное значение в массиве: " + Arrays.stream(createList())
                .max(Comparator.comparing(i -> i)).get());
    }

//    task 5

    public static void taskFive() {

        List<Integer> list = Arrays.asList(createList());
        int sum = list.stream().reduce((x, y) -> x + y).get();
        System.out.println((double) sum / list.size());
    }

//    task 6

    public static void taskSix() {

        String simbol = "a";
        List<String> list = new ArrayList<>();
        Collections.addAll(list, "qazw", "aqse", "aaa", "Aax","afd" , "qaws", "azs" );

        System.out.println(list.stream()
                .filter( n -> n.length() == 3 )
                .filter(n -> n.substring(0,1).equals(simbol))
                .collect( Collectors.toList()));
    }
}
