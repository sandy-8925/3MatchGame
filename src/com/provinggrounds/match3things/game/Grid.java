package com.provinggrounds.match3things.game;

import java.util.Random;

import com.provinggrounds.match3things.util.Direction;
import com.provinggrounds.match3things.util.Orientation;


/*
 * Represents a game grid. Contains a rectangular grid of numbers.
 * All numbers must be positive integers greater than 0, between 1 and numObjectTypes(both inclusive)
 */
public class Grid {
    private static final int NUM_MATCHING_SETS = 4;
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
		//create matching sets first, then fill in remaining elements with random numbers

		//pick 4 random positions
    	Coord[] matchingSetStartPoints = getRandomGridPositions(NUM_MATCHING_SETS);

    	//
    	for(Coord currentPoint : matchingSetStartPoints ) {
    		//decide match set orientation - vertical/horizontal, up/down/left/right
    		Direction matchSetDirection = getRandomMatchSetDirection(Orientation.getRandom());
    		//decide block type
    		//generate block coords and fill in grid
    	}

		for(int counter = 0; counter < gameGrid.length; counter++) {
			if(gameGrid[counter] == null)
				gameGrid[counter] = randomNumberGenerator.nextInt(numObjectTypes) + 1;
		}
    }

	private Direction getRandomMatchSetDirection(Orientation orientation) {
		if(Orientation.HORIZONTAL == orientation)
			return Direction.getRandomHorizontalDirection();

		return Direction.getRandomVerticalDirection();
	}

    private Coord[] getRandomGridPositions(int numElements) {
    	Coord[] gridPositions = new Coord[numElements];
		for(Coord currentPoint : gridPositions) {
			currentPoint.x = randomNumberGenerator.nextInt(width);
			currentPoint.y = randomNumberGenerator.nextInt(height);
		}
		return gridPositions;
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
