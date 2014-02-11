package com.provinggrounds.match3things.util;

public enum Direction {
	UP,DOWN,LEFT,RIGHT;

	private static final Direction[] VALUES = values();

	public static Direction getRandomVerticalDirection() {
		return VALUES[RandomUtils.randomNumberGenerator.nextInt(2)];
	}

	public static Direction getRandomHorizontalDirection() {
		return VALUES[RandomUtils.randomNumberGenerator.nextInt(2) + 2];
	}
}
