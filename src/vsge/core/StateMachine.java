package vsge.core;


import java.util.Stack;

import vsge.states.MainMenu;

public class StateMachine {

	private Stack<State> states = new Stack<State>();
	private static StateMachine instance;
	
	public static void init() {
		if (instance == null) instance = new StateMachine();
		//clear((State) (new Game()));
		clear((State) (new MainMenu()));
	}
	
	public StateMachine() {
		
	}
	
	public State getCurrentState() {
		return states.peek();
	}
	
	public static void change(State state) {
		pop();
		instance.states.push(state);
	}
	
	public static void push(State state) {
		instance.states.push(state);
	}
	
	public static void clear(State state) {
		instance.states.clear();
		push(state);
	}
	
	public static void pop() {
		instance.states.pop();
		if (instance.states.isEmpty()) Main.exit();
	}
	
	public static State getState() {
		return instance.getCurrentState();
	}
}
