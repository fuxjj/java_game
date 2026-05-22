import java.util.List;

public class ForfeitHit implements HitRule {

    @Override
    public int apply(Player player, int newPosition, List<Player> otherPlayers) {
        for (Player other : otherPlayers) {
            if (other.getPosition() == newPosition) {
                System.out.printf("%s hit %s at position Position %d%n", player.getName(), other.getName(), newPosition);
                System.out.printf("%s moves from %d to %d%n", player.getName(), newPosition, player.getPosition());
                return player.getPosition();
            }
        }
        return newPosition;
    }
}
