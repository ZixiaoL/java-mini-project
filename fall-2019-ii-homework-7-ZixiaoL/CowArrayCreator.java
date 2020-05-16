import java.util.concurrent.atomic.AtomicReferenceArray;

public class CowArrayCreator implements ArrayCreator<Cow> {

    @Override public AtomicReferenceArray<Cow> create(int size) {
	return new AtomicReferenceArray<Cow>(size);
    }

}
