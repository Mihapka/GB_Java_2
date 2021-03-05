/*
       1. Необходимо написать два метода, которые делают следующее:
        1) Создают одномерный длинный массив, например:

        static final int size = 10000000;
        static final int h = size / 2;
        float[] arr = new float[size];

        2) Заполняют этот массив единицами;
        3) Засекают время выполнения: long a = System.currentTimeMillis();
        4) Проходят по всему массиву и для каждой ячейки считают новое значение по формуле:
        arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5)
        * Math.cos(0.4f + i / 2));
        5) Проверяется время окончания метода System.currentTimeMillis();
        6) В консоль выводится время работы: System.out.println(System.currentTimeMillis() - a);

        Отличие первого метода от второго:
        Первый просто бежит по массиву и вычисляет значения.
        Второй разбивает массив на два массива, в двух потоках высчитывает новые значения и потом
        склеивает эти массивы обратно в один.

        Пример деления одного массива на два:

        System.arraycopy(arr, 0, a1, 0, h);
        System.arraycopy(arr, h, a2, 0, h);

        Пример обратной склейки:

        System.arraycopy(a1, 0, arr, 0, h);
        System.arraycopy(a2, 0, arr, h, h);

    Примечание:
        System.arraycopy() – копирует данные из одного массива в другой:
        System.arraycopy(массив-источник, откуда начинаем брать данные из массива-источника,
        массив-назначение, откуда начинаем записывать данные в массив-назначение, сколько ячеек копируем)
        По замерам времени:
        Для первого метода надо считать время только на цикл расчета:

        for (int i = 0; i < size; i++) {
        arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5)
        * Math.cos(0.4f + i / 2));
        }

        Для второго метода замеряете время разбивки массива на 2, просчета каждого из двух
        массивов и склейки.*/

package Lesson_5;


public class StartLesson_5 {

    static final int size = 10000000;
    static final int h = size / 2;
    static float[] arr = new float[size];

    public static void main(String[] args) {

        oneTreadExecution(arr);
        twoTreadsExecution(arr);
        sequentialExecution(arr);
    }

    public static void oneTreadExecution(float[] arr) {

        System.out.println("Однопоточная реализаия: " + "\n");
        long a = System.currentTimeMillis();

        arrayFilling(arr);

        System.out.println(System.currentTimeMillis() - a);
    }

    public static void twoTreadsExecution(float[] arr) {

        System.out.println("Многопоточная реализаия: " + "\n");

        float[] arr1 = new float[h];
        float[] arr2 = new float[h];

        long a = System.currentTimeMillis();

        System.arraycopy(arr, 0, arr1, 0, h);
        System.arraycopy(arr, h, arr2, 0, h);

        Thread t = new Thread(() -> arrayFilling(arr1));
        t.start();

        Thread t2 = new Thread(() -> arrayFilling(arr2));
        t2.start();

        try {
            t.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.arraycopy(arr1, 0, arr, 0, h);
        System.arraycopy(arr2, 0, arr, h, h);

        System.out.println(System.currentTimeMillis() - a);
    }

    public static void sequentialExecution(float[] arr) {

        System.out.println("Последовательная реализаия: " + "\n");

        float[] arr1 = new float[h];
        float[] arr2 = new float[h];

        long a = System.currentTimeMillis();

        System.arraycopy(arr, 0, arr1, 0, h);
        System.arraycopy(arr, h, arr2, 0, h);

        arrayFilling(arr1);
        arrayFilling(arr2);

        System.arraycopy(arr1, 0, arr, 0, h);
        System.arraycopy(arr2, 0, arr, h, h);

        System.out.println(System.currentTimeMillis() - a);

    }

    public static void arrayFilling(float[] arr) {


        for (int i = 0; i < arr.length; i++) {
            arr[i] = 1;
        }
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5)
                    * Math.cos(0.4f + i / 2));
        }
    }
}
