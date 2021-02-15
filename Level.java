package a11847329;

public enum Level {

	BEGINNER("Anfänger"), NORMAL("Normal"), ADVANCED("Fortgeschritten"), PROFESSIONAL("Profi");

	String mappedName;

	Level(String mappedName) {

		if (mappedName == null || mappedName.isEmpty()) {
			throw new IllegalArgumentException("err");
		} else {
			this.mappedName = mappedName;
		}

	}

	public Level next() {
		if (getMappedName().equals("Profi")) {
			return Level.PROFESSIONAL;
		} else {
			return Level.values()[this.ordinal() + 1];
		}
	}

	public String getMappedName() {

		return mappedName;
	}

	@Override
	public String toString() {
		return mappedName;
	}
}