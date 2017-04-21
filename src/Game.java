import java.util.Random;
import java.util.Scanner;

/**
 * Created by Daniel Torres on 4/6/2017.
 */
public class Game {

    //Declare all my instance variables
    private Scanner scanner;
    private Random random;
    private Player player;
    public Game(){ //Constructor
        scanner = new Scanner(System.in);
        random = new Random();
    }


    public void start() {
        System.out.println("Hello player! Welcome to the Gauntlet!\n" +
                "Please choose your class.");
        System.out.println();
        while(true) {
            System.out.println("1.Warrior \n" +
                    "2.Wizard \n" +
                    "3.Archer");
            String choice = scanner.next();
            System.out.println();
            if (choice.equals("1")) {
                player = new Player(Player.WARRIOR);
                player.printBeginStats();
            } else if (choice.equals("2")) {
                player = new Player(Player.WIZARD);
                player.printBeginStats();
            } else if (choice.equals("3")) {
                player = new Player(Player.ARCHER);
                player.printBeginStats();
            }
            System.out.println();
            System.out.println("Are you okay with this load-out?");
            System.out.println("1. Yes");
            System.out.println("2. No");
            choice = scanner.next();
            if (choice.equals("1")){
                break;
            }
        }
        System.out.println("Welcome to the deep dark dungeon, you must defeat the evil Orc and retrieve your stolen prized pocesion, your gauntlet, Good Luck! ");
        System.out.println();
        gotoLevel1();
        gotoLevel2();
        gotoLevel3();
    }

    private void gotoLevel1() {
        System.out.println("=================================");
        System.out.println("YOU HAVE ENTERED THE FIRST LEVEL.");
        System.out.println("=================================");
        System.out.println();
        int room = 1;
        while(room <= 3){
            System.out.println("===================================");
            System.out.println("         YOU ENTER ROOM "+ room);
            System.out.println("===================================");
            battle(2, Enemy.GOBLIN);
            room++;
        }
    }

    private void gotoLevel2(){
        System.out.println("=================================");
        System.out.println("YOU HAVE ENTERED THE SECOND LEVEL");
        System.out.println("=================================");
        System.out.println();
        int room = 1;
        while (room <= 4){
            System.out.println("===================================");
            System.out.println("         YOU ENTER ROOM "+ room);
            System.out.println("===================================");
            battle(3, Enemy.GOBLIN);
            room++;
        }
    }

    private void gotoLevel3(){
        System.out.println("=================================");
        System.out.println("YOU HAVE ENTERED THE THIRD LEVEL");
        System.out.println("           BOSS FIGHT           ");
        System.out.println("=================================");
        System.out.println();
        battle(1, Enemy.BOSS);
    }

