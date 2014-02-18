package com.provinggrounds.match3things.game;

import java.util.Collection;
import java.util.HashSet;

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
}
