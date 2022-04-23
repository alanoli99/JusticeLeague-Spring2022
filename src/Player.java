import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Class: Player
 *
 * @author: Raven Gardner
 * created attributes, getter/setter, and hashmap info
 * Methods: consume, equip, and unequip
 *
 *
 */

public class Player extends Rooms {
    protected static Rooms location;
    protected static int playerHealth;
    protected static int playerAttkDamage;
    private static HashMap<Artifacts, String> equippedItems = new HashMap<>();
    private static HashMap<Artifacts, String> inventoryMap = new HashMap<>();
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

    public static int setPlayerHealth(int health) {
        Player.playerHealth += health;
        return health;
    }

    public static int getPlayerAttkDamage() {
        return playerAttkDamage;
    }

    public static void setPlayerAttkDamage(int playerAttkDamage) {
        Player.playerAttkDamage = playerAttkDamage;
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
     */
    public static String consume(String obname) {
        String beenConsumed = "";
        Artifacts inInventory;
        int healthPoints;

        //for (Artifacts artifacts : getInventory()) {
        //getPlayerInventoryMap().put(artifacts, artifacts.getArtiName());
        //}

        obname = obname.trim().replaceAll("\\s{2,}", " "); // gets rid of the space in the beginning of the item name -- Raven

        for (Map.Entry<Artifacts, String> collected : getPlayerInventoryMap().entrySet()) {

            inInventory = collected.getKey();
            //System.out.println("in inventory: " + inInventory);

            //System.out.println("entered for loop");
            //System.out.println("in inventory:" + inInventory.getArtiName());
            //System.out.println("obj name:"+obname);
            if (inInventory.getArtiName().equalsIgnoreCase(obname)) {
                if (inInventory.getConsumeHealth() == 0) {
                    beenConsumed = "\n" + obname + " cannot be consumed!";
                } else {
                    healthPoints = inInventory.getConsumeHealth();
                    setPlayerHealth(healthPoints);
                    playerInfo.clear();
                    beenConsumed = "\n" + obname + " has been consumed! " + obname + " has changed your health by " + healthPoints + " points!\n" +
                            "You now have " + getPlayerHealth() + " health points.";
                    //will drop item out of inventory
                    //System.out.println("inventory before: " + getInventory());
                    getInventory().remove(collected.getKey());
                    getPlayerInventoryMap().remove(inInventory);
                    //System.out.println("inventory after: " + getInventory());
                }
                playerInfo.put(inInventory, getPlayerHealth());
                break;
            }
        }
        if (beenConsumed.isEmpty()) {
            beenConsumed = "\nThere is/are no " + obname + " in inventory \n";
        }
        return beenConsumed;
    }

    /**
     * @author: Raven Gardner
     * players can equip items
     */
    public static String equip(String obname) {
        String beenEquipped = "";
        Artifacts inInventory;
        int healthPoints;

        //for (Artifacts artifacts : getInventory()) {
        //getPlayerInventoryMap().put(artifacts, artifacts.getArtiName());
        //}

        obname = obname.trim().replaceAll("\\s{2,}", " "); // gets rid of the space in the beginning of the item name -- Raven

        for (Map.Entry<Artifacts, String> collected : getPlayerInventoryMap().entrySet()) {

            inInventory = collected.getKey();
            //System.out.println("in inventory: " + inInventory);

            //System.out.println("in inventory:" + inInventory.getArtiName());
            // System.out.println("obj name:"+obname);
            if (inInventory.getArtiName().equalsIgnoreCase(obname)) {
                if (inInventory.getEquipHealth() == 0) {
                    beenEquipped = "\n" + obname + " cannot be equipped!";
                } else {
                    healthPoints = inInventory.getEquipHealth();
                    setPlayerHealth(healthPoints);
                    playerInfo.clear();
                    beenEquipped = "\n" + obname + " has been equipped! " + obname + " has changed your health by " + healthPoints + " points!\n" +
                            "You now have " + getPlayerHealth() + " health points.";
                    //will drop item out of inventory into room
                    //System.out.println("inventory before: " + getInventory());
                    getInventory().remove(collected.getKey());
                    getPlayerInventoryMap().remove(inInventory);
                    equippedItems.put(inInventory,inInventory.getArtiName());

                    //System.out.println("inventory after: " + getInventory());
                }
                playerInfo.put(inInventory, getPlayerHealth());
                break;
            }
        }
        if (beenEquipped.isEmpty()) {
            beenEquipped = "\nThere is/are no " + obname + " in inventory \n";
        }
        return beenEquipped;
    }

    /**
     * @author: Raven Gardner
     * players can unequip items
     */
    public static String unequip(String obname) {
        String beenEquipped = "";
        Artifacts inEquipMap;
        int healthPoints;

        //for (Artifacts artifacts : getInventory()) {
        //getPlayerInventoryMap().put(artifacts, artifacts.getArtiName());
        //}

        obname = obname.trim().replaceAll("\\s{2,}", " "); // gets rid of the space in the beginning of the item name -- Raven

        for (Map.Entry<Artifacts, String> equipped : getEquippedItems().entrySet()) {

            inEquipMap = equipped.getKey();
            //System.out.println("in inventory: " + inInventory);

            //System.out.println("in inventory:" + inInventory.getArtiName());
            // System.out.println("obj name:"+obname);
            if (inEquipMap.getArtiName().equalsIgnoreCase(obname)) {
                healthPoints = inEquipMap.getEquipHealth();
                setPlayerHealth(-healthPoints);
                playerInfo.clear();
                beenEquipped = "\n" + obname + " has been unequipped!" +
                        "\nYou now have " + getPlayerHealth() + " health points.";

                getEquippedItems().remove(equipped.getKey());
                //System.out.println("in equip map: " + getEquippedItems());

                playerInfo.put(inEquipMap, getPlayerHealth());
                break;
            }
        }
        if (beenEquipped.isEmpty()) {
            beenEquipped = "\n" + obname + " has not been equipped yet\n";
        }
        return beenEquipped;
    }

    /**
     * @author: Alan Oliver
     * ID: IF7; drop item
     */
    public static String removeFromInventory(String obname) {

        Artifacts inInventory;

        obname = obname.trim().replaceAll("\\s{2,}", " "); // gets rid of the space in the beginning of the item name -- Raven

        for (Map.Entry<Artifacts, String> collected : getPlayerInventoryMap().entrySet()) {

            inInventory = collected.getKey();

            if (inInventory.getArtiName().equalsIgnoreCase(obname)) {

                if(getPlayerInventoryMap().size() == 0){
                    System.out.println("No items to drop!");
                    break;
                }

                    getInventory().remove(collected.getKey());
                    getPlayerInventoryMap().remove(inInventory);
                    System.out.println(obname + " has been dropped!");
                }

                playerInfo.put(inInventory, getPlayerHealth());
                break;
            }
        return obname;
    }



        public static void addToInventory(String noun) {
        ArrayList<Artifacts> artifactsList = new ArrayList<>();
        Text.artiList(artifactsList);
        Artifacts artifacts;
        noun = noun.trim().replaceAll("\\s{2,}", " ");
        artifacts = Artifacts.getItemObject(Game.getRooms().getItemID(), artifactsList);
        if (artifacts != null && artifacts.getArtiName().equalsIgnoreCase(noun)){
            if (inventory.size() >= 5){
                System.out.println("Inventory is full! drop item.");
                return;
            }
            inventory.add(artifacts);
            System.out.println(noun + " has been added to inventory");
            //System.out.println(inventory.size());
            getPlayerInventoryMap().put(artifacts,noun); //Raven -- created to update map for inventory
        }
        else if(artifacts != null && !artifacts.getArtiName().equalsIgnoreCase(noun) ){
            if (inventory.size() > 5){
                System.out.println("Inventory is full! drop item.");
                return;
            }
            System.out.println(noun + " is not in this room");
        }
        else{
            System.out.println("There's no item in this room");
        }

    }// used to test check inventory -Joe N

    //@Alan
//    public static void removeFromInventory(String noun) {
//        ArrayList<Artifacts> artifactsList = new ArrayList<>();
//        Text.artiList(artifactsList);
//        Artifacts artifacts;
//
//        noun = noun.trim().replaceAll("\\s{2,}", " ");
//        artifacts = Artifacts.getItemObject(Player.getLocation().getItemID(), artifactsList);
//
//        if (artifacts != null && artifacts.getArtiName().equalsIgnoreCase(noun)){
//            if (inventory.size() == 0){
//                System.out.println("Inventory is empty! no item to drop!");
//                return;
//            }
//            inventory.remove(artifacts);
//            System.out.println(noun + " has been dropped from inventory");
//            // System.out.println(inventory.size());
//            getPlayerInventoryMap().remove(artifacts,noun); //Raven -- created to update map for inventory
//        }
//        else{
//            System.out.println("There's no item in your inventory!");
//        }
//
//    }




    @Override
    public String toString() {
        return "\nPlayer info\n\n" +
                "Current Room: " + location.getRoomName() +
                "\nYour Health: " + playerHealth + "\nInventory: " + inventory;
    }
}
