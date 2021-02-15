package a11847329;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class Member implements Comparable<Member> {

	private String name;
	private Map<Sports, Level> sports = new LinkedHashMap<>();

	public Member(String name) {
		if (name == null || name.isEmpty()) {
			throw new IllegalArgumentException("Error");
		}
		this.name = name;
	}

	public Member(String name, Map<Sports, Level> sports) {

		this(name);
		if (sports == null || sports.isEmpty()) {
			throw new IllegalArgumentException("err");
		}

		for (Map.Entry<Sports, Level> entry : sports.entrySet()) {
			if (entry.getValue() == null || entry.getKey() == null) {
				throw new IllegalArgumentException("err");
			}
		}
		this.sports = Map.copyOf(sports);

	}
    //returns name
	public String getName() {
		return name;
	}
    //returns sports
	public Map<Sports, Level> getSports() {
		return new LinkedHashMap<>(sports);
	}
    //returns set
	public Set<Sports> getBillableSports() {

		return sports.keySet();
	}

	public Level learn(Sports newSports, Level newLevel) {

		Level currLevel = sports.get(newSports);
		if (currLevel == null) {
			Map<Sports, Level> tmpsports = new LinkedHashMap<>(getSports());
			tmpsports.put(newSports, Level.BEGINNER);
			this.sports = new LinkedHashMap<>(tmpsports);
			return Level.BEGINNER;
		}
		if (newLevel.ordinal() - currLevel.ordinal() >= 1) {
			currLevel = currLevel.next();
			Map<Sports, Level> tmpsports = new LinkedHashMap<>(getSports());
			// tmpsports.remove(newSports);
			tmpsports.put(newSports, currLevel);
			this.sports = new LinkedHashMap<>(tmpsports);
		}
		return currLevel;
	}

	@Override
	public String toString() {
		return "name: " + name + ", sports: " + sports.toString();
	}

	@Override
	public int compareTo(Member member) {
		return name.compareTo(member.getName());
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Member member = (Member) o;
		return Objects.equals(name, member.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}
}


