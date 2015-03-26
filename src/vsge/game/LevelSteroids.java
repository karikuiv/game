package vsge.game;

import java.util.Random;

import vsge.core.Config;
import vsge.graphics.ImageConstants;
import vsge.states.Game;

public class LevelSteroids extends Level {
		protected Random r;
		protected int maxEnemiesOnScreen;
		protected int spawnDelay;
		protected int waveTimer;
		protected int enemiesKilled;
		protected boolean waveDone;
				
	public LevelSteroids() {
		super();
		isDone = false;
		numberOfEnemies = 0;
		enemiesLeft = 0;
		enemiesKilled = 0;
		maxEnemiesOnScreen = 0;
		spawnDelay = 0;
		numberOfWaves = 0;
		currentWave = 0;
		waveTimer = 0;
		Game.lifeTimer = 1000;
		Game.lifeTimerPotential = 0;
		waveDone = false;
		background = new Sprite(ImageConstants.IMAGE_HEAVEN, 640, 480, 1.0f);
	}	
	
	public void init() {
		r = new Random();
		maxEnemiesOnScreen = 3;
		spawnDelay = 170;
		waveTimer = spawnDelay;
		enemiesLeft = -1;		
	}
	
	public void update(int deltatime) {
		int i;
		for(i = 0; i < targets.size(); i++) {
			targets.get(i).update(deltatime);
		}
		
		i = 0;
		while(i < targets.size()) {
			if(targets.get(i).isDead()) {
				targets.remove(i);
				enemiesKilled++;
			} else {
				i++;
			}
		}		
		
		int count = 0;
		do {
			count++;
			if(Game.lifeTimerPotential >= (5 * (deltatime/Config.TARGET_FRAME_MS))) {
				Game.lifeTimer += (5 * (deltatime/Config.TARGET_FRAME_MS));
				Game.lifeTimerPotential -= 5;
			} else if (Game.lifeTimerPotential > 0) {
				Game.lifeTimer += Game.lifeTimerPotential;
				count = 4;
			}
			
		} while (count < 4);
		
		Game.lifeTimer -= deltatime * (deltatime/Config.TARGET_FRAME_MS);
		
		
		if(Game.lifeTimer <= 0) {
			Game.gameOver = true;
		}
		
		if (Game.lifeTimer > 1000) {
			Game.lifeTimer = 1000;
		}
		
		waveTimer += deltatime;
		if(numberOfEnemies < maxEnemiesOnScreen) {
			if(waveTimer >= spawnDelay) {
				Target target = null;
				count = 0;
				boolean conflict;
				target = new BullseyeTarget(-1, 0.8f);
	
				do {
					count++;
					target.x = r.nextInt(880) + 200;
					target.y = r.nextInt(560) + 200;
					conflict = checkConflict(target); 
				} while((conflict == true) && (count < 10));
				
				if(conflict == false) {
					target.setListener(this);
					targets.add(target);
					numberOfEnemies++;
					waveTimer = 0;
				}
			}
		}
	}
	
	public void paint() {
		super.paint();
	}
}
