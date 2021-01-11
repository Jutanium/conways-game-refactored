package com.jutanium;

public class Cell {

	public Boolean alive;
	public int numNeighbors;
	public int x;
	public int y;

	public Cell(int x, int y, Boolean alive) {
		this.x = x;
		this.y = y;
		this.alive = alive;
		this.numNeighbors = 0;
	}
}

