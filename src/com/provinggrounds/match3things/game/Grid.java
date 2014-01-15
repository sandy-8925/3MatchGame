package com.provinggrounds.match3things.game;


/*
 * Represents a game grid. Contains a rectangular grid of Block objects.
 */
public class Grid {
    int width;
    int height;
    Block[][] gameGrid;

    /*
     * Width and height must always be provided to Grid - should not be constructed without initializing/passing in those values
     */
    @SuppressWarnings("unused")
    private Grid() {

    }

    Grid(int width, int height) {
	this.width = width;
	this.height = height;
    }
}
