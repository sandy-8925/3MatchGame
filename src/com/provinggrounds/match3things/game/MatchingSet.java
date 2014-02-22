package com.provinggrounds.match3things.game;

public class MatchingSet {
    private int blockType;
    private Coord[] coordinates;

    int length() {
	return coordinates.length;
    }

    public int getBlockType() {
        return blockType;
    }

    public void setBlockType(int blockType) {
        this.blockType = blockType;
    }

    public Coord[] getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coord[] coordinates) {
        this.coordinates = coordinates;
    }

    /**
     * Returns all information about the matching set in a particular
     * format which is subject to change.
     *
     * @return    String representation of MatchingSet object
     *
     * If any of the Coord elements are null, they will be skipped
     */
    @Override
    public String toString() {
	StringBuilder matchSetString = new StringBuilder("");
	matchSetString.append(String.format("Match %d(%d)):", length(), blockType));
	for(Coord currentPoint : coordinates) {
	   if(currentPoint!=null) matchSetString.append(String.format(" %s", currentPoint.toString()));
	}
	return matchSetString.toString();
    }
}
