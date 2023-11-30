import java.util.Scanner;

public abstract class State {

     protected BattleSystem bs;
     protected String option;
     protected Scanner sc;

     protected Character getCurrChar(){
          return bs.getCharacters().get(bs.getCurrentTurn());
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
               bs.setState(new Turn(bs));
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
                    case "Details":
                         for(Character chara : bs.getCharacters()){
                              chara.currentDetails();
                         }
                         bs.setState(new Turn(bs));
                         break;
                    default:
                         bs.setState(new Turn(bs));
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
               bs.setState(new Turn(bs));
          }
     }

}
