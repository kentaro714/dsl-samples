package kentaro714.model.dn;

import java.util.List;

import com.google.common.collect.Lists;

import kentaro714.model.dn.Environment.Keys;

public abstract class TimeCalculator {
	
	protected SubstanceElement substance;
	
	public SubstanceElement getSubstance() {
		return substance;
	}
	
	protected TimeCalculator(SubstanceElement substance) {
		this.substance = substance;
	}
	
	public abstract int getValue();

	public List<? extends Keys> getEnvironmentReferences() {
		return Lists.newArrayList();
	}

}
