/**
 * Class PlayZookeeper extends {@link Zookeeper} which defines all the properties and methods a PlayZookeeper should have. 
 * Right now the main purpose of this Class is to help differentiate between different types of Zookeepers for treating.
 * @author Vedant Chokshi
 */
public class PlayZookeeper extends Zookeeper {

	public PlayZookeeper(Enclosure inputEnclosure, Foodstore foodstore) {
		super(inputEnclosure, foodstore);
	}

}
