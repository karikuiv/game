package vsge.game;

//import java.util.Random;

//import vsge.graphics.Graphics;
import vsge.graphics.ImageConstants;
import vsge.states.Game;
import vsge.states.Game.Hit;

public class BullseyeTarget extends Target {
//	private Random r;
	
	public BullseyeTarget(int ttl, float size) {
		super(ImageConstants.IMAGE_BULLSEYETARGET, ttl, size);
//		r = new Random();
		visible = true;
	}
	
	public void place(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public boolean isInside(int pointerX, int pointerY) {
		float radius = getWidth();
		float xDist = (x - (float)pointerX);
		float yDist = (y - (float)pointerY);
		float dist = (float) Math.sqrt(xDist*xDist + yDist*yDist);
		
		if(dist < radius) {
			float distance = dist/radius;
			Game.dist += distance;
			Game.hits.add(new Hit(xDist, yDist));
			return true;
		} else {
			return false;
		}
	}
	
	public void update(int deltatime) {
		if(ttl > 0) {
			ttl -= deltatime;
		}
		
		if(dying == false) {
			if ((ttl == 0) || (ttl < -1)) {
				killMe(300);
			} else if (ttl == -1) {
				//nothing
			} else if (ttl <= (0.75 * originalTTL)) {
				size -= (originalSize/(2*originalTTL))*deltatime;
			}
		} else {
			if(ttl <= 0) {
				visible = false;
				dead = true;
			} else { 
				if(ttl <= (0.14 * originalTTL)) {
					img = ImageConstants.IMAGE_BULLSEYETARGET_6;
				} else if(ttl <= (0.28 * originalTTL)) {
					img = ImageConstants.IMAGE_BULLSEYETARGET_5;
				} else if(ttl <= (0.42 * originalTTL)) {
					img = ImageConstants.IMAGE_BULLSEYETARGET_4;
				} else if(ttl <= (0.56 * originalTTL)) {
					img = ImageConstants.IMAGE_BULLSEYETARGET_3;
				} else if(ttl <= (0.70 * originalTTL)) {
					img = ImageConstants.IMAGE_BULLSEYETARGET_2;
				} else if(ttl <= (0.84 * originalTTL)) {
					img = ImageConstants.IMAGE_BULLSEYETARGET_1;
				} else if(ttl > (0.84 * originalTTL)) {
					img = ImageConstants.IMAGE_BULLSEYETARGET;
				}
			}
		}
	}
}
