import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class State {

     protected BattleSystem bs;
     protected String option;
     protected Scanner sc;
     protected static List<Character> allies = new ArrayList<>();
     protected static List<Character> enemies = new ArrayList<>();
     protected Character getCurrChar(){
          return bs.getCharacters().get(bs.getCurrentTurn());
     }
     protected void newTurn(){
          bs.setState(new Turn(bs));
     }

     protected int attackIndex(int target){
          return getCurrChar().attack(enemies.get(target-1));
     }

     public State(BattleSystem bs){
          this.bs = bs;
          sc = new Scanner(System.in);
     }

     public BattleSystem getBs() {
          return bs;
     }

     public abstract void Start();

     //States
     public static class BattleStart extends State{
          public BattleStart(BattleSystem bs) {
               super(bs);
          }

          @Override
          public void Start() {
               System.out.println("The battle is starting....");
               bs.turnOrder();
               for(Character chara : bs.getCharacters()){
                    if(chara instanceof Character.Enemy){
                         enemies.add(chara);
                    }else{
                         allies.add(chara);
                    }
               }
               newTurn();
          }
     }


     public static class Turn extends State{
          public Turn(BattleSystem bs) {
               super(bs);
          }

          @Override
          public void Start() {
               bs.incrementTurn();
               if(!(getCurrChar() instanceof Character.Enemy)){
                    bs.setState(new PlayerTurn(bs));
               } else {
                    bs.setState(new EnemyTurn(bs));
               }

          }
     }

     public static class PlayerTurn extends State{
          public PlayerTurn(BattleSystem bs) {
               super(bs);
          }

          @Override
          public void Start() {
               System.out.println("What will " + getCurrChar() + " do?");
               option = sc.nextLine();
               switch (option){
                    case "Exit":
                         break;
                    case "List Allies":
                         System.out.println("Allies: ");
                         for(Character chara : allies){
                              System.out.println(chara);
                         }

                         System.out.println("Enemies: ");
                         for(Character chara : enemies){
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

                         for(Character enemy : enemies){
                              System.out.println(list + ". " + enemy);
                              list++;
                         }
                         System.out.println("Enter number: ");
                         target = sc.nextInt();
                         System.out.println(getCurrChar() + " dealt " + attackIndex(target) + " damage to "
                                 + enemies.get(target-1) + "!");

                         newTurn();
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
               System.out.println(getCurrChar() + " is wondering about what they " +
                       "will do next....");
               newTurn();
          }
     }

}
