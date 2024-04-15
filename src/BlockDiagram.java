import Channels.IChannel;
import Components.IComponent;
import Data.IData;
import Destinations.IDestination;
import Sources.ISource;

import java.util.LinkedList;

class ConnectionException extends RuntimeException {

    public ConnectionException(String c1, String c2) {
        super(c1 + " can not be connected to " + c2);
    }

}

public class BlockDiagram {

    protected LinkedList<IComponent> transmitterComponents;
    protected LinkedList<IComponent> receiverComponents;
    protected IChannel channel;
    protected ISource source;
    protected IDestination destination;
    protected String dumpFileName = null;

    public void setChannel(IChannel channel) {
        this.channel = channel;
    }

    public void setSource(ISource source) {
        this.source = source;
    }

    public void setDestination(IDestination destination) {
        this.destination = destination;
    }

    public void setDumpFile(String dumpFileName) {
        this.dumpFileName = dumpFileName;
    }

    public BlockDiagram() {
        transmitterComponents = new LinkedList<>();
        receiverComponents = new LinkedList<>();
    }

    public void addComponentPair(IComponent transmitterComponent, IComponent receiverComponent) {
        if ( !transmitterComponents.isEmpty() ) {
            IComponent lastComponent = transmitterComponents.getLast();
            if ( !transmitterComponent.getInputDataType().equals(lastComponent.getOutputDataType()) ){
                throw new ConnectionException(transmitterComponent.toString(), lastComponent.toString());
            }
            lastComponent = receiverComponents.getFirst();
            if ( !receiverComponent.getOutputDataType().equals(lastComponent.getInputDataType()) ){
                throw new ConnectionException(lastComponent.toString(), receiverComponent.toString());
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

    public void simulate() {
        if (transmitterComponents.isEmpty()) {
            throw new RuntimeException("Block diagram is empty");
        }
        else if ( !transmitterComponents.getLast().getOutputDataType().equals(channel.getInputDataType()) ) {
            throw new ConnectionException(transmitterComponents.getLast().toString(), channel.toString());
        }
        else if ( !receiverComponents.getFirst().getInputDataType().equals(channel.getOutputDataType()) ) {
            throw new ConnectionException(channel.toString(), receiverComponents.getFirst().toString());
        }
        else if ( !source.getDataType().equals(transmitterComponents.getFirst().getInputDataType()) ) {
            throw new ConnectionException(source.toString(), transmitterComponents.getFirst().toString());
        }
        else if ( !destination.getDataType().equals(receiverComponents.getLast().getOutputDataType()) ) {
            throw new ConnectionException(receiverComponents.getLast().toString(), destination.toString());
        }

        //System.out.println("Simulation started\n");

        IData data = source.getData();
        //printBlock("Source", data);

        for ( IComponent component : transmitterComponents ) {
            data = component.process(data);
            //printBlock(component.toString(), data);
        }

        data = channel.simulateTransmission(data);
        //printBlock(channel.toString(), data);

        for ( IComponent component : receiverComponents ) {
            data = component.process(data);
            //printBlock(component.toString(), data);
        }

        destination.putData(data);
        //printBlock("Destination", data);

        //System.out.println("\nSimulation ended");
    }
}
