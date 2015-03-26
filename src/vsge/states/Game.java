package vsge.states;

import java.util.ArrayList;

import vsge.core.State;
import vsge.core.StateMachine;
import vsge.events.Input;
import vsge.game.HitMarker;
import vsge.game.Level;
import vsge.game.LevelClassic;
import vsge.game.LevelSteroids;
import vsge.graphics.Graphics;
import vsge.ui.ExitButton;

public class Game extends State {

	private ExitButton exitButton;
	
	private int pointerX;
	private int pointerY;
	
	public static int score;
	public static int ammo;
	public static int hp;
	public static int lastFrameTime = 1;	
	public static float timePlayed = 0;
	public static float dist;
	public static boolean inited;
	public static boolean newGame;
	public static boolean classicMode;
	public static boolean steroidsMode;
	public static boolean gameOver;
	public static int lifeTimer;
	public static int lifeTimerPotential;
	
	public static class Hit {
		public float x;
		public float y;
		public Hit(float x, float y) {
			this.x = x;
			this.y = y;
		}
	}
	public static ArrayList<Hit> hits = new ArrayList<Hit>(); 
	
	private boolean crosshairVisible;
	private boolean shotFired;
	private boolean targetHit;
	
	public static ArrayList<Level> levels = new ArrayList<Level>();
	private HitMarker hitmarker;
	
	public void init() {
		System.out.println("init game");
        
		crosshairVisible = true;
		hitmarker = new HitMarker(IMAGE_BACON, 0, 0, 0.5f);
		exitButton = new ExitButton(IMAGE_EXIT, IMAGE_EXIT_ACTIVE, 0, 0);
		
		reset();
		inited = true;
	}
	
	public void reset() {
		lastFrameTime = 1;
		timePlayed = 0;
		score = 0;
		hp = 3;
		ammo = 5;
		dist = 0;
		levels.clear();
		hits.clear();
		classicMode = false;
		steroidsMode = false;
		gameOver = false;
	}
	
	public void update(int deltatime) {
		lastFrameTime = deltatime;
		timePlayed += deltatime;
		
		if (gameOver) {
			StateMachine.change(new ScoreScreen());
		}		
		
		if(Game.newGame) {
			Game.newGame = false;
			reset();

			if(MainMenu.levelSelected == 0) {
				levels.add(new LevelClassic());
				hp = 1;
				ammo = 1;
				classicMode = true;
				
			} else if (MainMenu.levelSelected == 1) {
				levels.add(new LevelSteroids());
				hp = 1;
				ammo = 1;
				steroidsMode = true;
			}

			levels.get(0).setListener(this);
			levels.get(0).init();
		}
		
		if(shotFired == true) {
			shotFired = false;
			hitmarker.place(pointerX, pointerY);
			hitmarker.setVisible(666);			
			if(crosshairVisible == true) {
				crosshairVisible = false;
			}
			
			targetHit = levels.get(0).shoot(pointerX, pointerY);
			if(targetHit == false) {
				ammo--;
				if(ammo <= 0) {
					gameOver = true;
				}
			} else {
				score++; 
			}
		}
		
		levels.get(0).update(deltatime);
		
		if(hitmarker.getVisible()) {
			hitmarker.update(deltatime);
		}
	}
	
	public void kill() {
		hp--;
		if(hp <= 0) {
			gameOver = true;
		}
	}
	
	public boolean event(int eventType, int px, int py, int eventCode) {
		pointerX = px;
		pointerY = py;
		
		if (eventType == Input.TYPE_MOUSE_DOWN) {
			if(!exitButton.press(px, py)) {
				shotFired = true;
			}
		} else if (eventType == Input.TYPE_MOUSE_UP) {
			if(exitButton.release(px, py)) {
				shotFired = false;
			}
		}
		
		return true;
	}
	
	public void paint() {
		Graphics.clear(0x000000);
		
		levels.get(0).paintBackground();
		levels.get(0).paint();
		
		exitButton.paint(0, 0);
		if(crosshairVisible) {
			Graphics.drawImage(IMAGE_TARGET, 
				pointerX - Graphics.getImageWidth(IMAGE_TARGET)/2, 
				pointerY - Graphics.getImageHeight(IMAGE_TARGET)/2);
		}
		
		if(hitmarker.getVisible()) {
			hitmarker.paint();
		}
	}

}
