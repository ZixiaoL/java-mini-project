public class Cow implements Animal {

    private final String name;

    public Cow(String name) {
	this.name = name;
    }

    @Override public String getName() {
	return name;
    }

    @Override public boolean equals(Object o) {
	if(this == o) {
	    return true;
	}
	if(o == null || getClass() != o.getClass()) {
	    return false;
	}

	Cow that = (Cow) o;

	if(name != null ? !name.equals(that.name) : that.name != null) {
	    return false;
	}
	return true;

    }

    @Override public int hashCode() {
	int result = name != null ? name.hashCode() : 0;
	return result;
    }

}
