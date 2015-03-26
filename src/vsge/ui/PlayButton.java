package vsge.ui;

import java.util.Random;
//import vsge.core.StateMachine;
import vsge.states.Game;

public class PlayButton extends Button {
	private String[] tracks = new String[4];
	Random r = new Random();
	
	public PlayButton(int imageDefault, int imageActive, int x, int y) {
		super(imageDefault, imageActive, x, y);
	}
	
	public void call() {
		Game.newGame = true;
//		StateMachine.clear(new Game());
	}
}
