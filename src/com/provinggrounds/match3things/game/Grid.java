package com.provinggrounds.match3things.game;


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
    int[][] gameGrid;

    /*
     * Width and height must always be provided to Grid - should not be constructed without initializing/passing in those values
     */
    @SuppressWarnings("unused")
    private Grid() {

    }

    Grid(int width, int height) {
	this.width = width;
	this.height = height;

	initGameGridArray();
    }

    /*
     * Creates grid, allocates storage
     */
    private void initGameGridArray() {
	gameGrid = new int[width][height];
    }

    /*
     * Initialize/fill game grid with random objects
     */
    private void fillGameGrid() {

    }
}
