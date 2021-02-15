package a11847329;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class SportsClub {

	private String name;
	private BigDecimal feePerSports;
	private Set<Member> members = new LinkedHashSet<>();
	private Map<Sports, Set<Trainer>> offeredSports = new LinkedHashMap<>();

	public SportsClub(String name, BigDecimal feePerSports) {
     // throws IllegalArgumentException if name is null or empty
        // throws IllegalArgumentException if feePerSports is null and sets member variables
		if (name == null || feePerSports == null || name.isEmpty()) {
			throw new IllegalArgumentException("err");
		} else {
			this.name = name;
			this.feePerSports = feePerSports;
		}
	}

	public BigDecimal calculateMembershipFee(Member member) {

		if (!members.contains(member)) {
			throw new IllegalArgumentException("err");
		}
		Set<Sports> toBill = member.getBillableSports().stream().flatMap(n -> getSports().stream().filter(n::equals))
				.collect(Collectors.toSet());

		double sum = 0;
		for (Sports sport : toBill) {
			sum += Double.parseDouble(sport.getFee(getFeePerSports()).toString());
		}
		return new BigDecimal(Double.toString(sum));
	}

	public boolean registerSports(Member member, Sports sports, Level level) {

		if (!members.contains(member)) {
			throw new IllegalArgumentException("Errror");
		}
		Set<Trainer> trainers = offeredSports.get(sports);
		if (trainers == null) {
			return false;
		}

		for (Trainer trainer : trainers) {
			var acres = trainer.getAccreditations();
			if (acres.get(sports).ordinal() >= level.ordinal()) {
				Level ret = member.learn(sports, level);
				if (ret.ordinal() - level.ordinal() >= 1) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean addMember(Member member) {
  
	


		if (member instanceof Trainer) {
			Set<Sports> accred = ((Trainer) member).getAccreditations().keySet();
			Map<Sports, Set<Trainer>> tmp = new LinkedHashMap<>(offeredSports);
			for (Sports sport : accred) {
				if (tmp.get(sport) != null) {
					tmp.get(sport).add((Trainer) member);
				} else {
					Set<Trainer> tra = new HashSet<>();
					tra.add((Trainer) member);
					tmp.put(sport, tra);
				}
			}
			offeredSports = new LinkedHashMap<>(tmp);
		}

		if (members.contains(member)) {
			return false;
		} else {
			members.add(member);
			return true;
		}
	}

	public boolean removeMember(Member member) {
		boolean removed = false;
		if (member instanceof Trainer) {
			Map<Sports, Set<Trainer>> tmp = new LinkedHashMap<>(offeredSports);
			for (Map.Entry<Sports, Set<Trainer>> i : tmp.entrySet()) {
				if (i.getValue().contains(member)) {
					if (i.getValue().size() == 1) {
						tmp.remove(i.getKey());
						removed = true;
					} else {
						tmp.get(i.getKey()).remove(member);
						removed = true;
					}
				}
			}
			members.remove(member);
			offeredSports = new LinkedHashMap<>(tmp);
			return removed;
		} else {
			if (members.contains(member)) {
				return members.remove(member);
			}
		}
		return false;
	}

	@Override
	public String toString() {
		return "SportsClub[name: " + name + ", feePerSports: " + feePerSports + ", offeredSports: " + offeredSports
				+ "]";

	}

	public String getName() {
		return name;
	}

	public BigDecimal getFeePerSports() {
		return feePerSports;
	}

	public Set<Member> getMembers() {
		return new HashSet<>(members);
	}

	public Set<Sports> getSports() {
		return offeredSports.keySet();
	}
}
