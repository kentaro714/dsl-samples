package kentaro714.model.statemachine;

import kentaro714.model.statemachine.CommandChannel;
import kentaro714.model.statemachine.State;
import kentaro714.model.statemachine.StateMachine;

public class Controller {
	private State currentState;
	private StateMachine machine;
	protected CommandChannel commandsChannel;
	
	public Controller(StateMachine machine, CommandChannel commandChannel) {
		this.machine = machine;
		this.currentState = machine.getStart();
		this.commandsChannel = commandChannel;
	}

	public State getCurrentState() {
		return currentState;
	}

	public void setCurrentState(State currentState) {
		this.currentState = currentState;
	}

	public CommandChannel getCommandChannel() {
		return commandsChannel;
	}
	
	public void handle(String eventCode) {
		if (currentState.hasTransition(eventCode)) {
			transitionTo(currentState.targetState(eventCode));
		} else if (machine.isResetEvent(eventCode)) {
			transitionTo(machine.getStart());
		}
		// 知らないイベントは無視
	}

	private void transitionTo(State target) {
		currentState = target;
		currentState.executeActions(commandsChannel);
	}
}
