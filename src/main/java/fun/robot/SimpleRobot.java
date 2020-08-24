package fun.robot;

import java.util.function.Consumer;
import java.util.function.Predicate;

public class SimpleRobot implements Robot {

    private final Coordinates grid;
    private final Position position;
    private boolean lost;
    private final Predicate<Robot> testBeforeMovingForward;
    private final Consumer<Robot> onLost;

    public SimpleRobot(final Coordinates grid, final Position position, final Predicate<Robot> testBeforeMovingForward, final Consumer<Robot> onLost) {
        this.grid = grid;
        this.position = position;
        this.testBeforeMovingForward = testBeforeMovingForward;
        this.onLost = onLost;
    }

    @Override
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

    @Override
    public Position position() {
        return position;
    }

    @Override
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
        if (!testBeforeMovingForward.test(this)) {
            return Status.IGNORED;
        }
        switch (position.orientation()) {
            case N:
                if (position.coordinates().y() < grid.y()) {
                    position.coordinates().y(position.coordinates().y() + 1);
                    return Status.SUCCESSFUL;
                } else {
                    lost = true;
                    onLost.accept(this);
                    return Status.FAILED;
                }
            case E:
                if (position.coordinates().x() < grid.x()) {
                    position.coordinates().x(position.coordinates().x() + 1);
                    return Status.SUCCESSFUL;
                } else {
                    lost = true;
                    onLost.accept(this);
                    return Status.FAILED;
                }
            case S:
                if (position.coordinates().y() > 0) {
                    position.coordinates().y(position.coordinates().y() - 1);
                    return Status.SUCCESSFUL;
                } else {
                    lost = true;
                    onLost.accept(this);
                    return Status.FAILED;
                }
            case W:
                if (position.coordinates().x() > 0) {
                    position.coordinates().x(position.coordinates().x() - 1);
                    return Status.SUCCESSFUL;
                } else {
                    lost = true;
                    onLost.accept(this);
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
