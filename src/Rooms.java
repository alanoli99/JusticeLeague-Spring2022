import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Rooms {
    /**
     * @author: Raven Gardner
     * Note: I created the attributes, constructor, basic getter/setter methods,
     * fixed bug for locked door feature, implemented explore room feature,
     * and unlock door if key is equipped.
     *
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
    private static int currentRoom = 1;
    private static HashMap<Integer, Boolean> beenHere1 = new HashMap<>();
    private  static int count = 0;

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
     * purpose: to receive the room name and room description and output both to the player
     * also prints if monster is nearby, puzzle is in room, and what items are in the room
     */
    public String exploreRoom() {

        ArrayList<Rooms> roomInfo = new ArrayList<>();
        Text.readRoomFile(roomInfo);
        ArrayList<Artifacts> itemInfo = new ArrayList<>();
        Text.artiList(itemInfo);

        String s = "";
        String m = "";
        String i = "";
        String p = "";

        //System.out.println("current room id: " + getRoomID());
        for (Rooms rooms : roomInfo) {
            // will change to compare room id in file to current room of the player
            if (getRoomID() == rooms.getRoomID()) {
                s = "\n" + rooms.getRoomName() + "\n" + rooms.getDescription() + "\n\n";
                if(rooms.getMonsterID() > 0){
                    m = "\nThere's a monster nearby...";
                }
                if(rooms.getItemID() > 0 || !Player.getItemsDropped().isEmpty()){
                    for(Map.Entry<Artifacts, String> inDrop : Player.getItemsDropped().entrySet()) {
                        System.out.println(inDrop.getKey().getArtiID() == rooms.getRoomID());
                        if (inDrop.getKey().getArtiID() == rooms.getRoomID()) {
                            i = inDrop.getValue();
                            break;
                        }
                    }
                    for(Artifacts item : itemInfo){
                            //System.out.println(Player.getItemsDropped());
                            // ADDED FOR DROP ITEM/EXPLORE ROOM FEATURE -> || Player.getItemsDropped().containsValue(item.getArtiName())
                            if (rooms.getItemID() == item.getArtiID() || Player.getItemsDropped().containsValue(item.getArtiName())) {
                                // Player.getPlayerInventoryMap().containsValue(item.getArtiName().toLowerCase()) -- may need if multi items in room
                                i = "Artifacts here! " + i + "\n" + item.getArtiName();
                            }

                    }
                } if(rooms.getPuzzleID() > 0){
                    for(Map.Entry<Puzzle,String> puzzle : Puzzle.getPuzzleInfo().entrySet()){
                        if(rooms.getPuzzleID() == puzzle.getKey().getPuzzleID()){
                            p = "\nThere is a puzzle here!";
                            break;
                        }
                    }
                }

                if (!(m.isEmpty()) || !(i.isEmpty()) || !(p.isEmpty())){
                    s = s + i  + p + m;
                }
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
     * fixed locked door feature; no longer stuck in room 35,
     * implemented unlocking door if key is equipped
     */
    public void moveAround(String verb) {
        ArrayList<Rooms> roomList = new ArrayList<>();
        Text.readRoomFile(roomList);
        Rooms room;
        room = getRoomObject(currentRoom, roomList);
        if (count == 0){
            for (int i = 1; i <= roomList.size(); i++){ // changed to initialize as 1 to include room 36 -- Raven
                beenHere1.put(i,false);
            }
        }// used to populate hashMap for tracking rooms -Joe N
        beenHere1.replace(1,true);// sets initial room you spawn in as already visited - Joe N
        if (verb.equalsIgnoreCase("n") || verb.equalsIgnoreCase("north")) {
            if (room.getNorth() > 0) {
                //Alan
                currentRoom = room.getNorth();
                room = getRoomObject(currentRoom, roomList);
                if (beenHere1.get(currentRoom)){
                    System.out.println("You have been here before!");
                }
                System.out.println("You're in Room " + room.getRoomID());
                setRoomID(room.getRoomID()); // added for explore room; Raven
                Game.setRooms(room); // added to keep track of rooms in Game class; for item features; Raven
                Player.setLocation(room); // same ^^ also updates player info; Raven
                beenHere1.replace(currentRoom,true);
                count++;
            }

            else if (room.getNorth() == -2) { // instead of locked door variable if navigation = -2; the door is locked; -2 also represents the locked door roomID. -- Raven

                // unlocks door if diamond key is in use -- Raven
                if(Player.getEquippedItems().containsValue("Diamond key")){
                    currentRoom = room.getNorth();
                    room = getRoomObject(currentRoom, roomList);
                    if (beenHere1.get(36)){ // current roomID is -2 so change it to 36 for room 36 -- Raven
                        System.out.println("You have been here before!");
                    }
                    System.out.println("You're in Room 36");
                    setRoomID(room.getRoomID()); // added for explore room; Raven
                    Game.setRooms(room); // added to keep track of rooms in Game class; for item features; Raven
                    Player.setLocation(room); // same ^^ also updates player info; Raven
                    beenHere1.replace(36,true);// change to 36 so room 36 is true in hashmap
                    count++;
                } else {
                    currentRoom = room.getRoomID();
                    room = getRoomObject(currentRoom, roomList);
                    System.out.println("You need a key!");
                    System.out.println("You're in Room " + room.getRoomID());
                }

            } else {
                System.out.println("There’s no exit in this direction");
            }
            return;
        }

        if (verb.equalsIgnoreCase("s") || verb.equalsIgnoreCase("south")) {
            if (room.getSouth() > 0) {
                //Alan
                currentRoom = room.getSouth();
                room = getRoomObject(currentRoom, roomList);
                if (beenHere1.get(currentRoom)){
                    System.out.println("You have been here before!");
                }
                System.out.println("You're in Room " + room.getRoomID());
                setRoomID(room.getRoomID());
                Game.setRooms(room); // added to keep track of rooms in Game class; for item features; Raven
                Player.setLocation(room); // same ^^ also updates player info; Raven
                beenHere1.replace(currentRoom,true);
                count++;
            } else if (room.getSouth() == -2) {

                if(Player.getEquippedItems().containsValue("Diamond key")){
                    currentRoom = room.getSouth();
                    room = getRoomObject(currentRoom, roomList);
                    if (beenHere1.get(36)){
                        System.out.println("You have been here before!");
                    }

                    System.out.println("You're in Room 36");
                    setRoomID(room.getRoomID()); // added for explore room; Raven
                    Game.setRooms(room); // added to keep track of rooms in Game class; for item features; Raven
                    Player.setLocation(room); // same ^^ also updates player info; Raven
                    beenHere1.replace(36,true);// change to 36 so room 36 is true in hashmap
                    count++;
                } else {
                    currentRoom = room.getRoomID();
                    room = getRoomObject(currentRoom, roomList);
                    System.out.println("You need a key!");
                    System.out.println("You're in Room " + room.getRoomID());
                }

            } else {
                System.out.println("There’s no exit in this direction");
            }
            return;
        }

        if (verb.equalsIgnoreCase("e") || verb.equalsIgnoreCase("east")) {
            if (room.getEast() > 0) {
                currentRoom = room.getEast();
                room = getRoomObject(currentRoom, roomList);
                if (beenHere1.get(currentRoom)){
                    System.out.println("You have been here before!");
                }
                System.out.println("You're in Room " + room.getRoomID());
                setRoomID(room.getRoomID());
                Game.setRooms(room); // added to keep track of rooms in Game class; for item features; Raven
                Player.setLocation(room); // same ^^ also updates player info; Raven
                beenHere1.replace(currentRoom,true);
                count++;
            } else if (room.getEast() == -2) {

                if(Player.getEquippedItems().containsValue("Diamond key")){
                    currentRoom = room.getEast();
                    room = getRoomObject(currentRoom, roomList);
                    if (beenHere1.get(36)){
                        System.out.println("You have been here before!");
                    }
                    System.out.println("You're in Room 36");
                    setRoomID(room.getRoomID()); // added for explore room; Raven
                    Game.setRooms(room); // added to keep track of rooms in Game class; for item features; Raven
                    Player.setLocation(room); // same ^^ also updates player info; Raven
                    beenHere1.replace(36,true);// change to 36 so room 36 is true in hashmap
                    count++;
                } else {
                    currentRoom = room.getRoomID();
                    room = getRoomObject(currentRoom, roomList);
                    System.out.println("You need a key!");
                    System.out.println("You're in Room " + room.getRoomID());
                }

            } else {
                System.out.println("There’s no exit in this direction");
            }
            return;
        }

        if (verb.equalsIgnoreCase("w") || verb.equalsIgnoreCase("west")) {
            if (room.getWest() > 0) {
                currentRoom = room.getWest();
                room = getRoomObject(currentRoom, roomList);
                if (beenHere1.get(currentRoom)){
                    System.out.println("You have been here before!");
                }
                System.out.println("You're in Room " + room.getRoomID());
                setRoomID(room.getRoomID());
                Game.setRooms(room); // added to keep track of rooms in Game class; for item features; Raven
                Player.setLocation(room); // same ^^ also updates player info; Raven
                beenHere1.replace(currentRoom,true);
                count++;
            } else if (room.getWest() == -2) {

                if(Player.getEquippedItems().containsValue("Diamond key")){
                    currentRoom = room.getWest();
                    room = getRoomObject(currentRoom, roomList);
                    if (beenHere1.get(36)){
                        System.out.println("You have been here before!");
                    }
                    System.out.println("You're in Room 36");
                    setRoomID(room.getRoomID()); // added for explore room; Raven
                    Game.setRooms(room); // added to keep track of rooms in Game class; for item features; Raven
                    Player.setLocation(room); // same ^^ also updates player info; Raven
                    beenHere1.replace(36,true);// change to 36 so room 36 is true in hashmap
                    count++;
                } else {
                    currentRoom = room.getRoomID();
                    room = getRoomObject(currentRoom, roomList);
                    System.out.println("You need a key!");
                    System.out.println("You're in the Room " + room.getRoomID());
                }

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
                '}' ;
    }
}
