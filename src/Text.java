import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Text {
    /**
     * @author: Raven Gardner
     * Created: April 3, 2022
     * Note: created the method to read room file
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
                String roomID = scan.nextLine();
                String description = scan.nextLine();
                String itemID = scan.nextLine();
                String monsterID = scan.nextLine();
                String puzzleID = scan.nextLine();
                String toNorthString = scan.nextLine();
                String toEastString = scan.nextLine();
                String toWestString = scan.nextLine();
                String toSouthString = scan.nextLine();

                // Room object to store the variables read from the file
                Rooms currentRoom = new Rooms(roomName, roomID, description, itemID, monsterID, puzzleID,
                        toNorthString, toEastString, toWestString, toSouthString,false);
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
}
