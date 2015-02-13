package javaball.enums;

/** The level of play in a match */
public enum MatchLevel {
	JUNIOR("Junior"), SENIOR("Senior");

	/** Name of enum as instance variable */
	private final String LocationString;

	/**
	 * Enum constructor with name
	 * @param name of the enum
	 */
	private MatchLevel(final String name) {
		LocationString = name;
	}

	/**
	 * @return the name of the enum as a String
	 */
	public String toString() {
		return LocationString;
	}
}