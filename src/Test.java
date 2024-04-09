import Channels.BinarySymmetricChannel;
import Test.*;

public class Test {

    public static void main(String[] args) {
        BlockDiagram b = new BlockDiagram();
        b.addComponentPair(new TestComponentT(), new TestComponentR());
        b.setChannel(new BinarySymmetricChannel(0.1));

        b.simulate(new TestSource(), new TestDestination());
    }
}
