package vsge.ui;

import java.awt.Color;

import vsge.graphics.Graphics;
import vsge.states.Game;

public class Hud {
	public Hud() {
	}
	
	public static void paint() {
		if(Game.classicMode) {
			Graphics.drawString("CLASSIC LEVEL", 150, 60, Color.RED);
			Graphics.drawString("CLASSIC LEVEL", 153, 63, Color.WHITE);
			
			Graphics.drawString("TTL: " + (5000 - (Game.score * 15)), 650, 875, Color.RED);
			Graphics.drawString("TTL: " + (5000 - (Game.score * 15)), 653, 878, Color.WHITE);
			Graphics.drawString("SIZE: " + (1.0f-(Game.score * 0.002f)), 650, 925, Color.RED);
			Graphics.drawString("SIZE: " + (1.0f-(Game.score * 0.002f)), 653, 928, Color.WHITE);
		} else if (Game.steroidsMode) {
			Graphics.drawString("STEROIDS LEVEL", 150, 60, Color.RED);
			Graphics.drawString("STEROIDS LEVEL", 153, 63, Color.WHITE);
			
			Graphics.drawString("TTL: " + (Game.lifeTimer), 650, 900, Color.RED);
			Graphics.drawString("TTL: " + (Game.lifeTimer), 653, 903, Color.WHITE);
			
		    Graphics.drawRectangle(90, 910, Game.lifeTimer, 40);

		}
		
		Graphics.drawString("TIME: " + String.format("%.2f", (Game.timePlayed/1000)), 580, 60, Color.RED);
		Graphics.drawString("TIME: " + String.format("%.2f", (Game.timePlayed/1000)), 583, 63, Color.WHITE);
		Graphics.drawString("FPS: " + String.format("%.1f", (float)(1000/Game.lastFrameTime)), 1000, 60, Color.RED);
		Graphics.drawString("FPS: " + String.format("%.1f", (float)(1000/Game.lastFrameTime)), 1003, 63, Color.WHITE);

		Graphics.drawString("HP: " + Game.hp, 50, 900, Color.RED);
		Graphics.drawString("HP: " + Game.hp, 53, 903, Color.WHITE);
		Graphics.drawString("AMMO: " + Game.ammo, 310, 900, Color.RED);
		Graphics.drawString("AMMO: " + Game.ammo, 313, 903, Color.WHITE);
		Graphics.drawString("SCORE: " + Game.score, 950, 900, Color.RED);
		Graphics.drawString("SCORE: " + Game.score, 953, 903, Color.WHITE);
	}
}
