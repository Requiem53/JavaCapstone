import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Character char1 = new Character("Maurice");
        Character char2 = new Character("Jorash", 100, 25,20, 15, true);
        Character char3 = new Character("Van", 200, 25,25, 20, true);
        Character char4 = new Character("Slamm", 1, 1,1, 1, true);

        Character enemyTest = new Character("Ugang", 1, 1,1, 16, false);
        List<Character> characters = new ArrayList<>();
        characters.add(char1);
        characters.add(char2);
        characters.add(char3);
        characters.add(char4);
        characters.add(enemyTest);

        BattleSystem bs = new BattleSystem(characters);

    }
}