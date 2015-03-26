package vsge.graphics;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import vsge.core.Window;

public class Graphics {

	private static Window smWindow;
	private static Graphics2D graphics;
	private static Image[] images;
	private static BufferedImage screen;
	private static Polygon polygon;
	
	private static final int I11 = 0;
	private static final int I12 = 1;
	private static final int I13 = 2;
	private static final int I21 = 3;
	private static final int I22 = 4;
	private static final int I23 = 5;
	private static final int SIZE = 6;
	
	private static float[] transform = new float[SIZE];
	private static AffineTransform affine = new AffineTransform();
	
	public Graphics(Window window) {
		smWindow = window;
		graphics = smWindow.getGfx();
		images = new Image[ImageConstants.NUM_IMAGES];
		polygon = new Polygon();
		for (int i=0; i<ImageConstants.NUM_IMAGES; i++) {
			images[i] = new Image();
			images[i].loadImage(i);
		}
	}
	
	public static void reset() {
		disposeGraphics();
		screen = smWindow.getBuffer();
		graphics = (Graphics2D)screen.getGraphics();
		resetTransform();
		resetClip();
	}
	
	// ---- transformations
	
	public static void resetTransform() {
		transform[I11] = 1;
		transform[I12] = 0;
		transform[I13] = 0;
		transform[I21] = 0;
		transform[I22] = 1;
		transform[I23] = 0;
	}
	
	public static void translate(float x, float y) {
		transform[I13] += x * transform[I11] + y * transform[I12];
		transform[I23] += x * transform[I21] + y * transform[I22];
	}

	public static void rotate(float rads) {
		final float cos = (float) Math.cos(rads);
		final float sin = (float) Math.sin(rads);
		final float a11 =  cos * transform[I11] + sin * transform[I12];
		final float a12 = -sin * transform[I11] + cos * transform[I12];
		final float a21 =  cos * transform[I21] + sin * transform[I22];
		final float a22 = -sin * transform[I21] + cos * transform[I22];
		transform[I11] = a11;
		transform[I12] = a12;
		transform[I21] = a21;
		transform[I22] = a22;
	}
	
	public static void scale(float sx, float sy) {
		transform[I11] *= sx;
		transform[I12] *= sy;
		transform[I21] *= sx;
		transform[I22] *= sy;
	}
	
	public static int getWidth() {
		return smWindow.getSizeX();
	}
	
	public static int getHeight() {
		return smWindow.getSizeY();
	}
	
	public static void clear(int rgb) {
		graphics.setColor(new Color(rgb));
		graphics.fillRect(0, 0, getWidth(), getHeight());
	}
	
	public static void setColor(int rgb) {
		graphics.setColor(new Color(rgb));
	}
	
	public static void fillRect(int x, int y, int w, int h) {
		graphics.fillRect(x, y, w, h);

	}
	
	public static void fillQuadrilateral(int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4) {
		polygon.reset();
		polygon.addPoint(x1, y1);
		polygon.addPoint(x2, y2);
		polygon.addPoint(x3, y3);
		polygon.addPoint(x4, y4);
		graphics.fillPolygon(polygon);
	}
	
	public static void drawImage(int img, float x, float y) {
		resetTransform();
		translate(x, y);
		drawImage(img);
	}
	
	public static void drawImage(int img) {
		affine.setTransform(transform[I11], transform[I21],
				transform[I12], transform[I22],
				transform[I13], transform[I23]);
		graphics.drawImage(images[img].getImage(), affine, null);
	}
	
	public static void resetClip() {
		graphics.setClip(0, 0, getWidth(), getHeight());
	}
	
	public static void setClip(int x, int y, int w, int h) {
		graphics.setClip(x, y, w, h);
	}
	
	public static int getImageWidth(int img) {
		return images[img].getImage().getWidth();
	}
	
	public static int getImageHeight(int img) {
		return images[img].getImage().getHeight();
	}
	
	private static void disposeGraphics() {
		if (graphics!=null) {
			graphics.dispose();
			graphics = null;
		}
	}
	
	public static void drawLine(float x1, float y1, float x2, float y2) {
		graphics.drawLine((int)x1, (int)y1, (int)x2, (int)y2);
	}
	
	public void repaint() {
		disposeGraphics();
		smWindow.repaint();
	}

	public static void drawString(String string, int x, int y, Color clr) {
		graphics.setFont(new Font("Arial", Font.PLAIN, 48));
		graphics.setColor(clr);
		graphics.drawString(string, x, y);
	}
	
	public static void drawRectangle(int x, int y, int width, int height) {
		graphics.setPaint(Color.red);
		graphics.fill(new Rectangle.Double(x, y, width, height));
	}

}
