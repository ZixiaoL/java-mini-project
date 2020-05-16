public class Barn<T extends Animal> extends Repository<T> {

    public Barn(ArrayCreator<T> barnArrayCreator, int size) {
	super(barnArrayCreator, size);
    }

    @Override public boolean add(T value) {
	return super.add(value);
    }

}
