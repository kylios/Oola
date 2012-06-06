package com.morendi.SpaceShooter;

public class CompletedLevel {
	
	private int score;
	private Level level;
	
	public CompletedLevel(Level level, int score) {
		this.score = score;
		this.level = level;
	}
	
	public int getScore() {
		return score;
	}
	
	public Level getLevel() {
		return level;
	}
}
