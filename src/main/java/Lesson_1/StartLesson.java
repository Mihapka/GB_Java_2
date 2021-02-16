package Lesson_1;

import Lesson_1.entities.Cat;
import Lesson_1.entities.Man;
import Lesson_1.entities.Robat;

public class StartLesson {

    static boolean win = false;

    static Object[] entities = new Object[3];
    static Object[] things = new Object[2];

    public static void main(String[] args) {

        Wall wall = new Wall();
        Treadmill treadmill = new Treadmill();
        Cat cat = new Cat("Barsik");
        Man man = new Man("Vasia");
        Robat robat = new Robat("T-800");
        entities[0] = cat;
        entities[1] = man;
        entities[2] = robat;
        things[0] = wall;
        things[1] = treadmill;

        for (int i = 0; i < entities.length; i++) {
            if (entities[i] instanceof Cat) {
                ((Cat) entities[i]).move(wall, treadmill);
            } else {
                if (entities[i] instanceof Man) {
                    ((Man) entities[i]).move(wall, treadmill);
                } else {
                    ((Robat) entities[i]).move(wall, treadmill);
                }
            }
        }
    }
}

