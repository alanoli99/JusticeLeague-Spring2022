import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Class: Player
 *
 * @author: Raven Gardner
 * created attributes, getter/setter, and hashmap info
 * Methods: consume, equip, and unequip
 * @author: Alan Oliver
 * playerHealth and Playerattck Damage
 */

public class Player extends Rooms {
    protected static Rooms location;
    protected static int playerHealth;
    protected static int playerAttkDamage;
    private static HashMap<Artifacts, String> equippedItems = new HashMap<>();
    private static HashMap<Artifacts, String> inventoryMap = new HashMap<>();
    private static HashMap<Artifacts, Integer> playerInfo = new HashMap<>(); // key = Artifacts info, value = health -- Raven
    public static ArrayList<Artifacts> inventory = new ArrayList<>();
    private static HashMap<Artifacts, String> itemsDropped = new HashMap<>();


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

    public static HashMap<Artifacts, String> getItemsDropped() {
        return itemsDropped;
    }

    public static void setItemsDropped(HashMap<Artifacts, String> itemsDropped) {
        Player.itemsDropped = itemsDropped;
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
                    if (getPlayerHealth() < 1) {
                        System.out.println("Your health is now " + getPlayerHealth() + "HP");
                        System.out.println("You've reached the end of your journey :(");
                        System.out.println("\nThanks for playing!");
                        System.exit(0);
                    }
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
                    equippedItems.put(inInventory, inInventory.getArtiName());

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
     * drop item from inventory
     */
    public static String removeFromInventory(String obname) {

        Artifacts inInventory;

        obname = obname.trim().replaceAll("\\s{2,}", " "); // gets rid of the space in the beginning of the item name -- Raven

        for (Map.Entry<Artifacts, String> collected : getPlayerInventoryMap().entrySet()) {

            inInventory = collected.getKey();

            if (inInventory.getArtiName().equalsIgnoreCase(obname)) {

                if (getPlayerInventoryMap().size() == 0) {
                    System.out.println("No items to drop!");
                    break;
                }

                //Player.getLocation().setItemID(inInventory.getArtiID());

                // prevents from dropping in all rooms
                inInventory.setArtiID(getLocation().getRoomID()); // sets the specific item that was dropped room id to the current location of the player

                getInventory().remove(collected.getKey());
                getPlayerInventoryMap().remove(inInventory);

                getItemsDropped().put(inInventory, inInventory.getArtiName()); // ADDED FOR DROP ITEM/EXPLORE ROOM FEATURE

                System.out.println(obname + " has been dropped!");
                break;
            }

            playerInfo.put(inInventory, getPlayerHealth());
           // break;
        }
        return obname;
    }

    // @author: Joe Nsengiyumva
    public static void addToInventory(String noun) {
        ArrayList<Artifacts> artifactsList = new ArrayList<>();
        Text.artiList(artifactsList);
        Artifacts artifacts;
        noun = noun.trim().replaceAll("\\s{2,}", " ");
        artifacts = Artifacts.getItemObject(Game.getRooms().getItemID(), artifactsList);
        //artifacts = artifacts in text file; static to room
        //if artifacts is not null and the artifacts name equals the command from console
        if (artifacts != null && artifacts.getArtiName().equalsIgnoreCase(noun)) {
            if (inventory.size() >= 5) {
                System.out.println("Inventory is full! drop item.");
                return;
            }
            inventory.add(artifacts);
            System.out.println(noun + " has been added to inventory");
            //System.out.println(inventory.size());
            getPlayerInventoryMap().put(artifacts, noun); //Raven -- created to update map for inventory

            // ADDED FOR DROP ITEM/EXPLORE ROOM FEATURE
            if(getItemsDropped().containsValue(artifacts.getArtiName())) {
                Artifacts dropped;
                String droppedString;
                for(Map.Entry<Artifacts, String> inDrop : getItemsDropped().entrySet()){
                    dropped = inDrop.getKey();
                    droppedString = inDrop.getValue();
                    if(droppedString.equalsIgnoreCase(artifacts.getArtiName())){
                        //System.out.println("artifacts object:" + artifacts);
                        //System.out.println("drop hashmap item obj:" + dropped);
                        getItemsDropped().remove(dropped);
                        break;
                    }
                }
            }

        } else if (artifacts != null && !artifacts.getArtiName().equalsIgnoreCase(noun)) {
            if (inventory.size() > 5) {
                System.out.println("Inventory is full! drop item.");
                return;
            }

            // ADDED FOR DROP ITEM/EXPLORE ROOM FEATURE
            for (Map.Entry<Artifacts, String> pickup : getItemsDropped().entrySet()) {
                //System.out.println("pickup value:"+pickup.getValue());
                //System.out.println("artifacts value:"+artifacts.getArtiName());
                if (!pickup.getValue().equalsIgnoreCase(artifacts.getArtiName()) && pickup.getKey().getArtiID() == getLocation().getRoomID()) {
                    if (inventory.size() >= 5) {
                        System.out.println("Inventory is full! drop item.");
                        return;
                    }
                    inventory.add(pickup.getKey());
                    System.out.println(noun + " has been added to inventory");
                    //System.out.println(inventory.size());
                    getPlayerInventoryMap().put(pickup.getKey(), noun); //Raven -- created to update map for inventory
                    getItemsDropped().remove(pickup.getKey());
                    //System.out.println(getItemsDropped());
                    return;
                }
            }

            System.out.println(noun + " is not in this room");
        } else if(!getItemsDropped().isEmpty()){
            if (inventory.size() > 5) {
                System.out.println("Inventory is full! drop item.");
                return;
            }

            // ADDED FOR DROP ITEM/EXPLORE ROOM FEATURE
            for (Map.Entry<Artifacts, String> pickup : getItemsDropped().entrySet()) {
                //System.out.println("pickup value:"+pickup.getValue());
                //System.out.println("console:"+noun);
                if (pickup.getValue().equalsIgnoreCase(noun)) {
                    if (inventory.size() >= 5) {
                        System.out.println("Inventory is full! drop item.");
                        return;
                    }
                    inventory.add(pickup.getKey());
                    System.out.println(noun + " has been added to inventory");
                    //System.out.println(inventory.size());
                    getPlayerInventoryMap().put(pickup.getKey(), noun); //Raven -- created to update map for inventory
                    getItemsDropped().remove(pickup.getKey());
                    //System.out.println(getItemsDropped());
                    return;
                }
            }

            System.out.println(noun + " is not in this room");

        }else {
            System.out.println("There's no item in this room");
        }

    }// used to test check inventory -Joe N


    @Override
    public String toString() {
        return "\nPlayer info\n\n" +
                "Current Room: " + location.getRoomName() +
                "\nYour Health: " + playerHealth + "\nInventory: " + inventory +
                "\nItem in Room: "+ Player.getLocation().getItemID() +
                "\nItem Dropped: " + Player.getItemsDropped(); // ADDED FOR DROP ITEM/EXPLORE ROOM FEATURE
    }
}
