import java.util.Scanner;

public abstract class State {

     protected BattleSystem bs;
     protected String option;
     Scanner sc;

     public State(BattleSystem bs){
          this.bs = bs;
          sc = new Scanner(System.in);
     }


     public BattleSystem getBs() {
          return bs;
     }

     public abstract void Start();
}
