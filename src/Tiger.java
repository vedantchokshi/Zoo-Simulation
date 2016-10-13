/**
 * Class Tiger extends BigCat which defines all the basic properties and methods a Tiger should have 
 * @author Vedant Chokshi
 */
public class Tiger extends BigCat {

	/**
	 * Constructor for a Tiger
	 * @param name name of Tiger
	 * @param age age of Tiger
	 * @param gender gender of Tiger
	 * @param health health of Tiger
	 * @param input Enclosure the Tiger is added to
	 */
	public Tiger (String name, Integer age, char gender, Integer health, Enclosure input) {
		super (name, age, gender, health, input);
	}

	/**
	 * Second Constructor which calls super Constructor. Used in {@link Simulator} when config file is read 
	 * because it makes it easier to add the Animal without making mistakes
	 * @param inputLine String Array which contains name, age, gender and health of the Tiger 
	 * @param input Enclosure the Tiger is added to
	 */
	public Tiger (String[] inputLine, Enclosure input) {
		this(inputLine[0],Integer.parseInt(inputLine[2]),inputLine[1].charAt(0),Integer.parseInt(inputLine[3]), input);
	}
	
	/**
	 * {@link Animal} abstract treat method implemented. 
	 * Treats Tiger which increases its health.
	 */
	public void treat () {
		this.increaseHealth(3);
		System.out.println("Stroking " + this.name + " the " + this.getClass().getName() + " (" + this.getGender() + ")");
	}
	
	/**
	 * {@link Animal} abstract canTreat method implemented, returns true as all types of Zookeepers can treat a Tiger
	 */
	public boolean canTreat (Zookeeper zookeeper) {
		return true;
	}
}
