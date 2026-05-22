import java.util.List;

public class IgnoreHit implements HitRule {

    @Override
    public int apply(Player player, int newPosition, List<Player> otherPlayers) {
        for (Player other : otherPlayers) {
            if (other.getPosition() == newPosition) {
                System.out.printf("%s hit %s at position Position %d%n", player.getName(), other.getName(), newPosition);
            }
        }
        return newPosition;
    }
}
