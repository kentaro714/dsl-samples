package kentaro714.model.statemachine;

import junit.framework.Assert;
import kentaro714.model.statemachine.CommandChannel;
import kentaro714.model.statemachine.Controller;
import kentaro714.model.statemachine.Event;
import kentaro714.model.statemachine.State;
import kentaro714.model.statemachine.StateMachine;

import org.junit.Before;

abstract public class AbstractStateTestLib {
	protected CommandChannel commandChannel = new CommandChannel();
	protected StateMachine machine;
	protected Controller controller;

	//-------- Utility methods ------------
	protected void fire(Event e) {
		controller.handle(e.getCode());
	}
	
	//-------- Custom Assertion method ----------
	protected void assertCurrentState(State s) {
		Assert.assertEquals(s, controller.getCurrentState());
	}
	
	@Before
	public void setup() {
		machine = createMachine();
		controller = new Controller(machine, commandChannel);
	}

	abstract protected StateMachine createMachine();
}
