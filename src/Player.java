import java.util.List;

public class Player {
    private final String name;
    private final List<Integer> track;
    private int trackIndex;
    private int turnCount;

    public Player(String name, List<Integer> track) {
        this.name = name;
        this.track = track;
        this.trackIndex = 0;
    }

    public String getName() { return name; }
    public int getPosition() { return track.get(trackIndex); }
    public int getHomePosition() { return track.get(0); }
    public int getEndPosition() { return track.get(track.size() - 1); }
    public int getTurnCount() { return turnCount; }
    public List<Integer> getTrack() { return track; }

    public void setTrackIndex(int index) { this.trackIndex = index; }
    public int getTrackIndex() { return trackIndex; }

    public void incrementTurnCount() { turnCount++; }
    public boolean isAtEnd() { return trackIndex == track.size() - 1; }

    @Override
    public String toString() { return name; }
}
