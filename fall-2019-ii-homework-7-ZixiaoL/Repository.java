import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicReferenceArray;

public class Repository<T> {

    private final AtomicReferenceArray<T> array;
    private final AtomicReference<Integer> arraySize;
    private final ArrayCreator<T> repositoryArrayCreator;

    public Repository(ArrayCreator<T> repositoryArrayCreator, int size) {
	this.repositoryArrayCreator = repositoryArrayCreator;
	this.array = repositoryArrayCreator.create(size);
	this.arraySize = new AtomicReference<>(0);
    }

    public boolean add(T value) {
	if(value == null) {
	    return false;
	}
	if(contains(value)) {
	    return false;
	}
	if(arraySize.get() == array.length()) {
	    return false;
	}
	array.set(arraySize.get(), value);
	arraySize.set(arraySize.get()+1);
	return true;
    }

    public boolean contains(T value) {
	if(value == null) {
	    return false;
	}
	for(int i = 0; i < arraySize.get(); i++) {
	    if(array.get(i).equals(value)) {
		return true;
	    }
	}
	return false;
    }

    public boolean remove(T value) {
	if(value == null) {
	    return false;
	}
	for(int i = 0; i < arraySize.get(); i++) {
	    if(array.get(i).equals(value)) {
		for(int j = i; j < arraySize.get()-1; j++) {
		    array.set(j, array.get(j+1));
		}
		arraySize.set(arraySize.get()-1);
		return true;
	    }
	}
	return false;
    }

    public T get(int index) {
	if(index < 0 || index >= arraySize.get()) {
	    return null;
	}
	return array.get(index);
    }

    public int size() {
	return arraySize.get();
    }

}
