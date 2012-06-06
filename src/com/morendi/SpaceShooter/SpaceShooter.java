package com.morendi.SpaceShooter;

import java.applet.Applet;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

import com.morendi.Oola.OolaGame;
import com.morendi.Oola.OolaObject;
import com.morendi.Oola.OolaObject.OolaObjectDestroyEvent;

public class SpaceShooter extends OolaGame {
	
	protected static final int GAME_WIDTH = 900;
	protected static final int GAME_HEIGHT = 480;
	
	protected static final long FRAME_DELAY = 20;
	
	// Game state variables
	
	private SpaceShip player;
	
	private LinkedList<Level> upcomingLevels;
	private LinkedList<CompletedLevel> completedLevels;

	public SpaceShooter(Applet context) {
		super(context);
		
		initObjects();
	}

	@Override
	protected void initObjects() {
		player = new SpaceShip(this, 80, GAME_HEIGHT / 2);
		player.addEventListener(this);
		
		addObject(player);
		
		upcomingLevels = new LinkedList<Level>();
		completedLevels = new LinkedList<CompletedLevel>();
		
		upcomingLevels.add(new Level0(this));
		
		for (Level l:upcomingLevels) {
			l.init();
		}
	}

	@Override
	public int getGameWidth() {
		return GAME_WIDTH;
	}

	@Override
	public int getGameHeight() {
		return GAME_HEIGHT;
	}
	
	public int getInitialGameWidth() {
		return GAME_WIDTH;
	}
	
	public int getInitialGameHeight() {
		return GAME_HEIGHT;
	}
	
	public LinkedList<OolaObject> getObjects() {
		return this.objects;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode())
		{
			case KeyEvent.VK_LEFT:
				this.player.moveLeft();
				break;
			case KeyEvent.VK_RIGHT:
				this.player.moveRight();
				break;
			case KeyEvent.VK_UP:
				this.player.moveDown();
				break;
			case KeyEvent.VK_DOWN:
				this.player.moveUp();
				break;
			case KeyEvent.VK_SPACE:
				this.player.shoot();
				break;
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleOolaObjectDestroyEvent(OolaObjectDestroyEvent e) {
		OolaObject o = (OolaObject) e.getSource();
		removeObject(o);
	}

	@Override
	public void run() {
		
		LinkedList<OolaObject> toDestroy = new LinkedList<OolaObject>();
		
		long currentTime, lastUpdateTime;
		currentTime = lastUpdateTime = System.currentTimeMillis();
		while (true) {
			long sleepTime = FRAME_DELAY - (currentTime - lastUpdateTime);
			try {
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			currentTime = System.currentTimeMillis();
			synchronized (this) {
				for (OolaObject o:objects) {
					o.move();
					if (o.isDestroyed()) {
						toDestroy.add(o);
					}
				}
				for (OolaObject o:toDestroy) {
					objects.remove(o);
				}
			}
			toDestroy.clear();
			
			this.getContext().repaint();
			lastUpdateTime = System.currentTimeMillis();
		}
	}
}
