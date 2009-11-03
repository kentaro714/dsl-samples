package kentaro714.model.fsm;

import kentaro714.model.fsm.CommandChannel;
import kentaro714.model.fsm.State;
import kentaro714.model.fsm.StateMachine;

public class Controller {
	private State currentState;
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
