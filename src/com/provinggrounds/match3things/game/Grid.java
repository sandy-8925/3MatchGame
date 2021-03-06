package com.provinggrounds.match3things.game;

import java.util.Collection;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import com.provinggrounds.match3things.util.Direction;


/*
 * Represents a game grid. Contains a rectangular grid of numbers.
 * All numbers must be positive integers greater than 0, between 1 and numObjectTypes(both inclusive)
 */
public class Grid {
    private static final int NUM_MATCHING_SETS = 4;
    private static final int NUMBER_ELEMENTS_MATCHING_SET = 3;
    private int width;
    private int height;
    /**
     * Represents number of unique object types
     */
    int numObjectTypes;
    Integer[] gameGrid;
    static final Random randomNumberGenerator = new Random();

    Grid(int width, int height, int numObjectTypes) {
	this.width = width;
	this.height = height;
	this.numObjectTypes = numObjectTypes;
    }

    public static Grid createGrid(int width, int height, int numObjectTypes) {
	Grid newGrid = new Grid(width, height, numObjectTypes);

	newGrid.initGameGridArray();
	newGrid.fillGameGrid();

	return newGrid;
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
    	Set<Coord> matchingSetStartPoints = getRandomGridPositions(NUM_MATCHING_SETS);

    	for(Coord currentPoint : matchingSetStartPoints ) {
    		//decide match set orientation - vertical/horizontal, up/down/left/right
    		Direction matchSetDirection = getRandomMatchSetDirection(currentPoint);
    		//decide block type
    		int blockType = randomNumberGenerator.nextInt(numObjectTypes) + 1;
    		//generate block coords and fill in grid
    		fillMatchingSet(currentPoint, matchSetDirection, blockType);
    	}

		for(int counter = 0; counter < gameGrid.length; counter++) {
			if(gameGrid[counter] == null)
				gameGrid[counter] = randomNumberGenerator.nextInt(numObjectTypes) + 1;
		}
    }

    private void fillMatchingSet(Coord currentPoint, Direction matchSetDirection, int blockType) {
    	//generate set coords based on starting point and match set direction
    	Coord[] matchingSetCoords = new Coord[NUMBER_ELEMENTS_MATCHING_SET];
    	matchingSetCoords[0] = currentPoint;
    	switch(matchSetDirection) {
    		case UP:
    			for(int counter = 1; counter<NUMBER_ELEMENTS_MATCHING_SET; counter++) {
    				Coord currentPointCoords = new Coord(currentPoint.x, currentPoint.y - counter);
    				matchingSetCoords[counter] = currentPointCoords;
    			}
    			break;
    		case DOWN:
    			for(int counter = 1; counter<NUMBER_ELEMENTS_MATCHING_SET; counter++) {
    				Coord currentPointCoords = new Coord(currentPoint.x, currentPoint.y + counter);
    				matchingSetCoords[counter] = currentPointCoords;
    			}
    			break;
    		case LEFT:
    			for(int counter = 1; counter<NUMBER_ELEMENTS_MATCHING_SET; counter++) {
    				Coord currentPointCoords = new Coord(currentPoint.x - counter, currentPoint.y);
    				matchingSetCoords[counter] = currentPointCoords;
    			}
    			break;
    		case RIGHT:
    			for(int counter = 1; counter<NUMBER_ELEMENTS_MATCHING_SET; counter++) {
    				Coord currentPointCoords = new Coord(currentPoint.x + counter, currentPoint.y);
    				matchingSetCoords[counter] = currentPointCoords;
    			}
    			break;
			default:
				break;
    	}
    	for(Coord elementCoord : matchingSetCoords) {
    		gameGrid[width*elementCoord.y + elementCoord.x] = blockType;
    	}
	}

    /*
     * Determine which directions are valid (match set must stay within
     * grid boundary), given a starting point and Grid dimensions
     * and randomly pick a valid direction
     */
	private Direction getRandomMatchSetDirection(Coord matchSetStartingPoint) {
		Direction[] validDirections = getValidMatchSetDirections(matchSetStartingPoint);
		//return random valid direction
		return validDirections[randomNumberGenerator.nextInt(validDirections.length)];
	}

    private Direction[] getValidMatchSetDirections(Coord matchSetStartingPoint) {
    	Set<Direction> validDirectionSet = new HashSet<Direction>();
    	//check UP
		if(matchSetStartingPoint.y - 2 >= 0) validDirectionSet.add(Direction.UP);
    	//check DOWN
		if(matchSetStartingPoint.y + 2 < height) validDirectionSet.add(Direction.DOWN);
    	//check LEFT
		if(matchSetStartingPoint.x - 2 >= 0) validDirectionSet.add(Direction.LEFT);
    	//check RIGHT
		if(matchSetStartingPoint.x + 2 < width) validDirectionSet.add(Direction.RIGHT);
		return validDirectionSet.toArray(new Direction[validDirectionSet.size()]);
	}

