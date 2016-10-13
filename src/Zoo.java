import java.util.*;
/**
 * Main class Zoo is where the Zoo Simulation runs
 * @author Vedant Chokshi
 */
public class Zoo {

	//private ArrayList of Enclosures as you do not want other classes directly changing it. It stores all the Enclosures in the Zoo (as a Zoo can have more than 1 Enclosure)
	private ArrayList<Enclosure> enclosures = new ArrayList<Enclosure>();
	//private ArrayList of Zookeepers as you do not want other classes directly changing it. It stores all the Zookeepers in the Zoo (as a Zoo can have more than 1 Zookeeper)
	private ArrayList<Zookeeper> zookeepers = new ArrayList<Zookeeper>();
	//private Foodstore of Zoo Foodstore as you do not want other classes directly changing it. Stores food for the Zookeepers to move into Enclosures
	private Foodstore zooFoodstore = new Foodstore();
	
	/**
	 * Main method which calls the {@link Simulator} Class and creates the Zoo
	 * @param args configFile inputed in cmd by user 
	 */
	public static void main (String [] args) {
		Simulator one = new Simulator (args[0]);
		Zoo zoo = one.createMyZoo();
		zoo.go();
	}
	
	/**
	 * While there are Enclosures in the Zoo, run aMonthPasses()
	 */
	public void go () {
		while (this.enclosures.size() > 0) {
			System.out.print("========================================= A MONTH HAS PASSED =========================================");
			try {
				Thread.sleep(500);
				this.aMonthPasses();
			} catch (InterruptedException e) {
			}
		}
		System.out.println("======================================================================================================");
	}
	
	/**
	 * Calls the aMonthPasses for each Enclosure and removes and dead Animals
	 */
	public void aMonthPasses () {
		for (Enclosure enclosure : this.enclosures) {
			System.out.println("\nENCLOSURE " + (this.getEnclosureIndex(enclosure) + 1) + ":");
			enclosure.aMonthPasses();
			this.runZookeeperDuties(enclosure);
			this.removeDeadAnimals(enclosure);
			enclosure.printAnimals();
			if (this.enclosures.size() > 1) {
				System.out.println("\n******************************************************************************************************");
			}
		}
		this.removeEmptyEnclosures();
		if (this.enclosures.size() > 0) {
			this.restockZooFoodstore();
		}	
	}
	
	/**
	 * Gets the ArrayList of all the Enclosures present in the Zoo (as a Zoo can have more than 1 Enclosure)
	 * @return ArrayList of Enclosures in the Zoo
	 */
	public ArrayList<Enclosure> getEnclosures() {
		return this.enclosures;
	}
	
	/**
	 * Returns the the size of ArrayList Enclosures
	 * @return number of Enclosures in the Zoo
	 */
	public int getEnclosuresSize () {
		return this.enclosures.size();
	}
	
	/**
	 * Gets the index of an Enclosure in the ArrayList enclosures
	 * @param enclosureIndexNeededFor the Enclosure you want the index of
	 * @return index of the Enclosure
	 */
	public int getEnclosureIndex (Enclosure enclosureIndexNeededFor) {
		return enclosures.indexOf(enclosureIndexNeededFor);
	}
	
	/**
	 * Adds a Enclosure to the the Zoo
	 * @param input Enclosure you want to add to the Zoo
	 */
	public void addEnclosure (Enclosure input) {
		this.getEnclosures().add(input);
	}

	/**
	 * Gets all the Zookeepers in the Zoo
	 * @return All Zookeepers in the Zoo
	 */
	public ArrayList<Zookeeper> getZookeepers() {
		return this.zookeepers;
	}
	
	/**
	 * Adds Zookeepers to the Zoo
	 * @param zookeeper zookeeper you wish to add to the Zookeeper ArrayList
	 */
	public void addZookeepers (Zookeeper zookeeper) {
		this.zookeepers.add(zookeeper);
	}

	/**
	 * Gets the foodstore of the Zoo
	 * @return Zoo Foodstore
	 */
	public Foodstore getZooFoodstore() {
		return this.zooFoodstore;
	}
	
