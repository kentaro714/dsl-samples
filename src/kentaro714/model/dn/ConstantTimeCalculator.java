package kentaro714.model.dn;


public class ConstantTimeCalculator extends TimeCalculator {

	private int timeInHours;
	
	public ConstantTimeCalculator(int time) {
		super(null);
		this.timeInHours = time;
	}

	@Override
	public int getValue() {
		return timeInHours;
	}

}
