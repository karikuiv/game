package vsge.states;

import java.awt.Color;

import vsge.core.State;
import vsge.core.StateMachine;
import vsge.events.Input;
import vsge.game.BullseyeTarget;
import vsge.game.Sprite;
import vsge.graphics.Graphics;
import vsge.ui.Button;
import vsge.ui.ExitButton;
import vsge.ui.PlayButton;

public class ScoreScreen extends State {
	
	private ExitButton exitButton;
	private Button playButton;
	private int pointerX;
	private int pointerY;
	private boolean inited;
	private BullseyeTarget bull;
	private Sprite hole;
	
	public void init() {
		System.out.println("init endscreen");
		exitButton = new ExitButton(IMAGE_EXIT, IMAGE_EXIT_ACTIVE, 0, 0);
		playButton = new PlayButton(IMAGE_PLAY, IMAGE_PLAY_ACTIVE, 250, 250);
		System.out.println(Graphics.getWidth() + " " + Graphics.getHeight());
		bull = new BullseyeTarget(-1, 1.0f);
		bull.place(250, 550);
		hole = new Sprite(IMAGE_HOLE, 0, 0, 0.5f);
		inited = true;
	}
	
	public void paintHits() {
		bull.paint();
		for(int i = 0; i < Game.hits.size(); i++) {
			hole.place(250 + Game.hits.get(i).x, 550 + Game.hits.get(i).y);
			hole.paint();
		}
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
		
		paintHits();
		
		Graphics.drawString("ACCURACY: " + String.format("%.2f", (Game.dist/Game.score)), 650, 350, Color.RED);
		Graphics.drawString("ACCURACY: " + String.format("%.2f", (Game.dist/Game.score)), 650, 353, Color.WHITE);
		
		Graphics.drawString("TIME PER KILL: " + String.format("%.2f", (Game.timePlayed/Game.score)), 650, 450, Color.RED);
		Graphics.drawString("TIME PER KILL: " + String.format("%.2f", (Game.timePlayed/Game.score)), 650, 453, Color.WHITE);
		
		Graphics.drawString("KILLS PER SECOND: " + String.format("%.2f", (Game.score/(Game.timePlayed/1000))), 650, 550, Color.RED);
		Graphics.drawString("KILLS PER SECOND: " + String.format("%.2f", (Game.score/(Game.timePlayed/1000))), 650, 553, Color.WHITE);
		
		
		Graphics.drawImage(IMAGE_TARGET, 
				pointerX - Graphics.getImageWidth(IMAGE_TARGET)/2, 
				pointerY - Graphics.getImageHeight(IMAGE_TARGET)/2);
	}
}
