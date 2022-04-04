public class Puzzle {

    private String puzzleName;
    private String puzzleType;
    private String puzzleDescription;
    private String puzzleSolution;

    public Puzzle(String puzzleName, String puzzleType, String puzzleDescription, String puzzleSolution) {
        this.puzzleName = puzzleName;
        this.puzzleType = puzzleType;
        this.puzzleDescription = puzzleDescription;
        this.puzzleSolution = puzzleSolution;
    }

    public String getPuzzleName() {
        return puzzleName;
    }

    public void setPuzzleName(String puzzleName) {
        this.puzzleName = puzzleName;
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

    @Override
    public String toString() {
        return "Puzzle{" +
                "puzzleName='" + puzzleName + '\'' +
                ", puzzleType='" + puzzleType + '\'' +
                ", puzzleDescription='" + puzzleDescription + '\'' +
                ", puzzleSolution='" + puzzleSolution + '\'' +
                '}';
    }

}
