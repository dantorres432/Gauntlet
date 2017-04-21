/**
 * Created by Daniel Torres on 4/7/2017.
 */
public class Player {
    //Constant Variables
    public static final int WARRIOR = 1;
    public static final int WIZARD = 2;
    public static final int ARCHER = 3;
    private int maxHealth;
    private final int MAX_POTIONS;
    private int DEFAULT_WIZARD_DAMAGE;
    private double DEFAULT_ARCHER_EVASION;
    //Instance Variables
    private int clazz;
    private int health;
    private int damage;
    private double evasion;
    private int healthPotions;
    private int powerPotions;
    public Player(int clazz) {
        if (clazz == WARRIOR) {
            this.clazz = clazz;//starts the weakest ends the strongest
            maxHealth = 120;
            health = maxHealth;
            damage = 10;
            evasion = .40;
        } else if (clazz == WIZARD) {
            this.clazz = clazz;
            maxHealth = 100;
            health = maxHealth;
            DEFAULT_WIZARD_DAMAGE = 20;
            damage = DEFAULT_WIZARD_DAMAGE;
            evasion = .60;
        } else if (clazz == ARCHER) {
            this.clazz = clazz;
            maxHealth = 100;
            health = maxHealth;
            DEFAULT_ARCHER_EVASION = .75;
            evasion = DEFAULT_ARCHER_EVASION;
            damage = 15;
        } else {
            maxHealth = -1; //THERE WAS AN ERROR
            DEFAULT_WIZARD_DAMAGE = -1;
            DEFAULT_ARCHER_EVASION = -1;
        }
        MAX_POTIONS = 2;
        healthPotions = 1;
        powerPotions = 1;
    }

    public int getHealth(){
        return health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getMAX_POTIONS() {
        return MAX_POTIONS;
    }

    public int getClazz() {
        return clazz;
    }

    public void upgradeMaxHealth(){
        maxHealth +=25;
    }

    public int getDamage() {
        return damage;
    }

    public double getEvasion() {
        return evasion;
    }

    public int getHealthPotions() {
        return healthPotions;
    }

    public int getPowerPotions() {
        return powerPotions;
    }

    public void givePowerPotions() {
        powerPotions+=1;
    }

    public int getNumPotions(){
        return healthPotions+powerPotions;
    }

    public void healthRegen(int healthToRegen){
        health+=healthToRegen;
        if (health> maxHealth){
            health= maxHealth;
        }

    }

    public void printBeginStats(){
        System.out.println("Health: " + maxHealth);
        System.out.println("Attack: " + damage);
        System.out.println("Evade/Block: " + evasion*100 +"%");
        if (clazz == WARRIOR){
            System.out.println("Received: Steel Sword");
            System.out.println("Received: Sturdy Shield");
            System.out.println("Super Healing Potions: " + powerPotions);
        }
        else if (clazz == WIZARD){
            System.out.println("Received: 1 Staff of Novice Wizardry.");
            System.out.println("Received: 1 Spell Book.");
            System.out.println("Mana Potions: " + powerPotions);
        }
        else if (clazz == ARCHER){
            System.out.println("Received: 1 Short Bow.");
            System.out.println("Received: 1 Bronze Arrows Quiver.");
            System.out.println("Critical Hit Potions: " + powerPotions);
        }
        System.out.println("Health Potions: " + healthPotions);
    }

    public void printBattleStats(){
        System.out.println("Health: " + health);
        System.out.println("Health Potions: " + healthPotions);
        System.out.println("Power Potions: " + powerPotions);
    }

    public void takeDamage(int damageTaken){
        health -= damageTaken;
    }

    public void drinkHealthPotion() {
        healthPotions-=1;
        health+=20;
        if (health> maxHealth){
            health= maxHealth;
        }
    }

    public void drinkPowerPotion(){
        powerPotions-=1;
        if (clazz == WARRIOR){
            health+=40;
        }
        else if (clazz == WIZARD){
            evasion+=.20;
        }
        else if (clazz == ARCHER){
            damage+=20;
        }
    }

    public void powerDown() {
        if (clazz == WIZARD){
            damage = DEFAULT_WIZARD_DAMAGE;
        }
        else if (clazz == ARCHER){
            evasion = DEFAULT_ARCHER_EVASION;
        }
    }

    public void giveHealthPotion() {
        healthPotions+=1;
    }

    public void upgradeDamage() {
        damage+=5;
    }

    public void upgradeEvasion() {
        evasion+=.03;
    }
}