    /**
     * This function is only used for battling enemies.
     * @param enemiesToBattle the number of enemies the player has to fight
     */
    private void battle(int enemiesToBattle, int enemyType) {
        System.out.println("You enter a room with " + enemiesToBattle + " enemies in it!");
        int count = 0;
        while(count < enemiesToBattle){

            boolean battling = true;
            boolean playerTurn = random.nextBoolean();
            Enemy enemy = new Enemy(enemyType);
            while (battling) {

                if (playerTurn) {
                    boolean stillChoosing = true;
                    while (stillChoosing) {
                        player.printBattleStats();
                        System.out.println();
                        System.out.println("Enemy Health: " + enemy.getHealth());
                        System.out.println();
                        System.out.println("What do you do?");
                        System.out.println();
                        System.out.println("1. Attack");
                        System.out.println("2. Drink Health Potion (You have " + player.getHealthPotions() + " potions left)");
                        System.out.println("3. Drink Potion of Power (You have " + player.getPowerPotions() + " potions left)");
                        System.out.println("4. Run Away");
                        String choice = scanner.next();
                        if (choice.equals("1")) {
                            if (Math.random() < enemy.getEvasion()) {
                                System.out.println("The enemy evaded your attack!");
                            } else {
                                System.out.println("You dealt " + player.getDamage() + " damage to the enemy!");
                                enemy.takeDamage(player.getDamage());
                            }

                            if (enemy.getHealth() <= 0) {
                                battling = false;
                                System.out.println("You killed the enemy!");
                            }
                            stillChoosing = false;

                        } else if (choice.equals("2")) {
                            if (player.getHealthPotions() <= 0) {
                                System.out.println("You don't have any health potions!");
                            } else {
                                System.out.println("You drank 1 health potion.");
                                player.drinkHealthPotion();
                                player.printBattleStats();
                                stillChoosing = false;
                            }
                        } else if (choice.equals("3")) {
                            if (player.getPowerPotions() <= 0) {
                                System.out.println("You don't have any power potions!");
                            } else {
                                System.out.println("You drank 1 power potion.");
                                player.drinkPowerPotion();
                                player.printBattleStats();
                                stillChoosing = false;
                            }
                        } else if (choice.equals("4")) {
                            System.out.println();
                            System.out.println("You have fled the room and left the dungeon, congratulations, you are a wimp.");
                            System.out.println();
                            printGameOver();
                            System.exit(0);
                        }
                    }
                    System.out.println();
                    playerTurn = false;
                }

                //The enemy's turn
                else {
                    System.out.println("The enemy makes its move!");
                    if (Math.random() < player.getEvasion()){
                        System.out.println("You evaded the enemy's attack!");
                    }
                    else {
                        System.out.println("The enemy dealt " + enemy.getDamage() + " damage!");
                        player.takeDamage(enemy.getDamage());
                    }

                    if (player.getHealth() <= 0) {
                        System.out.println();
                        System.out.println("You have perished in the dungeon...");
                        System.out.println();
                        printGameOver();
                        System.exit(0);
                    }

                    System.out.println();
                    playerTurn = true;
                }
            }
            count++;
        }

        if (enemyType == Enemy.GOBLIN) {
            System.out.println("==============================");
            System.out.println("   YOU HAVE BEATEN THIS ROOM  ");
            System.out.println("YOU REGAIN SOME OF YOUR HEALTH");
            System.out.println("===============================");
            System.out.println();
            if (player.getClazz() == Player.ARCHER || player.getClazz() == Player.WIZARD) {
                player.powerDown();
                System.out.println("Your power potion effect have worn off.");
            }
            //Warrior gets health potions OR power potions OR a health upgrade BUT only once per level
            int chance = random.nextInt(3);
            if (chance == 0) {
                if (player.getNumPotions() < 2) {
                    player.giveHealthPotion();
                    System.out.println("=================================");
                    System.out.println("    YOU FOUND 1 HEALTH POTION    ");
                    System.out.println("=================================");
                } else {
                    System.out.println("You found nothing in the chest.");
                }
            } else if (chance == 1) {
                if (player.getNumPotions() < 2) {
                    player.givePowerPotions();
                    System.out.println("=================================");
                    System.out.println("    YOU FOUND 1 POWER POTION     ");
                    System.out.println("=================================");
                } else {
                    System.out.println("You found nothing in the chest.");
                }

            } else if (chance == 2) {
                if (player.getClazz() == Player.WARRIOR) {
                    player.upgradeMaxHealth();
                    System.out.println("You found a of ring healing, your max health has been upgraded.");
                }
                else if (player.getClazz() == Player.WIZARD){
                    player.upgradeDamage();
                    System.out.println("You found a power crystal for you staff.");
                }
                else if (player.getClazz() == Player.ARCHER){
                    player.upgradeEvasion();
                    System.out.println("You found a pair of light feet boots.");
                }
            }
            player.healthRegen(30);
            player.printBattleStats();
            System.out.println();
            System.out.println("Press any key and then enter when you would like to go to the next room");
            scanner.next();
        }
        else if (enemyType == Enemy.BOSS){
            System.out.println("=============================================");
            System.out.println("           YOU HAVE DEFEATED THE BOSS        ");
            System.out.println("      CONGRATULATIONS ON BEATING GAUNTLET    ");
            System.out.println("=============================================");
            System.out.println();
            System.out.println("You received 1 Gauntlet of Infinity");
        }
    }

    public void printGameOver(){
        System.out.println("===================================");
        System.out.println("             GAME OVER             ");
        System.out.println("===================================");
    }





















}

