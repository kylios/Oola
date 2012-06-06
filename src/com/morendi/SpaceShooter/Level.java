package com.morendi.SpaceShooter;

public abstract class Level {

	private SpaceShooter game;
	
	public Level(SpaceShooter game) {
		this.game = game;
	}
	
	public SpaceShooter getGame() {
		return this.game;
	}
	
	public abstract void init();
}
