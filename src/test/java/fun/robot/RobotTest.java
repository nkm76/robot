package fun.robot;

import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class RobotTest {

    @Test
    public void testZeroByZero() {
        Robot robot = new SimpleRobot(Coordinates.of(0, 0), Position.of(Coordinates.of(0, 0), Orientation.N), x -> true, x -> {
        });
        Assert.assertEquals(Status.FAILED, robot.instruct(Instruction.F));
        Assert.assertTrue(robot.isLost());
    }

    @Test
    public void testZeroByOne() {
        Robot robot = new SimpleRobot(Coordinates.of(0, 1), Position.of(Coordinates.of(0, 0), Orientation.N), x -> true, x -> {
        });
        Assert.assertEquals(Status.SUCCESSFUL, robot.instruct(Instruction.F));
        Assert.assertFalse(robot.isLost());
    }

    @Test
    public void test_5_3_1_1_E_RFRFRFRF() {
        Robot robot = new SimpleRobot(Coordinates.of(5, 3), Position.of(Coordinates.of(1, 1), Orientation.E), x -> true, x -> {
        });
        String instructions = "RFRFRFRF";
        Instruction.parse(instructions).stream().forEach(robot::instruct);
        Assert.assertEquals(Position.of(Coordinates.of(1, 1), Orientation.E), robot.position());
        Assert.assertFalse(robot.isLost());
    }

    @Test
    public void test_5_3_3_2_N_FRRFLLFFRRFLL() {
        Robot robot = new SimpleRobot(Coordinates.of(5, 3), Position.of(Coordinates.of(3, 2), Orientation.N), x -> true, x -> {
        });
        String instructions = "FRRFLLFFRRFLL";
        Instruction.parse(instructions).stream().forEach(robot::instruct);
        Assert.assertEquals(Position.of(Coordinates.of(3, 3), Orientation.N), robot.position());
        Assert.assertTrue(robot.isLost());
    }

    @Test
    public void test_5_3_0_3_W_LLFFFLFLFL() {
        Set<Position> scent = new HashSet<>();
        scent.add(Position.of(Coordinates.of(3, 3), Orientation.N));
        Robot robot = new SimpleRobot(Coordinates.of(5, 3), Position.of(Coordinates.of(0, 3), Orientation.W), r -> !scent.contains(r.position()), r -> scent.add(r.position()));
        String instructions = "LLFFFLFLFL";
        Instruction.parse(instructions).stream().forEach(robot::instruct);
        Assert.assertEquals(Position.of(Coordinates.of(2, 3), Orientation.S), robot.position());
        Assert.assertFalse(robot.isLost());
    }

}
