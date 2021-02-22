package Lesson_1;

import Lesson_1.entities.Cat;
import Lesson_1.entities.Man;
import Lesson_1.entities.Robat;

public class StartLesson {

    /*задание*/ {/*Создайте три класса Человек, Кот, Робот, которые не наследуются от одного класса.
          Эти классы должны уметь бегать и прыгать (методы просто выводят информацию о действии в консоль).
            Создайте два класса: беговая дорожка и стена, при прохождении через которые,
          участники должны выполнять соответствующие действия (бежать или прыгать),
          результат выполнения печатаем в консоль (успешно пробежал, не смог пробежать и т.д.).
            Создайте два массива: с участниками и препятствиями, и заставьте всех участников
          пройти этот набор препятствий.
            * У препятствий есть длина (для дорожки) или высота (для стены), а участников
        ограничения на бег и прыжки. Если участник не смог пройти одно из препятствий, то
        дальше по списку он препятствий не идет.*/
    }

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

