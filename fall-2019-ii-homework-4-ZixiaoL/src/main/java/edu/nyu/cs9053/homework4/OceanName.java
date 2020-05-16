package edu.nyu.cs9053.homework4;

public enum OceanName {

    AtlanticOcean(106400000),

    ArcticOcean(14056000),

    IndianOcean(28350000),

    PacificOcean(181344000),

    SouthernOcean(20327000);

    private final double area;

    private OceanName(double area) {
	this.area = area;
    }

    public double getArea() {
	return area;
    }

    public static void main(String [] args) {

        for (String argument : args) {
            OceanName oceanName = OceanName.valueOf(OceanName.class, argument);
            System.out.println(oceanName.getArea() + " squared km");
        }

    }

}
