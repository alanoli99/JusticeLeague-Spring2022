/**
 * @dkilbert
 */

public class Monsters {
    private String name;
    private String description;
    private int id;
    private int health;
    private int damage;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public Monsters(String name, String description, int id, int health, int damage) {
        this.name = name;
        this.description = description;
        this.id = id;
        this.health = health;
        this.damage = damage;
    }
}

