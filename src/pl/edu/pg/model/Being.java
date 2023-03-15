package pl.edu.pg.model;

/** Class for all Beings */
public class Being {

    public enum Sex {
        MEN("męska"),
        WOMEN("żeńska");

        private String value;

        Sex(String value) {
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
    protected String name;
    protected Sex sex;
    protected String age;

    /** Constructor */
    public Being(String name, Sex sex, String age) {
        this.name = name;
        this.sex = sex;
        this.age = age;
    }

    /** Display Being basic info */
    public void beingBasicInfo() {
        System.out.println("Imię: " + this.name);
        System.out.println("Płeć: " + this.sex.value);
        System.out.println("Wiek: " + this.age);
    }

    // Getters & Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
