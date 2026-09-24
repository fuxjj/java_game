import java.util.List;
import java.util.Scanner;

public class InteractiveMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Choose your player: Red or Blue");
        String humanPlayer = readPlayer(scanner);

        System.out.println("Choose dice: 1 or 2");
        int diceCount = readDiceCount(scanner);

        List<String> playerNames = List.of("Red", "Blue");
        List<Player> players = List.of(
                new Player("Red", TrackBuilder.buildSmallRedTrack()),
                new Player("Blue", TrackBuilder.buildSmallBlueTrack())
        );

        DiceShaker dice = new InteractiveDiceShaker(playerNames, humanPlayer, diceCount, scanner);
        Game game = new Game(
                new Board(5, 5),
                players,
                dice,
                new OvershootEnd(),
                new IgnoreHit(),
                new IgnoreTeleport(),
                List.of()
        );
        game.start();
    }

    private static String readPlayer(Scanner scanner) {
        while (true) {
            System.out.print("Player: ");
            String player = scanner.nextLine().trim();
            if (player.equalsIgnoreCase("red")) return "Red";
            if (player.equalsIgnoreCase("blue")) return "Blue";
            System.out.println("Please choose Red or Blue.");
        }
    }

    private static int readDiceCount(Scanner scanner) {
        while (true) {
            System.out.print("Dice: ");
            String input = scanner.nextLine().trim();
            if (input.equals("1") || input.equals("2")) return Integer.parseInt(input);
            System.out.println("Please choose 1 or 2.");
        }
    }
}