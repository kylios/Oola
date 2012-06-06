package com.morendi.SpaceShooter;

import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;

import com.morendi.Oola.OolaGame;
import com.morendi.Oola.OolaObject;

public class Enemy0 extends OolaObject {
	
	protected static final int WIDTH = 32;
	protected static final int HEIGHT = 32;
	
	protected static final String NAME = "Enemy0";
	protected static final String IMAGE_NAME = "Enemy0.png";
	
	private Image image;

	public Enemy0(OolaGame game, int x, int y) {
		super(game, WIDTH, HEIGHT, x, y);
		
		try {
			this.image = game.getLoader().load(IMAGE_NAME);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public OffscreenBehavior getOffScreenBehavior() {
		return OffscreenBehavior.OFFSCREEN_ALLOW;
	}

	@Override
	public void move() {
		this.x -= 2;
		
		if (this.x + this.width / 2 < 0) {
			this.destroy();
		}
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(this.image, 
				this.x - this.width / 2, this.y - this.height / 2, 
				this.width, this.height, this.getGame().getContext());
	}

}
