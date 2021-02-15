// insert your package here

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Main {

	public static void main(String[] args) {
		//testMemberCreation();
		//testMemberMethods();
		//testTrainerCreation();
		//testTrainerMethods();
		//testSportsClubCreation();
		//testSportsClubMethods();
	}

	private static void testMemberCreation() {
		try {
			new Member("");
		}
		catch(IllegalArgumentException e) {
			System.out.println("name is empty");
		}
		try {
			new Member(null);
		}
		catch(IllegalArgumentException e) {
			System.out.println("name is null");
		}
		try {
			new Member("Anna",new HashMap<>());
		}
		catch(IllegalArgumentException e) {
			System.out.println("sports is empty");
		}
		try {
			new Member("Anna", null);
		}
		catch(IllegalArgumentException e) {
			System.out.println("sports is null");
		}
		try {
			Map<Sports, Level> m = new HashMap<>();
			m.put(Sports.CLIMBING, Level.ADVANCED);
			m.put(Sports.ARCHERY, null);
			new Member("Anna", m);
		}
		catch(IllegalArgumentException e) {
			System.out.println("sports contains null value");
		}
		System.out.println("create member: " + new Member("Anna", Map.of(Sports.CLIMBING,Level.ADVANCED)));

		/*
		 * EXPECTED OUTPUT:
		 * name is empty
		 * name is null
		 * sports is empty
		 * sports is null
		 * sports contains null value
		 * create member: name: Anna, sports: {CLIMBING=Fortgeschritten}
		 */
	}

	private static void testMemberMethods() {
		//test learn()
		System.out.println("--------LEARN--------");
		Member anna = new Member("Anna", Map.of(Sports.CLIMBING,Level.ADVANCED));
		System.out.println(anna);
		System.out.println(anna.learn(Sports.ARCHERY, Level.PROFESSIONAL));
		System.out.println(anna);
		System.out.println(anna.learn(Sports.ARCHERY, Level.PROFESSIONAL));
		System.out.println(anna);
		System.out.println(anna.learn(Sports.ARCHERY, Level.BEGINNER));
		System.out.println(anna);
		System.out.println(anna.learn(Sports.CLIMBING, Level.ADVANCED));
		System.out.println(anna);
		System.out.println(anna.learn(Sports.BASKETBALL, Level.BEGINNER));
		System.out.println(anna);

		//test getter
		System.out.println("\n--------GETTER--------");
		System.out.println(anna.getName());
		System.out.println(anna.getBillableSports());
		System.out.println(anna.getSports());
		// check shallow copy
		System.out.println(anna.getSports().remove(Sports.BASKETBALL));
		System.out.println(anna.getSports().remove(Sports.CLIMBING));
		System.out.println(anna.getSports().size() == 3);

		//test compare methods
		System.out.println("\n--------COMPARE--------");
		Member paul = new Member("Paul", Map.of(Sports.CLIMBING,Level.ADVANCED));
		System.out.println(anna.compareTo(anna));
		System.out.println(anna.compareTo(paul));
		System.out.println(new Member("Bob").compareTo(new Member("bob")));
		System.out.println(anna.equals(anna));
		System.out.println(anna.equals(paul));
		System.out.println(new Member("Bob").compareTo(new Member("bob")));
		System.out.println(anna.hashCode() == anna.hashCode());
		System.out.println(anna.hashCode() == paul.hashCode());
		System.out.println(new Member("Bob").hashCode() == new Member("bob").hashCode());

		/*
		 * EXPECTED OUTPUT:
		 * --------LEARN--------
		 * name: Anna, sports: {CLIMBING=Fortgeschritten}
		 * Anfänger
		 * name: Anna, sports: {CLIMBING=Fortgeschritten, ARCHERY=Anfänger}
		 * Normal
		 * name: Anna, sports: {CLIMBING=Fortgeschritten, ARCHERY=Normal}
		 * Normal
		 * name: Anna, sports: {CLIMBING=Fortgeschritten, ARCHERY=Normal}
		 * Fortgeschritten
		 * name: Anna, sports: {CLIMBING=Fortgeschritten, ARCHERY=Normal}
		 * Anfänger
		 * name: Anna, sports: {CLIMBING=Fortgeschritten, ARCHERY=Normal, BASKETBALL=Anfänger}
		 *
		 * --------GETTER--------
		 * Anna
		 * [CLIMBING, ARCHERY, BASKETBALL]
		 * {ARCHERY=Normal, BASKETBALL=Anfänger, CLIMBING=Fortgeschritten} (Reihenfolge kann variieren)
		 * Anfänger
		 * Fortgeschritten
		 * true
		 *
		 * --------COMPARE--------
		 * 0
		 * -15
		 * -32
		 * true
		 * false
		 * -32
		 * true
		 * false
		 * false
		 */
	}

	private static void testTrainerCreation() {
		try {
			new Trainer("Anna", null);
		}
		catch(IllegalArgumentException e) {
			System.out.println("accreditations is null");
		}
		try {
			new Trainer("Anna",new HashMap<Sports, Level>());
		}
		catch(IllegalArgumentException e) {
			System.out.println("accreditations is empty");
		}
		System.out.println("create trainer: " + new Trainer("Anna", Map.of(Sports.CLIMBING,Level.ADVANCED)));

		/*
		 * EXPECTED OUTPUT:
		 * accreditations is null
		 * accreditations is empty
		 * create trainer: name: Anna, sports: {CLIMBING=Fortgeschritten}, accreditations: {CLIMBING=Fortgeschritten}
		 */
	}

	private static void testTrainerMethods() {
		Trainer anna = new Trainer("Anna", Map.of(Sports.CLIMBING,Level.ADVANCED));
		//test learn()
		System.out.println(anna);
		System.out.println(anna.learn(Sports.ARCHERY, Level.PROFESSIONAL));
		System.out.println(anna);

		//test getter
		System.out.println(anna.getAccreditations());
		System.out.println(anna.getBillableSports());
		System.out.println(anna.getAccreditations().remove(Sports.CLIMBING));
		System.out.println(anna.getAccreditations().size() == 1);

		/*
		 * EXPECTED OUTPUT:
		 * name: Anna, sports: {CLIMBING=Fortgeschritten}, accreditations: {CLIMBING=Fortgeschritten}
		 * Anfänger
		 * name: Anna, sports: {CLIMBING=Fortgeschritten, ARCHERY=Anfänger}, accreditations: {CLIMBING=Fortgeschritten}
		 * {CLIMBING=Fortgeschritten}
		 * [ARCHERY]
		 * Fortgeschritten
		 * true
		 */
	}

	private static void testSportsClubCreation() {
		try {
			new SportsClub(null, new BigDecimal("2.4"));
		}
		catch(IllegalArgumentException e) {
			System.out.println("name is null");
		}
		try {
			new SportsClub("", new BigDecimal("2.4"));
		}
		catch(IllegalArgumentException e) {
			System.out.println("name is empty");
		}
		try {
			new SportsClub("SC Hinterdupfingen", null);
		}
		catch(IllegalArgumentException e) {
			System.out.println("feePerSports is null");
		}
		System.out.println(new SportsClub("SC Hinterdupfingen", new BigDecimal("2")));

		/*
		 * EXPECTED OUTPUT:
		 * name is null
		 * name is empty
		 * feePerSports is null
		 * SportsClub[name: SC Hinterdupfingen, feePerSports: 2, offeredSports: {}]
		 */
	}

	private static void testSportsClubMethods() {
		SportsClub hinterdupfingen = new SportsClub("SC Hinterdupfingen", new BigDecimal("2"));

		//test getters
		System.out.println("--------GETTER--------");
		System.out.println(hinterdupfingen.getName());
		System.out.println(hinterdupfingen.getFeePerSports());
		System.out.println(hinterdupfingen.getMembers());
		System.out.println(hinterdupfingen.getSports());

		Member paul = new Member("Paul", Map.of(Sports.CLIMBING,Level.ADVANCED));
		Trainer anna = new Trainer("Anna", Map.of(Sports.CLIMBING,Level.ADVANCED));
		try {
			System.out.println(hinterdupfingen.calculateMembershipFee(paul));
		}
		catch(IllegalArgumentException e) {
			System.out.println("Member not found in " + hinterdupfingen.getName());
		}
		try {
			System.out.println(hinterdupfingen.registerSports(paul,Sports.ARCHERY,Level.ADVANCED));
		}
		catch(IllegalArgumentException e) {
			System.out.println("Member not found in " + hinterdupfingen.getName());
		}

		//test addMember() and calculateMembershipFee()
		System.out.println("\n--------addMember calculateMembershipFee--------");
		System.out.println(hinterdupfingen.addMember(paul));
		System.out.println(hinterdupfingen.addMember(paul));
		System.out.println(hinterdupfingen);
		System.out.println(hinterdupfingen.calculateMembershipFee(paul));
		System.out.println(hinterdupfingen.addMember(anna));
		System.out.println(hinterdupfingen);
		System.out.println(hinterdupfingen.calculateMembershipFee(paul));
		System.out.println(hinterdupfingen.calculateMembershipFee(anna));
		// shallow copy
		System.out.println(hinterdupfingen.getMembers().remove(anna));
		System.out.println(hinterdupfingen.getMembers().size() == 2);

		//test registerSports()
		System.out.println("\n--------registerSports--------");
		System.out.println(hinterdupfingen.registerSports(paul, Sports.GOLF, Level.ADVANCED));
		Member finn = new Member("Finn", Map.of(Sports.GOLF,Level.ADVANCED));
		hinterdupfingen.addMember(finn);
		System.out.println(hinterdupfingen.registerSports(finn, Sports.CLIMBING, Level.ADVANCED));
		System.out.println(finn);
		System.out.println(hinterdupfingen.registerSports(finn, Sports.CLIMBING, Level.ADVANCED));
		System.out.println(finn);

		//test removeMember()
		System.out.println("\n--------removeMember--------");
		System.out.println(hinterdupfingen.removeMember(paul));
		System.out.println(hinterdupfingen.removeMember(paul));
		System.out.println(hinterdupfingen);
		System.out.println(hinterdupfingen.getMembers());
		System.out.println(hinterdupfingen.removeMember(anna));
		System.out.println(hinterdupfingen);
		System.out.println(hinterdupfingen.getMembers());

		/*
		 * EXPECTED OUTPUT:
		 * --------GETTER--------
		 * SC Hinterdupfingen
		 * 2
		 * []
		 * []
		 * Member not found in SC Hinterdupfingen
		 * Member not found in SC Hinterdupfingen
		 *
		 * --------addMember calculateMembershipFee--------
		 * true
		 * false
		 * SportsClub[name: SC Hinterdupfingen, feePerSports: 2, offeredSports: {}]
		 * 0
		 * true
		 * SportsClub[name: SC Hinterdupfingen, feePerSports: 2, offeredSports: {CLIMBING=[name: Anna, sports: {CLIMBING=Fortgeschritten}, accreditations: {CLIMBING=Fortgeschritten}]}]
		 * 2.4
		 * 0
		 * true
		 * true
		 *
		 * --------registerSports--------
		 * false
		 * false
		 * name: Finn, sports: {GOLF=Fortgeschritten, CLIMBING=Anfänger}
		 * false
		 * name: Finn, sports: {GOLF=Fortgeschritten, CLIMBING=Normal}
		 *
		 * --------removeMember--------
		 * true
		 * false
		 * SportsClub[name: SC Hinterdupfingen, feePerSports: 2, offeredSports: {CLIMBING=[name: Anna, sports: {CLIMBING=Fortgeschritten}, accreditations: {CLIMBING=Fortgeschritten}]}]
		 * [name: Anna, sports: {CLIMBING=Fortgeschritten}, accreditations: {CLIMBING=Fortgeschritten}, name: Finn, sports: {GOLF=Fortgeschritten, CLIMBING=Normal}]
		 * true
		 * SportsClub[name: SC Hinterdupfingen, feePerSports: 2, offeredSports: {}]
		 * [name: Finn, sports: {GOLF=Fortgeschritten, CLIMBING=Normal}]
		 */
	}

}
