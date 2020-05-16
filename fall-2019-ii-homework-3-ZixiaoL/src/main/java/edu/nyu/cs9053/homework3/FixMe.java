package edu.nyu.cs9053.homework3;
import edu.nyu.cs9053.homework3.metadata.Processor;
/**
 * User: blangel
 */
public class FixMe {

    private final String name;

    public final String metadata;

    public FixMe(String name) {
        this.name = name;
	this.metadata = "";
    }

    public FixMe(String name, String metadata) {
        this.name = name;
        this.metadata = new Processor(true).processMetadata(metadata);
    }

    public FixMe changeName(String name) {
        return new FixMe(name);
    }

    public FixMe changeName(String firstName, String lastName) {
        return new FixMe(getName(firstName, lastName), metadata);
    }

    public String getName(String firstName, String lastName) {
        return String.format("%s %s", firstName, lastName);
    }

    public String getName() {
        return name;
    }

}
