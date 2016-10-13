/**
 * Class Elephant extends Animal which defines all the basic properties and methods an Elephant should have 
 * @author Vedant Chokshi
 */
public class Elephant extends Animal {

	/**
	 * Constructor for a Elephant
	 * @param name name of Elephant
	 * @param age age of Elephant
	 * @param gender gender of Elephant
	 * @param health health of Elephant
	 * @param input Enclosure the Elephant is added to
	 */
	public Elephant(String name, Integer age, char gender, Integer health, Enclosure input) {
		super (name, age, gender, new String [] {"hay", "fruit"}, health, 36, input);
	}

	/**
	 * Second Constructor which calls super Constructor. Used in {@link Simulator} when config file is read 
	 * because it makes it easier to add the Animal without making mistakes
	 * @param inputLine String Array which contains name, age, gender and health of the Elephant 
	 * @param input Enclosure the Elephant is added to
	 */
	public Elephant (String[] inputLine, Enclosure input) {
		this(inputLine[0],Integer.parseInt(inputLine[2]),inputLine[1].charAt(0),Integer.parseInt(inputLine[3]), input);
	}
	
	/**
	 * {@link Animal} abstract treat method implemented. 
	 * Treats Elephant which increases its health.
	 */
	public void treat () {
		this.increaseHealth(5);
		System.out.println("Bathing " + this.name + " the " + this.getClass().getName() + " (" + this.getGender() + ")");
	}

	/**
	 * {@link Animal} abstract canTreat method implemented, returns true if zookeeper is a PhysioZookeeper, false otherwise 
	 */
	public boolean canTreat (Zookeeper zookeeper) {
		return (zookeeper instanceof PhysioZookeeper);
	}
}
