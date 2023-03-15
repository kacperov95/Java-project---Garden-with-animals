package pl.edu.pg.model;
/** Class with Turtles */
public class Turtle extends Animal{

    /** Constructor */
    public Turtle(int id, Kind kind, String name, Sex sex, String age, Owner owner) {
        super(id, kind, name, sex, age, owner);
        this.state = TurtleState.INACTIVE;
    }
    // Getters & Setters

    public TurtleState getState() {
        return state;
    }

    public void setState(TurtleState state) {
        this.state = state;
    }
}
