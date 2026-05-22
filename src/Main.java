import javax.sound.midi.Track;
import java.util.List;


public class Main {
    public static void main(String[] args) {

        Board board = new Board( 5,5);
        Board largeBoard = new Board(6, 6);

        List<Player> fourPlayers = List.of(
                new Player("Red", TrackBuilder.buildLargeRedTrack()),
                new Player("Blue", TrackBuilder.buildLargeBlueTrack()),
                new Player("Yellow", TrackBuilder.buildLargeYellowTrack()),
                new Player("Green", TrackBuilder.buildLargeGreenTrack())
        );

        List<Wormhole> wormholes = List.of(
                new Wormhole(4, 9),
                new Wormhole(19, 23)
        );

        System.out.println("----- Random 2 dice -----");
        Game game1 = new Game(
                board,
                List.of(new Player("Red", TrackBuilder.buildSmallRedTrack()), new Player("Blue", TrackBuilder.buildSmallBlueTrack())),
                new RandomDoubleDiceShaker(),
                new OvershootEnd(),
                new IgnoreHit(),
                new IgnoreTeleport(),
                List.of()
        );
        game1.start();

        System.out.println("----- Random 1 dice -----");
        Game game2 = new Game(
                board,
                List.of(new Player("Red", TrackBuilder.buildSmallRedTrack()), new Player("Blue", TrackBuilder.buildSmallBlueTrack())),
                new RandomSingleDiceShaker(),
                new OvershootEnd(),
                new IgnoreHit(),
                new IgnoreTeleport(),
                List.of()
        );
        game2.start();

        System.out.println("----- Basic overshoot game -----");
        Game game3 = new Game(
                board,
                List.of(new Player("Red", TrackBuilder.buildSmallRedTrack()), new Player("Blue", TrackBuilder.buildSmallBlueTrack())),
                new FixedDiceShaker(12, 10, 12),
                new OvershootEnd(),
                new IgnoreHit(),
                new IgnoreTeleport(),
                wormholes
        );
        game3.start();

        System.out.println("----- Exact end game -----");
        Game game4 = new Game(
                board,
                List.of(new Player("Red", TrackBuilder.buildSmallRedTrack()), new Player("Blue", TrackBuilder.buildSmallBlueTrack())),
                new FixedDiceShaker(9, 10, 10, 9, 12, 7, 2, 2),
                new ExactEnd(),
                new IgnoreHit(),
                new IgnoreTeleport(),
                wormholes
        );
        game4.start();

        System.out.println("----- Ignore hit -----");
        Game game5 = new Game(
                board,
                List.of(new Player("Red", TrackBuilder.buildSmallRedTrack()), new Player("Blue", TrackBuilder.buildSmallBlueTrack())),
                new FixedDiceShaker(12, 6, 6, 12, 5 ,6),
                new OvershootEnd(),
                new IgnoreHit(),
                new IgnoreTeleport(),
                wormholes
        );
        game5.start();

        System.out.println("----- Forfeit hit -----");
        Game game6 = new Game(
                board,
                List.of(new Player("Red", TrackBuilder.buildSmallRedTrack()), new Player("Blue", TrackBuilder.buildSmallBlueTrack())),
                new FixedDiceShaker(12, 6 ,6 ,12, 5, 6),
                new OvershootEnd(),
                new ForfeitHit(),
                new IgnoreTeleport(),
                wormholes
        );
        game6.start();


        System.out.println("----- Ignore teleport -----");
        Game game7 = new Game(
                board,
                List.of(new Player("Red", TrackBuilder.buildSmallRedTrack()), new Player("Blue", TrackBuilder.buildSmallBlueTrack())),
                new FixedDiceShaker(3, 2, 12, 4, 3, 10, 4, 5, 2),
                new OvershootEnd(),
                new IgnoreHit(),
                new IgnoreTeleport(),
                wormholes
        );
        game7.start();

        System.out.println("----- Teleport active -----");
        Game game8 = new Game(
                board,
                List.of(new Player("Red", TrackBuilder.buildSmallRedTrack()), new Player("Blue", TrackBuilder.buildSmallBlueTrack())),
                new FixedDiceShaker(3, 2, 12, 10, 2, 3),
                new OvershootEnd(),
                new IgnoreHit(),
                new Teleport(),
                wormholes
        );
        game8.start();

        System.out.println("----- Large board, 4 players -----");
        Game game9 = new Game(
                largeBoard,
                fourPlayers,
                new FixedDiceShaker(7, 3, 8, 5, 7, 6, 8, 7, 6, 8, 2, 4, 4, 8, 5, 7, 8, 3, 9, 9, 7, 5, 7, 9),
                new OvershootEnd(),
                new IgnoreHit(),
                new IgnoreTeleport(),
                List.of()
        );
        game9.start();
    }
}
