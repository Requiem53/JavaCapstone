import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class State {

     protected BattleSystem bs;
     protected String option;
     protected Scanner sc;

     public abstract void Start();

     protected void newTurn(){
          bs.dequeue();
          bs.setState(new Turn(bs));
     }

     protected void deadValidate(){
          if(bs.currChar().isAlive()){
               System.out.println(bs.currChar() + " is already dead!");
               newTurn();
          }
     }

     public State(BattleSystem bs){
          this.bs = bs;
          sc = new Scanner(System.in);
     }

     public BattleSystem getBs() {
          return bs;
     }

     public Character getTargeted(int target){
          return bs.getEnemies().get(target-1);
     }

     //States
     public static class BattleStart extends State{
          public BattleStart(BattleSystem bs) {
               super(bs);
          }

          @Override
          public void Start() {
               bs.initializeBattle();
               System.out.println("The battle is starting....");
               bs.setState(new Turn(bs));
          }
     }


     public static class Turn extends State{
          public Turn(BattleSystem bs) {
               super(bs);
          }

          @Override
          public void Start() {
               boolean victory = false;
               boolean defeat = false;

               for(Character enemy : bs.getEnemies()){
                    if(enemy.isAlive()){
                         victory = false;
                         break;
                    }
                    victory = true;
               }

               for(Character ally : bs.getAllies()){
                    if(ally.isAlive()){
                         defeat = false;
                         break;
                    }
                    defeat = true;
               }

               if(victory){
                    System.out.println("You have won the battle!");
                    //Add new battle diri ug file handling para record sa number of battles won niya sa
                    //characters gigamit
                    return;
               }

               if(defeat){
                    System.out.println("You have lost the battle!");
                    //Add new battle diri ug file handling para record sa number of battles won niya sa
                    //characters gigamit
                    return;
               }

               if(!bs.currChar().isAlive()){
                    deadValidate();
               }else {
                    if(!(bs.currChar() instanceof Character.Enemy)){
                         bs.setState(new PlayerTurn(bs));
                    } else {
                         bs.setState(new EnemyTurn(bs));
                    }
               }
          }
     }

     public static class PlayerTurn extends State{
          public PlayerTurn(BattleSystem bs) {
               super(bs);
          }

          @Override
          public void Start() {
               System.out.println("What will " + bs.currChar() + " do?");
               option = sc.nextLine();
               switch (option){
                    case "Exit":
                         break;
                    case "View Order":
                         System.out.println(bs.getTurnOrder());
                         newTurn();
                         break;
                    case "List Allies":
                         System.out.println("Allies: ");
                         for(Character chara : bs.getAllies()){
                              System.out.println(chara);
                         }

                         System.out.println("Enemies: ");
                         for(Character chara : bs.getEnemies()){
                              System.out.println(chara);
                         }
                         newTurn();
                         break;
                    case "Details":
                         for(Character chara : bs.getCharacters()){
                              chara.currentDetails();
                         }
                         newTurn();
                         break;
                    case "Attack":
                         System.out.println("Attack who?");
                         int list = 1;
                         int target;

                         for(Character enemy : bs.getEnemies()){
                              if(enemy.isAlive()){
                                   System.out.println(list + ". " + enemy);
                                   list++;
                              }
                         }
                         System.out.println("Enter number: ");
                         target = sc.nextInt();

                         System.out.println(bs.currChar() + " dealt " +
                                 bs.currChar().attack(getTargeted(target)) + " damage to " +
                                 getTargeted(target) + "! ");

                         newTurn();
                         break;
                    default:
                         newTurn();
                         break;
               }
          }
     }

     public static class EnemyTurn extends State{
          public EnemyTurn(BattleSystem bs) {
               super(bs);
          }

          @Override
          public void Start() {
               System.out.println(bs.currChar() + " is wondering about what they " +
                       "will do next....");
               newTurn();
          }
     }

}
