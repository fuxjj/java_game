import java.util.ArrayList;
import java.util.List;

public class TrackBuilder {
    public static List<Integer> buildSmallRedTrack() {
        return buildForwardTrack(1, 25);
    }

    public static List<Integer> buildSmallBlueTrack() {
        return buildForwardTrack(25, 1);
    }

    public static List<Integer> buildLargeRedTrack() {
        return buildForwardTrack(1, 36);
    }

    public static List<Integer> buildLargeBlueTrack() {
        return List.of(31, 32, 33, 34,35,36,25,26,27,28,29,30,
                19,20,21,22,23,24,13,14,15,16,17,18,
                7,8,9,10,11,12,1,2,3,4,5,6);
    }

    public static List<Integer> buildLargeYellowTrack() {
        return buildForwardTrack(36, 1);
    }

    public static List<Integer> buildLargeGreenTrack() {
        return List.of(6,5,4,3,2,1,12,11,10,9,8,7,
                18,17,16,15,14,13,24,23,22,21,20,19,
                30,29,28,27,26,25,36,35,34,33,32,31);
    }

    private static List<Integer> buildForwardTrack(int start, int end) {
        List<Integer> track = new ArrayList<>();
        if(start < end) {
            for (int i = start; i <= end; i++) track.add(i);
        } else {
            for (int i = start; i >= end; i--) track.add(i);
        }
        return track;
    }
}
