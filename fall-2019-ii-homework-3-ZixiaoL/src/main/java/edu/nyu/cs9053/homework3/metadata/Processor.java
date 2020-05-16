package edu.nyu.cs9053.homework3.metadata;

/**
 * User: blangel
 */
public class Processor {

    private final boolean strict;

    public Processor(boolean strict) {
        this.strict = strict;
    }

    public String processMetadata(String metadata) {
        return strict ? String.format("\"%d\"", metadata) : metadata;
    }

}
