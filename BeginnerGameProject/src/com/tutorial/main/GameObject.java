package com.tutorial.main;

import java.awt.Graphics;
import java.awt.Rectangle;

/*
 * All the game objects.
 * 
 * Player objects and BadGuy objects are GameObjects
 */
public abstract class GameObject {

	/*
	 * Location within the game.
	 */
	protected int x, y;
	
	/*
	 * ID for each GameObject
	 */
	protected ID id;
	
	/*
	 * variables that control speed in x/y-direction 
	 */
	protected int velX, velY;

	/*
	 * handlers for GameObjects
	 */
	protected Handler handler;

	/*
	 * GameObject constructor
	 */
	public GameObject(int x, int y, ID id, Handler handler) {
		this.x = x;
		this.y = y;
		this.id = id;
		this.handler = handler;
		
		handler.addObject(this);
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);
	
	// intersects method for collusions
	public abstract Rectangle getBounds();

	/*
	 * Getters and Setters
	 */
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public ID getId() {
		return id;
	}

	public void setId(ID id) {
		this.id = id;
	}

	public int getVelX() {
		return velX;
	}

	public void setVelX(int velX) {
		this.velX = velX;
	}

	public int getVelY() {
		return velY;
	}

	public void setVelY(int velY) {
		this.velY = velY;
	}
	
	
	
	
	
}
