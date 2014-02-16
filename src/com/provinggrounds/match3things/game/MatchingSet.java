package com.provinggrounds.match3things.game;

import java.util.Collection;
import java.util.HashSet;

public class MatchingSet {
    private int blockType;
    private Collection<Coord> coordinates;

    public MatchingSet() {
	coordinates = new HashSet<Coord>();
    }

    int length() {
	return coordinates.size();
    }

    public int getBlockType() {
        return blockType;
    }

    public void setBlockType(int blockType) {
        this.blockType = blockType;
    }

    public Collection<Coord> getCoordinates() {
        return coordinates;
    }
}
