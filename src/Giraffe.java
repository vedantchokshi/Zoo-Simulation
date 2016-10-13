/**
 * Class Giraffe extends Animal which defines all the basic properties and methods a Giraffe should have 
 * @author Vedant Chokshi
 */
public class Giraffe extends Animal {

	/**
	 * Constructor for a Giraffe
	 * @param name name of Giraffe
	 * @param age age of Giraffe
	 * @param gender gender of Giraffe
	 * @param health health of Giraffe
	 * @param input Enclosure the Giraffe is added to
	 */
	public Giraffe (String name, Integer age, char gender, Integer health, Enclosure input) {
		super (name, age, gender, new String [] {"hay", "fruit"}, health, 28, input);
	}

	/**
	 * Second Constructor which calls super Constructor. Used in {@link Simulator} when config file is read 
	 * because it makes it easier to add the Animal without making mistakes
	 * @param inputLine String Array which contains name, age, gender and health of the Giraffe 
	 * @param input Enclosure the Giraffe is added to
	 */
	public Giraffe (String[] inputLine, Enclosure input) {
		this(inputLine[0],Integer.parseInt(inputLine[2]),inputLine[1].charAt(0),Integer.parseInt(inputLine[3]), input);
	}
	
	/**
	 * {@link Animal} abstract treat method implemented. 
	 * Treats Giraffe which increases its health.
	 */
	public void treat () {
		this.increaseHealth(4);
		System.out.println("Giving neck massage to " + this.name + " the " + this.getClass().getName() + " (" + this.getGender() + ")");
	}
	
	/**
	 * {@link Animal} abstract canTreat method implemented, returns true if zookeeper is a PhysioZookeeper, false otherwise 
	 */
	public boolean canTreat (Zookeeper zookeeper) {
		return (zookeeper instanceof PhysioZookeeper);
	}
}
