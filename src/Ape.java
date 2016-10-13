/**
 * Abstract class Ape which extends Animal defines the basic properties and methods any Animal which is an Ape should have
 * @author Vedant Chokshi
 */
public abstract class Ape extends Animal {
	
	/**
	 * Constructor for an Animal which is considered an Ape
	 * @param name name of Ape
	 * @param age age of Ape
	 * @param gender gender of Ape
	 * @param health health of Ape
	 * @param lifeExpectancy lifeExpectancy of Ape
	 * @param input Enclosure the Ape is added to
	 */
	public Ape (String name, Integer age, char gender, Integer health, Integer lifeExpectancy, Enclosure input) {
		super (name, age, gender, new String [] {"fruit", "ice cream"}, health, lifeExpectancy,input);
	}
}
