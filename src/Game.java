import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

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

    // Raven: used to parse commands from user
    List<String> commands = new ArrayList<>(Arrays.asList(
            "explore room"));


    // Raven: I used this in the main method (needed it to check that the explore room feature was working)
    // ** created my own main method before Joe's main method was pushed to GitHub
    public Game() {
        ArrayList<Rooms> roomInfo = new ArrayList<>();
        rooms = Text.readRoomFile(roomInfo).get(0);
    }

    // Raven: used to retrieve room name and description
    private void explore() {
        rooms.exploreRoom();
    }

    /*
    IGNORE METHOD

    // @author: Raven Gardner; to process the word list and parse info to the main/view

    public static List<String> wordList(String input) {
        String delims = "[ \t,.:;?!\"']+";
        List<String> strlist = new ArrayList<>();
        String[] words = input.split(delims);

        for (String word : words) {
            strlist.add(word);
        }
        return strlist;
    }

    public String parseCommand(List<String> wordlist) {
        String msg;
        if (wordlist.size() >= 1) {
            msg = processVerb(wordlist);
        } else {
            msg = "Only 2 word commands allowed!";
        }
        return msg;
    }


 */
    // @author: Raven Gardner; created to process commands from user
    public String processVerb(List<String> wordlist) {
        String verb;
        String msg = "";

        verb = wordlist.get(0);
        // @author: Raven Gardner; created if- else if -else structure; added explore room
        if(commands.contains("explore room")){
            explore();
        } else if (commands.contains(verb)) {
            switch (verb) {
                default -> msg = verb + " (not a valid command)";
            }
        } else {
            msg = verb + " is not a known verb! ";
        }
        return msg;
    }

    // @author: Raven Gardner; created to show the intro after starting game
    public void showIntro(){
        String s;
        s = "----------------------------------\n" + "\tWelcome to Necromancing Dreams!\n" + "----------------------------------\n";

        System.out.println(s);
    }

    /*
    IGNORE METHOD

    // @author: Raven Gardner; created to play game

    // notes for team members: y'all can use this to run the game in the main
    // I created it to make sure the room file was read and the explore room
    // feature worked; created the exit command (marked by this: ****), so then I could end the game;
    // Alan you can ignore it in order to implement the exit command feature

    public String playGame(String inputstr) {
        List<String> wordlist;
        String s = "--------------------------------------------\n" +
                "You are exiting Necromancing Dreams. Goodbye now!\n" +
                "--------------------------------------------";
        String lowstr = inputstr.trim().toLowerCase();
        if (!lowstr.equals("exit")) { **************************************************
            if (lowstr.equals("")) {
                s = "You must enter a command";
            } else {
                wordlist = wordList(lowstr);
                s = parseCommand(wordlist);
            }
        }
        return s;
    }

     */
}
