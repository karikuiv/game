package vsge.game;

import vsge.graphics.Graphics;

public class Sprite {

	protected float x;
	protected float y;
	protected float size;
	protected float originalSize;
	protected int img;
	protected boolean visible;
	protected float rads;
	
	public Sprite(int img, float x, float y, float size) {
		this.img = img;
		this.x = x;
		this.y = y;
		this.size = size;
		this.originalSize = size;
		visible = false;
		this.rads = 0;
	}
	
	public Sprite(int img) {
		this.img = img;
		visible = false;
	}
	
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	public boolean getVisible() {
		return visible;
	}
	
	public void place(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public void setImg(int img) {
		this.img = img;
	}
	
	public float getWidth() {
		return (Graphics.getImageWidth(img) * size)/2;
	}

	public float getHeight() {
		return (Graphics.getImageHeight(img) * size)/2;
	}
	
	public boolean isInside(int px, int py, int w, int h) {
		final int x1 = (int) (x - size*Graphics.getImageWidth(img)/2);
		final int x2 = (int) (x + size*Graphics.getImageWidth(img)/2);
		final int y1 = (int) (y - size*Graphics.getImageHeight(img)/2);
		final int y2 = (int) (y + size*Graphics.getImageHeight(img)/2);
		return px+w >= x1 && px-w <= x2 && py+h >= y1 && py-h <= y2;
	}
	
	public boolean isInside(int px, int py) {
		final int x1 = (int) (x - size*Graphics.getImageWidth(img)/2);
		final int x2 = (int) (x + size*Graphics.getImageWidth(img)/2);
		final int y1 = (int) (y - size*Graphics.getImageHeight(img)/2);
		final int y2 = (int) (y + size*Graphics.getImageHeight(img)/2);
		return px >= x1 && px <= x2 && py >= y1 && py <= y2;
	}
	
	public void paint() {
		Graphics.resetTransform();
		Graphics.translate(x, y);
		Graphics.scale(size, size);
		Graphics.rotate(rads);
//		Graphics.translate(-Graphics.getImageWidth(img)/2, -Graphics.getImageWidth(img)/2);
		Graphics.translate(-Graphics.getImageWidth(img)/2, -Graphics.getImageHeight(img)/2);
		Graphics.drawImage(img);
	}
}
