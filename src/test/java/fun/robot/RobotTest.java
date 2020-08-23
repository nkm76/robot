package fun.robot;

import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;
import java.util.HashSet;

public class RobotTest {

    @Test
    public void testZeroByZero() {
        Robot robot = new Robot(Coordinates.of(0, 0), Position.of(Coordinates.of(0, 0), Orientation.N), new HashSet<>());
        Assert.assertEquals(Status.FAILED, robot.instruct(Instruction.F));
        Assert.assertTrue(robot.isLost());
    }

    @Test
    public void testZeroByOne() {
        Robot robot = new Robot(Coordinates.of(0, 1), Position.of(Coordinates.of(0, 0), Orientation.N), Collections.emptySet());
        Assert.assertEquals(Status.SUCCESSFUL, robot.instruct(Instruction.F));
        Assert.assertFalse(robot.isLost());
    }

    @Test
    public void test_5_3_1_1_E_RFRFRFRF() {
        Robot robot = new Robot(Coordinates.of(5, 3), Position.of(Coordinates.of(1, 1), Orientation.E), Collections.emptySet());
        String instructions = "RFRFRFRF";
        Instruction.parse(instructions).stream().forEach(robot::instruct);
        Assert.assertEquals(Position.of(Coordinates.of(1, 1), Orientation.E), robot.position());
        Assert.assertFalse(robot.isLost());
    }

    @Test
    public void test_5_3_3_2_N_FRRFLLFFRRFLL() {
        Robot robot = new Robot(Coordinates.of(5, 3), Position.of(Coordinates.of(3, 2), Orientation.N), new HashSet<>());
        String instructions = "FRRFLLFFRRFLL";
        Instruction.parse(instructions).stream().forEach(robot::instruct);
        Assert.assertEquals(Position.of(Coordinates.of(3, 3), Orientation.N), robot.position());
        Assert.assertTrue(robot.isLost());
    }

    @Test
    public void test_5_3_0_3_W_LLFFFLFLFL() {
        Robot robot = new Robot(Coordinates.of(5, 3), Position.of(Coordinates.of(0, 3), Orientation.W),
                Collections.singleton(Position.of(Coordinates.of(3, 3), Orientation.N)));
        String instructions = "LLFFFLFLFL";
        Instruction.parse(instructions).stream().forEach(robot::instruct);
        Assert.assertEquals(Position.of(Coordinates.of(2, 3), Orientation.S), robot.position());
        Assert.assertFalse(robot.isLost());
    }

}
