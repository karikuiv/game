package vsge.core;


import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import vsge.events.Input;

public class Window implements ComponentListener, ActionListener {
	private BufferedImage screenBuffer;

	/** Application display size. */
	private int width;
	private int height;

	protected Input input;

	private JFrame frame;
	private Container parent;
	private Component inputSource;
	
	private DisplayComponent canvas;
	
	public Window(int width, int height, Container parentContainer, Input input) {
		this.width = width;
		this.height = height;
		this.parent = parentContainer;
		this.input = input;

		canvas = new DisplayComponent(this);
		
		if (parent == null) {
			frame = new JFrame();
			parent = frame;
			inputSource = canvas;
			frame.setSize(this.width, this.height);
			frame.setResizable(false);

			frame.getContentPane().removeAll();
			frame.getContentPane().add(canvas);

//			canvas.setSize(this.width, this.height);
//			canvas.setPreferredSize(new Dimension(this.width, this.height));
			
			canvas.setSize(this.width + 2, this.height + 2);
			canvas.setPreferredSize(new Dimension(this.width + 2, this.height + 2));

			frame.pack();
			
			//fullscreen?
			//frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
			frame.setVisible(true);
						
			canvas.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB), new Point(0, 0), "cursor"));
			
		} else {
			inputSource = parent;
			parent.add(canvas);
			
		}

		if (frame != null) {
			// frame post-init after frame has been resized
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			frame.addComponentListener(this);
		}
		
		resizeScreen();
		startingHandlingEvents();
	}
	
	public void update() {
			
	}
	
	public int getSizeX() {
		return width;
	}

	public int getSizeY() {
		return height;
	}
	
	public BufferedImage getBuffer() {
		return screenBuffer;
	}

	public Graphics2D getGfx() {
		return (Graphics2D) screenBuffer.getGraphics();
	}
	
	public void paint(Graphics g) {
		synchronized (Window.class) {
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, width+2, height+2);

			g.drawImage(screenBuffer, 1, 1, width, height, null);
		}
	}
	
	public void close() {
		stopHandlingEvents();
		if (frame!=null) {
			frame.setVisible(false);
			frame.dispose();
		}
	}

	public void activate() {
		canvas.requestFocus();
		parent.requestFocus();
	}

	synchronized public void resizeScreen() {
		screenBuffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	}

	// ---- ComponentListener for screen size changes
	
	public void componentHidden(ComponentEvent e) {
	}

	public void componentMoved(ComponentEvent e) {
	}

	synchronized public void componentResized(ComponentEvent e) {

	}


	// ---- ActionListener for menus

	public void componentShown(ComponentEvent e) {
	}

	public void actionPerformed(ActionEvent e) {
	}

	public void startingHandlingEvents() {
		input.addWindow(this);		
		parent.addKeyListener(input);
		inputSource.addMouseListener(input);
		inputSource.addMouseMotionListener(input);
		inputSource.addMouseWheelListener(input);
		if (frame!=null) {
			frame.addWindowListener(input);
		}
	}
	
	public void stopHandlingEvents() {
		parent.removeKeyListener(input);
		inputSource.removeMouseListener(input);
		inputSource.removeMouseMotionListener(input);
		inputSource.removeMouseWheelListener(input);
		if (frame!=null) {
			frame.removeWindowListener(input);
		}
		input.clearEvents();
		input = null;
	}

	public void repaint() {
		canvas.repaint();
		parent.repaint();
	}

	public Input getInput() {
		return input;
	}

	public void setLocation(int x, int y) {
		frame.setLocation(x, y);
	}




}
