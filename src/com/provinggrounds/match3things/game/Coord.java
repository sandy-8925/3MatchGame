package com.provinggrounds.match3things.game;

public class Coord {
	int x;
	int y;
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Coord(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * @return String representation of Coord in following format: {x,y}
	 */
	@Override public String toString() {
	    return String.format("{%d,%d}", x, y);
	}
}
