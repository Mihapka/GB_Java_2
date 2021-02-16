package Lesson_1;

import Lesson_1.things.Treadmill;
import Lesson_1.things.Wall;

public interface Move {

    default void move(int jump, int dist, Wall wall, Treadmill treadmill, String name) {

        boolean win = false;
        if (jump > wall.getHight()) {
            System.out.println("Участник " + name + " преодалевает высоту в " + wall.getHight() + "м");
        } else {
            System.out.println("Участник " + name + " недопрыгнул");
            return;
        }
        if (dist > treadmill.getDistance()) {
            System.out.println("Участник " + name + " преодалевает расстояние в " + treadmill.getDistance() + "м");
        } else {
            System.out.println("Участник " + name + " недобежал");
        }
    }
}
