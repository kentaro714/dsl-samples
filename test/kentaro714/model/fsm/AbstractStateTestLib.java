package kentaro714.model.fsm;

import junit.framework.Assert;

import org.junit.Before;

abstract public class AbstractStateTestLib {
	protected CommandChannel commandChannel = new CommandChannel();
	protected StateMachine machine;
	protected Controller controller;

	//-------- Utility methods ------------
	protected void fire(Event e) {
		controller.handle(e.getCode());
	}
	
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
