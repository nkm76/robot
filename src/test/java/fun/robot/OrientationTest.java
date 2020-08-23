package fun.robot;

import org.junit.Assert;
import org.junit.Test;

public class OrientationTest {

    @Test
    public void testLeft() {
        Assert.assertEquals(Orientation.W, Orientation.N.left());
        Assert.assertEquals(Orientation.N, Orientation.E.left());
        Assert.assertEquals(Orientation.E, Orientation.S.left());
        Assert.assertEquals(Orientation.S, Orientation.W.left());
    }

    @Test
    public void testRight() {
        Assert.assertEquals(Orientation.E, Orientation.N.right());
        Assert.assertEquals(Orientation.S, Orientation.E.right());
        Assert.assertEquals(Orientation.W, Orientation.S.right());
        Assert.assertEquals(Orientation.N, Orientation.W.right());
    }

}
