import java.util.*;
/**
 * Abstract class Animal which defines all the basic properties and methods that any Animal should have
 * @author Vedant Chokshi
 */
public abstract class Animal implements Comparable<Animal> {

	// Variables protected so they can also be accessed by the subclasses which extend Animal
	protected Integer age, lifeExpectancy, health;
	protected char gender;
	protected String[] eats;
	protected String name, foodEaten;
	protected Enclosure enclosure;
	protected int ageDiff = 5;
	
	/**
	 * Constructor for a generic Animal
	 * @param name Name of the Animal
	 * @param age Age of the Animal
	 * @param gender Gender of the Animal
	 * @param eats String[] which stores the type of foods the Animal eats
	 * @param health Health of the Animal
	 * @param lifeExpectancy Life Expectancy if Animal
	 * @param assignEnclosure Enclosure which is assigned to the Animal
	 */
	public Animal (String name, Integer age, char gender, String[] eats, Integer health, Integer lifeExpectancy, Enclosure assignEnclosure) {
		/*
		 * If a user enters invalid age, gender or health, I do not want my program to stop running and hence I randomly generate values for each of them
		 * This was also useful when making my animals reproduce as I can put in invalid health and gender and hence the new born baby animal will have random health and gender
		 */
		Toolbox toolbox = new Toolbox();
		//If age entered into the constructor is 0 or positive age then accept it, else generate random age between 0 and the life expectancy of the animal
		if (age >= 0) {
			this.age = age;
		} else {
			this.age = toolbox.getRandomInteger(lifeExpectancy);
		}
		//If gender entered into the constructor is f or m then accept it, else generate a random gender
		if (gender == 'f' || gender == 'F') {
			this.gender = 'f';
		} else if (gender == 'm' || gender == 'M') {
			this.gender ='m';
		} else {
			if(toolbox.getRandomInteger(2).equals(1)) {
				this.gender = 'm';
			} else {
				this.gender = 'f';
			}
		}
		//If health entered into the constructor is between 1-10 then accept it, else generate random health between 1-10
		if (health > 0 && health <= 10) {
			this.health = health;
		} else {
			this.health = (toolbox.getRandomInteger(9) + 1);
		}
		//Sets constructor values equal to class variables
		this.eats = eats;
		this.lifeExpectancy = lifeExpectancy;
		this.name = name.substring(0,1).toUpperCase() + name.substring(1);
		//Sets enclosure for the aimal
		this.setEnclosure(assignEnclosure);
	}
	
	/**
	 * Returns the name of the Animal
	 * @return name
	 */
	public String getName () {
		return this.name;
	}

	/**
	 * Returns age of the Animal
	 * @return age
	 */
	public int getAge() {
		return this.age;
	}
	
	/**
	 * Returns gender of the Animal
	 * @return gender
	 */
	public char getGender() {
		return this.gender;
	}
	
	/**
	 * Returns health of the Animal
	 * @return health
	 */
	public int getHealth() {
		return this.health;
	}
	
	/**
	 * Returns life expectancy of the Animal
	 * @return lifeExpectancy
	 */
	public int getLifeExpectancy() {
		return this.lifeExpectancy;
	}
	
	/**
	 * Increases age of the Animal
	 * @param increaseAgeBy number to increase the age of the Animal by
	 */
	public void increaseAge (int increaseAgeBy) {
		this.age += increaseAgeBy;
	}
	
	/**
	 * Checks if the Animal can eat a provided food
	 * @param food food to be checked if can be eaten or not
	 * @return true if can eat, false otherwise
	 */
	public boolean canEat (String food) {
		/**
		 * Loops throw the String Array in animal which stores all the foods the animal can eat
		 * If the inputed food is equal to any of the foods the animal eats, then return true, false otherwise
		 */
		boolean answer = false;
		for (String foods : this.eats) {
			if(foods.equals(food)){
				answer = true;
			}
		}
		return answer;
	}
	
	/**
	 * Allows animal to eat food from the Foodstore of the Enclosure that it is stored in.
	 * If animal eats, increases health and adds waste to Enclosure.
	 * @return true if animal has eaten, false otherwise
	 */
	public boolean eat () {
		boolean hasEaten = false;
		/**
		 * Stores a list of all the food names and uses Collections.shuffle to randomly order them so the same food isn't eaten every time hte method is called
		 * For each food, if the stock of that food in the foodstore > 0 and the animal can eat the food
		 * Remove 1 of that food from the foodstore, find health of that food and add it to the animal and then break from the loop
		 */
		List<String> randomFood = new ArrayList<String>(this.enclosure.getFoodstore().getFoodStock().keySet());
		Collections.shuffle(randomFood);
		for (String foodName : randomFood) {
			if (this.enclosure.getFoodstore().getFoodStock().get(foodName) > 0 && this.canEat(foodName)) {
				this.enclosure.getFoodstore().takeFood(foodName, 1);
				this.increaseHealth(this.enclosure.getFoodstore().findHealth(foodName));
				System.out.println(this.name + " the " + this.getClass().getName() + " (" + this.getGender() + ") has eaten " + foodName.toUpperCase());
				hasEaten = true;
				foodEaten = foodName;
				break;
			}
		} 
		if (!hasEaten) {
			System.out.println(this.name + " the " + this.getClass().getName() + " (" + this.getGender() + ") has not eaten this month");
		}
		return hasEaten;
	}
	
