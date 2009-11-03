package kentaro714.application.portion.domain;

import kentaro714.model.dn.TrackedValue;

public class Environment {
	public enum Keys {PHASE_OF_MOON, MINIMUM_TEMPARATURE};
	
	private TrackedValue<MoonPhases> phaseOfMoon = new TrackedValue<MoonPhases>();
	
}
