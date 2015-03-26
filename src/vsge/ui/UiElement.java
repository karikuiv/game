package vsge.ui;

public abstract class UiElement {

	protected int x, y, sizeX, sizeY;
	
	
	public int getSizeX() {
		return sizeX;
	}
	
	public int getSizeY() {
		return sizeY;
	}
	
	public boolean isInside(int x, int y) {
		return x >= this.x && y >= this.y && x < this.x + getSizeX() && y < this.y + getSizeY();
	}
	
	public abstract boolean press(int x, int y);
	public abstract boolean release(int x, int y);
	public abstract void paint(int x, int y);
}
