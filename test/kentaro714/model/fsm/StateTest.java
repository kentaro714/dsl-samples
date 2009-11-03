package kentaro714.model.fsm;

import org.junit.Test;


public class StateTest {
	
	@Test
	public void basicTransitionTest() {
		Event event = new Event("SampleEvent", "SAMPLE");
		State source = new State();
		State target = new State();
		
		source.addTransition(event, target);
	}
}
