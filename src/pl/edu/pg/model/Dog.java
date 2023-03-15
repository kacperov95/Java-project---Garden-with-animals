package pl.edu.pg.model;
/** Class with Dogs */
public class Dog extends Animal{

    /** Constructor */
    public Dog(int id, Kind kind, String name, String race, Sex sex, String age, Owner owner) {
        super(id, kind, name, sex, age, owner);
        this.race = race;
    }
}

