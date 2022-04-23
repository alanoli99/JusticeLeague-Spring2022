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

    public Monsters(int id, String description, String itemsHeld, String difficulty, String riddle, String hint, String answer, String choices,
                    int monsHealth, int monsAttack) {
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



        //System.out.println("current room id: " + getRoomID());

        // will change to compare room id in file to current room of the player

        // while (playing) {

        for (Monsters mons : monsInfo) {




            if (mons.getId() == Player.getLocation().getMonsterID()) {

                int playerAttack = obj.nextInt(0,4);

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

                        if(mons.monsHealth >= 1) {

                            int damageCaused = playerAttack;
                            int damagetaken = mons.getMonsAttack();

                            int currentMonsHealth = mons.getMonsHealth();
                            currentMonsHealth -= damageCaused;

                            playerHealth -= damagetaken;
                            int updatedPlayerHealth = playerHealth;
                            Player.setPlayerHealth(-damagetaken); // previously would update random wrong numbers -- Raven

                            mons.setMonsHealth(currentMonsHealth);

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
                        }

                       else if (mons.monsHealth == 0){
                            System.out.println(mons.getDescription() + " has been killed!");

                        }

                        response = input.nextLine();
                    }
                    else if (response.equalsIgnoreCase("riddle")) {
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


}

