/**
 * @dkilbert
 */

public class Monsters {
    private String id;
    private String description;
    private int difficulty;
    private int riddle;
    private int hint;
    private int answer;
    private int choices;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public int getRiddle() {
        return riddle;
    }

    public void setRiddle(int riddle) {
        this.riddle = riddle;
    }

    public int getHint() {
        return hint;
    }

    public void setHint(int hint) {
        this.hint = hint;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    public int getChoices() {
        return choices;
    }

    public void setChoices(int choices) {
        this.choices = choices;
    }

    public Monsters(String id, String description, int difficulty, int riddle, int hint, int answer, int choices) {
        this.id = id;
        this.description = description;
        this.difficulty = difficulty;
        this.riddle = riddle;
        this.hint = hint;
        this.answer = answer;
        this.choices = choices;
    }
}

