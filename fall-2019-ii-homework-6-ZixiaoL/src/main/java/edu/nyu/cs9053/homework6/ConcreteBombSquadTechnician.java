package edu.nyu.cs9053.homework6;

import java.util.*;

public class ConcreteBombSquadTechnician implements BombSquadTechnician {

    private final Toolbox toolbox = new Toolbox();

    @Override public WireDiagram assess(Bomb bomb) {
	Wire[] wires = bomb.getWires();
	List<Wire> tripWires = new ArrayList<Wire>();
	//need a try-catch block since the overrided method does not throw any exception
	try {
	    //get the blue and red wires which are trip wires, and store them in list tripWires
	    for(Wire wire : wires) {
	        WireColor color = toolbox.getColor(wire);
	        if(color == WireColor.Blue || color == WireColor.Red) {
		    tripWires.add(wire);
	        }
		//for each wire try to catch villain
	        Object o = toolbox.invokeMethod(wire, "callingCard");
	        if(o.getClass() != wire.getClass()) {
		    EvilVillain villain = (EvilVillain) o;
		    toolbox.setField(villain, "free", false);
	        }
	    }
	}catch(ToolMisuseException e) {
	    System.out.println(e.getMessage());
	}
	//supply all trip wires to diagram constructor, also the toolbox to save memory
	WireDiagram diagram = new ConcreteWireDiagram(tripWires, toolbox);
	return diagram;
    }

    @Override public void defuse(Bomb bomb, WireDiagram diagram) {
	ConcreteWireDiagram concreteDiagram = (ConcreteWireDiagram)diagram;
	//loop until all trip wires are removed
	while(!concreteDiagram.allTripWiresRemoved()) {
	    TripWire tripWire = concreteDiagram.getTripWires();
	    Wire blueWire = tripWire.getBlueWire();
	    Wire redWire = tripWire.getRedWire();
	    //cut the wires
	    if(blueWire != null) {
		blueWire.cut();
	    }
	    if(redWire != null) {
		redWire.cut();
	    }
	}
    }

}


