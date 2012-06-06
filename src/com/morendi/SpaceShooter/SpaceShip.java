package com.morendi.SpaceShooter;

import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;

import com.morendi.Oola.OolaObject;

public class SpaceShip extends OolaObject implements OolaObject.MovementCurve{
	
	protected static final int WIDTH = 48;
	protected static final int HEIGHT = 64;
	
	protected static final int X_MOVE_SPEED = 5;
	protected static final int Y_MOVE_SPEED = 5;
	
	protected static final String NAME = "SpaceShip";
	protected static final String IMAGE_NAME = "SpaceShip.png";
	
	private int moveX = 0;
	private int moveY = 0;
	
	private int moveXSpeed = X_MOVE_SPEED;
	private int moveYSpeed = Y_MOVE_SPEED;
	
	private Direction dir;
	
	private Image image;

	public SpaceShip(SpaceShooter game, int x, int y) {
		super(game, WIDTH, HEIGHT, x, y);
		
		try {
			this.image = game.getLoader().load(IMAGE_NAME);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void moveUp() {
		if (this.dir != Direction.UP) {
			this.moveYSpeed = Y_MOVE_SPEED;
		}
		this.moveY += this.moveYSpeed--;
		this.dir = Direction.UP;
	}
	public void moveDown() {
		if (this.dir != Direction.DOWN) {
			this.moveYSpeed = Y_MOVE_SPEED;
		}
		this.moveY -= this.moveYSpeed--;
		this.dir = Direction.DOWN;
	}
	public void moveLeft() {
		if (this.dir != Direction.LEFT) {
			this.moveXSpeed = X_MOVE_SPEED;
		}
		this.moveX -= this.moveXSpeed--;
		this.dir = Direction.LEFT;
	}
	public void moveRight() {
		if (this.dir != Direction.RIGHT) {
			this.moveXSpeed = X_MOVE_SPEED;
		}
		this.moveX += this.moveXSpeed--;
		this.dir = Direction.RIGHT;
	}
	
	public void shoot() {
		
		SpaceShooter game = (SpaceShooter) this.getGame();
		int halfWidth = this.width / 2;
		int halfHeight = this.height / 2;
		
		int[] bullet1 = {
			this.x + halfWidth + SpaceShip_Bullet0.WIDTH / 2,
			this.y + halfHeight - SpaceShip_Bullet0.HEIGHT
		};
		
		int[] bullet2 = {
			this.x + halfWidth + SpaceShip_Bullet0.WIDTH / 2,
			this.y - halfHeight + SpaceShip_Bullet0.HEIGHT / 2
		};
		
		game.addObject(new SpaceShip_Bullet0(game, bullet1[0], bullet1[1]));
		game.addObject(new SpaceShip_Bullet0(game, bullet2[0], bullet2[1]));
		
	}
	
	public OffscreenBehavior getOffScreenBehavior() {
		return OffscreenBehavior.OFFSCREEN_DENY_STOP;
	}

	@Override
	public void move() {
		
		this.addX(this.moveX);
		this.addY(this.moveY);
	}
	
	protected void checkX() {
		int gameWidth = getGame().getGameWidth();
		int halfWidth = this.width / 2;
		if (this.x + halfWidth > gameWidth) {
			this.x = gameWidth - halfWidth;
			this.moveX = 0;
		} else if (this.x - halfWidth < 0) {
			this.x = halfWidth;
			this.moveX = 0;
		}
	}
	protected void checkY() {
		int gameHeight = getGame().getGameHeight();
		int halfHeight = this.height / 2;
		if (this.y + halfHeight > gameHeight) {
			this.y = gameHeight - halfHeight;
			this.moveY = 0;
		} else if (this.y - halfHeight < 0) {
			this.y = halfHeight;
			this.moveY = 0;
		}
	}

	@Override
	public void render(Graphics g) {
		
		int width = this.getWidth();
		int height = this.getHeight();
		int drawX = this.getX() - width / 2;
		int drawY = this.getY() - height / 2;
		g.drawImage(this.image, drawX, drawY, width, height, getGame().getContext());
	}

	@Override
	public int getCurveForTick(int tick, OolaObject.Direction dir) {
		tick = Math.abs(tick);
		int val = (int) (-4 * Math.sqrt(tick / 32));
		if (val < 1)	return 1;
		System.out.println("f(" + tick + ") = " + val);
		return val;
	}
}
