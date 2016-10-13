import java.util.*;
/**
 * The Zoo has Enclosures which stores Animals, Food and to which a Zookeeper is assigned to
 * @author Vedant Chokshi
 *
 */
public class Enclosure {

	private int waste;
	private Foodstore enclosureFoodstore;
	private ArrayList<Animal> animals = new ArrayList<Animal> ();
    
	/**
	 * Constructor for Enclosoure 
	 * @param waste waste in Enclosure to start of with (Integer)
	 * @throws Exception if waste that wishes to be added is negative then throw Exception
	 */
    public Enclosure (int waste) {
    	//Throws exception if user tries to add negative waste to the enclosure when making a new enclosure
    	try {
			this.addWaste(waste);
		} catch (Exception e) {
			System.err.println(e.getMessage());
			System.exit(1);
		}
    	//Makes new foodstore every time a new enclosure is made as each enclosure needs its own foodstore
		this.enclosureFoodstore = new Foodstore();
	}
	
	/**
	 * Returns number of Animals in the Enclosure
	 * @return size of Enclosure
	 */
	public int getSize () {
		return animals.size();
	}
	
	/**
	 * Returns the Foodstore of the Enclosure
	 * @return Foodstore
	 */
	public Foodstore getFoodstore () {
		return enclosureFoodstore;
		
	}	
	
	/**
	 * Returns ArrayList of all the Animals in the ArrayList animals
	 * @return Animals in Enclosure
	 */
	public ArrayList<Animal> getAnimals() {
		return animals;
	}
	
	/**
	 * Adds an animal to the Enclosure
	 * @param addAnimal Animal you want to add to the Enclosure
	 * @throws Exception if Animal is tried adding to a full Enclosure (max 20 Animals)
	 */
	public void addAnimal (Animal addAnimal) throws Exception  {
		if (animals.size() < 20) {
			animals.add(addAnimal);
		} else {
			throw new Exception("Error - Animal could not be added. Enclosure is full... Please create a new enclosure");
		}
	}
	
	/**
	 * Removes an animal from the Enclosure
	 * @param removeAnimal Animal you want to remove from the Enclosure
	 */
	public void removeAnimal (Animal removeAnimal) {
		if (animals.contains(removeAnimal)) {
			animals.remove(removeAnimal);
		}
	}
	
	/**
	 * Prints all Animals in Enclosure with Name, Type, Gender, Health, Age
	 */
	public void printAnimals () {
		System.out.println("ANIMALS IN ENCLOSURE AFTER A MONTH HAS PASSED...");
		//Loops through each animal in the enclosure and prints details of all the animals
		if (this.getSize() > 0) {
			for (Animal a : animals) {
			System.out.println(a.getName() + " the " + a.getClass().getName() + " (" + a.getGender()+ ") - Health: " + a.getHealth() + " - Age: " + a.getAge());
			}
		} else {
			System.out.println("No animals in enclosure");
		}
	}
	
	/**
	 * Returns waste in Enclosure
	 * @return waste
	 */
	public int getWaste () {
		return this.waste;
	}
	
	/**
	 * Adds food to the Enclosure Foodstore
	 * @param item name of food you want to add
	 * @param numOfItems number of the food you wish to add
	 */
	public void addFoodToEnclosure (String item, Integer numOfItems) {
		//Throws exception if a food that is not accepted by the Zoo is wished to be added the enclosure foodstore
		try {
			this.enclosureFoodstore.addFood(item, numOfItems);
		} catch (Exception e) {
			System.err.println(e.getMessage());
			System.exit(1);
		}
	}
	
	/**
	 * Adds waste to Enclosure
	 * @param addWaste waste in Enclosure you want to add
	 * @throws Exception if negative waste is entered to add
	 */
	public void addWaste (int addWaste) throws Exception {
		if (addWaste >= 0) {
			this.waste += addWaste;
		} else {
			throw new Exception("Error - Negative waste cannot be added to an Enclosure");
		}		
	}
	
	/**
	 * Removes waste from the Enclosure
	 * @param removeWaste waste in Enclosure you want to remove
	 * @throws Exception if negative waste is entered to remove
	 */
	public void removeWaste (int removeWaste) throws Exception {
		if (removeWaste >= 0) {
			this.waste -= removeWaste;
			if (this.waste < 0) {
				this.waste = 0;
			}
		} else {
			throw new Exception("Error - Negative waste cannot be removed from an Enclosure");
		}
	}
	
	/**
	 * Calls the Animal aMonthPasses() on each Animal in the Enclosure
	 */
	public void aMonthPasses () {
		System.out.println("FEEDING ANIMALS...");
		//Loops through all the animals in the enclosure and calls aMonthPasses() for each animal
		if (this.animals.isEmpty()) {
			System.out.println("No animals in Enclosure to feed");
		} else {
			for (Animal animal : this.animals) {
				animal.aMonthPasses();
			}
		}
	}
}
		
			
