package pl.edu.pg.model;

import pl.edu.pg.Saveable;
import java.io.File;

/** Class with Garden data and methods */
public class Garden implements Saveable {
    private int size;

    /** Constructor */
    public Garden(int size) {
        this.size = size;
    }

    /** Display garden size */
    public void showGardenSize() {
        System.out.println("Rozmiar ogrodu:" + this.size + "x" + this.size);
    }

    /** Returning file name to save garden size */
    public String showFileNameToSave() {
        return "garden.txt";
    }

    /** Data for saving in file */
    public String showDataToSave() {
        return String.valueOf(this.size);
    }

    /** Check if file with Garden exists */
    public boolean gardenExistCheck() {
        File f = new File("Garden.txt");
        return f.exists();
    }

// Getters & Setters
    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
