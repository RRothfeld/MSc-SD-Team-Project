package javaball.enums;

/** The possible locations for JavaBall matches */
public enum Location {
	NORTH("North"), CENTRAL("Central"), SOUTH("South");

	/** Name of enum as instance variable */
	private final String LocationString;

	/**
	 * Enum constructor with name
	 * @param name of the enum
	 */
	private Location(final String name) {
		LocationString = name;
	}

	/**
	 * @return the name of the enum as a String
	 */
	public String toString() {
		return LocationString;
	}
}