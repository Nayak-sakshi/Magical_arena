import java.util.Random;

class Player {
    private String name;
    private int health;
    private int strength;
    private int attack;

    public Player(String name, int health, int strength, int attack) {
        this.name = name;
        this.health = health;
        this.strength = strength;
        this.attack = attack;
    }

    public String getName() {
        return name;
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

    public int rollDice() {
        Random rand = new Random();
        return rand.nextInt(6) + 1; // Generating random number between 1 and 6
    }

    public int calculateDamage(int diceValue) {
        return diceValue * attack;
    }

    public int calculateDefense(int diceValue) {
        return diceValue * strength;
    }
}

public class MagicalArena {
    public static void main(String[] args) {
        // Create two players with random attributes
        Player playerA = new Player("Warrior A", getRandomAttribute(), getRandomAttribute(), getRandomAttribute());
        Player playerB = new Player("Warrior B", getRandomAttribute(), getRandomAttribute(), getRandomAttribute());

        // Print initial attributes
        System.out.println("Initial attributes:");
        System.out.println(playerA.getName() + ": Health=" + playerA.getHealth() + ", Strength=" + playerA.getStrength() + ", Attack=" + playerA.getAttack());
        System.out.println(playerB.getName() + ": Health=" + playerB.getHealth() + ", Strength=" + playerB.getStrength() + ", Attack=" + playerB.getAttack());
        System.out.println();

        // Player with lower health attacks first
        Player attacker = playerA.getHealth() < playerB.getHealth() ? playerA : playerB;
        Player defender = attacker == playerA ? playerB : playerA;

        int round = 1;

        while (playerA.getHealth() > 0 && playerB.getHealth() > 0) {
            int attackDice = attacker.rollDice();
            int defendDice = defender.rollDice();

            int damage = attacker.calculateDamage(attackDice);
            int defense = defender.calculateDefense(defendDice);
            int actualDamage = Math.max(damage - defense, 0);

            defender.reduceHealth(actualDamage);

            System.out.println("Round " + round + ":");
            System.out.println(attacker.getName() + " attacks with " + attackDice + "!");
            System.out.println(defender.getName() + " defends with " + defendDice + "!");
            System.out.println("Damage Dealt: " + damage);
            System.out.println("Defense Value: " + defense);
            System.out.println("Actual Damage Dealt: " + actualDamage);
            System.out.println("Defender's Health: " + defender.getHealth());
            System.out.println();

            // Swap attacker and defender for next turn
            Player temp = attacker;
            attacker = defender;
            defender = temp;

            round++;
        }

        // Determine the winner
        Player winner = playerA.getHealth() > 0 ? playerA : playerB;
        System.out.println("Player " + winner.getName() + " wins the game!");
    }

    private static int getRandomAttribute() {
        Random rand = new Random();
        return rand.nextInt(51) + 50; // Generating random number between 50 and 100
    }
}
