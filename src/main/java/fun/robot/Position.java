package fun.robot;

/**
 * Position of a Robot includes the co-ordinates and orientation
 */

public class Position {

    private Coordinates coordinates;
    private Orientation orientation;

    public Position(Coordinates coordinates, Orientation orientation) {
        this.coordinates = coordinates;
        this.orientation = orientation;
    }

    public Coordinates coordinates() {
        return coordinates;
    }

    public void coordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public Orientation orientation() {
        return orientation;
    }

    public void orientation(Orientation orientation) {
        this.orientation = orientation;
    }

    public static Position of(Coordinates coordinates, Orientation orientation) {
        return new Position(coordinates, orientation);
    }

    @Override
    public String toString() {
        return "Position{coordinates=" + coordinates + ", orientation=" + orientation + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Position position = (Position) o;

        if (!coordinates.equals(position.coordinates)) return false;
        return orientation == position.orientation;
    }

    @Override
    public int hashCode() {
        int result = coordinates.hashCode();
        result = 31 * result + orientation.hashCode();
        return result;
    }
}
