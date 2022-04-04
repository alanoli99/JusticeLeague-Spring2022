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

   
    // @author: Raven Gardner; created to process commands from user
    public String processVerb(List<String> wordlist) {
        String verb;
        String msg = "";

        verb = wordlist.get(0);
        // @author: Raven Gardner; created if- else if -else structure; added explore room
        if (commands.contains(verb)) {
            switch (verb) {
                case "explore room" -> explore();
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
}
