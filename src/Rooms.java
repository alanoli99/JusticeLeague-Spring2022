import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Rooms {
    /**
     * @author: Raven Gardner
     * Created: April 3, 2022
     * Note: I created the attributes, constructor, and basic getter/setter methods
     * @author: Alan Oliver
     * Note: I modified the original room file and created
     * the variable "lockedDoor" to implement if a door
     * needs a key
     */
    private String roomName;
    private int roomID;
    private String description;
    private int itemID;
    private int monsterID;
    private int puzzleID;
    private int north;
    private int east;
    private int west;
    private int south;
    //private int lockedDoor;
    private boolean visit;
    private static int initialRoom = 1;

    public Rooms(String roomName, int roomID, String description, int itemID, int monsterID,
                 int puzzleID, int north, int east, int west, int south, /*int lockedDoor, */ boolean visit) {
        this.roomName = roomName;
        this.roomID = roomID;
        this.description = description;
        this.itemID = itemID;
        this.monsterID = monsterID;
        this.puzzleID = puzzleID;
        this.north = north;
        this.east = east;
        this.west = west;
        this.south = south;
        //this.lockedDoor = lockedDoor;
        this.visit = visit;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public int getMonsterID() {
        return monsterID;
    }

    public void setMonsterID(int monsterID) {
        this.monsterID = monsterID;
    }

    public int getPuzzleID() {
        return puzzleID;
    }

    public void setPuzzleID(int puzzleID) {
        this.puzzleID = puzzleID;
    }

    public int getNorth() {
        return north;
    }

    public void setNorth(int north) {
        this.north = north;
    }

    public int getEast() {
        return east;
    }

    public void setEast(int east) {
        this.east = east;
    }

    public int getWest() {
        return west;
    }

    public void setWest(int west) {
        this.west = west;
    }

    public int getSouth() {
        return south;
    }

    public void setSouth(int south) {
        this.south = south;
    }

/*
    public int getLockedDoor() {
        return lockedDoor;
    }

 */
/*
    public void setLockedDoor(int lockedDoor) {
        this.lockedDoor = lockedDoor;
    }
 */
    public boolean isVisit() {
        return visit;
    }

    public void setVisit(boolean visit) {
        this.visit = visit;
    }


    /**
     * @author: Raven Gardner
     * Method Name: exploreRoom
     * purpose: to receive the rooom name and room description
     */
    public String exploreRoom() {
        ArrayList<Rooms> roomInfo = new ArrayList<>();
        Text.readRoomFile(roomInfo);
        String s = "";

        //System.out.println("current room id: " + getRoomID());
        for (Rooms rooms : roomInfo) {
            // will change to compare room id in file to current room of the player
            if (getRoomID() == rooms.getRoomID()) {
                s = "\n" + rooms.getRoomName() + "\n" + rooms.getDescription() + "\n\n";
            }
        }
        if (s.isEmpty()) {
            s = "No room to explore";
        }
        return s;
    }

    /**
     * @author: Joe Nsengiyumva
     * Method Name: moveAround
     * purpose: to navigate between rooms
     * <p>
     * * @author: Alan Oliver
     * * What was added: if statement to check
     * if the doors are locked
     * * purpose: if the door is locked
     * the player needs a key
     * to open the door
     *
     * @author: Raven Gardner
     * fixed locked door feature; no longer stuck in room 35
     */
    public void moveAround(String verb) {
        ArrayList<Rooms> roomList = new ArrayList<>();
        Text.readRoomFile(roomList);
        Rooms room;
        room = getRoomObject(initialRoom, roomList);

        if (verb.equalsIgnoreCase("n") || verb.equalsIgnoreCase("north")) {
            if (room.getNorth() > 0) {
                //Alan
                initialRoom = room.getNorth();
                //System.out.println(initialRoom);
                room = getRoomObject(initialRoom, roomList);
                System.out.println("You're in the Room " + room.getRoomID());
                setRoomID(room.getRoomID()); // added for explore room; Raven

                Game.setRooms(room); // added to keep track of rooms in Game class; for item features; Raven
                Player.setLocation(room); // same ^^ also updates player info; Raven

            }


            else if (room.getNorth() == -2) { // instead of locked door variable if navigation = -2; the door is locked; -2 also represents the locked door roomID.
                initialRoom = room.getRoomID();
                room = getRoomObject(initialRoom, roomList);
                System.out.println("You need a key!");
                System.out.println("You're in the Room " + room.getRoomID());
            } else {
                System.out.println("There’s no exit in this direction");
            }
            return;
        }

        if (verb.equalsIgnoreCase("s") || verb.equalsIgnoreCase("south")) {
            if (room.getSouth() > 0) {
                //Alan
                initialRoom = room.getSouth();
                //System.out.println(initialRoom);
                room = getRoomObject(initialRoom, roomList);
                System.out.println("You're in the Room " + room.getRoomID());
                setRoomID(room.getRoomID());

                Game.setRooms(room);
                Player.setLocation(room); // same ^^ also updates player info; Raven

            } else if (room.getSouth() == -2) {
                initialRoom = room.getRoomID();
                room = getRoomObject(initialRoom, roomList);
                System.out.println("You need a key!");
                System.out.println("You're in the Room " + room.getRoomID());
            } else {
                System.out.println("There’s no exit in this direction");
            }
            return;
        }

        if (verb.equalsIgnoreCase("e") || verb.equalsIgnoreCase("east")) {
            if (room.getEast() > 0) {
                initialRoom = room.getEast();
                //System.out.println(initialRoom);
                room = getRoomObject(initialRoom, roomList);
                System.out.println("You're in the Room " + room.getRoomID());
                setRoomID(room.getRoomID());

                Game.setRooms(room);
                Player.setLocation(room); // same ^^ also updates player info; Raven

            } else if (room.getEast() == -2) {
                initialRoom = room.getRoomID();
                room = getRoomObject(initialRoom, roomList);
                System.out.println("You need a key!");
                System.out.println("You're in the Room " + room.getRoomID());
            } else {
                System.out.println("There’s no exit in this direction");
            }
            return;
        }

        if (verb.equalsIgnoreCase("w") || verb.equalsIgnoreCase("west")) {
            if (room.getWest() > 0) {

                initialRoom = room.getWest();
                //System.out.println(initialRoom);
                room = getRoomObject(initialRoom, roomList);
                System.out.println("You're in the Room " + room.getRoomID());
                setRoomID(room.getRoomID());

                Game.setRooms(room);
                Player.setLocation(room); // same ^^ also updates player info; Raven

            } else if (room.getWest() == -2) {
                initialRoom = room.getRoomID();
                room = getRoomObject(initialRoom, roomList);
                System.out.println("You need a key!");
                System.out.println("You're in the Room " + room.getRoomID());
            } else {
                System.out.println("There’s no exit in this direction");
            }
            return;
        }
        System.out.println("There’s no exit in this direction");
    }

    /**
     * @author: Joe Nsengiyumva
     */
    private Rooms getRoomObject(int currentRoom, ArrayList<Rooms> roomList) {
    //current room of the player
        for (Rooms a : roomList) {
            if (a.getRoomID() == currentRoom) {
                return a;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "Rooms{" +
                "roomName='" + roomName + '\'' +
                ", roomID='" + roomID + '\'' +
                ", description='" + description + '\'' +
                ", itemID='" + itemID + '\'' +
                ", monsterID='" + monsterID + '\'' +
                ", puzzleID='" + puzzleID + '\'' +
                ", north='" + north + '\'' +
                ", east='" + east + '\'' +
                ", west='" + west + '\'' +
                ", south='" + south + '\'' +
                '}';
    }
}
