import Channels.IChannel;
import Components.IComponent;
import Data.IData;
import Destinations.IDestination;
import Sources.ISource;

import java.util.LinkedList;

public class BlockDiagram {

    protected LinkedList<IComponent> transmitterComponents;
    protected LinkedList<IComponent> receiverComponents;
    protected IChannel channel;

    public void setChannel(IChannel channel) {
        this.channel = channel;
    }

    public BlockDiagram() {
        transmitterComponents = new LinkedList<>();
        receiverComponents = new LinkedList<>();
    }

    public void addComponentPair(IComponent transmitterComponent, IComponent receiverComponent) {
        if ( !transmitterComponents.isEmpty() ) {
            IComponent lastComponent = transmitterComponents.getLast();
            if ( !transmitterComponent.getInputDataType().equals(lastComponent.getOutputDataType()) ){
                throw new RuntimeException(transmitterComponent.toString() + " can not be connected to " + lastComponent.toString());
            }
        }
        transmitterComponents.addLast(transmitterComponent);
        receiverComponents.addFirst(receiverComponent);
    }

    public boolean removeComponentPair(IComponent transmitterComponent, IComponent receiverComponent) {
        return transmitterComponents.remove(transmitterComponent) && receiverComponents.remove(receiverComponent);
    }

    private static void printBlock(String name, IData data) {
        System.out.println("-----------------------------------");
        System.out.println(name);
        System.out.println("-----------------------------------");
        System.out.println("Data: " + data.toString());
    }

    public void simulate(ISource source, IDestination destination) {
        if (transmitterComponents.isEmpty()) {
            throw new RuntimeException("Block diagram is empty");
        }
        else if ( !transmitterComponents.getLast().getOutputDataType().equals(channel.getInputDataType()) ) {
            throw new RuntimeException(transmitterComponents.getLast().toString() + " can not be connected to " + channel.toString());
        }

        System.out.println("Simulation started\n");

        IData data = source.getData();
        printBlock("Source", data);

        for ( IComponent component : transmitterComponents ) {
            data = component.process(data);
            printBlock(component.toString(), data);
        }

        data = channel.simulateTransmission(data);
        printBlock(channel.toString(), data);

        for ( IComponent component : receiverComponents ) {
            data = component.process(data);
            printBlock(component.toString(), data);
        }

        destination.putData(data);
        printBlock("Destination", data);

        System.out.println("\nSimulation ended");
    }
}
