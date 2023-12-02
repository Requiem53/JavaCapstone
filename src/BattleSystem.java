import java.util.*;

public class BattleSystem extends StateMachine{
    private final List<Character> characters;

    private Queue<Character> turnOrder = new LinkedList<>();
    private List<Character> allies = new ArrayList<>();
    private List<Character> enemies = new ArrayList<>();

    public BattleSystem(List<Character> characters){
        this.characters = characters;
        setState(new State.BattleStart(this));
        initializeBattle();
    }

    public void initializeBattle(){
        Collections.sort(characters);
        turnOrder = new LinkedList<>(characters);
    }

    public Queue<Character> turnOrder(){
        if(turnOrder.isEmpty()){
            System.out.println("Current turn order: ");
            int list = 1;
            for(Character chara : turnOrder){
                System.out.println(list + ". " + chara);
                list++;
            }
        }
        return turnOrder;
    }

    public void dequeue(){
        turnOrder.remove();
    }

    public Character currChar(){
        return turnOrder.peek();
    }

    public List<Character> getAllies(){
        if(allies.isEmpty()){
            allies = new ArrayList<>();
            for(Character chara : characters){
                if(chara instanceof Character.Ally){
                    allies.add(chara);
                }
            }
        }
        return allies;
    }

    public List<Character> getEnemies(){
        if(enemies.isEmpty()){
            enemies = new ArrayList<>();
            for(Character chara : characters){
                if(chara instanceof Character.Enemy){
                    enemies.add(chara);
                }
            }
        }

        for(Character chara : characters){
            if(!chara.isAlive()){
                enemies.remove(chara);
            }
        }

        return enemies;
    }

    public List<Character> getCharacters() {
        return characters;
    }

    public Queue<Character> getTurnOrder() {
        return turnOrder;
    }
}
