package edu.nyu.cs9053.homework3.oo;
import java.io.PrintStream;

/**
 * User: ZixiaoL
 */
public class Letter {

    private final char[][] asciiArt;

    public final PrintStream ps;

    public Letter(char[][] asciiArt, PrintStream ps) {
	this.asciiArt = asciiArt;
	this.ps = ps;
    }
    
    public void print(){
	for(char[] row : asciiArt){
		this.ps.println(row);
	}
    }

}

