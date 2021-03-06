package com.tutorial.main;

import java.awt.Graphics;
import java.util.LinkedList;

/*
 * Updates all of the objects within the Game
 * 
 * Loop through all objects in the game, update them, and render them in-game.
 */
public class Handler {
	
	LinkedList<GameObject> object = new LinkedList<GameObject>();
	
	public void tick() {
		
		/*
		 * go through all of our GameObjects and do stuff
		 */
		for(int i = 0; i < object.size(); i++) {
			
			// get ID of object
			GameObject tempObject = object.get(i);
			
			tempObject.tick();
		}
	}
	
	public void render(Graphics g) {
		
		/*
		 * go through all of our GameObjects and render
		 */
		for(int i = 0; i < object.size(); i++) {
			
			GameObject tempObject = object.get(i);
			
			tempObject.render(g);
		}
		
	}
	
	public void addObject(GameObject object) {
		this.object.add(object);
	}
	
	public void removeObject(GameObject object) {
		this.object.remove(object);
	}
	

}
