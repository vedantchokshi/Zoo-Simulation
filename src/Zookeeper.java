import java.util.*;
/**
 * Class Zookeeper defines the basic properties and methods a Zookeeper must have
 * @author Vedant Chokshi
 */
public class Zookeeper {
	
	//Variables private as you only want Zookeepers to change them directly and not other classes which create instances of Zookeepers
	private Enclosure enclosure;
	private Foodstore foodstore;
	
	/**
	 * Constructor for a Zookeeper
	 * @param inputEnclosure Enclosure which the Zookeeper is assigned to
	 * @param foodstore /Foodstore the Zookeeper moves food from to the Enclosure
	 */
	public Zookeeper (Enclosure inputEnclosure, Foodstore foodstore) {
		this.enclosure = inputEnclosure;
		this.foodstore = foodstore;		
	}

	/**
	 * Gets the ArrayList of all the Enclosures in Zoo
	 * @return Enclosures in Zoo
	 */
	public Enclosure getEnclosure() {
		return enclosure;
	}
	
	/**
	 * Fulfils Zookeeper duties of aMonthPasses() by calling giveTreat(), moveFood() and removeWaste()
	 */
	public void aMonthPasses () {
		System.out.println();
		this.giveTreat();
		this.moveFood();
		this.removeWaste();
	}
	
	/**
	 * Removes and prints how much waste is removed from the Enclosure by the Zookeeper
	 */
	public void removeWaste () {
		System.out.println("REMOVING WASTE...");
		int wasteRemoved = 0;
		if (this.enclosure.getWaste() == 0) {
			System.out.println("No waste in Enclosure for Zookeeper to move");
		} else {
			if (this.enclosure.getWaste() <= 20) {
				wasteRemoved = this.enclosure.getWaste();
			} else {
				wasteRemoved = 20;
			}
			try {
				this.enclosure.removeWaste(20);
			} catch (Exception e) {
				System.err.println(e.getMessage());
				System.exit(1);
			}
			System.out.println("Zookeeper has removed " + wasteRemoved + " waste: " + this.enclosure.getWaste() + " waste in Enclosure ");
		}
	}

	/**
	 * Randomly shuffles food and upto 20 items of Food from the Foodstore to the Enclosure the Zookeeper is assigned to
	 */
	public void moveFood () {
		//Shuffles and randomly moves food from foodstock to enclosure the zookeeper is assigned to
		System.out.println("MOVING FOOD...");
		List<String> shuffle = new ArrayList<String>(this.foodstore.getFoodStock().keySet());
		int foodMoved = 0;
		for (int i = 0; i < 20; i++) {
			if(this.foodstore.foodStockSize() == 0) {
				break;
			} else {
				Collections.shuffle(shuffle);
				for (String food : shuffle) {
					int stock = this.foodstore.getFoodStock().get(food);
					if (stock > 0) {
						this.foodstore.takeFood(food, 1);
						this.enclosure.addFoodToEnclosure(food, 1);
						break;
					}
				}
				foodMoved = i + 1;
			}
		}
		if (foodMoved == 0) {
			System.out.println("No food in Zoo Foodstore for Zookeeper to move. More food being ordered...");
		} else if (foodMoved == 1) {
			System.out.println("Zookeeper has moved " + foodMoved + " piece of food");
		} else {
			System.out.println("Zookeeper has moved " + foodMoved + " pieces of food");
		}
		System.out.println();
	}
	
	/**
	 * Treats up to 2 Animals with the lowest health that the Zookeeper can treat
	 */
	public void giveTreat () {
		System.out.println("TREATING...");
		ArrayList<Animal> animals = this.enclosure.getAnimals();
		//If no animals in enclosure, then nothing to treat
		if (animals.size() == 0) {
			System.out.println("No animals to treat");
		//If 1 animal and the animal can be treated, treat that 1 animal, else nothing to treat
		} else if (animals.size() == 1) {
			if (animals.get(0).canTreat(this)) {
				animals.get(0).treat();
			} else {
				System.out.println("No animals to treat");
			}
		/*
		 * If more than 2 animals in the enclosure, make a new ArrayList of all animals which can be treated by the Zookeeper
		 * Order the ArrayList by health from lowest to highest using Collections.sort
		 * If size of new ArrayList is 1 then treat the Animal with index 0
		 * If size greater > 1 then treat index 0, 1
		 */
		} else if (animals.size() > 1) {
			ArrayList<Animal> canTreat = new ArrayList<>();
			for (Animal animal : animals) {
				if (animal.canTreat(this)) {
					canTreat.add(animal);
				}
			}
			Collections.sort(canTreat);
			if (canTreat.size() == 1) {
				canTreat.get(0).treat();;
			} else if (canTreat.size() > 1) {
				canTreat.get(0).treat();;
				canTreat.get(1).treat();;
			} else {
				System.out.println("No animals to treat");
			}
		}
		System.out.println();
	}		
}
