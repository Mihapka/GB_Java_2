package Lesson_1.entities;

import Lesson_1.Move;
import Lesson_1.things.Treadmill;
import Lesson_1.things.Wall;

public class Cat implements Move {

    private String name;
    private int maxDistance;
    private int maxJump;

    public Cat(String name) {
        this.name = name;
        this.maxDistance = 1000;
        this.maxJump = 3;
    }

    public void move(Wall wall, Treadmill treadmill) {
        int jump = this.maxJump;
        int dist = this.maxDistance;
        move(jump, dist, wall, treadmill, this.name);
    }


    public String getName() {
        return name;
    }

    public int getMaxDistance() {
        return maxDistance;
    }

    public int getMaxJump() {
        return maxJump;
    }
}
