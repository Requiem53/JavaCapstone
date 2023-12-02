public abstract class Action {
    public abstract void execute(Character actor);

    public static class Attack extends Action implements Targeted{
        Character target;

        public Attack(Character chara){
            setTarget(chara);
        }

        @Override
        public void execute(Character actor) {
            System.out.println(actor + " dealt " + actor.attack(target) + " to the enemy");
        }

        @Override
        public void setTarget(Character chara) {
            target = chara;
        }
    }
}
