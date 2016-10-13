/**
 * Abstract class BigCat which extends Animal defines the basic properties and methods any Animal which is a BigCat should have
 * @author Vedant Chokshi
 */
public abstract class BigCat extends Animal {

	/**
	 * Constructor for an Animal which is considered an BigCat
	 * @param name name of BigCat
	 * @param age age of BigCat
	 * @param gender gender of BigCat
	 * @param health health of BigCat
	 * @param input Enclosure the BigCat is added to
	 */
	public BigCat(String name, Integer age, char gender, Integer health, Enclosure input) {
		super(name, age, gender, new String [] {"steak", "celery"}, health, 24, input);
	}
}
