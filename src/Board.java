public class Board {
    private final int rows;
    private final int columns;

    public Board(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
    }

    public int getSize() { return rows * columns; }
    public int getRows() { return rows; }
    public int getColumns() { return columns; }

    @Override
    public String toString() {
        return String.format("rows=%d columns=%d", rows, columns);
    }
}
