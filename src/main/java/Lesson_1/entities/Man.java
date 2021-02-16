package Lesson_1.entities;

import Lesson_1.Treadmill;
import Lesson_1.Wall;

public class Man implements Move{

    private String name;
    private int maxDistance;
    private int maxJump;

    public Man(String name) {
        this.name = name;
        this.maxDistance = 5000;
        this.maxJump = 1;
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
