package com.morendi.SpaceShooter;

public class Level0 extends Level {

	public Level0(SpaceShooter game) {
		super(game);
	}

	@Override
	public void init() {
		SpaceShooter game = this.getGame();
		
		for (int i = 0; i < 32; i++) {	
			game.addObject(new Enemy0(game, 
					game.getGameWidth() + i * 128, 
					20 + (96 * i) % (game.getGameHeight() - 20)));
		}
	}

}
