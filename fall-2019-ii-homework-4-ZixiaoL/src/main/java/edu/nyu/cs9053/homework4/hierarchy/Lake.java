package edu.nyu.cs9053.homework4.hierarchy;

public class Lake extends FreshWater{

    private final int connections;

    private final double flow;

    private final int id;

    public Lake(String name, double volumn, int connections, double flow, int id) {
        super(name, volumn);
        this.connections = connections;
        this.flow = flow;
        this.id = id;
    }

    public int getConnectedWaterBodiesCount() {
        return connections;
    }

    public double getFlow() {
        return flow;
    }

    @Override public boolean equals(Object otherObject) {
        if (this == otherObject) {
            return true;
        }
        if (otherObject == null) {
            return false;
        }
        if (getClass() != otherObject.getClass()) {
            return false;
        }

        Lake other = (Lake) otherObject;

        return id == other.id
            && connections == other.connections
            && flow == other.flow;
    }

    @Override public int hashCode() {
        return 7 * new Integer(id).hashCode()
            + 11 * new Integer(connections).hashCode()
            + 13 * new Double(flow).hashCode;
    }

}

