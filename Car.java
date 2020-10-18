/**
 * The car class represents a train car. Each car can hold people and is linked up to the previous and next cars.
 */
class Car {
	
	/**
	 * Name of the car.
	 */
	private String name;

	/**
	 * Reference to the next car in the train.
	 */
	private Car next;

	/**
	 * Reference to the previous car in the train.
	 */
	private Car previous;

	/**
	 * Constructor for the Car class. Initializes the car's name.
	 * @param name The name of the car.
	 */
	public Car(String name) {
		this.name = name;
	}
	
	/**
	 * Gets the car being referenced by this car's "next" variable.
	 * @return The car being referenced by "next".
	 */
	public Car getNext() {
		//returns the next car after this one
		//O(1)
		return this.next;
	}
	
	/**
	 * Gets the car being referenced by this car's "previous" variable.
	 * @return The car being referenced by "previous".
	 */
	public Car getPrevious() {
		//returns the car before this one
		//O(1)
		return this.previous;
	}
	
	/**
	 * Sets the value of the "next" reference variable.
	 * @param next The reference to the next car.
	 */
	public void setNext(Car next) {
		//sets the car after this one to next (the parameter)
		//O(1)
		this.next = next;
	}
	
	/**
	 * Sets the value to the "previous" reference variable.
	 * @param previous The reference to the previous car.
	 */
	public void setPrevious(Car previous) {
		//sets the car before this one to previous (the parameter)
		//O(1)
		this.previous = previous;
	}
	
	/**
	 * Gets the name of the car.
	 * @return The value of "name".
	 */
	public String getName() {
		//return's the car's name
		//O(1)
		return name;
	}

	/**
	  * Checks if this cars name is equal to the name of another car.
	  * @param o The object to be checked.
	  * @return Boolean indicating whether or not the names are equal.
	  */
	public boolean equals(Object o) {
		//two cars are equal if they have the same name
		//O(1)
		return this.name.equals(o.toString());
	}
	
	/**
	 * ToString method for car that returns the name of the car.
	 * @return The name of the car.
	 */
	public String toString() {
		//return's the car's name
		//O(1)
		return name;
	}
	
	/**
	 * Main method.
	 * @param args command line arguments.
	 */
	public static void main(String[] args) {
		Car c1 = new Car("C1");
		Car c2 = new Car("C2");
		
		c1.setNext(c2);
		c2.setPrevious(c1);
		
		if(c1.getName().equals("C1")) {
			System.out.println("Yay 1");
		}
		
		if(c1.getNext().equals(c2) && c2.getPrevious().equals(c1)) {
			System.out.println("Yay 2");
		}
		
		Car c1b = new Car("C1");
		if(c1.equals(c1b)) {
			System.out.println("Yay 3");
		}
		
		c1.printAscii();
	}
	
	//*****************************************************************/
	//****************** DO NOT EDIT BELOW THIS LINE ******************/
	//*****************************************************************/
	
	/**
	 * prints ascii.
	 */
	public void printAscii() {
		/*
		From: http://www.ascii-art.de/ascii/t/train.txt
		 _________
		 |O O O O|
		-|_______|
		   o   o  
		*/
		
		Car current = this;
		while(current != null) {
			System.out.print(" _________");
			current = current.getNext();
		}
		System.out.println();
		
		current = this;
		while(current != null) {
			System.out.print(" | "+String.format("%-5s",current.getName())+" |");
			current = current.getNext();
		}
		System.out.println();
		
		current = this;
		while(current != null) {
			System.out.print("-|_______|");
			current = current.getNext();
		}
		System.out.println();
		
		current = this;
		while(current != null) {
			System.out.print("   o   o  ");
			current = current.getNext();
		}
		System.out.println();
	}
}