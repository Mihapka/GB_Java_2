package Lesson_2;

public class MyException extends NumberFormatException {


    public MyException(String a, int b) {
        System.out.println("Неверный формат " + a + " номер " + b);
    }

    public MyException(int k, int l) {

        System.out.println("Не верный формат данных в ячейке: " + "[" + k + "]" + "[" + l + "]");
    }
}
