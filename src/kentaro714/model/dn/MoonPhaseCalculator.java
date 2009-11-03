package kentaro714.model.dn;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import kentaro714.model.dn.Environment.Keys;

public class MoonPhaseCalculator extends TimeCalculator {

	private Map<MoonPhases, Integer> times = Maps.newHashMap();
	
	protected MoonPhaseCalculator(SubstanceElement substance, int newTime, int mid, int full) {
		super(substance);
		times.put(MoonPhases.FULL, full);
		times.put(MoonPhases.MID, mid);
		times.put(MoonPhases.NEW, newTime);
	}

	@Override
	public List<? extends Keys> getEnvironmentReferences() {
		return Lists.newArrayList(Keys.PHASE_OF_MOON);
	}
	
	@Override
	public int getValue() {
		MoonPhases key = substance.getEnvironmentValue(Keys.PHASE_OF_MOON);
		return times.get(key);
	}

}
