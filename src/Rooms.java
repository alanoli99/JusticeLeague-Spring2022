import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Rooms {
    /**
     * @author: Raven Gardner
     * Created: April 3, 2022
     * Note: I created the attributes, constructor, and basic getter/setter methods
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
    private boolean visit;
    private static int initialRoom = 1;

    public Rooms(String roomName, int roomID, String description, int itemID, int monsterID, int puzzleID, int north, int east, int west, int south, boolean visit) {
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
        for(Rooms rooms : roomInfo) {
            // will change to compare room id in file to current room of the player
            if(getRoomID() == rooms.getRoomID()) {
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
     */
    public void moveAround(String verb) {
        ArrayList<Rooms> roomList = new ArrayList<>();
        Text.readRoomFile(roomList);
        Rooms room;
        room = getRoomObject(initialRoom, roomList);

        if (verb.equalsIgnoreCase("n") || verb.equalsIgnoreCase("north")) {
            if (room.getNorth() > 0) {
                initialRoom = room.getNorth();
                //System.out.println(initialRoom);
                room = getRoomObject(initialRoom, roomList);
                System.out.println("You're in the Room " + room.getRoomID());
                setRoomID(room.getRoomID()); // added for explore room; Raven

            } else {
                System.out.println("There’s no exit in this direction");
            }
            return;
        }

        if (verb.equalsIgnoreCase("s") || verb.equalsIgnoreCase("south")) {
            if (room.getSouth() > 0) {
                initialRoom = room.getSouth();
                //System.out.println(initialRoom);
                room = getRoomObject(initialRoom, roomList);
                System.out.println("You're in the Room " + room.getRoomID());
                setRoomID(room.getRoomID());

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
