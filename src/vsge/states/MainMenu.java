package vsge.states;

import vsge.core.State;
import vsge.core.StateMachine;
import vsge.events.Input;
import vsge.graphics.Graphics;
import vsge.ui.Button;
import vsge.ui.ExitButton;
import vsge.ui.LevelSelectButton;
import vsge.ui.PlayButton;

public class MainMenu extends State {
	
	private ExitButton exitButton;
	private Button playButton;
	private int pointerX;
	private int pointerY;
	private LevelSelectButton levelSelect;
	public static int levelSelected;
	
	public void init() {
		System.out.println("init menu");
		levelSelected = 0;
		exitButton = new ExitButton(IMAGE_EXIT, IMAGE_EXIT_ACTIVE, 0, 0);
		playButton = new PlayButton(IMAGE_PLAY, IMAGE_PLAY_ACTIVE, 250, 250);
		levelSelect = new LevelSelectButton(IMAGE_BORDERS, 500, 250);
	}
	
	public void update(int deltatime) {
		
	}
	
	public boolean event(int eventType, int px, int py, int eventCode) {
		if (eventType == Input.TYPE_MOUSE_DOWN) {
			exitButton.press(px, py);
			playButton.press(px, py);
			levelSelected = levelSelect.press(px, py);
		} else if (eventType == Input.TYPE_MOUSE_UP) {
			if(playButton.release(px, py)) {
				StateMachine.push(new Game());
			}
			exitButton.release(px, py);
		}
		pointerX = px;
		pointerY = py;
		return true;
	}
	
	public void paint() {
		Graphics.clear(0xbbccdd);
		exitButton.paint(0,0);
		playButton.paint(0,0);
		levelSelect.paint();
		Graphics.drawImage(IMAGE_TARGET, 
				pointerX - Graphics.getImageWidth(IMAGE_TARGET)/2, 
				pointerY - Graphics.getImageHeight(IMAGE_TARGET)/2);
	}
}