    private Set<Coord> getRandomGridPositions(int numElements) {
    	Set<Coord> gridPositions = new HashSet<Coord>();
		for(int counter=0; counter<numElements; counter++) {
			int x = randomNumberGenerator.nextInt(width);
			int y = randomNumberGenerator.nextInt(height);
			Coord currentPoint = new Coord(x, y);
			gridPositions.add(currentPoint);
		}
		return gridPositions;
    }
    
    public Collection<MatchingSet> findMatches() {
	Set<MatchingSet> matchingSets = new HashSet<MatchingSet>();
	//find horizontal matches
	matchingSets.addAll(findHorizontalMatches());
	//find vertical matches
	matchingSets.addAll(findVerticalMatches());
	//mark all matching blocks deleted (make them 0)
	//markMatchingBlocksDeleted(matchingSets);
	return matchingSets;
    }

    private Collection<MatchingSet> findVerticalMatches() {
        // for each row, find matches
        Set<MatchingSet> verticalMatchingSets = new HashSet<MatchingSet>();
        for (int columnCounter = 0; columnCounter < width; columnCounter++) {
            verticalMatchingSets.addAll(findMatchesInColumn(columnCounter));
        }
        return verticalMatchingSets;
    }

    private Collection<MatchingSet> findMatchesInColumn(final int columnNumber) {
        Set<MatchingSet> matchingSetsInColumn = new HashSet<MatchingSet>();
        int currentIndex = 0;
        while(currentIndex < height) {
            int currentSequenceNumber = gameGrid[currentIndex*width+columnNumber];
            int begIndex = currentIndex;
            int currentSequenceLength = 1;
            currentIndex++;
            while(currentIndex < height && gameGrid[currentIndex*width+columnNumber].equals(currentSequenceNumber)) {
                currentIndex++;
                currentSequenceLength++;
            }
            if(currentSequenceLength >= NUMBER_ELEMENTS_MATCHING_SET) { //if matching set, save it
                int endIndex = currentIndex - 1;
                matchingSetsInColumn.add(createMatchingSetInColumn(begIndex, endIndex, columnNumber, currentSequenceNumber));
            }
        }
        return matchingSetsInColumn;
    }

    private MatchingSet createMatchingSetInColumn(int begIndex, int endIndex, int columnNumber, int blockType) {
        MatchingSet matchingSet = new MatchingSet();
        int sequenceLength = endIndex - begIndex + 1;
        Coord[] blockCoords = new Coord[sequenceLength];
        matchingSet.setBlockType(blockType);
        for(int counter=begIndex; counter<=endIndex; counter++) {
            Coord newCoord = new Coord(columnNumber, counter);
            blockCoords[counter-begIndex] = newCoord;
        }
        matchingSet.setCoordinates(blockCoords);
        return matchingSet;
    }

    private Collection<MatchingSet> findHorizontalMatches() {
	//for each row, find matches
	Set<MatchingSet> horizontalMatchingSets = new HashSet<MatchingSet>();
	for(int rowCounter=0; rowCounter<height; rowCounter++) {
	    horizontalMatchingSets.addAll(findMatchesInRow(rowCounter));
	}
	return horizontalMatchingSets;
    }

    private Collection<MatchingSet> findMatchesInRow(int rowNumber) {
	Set<MatchingSet> matchingSetsInRow = new HashSet<MatchingSet>();
	int beginningOfRow = rowNumber*width;
	int endOfRow = (rowNumber+1)*width - 1;
	int currentIndex = beginningOfRow;
	while(currentIndex <= endOfRow) {
	    int currentSequenceNumber = gameGrid[currentIndex];
	    int begIndex = currentIndex;
	    int currentSequenceLength = 1;
	    currentIndex++;
	    while(currentIndex <= endOfRow && gameGrid[currentIndex].equals(currentSequenceNumber)) {
		    currentIndex++;
		    currentSequenceLength++;
		}
	    int endIndex = currentIndex - 1;
	    if(currentSequenceLength >= NUMBER_ELEMENTS_MATCHING_SET) { //if matching set, save it
		matchingSetsInRow.add(createMatchingSetInRow(begIndex - beginningOfRow, endIndex - beginningOfRow, rowNumber, currentSequenceNumber));
	    }
	}
	return matchingSetsInRow;
    }

    private MatchingSet createMatchingSetInRow(int beg, int end, int rowNumber,
	    int blockType) {
	MatchingSet matchingSet = new MatchingSet();
	int sequenceLength = end - beg + 1;
	Coord[] blockCoords = new Coord[sequenceLength];
	matchingSet.setBlockType(blockType);
	for(int counter=beg; counter<=end; counter++) {
	    Coord newCoord = new Coord(counter, rowNumber);
	    blockCoords[counter-beg] = newCoord;
	}
	matchingSet.setCoordinates(blockCoords);
	return matchingSet;
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

    public void markMatchingBlocksDeleted(Collection<MatchingSet> matchingSets) {
        for(MatchingSet ms : matchingSets ) {
            for( Coord position : ms.getCoordinates() ) {
                gameGrid[position.y*width + position.x] = 0;
            }
        }
    }
}
