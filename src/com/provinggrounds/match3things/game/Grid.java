package com.provinggrounds.match3things.game;


/*
 * Represents a game grid. Contains a rectangular grid of numbers.
 */
public class Grid {
    int width;
    int height;
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

	createGameGrid();
    }

    /*
     * Creates grid, allocates storage
     */
    private void createGameGrid() {
	gameGrid = new int[width][height];
    }

    /*
     * Initialize/fill game grid with random objects
     */
    private void fillGameGrid() {

    }
}
