package vsge.game;

import java.util.Random;

import vsge.graphics.ImageConstants;

public class LevelClassic extends Level {
		protected Random r;
		protected int maxEnemiesOnScreen;
		protected int spawnDelay;
		protected int waveTimer;
		protected int enemiesKilled;
		protected boolean waveDone;
		
	public LevelClassic() {
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
		waveDone = false;
		background = new Sprite(ImageConstants.IMAGE_HEAVEN, 640, 480, 1.0f);
		setupWave(0);
	}	
	
	public void init() {
		r = new Random();
		setupWave(0);
		
	}
	
	public void setupWave(int wave) {
			maxEnemiesOnScreen = 5;
			spawnDelay = 250;
			enemiesLeft = -1;
	}
	
	public void update(int deltatime) {
		int i;
		for(i = 0; i < targets.size(); i++) {
			targets.get(i).update(deltatime);
		}
		
		i = 0;
		while(i < targets.size()) {
			if(targets.get(i).isDead() == true) {
				targets.remove(i);
				enemiesKilled++;
			} else {
				i++;
			}
		}		
	
		waveTimer += deltatime;
		if(numberOfEnemies < maxEnemiesOnScreen) {
			if(waveTimer >= spawnDelay) {
				waveTimer = 0;
				Target target = null;
				int count = 0;
				boolean conflict;
				target = new BullseyeTarget(5000 - (enemiesKilled * 15), 1.0f-(enemiesKilled * 0.002f));
	
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
					enemiesLeft--;
//					System.out.println("Spawned. Num enemies: " + numberOfEnemies + " Enemies left: " + enemiesLeft);
				}
			}
		}
	}
	
	public void paint() {
		super.paint();
	}
}
