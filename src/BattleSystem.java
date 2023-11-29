public class BattleSystem extends StateMachine{
    public BattleSystem(){
        setState(new TestState(this));
    }

}
