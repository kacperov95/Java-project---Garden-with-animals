package pl.edu.pg;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/** Class with methods for Garden used in Menu */
public class GardenMethods extends Menu{

    /** Load Garden size from file  */
    public int loadGarden (String fileName) {
        int garden = 1;
        try {
            Scanner scanner = new Scanner(new File(fileName));
            garden = scanner.nextInt();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return garden;
    }

    /** Files for recreate garden missing message */
    public void filesNotExists() {
        System.out.println("Wymagane pliki nie istnieją lub nie mogę ich znaleźć.\nTworzę nowy ogród");
        garden.setSize((new GardenMethods().returnGardenSize()));
        System.out.println("Utworzono nowy ogród o rozmiarze " + garden.getSize());
    }

    /** Set garden size from keyboard with validation  */
    public int returnGardenSize() {
        int gardenSize;
        while (true){
            System.out.println("Wybierz rozmiar ogrodu.\nMinimalny rozmiar to 2 pola.\nMaksymalny rozmiar to 14 pól.");
            String i = scanner.next();
            if (i.equals("2") || i.equals("3") || i.equals("4") || i.equals("5") || i.equals("6") || i.equals("7")
                    || i.equals("8") || i.equals("9") || i.equals("10") || i.equals("11") || i.equals("12")
                    ||  i.equals("13") || i.equals("14")){
                return gardenSize = Integer.parseInt(i);
            }
            else {
                System.out.println("Podano błędny rozmiar");
            }
        }
    }


}
