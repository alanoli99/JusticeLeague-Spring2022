import java.util.*;

/**
 * Author name: Raven Gardner
 * Worked on: Explore room feature, process/parse commands, consume/eat,
 * observe puzzle, solve puzzle, equip item
 *
 * Author name:
 * Worked on:
 *
 * Author name:
 * Worked on:
 *
 * Author name:
 * Worked on:
 *
 */

public class Game {
    private static Rooms rooms;
    private Player player;
    private Puzzle puzzle = new Puzzle();
    private Monsters monster = new Monsters();


    // Raven: used to parse command(s) from user
    List<String> commands = new ArrayList<>(Arrays.asList("help","n","s","e","w", "north",
            "south", "east", "west", "explore", "consume" , "eat" , "info", "try",
            "observe", "solve","help","check", "pickup","hint","drop", "equipped", "wear")); // try is for the test case -- will be deleted before final deliverable - Raven
    List<String> objects = new ArrayList<>(Arrays.asList(
            "room","old bread", "blood jar", "dagger", "bone head", "kite shield",
            "crystal ring", "midnight sword", "stone hammer", "metal armor", "berries",
            "diamond key", "puzzle","inventory", "test case for game", "test case" // testing multiple commands in one -- will be deleted - Raven
    ));

    // Raven: used to keep track of rooms for the item features
    public static Rooms getRooms() {
        return rooms;
    }

    public static void setRooms(Rooms rooms) {
        Game.rooms = rooms;
    }

    // Raven: created constructor
    public Game() {
        ArrayList<Rooms> roomInfo = new ArrayList<>();
        rooms = Text.readRoomFile(roomInfo).get(0);
        player = new Player(getRooms().getRoomName(), getRooms().getRoomID(), getRooms().getDescription(),
                getRooms().getItemID(), getRooms().getMonsterID(), getRooms().getPuzzleID(),
                getRooms().getNorth(), getRooms().getEast(), getRooms().getWest(), getRooms().getSouth(),
                getRooms().isVisit(), getRooms());
    }


    //@Alan - use to get the monsters name,health, and attack damage
    //@author: Raven; added run feature
    public String exploreMons() {
        Scanner input = new Scanner(System.in);
        String response = "";

        ArrayList<Rooms> roomInfo = new ArrayList<>();
        Text.readRoomFile(roomInfo);
        String s = "";

        ArrayList<Monsters> monsInfo = new ArrayList<>();
        Text.monsReader(monsInfo);
        String m = "";
        Random obj = new Random();

        int playerHealth = Player.getPlayerHealth();
        boolean playing = true;

        int playerAttack = Player.getPlayerAttkDamage();

        //System.out.println("current room id: " + getRoomID());

        // will change to compare room id in file to current room of the player

       // while (playing) {
            for (Monsters mons : monsInfo) {



                if (mons.getId() == Player.getLocation().getMonsterID()) {

                    m = "\n" + mons.getDescription() + " is in the room! " +
                            " Attack Damage: -" + mons.getMonsAttack() + ".";

                    m = m + "\n\n" + "What would you like to do?" + "\nType 'run' to run away" +
                            "\nType 'riddle' to answer the riddle" + "\nType 'attack' to attack monster";

                    System.out.println(m);
                    response = input.nextLine();
                    while (!response.isEmpty()) {

                        if (response.equalsIgnoreCase("run")) {
                            System.out.println("\n\n------------------------ ᕕ( ◎_◎)ᕗ\n\n" +
                                    "You have survived this encounter... don't waste it\n");

                            playing = false;
                            break;
                        }



                        else if (response.equalsIgnoreCase("Attack")) {

                             int damageCaused = playerAttack;
                            int damagetaken = mons.getMonsAttack();

                            int currentMonsHealth = mons.getMonsHealth();
                              currentMonsHealth -= damageCaused;

                            playerHealth -= damagetaken;
                            int updatedPlayerHealth = playerHealth;
                            Player.setPlayerHealth(updatedPlayerHealth - 50);


                        System.out.println("\t> You attacked " + mons.getDescription() + "for "
                                + damageCaused + " damage.");
                        System.out.println("\t>Monster Health " + currentMonsHealth);
                        System.out.println(" ");

                        System.out.println("\t>You recieved " + damagetaken + " point damage in return!");
                        System.out.println("\t>Your Health " + playerHealth);
//                        if (playerHealth < 1) {
//                            System.out.println("\t>Too much damage! The monster took advantage of your state and ate you");
//                            break;
//                        }

                            response = input.nextLine();
                        } else if (response.equalsIgnoreCase("riddle")) {
                            System.out.println("riddle feature\n");
                            response = input.nextLine();
                        } else {
                            System.out.println("Invalid command. Try again");
                            response = input.nextLine();
                        }
                    }
                    m = "";
                    break;
                }
                if (m.isEmpty()) {
                    m = "No monsters in here";
                }

            }

        return m;
    }

