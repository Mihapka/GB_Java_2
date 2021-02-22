package Lesson_2;

public class StartLesson_2 {

    /*задание*/ {/*
    1. Напишите метод, на вход которого подаётся двумерный строковый массив размером 4х4,
    при подаче массива другого размера необходимо бросить исключение MyArraySizeException.
    2. Далее метод должен пройтись по всем элементам массива, преобразовать в int, и просуммировать.
    Если в каком-то элементе массива преобразование не удалось (например, в ячейке лежит символ или
    текст вместо числа), должно быть брошено исключение MyArrayDataException, с детализацией в какой именно
    ячейке лежат неверные данные.
    3. В методе main() вызвать полученный метод, обработать возможные исключения MySizeArrayException и
    MyArrayDataException, и вывести результат расчета.*/
    }

    static int summ = 0;

    public static void main(String[] args) {

//        73
        String[][] array = {{"1", "4", "7", "0"}, {"-2", "1", "4", "-3"}, {"7", "10", "13", "6"}, {"3", "5", "9", "8"}};
//        63
        String[][] arrayWrongContent = {{"1", "4", "7", "0"}, {"-2", "1", "4", "-3"}, {"7", "q", "13", "6"}, {"3", "5", "9", "8"}};
//        49
        String[][] arrayWrongLenght = {{"1q", "4", "7", "0"}, {"-2", "1", "4", "-3"}, {"7", "6"}, {"3", "5", "9", "8"}};
//        37
        String[][] arrayWrongLenght2 = {{"1", "4", "7", "0"}, {"-2", "1", "4", "-3"}, {"3", "5", "9", "8"}};

        checkArray(array);
        checkArray(arrayWrongContent);
        checkArray(arrayWrongLenght);
        checkArray(arrayWrongLenght2);
    }


    public static void checkSumArray(String[][] array) {

        for (int k = 0; k < array.length; k++) {
            for (int l = 0; l < array[k].length; l++) {
                try {
                    summ += Integer.parseInt(array[k][l]);
                } catch (NumberFormatException e) {
                    System.out.println("Ой, неверный формат в ячейке: " + "[" + k + "]" + "[" + l + "]");
                }
            }
        }
        System.out.println("Сумма чисел в массиве: " + summ + "\n");
        summ = 0;
    }

    public static void checkLenghtArray(String[][] array) {

        if (array.length != 4) {
            throw new IllegalArgumentException("неверный количество строк, их: " + array.length);
        }

        for (int k = 0; k < array.length; k++) {
            if (array[k].length != 4) {
                throw new IllegalArgumentException("неверный размер столбца номер: " + k);
            }
        }
    }

    public static void checkArray(String[][] array) {

        try {
            checkLenghtArray(array);
            checkSumArray(array);
        } catch (MyException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
            checkSumArray(array);
        }
    }
}
