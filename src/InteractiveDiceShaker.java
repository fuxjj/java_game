import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class InteractiveDiceShaker implements DiceShaker {
    private final List<String> playerNames;
    private final String humanPlayerName;
    private final int diceCount;
    private final Scanner scanner;
    private final Random random = new Random();
    private int turnIndex;

    public InteractiveDiceShaker(List<String> playerNames, String humanPlayerName, int diceCount, Scanner scanner) {
        this.playerNames = playerNames;
        this.humanPlayerName = humanPlayerName;
        this.diceCount = diceCount;
        this.scanner = scanner;
    }

    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    public int next() {
        String playerName = playerNames.get(turnIndex % playerNames.size());
        turnIndex++;

        if (playerName.equals(humanPlayerName)) {
            return readHumanRoll(playerName);
        }

        int roll = rollRandomDice();
        System.out.printf("%s rolls %d.%n", playerName, roll);
        return roll;
    }

    private int readHumanRoll(String playerName) {
        int minimum = diceCount;
        int maximum = diceCount * 6;

        while (true) {
            System.out.printf("%s, enter your roll (%d-%d): ", playerName, minimum, maximum);
            String input = scanner.nextLine().trim();
            try {
                int roll = Integer.parseInt(input);
                if (roll >= minimum && roll <= maximum) {
                    return roll;
                }
            } catch (NumberFormatException ignored) {
                // Ask again for invalid input.
            }
            System.out.printf("Please enter a number from %d to %d.%n", minimum, maximum);
        }
    }

    private int rollRandomDice() {
        int total = 0;
        for (int die = 0; die < diceCount; die++) {
            total += random.nextInt(6) + 1;
        }
        return total;
    }
}