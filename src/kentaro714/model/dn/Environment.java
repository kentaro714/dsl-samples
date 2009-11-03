package kentaro714.model.dn;


public class Environment {
	public enum Keys {PHASE_OF_MOON, MINIMUM_TEMPARATURE};
	
	// FIXME 原書のメカニズムをそのまま使うと、コーディング規約に違反する
	private TrackedValue<MoonPhases> PHASE_OF_MOON = new TrackedValue<MoonPhases>();
	
	public <T> T getValue(Keys key) {
		return this.<T>getTrackedProperty(key).getValue();
	}
	
	public <T> TrackedValue<T> getTrackedProperty(Keys key) {
		// 誰がPHASE_OF_MOONを更新する？
		
		return null;
	}
}
