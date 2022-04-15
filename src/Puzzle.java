import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Puzzle {

    private int puzzleID;
    private String puzzleType;
    private String puzzleDescription;
    private String puzzleSolution;
    private static HashMap<Puzzle,String> puzzleInfo = Text.getPuzzleMap(); // this hashmap helps track the puzzle information -- Raven

    // added an empty constructor for the Game class -- Raven
    public Puzzle() {
    }

    public Puzzle(int puzzleID, String puzzleType, String puzzleDescription, String puzzleSolution) {
        this.puzzleID = puzzleID;
        this.puzzleType = puzzleType;
        this.puzzleDescription = puzzleDescription;
        this.puzzleSolution = puzzleSolution;
    }

    public int getPuzzleID() {
        return puzzleID;
    }

    public void setPuzzleID(int puzzleID) {
        this.puzzleID = puzzleID;
    }

    public String getPuzzleType() {
        return puzzleType;
    }

    public void setPuzzleType(String puzzleType) {
        this.puzzleType = puzzleType;
    }

    public String getPuzzleDescription() {
        return puzzleDescription;
    }

    public void setPuzzleDescription(String puzzleDescription) {
        this.puzzleDescription = puzzleDescription;
    }

    public String getPuzzleSolution() {
        return puzzleSolution;
    }

    public void setPuzzleSolution(String puzzleSolution) {
        this.puzzleSolution = puzzleSolution;
    }

    public static HashMap<Puzzle, String> getPuzzleInfo() {
        return puzzleInfo;
    }

    public static void setPuzzleInfo(HashMap<Puzzle, String> puzzleInfo) {
        Puzzle.puzzleInfo = puzzleInfo;
    }

    /**
     * @author Raven
     * note: used to observe puzzle
     */
    public String observePuzzle(){
        String s = "";
        String response = "";
        Scanner input = new Scanner(System.in);

        for(Map.Entry<Puzzle, String> allPuzzles : getPuzzleInfo().entrySet()) {
            if (Player.getLocation().getPuzzleID() == allPuzzles.getKey().getPuzzleID()) {
                if(allPuzzles.getKey().getPuzzleType().equalsIgnoreCase("Word Scramble")){
                    s = "Type 'solve puzzle' to unscramble the word: \n\n";
                } else if (allPuzzles.getKey().getPuzzleType().equalsIgnoreCase("Riddle")) {
                    s = "Type 'solve puzzle' to answer the riddle: \n\n";
                }
                s = s + allPuzzles.getKey().getPuzzleDescription() + "\n\n";
                System.out.println(s);
                response = input.nextLine();
                while(!response.isEmpty()){
                    if(response.equalsIgnoreCase("solve puzzle")){
                        solvePuzzle();
                        break;
                    } else {
                        System.out.println("Invalid command. Try again");
                        response = input.nextLine();
                    }
                }
                s = "";
                break;
            }
            if(s.isEmpty()){
                s = "There are no puzzles in this room";
            }
        }
        return s;
    }

    /**
     * @author: Raven
     * Note: used to solve puzzle
     */
    public String solvePuzzle() {
        String s = "";
        Scanner input = new Scanner(System.in);
        int attempt = 2;

        for(Map.Entry<Puzzle, String> allPuzzles : getPuzzleInfo().entrySet()) {
            if (Player.getLocation().getPuzzleID() == allPuzzles.getKey().getPuzzleID()) {
                s = "";
                System.out.println("\n\nYou have two attempts...\n\n");
                String answer = input.next();
                while(input.hasNextLine()) {
                    if (answer.equalsIgnoreCase(allPuzzles.getKey().getPuzzleSolution()) && attempt >= 0) {
                        System.out.println("Correct!\n\n");
                        getPuzzleInfo().remove(allPuzzles.getKey());
                        break;
                    } else if (!answer.equalsIgnoreCase(allPuzzles.getKey().getPuzzleSolution()) && attempt > 0) {
                        attempt -= 1;
                        System.out.println("Incorrect you have " + attempt + " attempt left!");
                        attempt -= 1;
                        answer = input.next();
                    } else if (!answer.equalsIgnoreCase(allPuzzles.getKey().getPuzzleSolution()) && attempt == 0) {
                        System.out.println("Sorry that was incorrect :( \n" + "The puzzle is now gone...\n");
                        getPuzzleInfo().remove(allPuzzles.getKey());
                        break;
                    }
                }
                break;
            }
            if (s.isEmpty()){
                s = "No puzzle to solve here...";
            }
        }
        return s;
    }

    @Override
    public String toString() {
        return "Puzzle{" +
                "puzzleID='" + puzzleID + '\'' +
                ", puzzleType='" + puzzleType + '\'' +
                ", puzzleDescription='" + puzzleDescription + '\'' +
                ", puzzleSolution='" + puzzleSolution + '\'' +
                '}';
    }

}
