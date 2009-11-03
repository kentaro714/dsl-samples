package kentaro714.model.fsm;

import kentaro714.model.fsm.CommandChannel;
import kentaro714.model.fsm.State;
import kentaro714.model.fsm.StateMachine;

public class Controller {
	private State currentState;
	public State getCurrentState() {
		return currentState;
	}

	public void setCurrentState(State currentState) {
		this.currentState = currentState;
	}

	private StateMachine machine;
	
	protected CommandChannel commandsChannel;
	
	public CommandChannel getCommandChannel() {
		return commandsChannel;
	}
	
	public void handle(String eventCode) {
		if (currentState.hasTransition(eventCode)) {
			
		}
	}
}
