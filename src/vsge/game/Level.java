package vsge.game;

import java.util.ArrayList;

import vsge.graphics.Graphics;
import vsge.states.Game;

public class Level {
	protected int currentWave;
	protected int numberOfWaves;
	public int numberOfEnemies;
	public int enemiesLeft;
	protected Sprite background;
	protected Game listener;
	
	protected boolean isDone;
	
	public ArrayList<Target> targets;
	
	
	public Level() {
		targets = new ArrayList<Target>();
	}
	
	public void update(int deltatime) {
		
	}
	
	public void setListener(Game listener) {
		this.listener = listener; 
	}
	
	public void init() {
	}
	
	public boolean isDone() {
		return isDone;
	}

	public boolean shoot(int pointerX, int pointerY) {
		for(int i = 0; i < targets.size(); i++) {
			if(targets.get(i).isDying() == false) {
				if(targets.get(i).getVisible() == true) {
					if(targets.get(i).isInside(pointerX, pointerY) == true) {
						targets.get(i).setDying(300);
						killedTarget();
						
						if(Game.steroidsMode) {
							Game.lifeTimerPotential += 225;
						}
						
						if(targets.get(i).exploding == true) {
							targets.get(i).place(pointerX, pointerY);
						}
//						targets.get(i).setDying(300);
//						killedTarget();
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public boolean checkConflict(Sprite sprite) {
		int x = (int) sprite.x;
		int y = (int) sprite.y;
		int w = (int) (sprite.size * Graphics.getImageWidth(sprite.img))/2;
		int h = (int) (sprite.size * Graphics.getImageHeight(sprite.img))/2;
		for(int i = 0; i < targets.size(); i++) {
			if(targets.get(i).isInside(x, y, w, h) == true) {
				return true;
			}
			
		}
		return false;
	}
	
	public void killTarget() {
		listener.kill();
		numberOfEnemies--;
//		System.out.println("Enemy died " + numberOfEnemies + " " + enemiesLeft);
	}
	
	public void killedTarget() {
		numberOfEnemies--;
//		System.out.println("Killed enemy " + numberOfEnemies + " " + enemiesLeft);
	}
	
	public void paintBackground() {
		background.paint();
	}
	
	public void paint() {
		for(int i = 0; i < targets.size(); i++) {
			if(targets.get(i).getVisible() == true) {
				targets.get(i).paint();
			}
		}
		
	}
	
}
