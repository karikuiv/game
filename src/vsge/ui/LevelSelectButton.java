package vsge.ui;

import java.awt.Color;

import vsge.game.Sprite;
import vsge.graphics.Graphics;
import vsge.graphics.ImageConstants;

public class LevelSelectButton {
	private int levelSelected;
	private int x;
	private int y;
	Sprite img;
	
	public LevelSelectButton(int imageDefault, int x, int y) {
		this.x = x;
		this.y = y;
		img = new Sprite(imageDefault, x, y, 1.0f);
		levelSelected = 0;
	}
	
	public void paint() {
		img.setImg(ImageConstants.IMAGE_LEVELSELECT);
		img.place(x + (Graphics.getImageWidth(ImageConstants.IMAGE_BORDERS)/2), y);
		img.paint();
		
		if(levelSelected == 0) {
			Graphics.drawString("CLASSIC MODE", x+25, y, Color.RED);
			Graphics.drawString("CLASSIC MODE", x+28, y+3, Color.WHITE);
		} else {
			Graphics.drawString("STEROIDS MODE", x+25, y, Color.RED);
			Graphics.drawString("STEROIDS MODE", x+28, y+3, Color.WHITE);
		}

	}
	
	public int press(int px, int py) {
		if(img.isInside(px, py)) {
			levelSelected++;
		}
		if(levelSelected > 1) {
			levelSelected = 0;
		}
		return levelSelected;
	}


}