    // Raven: used to keep track of player
    public Player getPlayer() {
        return player;
    }

    // Raven: method can be removed because they didn't ask for it on their SRS, but helps see if player's health and location actually changed
    private void info() {
        System.out.println(getPlayer().toString());
    }

    // Raven: used to retrieve room name and description
    private String exploreRoom(String name) {
        return rooms.exploreRoom();
    }

    private String exploreMons(String name) {
        return exploreMons();
    }

    // Raven: used to consume item
    private String consumeItem(String name) {
        return Player.consume(name);
    }

    // Raven: used to equip item
    private String equipItem(String name) {
        return Player.equip(name);
    }

    // Raven: used to observe puzzle
    private String observePuzzle(String name) {
        return puzzle.observePuzzle();
    }

    // Raven: used to solve puzzle
    private String solvePuzzle(String name) {
        return puzzle.solvePuzzle();
    }

    //Joe: created to navigate rooms
    private void moveAround(String verb) {
        rooms.moveAround(verb);
    }
    //Joe: used to get controls of the game
    private void controls() {
        String c = "";
        Text.showControls(c);
    }
    // Joe N: Method is used to check user inventory
    private void checkInventory() {
        System.out.println(Player.getInventory());
    }
    //Joe N: Method to explore items in the room
    private void exploreItem(String noun) {
        Artifacts.exploreArtifacts(noun);
    }
    //Joe N: Method to pickup items
    private void pickupItem(String noun) {
        Player.addToInventory(noun);
    }

    //Alan o: method to drop items
    private void dropItem(String noun) {
        Player.removeFromInventory(noun);
    }

    // Raven: EXAMPLE FOR MULTIPlE OBJECTS IN ONE COMMAND; similar structure can be used for item features
    private String test(String name) {

        return name + " will be implemented";
    }

    // @author: Raven Gardner; created to process commands from user when it is one command
    public String processVerb(List<String> wordlist) {
        String verb;
        String msg = "";

        verb = wordlist.get(0);
        if (commands.contains(verb)) {
            switch (verb) {
                case "n", "north", "w", "west", "s", "south", "e", "east" -> moveAround(verb);
                case "help" -> controls();
                case "info" -> info();
                default -> msg = verb + "--not implemented yet";
            }
        } else {
            msg = verb + " is not a valid input ";
        }
        return msg;
    }

