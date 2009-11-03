package kentaro714.model.fsm;

import org.junit.Test;


public class TransitionTester extends AbstractStateTestLib {
	State idle, a, b;
	Event trigger_a, trigger_b, unknown;
	
	@Test
	public void event_causees_transition() {
		fire(trigger_a);
		assertCurrentState(a);
	}
	
	@Test
	public void event_without_transition_is_ignored() {
		fire(unknown);
		assertCurrentState(idle);
	}
	
	@Override
	protected StateMachine createMachine() {
		idle = new State("idle");
		StateMachine result = new StateMachine(idle);
		trigger_a = new Event("trigger_a", "TRGA");
		trigger_b = new Event("trigger_b", "TRGB");
		unknown = new Event("Unknown", "UNKN");
		a = new State("a");
		// ファウラーのサンプル、bのインスタンス生成が抜けてない？
		b = new State("b");
		idle.addTransition(trigger_a, a);
		idle.addTransition(trigger_b, b);
		return result;
	}
}
