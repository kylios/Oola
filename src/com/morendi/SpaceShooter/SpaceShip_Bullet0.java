package com.morendi.SpaceShooter;

import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;

import com.morendi.Oola.OolaGame;
import com.morendi.Oola.OolaObject;

public class SpaceShip_Bullet0 extends OolaObject {
	
	protected static final int WIDTH = 16;
	protected static final int HEIGHT = 12;

	protected static final int HORIZ_SPEED = 8;
	protected static final int VERT_SPEED = 0;
	
	protected Image image;
	
	public SpaceShip_Bullet0(OolaGame game, int x,
			int y) {
		super(game, WIDTH, HEIGHT, x, y);
		
		try {
			this.image = game.getLoader().load("SpaceShip_Bullet0.png");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public OffscreenBehavior getOffScreenBehavior() {
		return OffscreenBehavior.OFFSCREEN_DENY_KILL;
	}

	@Override
	public void move() {
		this.x += HORIZ_SPEED;
		this.y += VERT_SPEED;
		
		if (this.x - this.width / 2 > this.getGame().getGameWidth() ||
				this.x + this.width / 2 < 0 ||
				this.y - this.height / 2 > this.getGame().getGameHeight() ||
				this.y + this.height / 2 < 0) {
			this.destroy();
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
}