	/**
	 * Loops through all the Enclosures in the Zoo. If empty then adds it to a "removeEnclosure" ArrayList. At the end all the empty Enclosures are removed.
	 */
	public void removeEmptyEnclosures () {
		/**
		 * For each enclosure in Zoo enclosures, if empty add to a temporary ArrayList<Enclosure> removeEnclosure
		 * Then for each enclosure in removeEnclosure, remove from Zoo enclosures
		 */
		ArrayList<Enclosure> removeEnclosure = new ArrayList<Enclosure>();
		for (Enclosure enclosure : this.enclosures) {
			if (enclosure.getAnimals().isEmpty()) {
				removeEnclosure.add(enclosure);
			}
		}
		for (Enclosure enclosure : removeEnclosure) {
			this.enclosures.remove(this.getEnclosureIndex(enclosure));
			System.out.println("\nENCLOSURE IS EMPTY AND HAS BEEN REMOVED");
		}
	}	
	
	/**
	 * For each Zookeeper assigned to an Enclosure, run the Zookeeper aMonthPasses()
	 * @param input Enclosure you want the aMonthPasses() of the Zookeepers to be called on
	 */
	public void runZookeeperDuties (Enclosure input) {
		//Runs zookeeper duties on a given enclosure
		for (Zookeeper z : this.zookeepers) {
			if(z.getEnclosure().equals(input)) {
				z.aMonthPasses();
			}
		}
	}
	
	/**
	 * Loops through all the Animals in a given Enclosure. If an Animal is dead, remove it from the Enclosure
	 * @param input Enclosure you wish to run the removeDeadAnimals () on
	 */
	public void removeDeadAnimals (Enclosure input) {
		//For each animal, fixHealth() then if it meets the criteria for dying, add to deadAnimals ArrayList, else reproduce ()
		ArrayList<Animal> deadAnimals = new ArrayList<Animal>();
			for (int i = 0; i < input.getAnimals().size(); i++) {
			Animal animal = input.getAnimals().get(i);
			animal.fixHealth();
			if (animal.getHealth() == 0) {
				deadAnimals.add(animal);
			} else if (animal.getAge() > animal.getLifeExpectancy()) {
				//Animal has a 1 in 3 chance of dying - Done by generating numbers between 1-3 and if number generated = 2, animal dies
				Toolbox toolbox = new Toolbox();
				if (toolbox.getRandomInteger(3) == 2) {
					deadAnimals.add(animal);
				}
			} else {
				animal.reproduce();
			}
		}
		//Removes all the dead animals from the deadAnimals ArrayList if there are any
		if (!deadAnimals.isEmpty()) {
			System.out.println("\nCHECKING AND REMOVING DEAD ANIMALS...");
			for (Animal animal : deadAnimals) {
			input.removeAnimal(animal);
				if ((animal.getAge()) > animal.getLifeExpectancy()) {
					System.out.println(animal.name + " has died from aging - " + animal.name + " the " + animal.getClass().getName() + " has been removed from Enclosure " + (this.getEnclosureIndex(input) + 1));
				} else {
					System.out.println(animal.name + " has died from starvation - " + animal.name + " the " + animal.getClass().getName() + " has been removed from Enclosure " + (this.getEnclosureIndex(input) + 1));
				}
			}
		}		
		System.out.println();
	}
	
	/**
	 * If Zoo Foodstore = 0, add 2 of each food into the Zoo Foodstore
	 */
	public void restockZooFoodstore () {
		if (this.zooFoodstore.foodStockSize() == 0) {
			System.out.println("\nZOO...");
			System.out.println("Restocking Zoo Foodstore - Adding 2 hay, steak, fruit, celery, fish and ice cream to the Zoo Foodstore");
			//For each food type just add 2 of each
			for (String food : zooFoodstore.getFoodList()) {
				try {
					zooFoodstore.addFood(food, 2);
				} catch (Exception e) {
					System.err.println(e.getMessage());
					System.exit(1);
				}
			}
		}		
	}		
}
