package pl.edu.pg.model;

import pl.edu.pg.Saveable;
import java.util.List;

/** Class with Animals */
public class Animal extends Being implements Saveable {

    public enum TurtleState {
        ACTIVE("Nakarmiony"),
        INACTIVE("Nienakarmiony");

        private String value;

        TurtleState(String value) {
            this.value = value;
        }

        // Getters & Setters
        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
    public enum Kind {
        CAT("Kot"),
        DOG("Pies"),
        TURTLE("Żółw");

        private String value;

        Kind(String value) {
            this.value = value;
        }

        // Getters & Setters
        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    private Kind kind;
    protected int animalId;
    private int coordinateX;
    private int coordinateY;
    private Owner owner;
    protected String race;
    protected TurtleState state;

    /** Constructor */
    public Animal(int id,Kind kind, String name, Sex sex, String age, Owner owner) {
        super(name, sex, age);
        this.kind = kind;
        this.coordinateX = 1;
        this.coordinateY = 1;
        this.owner = owner;
        this.animalId = id;
    }

    /** Display information about single Animal */
    public void infoAnimal(){
        System.out.println("+++++ Zwierzę " + this.animalId + " +++++");
        System.out.println("Gatunek: " + this.kind.value);
        if (race != null) { System.out.println("Rasa: " + this.race); }
        this.beingBasicInfo();
        System.out.println("Pozycja: " + this.coordinateX + " x " + this.coordinateY);
        System.out.println("ID właściciela: " + owner.getOwnerId());
        if (state != null) { System.out.println("Stan: " + this.state.value); }
        System.out.println();
    }

    /** Animal goes up  */
    public void moveUp (List<Animal> animals){
        if (this.coordinateY == 1) {
            System.out.println( "Jesteś na szczycie ogrodu, nie możesz iść w górę" );
        }
        else {
            for (Animal animal : animals) {
                if (animal.coordinateY == this.coordinateY - 1) {
                    for (Animal animal1 : animals) {
                        if (animal.coordinateX == this.coordinateX) {
                            System.out.println("Pole zajęte");
                            break;
                            }
                    }
                }
                else {
                    System.out.println("Zmieniam położenie zwierzęcia " + this.animalId);
                    this.coordinateY = this.coordinateY - 1;
                    break;
                }
            }
        }
    }

    /** Animal goes down  */
    public void moveDown (Garden garden, List<Animal> animals) {
        if (this.coordinateY == garden.getSize()) {
            System.out.println( "Jesteś na dnie ogrodu, nie możesz iść dalej." );
        }
        else {
            for (Animal animal : animals) {
                if (animal.coordinateY == this.coordinateY + 1) {
                    for (Animal animal1 : animals) {
                        if (animal.coordinateX == this.coordinateX) {
                            System.out.println("Pole zajęte");
                            break;
                        }
                    }
                }
                else {
                    System.out.println("Zmieniam położenie zwierzęcia " + this.animalId);
                    this.coordinateY = this.coordinateY + 1;
                    break;
                }
            }
        }
    }

    /** Animal goes left  */
    public void moveLeft (List<Animal> animals) {
        if (this.coordinateX == 1) {
            System.out.println( "Jesteś przy lewej krawędzi ogrodu, nie możesz iść dalej" );
        }
        else {
            for (Animal animal : animals) {
                if (animal.coordinateX == this.coordinateX - 1) {
                    for (Animal animal1 : animals) {
                        if (animal.coordinateY == this.coordinateY) {
                            System.out.println("Pole zajęte");
                            break;
                        }
                    }
                }
                else {
                    System.out.println("Zmieniam położenie zwierzęcia " + this.animalId);
                    this.coordinateX = this.coordinateX - 1;
                    break;
                }
            }
        }
    }

    /** Animal goes right  */
    public void moveRight (Garden garden, List<Animal> animals) {
        if (this.coordinateX == garden.getSize()) {
            System.out.println( "Jesteś przy prawej krawędzi ogrodu, nie możesz iść dalej" );
        }
        else {
            for (Animal animal : animals) {
                if (animal.coordinateX == this.coordinateX + 1) {
                    for (Animal animal1 : animals) {
                        if (animal.coordinateY == this.coordinateY) {
                            System.out.println("Pole zajęte");
                            break;
                        }
                    }
                }
                else {
                    System.out.println("Zmieniam położenie zwierzęcia " + this.animalId);
                    this.coordinateX = this.coordinateX + 1;
                    break;
                }
            }
        }
    }

    /** Single Owner data to save in file */
    public String showDataToSave() {
        String data = "\n" + String.valueOf(this.animalId) + " ";
        data += this.kind + " ";
        if (this.race != null) {data += this.race + " ";}
        data += this.name + " ";
        data += this.sex + " ";
        data += this.age + " ";
        data += this.coordinateX + " ";
        data += this.coordinateY + " ";
        data += this.owner.getOwnerId();
        return data;
    }

    /** File name for file with Owners */
    public String showFileNameToSave() {
        return "Animals.txt";
    }

    /** Return Animal kind as a String */
    public String showKindString() {
        return kind.value;
    }

    /** Return Animal owner ID */
    public int showOwnerId() {
        return this.getOwner().getOwnerId();
    }

    // Getters & Setters
    public int getAnimalId() {
        return animalId;
    }

    public void setAnimalId(int animalId) {
        this.animalId = animalId;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public void setCoordinateX(int coordinateX) {
        this.coordinateX = coordinateX;
    }

    public void setCoordinateY(int coordinateY) {
        this.coordinateY = coordinateY;
    }

    public int getCoordinateX() {
        return coordinateX;
    }

    public int getCoordinateY() {
        return coordinateY;
    }

    public Kind getKind() {
        return kind;
    }

    public TurtleState getState() {
        return state;
    }

    public void setState(TurtleState state) {
        this.state = state;
    }
}