import java.util.concurrent.atomic.AtomicReferenceArray;

public interface ArrayCreator<T> {

    public AtomicReferenceArray<T> create(int size);

}
