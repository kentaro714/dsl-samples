package kentaro714.model.statemachine;

import junit.framework.Assert;
import kentaro714.model.statemachine.Command;
import kentaro714.model.statemachine.CommandChannel;
import kentaro714.model.statemachine.Controller;
import kentaro714.model.statemachine.Event;
import kentaro714.model.statemachine.State;
import kentaro714.model.statemachine.StateMachine;

import org.junit.Before;
import org.junit.Test;


public class ModelTest {
	private Event doorClosed, drawOpened, lightOn, doorOpened, panelClosed;
	private State activeState, waitingForLightState, unlockedPanelState, idle, waitingForDrawState;
	private Command unlockPanelCmd, lockDoorCmd, lockPanelCmd, unlockDoorCmd;
	private CommandChannel channel = new CommandChannel();
	private Controller con;
	private StateMachine machine;
	
	@Before
	public void setup() {
		doorClosed = new Event("doorClosed", "D1CL");
		drawOpened = new Event("drawOpened", "D2OP");
		lightOn = new Event("lightOn", "LION");
		doorOpened = new Event("doorOpened", "D1OP");
		panelClosed = new Event("panelClosed", "PNCL");
		
		unlockPanelCmd = new Command("unlockPanel", "PNUL");
		lockPanelCmd = new Command("lockPanel", "PNLK");
		lockDoorCmd = new Command("lockDoor", "D1LK");
		unlockDoorCmd = new Command("unlockDoor", "D1Ul");
		idle = new State("idle");
		activeState = new State("active");
		waitingForLightState = new State("waitingForLight");
		waitingForDrawState = new State("waitingForDraw");
		unlockedPanelState = new State("unlockedPanel");
		
		machine = new StateMachine(idle);
		
		idle.addTransition(doorClosed, activeState);
		idle.addAction(unlockDoorCmd);
		idle.addAction(lockPanelCmd);
		
		activeState.addTransition(drawOpened, waitingForLightState);
		activeState.addTransition(lightOn, waitingForDrawState);
		
		waitingForLightState.addTransition(lightOn, unlockedPanelState);
		
		waitingForDrawState.addTransition(drawOpened, unlockedPanelState);
		
		unlockedPanelState.addAction(unlockPanelCmd);
		unlockedPanelState.addAction(lockDoorCmd);
		unlockedPanelState.addTransition(panelClosed, idle);
		
		machine.addResetEvents(doorOpened);
		con = new Controller(machine, channel);
		channel.clearHistory();
	}
	
	@Test
	public void event_causes_state_change() {
		fire(doorClosed);
		assertCurrentState(activeState);
	}
	
	public void ignore_event_if_no_transition() {
		fire(drawOpened);
		assertCurrentState(idle);
	}

	//-------- Utility methods ------------
	protected void fire(Event e) {
		con.handle(e.getCode());
	}
	
	//-------- Custom Assertion method ----------
	protected void assertCurrentState(State s) {
		Assert.assertEquals(s, con.getCurrentState());
	}

}
