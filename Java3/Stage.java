package ru.gb.jThree.hwork.race;

public abstract class Stage {
    int length;
    String description;
    public abstract void go(Car c);
    String getDescription() {
        return description;
    }
}
