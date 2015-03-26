package vsge.game;

import vsge.graphics.ImageConstants;

public class HitMarker extends Sprite implements ImageConstants {
	private int ttl;
	private int originalTTL;
	
	public HitMarker(int img, float x, float y, float size) {
		super(img, x, y, size);
		this.visible = false;
	}

	public void update(int deltatime) {
		ttl -= deltatime;
		if(ttl <= 0) {
			visible = false;
			return;
		} else if(ttl <= (0.25 * originalTTL)) {
			img = ImageConstants.IMAGE_BACON_25;
			return;
		} else if(ttl <= (0.5 * originalTTL)) {
			img = ImageConstants.IMAGE_BACON_50;
			return;
		} else if(ttl <= (0.75 * originalTTL)) {
			img = ImageConstants.IMAGE_BACON_75;
			return;
		} else if(ttl > (0.75 * originalTTL)) {
			img = ImageConstants.IMAGE_BACON;
		} 
	}
	
	public void setVisible(int ttl) {
		this.ttl = ttl;
		this.originalTTL = ttl;
		visible = true;
	}
	
	public boolean getVisible() {
		return visible;
	}
}
