import java.util.List;

public interface HitRule {
    int apply (Player player, int newPosition, List<Player> otherPlayers);
}
