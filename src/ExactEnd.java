public class ExactEnd implements EndRule{

    @Override
    public int apply(Player player, int roll) {
        int maxIndex = player.getTrack().size() - 1;
        int newIndex = player.getTrackIndex() + roll;

        if (newIndex > maxIndex) {
            int overshoot = newIndex - maxIndex;
            System.out.printf("%s overshoots end.%n", player.getName());
            return player.getTrack().get(maxIndex - overshoot);
        }

        return player.getTrack().get(newIndex);
    }
}
