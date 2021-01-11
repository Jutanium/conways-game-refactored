package com.jutanium;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
//Copyright 2013 Jutanium
//Change this code however you want, but if you leave any of the methods the same, make sure to credit me for them!

public class Main {

	/**
	 * @param args
	 */
	static final short numRows = 10; //Number of the rows in the game
	static final short numColumns = 10; //Number of columns in the game
	static final int interval = 1000; //Interval of timer in milliseconds
	static final char aliveChar = '+';
	static final char deadChar = '=';

	public static void main(String[] args) {
        Point[] blinker = {new Point(0, 3), new Point(1,3), new Point(2, 3)};
        Point[] glider = {
				new Point(3, 1),
				new Point(4, 2),
				new Point(4, 3),
				new Point(3, 3),
				new Point(2, 3)
        };

        Game game = new Game(numRows, numColumns, glider);

		//Create a new timer
		Timer timer = new Timer();
		//Timers need timertasks
		TimerTask timertask = new TimerTask() {
			//Timertasks need a method called run
			public void run() {
			    game.nextState();

			    //Empty lines between draws
			    for (int i = 0; i < 10; i++) {
					System.out.println();
				}

				game.printBoard(aliveChar, deadChar);
			}
		};
		//Set the timer to start in 1 second and to go every certain amount of milliseconds.
		timer.scheduleAtFixedRate(timertask, 1000, interval);
	}

}
