import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * @dkilbert
 * @AlanOliver - added the monsters health and damage points.
 * updated the textfile to match this class. changed id to int, and
 * desc,diff,riddle,hint,answer,choices to string. In the text file i changed it to
 * int instead of doubles. added items held to the class
 * @author: Raven - implemented run feature.
 * @author: Joe - implemented monster riddle
 */

public class Monsters {
    private int id;
    private String description;
    private String itemsHeld;
    private String difficulty;
    private String riddle;
    private String hint;
    private String answer;
    private String choices;
    private int monsHealth;
    private int monsAttack;
    private String riddleReward;
    // added an empty constructor for the Game class -- Alan
    public Monsters() {

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getItemsHeld() {
        return itemsHeld;
    }

    public void setItemsHeld(String itemsHeld) {
        this.itemsHeld = itemsHeld;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getRiddle() {
        return riddle;
    }

    public void setRiddle(String riddle) {
        this.riddle = riddle;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getChoices() {
        return choices;
    }

    public void setChoices(String choices) {
        this.choices = choices;
    }

    public int getMonsHealth() {
        return monsHealth;
    }

    public void setMonsHealth(int monsHealth) {
        this.monsHealth = monsHealth;
    }

    public int getMonsAttack() {
        return monsAttack;
    }

    public void setMonsAttack(int monsAttack) {
        this.monsAttack = monsAttack;
    }

    public String getRiddleReward() {
        return riddleReward;
    }

    public void setRiddleReward(String riddleReward) {
        this.riddleReward = riddleReward;
    }

    public Monsters(int id, String description, String itemsHeld, String difficulty, String riddle, String hint, String answer, String choices,
                    int monsHealth, int monsAttack, String riddleReward) {
        this.id = id;
        this.description = description;
        this.itemsHeld = itemsHeld;
        this.difficulty = difficulty;
        this.riddle = riddle;
        this.hint = hint;
        this.answer = answer;
        this.choices = choices;
        this.monsHealth = monsHealth;
        this.monsAttack = monsAttack;
        this.riddleReward = riddleReward;
    }

    //@Alan - use to get the monsters name,health, and attack damage
    //@author: Raven; added run feature
    //@author: Joe N; added riddle feature
    public String exploreMons() {

        Scanner input = new Scanner(System.in);
        String response = "";

        ArrayList<Rooms> roomInfo = new ArrayList<>();
        Text.readRoomFile(roomInfo);

        ArrayList<Monsters> monsInfo = new ArrayList<>();
        Text.monsReader(monsInfo);
        String m = "";

        Random obj = new Random();

        int playerHealth = Player.getPlayerHealth();
        boolean playing = true;




        for (Monsters mons : monsInfo) {


            if (mons.getId() == Player.getLocation().getMonsterID()) {


                m = "\n" + mons.getDescription() + " is in the room! ";

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

                        if(mons.monsHealth > 0) {

                            int playerAttack = obj.nextInt(4);

                            int damageCaused = playerAttack;
                            int damagetaken = obj.nextInt(2);

                            int currentMonsHealth = mons.getMonsHealth();
                            currentMonsHealth -= damageCaused;

                            playerHealth -= damagetaken;
                            //int updatedPlayerHealth = playerHealth;
                            Player.setPlayerHealth(-damagetaken); // previously would update random wrong numbers -- Raven

                            mons.setMonsHealth(currentMonsHealth);

                            if (playerHealth < 1) {
                                System.out.println("\t>Too much damage! The monster took advantage of your state and ate you");
                                System.out.println("Thanks for playing!");
                                System.exit(0);
                            }

                            else if (mons.monsHealth < 1 && mons.getId() == 8 ){

                                System.out.println(mons.getDescription() + " has been killed! You have rescued the princess!");
                                System.out.println("Congratulations! You and the Princess leave the castle to live a happy life. ");
                                System.exit(0);
                                break;
                            }

                            else if (mons.monsHealth < 1){

                                System.out.println(mons.getDescription() + " has been killed!");
                                break;
                            }



                            System.out.println("\t> You attacked " + mons.getDescription() + "for "
                                    + damageCaused + " damage.");
                            System.out.println("\t>Monster Health " + currentMonsHealth);
                            System.out.println(" ");

                            System.out.println("\t>You recieved " + damagetaken + " point damage in return!");
                            System.out.println("\t>Your Health " + playerHealth);




                        }



                        response = input.nextLine();
                    }
                    else if (response.equalsIgnoreCase("riddle")) {
                        int count = 2;

                        while (count >= 0){
//
                            System.out.println("Answer the following riddle: " + mons.getRiddle());
                            String riddleAnswer = input.nextLine();

                            if (mons.getAnswer().equalsIgnoreCase(riddleAnswer)){
                                System.out.println("You have answered right! here's your reward: ");
                                System.out.println(mons.getRiddleReward());
                                if (mons.getId() == 8){
                                    System.out.println(mons.getDescription() + " has been killed! You have rescued the princess!");
                                    System.out.println("Congratulations! You and the Princess leave the castle to live a happy life. ");
                                    System.exit(0);
                                }
                                return "";
                            }
                            count--;
                            if (count == 0){
                                System.out.println("you have no more chances left. better luck next time!");
                                return "";
                            }

                            System.out.println("wrong answer you have " + count + " chance left");
                            System.out.println("Here's a hint: " + mons.getHint() + "\n");

                        }

//                        response = input.nextLine();
                    }
                    else {
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


}

