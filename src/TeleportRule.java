import java.util.List;

public interface TeleportRule {
    int apply(Player player, int position, List<Wormhole> wormholes);
}
