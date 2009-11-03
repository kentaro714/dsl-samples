package kentaro714.model.statemachine;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class State {
	private String name;

	private List<Command> actions = new ArrayList<Command>();
	private Map<String, Transition> transitions = new HashMap<String, Transition>();
	
	public State(String name) {
		this.name = name;
	}

	public void addTransition(Event event, State targetState) {
		transitions.put(event.getCode(), new Transition(this, event, targetState));
	}
	
	public void addAction(Command command) {
		actions.add(command);
	}

	public void executeActions(CommandChannel commandsChannel) {
		for (Command command : actions) {
			commandsChannel.send(command.getCode());
		}
	}
	
	public Collection<State> getAllTargets() {
		List<State> result = new ArrayList<State>();
		for (Transition t : transitions.values()) {
			result.add(t.getTarget());
		}
		return result;
	}

	public boolean hasTransition(String eventCode) {
		return transitions.containsKey(eventCode);
	}
	
	public State targetState(String eventCode) {
		return transitions.get(eventCode).getTarget();
	}

	public String getName() {
		return name;
	}

	public List<Command> getActions() {
		return Collections.unmodifiableList(actions);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		State other = (State) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
}
