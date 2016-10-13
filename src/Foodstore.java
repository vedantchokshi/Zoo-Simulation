import java.util.*;

/**
 * Class Foodstore stores Food for Enclosures and the Zoo
 * @author Vedant Chokshi
 */
public class Foodstore {
	
	//Variables private as you do not want other classes to directly edit them
	private HashMap<String, Integer> foodStock = new HashMap<>();
	private ArrayList<Food> foodTypes = new ArrayList<Food>();
	
	public Foodstore () {
		addFoodTypes();
	}
	
	/**
	 * Adds types of Food that the Zoo can accept
	 */
	public void addFoodTypes () {
		foodTypes.add(new Food("hay", 1, 4));
		foodTypes.add(new Food("steak", 3, 4));
		foodTypes.add(new Food("fruit", 2, 3));
		foodTypes.add(new Food("celery", 0, 1));
		foodTypes.add(new Food("fish", 3, 2));
		foodTypes.add(new Food("ice cream", 1, 3));
	}
	
	/**
	 * Returns HashMap of FoodStock
	 * @return foodStock
	 */
	public HashMap<String, Integer> getFoodStock() {
		return foodStock;
	}
	
	/**
	 * Makes an ArrayList<String> of all the types of food the Zoo works with
	 * @return String ArrayList of types of food the Zoo works with
	 */
	public ArrayList<String> getFoodList () {
		//Loops through all foods in foodTypes, gets their names and adds it to a temp ArrayList<String> which is returned at the end of the method
		ArrayList<String> foods = new ArrayList<String>();
		for (Food food : this.foodTypes) {
			foods.add(food.getName());
		}
		return foods;
	}
	
	/**
	 * Finds health for a given Food name
	 * @param foodName name of Food you want to find health of
	 * @return health given by Food
	 */
	public int findHealth (String foodName) {
		/*
		 * For each Food in foodTypes, if the name of the food equals the name of the food you want the health for
		 * Then find health for that food as it is present in foodTypes and return health
		 */
		int health = 0;
		for (Food food : this.foodTypes) {
			if (food.getName().equals(foodName)) {
				health = food.getHealth();
				break;
			}
		}
		return health;
	}
	
	/**
	 * Finds waste for a given Food name
	 * @param foodName name of Food you want to find waste of
	 * @return waste given by Food
	 */
	public int findWaste (String foodName) {
		/*
		 * For each Food in foodTypes, if the name of the food equals the name of the food you want the waste for
		 * Then find waste for that food as it is present in foodTypes and return waste
		 */
		int waste = 0;
		for (Food food : this.foodTypes) {
			if (food.getName().equals(foodName)) {
				waste = food.getWaste();
				break;
			}
		}
		return waste;
	}
	
	/**
	 * Adds Food to the Foodstore
	 * @param item name of Food you want to add
	 * @param numOfItems number of the Food you want to add
	 * @throws Exception if Food you wish to add is not a Food accepted by the Zoo or the number of Food you wish to add is negative
	 */
	public void addFood (String item, Integer numOfItems) throws Exception {
		boolean throwException = false;
		if (numOfItems < 0) {
			throw new Exception ("Error - Please enter a positive number for the food you wish to add");
		}	
		/*
		 * For each Food in foodTypes, if the name of the food equals the name of the food you want the add
		 * Check if it is already present in the foodStock HashMap, if present then add to the value for that food, else add a new Key and Value for food and item
		 */
		for (Food food : this.foodTypes) {
			if (food.getName().equals(item)) {
				if (foodStock.containsKey(item)) {
					this.foodStock.put(item, this.foodStock.get(item) + numOfItems);
					throwException = true;
					break;
				} else {
					this.foodStock.put(item, numOfItems);
					throwException = true;
					break;
				}
			}
		}
		if (!throwException) {
			throw new Exception ("Error - The food that you wish to add is not a food type accepted by this Zoo");
		}
	} 
	
	/**
	 * Removes Food from the Foodstore
	 * @param item name of Food you want to remove
	 * @param numOfItems number of the Food you want to add
	 */
	public void takeFood (String item, Integer numOfItems) {
		/*
		 * For each Food in foodTypes, if the name of the food equals the name of the food you want the take away
		 * Check if it is already present in the foodStock HashMap, if present then take away from the value for that food
		 */
		if (foodStock.containsKey(item) && foodStock.get(item) >= numOfItems) {
			this.foodStock.put(item, this.foodStock.get(item) - numOfItems);
		}
	}
	
	/**
	 * Returns sum of all the Food in the Foodstore
	 * @return Food stock size
	 */
	public int foodStockSize () {
		/*
		 * For each food in the foodStock HashMap, get the value for the food and add it to size
		 */
		int size = 0;
		for (String food : foodStock.keySet()) {
			size += foodStock.get(food);
		}
		return size;
	}
}

