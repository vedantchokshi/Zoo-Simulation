/**
 * Class Gorilla extends Ape which defines all the basic properties and methods a Gorilla should have 
 * @author Vedant Chokshi
 */
public class Gorilla extends Ape {

	/**
	 * Constructor for a Gorilla
	 * @param name name of Gorilla
	 * @param age age of Gorilla
	 * @param gender gender of Gorilla
	 * @param health health of Gorilla
	 * @param input Enclosure the Gorilla is added to
	 */
	public Gorilla (String name, Integer age, char gender, Integer health, Enclosure input) {
		super (name, age, gender, health, 32, input);
	}

	/**
	 * Second Constructor which calls super Constructor. Used in {@link Simulator} when config file is read 
	 * because it makes it easier to add the Animal without making mistakes
	 * @param inputLine String Array which contains name, age, gender and health of the Gorilla 
	 * @param input Enclosure the Gorilla is added to
	 */
	public Gorilla (String[] inputLine, Enclosure input) {
		this(inputLine[0],Integer.parseInt(inputLine[2]),inputLine[1].charAt(0),Integer.parseInt(inputLine[3]), input);
	}
	
	/**
	 * {@link Animal} abstract treat method implemented. 
	 * Treats Gorilla which increases its health.
	 */
	public void treat () {
		this.increaseHealth(4);
		System.out.println("Painting with " + this.name + " the " + this.getClass().getName() + " (" + this.getGender() + ")");
	}
	
	/**
	 * {@link Animal} abstract canTreat method implemented, returns true if zookeeper is a PlayZookeeper, false otherwise 
	 */
	public boolean canTreat (Zookeeper zookeeper) {
		return (zookeeper instanceof PlayZookeeper);
	}
}
