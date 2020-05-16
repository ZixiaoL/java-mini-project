package edu.nyu.cs9053.homework6;

import java.util.*;

public class ConcreteWireDiagram implements WireDiagram {

    private final List<Wire> tripWires;
    private final Toolbox toolbox;

    //I pass the toolbox into the constructer to save memory
    public ConcreteWireDiagram(List<Wire> tripWires, Toolbox toolbox) {
	this.tripWires = tripWires;
	this.toolbox = toolbox;
    }

    @Override public TripWire getTripWires() {
	Wire blueWire = null;
	Wire redWire = null;
	//get any one trip wire
	Wire wire = tripWires.get(0);
	WireColor color = null;
	//need a try-catch here since overrided method does not throw any exceptions
	try {
	    color = toolbox.getColor(wire);
	}catch(ToolMisuseException e) {
	    System.out.println(e.getMessage());
	}
	//test the wire is blue or red, if blue, construct a tripWire object with redWire = null, and blueWire = null otherwise
	if(color == WireColor.Red) {
	    redWire = wire;
	}
	else {
	    blueWire = wire;
	}
	//remove the chosen trip wire since it'll be cut off
	tripWires.remove(0);
	TripWire tripWire = new TripWire(blueWire, redWire);
	return tripWire;
    }

    //test whether all trip wires have been removed
    public boolean allTripWiresRemoved() {
	return tripWires.isEmpty();
    }

}

