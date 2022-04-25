/**
 * @author : Dominick Kilbert
 */

public class EquipArtifacts extends Artifacts{
    private int ArtifactDamage;

    public EquipArtifacts(int artiID, String artiName, String artiDescription, String artiType, String artiUsage, int consumeHealth, int equipHealth) {
        super(artiID, artiName, artiDescription, artiType, artiUsage, consumeHealth, equipHealth);
    }


    public int getArtifactDamage() {
        return ArtifactDamage;
    }

    public void setArtifactDamage(int artifactDamage) {
        ArtifactDamage = artifactDamage;
    }
}