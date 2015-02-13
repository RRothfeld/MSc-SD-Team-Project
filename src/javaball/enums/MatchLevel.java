package javaball.enums;

/** The level of play in a match */
public enum MatchLevel {
	JUNIOR("Junior"), SENIOR("Senior");
	private final String LevelString;

	private MatchLevel(final String levels) {
		LevelString = levels;
	}

	@Override
	public String toString() {
		return LevelString;
	}
}