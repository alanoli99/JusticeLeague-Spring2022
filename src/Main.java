import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        Game game = new Game();
        String input;
        String output;
        String gameStart;
        Scanner begin = new Scanner(System.in);
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("--------------------------");
        System.out.println("Type 'start' to start game");
        System.out.println("--------------------------");
        gameStart = begin.next();

        while (begin.hasNextLine()) {
            if (gameStart.equalsIgnoreCase("start")) {
                break;
            } else {
                System.out.println("invalid command\n");
                System.out.println("--------------------------");
                System.out.println("Type 'start' to start game");
                System.out.println("--------------------------");
                gameStart = begin.next();
            }
        }
        game.showIntro();
        do {
            System.out.println("->");
            input = in.readLine();
            output = game.playGame(input);

            System.out.println(output);

        } while (!input.equalsIgnoreCase("exit"));

    }

}
