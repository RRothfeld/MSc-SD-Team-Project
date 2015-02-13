package javaball.enums;

/** The possible locations for JavaBall matches */
public enum Location {
	NORTH("North"), CENTRAL("Central"), SOUTH("South");
	private final String LocationString;

	private Location(final String s) {
		LocationString = s;
	}

	@Override
	public String toString() {
		return LocationString;
	}
}