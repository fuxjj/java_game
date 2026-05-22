import java.util.List;

public class Teleport implements TeleportRule{
    @Override
    public int apply(Player player, int position, List<Wormhole> wormholes) {
        for (Wormhole wormhole : wormholes) {
            if (wormhole.contains(position)) {
                int otherEnd = wormhole.getOtherEnd(position);
                System.out.printf("%s is teleported.%n", player.getName());
                System.out.printf("%s moves from %d to %d%n", player.getName(), position, otherEnd);
                return otherEnd;
            }
        }
        return position;
    }
}
