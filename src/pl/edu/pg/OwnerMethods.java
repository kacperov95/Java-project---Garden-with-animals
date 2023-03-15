package pl.edu.pg;
import pl.edu.pg.model.*;
import pl.edu.pg.model.Owner;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/** Class for methods used in OwnersMenu */
public class OwnerMethods{
    Scanner scanner = new Scanner(System.in);
    int i;

    /** Adding new Owner by user */
    public Owner ownerFromKeyboard (List<Owner> owners) {
        System.out.println("Kreator nowego właściciela\n" +
                "Podaj imię: ");
        String name = scanner.next();
        System.out.println("Podaj nazwisko: ");
        String lastname = scanner.next();
        while (true) {
            System.out.println("Wybierz płeć: 1 - męska, 2 - żeńska");
            i = scanner.nextInt();
            if (i == 1) { break; };
            if (i == 2) { break; }
            System.out.println("Wybrano złą cyfrę, spróbuj ponownie");
        }
        System.out.println("Podaj wiek: ");
        String age = scanner.next();
        Being.Sex sex = null;
        if (i == 1) {sex = Being.Sex.MEN;};
        if (i == 2) {sex = Being.Sex.WOMEN;};
        return new Owner(owners.size()+1, name, lastname, sex, age);
    }

    /** Fill Owner ID */
    public int fillOwnerId () {
        System.out.println("Wprowadź ID właściciela:");
        return scanner.nextInt();
    }

    /** Load list of owners from file */
    public List<Owner> loadOwners(String fileName) {
        String name;
        String lastname;
        String age;
        int id;
        Being.Sex sex;
        List<Owner> owners = new ArrayList<>();

        try {
            Scanner scanner = new Scanner(new File(fileName));
            while (scanner.hasNextLine()) {
                /** Load ID */
                id = scanner.nextInt();

                /** Load name */
                name = scanner.next();

                /** Load lastname */
                lastname = scanner.next();

                /** Load sex */
                String sexTmp = scanner.next();
                if (sexTmp == "MEN") {
                    sex = Being.Sex.MEN;
                }
                else {
                    sex = Being.Sex.WOMEN;
                }

                /** Load age */
                age = scanner.next();

                owners.add(new Owner(id,name,lastname,sex,age));
            }
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        return owners;
    }

    /** Check if file with Owners exists */
    public boolean ownersExistCheck() {
        File f = new File("Owners.txt");
        return f.exists();
    }
}
