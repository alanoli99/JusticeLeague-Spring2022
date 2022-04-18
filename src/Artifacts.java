import java.util.ArrayList;

/**
 * @author: Alan Oliver
 * Note: I created the attributes, constructor, and basic getter/setter methods
 *
 * @author: Raven Gardner
 * Note: split strength into consumeHealth and equipHealth
 */
public class Artifacts {

    private int artiID;
    private String artiName;
    private String artiDescription;
    private String artiType;
    private String artiUsage;
    private int consumeHealth;
    private int equipHealth;

    //private static HashMap<Artifacts, String> artifactsMap = Text.getHashMapForArti(); // putting static maintains the hashmap between methods.

    public Artifacts(int artiID, String artiName, String artiDescription, String artiType, String artiUsage, int consumeHealth, int equipHealth) {
        this.artiID = artiID;
        this.artiName = artiName;
        this.artiDescription = artiDescription;
        this.artiType = artiType;
        this.artiUsage = artiUsage;
        this.consumeHealth = consumeHealth;
        this.equipHealth = equipHealth;
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

    public int getConsumeHealth() {
        return consumeHealth;
    }

    public void setConsumeHealth(int consumeHealth) {
        this.consumeHealth = consumeHealth;
    }

    public int getEquipHealth() {
        return equipHealth;
    }

    public void setEquipHealth(int equipHealth) {
        this.equipHealth = equipHealth;
    }

    @Override
    public String toString() {
        return getArtiName();
    }

    public static Artifacts getItemObject(int artifactId, ArrayList<Artifacts> list) {

        for (Artifacts item : list) {
            if (item.getArtiID() == artifactId) {
                return item;
            }
        }
        return null;
    }

    public static void exploreArtifacts(String noun) {
        ArrayList<Artifacts> artifactsList = new ArrayList<>();
        Text.artiList(artifactsList);
        Artifacts artifacts;
        noun = noun.trim().replaceAll("\\s{2,}", " ");
        artifacts = getItemObject(Game.getRooms().getItemID(), artifactsList);

        if (artifacts != null && artifacts.getArtiName().equalsIgnoreCase(noun)){
            System.out.println(artifacts.getArtiDescription());
        }
        else{
            System.out.println("There's no such item in this room");
        }
        //return noun;
    }

}
