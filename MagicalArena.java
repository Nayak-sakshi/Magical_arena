import java.util.Random;

class Player {
    private int health;
    private int strength;
    private int attack;

    public Player(int health, int strength, int attack) {
        this.health = health;
        this.strength = strength;
        this.attack = attack;
    }

    public int getHealth() {
        return health;
    }

    public int getStrength() {
        return strength;
    }

    public int getAttack() {
        return attack;
    }

    public void reduceHealth(int damage) {
        health -= damage;
        if (health < 0) {
            health = 0;
        }
    }

    public boolean isAlive() {
        return health > 0;
    }
}

public class MagicalArena {
    private static final int DIE_SIDES = 6;
    private static Random random = new Random();

    public static int rollDie() {
        return random.nextInt(DIE_SIDES) + 1;
    }

    public static void main(String[] args) {
        Player playerA = new Player(50, 5, 10);
        Player playerB = new Player(100, 10, 5);

        boolean playerATurn = playerA.getHealth() <= playerB.getHealth();

        while (playerA.isAlive() && playerB.isAlive()) {
            if (playerATurn) {
                int attackRoll = rollDie();
                int defenseRoll = rollDie();

                int damage = playerA.getAttack() * attackRoll;
                int defense = playerB.getStrength() * defenseRoll;

                int damageInflicted = Math.max(0, damage - defense);

                playerB.reduceHealth(damageInflicted);
                System.out.println("Player A attacks! Damage inflicted: " + damageInflicted);
            } else {
                int attackRoll = rollDie();
                int defenseRoll = rollDie();

                int damage = playerB.getAttack() * attackRoll;
                int defense = playerA.getStrength() * defenseRoll;

                int damageInflicted = Math.max(0, damage - defense);

                playerA.reduceHealth(damageInflicted);
                System.out.println("Player B attacks! Damage inflicted: " + damageInflicted);
            }

            playerATurn = !playerATurn;
        }

        if (playerA.isAlive()) {
            System.out.println("Player A wins!");
        } else {
            System.out.println("Player B wins!");
        }
    }
}
