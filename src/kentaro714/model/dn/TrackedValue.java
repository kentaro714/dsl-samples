package kentaro714.model.dn;

import java.util.Date;

public class TrackedValue<T> implements DependencyNode {

	private T value;
	private static Date INITIAL_TIME = new Date(0L);
	private Date lastModified = INITIAL_TIME;
	
	public T getValue() {
		if (wasNeverSet()) {
			throw new RuntimeException();
		}
		return value;
	}
	
	public void setValue(T value) {
		lastModified = new Date();
		this.value = value;
	}
	
	private boolean wasNeverSet() {
		return lastModified == INITIAL_TIME;
	}

	@Override
	public void invoke() {
	}

	@Override
	public boolean modifiedSince(Date arg) {
		return lastModified.after(arg);
	}

}
