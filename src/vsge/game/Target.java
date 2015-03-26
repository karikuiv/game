package vsge.game;

public class Target extends Sprite {
	protected float yv;
	protected float xv;
	protected int ttl;
	protected int originalTTL;
	public float theta;
	protected boolean dying;
	protected boolean dead;
	protected boolean suicide;
	protected boolean exploding;
	protected Level listener;
	
	public Target(int img, int ttl, float size) {
		super(img);
		this.ttl = ttl;
		this.originalTTL = ttl;
		this.visible = true;
		this.dying = false;
		this.dead = false;
		this.suicide = false;
		this.exploding = false;
		this.theta = 0;
		this.size = size;
		this.originalSize = size;
		this.yv = 0.0f;
		this.xv = 0.0f;
	}
	
	public void place(int x, int y, float theta) {
		this.x = x;
		this.y = y;
		this.theta = theta;
	}
	
	public void setListener(Level level) {
		listener = level;
	}
	
	public void killMe(int ttl) {
		suicide = true;
		setDying(ttl);
		listener.killTarget();
	}
	
	public void update(int deltatime) {
	}
	
	public void setDying(int ttl) {
		dying = true;
		this.ttl = ttl;
		this.originalTTL = ttl;
	}
	
	public boolean isDead() {
		return dead;
	}
	
	public boolean isDying() {
		return dying;
	}
	
	public int getTTL() {
		return ttl;
	}
}