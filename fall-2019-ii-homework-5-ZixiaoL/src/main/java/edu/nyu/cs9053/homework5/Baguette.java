package edu.nyu.cs9053.homework5;

public class Baguette extends AbstractRecipe implements Recipe {

    private static final int DEFAULT_BAGUETTE_VOLUME_CUBIC_INCHES = 2000;
    
    private final double[] lambdaForBaguette = new double[1];


    @Override public void initializeFromOven(Oven oven){
	if(oven != null){
	    lambdaForBaguette[0] = 150 / oven.getSetTemperature();
            remainingSecondsUntilDone[0] = Math.log(10000) / lambdaForBaguette[0];
	}
    }



    /**

     * @return the food's volume in cubic inches

     */
    @Override public int getVolumeCubicInches() {
        return DEFAULT_BAGUETTE_VOLUME_CUBIC_INCHES;
    }




    /**

     * @return the remaining seconds until the recipe is done. If 0, this recipe is complete and should

     *         be removed from the {@linkplain Oven}

     */


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
                    remainingSecondsUntilDone[0] = (Math.log(10000) - lambdaForBaguette[0] * amount * 60) / 150 * ovenTemperature;
                    break;
                case Seconds:
                    remainingSecondsUntilDone[0] = (Math.log(10000) - lambdaForBaguette[0] * amount) / 150 * ovenTemperature;
                    break;
		default:
		
            }
	lambdaForBaguette[0] = 150 / ovenTemperature;
	}
    }



    /**

     * @return true if the recipe is done and should be removed from the oven

     */

    @Override public boolean isRecipeDone() {
        if(remainingSecondsUntilDone[0] <= 15) {
            return true;
        }
        return false;
    }


}

