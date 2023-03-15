package pl.edu.pg.model;

import pl.edu.pg.Saveable;
import java.util.ArrayList;
import java.util.List;

/** Class with Owners */
public class Owner extends Being implements Saveable {
    private static int ownerCount = 1;
    private int ownerId;
    private String lastname;
    private List<Animal> animalList = new ArrayList<>();

    /** Constructor */
    public Owner(int ownerId, String name, String lastname, Sex sex, String age) {
        super(name, sex, age);
        this.lastname = lastname;
        this.ownerId = ownerId;
    }

    /** Display information about single Owner */
    public void infoOwner() {
        System.out.println("----- Właściciel " + this.ownerId +" -----\nID: " + this.ownerId);
        System.out.println("Nazwisko: " + this.lastname);
        this.beingBasicInfo();
    }

    @Override
    /** Single Owner data to save in file */
    public String showDataToSave() {
        String data = "\n" + this.ownerId + " "
                + this.name + " "
                + this.lastname + " "
                + this.sex + " "
                + this.age + "";
        return data;
    }

    @Override
    /** File name for file with Owners */
    public String showFileNameToSave() {
        return "Owners.txt";
    }

    // Getters & Setters
    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public int getOwnerId() {
        return ownerId;
    }
}
