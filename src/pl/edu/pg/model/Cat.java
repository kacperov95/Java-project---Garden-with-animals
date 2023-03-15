package pl.edu.pg.model;

import pl.edu.pg.Saveable;

/** Class with Cats */
public class Cat extends Animal implements Saveable {

    /** Constructor */
    public Cat(int id, Kind kind, String race, String name, Sex sex, String age, Owner owner) {
        super(id, kind, name, sex, age, owner);
        this.race = race;
    }
}
