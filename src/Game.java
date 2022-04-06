import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Author name: Raven Garder
 * Worked on: Explore room feature, process/parse commands
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
    private Rooms rooms;

    // Raven: used to parse command(s) from user
    List<String> commands = new ArrayList<>(Arrays.asList("n","s","e","w", "north",
            "south", "east", "west", "explore", "try")); // try is for the test case -- will be deleted before final deliverable - Raven
    List<String> objects = new ArrayList<>(Arrays.asList(
            "room", "test case for game", "test case" // testing multiple commands in one -- will be deleted - Raven
    ));

    // Raven: created constructor
    public Game() {
        ArrayList<Rooms> roomInfo = new ArrayList<>();
        rooms = Text.readRoomFile(roomInfo).get(0);
    }

    // Raven: used to retrieve room name and description
    private String explore(String name) {
        return rooms.exploreRoom();
    }

    //Joe: created to navigate rooms
    private void moveAround(String verb) {
        rooms.moveAround(verb);
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
                default -> msg = verb + " (not a valid command)";
            }
        } else {
            msg = verb + " is not a known verb! ";
        }
        return msg;
    }

    // @author: Raven Gardner; same as processVerb; created because a few commands from SRS repeat
    public String processTwoWords(List<String> wordlist) {
        String verb;
        String noun;
        String msg = "";
        boolean error = false;
        verb = wordlist.get(0);
        noun = wordlist.get(1);

        if (!commands.contains(verb)) {
            msg = verb + " is not a known verb! ";
        }
        if (!objects.contains(noun)) {
            msg += (noun + " is not a known noun!");
        }
        if (!error && noun.equalsIgnoreCase("room")) {
            switch (verb) {
                case "explore" -> msg = explore(noun);
            }
        } else if (!error){
            switch (verb) {
                default -> msg += " (not yet implemented)";
            }
        }
        return msg;
    }

    // @author: Raven; created because on SRS we have to use more than two commands sometimes
    // example: picking up item 'Moldy Bread': can use the verb 'pickup' followed by 'moldy bread'
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
            msg = verb + " is not a known verb! ";
        }

        // iterates between input from user until full command is done
        // ex. 'room feature'; returns 'room' first then it returns 'room feature' - Raven
        for (int i = 1; i < wordlist.size(); i++) {
            nounCommand = wordlist.get(i);
            msg = msg + " " + nounCommand;
            //System.out.println("msg:" + msg + ":"); // uncomment to see example
        }

        // iterates over object list declared at the top of the class
        for (int k = 0; k < objects.size(); k++){
            inObjects = objects.get(k);
            objMsg = " " + inObjects;
            //System.out.println("msg:" + msg + ":");
            //System.out.println("objMsg:" + objMsg + ":");

            if ((msg.equalsIgnoreCase(objMsg))) { // add verb cases here - Raven
                //System.out.println("entered if");
                switch (verb) {
                    case "try" -> {
                        //System.out.println("entered switch");
                        msg = test(msg);
                        break;
                    }
                    //default -> msg = "";
                }
                error = false;
            }
        }

        // if it didn't enter if statement in the for loop then the noun isn't in object list - Raven
        if (error) {
            notNouns = true;

        }
        if (notNouns) {
            extra = " are not known nouns!";
        } else {
            extra = "";
        }
        if (notNouns) {
            msg += extra + " (not yet implemented)";
        }

        return msg;
    }


    // @author: Raven Gardner; created to show the intro after starting game
    public void showIntro(){
        String s;
        s = "----------------------------------\n" + "Welcome to Necromancing Dreams!\n" + "----------------------------------\n\n\n\n" +
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

    // This method is used to run the input the user entered - Joe N
    public String playGame(String inputstr) {
        List<String> wordlist;
        String s = "";
        String lowstr = inputstr.trim().toLowerCase();

        if (lowstr.equals("")) {
                s = "You must enter a command";
        } else {
            wordlist = wordList(lowstr);
            s = parseCommand(wordlist);
        }

        return s;
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