	/**
	 * Increases health of the Animal
	 * @param add number to increase the health of the Animal by
	 */
	public void increaseHealth (int add) {
		this.health += add;
	}
	
	/**
	 * Decreases health of Animal 
	 * @param decrease number to decrease the health of the Animal by
	 */
	public void decreaseHealth (int decrease) {
		this.health -= decrease;
	}
	
	/**
	 * Fixes health for Animals and the end of the month because some health attributes can be lost if increaseHealth()/decreaseHealth set max/min health.
	 */
	public void fixHealth () {
		if (this.health < 0) {
			this.health = 0;
		} else if (this.health > 10) {
			this.health = 10;
		}
	}
	/**
	 * Assigns animal to an Enclosure
	 * @param inputEnclosure enclosure to which you want the Animal to be assigned to
	 */
	public void setEnclosure (Enclosure inputEnclosure) {
		this.enclosure = inputEnclosure;		
	}
	
	/**
	 * When a month is passed on an Animal.
	 * If an Animal eats then find the waste of the food it ate and add it to the enclosure
	 * The health of the animal is reduced by 2.
	 * The age of the animal is increased by 1.
	 */
	public void aMonthPasses () {
		if (this.eat()) {
			try {
				this.enclosure.addWaste(this.enclosure.getFoodstore().findWaste(foodEaten));
			} catch (Exception e) {
				System.err.println(e.getMessage());
				System.exit(1);
			}
		}
		this.decreaseHealth(2);
		this.increaseAge(1);
	}
	
	/**
	 * Method which allows a male and female of the same type to reproduce with a random chance of reproduction
	 */
	public void reproduce () {
		//Only allow animals to reproduce if there is space in the enclosure (max 20) for more animals 
		if (this.enclosure.getSize() < 20) {
			Toolbox toolbox = new Toolbox();
			//Check if the gender of this animal equals 'f' and age is at least half the animal's life expectancy (so a baby animal doesn't start reproducing as soon as born)
			if (this.getGender() == 'f' && this.age > this.lifeExpectancy/2) {
			//Loops through all the animals in the enclosure
			for (int i = 0; i < this.enclosure.getAnimals().size(); i++) {
				Animal animal = this.enclosure.getAnimals().get(i);
				//If 2 animals are the of the same type, different genders, their age difference is 5 (so offsprings don't reproduce with parents) and they have a 1/10 chance of reproducing
				if (this.getClass().equals(animal.getClass()) && animal.getGender() == 'm' && Math.abs(this.getAge()-animal.getAge()) < this.ageDiff && toolbox.getRandomInteger(10) == 5) {
					System.out.println("\nANIMALS REPRODUCING...");
					System.out.println(this.getName() + " and " + animal.getName() + " have had a baby " + animal.getClass().getName() + "!");
					//Asks for a String input for the name of the baby animal
					String babyAnimalName = toolbox.readStringFromCmd();
					while (!babyAnimalName.matches("[a-zA-Z]+")) {
						babyAnimalName = toolbox.readStringFromCmd();
					}
					//Creates instance of the type of animal with random gender and health
					try {
						switch (animal.getClass().getName()) {
						case "Lion":
							this.enclosure.addAnimal(new Lion(babyAnimalName, 0, 'x', 11, this.enclosure));
							break;
						case "Tiger":
							this.enclosure.addAnimal(new Tiger(babyAnimalName, 0, 'x', 11, this.enclosure));
							break;
						case "Chimpanzee":
							this.enclosure.addAnimal(new Chimpanzee(babyAnimalName, 0, 'x', 11, this.enclosure));
							break;
						case "Gorilla":
							this.enclosure.addAnimal(new Gorilla(babyAnimalName, 0, 'x', 11, this.enclosure));
							break;
						case "Bear":
							this.enclosure.addAnimal(new Bear(babyAnimalName, 0, 'x', 11, this.enclosure));
							break;
						case "Giraffae":
							this.enclosure.addAnimal(new Giraffe(babyAnimalName, 0, 'x', 11, this.enclosure));
							break;
						case "Penguin":
							this.enclosure.addAnimal(new Penguin(babyAnimalName, 0, 'x', 11, this.enclosure));
							break;
						case "Elephant":
							this.enclosure.addAnimal(new Elephant(babyAnimalName, 0, 'x', 11, this.enclosure));
							break;
						default:
							break;
						}
						} catch (Exception e) {
						System.out.println(e.getMessage());
						System.exit(1);
						}
					}
				}
			}
		}
	}
	
	/**
	 * Compares healths of animals by using the inbuilt Interface Comparable<>
	 */
	public int compareTo (Animal animal) {
		return this.getHealth() - animal.getHealth();
	}
	
	/**
	 * Each Animal has a different way of being treated hence treat is in Abstract method.
	 * So when Animal is extended, the treat method will need to be defined in the child class
	 */
	public abstract void treat ();
	
	/**
	 * Checks if a Zookeeper can treat an Animal
	 * @param zookeeper the zookeeper that wishes to treat the Animal
	 * @return true if the Zookeeper can treat the Animal, false otherwise
	 */
	public abstract boolean canTreat (Zookeeper zookeeper);
}
