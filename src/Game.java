import java.util.List;

public class Game {
    private final Board board;
    private final List<Player> players;
    private final DiceShaker diceShaker;
    private final EndRule endRule;
    private final HitRule hitRule;
    private final TeleportRule teleportRule;
    private final List<Wormhole> wormholes;
    private boolean gameOver;
    private int totalTurns;

    public Game(Board board, List<Player> players, DiceShaker diceShaker, EndRule endRule, HitRule hitRule, TeleportRule teleportRule, List<Wormhole> wormholes) {
        this.board = board;
        this.players = players;
        this.diceShaker = diceShaker;
        this.endRule = endRule;
        this.hitRule = hitRule;
        this.teleportRule = teleportRule;
        this.wormholes = wormholes;
    }

    public void start() {
        System.out.println("Board: " + board);
        printPlayerTracks();
        System.out.println("Game State: Ready");
        System.out.println();

        while (!gameOver && diceShaker.hasNext()) {
            for (Player player : players) {
                if (gameOver) break;
                takeTurn(player);
            }
        }
    }

    private void takeTurn(Player player) {
        int roll = diceShaker.next();
        player.incrementTurnCount();
        totalTurns++;

        System.out.printf("%s turn %d rolls %d%n", player.getName(), player.getTurnCount(), roll);

        int oldPosition = player.getPosition();
        int newPosition = calculateNewPosition(player, roll);

        //hitrule
        List<Player> otherPlayers = players.stream().filter(p -> p != player).toList();
        newPosition = hitRule.apply(player, newPosition, otherPlayers);

        //Wormhole
        newPosition = teleportRule.apply(player, newPosition, wormholes);

        int newIndex = player.getTrack().indexOf(newPosition);
        player.setTrackIndex(newIndex);

        System.out.printf("%s moves from %s to %s%n", player.getName(), formatPosition(player, oldPosition), formatPosition(player, newPosition));

        if(player.isAtEnd()) {
            gameOver = true;
            System.out.printf("%n%s wins in %d turns.%n", player.getName(), player.getTurnCount());
            System.out.printf("Total turns: %d.%n", totalTurns);
            System.out.println("Game State: over");
        }
    }

    private int calculateNewPosition(Player player, int roll) {
        return endRule.apply(player, roll);
    }

    private String formatPosition (Player player, int position) {
        if (position == player.getHomePosition()) {
            return String.format("Home (Position %d)", position);
        }
        if (position == player.getEndPosition()) {
            return String.format("End (Position %d)", position);
        }
        return String.valueOf(position);
    }

    private void printPlayerTracks() {
        for (Player player : players) {
            System.out.printf("%s Home (Position %d) -> End (Position %d)%n", player.getName(), player.getHomePosition(), player.getEndPosition());
        }
        System.out.println();
    }
}
