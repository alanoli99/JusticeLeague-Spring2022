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
    private String roomID;
    private String description;
    private String itemID;
    private String monsterID;
    private String puzzleID;
    private String north;
    private String east;
    private String west;
    private String south;
    private boolean visit;


    public Rooms(String roomName, String roomID, String description, String itemID, String monsterID, String puzzleID,
                 String north, String east, String west, String south, boolean visit) {
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

    public String getRoomID() {
        return roomID;
    }

    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public String getMonsterID() {
        return monsterID;
    }

    public void setMonsterID(String monsterID) {
        this.monsterID = monsterID;
    }

    public String getPuzzleID() {
        return puzzleID;
    }

    public void setPuzzleID(String puzzleID) {
        this.puzzleID = puzzleID;
    }

    public String getNorth() {
        return north;
    }

    public void setNorth(String north) {
        this.north = north;
    }

    public String getEast() {
        return east;
    }

    public void setEast(String east) {
        this.east = east;
    }

    public String getWest() {
        return west;
    }

    public void setWest(String west) {
        this.west = west;
    }

    public String getSouth() {
        return south;
    }

    public void setSouth(String south) {
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
     * created: April 3, 2022
     * Method Name: exploreRoom
     * purpose: to receive the rooom name and room description
     */
    public void exploreRoom() {
        ArrayList<Rooms> roomInfo = new ArrayList<>();
        Text.readRoomFile(roomInfo);

        for(Rooms rooms : roomInfo) {
            // will change to compare room id in file to current room of the player
            System.out.println(rooms.getRoomName() + "\n" + rooms.getDescription() + "\n");
        }
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
