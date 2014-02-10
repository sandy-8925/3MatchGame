package com.provinggrounds.match3things.game;

import java.util.Random;


/*
 * Represents a game grid. Contains a rectangular grid of numbers.
 * All numbers must be positive integers greater than 0, between 1 and numObjectTypes(both inclusive)
 */
public class Grid {
    int width;
    int height;
    /*
     * Represents number of unique object types
     */
    int numObjectTypes;
    Integer[] gameGrid;
    static final Random randomNumberGenerator = new Random();

    /*
     * Width and height must always be provided to Grid - should not be constructed without initializing/passing in those values
     */
    private Grid() {
    }

    public Grid(int width, int height, int numObjectTypes) {
	this.width = width;
	this.height = height;
	this.numObjectTypes = numObjectTypes;

	initGameGridArray();
	fillGameGrid();
    }

    /*
     * Creates grid, allocates storage
     */
    private void initGameGridArray() {
	gameGrid = new Integer[height*width];
    }

    /*
     * Initialize/fill game grid with random objects
     */
    private void fillGameGrid() {
    for(int counter = 0; counter < gameGrid.length; counter++) {
		gameGrid[counter] = randomNumberGenerator.nextInt(numObjectTypes) + 1;
	}
    }

    public Integer[] getGameGrid() {
        return gameGrid;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
