package com.morendi.SpaceShooter;

import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;

import com.morendi.Oola.OolaGame;
import com.morendi.Oola.OolaRenderer;
import com.morendi.Oola.OolaObject;

public class SpaceShooterRenderer extends OolaRenderer {

	public SpaceShooterRenderer(OolaGame game) {
		super(game);
	}

	@Override
	public void render(Graphics g) {
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getGame().getGameWidth(), getGame().getGameHeight());
		
		LinkedList<OolaObject> objects = ((SpaceShooter) getGame()).getObjects();
		for (OolaObject o:objects) {
			o.render(g);
		}
	}

}
