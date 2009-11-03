package kentaro714.model.fsm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class StateMachine {
	
	private State start;
//	private Collection<Event> resetEvents = new ArrayList<Event>();
	
	public StateMachine(State start) {
		this.start = start;
	}
	
	public Collection<State> getStates() {
		List<State> result = new ArrayList<State>();
		gatherForwards(result, start);
		return result;
	}
	
	public void addResetEvents(Event... events) {
//		for (Event e : events) resetEvents.add(e);
		for (Event e : events) addResetEvent_byAddingTransitions(e);
	}
	
	private void addResetEvent_byAddingTransitions(Event e) {
		for (State s : getStates()) {
			if (!s.hasTransition(e.getCode())) s.addTransition(e, start);
		}
	}
	
	public boolean isResetEvent(String eventCode) {
//		return resetEventCodes().contains(eventCode);
		for (State s : getStates()) {
			if (s.hasTransition(eventCode) && s.targetState(eventCode).equals(start)) {
				return true;
			}
		}
		return false;
	}

//	private List<String> resetEventCodes() {
//		List<String> result = new ArrayList<String>();
//		for (Event e : resetEvents) result.add(e.getCode());
//		return result;
//	}

	private void gatherForwards(Collection<State> result, State start) {
		if (start == null) return;
		if (result.contains(start)) return;
		else {
			result.add(start);
			for (State next : start.getAllTargets()) {
				gatherForwards(result, next);
			}
			return;
		}
	}
}
