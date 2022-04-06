//Alan
public class Artifacts {

    private int artiID;
    private String artiName;
    private String artiDescription;
    private String artiType;
    private String artiUsage;
    private String artiStrength;

    public Artifacts(int artiID, String artiName, String artiDescription, String artiType, String artiUsage, String artiStrength) {
        this.artiID = artiID;
        this.artiName = artiName;
        this.artiDescription = artiDescription;
        this.artiType = artiType;
        this.artiUsage = artiUsage;
        this.artiStrength = artiStrength;
    }

    public int getArtiID() {
        return artiID;
    }

    public void setArtiID(int artiID) {
        this.artiID = artiID;
    }

    public String getArtiName() {
        return artiName;
    }

    public void setArtiName(String artiName) {
        this.artiName = artiName;
    }

    public String getArtiDescription() {
        return artiDescription;
    }

    public void setArtiDescription(String artiDescription) {
        this.artiDescription = artiDescription;
    }

    public String getArtiType() {
        return artiType;
    }

    public void setArtiType(String artiType) {
        this.artiType = artiType;
    }

    public String getArtiUsage() {
        return artiUsage;
    }

    public void setArtiUsage(String artiUsage) {
        this.artiUsage = artiUsage;
    }

    public String getArtiStrength() {
        return artiStrength;
    }

    public void setArtiStrength(String artiStrength) {
        this.artiStrength = artiStrength;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
