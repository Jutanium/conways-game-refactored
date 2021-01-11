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
	static short numRows = 10; //Number of the rows in the game
	static short numColumns = 10; //Number of columns in the game
	static int interval = 1000; //Interval of timer in milliseconds
	static ArrayList<Cell> cells = new ArrayList<Cell>(); //The list that holds the cell
	public static void main(String[] args) {
		//Add the appropriate number of rows and cells.
		for (int i = 0; i < numRows * numColumns; i++) 
			cells.add(new Cell(false));
		
		//Uncomment one of the following basic simulations:
		
		//Blinker

		ChangeCell(new Point(0, 3), true);
		ChangeCell(new Point(1, 3), true);
		ChangeCell(new Point(2, 3), true);

		
		//Glider


        /*
		 ChangeCell(new Point(3, 1), true);
		 ChangeCell(new Point(4, 2), true);
		 ChangeCell(new Point(4, 3), true);
		 ChangeCell(new Point(3, 3), true);
		 ChangeCell(new Point(2, 3), true);
		*/
		
		//Ten cell row
		/* for(int i = 0; i < 10; i++) 
			ChangeCell(new Point(i + 2, 6), true);
		 */
		
		//Create a new timer
		Timer timer = new Timer();
		//Timers need timertasks
		TimerTask timertask = new TimerTask() {
			//Timertasks need a method called run
			public void run() {
				//This is a new ArrayList of cells that holds the next generation of cells
				ArrayList<Cell> newCells = new ArrayList<Cell>(); 
				for (Cell cell : cells) { //Check how many cells each cell is touching
					int index = cells.indexOf(cell); //Get the position of the cell within the ArrayList
					short amountTouching = 0;
					/* The next thing we do is check how many live cells the cell is touching.
					 * That means we have to check the cell to the left, right, top, bottom, top-left,
					 * top-right, bottom-left, and bottom-right of the cell.
					 */
					//To get the cell to the right of the cell, we add 1 to the index.
					//But first, we have to make sure that that index is less than the size of the
					//ArrayList (so we don't get an error saying that the index is out of range.)
					if (index + 1 < cells.size() && cells.get(index + 1).alive == true)
						amountTouching++;
					//To get the cell to the left of the cell, we subtract 1 to the index.
					if (index - 1 > -1 && cells.get(index - 1).alive == true)
						amountTouching++;
					//To get the cell below the cell, we add the number of columns to the index.
					if (index + numColumns < cells.size() && cells.get(index + numColumns).alive == true)
						amountTouching++;
					//To get the cell above the cell, we subtract the number of columns to the index.
					if (index - numColumns > -1 && cells.get(index - numColumns).alive == true)
						amountTouching++;
					//To get the cell to the top-right, we add the number of columns and then add 1.
					if (index + numColumns + 1 < cells.size() && cells.get(index + numColumns + 1).alive == true)
						amountTouching++;
					//To get the cell to the top-left, we add the number of columns and then subtract 1.
					if (index + numColumns - 1 < cells.size() && cells.get(index + numColumns - 1).alive == true)
						amountTouching++;
					//To get the cell to the bottom-right, we subtract the number of columns and add 1.
					if (index - numColumns + 1 > -1 && cells.get(index - numColumns + 1).alive == true)
						amountTouching++;
					//To get the cell to the bottom-left, we subtract the number of columns and subtract 1.
					if (index - numColumns - 1 > -1 && cells.get(index - numColumns - 1).alive == true)
						amountTouching++;
					if (cell.alive) //If the cell we are on is alive
					{	
						if (amountTouching < 2) //If it is touching less than 2 live cells
							newCells.add(new Cell(false)); //Then in the new generation it will be dead.
						else if (amountTouching == 2 || amountTouching == 3) //If it is touching 2 or 3 live cells
							newCells.add(new Cell(true)); //Then in the new generation it will be alive.
						else if (amountTouching > 3)
							newCells.add(new Cell(false)); //Then in the new generation it will be dead.
					}
					else if (amountTouching == 3) //If the cell is dead and it is touching exactly 3 live cells
							newCells.add(new Cell(true)); //Then in the new generation it will be alive.
					else newCells.add(new Cell(false)); //If the cell is dead and is not touching exactly 3 live cells,
														//Then in the new generation it will be dead.
					
				}
				cells = newCells; //Set the cell ArrayList to the new generation
				UpdateCells(); //Call the update cells method which displays the cells.
			}
		};
		//Set the timer to start in 1 second and to go every certain amount of milliseconds.
		timer.scheduleAtFixedRate(timertask, 1000, interval);

	}
	//Here's a method that makes it easier for us to change the cells
	private static void ChangeCell(Point cell, boolean alive)
	{
		if (cell.x < numColumns && cell.y < numRows)
		{
			cells.get(numColumns * cell.y + cell.x).alive = alive;

		}
	}
	//This method prints out the cells
	private static void UpdateCells() {
		//Print a bunch of empty lines so the next print looks like another animation frame
		for (int i = 0; i <= numRows; i++)
			System.out.println();

		for (int y = 0; y < numRows; y++)
		{	
			for (int x = 0; x < numColumns; x++) //Loop through each cell
			{
				//In order to get the index of the cell based off of its X and Y coords,
				//we use this handy formula: index = numColumns * y + x
				if (cells.get(numColumns * y + x).alive == true) //If the cell is alive...
					System.out.print(Cell.aliveChar); //print the alive character
				else System.out.print(Cell.deadChar); //If it is dead, print the dead character
			}
			System.out.println(); //Go to the next line.
		}
		
	}

}
