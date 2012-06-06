package com.morendi.Oola;

import java.applet.*;
import java.awt.Graphics;
import java.awt.Image;

import com.morendi.SpaceShooter.SpaceShooter;
import com.morendi.SpaceShooter.SpaceShooterRenderer;

@SuppressWarnings("serial")
public class Oola extends Applet {

	private OolaGame game;
	private OolaRenderer render;
	
	private Image offScreen;
	private Graphics offScreenG;
	private int lastGameWidth;
	private int lastGameHeight;
	
	private Thread t;
	
	public void init() {
		game = new SpaceShooter(this);
		render = new SpaceShooterRenderer(game);
		
		this.lastGameWidth = game.getGameWidth();
		this.lastGameHeight = game.getGameHeight();
		this.offScreen = createImage(lastGameWidth, lastGameHeight);
		this.offScreenG = this.offScreen.getGraphics();
		
		this.setFocusable(true);
		this.resize(game.getInitialGameWidth(), game.getInitialGameHeight());
		
		t = new Thread(game);
		t.start();
	}
	
	private void refreshOffScreen() {
		int width = this.game.getGameWidth();
		int height = this.game.getGameHeight();
		
		if (width != this.lastGameWidth || height != this.lastGameHeight){
			this.lastGameWidth = width;
			this.lastGameHeight = height;
			this.offScreen = createImage(width, height);
			this.offScreenG = this.offScreen.getGraphics();
		}
	}
	
	public void paint(Graphics g) {
		this.update(g);
	}
	
	public void update(Graphics g) {

		this.refreshOffScreen();
		this.render.render(offScreenG);
		g.drawImage(this.offScreen, 0, 0, this);
	}
}
