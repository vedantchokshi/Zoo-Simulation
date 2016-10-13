/**
 * Class Lion extends BigCat which defines all the basic properties and methods a Lion should have 
 * @author Vedant Chokshi
 */
public class Lion extends BigCat {

	/**
	 * Constructor for a Lion
	 * @param name name of Lion
	 * @param age age of Lion
	 * @param gender gender of Lion
	 * @param health health of Lion
	 * @param input Enclosure the Lion is added to
	 */
	public Lion (String name, Integer age, char gender, Integer health, Enclosure input) {
		super (name, age, gender, health, input);
	}
	
	/**
	 * Second Constructor which calls super Constructor. Used in {@link Simulator} when config file is read 
	 * because it makes it easier to add the Animal without making mistakes
	 * @param inputLine String Array which contains name, age, gender and health of the Lion 
	 * @param input Enclosure the Lion is added to
	 */
	public Lion (String[] inputLine, Enclosure input) {
		this(inputLine[0],Integer.parseInt(inputLine[2]),inputLine[1].charAt(0),Integer.parseInt(inputLine[3]), input);
	}
	
	/**
	 * {@link Animal} abstract treat method implemented. 
	 * Treats Lion which increases its health.
	 */
	public void treat () {
		this.increaseHealth(2);
		System.out.println("Stroking " + this.name + " the " + this.getClass().getName() + " (" + this.getGender() + ")");
	}
	
	/**
	 * {@link Animal} abstract canTreat method implemented, returns true as all types of Zookeepers can treat a Lion
	 */
	public boolean canTreat (Zookeeper zookeeper) {
		return true;
	}
}
