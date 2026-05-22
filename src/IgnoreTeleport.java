import java.util.List;

public class IgnoreTeleport implements TeleportRule{

    @Override
    public int apply(Player player, int position, List<Wormhole> wormholes) {
        return position;
    }
}
