import java.io.*;
import java.lang.reflect.Constructor;

/**
 * Class Simulator reads the config file and creates all the animals, enclosures and zookeepers needed for the Zoo to run
 * @author Vedant Chokshi
 */
public class Simulator {
	
	//Private variables as you don't want other classes changing these variables directly
	private BufferedReader reader;
	private String config;
	
	/*
	 * Takes in a file to read, throws Exception if file cannot be found for reading
	 */
	public Simulator (String configFile) {
		try {
			//Reads the file into the Class variable, reader
			this.reader = new BufferedReader(new FileReader(this.config = configFile));
			System.out.println("Reading from " + configFile + "...\n");
		} catch (FileNotFoundException e) {
			System.err.println("Error - " + configFile + " not found...");
			System.exit(1);
		} 
	}
	
	/**
	 * Reads each line in the config file
	 * @return line read from the config file, if not read then empty string
	 */
	public String getLine () {
		try {
			//.readLine() used to read line
			return this.reader.readLine();
		} catch (IOException e) {
			System.err.println("Error - Line could not be read in " + this.config + "...");
			return "";
		} catch (NullPointerException e){
			return "";
		}
	}
	
	/**
	 * Checks if the first line in reads "zoo" and calls addToMyZoo(), if not throws exception for invalid config file formatting
	 * @return zoo created from the config file so it can be stored into an instance in Zoo to be able to run go() from the Zoo
	 */
	public Zoo createMyZoo () {
		Zoo zoo = null;
		//Reads, splits line by ":" and stores into a String []
		String line = this.getLine().toLowerCase();
		String [] lineSplit = line.split(":"); 
		//If the first line does not equal "zoo" then throw exception for invalid formatting, else call addToMyZoo() which adds all the components of the Zoo to the zoo
		if (lineSplit[0].equals("zoo")) {
			try {
				zoo = this.addToMyZoo();
			} catch (Exception e) {
				System.err.println(e.getMessage());
				System.exit(1);
			}
		} else {
			System.err.println("Error - Invalid " + this.config + " formatting entered...");
		}
		return zoo;
	}
	
