package Lesson_1.entities;

import Lesson_1.Wall;

public interface Jumpable {

    default void jump(int a, int b, String name) {
        if (a > b) {
            System.out.println(name + " преодалевает препятствие!");
        } else {
            System.out.println(name + " недопрыгнул");
        }
    }
}
