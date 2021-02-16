package Lesson_1.entities;

public interface RunRun {

    default void run(int a, int b, String name) {
        if (a > b) {
            System.out.println(name + " преодалевает препятствие!");
        } else {
            System.out.println(name + " недопрыгнул");
        }
    }
}
