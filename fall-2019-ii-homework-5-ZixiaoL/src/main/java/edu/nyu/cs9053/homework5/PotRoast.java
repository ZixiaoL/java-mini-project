package edu.nyu.cs9053.homework5;

public class PotRoast extends AbstractRecipe implements Recipe {

    private static final int DEFAULT_POT_ROAST_VOLUME_CUBIC_INCHES = 10000;

    private static final double TIME_DEVIDED_BY_OVEN_TEMPERATURE_FOR_POT_ROAST_IN_MINUTES = 0.2;

    private final int[] formerOvenTemperature = new int[1];


    @Override public void initializeFromOven(Oven oven){
	if(oven != null){
            remainingSecondsUntilDone[0] = TIME_DEVIDED_BY_OVEN_TEMPERATURE_FOR_POT_ROAST_IN_MINUTES * oven.getSetTemperature() * 60;
            formerOvenTemperature[0] = oven.getSetTemperature();
    	}
    }



    /**

     * @return the food's volume in cubic inches

     */
    @Override public int getVolumeCubicInches() {
        return DEFAULT_POT_ROAST_VOLUME_CUBIC_INCHES;
    }


    /**

     * @return the remaining seconds until the recipe is done. If 0, this recipe is complete and should

     *         be removed from the {@linkplain Oven}

     */


    /**

     * Adjust the remaining seconds done based on the amount of update time and the current oven temperature

     * @param unit   of time which has update with the existing oven temperature

     * @param amount of time which has update with the existing oven temperature

    @override Double getRemainingSecondsUntilDone() {
        return remainingSecondsUntilDone;
    }



    /**

     * Adjust the remaining seconds done based on the amount of update time and the current oven temperature

     * @param unit   of time which has update with the existing oven temperature

     * @param amount of time which has update with the existing oven temperature

     * @param ovenTemperature the potentially new current oven temperature

     */

    @Override public void adjust(Time unit, int amount, int ovenTemperature) {
        if(unit != null){
	    switch(unit) {
                case Minutes:
                    remainingSecondsUntilDone[0] = (remainingSecondsUntilDone[0] - 60 * amount) / formerOvenTemperature[0] * ovenTemperature;
                    break;
                case Seconds:
                    remainingSecondsUntilDone[0] = (remainingSecondsUntilDone[0] - amount) / formerOvenTemperature[0] * ovenTemperature;
                    break;
		default:

            }
            formerOvenTemperature[0] = ovenTemperature;
	}
    }




}

