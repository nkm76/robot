package fun.robot;

public interface Robot {

    Position position();

    Status instruct(final Instruction instruction);

    boolean isLost();
}
