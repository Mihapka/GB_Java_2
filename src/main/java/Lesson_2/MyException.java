package Lesson_2;

public class MyException extends NumberFormatException {


    public MyException(String a, int b) {
        System.out.println("Неверный формат " + a + " " + b);
    }

    public MyException(String a, String b) {
        System.out.println("Неверный формат " + a + " " + b);
    }
}
