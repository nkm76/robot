package fun.robot;

import java.util.Set;

public class Robot {

    private final Coordinates grid;
    private final Position position;
    private boolean lost;
    private final Set<Position> scent;

    public Robot(final Coordinates grid, final Position position, final Set<Position> scent) {
        this.grid = grid;
        this.position = position;
        this.scent = scent;
    }

    public Status instruct(final Instruction instruction) {
        if (!lost) {
            switch (instruction) {
                case L:
                    return left();
                case R:
                    return right();
                case F:
                    return forward();
            }
        }
        return Status.FAILED;
    }

    private Status left() {
        position.orientation(position.orientation().left());
        return Status.SUCCESSFUL;
    }

    private Status right() {
        position.orientation(position.orientation().right());
        return Status.SUCCESSFUL;
    }

    public Position position() {
        return position;
    }

    public boolean isLost() {
        return lost;
    }

    /**
     * This method returns if the movement was successful or not.
     * If the robot falls off the grid then it returns a false
     *
     * @return Status if the movement was successful, ignored or fell off edge.
     */
    private Status forward() {
        // If some robot has left a scent
        if (scent.contains(position)) {
            return Status.IGNORED;
        }
        switch (position.orientation()) {
            case N:
                if (position.coordinates().y() < grid.y()) {
                    position.coordinates().y(position.coordinates().y() + 1);
                    return Status.SUCCESSFUL;
                } else {
                    lost = true;
                    scent.add(position);
                    return Status.FAILED;
                }
            case E:
                if (position.coordinates().x() < grid.x()) {
                    position.coordinates().x(position.coordinates().x() + 1);
                    return Status.SUCCESSFUL;
                } else {
                    lost = true;
                    scent.add(position);
                    return Status.FAILED;
                }
            case S:
                if (position.coordinates().y() > 0) {
                    position.coordinates().y(position.coordinates().y() - 1);
                    return Status.SUCCESSFUL;
                } else {
                    lost = true;
                    scent.add(position);
                    return Status.FAILED;
                }
            case W:
                if (position.coordinates().x() > 0) {
                    position.coordinates().x(position.coordinates().x() - 1);
                    return Status.SUCCESSFUL;
                } else {
                    lost = true;
                    scent.add(position);
                    return Status.FAILED;
                }
            default:
                return Status.FAILED;
        }
    }

    @Override
    public String toString() {
        return "Robot{position=" + position + ", lost=" + lost + '}';
    }
}
