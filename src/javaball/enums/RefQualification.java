package javaball.enums;

/** The qualification of a referee */
public enum RefQualification {
	NJB("NJB"), IJB("IJB");

	/** Name of enum as instance variable */
	private final String LocationString;
	
	/** Highest level of referee qualification */
	public static int MAXIMUM = 4;

	/**
	 * Enum constructor with name
	 * @param name of the enum
	 */
	private RefQualification(final String name) {
		LocationString = name;
	}

	/**
	 * @return the name of the enum as a String
	 */
	public String toString() {
		return LocationString;
	}
}