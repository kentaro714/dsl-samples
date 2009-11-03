package kentaro714.model.fsm;

import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.Test;

public class ControllerTest {

	@Test
	public void testBasicEvent() {
		Controller controller = new Controller();

		Event event = new Event("SampleEvent", "SAMPLE");
		State source = new State();
		State target = new State();

		source.addTransition(event, target);
		controller.setCurrentState(source);

		controller.handle("SAMPLE");

		Assert.assertEquals(controller.getCurrentState(), target);
	}

	@Test
	public void testBasicGuardClauseOKCase() {
		Controller controller = new Controller();

		Event event = new Event("SampleEvent", "SAMPLE");
		State source = new State();
		State target = new State();

		source.addTransition(event, target);
		controller.setCurrentState(source);

		controller.handle("SAMPLE");

		Assert.assertEquals(controller.getCurrentState(), target);
	}

	@Test
	public void testBasicGuardClauseResultNotExistCase() {
		Controller controller = new Controller();

		Event event = new Event("SampleEvent", "SAMPLE");
		State source = new State();
		State target = new State();

		source.addTransition(event, target);
		controller.setCurrentState(source);

		try {
			controller.handle("SAMPLE");
		} catch (RuntimeException e) {
			assertTrue(true);
		}
	}

	@Test
	public void testBasicGuardClauseCase() {
		Controller controller = new Controller();

		Event event = new Event("AcceptProcedure", "ACCEPT");
		State source = new State();
		State next = new State();
		State rejected = new State();

		source.addTransition(event, next);
		source.addTransition(event, rejected);
		controller.setCurrentState(source);

		controller.handle("ACCEPT");
		
		Assert.assertEquals(controller.getCurrentState(), next);
	}

	@Test
	public void testBasicGuardClauseCase2() {
		Controller controller = new Controller();

		Event event = new Event("AcceptProcedure", "ACCEPT");
		State source = new State();
		State next = new State();
		State rejected = new State();

		source.addTransition(event, next);
		source.addTransition(event, rejected);
		controller.setCurrentState(source);

		controller.handle("ACCEPT");
		
		Assert.assertEquals(controller.getCurrentState(), rejected);
	}
}
