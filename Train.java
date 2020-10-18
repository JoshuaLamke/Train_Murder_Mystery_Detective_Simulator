import java.util.Iterator;

/**
 * The train class made up of many cars connected to one another. It has a name and references to the first and last car.
 */
class Train implements Iterable<Car> {

	/**
	 * The name of the train.
	 */
	private String name;

	/**
	 * The first car in the train.
	 */
	private Car head;

	/**
	 * The last car in the train.
	 */
	private Car tail;

	/**
	 * Size of the train.
	 */
	private int size = 0;

	/**
	 * Constructor for the Train class. Initializes the name of the train.
	 * @param name The name for the train.
	 */
	public Train(String name) {
		this.name = name;
	}

	/**
	 * Gets the name of the train.
	 * @return the name of the train.
	 */
	public String getName() {
		//returns the train's name
		//O(1)
		return name;
	}
	
	/**
	 * Gets an iterator that starts at the first traincar in the train and can go to the last train car.
	 * @return An iterator for this train.
	 */
	public Iterator<Car> iterator() {
		//returns an iterator which traverses
		//the train from the first car (the one closest
		//to the front of the train) to the last car
		//use an anonymous class here
		//required iterator methods: next() and hasNext()
		//both methods are required to work in O(1) time
		return new Iterator<Car>(){

			private Car indexStart = head;

			private int index = 0;

			public Car next() {
				Car currCar = indexStart;
				indexStart = indexStart.getNext();
				index++;
				return currCar;
			}

			public boolean hasNext() {
				return index < size;
			}
		};
	}
	
	/**
	 * Checks to see if two trains are equal by seeing if their names are equal.
	 * @param o Object to be compared.
	 * @return Boolean stating whether or not the trains are equal.
	 */
	public boolean equals(Object o) {
		//two trains are equal if they have the same name
		//O(1)
		return this.toString() == o.toString();
	}
	
	/**
	 * Connects a car to the back of the train as well as all cars attached after that car.
	 * @param c The car to be attached to the back of the train.
	 */
	public void connectCar(Car c) {
		//connects the car to the end of the cars for this train
		//requied Big-O: O(n) where n=the length of the linked list
		//of cars starting at c, NOT n=the number of cars already 
		//connected to this train.
		if(c == null) {
			return;
		}
		// Train is empty
		if(head == null) {
			Car currCar = c;
			size++;
			head = currCar;
			head.setPrevious(null);
			tail = currCar;
			while(tail.getNext() != null) {
				tail = tail.getNext();
				size++;
			}
			return;
		}
		while(c != null) {
			Car currCar = c;
			// Train has items in it
			currCar.setPrevious(tail);
			tail.setNext(currCar);
			tail = currCar;
			size++;
			c = c.getNext();
		}
	}
	
	/**
	 * Disconnects a car from the train as well as all subsequent cars.
	 * @param c The car to be disconnected from the train.
	 * @return The car that was disconnected.
	 */
	public Car disconnectCar(Car c) {
		//returns the car disconnected from the train
		//should throw the following exception if the car isn't on
		//the train: RuntimeException("Can not disconnect a car that doesn't exist");
		//required Big-O: O(n) where n=the number of cars on this train
		if(head == null) {
			throw new RuntimeException("Can not disconnect a car that doesn't exist");
		}
		if(c.equals(head)) {
			Car removedCar = head;
			head = null;
			tail = null;
			size = 0;
			return removedCar;
		}
		else{
			int index = 0;
			for(Car car: this){
				if(c.equals(car)) {
					Car removedCar = car;
					tail = car.getPrevious();
					removedCar.setPrevious(null);
					tail.setNext(null);
					size = index;
					return removedCar;
				}
				index++;
			}
		}
		throw new RuntimeException("Can not disconnect a car that doesn't exist");
	}

	/**
	 * Class that iterates througha train backwards.
	 */
	private class BackwardsIterator {

		/**
		 * Reference to the tail of the train.
		 */
		private Car indexEnd = tail;

		/**
		 * Checks if there is a previous car.
		 * @return Boolean stating if there is a previous car or not.
		 */
		public boolean hasPrevious() {
			return indexEnd != null;
		}

		/**
		 * Moves the index to the next previous car and returns the current car. 
		 * @return The current car.
		 */
		public Car previous() {
			Car currCar = indexEnd;
			indexEnd = indexEnd.getPrevious();
			return currCar;
		}
	}
	/**
	 * Reverses the order of the train cars in the train.
	 */
	public void reverseTrain() {
		//reconnects all the cars on the train in the reverse order
		//that they currently are (e.g. changes C1->C2->C3 to
		//C3->C2->C1). You may find using a second train to do this useful.
		//required Big-O: O(n) where n=the number of cars on this train
		//careful not to end up with O(n^2) which is easy to do by calling O(n)
		//methods in a loop
		Train temp = new Train(this.getName());
		BackwardsIterator iterator = new BackwardsIterator();
		while(iterator.hasPrevious()) {
			Car currCar = iterator.previous();
			if(temp.head == null) {
				temp.head = currCar;
				temp.head.setPrevious(null);
				temp.tail = currCar;
				temp.tail.setNext(null);
				temp.size++;
			}
			else {
				currCar.setPrevious(temp.tail);
				temp.tail.setNext(currCar);
				temp.tail = currCar;
				temp.tail.setNext(null);
				temp.size++;
			}
		}
		this.disconnectCar(head);
		this.connectCar(temp.head);
		this.size = temp.size;
	}
	
