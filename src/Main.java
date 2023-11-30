import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Character.Ally char1 = new Character.Ally("Maurice",100, 25,20, 15);
        Character.Ally char2 = new Character.Ally("Jorash", 100, 25,20, 15);
        Character.Ally char3 = new Character.Ally("Van", 200, 25,25, 20);
        Character.Ally char4 = new Character.Ally("Slamm", 1, 1,1, 1);

        Character.Enemy enemyTest = new Character.Enemy("Ugang", 1, 1,1, 16);
        Character.Enemy enemyTest2 = new Character.Enemy("Big Mom", 1, 1,1, 16);
        List<Character> characters = new ArrayList<>();
        characters.add(char1);
        characters.add(char2);
        characters.add(char3);
        characters.add(char4);
        characters.add(enemyTest);
        characters.add(enemyTest2);

        BattleSystem bs = new BattleSystem(characters);
    }
}