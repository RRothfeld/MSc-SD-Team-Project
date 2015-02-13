package javaball.enums;

/** The qualification of a referee */
public enum RefQualification {
	NJB("NJB"), IJB("IJB");
	private String value;
	public static int MAXIMUM = 4;

	private RefQualification(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return value;
	}
}