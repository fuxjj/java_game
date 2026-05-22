public class FixedDiceShaker implements DiceShaker {
    private final int[] rolls;
    private int index;

    public FixedDiceShaker(int... rolls) {
        this.rolls = rolls;
    }

    @Override
    public boolean hasNext() {
        return index < rolls.length;
    }

    @Override
    public int next() {

        return rolls[index++];
    }
}
