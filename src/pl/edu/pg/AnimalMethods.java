package pl.edu.pg;
import pl.edu.pg.model.*;
import pl.edu.pg.model.Animal;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/** Class with methods for Animals used in AnimalsMenu */
public class AnimalMethods{
    Scanner scanner = new Scanner(System.in);

    /** Where animal went */
    public void moveInfo (int id, List<Animal> animals) {
        System.out.println(animals.get(id-1).showKindString() + " " +animals.get(id-1).getAnimalId() + " zmienił położenie\n" +
                "+++ Nowe położenie +++\nKolumna: " + animals.get(id-1).getCoordinateX() + "\nWiersz: " + animals.get(id-1).getCoordinateY());
    }

    /** Moving animal to the left/right/up/down */
    public void move(int id, List<Animal> animals, Garden garden) {
        id = id - 1;
        System.out.println("Wybrałeś zwierzę: " + animals.get(id).showKindString() + " o ID: " + animals.get(id).getAnimalId() + "\n");
        System.out.println("Aby poruszyć wybranym zwierzęciem wybierz odpowiednią opcję:\n" +
                "1 - przejdź w lewo\n" +
                "2 - przejdź w prawo\n" +
                "3 - przejdź w górę\n" +
                "4 - przejdź w dół\n");
        int move = scanner.nextInt();

        if (move == 1) {
            animals.get(id).moveLeft(animals);
        }

        else if (move == 2) {
            animals.get(id).moveRight(garden, animals);
        }

        else if (move == 3) {
            animals.get(id).moveUp(animals);
        }

        else if (move == 4) {
            animals.get(id).moveDown(garden, animals);
        }

        else {
            System.out.println("Nie wybrałeś żadnego kierunku.\n Wracam do menu.\n");
        }
    }

    /** Adding new Animal by user */
    public Animal animalFromKeyboard(List<Owner> owners, List<Animal> animals) {
        String i;
        String kindTmp;
        int ownerIdNumber;
        String name;
        String race = "";
        Being.Sex sex;
        String age;
        if (owners.isEmpty()) {
            System.out.println("Nie ma właścicieli, którym można by przypisać zwierzę.");
            return null;
        }
        else {
            System.out.println("Kreator nowego zwierzęcia\n" +
                    "Wybierz rodzaj zwierzęcia: 1 - Kot, 2 - Pies, 3 - Żółw");
            kindTmp = scanner.next();
            while (true) {

                if ( kindTmp.equals("1")) {
                    Animal.Kind kind = Animal.Kind.CAT;
                    break;
                }  // Cat
                else if ( kindTmp.equals("2")) {
                    Animal.Kind kind = Animal.Kind.DOG;
                    break;
                }  // Dog
                else if ( kindTmp.equals("3")) {
                    Animal.Kind kind = Animal.Kind.TURTLE;
                    break;
                }  // Turtle
                else {
                    System.out.println("Nieprawidłowy znak, spróbuj ponownie:");
                }
            } // Kind of animal
            System.out.println("Podaj imię: ");
            name = scanner.next();
            if (kindTmp.equals("1") || kindTmp.equals("2")) {
                System.out.println("Podaj rasę");
                race = scanner.next();
            } // Rasa kota/psa
            while (true) {
                System.out.println("Wybierz płeć: 1 - męska, 2 - żeńska");
                i = scanner.next();
                if (i.equals("1")) { break; };
                if (i.equals("2")) { break; }
                System.out.println("Wybrano złą cyfrę, spróbuj ponownie");
            } // Sex scanner
            System.out.println("Podaj wiek: ");
            age = scanner.next();
            sex = null;
            if (i.equals("1")) {sex = Being.Sex.MEN;};
            if (i.equals("2")) {sex = Being.Sex.WOMEN;};
            // Id właściciela
            System.out.println("Podaj ID właściciela");


                ownerIdNumber = Integer.parseInt((scanner.next()));


            if (owners.size() >= ownerIdNumber) {
                }
                else {
                    System.out.println( "Właściciel o podanym ID nie istnieje. Spróbuj ponownie podać id właściciela lub wpisz nie, aby wyjść do menu");
                    String temp = scanner.next();
                    if (temp.equals("nie"))
                        return null;
                }
        }
        if (kindTmp.equals("1")) {
            return new Cat(animals.size()+1,Animal.Kind.CAT,name,race,sex,age, owners.get(ownerIdNumber - 1));
        }
        else if (kindTmp.equals("2")) {
            return new Dog(animals.size()+1,Animal.Kind.DOG, name, race, sex, age, owners.get(ownerIdNumber - 1));
        }
        else if (kindTmp.equals("3")) {
            return new Turtle(animals.size()+1,Animal.Kind.TURTLE, name, sex, age, owners.get(ownerIdNumber - 1));
        }
        else {
            return null;
        }
    }

    /** Load list of animals from file */
    public List<Animal> loadAnimals(String fileName, List<Owner> owners) {
        Animal.Kind kind;
        String name;
        String race = "";
        String age;
        int id;
        Being.Sex sex;
        List<Animal> animals = new ArrayList<>();
        File file = new File(fileName);
        long s = file.length();


        if (file.length()>0) {
            try {
            Scanner scanner = new Scanner(new File(fileName));
            while (scanner.hasNextLine()) {
                /** Load ID */
                id = scanner.nextInt();

                /** Load Kind */
                String kindTmp = scanner.next();
                if (kindTmp.equals("CAT")) {
                    kind = Animal.Kind.CAT;
                }
                else if (kindTmp.equals("DOG")) {
                    kind = Animal.Kind.DOG;
                }
                else {
                    kind = Animal.Kind.TURTLE;
                }

                /** Load race (CAT/DOG only) */
                if (kind == Animal.Kind.CAT || kind == Animal.Kind.DOG ) {
                    race = scanner.next();
                }

                /** Load name */
                name = scanner.next();

                /** Load sex */
                String sexTmp = scanner.next();
                if (sexTmp.equals("MEN")) {
                    sex = Being.Sex.MEN;
                }
                else {
                    sex = Being.Sex.WOMEN;
                }

                /** Load age */
                age = scanner.next();

                /** Load Position X */
                String posX = scanner.next();
                int x = Integer.parseInt(posX);

                /** Load Position Y */
                String posY = scanner.next();
                int y = Integer.parseInt(posY);

                /** Load Owner ID */
                String ownerId = scanner.next();

                if (kindTmp.equals("CAT")) {
                    animals.add(new Dog(id,kind,race,name,sex,age,owners.get(Integer.parseInt(ownerId)-1)));
                }
                else if (kindTmp.equals("DOG")) {
                    animals.add(new Cat(id,kind,race,name,sex,age,owners.get(Integer.parseInt(ownerId)-1)));
                }
                else {
                    animals.add(new Turtle(id,kind,name,sex,age,owners.get(Integer.parseInt(ownerId)-1)));
                }

                animals.get(animals.size()-1).setCoordinateX(x);
                animals.get(animals.size()-1).setCoordinateY(y);
            }




        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
            return animals;
        }
        else {return animals;}


    }

    /** Fill Animal ID */
    public int fillAnimalId () {
        System.out.println("Wprowadź ID zwierzaka:");
        return scanner.nextInt();
    }

    /** Check if file with Animals exists */
    public boolean animalsExistCheck() {
        File f = new File("Animals.txt");
        return f.exists();
    }
}
