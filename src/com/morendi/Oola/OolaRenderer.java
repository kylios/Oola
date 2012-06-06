package com.morendi.Oola;

import java.awt.Graphics;

public abstract class OolaRenderer {

	private OolaGame game;
	public OolaRenderer(OolaGame game) {
		this.game = game;
	}
	
	public OolaGame getGame() {
		return this.game;
	}
	
	public abstract void render(Graphics g);
}
