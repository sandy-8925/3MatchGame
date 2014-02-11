package com.provinggrounds.match3things.util;

public enum Orientation {
	VERTICAL,
	HORIZONTAL;

	private static final Orientation[] VALUES = values();

	public static Orientation getRandom() {
		return VALUES[RandomUtils.randomNumberGenerator.nextInt(VALUES.length)];
	}
}
