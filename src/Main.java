import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Character char1 = new Character("Maurice");
        Character char2 = new Character("Jorash", 100, 25,20, 15);
        Character char3 = new Character("Van", 200, 25,25, 20);
        Character char4 = new Character("Slamm", 1, 1,1, 1);

        List<Character> players = new ArrayList<>();
        players.add(char1);
        players.add(char2);
        players.add(char3);
        players.add(char4);

        BattleSystem bs = new BattleSystem();

    }
}