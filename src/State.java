public abstract class State {

     protected BattleSystem bs;

     public State(BattleSystem bs){
          this.bs = bs;
     }


     public BattleSystem getBs() {
          return bs;
     }

     public abstract void Start();
}
