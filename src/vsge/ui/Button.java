package vsge.ui;

import vsge.graphics.Graphics;

public abstract class Button extends UiElement {

	
	private int imageDefault;
	private int imageActive;
	private boolean isActive;
	
	public Button(int imageDefault, int imageActive, int x, int y) {
		this.imageDefault = imageDefault;
		this.imageActive = imageActive;
		this.x = x;
		this.y = y;
		this.sizeX = Graphics.getImageWidth(imageDefault);;
		this.sizeY = Graphics.getImageHeight(imageDefault);
		isActive = false;
	}

	public boolean press(int x, int y) {
		if (isInside(x, y)) {
			isActive = true;
			return true;
		}
		return false;
	}
	
	public boolean release(int x, int y) {
		isActive = false;
		if (isInside(x, y)) {
			call();
			return true;
		}
		return false;
	}
	
	public void paint(int x, int y) {
		Graphics.drawImage(isActive ? imageActive : imageDefault, x + this.x, y + this.y);
	}
	
	public abstract void call();
}
