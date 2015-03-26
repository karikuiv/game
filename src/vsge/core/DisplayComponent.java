package vsge.core;

import java.awt.Component;
import java.awt.Graphics;


public class DisplayComponent extends Component {

	private Window display;
	private static final long serialVersionUID = 1;

	public DisplayComponent(Window display) {
		this.display = display;
	}

	public void update(Graphics g) {
		display.paint(g);
	}

	public void paint(Graphics g) {
		display.paint(g);
	}

}
