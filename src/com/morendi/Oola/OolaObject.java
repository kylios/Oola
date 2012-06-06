package com.morendi.Oola;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public abstract class OolaObject {
	
	protected static final String NAME = "Object";

	private OolaGame game;
	
	protected int x;
	protected int y;
	protected int width;
	protected int height;
	
	private int id;
	private static int next_id = 0;
	
	private boolean destroy = false;
	
	public OolaObject(OolaGame game) {
		this.game = game;
		
		this.id = next_id++;
	}
	
	protected OolaObject(OolaGame game, int width, int height) {
		this.game = game;
		this.width = width;
		this.height = height;
		
		this.id = next_id++;
	}
	
	protected OolaObject(OolaGame game, int width, int height, int x, int y) {
		this.game = game;
		this.width = width;
		this.height = height;
		this.x = x;
		this.y = y;

		this.id = next_id++;
	}
	
	protected OolaGame getGame() {
		return this.game;
	}
	
	public String getName() {
		return NAME;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
	
	public void setX(int x) {
		this.x = x;
		this.checkX();
	}
	public void setY(int y) {
		this.y = y;
		this.checkY();
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	
	public void addX(int x) {
		this.x += x;
		this.checkX();
	}
	public void addY(int y) {
		this.y += y;
		this.checkY();
	}
	
	protected void checkX() {
		int gameWidth = game.getGameWidth();
		switch (this.getOffScreenBehavior()) {
		case OFFSCREEN_DENY_STOP:
			int halfWidth = this.width / 2;
			if (this.x + halfWidth > gameWidth) {
				this.x = gameWidth - halfWidth;
			} else if (this.x - halfWidth < 0) {
				this.x = halfWidth;
			}
			break;
		case OFFSCREEN_DENY_KILL:
			this.destroy();
			break;
		}
	}
	protected void checkY() {
		int gameHeight = game.getGameHeight();
		switch (this.getOffScreenBehavior()) {
		case OFFSCREEN_DENY_STOP:
			int halfHeight = this.height / 2;
			if (this.y + halfHeight > gameHeight) {
				this.y = gameHeight - halfHeight;
			} else if (this.y - halfHeight < 0) {
				this.y = halfHeight;
			}
			break;
		case OFFSCREEN_DENY_KILL:
			this.destroy();
			break;
		}
	}
	
	private List<OolaObjectDestroyEventListener> _listeners = new ArrayList<OolaObjectDestroyEventListener>();
	public synchronized void addEventListener(OolaObjectDestroyEventListener listener)	{
		_listeners.add(listener);
	}
	public synchronized void removeEventListener(OolaObjectDestroyEventListener listener)	{
		_listeners.remove(listener);
	}
	
	public void destroy() {
		
		System.out.println("Destroying object " + this);
		/*
		OolaObjectDestroyEvent event = new OolaObjectDestroyEvent(this);
		Iterator<OolaObjectDestroyEventListener> i = _listeners.iterator();
		while(i.hasNext())	{
			i.next().handleOolaObjectDestroyEvent(event);
		}
		*/
		this.destroy = true;
	}
	
	public boolean isDestroyed() {
		return this.destroy;
	}
	
	public enum OffscreenBehavior {
		OFFSCREEN_ALLOW,		// Allow objects off the screen
		OFFSCREEN_DENY_KILL,	// Kill objects once they move fully off the screen
		OFFSCREEN_DENY_STOP		// Stop an object at the edge of the screen
	}
	
	public abstract OffscreenBehavior getOffScreenBehavior();
	public abstract void move();
	public abstract void render(Graphics g);
	
	public String toString() {
		return "[" + getName() + "] (" + this.getX() + "," + this.getY() + ")";
	}
	
	@SuppressWarnings("serial")
	public class OolaObjectDestroyEvent extends java.util.EventObject {

		public OolaObjectDestroyEvent(OolaObject arg0) {
			super(arg0);
		}
		
	}
	
	public interface OolaObjectDestroyEventListener {
		public void handleOolaObjectDestroyEvent(OolaObjectDestroyEvent e);
	}
	
	public boolean isEqual(Object o) {
		OolaObject obj = (OolaObject) o;
		return this.id == obj.id;
	}
	
	public enum Direction {
		UP, DOWN, LEFT, RIGHT
	}
	public interface MovementCurve {
		public int getCurveForTick(int tick, Direction dir);
	}
}
