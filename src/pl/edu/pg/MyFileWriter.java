package pl.edu.pg;

import pl.edu.pg.model.Animal;
import pl.edu.pg.model.Owner;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;
import java.io.FileWriter;

/** Class for writing data to files */
public class MyFileWriter {

    /** Clear data from chosen file */
    public void clearFile (String fileName) {
        try {
            FileWriter tmp = new FileWriter(fileName);
            tmp.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** Overwrite file with current Animal list */
    public void overwritingAnimal(List<Animal> saveable, String fileName) {
        FileWriter tmp = null;
        try {
            tmp = new FileWriter(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        MyFileWriter fileWriter = new MyFileWriter();
        for (Saveable saveables : saveable) {
            fileWriter.saveList(saveables);
        }
        try {
            tmp.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** Overwrite file with current Owners list */
    public void overwritingOwners(List<Owner> saveable, String fileName) {
        FileWriter tmp = null;
        try {
            tmp = new FileWriter(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        MyFileWriter fileWriter = new MyFileWriter();
        for (Owner saveables : saveable) {
            fileWriter.saveList(saveables);
        }
        try {
            tmp.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** Overwrite file with current Garden size */
    public void overwritingGarden(Saveable saveable, String fileName) {
        FileWriter tmp = null;
        try {
            tmp = new FileWriter(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        MyFileWriter fileWriter = new MyFileWriter();
        fileWriter.saveList(saveable);
        try {
            tmp.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** Saving list */
    public void saveList(Saveable objectToSave){
        saveList(List.of(objectToSave));
    }

    /** Saving list */
    public void saveList(List<Saveable> objectsToSave){
        BufferedWriter bw = null;
        try {
            FileWriter fw = new FileWriter(objectsToSave.get(0).showFileNameToSave(), true);
            bw = new BufferedWriter(fw);
            for (Saveable saveable : objectsToSave) {
                bw.write(saveable.showDataToSave());
            }

        } catch (IOException e) {
            System.out.println("Błąd przy próbie zapisu do pliku " + objectsToSave.get(0).showFileNameToSave());
        }
        finally {
            if (bw != null) {
                try {
                    bw.close();
                }
                catch (IOException e) {
                    System.out.println("Błąd przy próbie zamknięcia pliku " + objectsToSave.get(0).showFileNameToSave());
                }
            }
        }
    }
}
