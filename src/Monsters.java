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




}

