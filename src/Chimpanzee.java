/**
 * Class Chimpanzee extends Ape which defines all the basic properties and methods a Chimpanzee should have 
 * @author Vedant Chokshi
 */
public class Chimpanzee extends Ape {
	
	/**
	 * Constructor for a Chimpanzee
	 * @param name name of Chimpanzee
	 * @param age age of Chimpanzee
	 * @param gender gender of Chimpanzee
	 * @param health health of Chimpanzee
	 * @param input Enclosure the Chimpanzee is added to
	 */
	public Chimpanzee (String name, Integer age, char gender, Integer health, Enclosure input) {
		super (name, age, gender, health, 24, input);
	}
	
	/**
	 * Second Constructor which calls super Constructor. Used in {@link Simulator} when config file is read 
	 * because it makes it easier to add the Animal without making mistakes
	 * @param inputLine String Array which contains name, age, gender and health of the Chimpanzee 
	 * @param input Enclosure the Chimpanzee is added to
	 */
	public Chimpanzee (String[] inputLine, Enclosure input) {
		this(inputLine[0],Integer.parseInt(inputLine[2]),inputLine[1].charAt(0),Integer.parseInt(inputLine[3]), input);
	}
	
	/**
	 * {@link Animal} abstract treat method implemented. 
	 * Treats Chimpanzee which increases its health.
	 */
	public void treat () {
		this.increaseHealth(4);
		System.out.println("Playing chase with " + this.name + " the " + this.getClass().getName() + " (" + this.getGender() + ")");
	}
	
	/**
	 * {@link Animal} abstract canTreat method implemented, returns true if zookeeper is a PlayZookeeper, false otherwise 
	 */
	public boolean canTreat (Zookeeper zookeeper) {
		return (zookeeper instanceof PlayZookeeper);
	}
}
