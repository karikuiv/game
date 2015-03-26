package vsge.events;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.LinkedList;

import vsge.core.Main;
import vsge.core.Window;

public class Input implements KeyListener, MouseListener, MouseMotionListener, MouseWheelListener, WindowListener {

	public static final int KEY_N = 0;
	public static final int KEY_S = 1;
	public static final int KEY_W = 2;
	public static final int KEY_E = 3;
	public static final int KEY_FIRE = 4;
	public static final int KEY_SW = 5;
	public static final int KEY_SE = 6;
	public static final int KEY_NW = 7;
	public static final int KEY_NE = 8;
	
	public static final int MOUSE_BUTTON_LEFT = 9;
	public static final int MOUSE_BUTTON_MIDDLE = 10;
	public static final int MOUSE_BUTTON_RIGHT = 11;
	
	public static final int TYPE_KEY_DOWN = 0;
	public static final int TYPE_KEY_UP = 1;
	public static final int TYPE_MOUSE_DOWN = 2;
	public static final int TYPE_MOUSE_UP = 3;
	public static final int TYPE_MOUSE_DRAG = 4;
	public static final int TYPE_MOUSE_MOVE = 5;
	
	private LinkedList<Event> events = new LinkedList<Event>();
	
	private static Input instance;
	
	private Window window;

	public static Input getInstance() {
		if (instance == null) instance = new Input();
		return instance;
	}
	
	public Input() {
		
	}
	
	public void addWindow(Window window) {
		this.window = window;
	}
	
	public static int getKey(int keyCode) {
		int key = -1;
		switch (keyCode) {
		case KeyEvent.VK_UP:
			key = KEY_N;
			break;
		case KeyEvent.VK_DOWN:
			key = KEY_S;
			break;
		case KeyEvent.VK_LEFT:
			key = KEY_W;
			break;
		case KeyEvent.VK_RIGHT:
			key = KEY_E;
			break;
		case KeyEvent.VK_SPACE:
		case KeyEvent.VK_ENTER:
			key = KEY_FIRE;
			break;
		case KeyEvent.VK_NUMPAD1:
			key = KEY_SW;
			break;
		case KeyEvent.VK_NUMPAD2:
			key = KEY_S;
			break;
		case KeyEvent.VK_NUMPAD3:
			key = KEY_SE;
			break;
		case KeyEvent.VK_NUMPAD4:
			key = KEY_W;
			break;
		case KeyEvent.VK_NUMPAD5:
			key = KEY_FIRE;
			break;
		case KeyEvent.VK_NUMPAD6:
			key = KEY_E;
			break;
		case KeyEvent.VK_NUMPAD7:
			key = KEY_NW;
			break;
		case KeyEvent.VK_NUMPAD8:
			key = KEY_N;
			break;
		case KeyEvent.VK_NUMPAD9:
			key = KEY_NE;
			break;

		}
		return key;
	}

	public void keyPressed(KeyEvent event) {
		// use escape for system exit
		final int keyCode = event.getKeyCode();
		if (keyCode == KeyEvent.VK_ESCAPE) {
			Main.exit();
			return;
		}
        addEvent(TYPE_KEY_DOWN, getKey(keyCode));
	}

	public void keyReleased(KeyEvent event) {
		final int keyCode = event.getKeyCode(); 
        addEvent(TYPE_KEY_UP, getKey(keyCode));
	}

	public void keyTyped(KeyEvent event) {

	}
	
	public void mouseClicked(MouseEvent e) {

	}

	public void mouseEntered(MouseEvent e) {

	}

	public void mouseExited(MouseEvent e) {

	}

	private void addEvent(int eventType, MouseEvent e) {
		if (window == null) return;
		
		int x = Math.max(0, Math.min((int)(e.getX() - 1), window.getSizeX()-1));
		int y = Math.max(0, Math.min((int)(e.getY() - 1), window.getSizeY()-1));

		int button = -1;
		switch (e.getButton()) {
		case MouseEvent.BUTTON1:
			button = MOUSE_BUTTON_LEFT;
			break;
		case MouseEvent.BUTTON2:
			button = MOUSE_BUTTON_MIDDLE;
			break;
		case MouseEvent.BUTTON3:
			button = MOUSE_BUTTON_RIGHT;
			break;
		}
		if (button > -1) addEvent(eventType, x, y, button);
	}

	public void mousePressed(MouseEvent e) {
		addEvent(TYPE_MOUSE_DOWN, e);
	}

	public void mouseReleased(MouseEvent e) {
		addEvent(TYPE_MOUSE_UP, e);
	}
	
	public void mouseMoved(MouseEvent e) {
		int x = Math.max(0, Math.min((int)(e.getX() - 1), window.getSizeX()-1));
		int y = Math.max(0, Math.min((int)(e.getY() - 1), window.getSizeY()-1));
		addEvent(TYPE_MOUSE_MOVE, x, y, -1);
	}

	public void mouseDragged(MouseEvent e) {
		int x = Math.max(0, Math.min((int)(e.getX() - 1), window.getSizeX()-1));
		int y = Math.max(0, Math.min((int)(e.getY() - 1), window.getSizeY()-1));
		addEvent(TYPE_MOUSE_DRAG, x, y, -1);
	}
	
	public void mouseWheelMoved(MouseWheelEvent e) {

	}

	public void windowClosed(WindowEvent e) {
	}

	public void windowClosing(WindowEvent e) {
		Main.exit();
	}

	public void windowActivated(WindowEvent e) {

	}

	public void windowDeactivated(WindowEvent e) {

	}

	public void windowDeiconified(WindowEvent e) {

	}

	public void windowIconified(WindowEvent e) {

	}

	public void windowOpened(WindowEvent e) {
	
	}
	
	public void addEvent(int eventType, int eventCode) {
		addEvent(eventType, -1, -1, eventCode);
	}
	
	public synchronized void addEvent(int eventType, int x, int y, int eventCode) {
		events.addLast(new Event(eventType, x, y, eventCode));
	}
	
	public synchronized void clearEvents() {
		events.clear();
	}
	
	public synchronized Event processEvent() {
		if (!events.isEmpty()) {
			try {
				return events.removeFirst();
			} catch (Exception e){
				return null;
			}
		}
		else return null;
	}

}
