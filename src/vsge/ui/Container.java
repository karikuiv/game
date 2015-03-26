package vsge.ui;

import java.util.ArrayList;

public class Container extends UiElement {

	private ArrayList<Button> buttons = new ArrayList<Button>();
	
	public Container(int x, int y, int sizeX, int sizeY) {
		this.x = x;
		this.y = y;
		this.sizeX = sizeX;
		this.sizeY = sizeY;
	}
	
	public void paint(int x, int y) {
		for (Button b : buttons) {
			b.paint(this.x + x, this.y + y);
		}
	}
	
	public void addButton(Button b) {
		buttons.add(b);
	}

	@Override
	public boolean press(int x, int y) {
		boolean active = false;
		for (Button b : buttons) {
			if (b.press(x - this.x, y - this.y)) active = true;
		}
		return active;
	}

	@Override
	public boolean release(int x, int y) {
		boolean active = false;
		for (Button b : buttons) {
			if (b.release(x - this.x, y - this.y)) active = true;
		}
		return active;
	}
}
