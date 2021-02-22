package Lesson_2;

public class MyException extends RuntimeException {



    public MyException(String mesage, int a, int b) {
        System.out.println(mesage + " в ячейке массива: " + a + " " + b);
    }
}