	/**
	 * Reads each line from the config file and adds Foods, Enclosures, Animals and Zookeepers to the Zoo
	 * @return zoo created from the config  file so it can be stored in createMyZoo () so the Zoo can run
	 * @throws Exception if there is invalid formatting in the config file
	 */
	public Zoo addToMyZoo () throws Exception {
		Zoo zoo = new Zoo ();
		String[] lineSplit;
		String line;
		
		//While there are no enclosures in the Zoo, only food and enclosure should be accepted
		while (zoo.getEnclosuresSize() == 0) {
			line = this.getLine().toLowerCase();
			lineSplit = line.split(":");
			if (lineSplit[0].equals("hay") || lineSplit[0].equals("steak") || lineSplit[0].equals("fruit") || lineSplit[0].equals("celery") || lineSplit[0].equals("fish") || lineSplit[0].equals("ice cream")) {
				zoo.getZooFoodstore().addFood(lineSplit[0], Integer.parseInt(lineSplit[1]));
				System.out.println("FOOD ADDED TO ZOO FOODSTORE - " + lineSplit[0] + ": " + Integer.parseInt(lineSplit[1]));
			} else if (lineSplit[0].equals("enclosure")){
				zoo.addEnclosure(new Enclosure(Integer.parseInt(lineSplit[1])));
				System.out.println("\nENCLOSURE ADDED - " + "  Waste: " + Integer.parseInt(lineSplit[1]));
			} else {
				throw new Exception("Error - No enclosure exists so you can only add VALID food to the Zoo Foodstore or create a NEW Enclosure");
			}
		}
		
		//Runs until 
		while ((line = this.getLine()) != null) {
			lineSplit = line.toLowerCase().split(":");
			String [] lineSplitTwo;
			String type;
			
			if (lineSplit[0].equals("enclosure")) {
				type = "enclosure";
			} else if (lineSplit[0].equals("hay") || lineSplit[0].equals("steak") || lineSplit[0].equals("fruit") || lineSplit[0].equals("celery") || lineSplit[0].equals("fish") || lineSplit[0].equals("ice cream")) {
				type = "food";
			} else if (lineSplit[0].equals("bear") || lineSplit[0].equals("chimpanzee") || lineSplit[0].equals("elephant") || lineSplit[0].equals("giraffe") || lineSplit[0].equals("gorilla") || lineSplit[0].equals("penguin") || lineSplit[0].equals("tiger") || lineSplit[0].equals("lion")) {
				type = "animal";
			} else if (lineSplit[0].contains("zookeeper")) {
				type = "zookeeper";
				if (lineSplit[0].equals("playzookeeper")) {
					lineSplit[0] = "PlayZookeeper";
				} else if (lineSplit[0].equals("physiozookeeper")) {
					lineSplit[0] = "PhysioZookeeper";
				} else if (lineSplit[0].equals("zookeeper")) {
					lineSplit[0] = "Zookeeper";
				}
			} else {
				throw new Exception("Error - Invaild " + this.config + " formatting");
			}
			switch (type) {
				case "enclosure" :
					zoo.addEnclosure(new Enclosure(Integer.parseInt(lineSplit[1])));
					System.out.println("\nENCLOSURE ADDED - "+ "  Waste: " + lineSplit[1]);
					break;
				case "food" :
					zoo.getEnclosures().get(zoo.getEnclosuresSize()-1).getFoodstore().addFood(lineSplit[0], Integer.parseInt(lineSplit[1]));
					System.out.println("FOOD ADDED TO ENCLOSURE " + zoo.getEnclosuresSize() + " - " + lineSplit[0] + ": " + Integer.parseInt(lineSplit[1]));
					break;
				case "animal" :
					Class<?> findAnimal = Class.forName(lineSplit[0].substring(0,1).toUpperCase() + lineSplit[0].substring(1));
					Constructor<?> constructAnimal = findAnimal.getDeclaredConstructor(String[].class, Enclosure.class);
					Animal animal;
					int indexChecker;
					lineSplitTwo = lineSplit[1].split(",");
					try {
						if(!lineSplitTwo[0].matches("[a-zA-Z]+")) {
							throw new Exception ("Invalid name entered. Please enter a name with only alaphabets");	
						}						
					} catch (Exception e) {
						System.err.println(e.getMessage());		
						System.exit(1);
					}
					for (String string : lineSplitTwo) {
						if (string.equals("") || string.equals(null)) {
							throw new Exception ("Error - Wrong formatting for " + lineSplit[1].substring(0,1).toUpperCase() + lineSplit[1].substring(1) + "! Please Enter - Name,Gender,Age,Health,Enclosure(Optional)");
						}
					}
					if (lineSplitTwo.length == 5) {
						indexChecker = (Integer.parseInt(lineSplitTwo[4])-1);
						try {
							if (!((indexChecker <= zoo.getEnclosuresSize()))) {
							throw new Exception ("The index " + Integer.parseInt(lineSplitTwo[4]) + " that you wish to add the Animal to does not exist");
							}
							} catch (Exception e) {
								System.err.println(e.getMessage());
								System.exit(1);
							}
						animal = (Animal)constructAnimal.newInstance(new Object [] {lineSplitTwo, zoo.getEnclosures().get(indexChecker)});
						zoo.getEnclosures().get(Integer.parseInt(lineSplitTwo[4])-1).addAnimal(animal);
						System.out.println(lineSplit[0].toUpperCase() + " ADDED TO ENCLOSURE " + lineSplitTwo[4] + " - " + " Name: " + animal.getName() + "  Gender: " + animal.getGender() + "  Age: " + animal.getAge() + "  Health: " + animal.getHealth());
					} else if (lineSplitTwo.length == 4) {
						animal = (Animal)constructAnimal.newInstance(new Object [] {lineSplitTwo, zoo.getEnclosures().get(zoo.getEnclosuresSize()-1)});
						zoo.getEnclosures().get(zoo.getEnclosuresSize()-1).addAnimal(animal);
						System.out.println(lineSplit[0].toUpperCase() + " ADDED TO ENCLOSURE " + zoo.getEnclosuresSize() + " - " + " Name: " + animal.getName() + "  Gender: " + animal.getGender() + "  Age: " + animal.getAge() + "  Health: " + animal.getHealth());
					} else {
						throw new Exception ("Invalid line format for Animal - Writes as AnimalType:Name,Gender,Age,Health,EnclosureIndex (Optional)");
					}
					break;		
				case "zookeeper" :
					Class<?> findZookeper = Class.forName(lineSplit[0]);
					Constructor<?> constructZookeeper = findZookeper.getDeclaredConstructor(Enclosure.class, Foodstore.class);
					Zookeeper zookeeper = (Zookeeper)constructZookeeper.newInstance(new Object [] {zoo.getEnclosures().get((zoo.getEnclosuresSize()-1)), zoo.getZooFoodstore()});
					zoo.addZookeepers(zookeeper);
					System.out.println(lineSplit[0].toUpperCase() + " ADDED TO ENCLOSURE " + zoo.getEnclosuresSize());
					break;
				default:
					break;
			}
		}
		System.out.println("\nA new Zoo has been created from " + config + "...");
		return zoo;
	}
}