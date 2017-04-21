/**
 * Created by Daniel Torres on 4/7/2017.
 */
public class Enemy {
    public static final int GOBLIN = 1;
    public static final int BOSS = 2;
    private int type;
    private int health;
    private int damage;
    private double evasion;

    public Enemy(int type){
        if (type == GOBLIN){
            this.type = type;
            health = 30;
            damage = 15;
            evasion = 0.10;
        }
        else if (type == BOSS){
            this.type = type;
            health = 150;
            damage = 20;
            evasion = 0;
        }
    }

    public int getHealth(){
        return health;
    }

    public int getDamage(){
        return damage;
    }

    public double getEvasion(){
        return evasion;
    }

    public void takeDamage(int damageTaken){
        health -= damageTaken;
    }
}
