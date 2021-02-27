/*задание*/
/*
    1. Создать массив с набором слов (10-20 слов, должны встречаться повторяющиеся).
       Найти и вывести список уникальных слов, из которых состоит массив (дубликаты не считаем).
       Посчитать сколько раз встречается каждое слово.

    2. Написать простой класс ТелефонныйСправочник, который хранит в себе список фамилий и
       телефонных номеров. В этот телефонный справочник с помощью метода add() можно добавлять
       записи. С помощью метода get() искать номер телефона по фамилии. Следует учесть, что под
       одной фамилией может быть несколько телефонов (в случае однофамильцев), тогда при запросе
       такой фамилии должны выводиться все телефоны.

       Желательно как можно меньше добавлять своего, чего нет в задании (т.е. не надо в телефонную запись
       добавлять еще дополнительные поля (имя, отчество, адрес), делать взаимодействие с пользователем
       через консоль и т.д.). Консоль желательно не использовать (в том числе Scanner), тестировать
       просто из метода main() прописывая add() и get().
*/

package Lesson_3;

import java.util.HashMap;

public class StartLesson_3 {

    public static void main(String[] args) {

        wordChecker();      /*задание 1*/
        phonebook();        /*задание 2*/
    }

    public static void wordChecker() {

        HashMap<String, Integer> map = new HashMap<>();
        String[] array = {
                "qwerty", "qwjkerty", "qaz", "qwjkerty", "qwerty", "qwerty", "qaz", "qwerty", "qwjkerty", "wwww",
                "qwexcvxcbrty", "qwersdftty", "qweredby", "qwererfdy", "qwerextty", "qwererey", "qwerty", "qwjaty",
                "qwerxbttty"};


        for (int i = 0; i < array.length; i++) {

            if (!map.containsKey(array[i])) {
                map.put(array[i], 1);
            } else {
                map.put(array[i], map.get(array[i]) + 1);
            }
        }
        for (String key : map.keySet()) {
            Integer value = map.get(key);
            System.out.println(key + " --> " + value);
        }
    }

    public static void phonebook() {

        PhoneBook phoneBook = new PhoneBook();

        phoneBook.add("qwerty", "8911000001");
        phoneBook.add("asdfgh", "8911222223");
        phoneBook.add("zxcvbn", "8911333334");
        phoneBook.add("qazwsx", "8911555551");
        phoneBook.add("wsxedc", "8911852745");
        phoneBook.add("qwerty", "8911000002");
        phoneBook.add("asdfgh", "8911222224");
        phoneBook.add("zxcvbn", "8911333335");
        phoneBook.add("qazwsx", "8911555552");
        phoneBook.add("qwerty", "8911000003");

        System.out.println(phoneBook.get("qwerty"));
        System.out.println(phoneBook.get("asdfgh"));
        System.out.println(phoneBook.get("zxcvbn"));
        System.out.println(phoneBook.get("qazwsx"));
        System.out.println(phoneBook.get("wsxedc"));

    }
}