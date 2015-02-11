/** The level of play in a match */
public enum Level {
	JUNIOR("Junior"), SENIOR("Senior");
	private final String LevelString;

	private Level(final String levels) {
		LevelString = levels;
	}

	@Override
	public String toString() {
		return LevelString;
	}
}