public class main {

    public static void main(String[] args) {
	CowArrayCreator cowArrayCreator = new CowArrayCreator();
	Barn<Cow> barn1 = new Barn<>(cowArrayCreator,5);
	Barn<Cow> barn2 = new Barn<>(cowArrayCreator,5);
	Cow cow1 = new Cow("Lily");
	Cow cow2 = new Cow("Lily");
	boolean a = barn1.add(cow1);
	boolean b = barn2.add(cow2);
	boolean c = barn1.contains(cow1);
	boolean d = barn2.contains(cow2);
	String e = barn1.get(0).getName();
	String f = barn2.get(0).getName();
	BarnTransfer trans = new BarnTransfer();
	trans.transfer(barn1,barn2);
	boolean g = barn1.remove(cow1);
	boolean h = barn2.remove(cow1);
	boolean i = barn2.remove(cow1);
	System.out.println(""+a+b+c+d+e+f+g+h+i);
    }

}