	/**
	 * Main method.
	 * @param args command line arguements.
	 */
	public static void main(String[] args) {
		/*Car c1 = new Car("C1");
		Car c2 = new Car("C2");
		
		c1.setNext(c2);
		c2.setPrevious(c1);
		
		Train t1 = new Train("T1");
		Train t1b = new Train("T1");
		
		if(t1.getName().equals("T1") && t1.equals(t1b)) {
			System.out.println("Yay 1");
		}
		
		t1.printAscii();
		
		t1.connectCar(c1);
		t1.printAscii();
		
		Car c3 = new Car("C3");
		Car c4 = new Car("C4");
		
		c3.setNext(c4);
		c4.setPrevious(c3);
		
		Car c5 = new Car("C5");
		Car c6 = new Car("C6");
		
		c6.setNext(c5);
		c5.setPrevious(c6);

		t1.connectCar(c3);
		t1.connectCar(c6);
		t1.printAscii();

		t1.reverseTrain();
		t1.printAscii();

		t1.disconnectCar(c5);
		t1.printAscii();

		Car C2 = new Car("C2");
		Car C1 = new Car("C1");

		t1.connectCar(C2);
		t1.connectCar(C1);
		Person p = new Person("Josh", C2);
		System.out.println(p.moveToCar(C1));
		t1.printAscii();
		Car f = new Car("C4");
		t1.connectCar(f);
		t1.printAscii();
		System.out.println(p.moveToCar(f));
		
		Car Car0 = new Car("Car0");
		Car Car1 = new Car("Car1");
		Car Car2 = new Car("Car2");
		Car Car3 = new Car("Car3");
		Car Car4 = new Car("Car4");
		Car Car5 = new Car("Car5");
		Car Car6 = new Car("Car6");
		Car Car9 = new Car("Car9");
		Car Car72 = new Car("Car72");
		Car Car80 = new Car("Car80");

		Person Xi = new Person("Xi", Car9);
		Person Sam = new Person("Sam", Car9);
		Person Hassan = new Person("Hassan", Car2);
		Person Alex = new Person("Alex", Car3);
		Person Billy = new Person("Billy", Car6);
		Person Ashley = new Person("Ashley", Car5);
		Person Jon = new Person("Jon", Car6);
		Person Kim = new Person("Kim", Car2);
		Person Lilly = new Person("Lilly", Car3);
		Person Misa = new Person("Misa", Car1);

		Train Tom = new Train("Tom");
		Tom.connectCar(Car1);
		Tom.connectCar(Car5);
		Tom.connectCar(Car6);
		Train Ed = new Train("Ed");
		Ed.connectCar(Car3);
		Ed.connectCar(Car80);
		Ed.connectCar(Car9);
		Train Jim = new Train("Jim");
		Jim.connectCar(Car0);
		Jim.connectCar(Car2);
		Train Em = new Train("Em");
		Em.connectCar(Car4);
		Em.connectCar(Car72);

		Hassan.moveToCar(Car0);
		Ashley.moveToCar(Car6);
		Jon.moveToCar(Car5);

		Tom.disconnectCar(Car5);
		Ed.disconnectCar(Car9);
		Jim.disconnectCar(Car2);

		Ashley.moveToCar(Car5);
		Billy.moveToCar(Car5);
		Alex.moveToCar(Car80);

		Tom.connectCar(Car2);
		Em.connectCar(Car9);

		Ashley.moveToCar(Car6);
		Kim.moveToCar(Car1);
		Sam.moveToCar(Car72);

		Tom.printAscii();
		Ed.printAscii();
		Jim.printAscii();
		Em.printAscii();
		System.out.println(Tom.size + " " + Ed.size + " " + Jim.size + " " + Em.size);
		*/
	}
	
	//*****************************************************************/
	//****************** DO NOT EDIT BELOW THIS LINE ******************/
	//*****************************************************************/
	/**
	 * Tostring method.
	 * @return a string representation of the train.
	 */
	public String toString() {
		String s = getName();
		for(Car c : this) {
			s += " " + c;
		}
		return s;
	}
	
	/**
	 * prints ascii.
	 */
	public void printAscii() {
		/*
		From: http://www.ascii-art.de/ascii/t/train.txt
		    o O___ _________
		  _][__|o| |O O O O|
		 <_______|-|_______|
		  /O-O-O     o   o  
		*/
		
		System.out.print(String.format("%-4s",getName())+"o O___");
		for(Car c : this) {
			System.out.print(" _________");
		}
		System.out.println();
		
		System.out.print("  _][__|o|");
		for(Car c : this) {
			System.out.print(" | "+String.format("%-5s",c.getName())+" |");
		}
		System.out.println();
		
		System.out.print(" |_______|");
		for(Car c : this) {
			System.out.print("-|_______|");
		}
		System.out.println();
		
		System.out.print("  /O-O-O  ");
		for(Car c : this) {
			System.out.print("   o   o  ");
		}
		System.out.println();
	}
}