package Lesson_3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PhoneBook {

    private Map<String, List<String>> phoneBook = new HashMap<>();
    private List<String> phoneNumbers;

    public void add(String lastname, String phoneNumber) {
        if (phoneBook.containsKey(lastname)) {
//              в наш массив копируем массив номеров по фамиии
            phoneNumbers = phoneBook.get(lastname);
//              добавляем новый номер с той же фамилией
            phoneNumbers.add(phoneNumber);
//              сохраняем обратно в мап с обновленным списком номеров
            phoneBook.put(lastname, phoneNumbers);
        } else {
            phoneNumbers = new ArrayList<>();
            phoneNumbers.add(phoneNumber);
            phoneBook.put(lastname, phoneNumbers);
        }
    }

    public List<String> get(String surname) {
        return phoneBook.get(surname);
    }
}
