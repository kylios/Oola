package com.morendi.Oola;

import java.applet.Applet;
import java.awt.Image;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.LinkedList;

public abstract class OolaGame 
		implements KeyListener, MouseMotionListener, MouseListener, 
		Runnable, OolaObject.OolaObjectDestroyEventListener {

	private Applet context;
	private OolaImageLoader loader;
	
	protected LinkedList<OolaObject> objects;
	
	public OolaGame(Applet context) {
		this.context = context;
		this.loader = new OolaImageLoader(this.context.getCodeBase().getPath() + "../res/SpaceShooter");

		objects = new LinkedList<OolaObject>();
		
		this.context.addKeyListener(this);
		this.context.addMouseListener(this);
		this.context.addMouseMotionListener(this);
	}
	
	public Applet getContext() {
		return this.context;
	}
	
	public OolaImageLoader getLoader() {
		return this.loader;
	}
	
	protected abstract void initObjects();
	
	public Image getGraphicsBuffer() {
		return this.context.createImage(this.getGameWidth(), this.getGameHeight());
	}
	
	public void addObject(OolaObject o) {
		synchronized (this) {
			objects.add(o);
			//o.addEventListener(this);
		}
	}
	
	public void removeObject(OolaObject o) {
		synchronized (this) {
			objects.remove(o);
		}
	}
	
	public abstract int getGameWidth();
	public abstract int getGameHeight();
	public abstract int getInitialGameWidth();
	public abstract int getInitialGameHeight();

}
