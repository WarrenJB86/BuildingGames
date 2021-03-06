package com.tutorial.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable{
	
	private static final long serialVersionUID = 1550691097823471818L;
	
	public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9;
	
	private Thread thread;
	private boolean running = false;
	
	private Random r;

	
	private Handler handler;
	private HUD hud;
	
	public Game() {
		
		/*
		 * this needs to be above new Window() so that the game
		 * knows what Handler() is.
		 */
		handler = new Handler();
		
		/*
		 * Listen for key and do stuff
		 */
		this.addKeyListener(new KeyInput(handler));
		
		r = new Random();
		
		new Window(WIDTH, HEIGHT, "My Game!!", this);
		
		hud = new HUD();
		
		// Player 1
		new Player(WIDTH/2-32, HEIGHT/2-32, ID.Player, handler);
		
		// BasicEnemy
		new BasicEnemy(WIDTH/2-32, HEIGHT/2-32, ID.BasicEnemy, handler);

	}

	/*
	 * Initializes the thread.
	 */
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		}catch(Exception e) {
			/*
			 * run error bug into the counsel if it fails.
			 */
			e.printStackTrace();
		}
	}
	
	/*
	 * The game loop
	 */
	public void run() {
		
		// makes it so you don't have to click on the screen
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			
			while(delta >= 1) {
				tick();
				delta--;
			}
			if(running) {
				render();
			}
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				//System.out.println("FPS: " + frames);
				frames = 0;
			}
		}
		stop();
	}
	
	private void tick() {
		handler.tick();
		hud.tick();
	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			/*
			 * how many buffers created: 3
			 * 
			 */
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		handler.render(g);
		hud.render(g);
		
		g.dispose();
		bs.show();
	}
	
	/*
	 * This method will prevent the player from going past
	 * the HEIGHT and WIDTH of the game.
	 */
	public static int clamp(int value, int min, int max) {
		if(value >= max) {
			return value = max;
		} else if(value <= min) {
			return value = min;
		} else {
			return value;
		}
	}
	
	public static void main(String[] args) {
		
		new Game();
	}

}
