package a11847329;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Trainer extends Member {

	private Map<Sports, Level> accreditations;

	public Trainer(String name, Map<Sports, Level> accreditations) {
		super(name, accreditations);
		this.accreditations = Map.copyOf(accreditations);
	}

	public Map<Sports, Level> getAccreditations() {
		return new HashMap<>(accreditations);
	}

	@Override
	public Set<Sports> getBillableSports() {
		Set<Sports> ret = new HashSet<>();
		for (Sports sport : super.getBillableSports()) {
			if (!accreditations.containsKey(sport)) {
				ret.add(sport);
			}
		}
		return ret;
	}

	@Override
	public String toString() {
		return super.toString() + ", accreditations: " + accreditations;
	}
}
