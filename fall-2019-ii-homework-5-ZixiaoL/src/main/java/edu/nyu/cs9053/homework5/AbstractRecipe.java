package edu.nyu.cs9053.homework5;

public abstract class AbstractRecipe implements Recipe {

    protected final double[] remainingSecondsUntilDone = new double[1];

    @Override public Double getRemainingSecondsUntilDone() {
        return remainingSecondsUntilDone[0];
    }

    @Override public boolean isRecipeDone() {
	if(remainingSecondsUntilDone[0] <= 0) {
	    return true;
	}
	return false;
    }

}
