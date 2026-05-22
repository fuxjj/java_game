public class OvershootEnd implements EndRule {
    @Override
    public int apply(Player player, int roll) {
        int maxIndex = player.getTrack().size() - 1;
        int newIndex = Math.min(player.getTrackIndex() + roll, maxIndex);
        return player.getTrack().get(newIndex);
    }
}
