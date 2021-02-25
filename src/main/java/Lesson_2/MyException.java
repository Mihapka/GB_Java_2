package Lesson_2;

public class MyException extends RuntimeException {

    public MyException() {
        super();
    }


    public MyException(String a, int b) {
        System.out.println("Неверный формат " + a + " " + b);
    }

}
