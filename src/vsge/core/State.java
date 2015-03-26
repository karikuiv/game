package vsge.core;

import vsge.events.Event;
import vsge.events.Input;
import vsge.graphics.ImageConstants;
import vsge.states.Game;
import vsge.ui.Hud;

abstract public class State implements ImageConstants {

	private boolean inited;
	private Input inputs;
	
	public State() {
		inputs = Input.getInstance();
	}
	
	public void initialize() {
		if (!inited) {
			inited = true;
			init();
		}
	}
	
	public boolean processEvents() {
		Event e;
		boolean stop = false;
		do {
			e = inputs.processEvent();
			if (e != null) {
				stop = !event(e.type, e.x, e.y, e.code);
			}
		}
		while (e != null && !stop);
		return !stop;
	}
	
	public void logic(int deltatime) {
		initialize();
		update(deltatime);
	}
	
	public void draw() {
		paint();
		if(Game.inited == true) { Hud.paint(); }
	}
	
	abstract public void init();
	abstract public void update(int deltatime);
	abstract public void paint();
	abstract public boolean event(int eventType, int px, int py, int eventCode);
	
}
