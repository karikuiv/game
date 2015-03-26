package vsge.ui;

import vsge.core.StateMachine;

public class ExitButton extends Button {

	public ExitButton(int imageDefault, int imageActive, int x, int y) {
		super(imageDefault, imageActive, x, y);
	}
	
	public void call() {
		StateMachine.pop();
	}
}
