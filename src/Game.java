import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Author name: Raven Garder
 * Worked on: Explore room feature,
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
            "south", "east", "west", "explore"));
    List<String> objects = new ArrayList<>(Arrays.asList(
            "room"
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
            msg = "only 2 word commands allowed";
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
}
