/**
 * Class that represents a person on the train. A person has a name and is in a train car.
 */
class Person {
	
	/**
	 * The name of the person. 
	 */
	private String name;

	/**
	 * The car the person is currently in.
	 */
	private Car currentCar;

	/**
	 * The constructor for Person. Initializes a Person with their name and with the car they are in.
	 * @param name The name of this person.
	 * @param currentCar The car this person is in currently.
	 */
	public Person(String name, Car currentCar) {
		this.name = name;
		this.currentCar = currentCar;
	}
	
	/**
	 * Gets the name of the person.
	 * @return The name of this person.
	 */
	public String getName() {
		//returns the name set in the constructor
		//O(1)
		return name;
	}
	
	/**
	 * Gets the car this person is currently in.
	 * @return The "currentCar" variable of this person.
	 */
	public Car getCurrentCar() {
		//returns the current car
		//O(1)
		return currentCar;
	}
	
	/**
	 * Moves this person to a different car. Returns true if the move was successful, and false otherwise.
	 * @param c The car to be moved to.
	 * @return Boolean stating whether the move was successful.
	 */
	public boolean moveToCar(Car c) {
		//Tries to move the person from their current car
		//to the car passed in as a parameter. If the two
		//cars are not adjacent, returns false (and the
		//person remains in their current car). Returns
		//true if the person was able to move.
		//O(1)
		if(currentCar.getPrevious() != null) {
			if(currentCar.getPrevious().equals(c)) {
				currentCar = currentCar.getPrevious();
				return true;
			}
		}
		if(currentCar.getNext() != null) {
			if(currentCar.getNext().equals(c)) {
				currentCar = currentCar.getNext();
				return true;
			}
		} 
		return false;
	}
	
	/**
	 * Checks if this person's name is equal to another person's name.
	 * @param o Object to be compared.
	 * @return Boolean stating whether the names are equal or not.
	 */
	public boolean equals(Object o) {
		//two people are "equal" if they have the same name
		//O(1)
		return o.toString() == this.toString();
	}
	
	/**
	 * ToString for Person. Returns the name of this person.
	 * @return The name of the person.
	 */
	public String toString() {
		//returns the person's name
		//O(1)
		return name;
	}
	
	/**
	 * Main method.
	 * @param args command line arguements.
	 */
	public static void main(String[] args) {
		Car c1 = new Car("C1");
		Car c2 = new Car("C2");
		Car c3 = new Car("C3");
		
		c1.setNext(c2);
		c2.setPrevious(c1);
		c3.setPrevious(c2);
		
		Person p1 = new Person("P1", c1);
		Person p3 = new Person("P3", c3);
		if(p1.getCurrentCar().equals(c1) && p1.getName().equals("P1")) {
			System.out.println("Yay 1");
		}
		
		if(p1.moveToCar(c2) && p1.getCurrentCar().equals(c2) && p1.moveToCar(c1) && p1.getCurrentCar().equals(c1)) {
			System.out.println("Yay 2");
		}
		
		Person p1b = new Person("P1", c1);
		if(p1.equals(p1b) && !p1.equals(new Person("P2", c1))) {
			System.out.println("Yay 3");
		}
		if(!p3.moveToCar(c1) && p3.moveToCar(c2) && p3.getCurrentCar().equals(c2)) {
			System.out.println("Yay 4");
		}
	}
}