/*
 * Class Food defines basic properties and methods of any Food
 */
public class Food {

	//Variables private as you do not want other classes to directly edit them
	private String name;
	private Integer health, waste;
	
	/**
	 * Constructor for food
	 * @param name name of Food
	 * @param health health given by eating the Food
	 * @param waste waste produced by eating the Food
	 */
	public Food (String name, Integer health, Integer waste) {
		this.name = name;
		this.health = health;
		this.waste = waste;
	}

	/**
	 * Returns Food name
	 * @return name
	 */
	public String getName () {
		return name;
	}

	/**
	 * Returns health given by Food
	 * @return health
	 */
	public Integer getHealth () {
		return health;
	}

	/**
	 * Returns waste produced by Food
	 * @return waste
	 */
	public Integer getWaste () {
		return waste;
	}
	
	
}