    // @author: Raven Gardner; same as processVerb; created because a few commands from SRS repeat
    // one verb, one noun
    public String processTwoWords(List<String> wordlist) {
        String verb;
        String noun;
        String msg = "";
        boolean error = false;
        verb = wordlist.get(0);
        noun = wordlist.get(1);

        if (!commands.contains(verb)) {
            msg = verb + " is not a valid input ";
        }
        if (!objects.contains(noun)) {
            msg += (noun + " is not a valid input ");
        }
        if (noun.equalsIgnoreCase("room")) {
            switch (verb) {
                case "explore" -> msg = exploreRoom(noun);
            }
        }
        else if (!error && noun.equalsIgnoreCase("puzzle")){
            switch (verb) {
                case "observe" -> msg = observePuzzle(noun);
                case "solve" -> msg = solvePuzzle(noun);
            }
        }
        else if (!error && noun.equalsIgnoreCase("monster")){
            switch (verb) {
                case "observe" -> msg = exploreMons(noun);

            }
        }
        else if (!error){
            switch (verb) {
                case "consume", "eat" -> msg = consumeItem(noun);
                case "explore" -> exploreItem(noun);
                case "check" -> checkInventory();
                case "pickup" -> pickupItem(noun);
                case "drop" -> dropItem(noun);
                case "equipped", "wear" -> msg = equipItem(noun);
                default -> msg += "--not yet implemented";
            }
        }
        return msg;
    }
    // @author: Raven; created because on SRS we have to use more than two commands sometimes
    // example: picking up item 'Moldy Bread': can use the verb 'pickup' followed by 'moldy bread'
    // one verb, two or more nouns
    public String processMulipleNouns(List<String> wordlist) {
        String verb;
        String extra;
        String nounCommand = "";
        String inObjects = "";
        String objMsg = "";
        String msg = "";
        boolean error = true;
        boolean notNouns = false;
        verb = wordlist.get(0); // just gets one word for commands - Raven
        if (!commands.contains(verb)) {
            msg = verb + " is an invalid input ";
        }
        // iterates between input from user until full command is done
        // ex. 'room feature'; returns 'room' first then it returns 'room feature' - Raven
        for (int i = 1; i < wordlist.size(); i++) {
            nounCommand = wordlist.get(i);
            msg = msg + " " + nounCommand;
//            System.out.println("msg:" + msg + ":"); // uncomment to see example
        }

        // iterates over object list declared at the top of the class
        for (int k = 0; k < objects.size(); k++){
            inObjects = objects.get(k);
            objMsg = " " + inObjects;
//            System.out.println("msg:" + msg + ":");
//            System.out.println("objMsg:" + objMsg + ":");


            if ((msg.equalsIgnoreCase(objMsg))) { // add verb cases here - Raven
                //System.out.println("entered if");
                switch (verb) {
                    case "try" -> {
                        //System.out.println("entered switch");
                        msg = test(msg);
                        break;
                    }
                    case "consume", "eat" -> {
                        msg = consumeItem(msg);
                        break;
                    }
                    case "explore" -> {
                        exploreItem(msg);
                        msg = "";
                        break;
                    }
                    case "pickup" -> {
                        pickupItem(msg);
                        msg = "";
                        break;
                    }
                    case "drop" -> {
                        dropItem(msg);
                        msg = "";
                        break;
                    }
                    case "equipped", "wear" -> {
                        msg = equipItem(msg);
                        break;
                    }
//                    default -> msg = "not implemented yet!";
                }
                error = false;
            }
        }

        // if it didn't enter if statement in the for loop then the noun isn't in object list - Raven
        if (error) {
            notNouns = true;

        }
        if (notNouns) {
            extra = " are not valid inputs";
        } else {
            extra = "";
        }
        if (notNouns) {
            msg += extra;
        }

        return msg;
    }


    // @author: Raven Gardner; created to show the intro after starting game
    public void showIntro(){
        String s;
        s = "----------------------------------\n" + "Welcome to Necromancing Dreams!\n" + "----------------------------------\n" +
                "DIRECTIONS: \n\n" +
                "You are a knight who must save the princess from the final boss (the necromancer), and leave the castle from where you started!\n" +
                "Throughout the game you will collect items that will help fight different level monsters and use some brain power\n" +
                "to solve puzzles that are mapped out throughout the game... \n\n\n\n" +
                "type 'help' to get control details\n\n\n\n" +
                "You are starting off in Room " + rooms.getRoomID() + ". \n";

        System.out.println(s);
    }

    // @author: Raven Gardner; created to parse commands
    public String parseCommand(List<String> wordlist) {
        String msg;
        if (wordlist.size() == 1) {
            msg = processVerb(wordlist);
        } else if (wordlist.size() == 2){
            msg = processTwoWords(wordlist);
        } else {
            msg = processMulipleNouns(wordlist);
        }
        return msg;
    }

    // @author: Raven Gardner; created to store word list
    public static List<String> wordList(String input) {
        String delims = "[ \t,.:;?!\"']+";
        List<String> strlist = new ArrayList<>();
        String[] words = input.split(delims);

        for (String word : words) {
            strlist.add(word);
        }
        return strlist;
    }

    //@author: Alan Oliver; Allows the user to quit playing
    // the game passed the start menu
    public String runCommand(String inputstr) {
        List<String> wordlist;
        String commands = "Thanks for playing!";
        String lowstr = inputstr.trim().toLowerCase();
        if (!lowstr.equalsIgnoreCase("Exit")) {
            if (lowstr.equals("")) {
                commands = "You must enter a command";
            } else {
                wordlist = wordList(lowstr);
                commands = parseCommand(wordlist); // changed processVerb to parseCommand -- Raven
            }
        }
        return commands;
    }
}
