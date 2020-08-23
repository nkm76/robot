package fun.robot;


/**
 * <pre>
 *     N
 *  W -|- E
 *     S
 * </pre>
 */

public enum Orientation {

    N, E, S, W;

    public Orientation left() {
        int ordinal = ordinal() == 0 ? 3 : ordinal() - 1;
        return Orientation.values()[ordinal];
    }

    public Orientation right() {
        int ordinal = ordinal() == 3 ? 0 : ordinal() + 1;
        return Orientation.values()[ordinal];
    }

}
