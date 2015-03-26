package vsge.states;

import vsge.core.State;
import vsge.core.StateMachine;
import vsge.events.Input;
import vsge.game.Sprite;
import vsge.graphics.Graphics;
import vsge.ui.Button;
import vsge.ui.ExitButton;
import vsge.ui.PlayButton;

public class WinScreen extends State {
	
	private ExitButton exitButton;
	private Button playButton;
	private int pointerX;
	private int pointerY;
	private boolean inited;
	
	public void init() {
		System.out.println("init endscreen");
		exitButton = new ExitButton(IMAGE_EXIT, IMAGE_EXIT_ACTIVE, 0, 0);
		playButton = new PlayButton(IMAGE_PLAY, IMAGE_PLAY_ACTIVE, 250, 250);
		System.out.println(Graphics.getWidth() + " " + Graphics.getHeight());
		inited = true;
	}
	
	public void update(int deltatime) {
		
	}
	
	public boolean event(int eventType, int px, int py, int eventCode) {
		pointerX = px;
		pointerY = py;
		if(inited == true) {
		if (eventType == Input.TYPE_MOUSE_DOWN) {
			exitButton.press(px, py);
			playButton.press(px, py);
		} else if (eventType == Input.TYPE_MOUSE_UP) {
			if(playButton.release(px, py)) {
				StateMachine.change(new Game());
			}
			exitButton.release(px, py);
		}
		}
		return true;
	}
	
	public void paint() {
		Graphics.clear(0xbbccdd);
		exitButton.paint(0,0);
		playButton.paint(0,0);
		Graphics.drawImage(IMAGE_TARGET, 
				pointerX - Graphics.getImageWidth(IMAGE_TARGET)/2, 
				pointerY - Graphics.getImageHeight(IMAGE_TARGET)/2);
	}
}
