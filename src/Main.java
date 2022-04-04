import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        Game game = new Game();
        String input;
        String output;
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        do {
            System.out.println("->");
            input = in.readLine();
            output = game.playGame(input);

            System.out.println(output);

        } while (!input.equalsIgnoreCase("Q"));

    }

}
