package kentaro714.model.dn;

import java.util.Date;

public interface DependencyNode {
	boolean modifiedSince(Date arg);
	void invoke();
}
