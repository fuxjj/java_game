import java.util.Objects;

public class Wormhole {
    private final int positionA;
    private final int positionB;

    public Wormhole(int positionA, int positionB) {
        if (positionA == positionB) {
            throw new IllegalArgumentException("Wormhole cannot connect to itself");
        }
        this.positionA = positionA;
        this.positionB = positionB;
    }

    public int getOtherEnd(int position) {
        if (position == positionA) return positionB;
        if (position == positionB) return positionA;
        return position;
    }

    public boolean contains(int position) {
        return position == positionA || position == positionB;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Wormhole that = (Wormhole) obj;
        return (positionA == that.positionA && positionB == that.positionB) || (positionA == that.positionB && positionB == that.positionA);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Math.min(positionA, positionB), Math.max(positionA, positionB));
    }

    @Override
    public String toString() {
        return String.format("Wormhole(%d <-> %d)", positionA, positionB);
    }
}
