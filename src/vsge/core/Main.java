package vsge.core;

import vsge.events.Input;
import vsge.graphics.Graphics;

public class Main {

	private static Main instance;
	private boolean running;
	
	public static void main(String[] args) {
		instance = new Main();
		instance.run();
		System.exit(0);
	}
	
	public Main() {
		
	}
	
	private void run() {
		running = true;
		StateMachine.init();
		Window window = new Window(Config.WINDOW_WIDTH, Config.WINDOW_HEIGHT, null, Input.getInstance());
		Graphics gfx = new Graphics(window);
		long deltatime = 0;
		while (running) {
			long time = System.currentTimeMillis();
			State state = StateMachine.getState();

			if (!state.processEvents()) continue;
			state.logic((int) deltatime);
			synchronized (Window.class) {
				Graphics.reset();
				state.draw();
				gfx.repaint();
			}
			deltatime = System.currentTimeMillis() - time;
			if (deltatime < Config.TARGET_FRAME_MS) {
				try {
					Thread.sleep(Config.TARGET_FRAME_MS - deltatime);
				} catch (Exception e) {
					
				}
				deltatime = Config.TARGET_FRAME_MS;
			}
			if (deltatime > Config.MAX_FRAME_MS) deltatime = Config.MAX_FRAME_MS;
		}
	}
	
	private void exitGame() {
		running = false;
	}
	
	public static void exit() {
		instance.exitGame();
	}
}
