import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Text {
    /**
     * @author: Raven Gardner
     * Created: April 3, 2022
     * Note: created the method to read room file
     *
     *  @author: Alan Oliver
     *  Note: added the conversion from string to int
     *  for the new lockedDoor variable
     *
     */
    public static ArrayList<Rooms> readRoomFile(ArrayList<Rooms> roomList) {
        String fileName = "Rooms.txt"; // hard coding file name
        Scanner scan;

        // try to read room file; catch if an error occurs
        try {
            File rooms = new File(fileName); // file object to be read
            scan = new Scanner(rooms); // scanner to read the file
        } catch (FileNotFoundException ex) {
            System.out.println("Unable to read rooms");
            return roomList;
        }
        // try to read the room file until it reaches the end; catch if an error occurs while reading room file
        try {
            /**
             * String roomName, String roomID, String description, String itemID, String monsterID, String puzzleID,
             *                  String north, String east, String west, String south, boolean visit
             */
            while (scan.hasNextLine()) { // loop until the scanner reaches the end of the file
                String roomName = scan.nextLine();


                String roomIDString = scan.nextLine();
                int roomID = Integer.parseInt(roomIDString);

                String description = scan.nextLine();

                String itemIDString = scan.nextLine();
                int itemID = Integer.parseInt(itemIDString);

                String monsterIDString = scan.nextLine();
                int monsterID = Integer.parseInt(monsterIDString);

                String puzzleIDString = scan.nextLine();
                int puzzleID = Integer.parseInt(puzzleIDString);

                String toNorthString = scan.nextLine();
                int toNorth = Integer.parseInt(toNorthString);

                String toEastString = scan.nextLine();
                int toEast = Integer.parseInt(toEastString);

                String toWestString = scan.nextLine();
                int toWest = Integer.parseInt(toWestString);

                String toSouthString = scan.nextLine();
                int toSouth = Integer.parseInt(toSouthString);

               // String doorLockedString = scan.nextLine();
                //int doorLocked = Integer.parseInt(doorLockedString);


                // Room object to store the variables read from the file
                Rooms currentRoom = new Rooms(roomName, roomID, description, itemID, monsterID, puzzleID,
                        toNorth, toEast, toWest, toSouth, /*doorLocked,*/ false);
                roomList.add(currentRoom); // add the room object to the array list
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Problem reading room file");
        } finally {
            scan.close(); // close the scanner once it is done reading file.
        }
        return roomList; // return the arraylist to be accessed later in the main method
    }


    public static ArrayList<Puzzle> readPuzzleFile(ArrayList<Puzzle> puzzleList) throws FileNotFoundException {

        File file = new File("Puzzle.txt");
        Scanner input = new Scanner(file);

        while (input.hasNextLine()) {
            String name = input.nextLine();
            int puzzleID = Integer.parseInt(name);

            String type = input.nextLine();
            String description = input.nextLine();
            String solution = input.nextLine();
            String hint = input.nextLine();

            Puzzle p = new Puzzle(puzzleID, type, description, solution, hint);
            puzzleList.add(p);
        }
        return puzzleList;

    }

    /**
     * @author: Raven Gardner
     * creates hashmap for everything in arifacts text file
     */
    public static HashMap<Puzzle, String> getPuzzleMap() {
        ArrayList<Puzzle> puzzlesInFile = new ArrayList<>();
        try {
            readPuzzleFile(puzzlesInFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        HashMap<Puzzle, String> mapContents = new HashMap<>();

        for (Puzzle puzzle : puzzlesInFile) {
            mapContents.put(puzzle, puzzle.getPuzzleType());
        }
        return mapContents;
    }

    /**
     * @author: Alan Oliver
     * Note: created the method to read the artifacts file
     */

    public static ArrayList<Artifacts> artiList(ArrayList<Artifacts> ArtiArrayList) {

        String fileName1 = "Artifacts.txt";
        Scanner scan = null;
        File artifacts = null;

        try {
            artifacts = new File(fileName1);
            scan = new Scanner(artifacts);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Can not read this file!");
            return ArtiArrayList;
        }
        try {
            while (scan.hasNextLine()) {
                int aID = Integer.parseInt(scan.nextLine());
                String aName = scan.nextLine();
                String aDescription = scan.nextLine();
                String aType = scan.nextLine();
                String aUsage = scan.nextLine();
                String consumeString = scan.nextLine();
                int consumeHealth = Integer.parseInt(consumeString);
                String equipString = scan.nextLine();
                int equipHealth = Integer.parseInt(equipString);

                Artifacts b = new Artifacts(aID,aName,aDescription,aType,aUsage,consumeHealth,equipHealth);
                ArtiArrayList.add(b);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("error while reading this file!");
        } finally {
            scan.close();
        }
        return ArtiArrayList;

    }

    /**
     * @author: Raven Gardner
     * creates hashmap for everything in arifacts text file
     */
    public static HashMap<Artifacts, String> getHashMapForArti() {
        ArrayList<Artifacts> itemsInFile = new ArrayList<>();
        artiList(itemsInFile);

        HashMap<Artifacts, String> mapContents = new HashMap<>();
        for (Artifacts item : itemsInFile) {
            mapContents.put(item , item.getArtiName());
        }
        return mapContents;
    }

//@Alan Oliver - creating method to read monster list
public static ArrayList<Monsters> monsReader(ArrayList<Monsters> monsArrayList) {

    String fileName1 = "Monsters.txt";
    Scanner scan = null;
    File monster = null;

    try {
        monster = new File(fileName1);
        scan = new Scanner(monster);
    } catch (FileNotFoundException e) {
        System.out.println("Can not read this file!");
        return monsArrayList;
    }

    try {
        while (scan.hasNextLine()) {
            int aID = Integer.parseInt(scan.nextLine());
            String aDescription = scan.nextLine();
            String itemsHeld = scan.nextLine();
            String aDifficulty = scan.nextLine();
            String aRiddle = scan.nextLine();
            String aHint = scan.nextLine();
            String aAnswer = scan.nextLine();
            String choices = scan.nextLine();
            int monsHealth = Integer.parseInt(scan.nextLine());
            int monsAttack = Integer.parseInt(scan.nextLine());

            Monsters m = new Monsters(aID, aDescription,itemsHeld, aDifficulty,aRiddle,aHint,
                    aAnswer,choices, monsHealth,monsAttack);
            monsArrayList.add(m);
        }
    }
    catch (Exception e) {
        e.printStackTrace();
        System.out.println("error while reading this file!");
    } finally {
        scan.close();
    }
    return monsArrayList;

}

//@Joe F Nsengiyumva - Method that reads controls for the game
    public static String showControls(String help)  {
        File file =  new File("Controls.txt");
        Scanner input;
        try {
            input = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to read file");
            return help;
        }

        while (input.hasNext()){
            help = input.nextLine();
            System.out.println(help);
        }
        return help;
    }


}
