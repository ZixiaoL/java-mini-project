public class BarnTransfer {

    //Instructions are really obscure. Not sure to "transfer" or "exchange". Wrote 2 mothods here just in case.
    //Transfer
    public <T extends Animal> void transfer(Barn<? extends T> barn_1, Barn<T> barn_2) {
	for(int i = 0; i < barn_1.size(); i++) {
	    T value = barn_1.get(i);
	    if(!barn_2.add(value)) {
		System.out.printf("Can't transfer %s because it already exists in target barn.%n", barn_1.get(i).getName());
	    }
	}
    }


    //Exchange
    public void exchange(Barn<? extends Animal> barn_1, Barn<? extends Animal> barn_2) {
	exchangeHelper(barn_1,barn_2);
    }

    @SuppressWarnings("unchecked")
    public <T extends Animal, S extends Animal> void exchangeHelper(Barn<T> barn_1, Barn<S> barn_2) {
	for(int i = 0; i < barn_1.size(); i++) {
	    try {
	    	S value_1 = (S) barn_1.get(i);
	    	if(!barn_2.add(value_1)) {
		    System.out.printf("Can't transfer %s because it already exists in target barn.%n", barn_1.get(i).getName());
	    	}
	    }catch(Exception e) {
		System.out.printf("Can't transfer %s because target barn cannot store this type of animal.%n", barn_1.get(i).getName());
	    }
	}
        for(int i = 0; i < barn_2.size(); i++) {
            try {
                T value_2 = (T) barn_2.get(i);
                if(!barn_1.add(value_2)) {
                    System.out.printf("Can't transfer %s because it already exists in target barn.%n", barn_2.get(i).getName());
                }
            }catch(Exception e) {
                System.out.printf("Can't transfer %s because target barn cannot store this type of animal.%n", barn_2.get(i).getName());
            }
        }
    }



}
