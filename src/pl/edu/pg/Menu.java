package pl.edu.pg;

import pl.edu.pg.model.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.Thread.sleep;


public class Menu {
    private String i = "1";
    protected Scanner scanner = new Scanner(System.in);
    protected List<Animal> animals = new ArrayList<>();
    protected List<Owner> owners = new ArrayList<>();
    protected Garden garden = new Garden(1);
    private final MyFileWriter fw = new MyFileWriter();
    private AnimalMethods animalMethods = new AnimalMethods();
    private OwnerMethods ownerMethods = new OwnerMethods();
    protected String ownersFileName = "Owners.txt";
    protected String animalsFileName = "Animals.txt";

    /** Stop program until user click "Enter" */
    public void stop() {
        System.out.println("\n(Kliknij enter by kontynuuować do menu)");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** Menu where you can choose if garden should be create from new or should be readed from files */
    public void startupMenu() {
        System.out.println("Witaj w programie zarządzającym ogrodem!");
        String i;
        while (true) {
            System.out.flush();
            scanner.reset();
            System.out.println("Wybierz czy chcesz stworzyć nowy ogród, czy może wolisz wczytać zapisany wcześniej ogród z plików\n" +
                    "1 - Utwórz nowy ogród\n" +
                    "2 - Wczytaj ogród z pliku (Wczytane zostaną również listy zwierząt i właścicieli)");
            i = scanner.next();
            System.out.println();

            if (i.equals("1")) {
                garden.setSize((new GardenMethods().returnGardenSize()));
                System.out.println("Utworzono nowy ogród o rozmiarze " + garden.getSize());
                break;
            }
            if (i.equals("2")) {
                if (garden.gardenExistCheck()) {
                    if (new OwnerMethods().ownersExistCheck()) {
                        if (new AnimalMethods().animalsExistCheck()) {
                            garden.setSize(new GardenMethods().loadGarden(garden.showFileNameToSave()));
                            owners = new OwnerMethods().loadOwners(ownersFileName);
                            animals = new AnimalMethods().loadAnimals(animalsFileName,owners);
                            break;
                        }
                        else {
                            new GardenMethods().filesNotExists();
                            break;
                        }
                    }
                    else {
                        new GardenMethods().filesNotExists();
                        break;
                    }
                }
                else {
                    new GardenMethods().filesNotExists();
                    break;
                }
            }
            else {
                System.out.println("Wybrano niepoprwaną opcję, spróbuj ponownie");
            }
        }
    }

    /** Main menu
     * 1 - Show garden (graphic representation)
     * 2 - Go to Owners Menu
     * 3 - Go to Animals Menu
     * 4 - Save Owners list in file (Action delete previously saved data)
     * 5 - Save Animals list in file (Action delete previously saved data)
     * 6 - Save all information about Garden in files (Action delete previously saved data in all files)
     * 7 - Clear Owners list
     * 8 - Clear Animals list
     * 0 - Close program
     * */
    public void mainMenu() {


        while(true){
            if (i.equals("0")) {
                break;
            }
            scanner.reset();
            System.out.flush();
            System.out.println("+++++ MENU GŁÓWNE +++++");
            System.out.println("Wybierz interesującą Cię opcje:\n" +
                    "1 - Pokaż rozmiar ogrodu\n" +
                    "2 - Przejdź do zarządzania właścicielami\n" +
                    "3 - Przejdź do zarządzania zwierzętami\n" +
                    "4 - Zapisz listę właścicieli do pliku (Uwaga: poprzedni zapis zostanie usunięty)\n" +
                    "5 - Zapisz listę zwierząt do pliku (Uwaga: poprzedni zapis zostanie usunięty)\n" +
                    "6 - Zapisz wszystkie informacje o ogrodzie do plików (Uwaga: Wszystkie pliki zostaną nadpisane!)\n" +
                    "7 - Wyczyść listę właścicieli\n" +
                    "8 - Wyczyść listę zwierząt\n" +
                    "0 - Zakończ działanie programu");
            i = scanner.next();
            switch (i) {
                /** Show garden size */
                case "1" -> {
                    garden.showGardenSize();
                    stop();
                }
                /** Go to Owners Menu */
                case "2" -> ownersMenu();
                /** Go to Animals Menu */
                case "3" -> animalsMenu();
                /** Save Owners list in file (Action delete previously saved data) */
                case "4" -> {
                    if (!owners.isEmpty()) {
                        System.out.println("Nadpisuję listę właścicieli.");
                        fw.overwritingOwners(owners, ownersFileName);
                    } else {
                        System.out.println("Lista właścicieli jest pusta. Czyszczę plik.");
                        fw.clearFile(ownersFileName);
                    }
                    stop();
                }
                /** Save Animals list in file (Action delete previously saved data) */
                case "5" -> {
                    if (!owners.isEmpty()) {
                        if (!animals.isEmpty()) {
                            System.out.println("Nadpisuję listę zwierząt.");
                            fw.overwritingAnimal(animals, animalsFileName);
                        } else {
                            System.out.println("Lista pusta, czyszczę plik.");
                            fw.clearFile(animalsFileName);
                        }
                    } else {
                        System.out.println("Lista pusta, czyszczę plik.");
                        fw.clearFile(animalsFileName);
                    }
                    stop();
                }
                /** Save all information about Garden in files (Action delete previously saved data in all files) */
                case "6" -> {
                    System.out.println("Nadpisuję wszystkie pliki.\n");
                    fw.overwritingOwners(owners, ownersFileName);
                    fw.overwritingAnimal(animals, animalsFileName);
                    String gardenFileName = "Garden.txt";
                    fw.overwritingGarden(garden, gardenFileName);
                    stop();
                }
                /** Clear Owners list */
                case "7" -> {
                    if (animals.isEmpty()) {
                        if (!owners.isEmpty()) {
                            fw.clearFile(ownersFileName);
                        } else {
                            System.out.println("Lista właścicieli jest już pusta.");
                        }
                    } else {
                        System.out.println("Nie możesz zostawić zwierząt bez właścicieli!\nMusisz usunąć zwierzęta zanim usuniesz wszystkich właścicieli!");
                    }
                    stop();
                }
                /** Clear Animals list */
                case "8" -> {
                    if (!animals.isEmpty()) {
                        fw.clearFile(animalsFileName);
                    } else {
                        System.out.println("Lista zwierząt jest już pusta.");
                    }
                    stop();
                }
                /** Close program */
                case "0" -> {
                    System.out.println("Do zobaczenia wkrótce!");
                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                /** Wrong choice, come back to menu */
                default -> {
                    System.out.println("Błędny wybór.");
                    stop();
                }
            }
        }
    }

    /** Menu to manage Owners
     * 1 - Add new Owner
     * 2 - Display list of Owners
     * 3 - Display info about Owner with entered ID
     * 4 - Display list of Owners with lists of Animals they have
     * 5 - Display list of Animals chosen Owner
     * 6 - Delete Animals of chosen Owner
     * 7 - Delete Owner
     * 0 - Go back to Main Menu
     * */
    public void ownersMenu() {
        String i = "1";
        do {
            if (i.equals("0")) {break;}
            scanner.reset();
            System.out.flush();
            System.out.println("+++++ MENU WŁAŚCICIELI +++++");
            System.out.println("Wybierz interesującą Cię opcje:\n" +
                    "1 - Dodaj nowego właściciela\n" +
                    "2 - Wyświetl listę właścicieli\n" +
                    "3 - Wyświetl dane właściciela o wprowadzonym ID\n" +
                    "4 - Wyświetl listę ID właścicieli razem z posiadanymi zwierzętami\n" +
                    "5 - Wyświetl listę zwierząt właściciela o wprowadzonym ID\n" +
                    "6 - Usuń właściciela\n" +
                    "0 - Wyjdź do menu głównego");
            i = scanner.next();

            switch (i) {
                /** Add new Owner */
                case "1" -> {
                    Owner method = new OwnerMethods().ownerFromKeyboard(owners);
                    if (method != null) {
                        owners.add(method);
                        System.out.println("Dodano nowego właściciela.");
                    } else {
                        System.out.println("Dodawanie nie powiodło się.");
                    }
                    stop();
                }
                /** Display list of Owners */
                case "2" -> {
                    if (!owners.isEmpty()) {
                        System.out.println("--- Lista właścicieli ---");
                        for (Owner owner : owners) {
                            owner.infoOwner();
                        }
                    }
                    else {
                        System.out.println("Lista właścicieli jest pusta.");
                    }

                    stop();
                }
                /** Display info about Owner with entered ID */
                case "3" -> {
                    if (!owners.isEmpty()) {
                        int temp = ownerMethods.fillOwnerId();
                        if (temp <= owners.size()) {
                            System.out.println("----- Dane właściciela o ID " + temp + " -----");
                            owners.get(temp - 1).infoOwner();
                        } else {
                            System.out.println("Właściciel o podanym ID nie istnieje.");
                        }
                    } else {
                        System.out.println("Nie wprowadzono jeszcze żadnego właściciela. Brak danych do wyświetlenia.");
                    }
                    stop();
                }
                /** Display list of Owners with lists of Animals they have */
                case "4" -> {
                    if (!owners.isEmpty()) {
                        if (!animals.isEmpty()) {
                            System.out.println("Lista zwierząt wszystkich właścicieli.\n");
                            for (int temp = 1; temp < owners.size(); temp++) {
                                System.out.println("Właściciel " + temp + "\n");
                                for (Animal animal : animals) {
                                    if (animal.showOwnerId() == temp) {
                                        animal.infoAnimal();
                                    }
                                }
                                stop();

                            }
                        } else {
                            System.out.println("Lista zwierząt jest pusta. Brak danych do wyświetlenia.");
                            stop();
                        }
                    } else {
                        System.out.println("Lista właścicieli jest pusta. Brak danych do wyświetlenia.");
                        stop();
                    }
                }
                /** Display list of Animals chosen Owner */
                case "5" -> {
                    if (!owners.isEmpty()) {
                        if (!animals.isEmpty()) {
                            int temp = ownerMethods.fillOwnerId();
                            if (temp <= owners.size()) {
                                System.out.println("Lista zwierząt Właściciela o ID: " + temp + "\n");
                                for (Animal animal : animals) {
                                    if (animal.showOwnerId() == temp) {
                                        animal.infoAnimal();
                                    }
                                }
                            } else {
                                System.out.println("Nieprawidłowe ID.");
                            }
                        } else {
                            System.out.println("Lista zwierząt jest pusta. Brak danych do wyświetlenia.");
                        }
                    } else {
                        System.out.println("Lista właścicieli jest pusta. Brak danych do wyświetlenia.");
                    }
                    stop();
                }
                /** Delete Owner */
                case "6" -> {
                    int counter = 0;
                    if (!owners.isEmpty()) {
                        System.out.println("Podaj ID właściciela, którego chcesz usunąć");
                        int temp = scanner.nextInt();
                        if (owners.size() >= temp) {
                            for (Animal animal : animals) {
                                if (animal.showOwnerId() == temp) {
                                    counter++;
                                }
                            }
                            if (counter == 0) {
                                System.out.println("========================================");
                                System.out.println("Usuwam właściciela o ID " + temp);
                                System.out.println("========================================");
                                owners.remove(temp - 1);
                                for (int tmp = temp - 1; tmp < owners.size(); tmp++) {
                                    owners.get(tmp).setOwnerId(temp + 1);
                                }
                            } else {
                                System.out.println("Nie możesz zostawić zwierząt bez właściciela!\nWłaściciel " + temp + " posiada " + counter + "zwierząt.\n" +
                                        "Usuń zwierzęta lub zmień im właściciela zanim usuniesz tego właściciela.\n");
                                System.out.println("+++++ Do tego właściciela należą zwierzęta o ID +++++");
                                for (Animal animal : animals) {
                                    if (animal.showOwnerId() == temp) {
                                        System.out.print("| " + animal.getAnimalId() + " ");
                                    }
                                }
                                stop();
                            }


                        } else {
                            System.out.println("Brak właściciela o podanym ID na liście.");
                            stop();
                        }


                    } else {
                        System.out.println("Lista właścicieli jest pusta.");
                        stop();
                    }
                }
                /** Go back to Main Menu */
                case "0" -> {
                    mainMenu();
                }
                /** Wrong choice, go back to menu */
                default -> {
                    System.out.println("Błędny wybór.");
                    stop();
                }

            }
        } while (true);
    }

    /** Menu to manage Animals
     * 1 - Adding new animal
     * 2 - List of all animals
     * 3 - List of animals belonging to owners with entered ID
     * 4 - Display info of animal with entered ID
     * 5 - Feeding turtles
     * 6 - Move animal
     * 7 - Display info about owner of animal with entered ID
     * 8 - Change owner of animal with entered ID
     * 9 - Delete animal with entered ID
     * 0 - Go back to main menu */
    public void animalsMenu() {
        System.out.println("test");
        String i = "1";
        while(true){
            if (i.equals("0")) {break;}
            System.out.flush();
            scanner.reset();
            System.out.println("+++++ MENU ZWIERZĄT +++++");
            System.out.println("Wybierz interesującą Cię opcje:\n" +
                    "1 - Dodaj nowego zwierzaka\n" +
                    "2 - Wyświetl listę zwierząt\n" +
                    "3 - Wyświetl listę zwierząt właściciela o wskazanym ID\n" +
                    "4 - Wyświetl zwierzę o wskazanym ID\n" +
                    "5 - Nakarm żółwia\n" +
                    "6 - Przesuń zwierzę\n" +
                    "7 - Wyświetl dane właściciela zwierzaka o wskazanym ID\n" +
                    "8 - Zmień właściciela zwierzaka o wskazanym ID\n" +
                    "9 - Usuń zwierzę\n" +
                    "0 - Wyjdź do menu głównego");
            // Zmień właściciela
            i = scanner.next();


            switch (i) {
                /** Adding new animal */
                case "1" -> {
                    Animal method = new AnimalMethods().animalFromKeyboard(owners, animals);
                    if (method != null) {
                        animals.add(method);
                        System.out.println("Dodano nowego zwierzaka.");
                    } else {
                        System.out.println("Dodawanie nie powiodło się.");
                    }
                    stop();
                }
                /** List of all animals */
                case "2" -> {
                    if (!animals.isEmpty()) {
                        System.out.println("--- Lista zwierząt ---");
                        for (Animal animal : animals) {
                            animal.infoAnimal();
                        }
                    }
                    else {
                        System.out.println("Lista zwierząt jest pusta.");
                    }
                    stop();
                }
                /** List of animals belonging to owners with entered ID */
                case "3" -> {
                    if (!owners.isEmpty()) {
                        if (!animals.isEmpty()) {
                            int temp = ownerMethods.fillOwnerId();
                            if (temp <= owners.size()) {
                                System.out.println("Lista zwierząt Właściciela o ID: " + temp + "\n");
                                for (Animal animal : animals) {
                                    if (animal.showOwnerId() == temp) {
                                        animal.infoAnimal();
                                    }
                                }
                            } else {
                                System.out.println("Nieprawidłowe ID.");
                            }
                        } else {
                            System.out.println("Lista zwierząt jest pusta. Brak danych do wyświetlenia.");
                        }
                    } else {
                        System.out.println("Lista właścicieli jest pusta. Brak danych do wyświetlenia.");
                    }
                    stop();
                }
                /** Display info of animal with entered ID */
                case "4" -> {
                    animalMethods.fillAnimalId();
                    int choosenID = scanner.nextInt();
                    if (animals.size() < choosenID) {
                        System.out.println("Nie ma takiego zwierzaka na liście.");
                    } else {
                        animals.get(choosenID - 1).infoAnimal();
                    }
                    stop();
                }
                /** Feeding turtles */
                case "5" -> {
                    if (animals.isEmpty()) {
                        System.out.println("Lista zwierząt jest pusta! Brak żółwi do karmienia :c");
                    } else {
                        System.out.println("Czy chcesz wyświetlić listę żółwi do nakarmienia?\n1 - TAK\n2 - NIE");
                        String intTemp = scanner.next();
                        int tempID = 0;
                        while (true) {
                            /** Showing list of INACTIVE Turtles */
                            if (intTemp.equals("1")) {
                                System.out.println("----- Lista nienakarmionych zółwi -----");
                                for (Animal animal : animals) {
                                    if (animal.getKind() == Animal.Kind.TURTLE) {
                                        if (animal.getState() == Animal.TurtleState.INACTIVE) {
                                            animal.infoAnimal();
                                        }
                                    }
                                }
                                intTemp = "2";

                            }
                            /** Changing State of Turtles from INACTIVE to ACTIVE */
                            if (intTemp.equals("2")) {
                                System.out.println("Podaj ID żółwia, którego chcesz nakarmić: ");
                                tempID = scanner.nextInt();
                                /** Checking if animal ID is correct */
                                if (tempID > animals.size()) {
                                    System.out.println("Błędne ID.");
                                }
                                /** ID correct */
                                else {
                                    /** Checking if animal is turtle */
                                    if (animals.get(tempID - 1).getKind() == Animal.Kind.TURTLE) {
                                        /** If turtle is starving, feed the turtle */
                                        if (animals.get(tempID - 1).getState() == Animal.TurtleState.INACTIVE) {
                                            animals.get(tempID - 1).setState(Animal.TurtleState.ACTIVE);
                                            System.out.println("Żółw " + this.animals.get(tempID - 1).getAnimalId() + " został nakarmiony");
                                        }
                                        /** That turtle is already feed */
                                        else {
                                            System.out.println("Ten żółw jest już nakarmiony.");
                                        }
                                    }
                                    /** Animal with that ID is not turtle */
                                    else {
                                        System.out.println("To ID nie należy do żółwia.");
                                    }
                                }
                                break;
                            }
                            /** Go back to menu after wrong choice */
                            else {
                                System.out.println("Niepoprawny wybór.");
                            }
                        }
                    }
                    stop();
                }
                /** Move animal */
                case "6" -> {
                    if (animals.isEmpty()) {
                        System.out.println("Brak zwierząt na liście.\n Wracam do menu.\n");
                    } else {
                        System.out.println("Wprowadź ID zwierzęcia, które ma się poruszyć: ");
                        int id = scanner.nextInt();
                        if (animals.size() < id) {
                            System.out.println("Na liście brakuje zwierzęcia o podanym ID.");
                        } else {
                            if (animals.get(id - 1).getKind() == Animal.Kind.TURTLE) {
                                if (animals.get(id - 1).getState() == Animal.TurtleState.INACTIVE) {
                                    System.out.println("Wybrałeś głodnego żółwia! Nakarm go zanim go przesuniesz.");
                                } else {
                                    animalMethods.move(id, animals, garden);
                                    animalMethods.moveInfo(id, animals);
                                    animals.get(id-1).setState(Animal.TurtleState.INACTIVE);
                                }
                            } else {
                                animalMethods.move(id, animals, garden);
                                animalMethods.moveInfo(id, animals);
                            }
                        }
                    }
                    stop();
                }
                /** Display info about owner of animal with entered ID */
                case "7" -> {
                    if (!owners.isEmpty()) {
                        if (!animals.isEmpty()) {
                            System.out.println("Podaj ID zwierzaka, którego właściciela chcesz wyświetlić");
                            int tempID = scanner.nextInt();
                            if (animals.size() >= tempID) {
                                owners.get((int) animals.get(tempID - 1).getOwner().getOwnerId()).infoOwner();
                            } else {
                                System.out.println("Brak zwierzaka o podanym ID na liście.");
                            }
                        } else {
                            System.out.println("Lista zwierząt jest pusta!");
                        }
                    } else {
                        System.out.println("Lista właścicieli jest pusta!");
                    }
                    stop();
                }
                /** Change owner of animal with entered ID */
                case "8" -> {
                    if (!owners.isEmpty()) {
                        if (owners.size() != 1) {
                            if (!animals.isEmpty()) {
                                System.out.println("Podaj ID zwierzaka, któremu zmieniasz właściciela:");
                                int temp1 = scanner.nextInt();
                                if (animals.size() >= temp1) {
                                    System.out.println("Wybrałeś: " + animals.get(temp1 - 1).showKindString() + " " + animals.get(temp1 - 1).getAnimalId() + "\n" +
                                            "Podaj ID nowego właściciela");
                                    int temp2 = scanner.nextInt();
                                    if (owners.size() >= temp2) {
                                        if ((int) animals.get(temp1 - 1).showOwnerId() != temp2) {
                                            System.out.println("Zwierzę zmienia właściciela!\n" +
                                                    "ID poprzedniego właściciela: " + animals.get(temp1 - 1).showOwnerId());
                                            animals.get(temp1 - 1).setOwner(owners.get(temp2 - 1));
                                            System.out.println("ID obecnego właściciela: " + animals.get(temp1 - 1).showOwnerId());
                                        } else {
                                            System.out.println("Nowe ID jest takie samo jak stare!");
                                        }
                                    } else {
                                        System.out.println("Podane ID właściciela jest nieprawidłowe.");
                                    }
                                } else {
                                    System.out.println("Nieprawidłowe ID, wracaj do menu.");
                                }
                            } else {
                                System.out.println("Lista zwierząt jest pusta! Nie ma komu zmieniać właściciela.");
                            }
                        } else {
                            System.out.println("Jest tylko jeden właściciel. Zmiana jest bezcelowa, więc odeślę Cię do menu. ;)");
                        }
                    } else {
                        System.out.println("Lista właścicieli jest pusta.");
                    }
                    stop();
                }
                /** Delete animal with entered ID */
                case "9" -> {
                    if (!animals.isEmpty()) {
                        System.out.println("Podaj id zwierzaka, którego chcesz usunąć");
                        int temp = scanner.nextInt();
                        if (animals.size() >= temp) {
                            System.out.println("Usuwam zwierzaka o ID: " + temp);
                            animals.remove(temp - 1);
                            for (int temp2 = temp - 1; temp2 < animals.size(); temp2++) {
                                animals.get(temp2).setAnimalId(temp2 + 1);
                            }
                        } else {
                            System.out.println("Brak zwierzaka o podanym ID na liście.");
                        }
                    } else {
                        System.out.println("Lista zwierząt jest pusta.");
                    }
                    stop();
                }
                /** Go back to main menu */
                case "0" -> {break;}
                /** Wrong choice, go back to menu */
                default -> {
                    System.out.println("Błędny wybór.");
                    stop();
                }

            }
        }
    }

    /** Program starter */
    public void start() {
        startupMenu();
        mainMenu();
    }
}
