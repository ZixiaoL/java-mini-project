package edu.nyu.cs9053.homework5;

/**
 * User: blangel
 */
public class Chef {

    private final Oven oven = new Oven(300);

    private final SousChef sousChef = new SousChef(oven);

    public static void main(String[] args) {
	// TODO - create an instance of Chef and cook the proper recipes according to the instructions.
	Chef chef = new Chef();
	PotRoast potRoast = new PotRoast();
	Baguette baguette = new Baguette();
	RoastedSweetPotato roastedSweetPotato = new RoastedSweetPotato();
	Baguette secondBaguette = new Baguette();
	Recipe[] recipes = { potRoast, baguette, roastedSweetPotato, secondBaguette};
	for(Recipe recipe : recipes) {
	    chef.prepareToCook(recipe);
	}
    }

    private void prepareToCook(Recipe recipe) {
	// TODO
	RecipeReadyCallback callback = new RecipeReadyCallback() {
	    @Override public void recipeReadyToCook(Recipe recipe){
                System.out.printf("Sous chef is preparing recipe %s...%n", recipe.getClass().getSimpleName().toLowerCase());
		recipe.initializeFromOven(oven);
		cookInOven(recipe,true);
            }
	};
	sousChef.prepare(recipe, callback);
    }

    private void cookInOven(Recipe recipe, boolean initialPutInOven) {
	// TODO
	oven.cook(recipe, new Timer(){
	    @Override public void update(Time unit, int value, int ovenTemperature) {
		recipe.adjust(unit, value, ovenTemperature);
		if(recipe.isRecipeDone()) {
		    oven.takeOut(recipe);
		}
		else {
		    cookInOven(recipe, false);
		}
	    }
	}, initialPutInOven);

    }
}
