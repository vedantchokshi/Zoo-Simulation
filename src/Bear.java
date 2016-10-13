/**
 * Class Bear extends Animal which defines all the basic properties and methods a Bear should have 
 * @author Vedant Chokshi
 */
public class Bear extends Animal {

	/**
	 * Constructor for a Bear
	 * @param name name of Bear
	 * @param age age of Bear
	 * @param gender gender of Bear
	 * @param health health of Bear
	 * @param input Enclosure the Bear is added to
	 */
	public Bear (String name, Integer age, char gender, Integer health, Enclosure input) {
		super (name, age, gender, new String [] {"fish", "steak"}, health, 18, input);
	}

	/**
	 * Second Constructor which calls super Constructor. Used in {@link Simulator} when config file is read 
	 * because it makes it easier to add the Animal without making mistakes
	 * @param inputLine String Array which contains name, age, gender and health of the Bear 
	 * @param input Enclosure the Bear is added to
	 */
	public Bear (String[] inputLine, Enclosure input) throws NumberFormatException, Exception {
		this(inputLine[0],Integer.parseInt(inputLine[2]),inputLine[1].charAt(0),Integer.parseInt(inputLine[3]), input);
	}
	
	/**
	 * {@link Animal} abstract treat method implemented. 
	 * Treats Bear which increases its health.
	 */
	public void treat () {
		this.increaseHealth(2);
		System.out.println("Hugging " + this.name + " the " + this.getClass().getName() + " (" + this.getGender() + ")");
	}
	
	/**
	 * {@link Animal} abstract canTreat method implemented, returns true as all types of Zookeepers can treat a Bear
	 */
	public boolean canTreat (Zookeeper zookeeper) {
		return true;
	}
}
