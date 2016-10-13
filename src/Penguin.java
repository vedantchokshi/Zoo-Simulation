/**
 * Class Penguin extends Animal which defines all the basic properties and methods an Penguin should have 
 * @author Vedant Chokshi
 */
public class Penguin extends Animal {

	/**
	 * Constructor for a Penguin
	 * @param name name of Penguin
	 * @param age age of Penguin
	 * @param gender gender of Penguin
	 * @param health health of Penguin
	 * @param input Enclosure the Penguin is added to
	 */
	public Penguin (String name, Integer age, char gender, Integer health, Enclosure input) {
		super (name, age, gender, new String [] {"fish", "ice cream"}, health, 15, input);
	}

	/**
	 * Second Constructor which calls super Constructor. Used in {@link Simulator} when config file is read 
	 * because it makes it easier to add the Animal without making mistakes
	 * @param inputLine String Array which contains name, age, gender and health of the Penguin 
	 * @param input Enclosure the Penguin is added to
	 */
	public Penguin (String[] inputLine, Enclosure input) {
		this(inputLine[0],Integer.parseInt(inputLine[2]),inputLine[1].charAt(0),Integer.parseInt(inputLine[3]), input);
	}
	
	/**
	 * {@link Animal} abstract treat method implemented. 
	 * Treats Penguin which increases its health.
	 */
	public void treat () {
		this.increaseHealth(2);
		System.out.println("Watching a film with " + this.name + " the " + this.getClass().getName() + " (" + this.getGender() + ")");
	}
	
	/**
	 * {@link Animal} abstract canTreat method implemented, returns true if zookeeper is a PlayZookeeper, false otherwise 
	 */
	public boolean canTreat (Zookeeper zookeeper) {
		return (zookeeper instanceof PlayZookeeper);
	}
}
