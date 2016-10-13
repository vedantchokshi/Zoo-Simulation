/**
 * Class PhysioZookeeper extends {@link Zookeeper} which defines all the properties and methods a PhysioZookeeper should have. 
 * Right now the main purpose of this Class is to help differentiate between different types of Zookeepers for treating.
 * @author Vedant Chokshi
 */
public class PhysioZookeeper extends Zookeeper {

	public PhysioZookeeper(Enclosure inputEnclosure, Foodstore foodstore) {
		super(inputEnclosure, foodstore);
	}

}
