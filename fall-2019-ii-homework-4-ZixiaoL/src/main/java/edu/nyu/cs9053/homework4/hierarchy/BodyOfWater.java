package edu.nyu.cs9053.homework4.hierarchy;

public abstract class BodyOfWater {

    private final String name;

    private final double volumn;

    protected BodyOfWater(String name, double volume) {
	this.name = name;
	this.volume = volumn;
    }

    public String getName() {
	return name;
    }

    public double getVolumn() {
	return volumn;
    }

}
