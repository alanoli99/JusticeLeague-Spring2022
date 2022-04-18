import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Class: Player
 *
 * @author: Raven Gardner
 * created attributes, getter/setter, and hashmap info
 * Methods: consume
 *
 *
 */

public class Player extends Rooms {
    protected static Rooms location;
    protected static int playerHealth = 50;
    private static HashMap<Artifacts, String> equippedItems = Text.getHashMapForArti(); // will change later to new HashMap<>() -- Raven
    private static HashMap<Artifacts, String> inventoryMap = Text.getHashMapForArti(); // will change later to new HashMap<>() -- Raven
    private static HashMap<Artifacts, Integer> playerInfo = new HashMap<>(); // key = Artifacts info, value = health -- Raven
    public static ArrayList<Artifacts> inventory = new ArrayList<>();

    public static ArrayList<Artifacts> getInventory() {
        return inventory;
    }

    public static void setInventory(ArrayList<Artifacts> inventory) {
        Player.inventory = inventory;
    }

    public Player(String roomName, int roomID, String description, int itemID,
                  int monsterID, int puzzleID, int north, int east, int west, int south, boolean visit, Rooms location) {
        super(roomName, roomID, description, itemID, monsterID, puzzleID, north, east, west, south, visit);
        this.location = location;
    }

    public static Rooms getLocation() {
        return location;
    }

    public static void setLocation(Rooms location) {
        Player.location = location;
    }

    public static int getPlayerHealth() {
        return playerHealth;
    }

    public static void setPlayerHealth(int health) {
        Player.playerHealth += health;
    }

    public static HashMap<Artifacts, String> getPlayerInventoryMap() {
        return inventoryMap;
    }

    public static HashMap<Artifacts, String> getEquippedItems() {
        return equippedItems;
    }

    public static void setEquippedItems(HashMap<Artifacts, String> equippedItems) {
        Player.equippedItems = equippedItems;
    }

    public static HashMap<Artifacts, Integer> getPlayerInfo() {
        return playerInfo;
    }

    public static void setPlayerInfo(HashMap<Artifacts, Integer> playerInfo) {
        Player.playerInfo = playerInfo;
    }


    /**
     * @author: Raven Gardner
     * ID: IF7; players can consume certain items
     * note to team: will update once the rest of the item features are done
     */
    public static String consume(String obname) {
        String beenEquipped = "";
        Artifacts inInventory;
        int healthPoints;

        obname = obname.trim().replaceAll("\\s{2,}", " "); // gets rid of the space in the beginning of the item name -- Raven

        for (Map.Entry<Artifacts, String> collected : getPlayerInventoryMap().entrySet()) {

            inInventory = collected.getKey();

            //System.out.println("entered for loop");
            //System.out.println("in inventory:" + inInventory.getArtiName());
            //System.out.println("obj name:"+obname);
            if (inInventory.getArtiName().equalsIgnoreCase(obname)) {
                if (inInventory.getConsumeHealth() == 0) {
                    beenEquipped = "\n" + obname + " cannot be consumed!";
                } else if(getPlayerHealth() >= 50 && inInventory.getConsumeHealth() >= 0){
                    beenEquipped = "\n" + "Your health is full this cannot be consumed";
                } else {
                    // right here get the attack damage from the item hashmap after I fix the item file, item class, and room class with hashmap
                    healthPoints = inInventory.getConsumeHealth();
                    setPlayerHealth(healthPoints);
                    playerInfo.clear();
                    beenEquipped = "\n" + obname + " has been consumed! " + obname + " has changed your health by " + healthPoints + " points!\n" +
                            "You now have " + getPlayerHealth() + " health points.";
                    //will drop item out of inventory into room
                }
                playerInfo.put(inInventory, getPlayerHealth());
                break;
            }
        }
        if (beenEquipped.isEmpty()) {
            beenEquipped = "\nThere is no " + obname + " in inventory \n";
        }
        return beenEquipped;
    }

//    public static void addToInventory() {
//        ArrayList<Artifacts> artifactsList = new ArrayList<>();
//        Text.artiList(artifactsList);
//        Artifacts artifacts;
//        artifacts = Artifacts.getItemObject(Game.getRooms().getItemID(), artifactsList,"jerry");
//        if (artifacts != null){
//            inventory.add(artifacts);
//        }
//        else{
//            System.out.println("There's no item to add");
//        }
//
//    }// used to test check inventory -Joe N
    @Override
    public String toString() {
        return "\nPlayer info\n\n" +
                "Current Room: " + location.getRoomName() +
                "\nYour Health: " + playerHealth + "\n Inventory: " + inventory;
    }
}
