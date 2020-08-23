package fun.robot;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

public class InstructionTest {

    @Test
    public void testSuccessfulParse() {
        String toParse = "LRFLR";
        List<Instruction> instructions = Instruction.parse(toParse);
        String str = instructions.stream().map(Instruction::name).collect(Collectors.joining());
        Assert.assertEquals(toParse, str);
    }

    @Test(expected = RuntimeException.class)
    public void testFailure() {
        String toParse = "LRAFLR";
        Instruction.parse(toParse);
    }

}
