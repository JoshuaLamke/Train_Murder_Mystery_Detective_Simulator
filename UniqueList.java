import java.util.Iterator;

import javax.security.auth.PrivateCredentialPermission;

/**
 * UniqueList is an generic iterable list that stores only unique values.
 * @param <T> generic item in the list.
 */
class UniqueList<T> implements Iterable<T> {
	
	/**
	 * The head of the list.
	 */
	Node<T> head;

	/**
	 * The tail of the list.
	 */
	Node<T> tail;

	/**
	 * The size of the list.
	 */
	int size = 0;

	/**
	 * Class that defines a node in the list.
	 * @param <E> generic item in the node.
	 */
	private class Node<E> {
		
		/**
		 * The reference to the next node.
		 */
		private Node<E> next;

		/**
		 * The data in the node.
		 */
		private E data;

		/**
		 * Constructor for the node class. Initializes the node with a gerenric piece of data.
		 * @param data The data stored in the node.
		 */
		Node(E data) {
			this.data = data;
		}

    }

	/**
	 * Appends an item to the list if the list does not already contain that item.
	 * @param value The item to be appended to the list.
	 * @return Boolean stating whther or not the item was appended.
	 */
	public boolean append(T value) {
		//adds an item to the list at the end
		
		//returns false if the value can not be added
		//(i.e. the value already exists in the list)
		
		//O(n) worst case, where n = the number of items
		//because you need to check for duplicates!
		Node<T> newNode = new Node<T>(value);
		if(head == null) {
			head = newNode;
			tail = newNode;
			head.next = null;
			tail.next = null;
			size++;
			return true;
		}
		if(!contains(value)) {
			tail.next = newNode;
			tail = newNode;
			size++;
			return true;
		}
		return false;
	}
	
	/**
	 * Removes a value from the list.
	 * @param value The value to be removed.
	 * @return Boolean stating whther or not the value was removed.
	 */
	public boolean remove(T value) {
		//remove a value from the list
		
		//return false if the item could not be found
		//return true if you remove the item
		
		//O(n) worst case, where n = the number of items
		if(head == null) {
			return false;
		}
		Node<T> indexNode = head; 
		int index = 1;
		if(indexNode.data.equals(value)) {
			head = head.next;
			size--;
			return true;
		}
		else{
			while(index < size){
				if(indexNode.next.data.equals(value)){
					if(indexNode.next.next == null) {
						indexNode.next = null;
						size--;
						return true;
					}
					indexNode.next = indexNode.next.next;
					size--;
					return true;
				}
				index++;
			}
		}
		return false;
	}
	
	/**
	 * Gets a specific value from the list.
	 * @param value The value you wish to get.
	 * @return The value.
	 */
	//@SuppressWarnings("unchecked")
	public T get(T value) {
		//return null if the item could not be found
		//return the item FROM THE LIST if it was found
		//(do not return the parameter, while the value
		//is "equal" they may not be the same in computer
		//memory... review the difference between
		//.equals() and == from CS211)
		
		//O(n) worst case, where n = the number of items
		for(T elem : this) {
			if(elem.equals(value)){
				return elem;
			}
		}
		return null;
	}
	
	/**
	 * Checks to see is an item is contained inside of the list.
	 * @param value The value that is checked in the list.
	 * @return Boolean stating whther or not the list contains the item.
	 */
	public boolean contains(T value) {
		//return true if the item can be found in the
		//list, reuse code from get() to implement this
		//method
		
		//O(n) worst case, where n = the number of items
		
		if(get(value) == null) {
			return false;
		}
		return true;
	}
	
	/**
	 * Gets the size of the list.
	 * @return The size of the list.
	 */
	public int size() {
		//return the number of items in the set
		//O(1)
		return size;
	}
	
	/**
	 * Creates a different list that stores the same values of the current list.
	 * @return The cloned list.
	 */
	public UniqueList<T> clone() {
		//make a copy of the UniqueList such that this is true:
		//	if s1 is a simple set containing {1, 2, 3} and s2 is a clone of s1, then:
		//	s1 != s2 BUT there exist three objects in s1 and s2 which are ==
		//see main method tests for example
		//O(n)

		UniqueList<T> clone = new UniqueList<>();
		for(T elem : this) {
			clone.append(elem);
		}
		return clone;
	}
	
	/**
	 * Returns a new iterator positioned at the front of the list.
	 * @return A new iterator for the list.
	 */
	public Iterator<T> iterator() {
		//return an iterator over the list, returning the first item
		//through the last item (in that order).
		
		//also, you only need to implement next() and
		//hasNext() for this iterator, previous(), add(), etc.
		//are all optional on this assignment (implement them
		//if you want to use them in your code).

		return new Iterator<T>() {
			Node<T> indexNode = head;
			int index = 0;

			public boolean hasNext() {
				return index < size;
			}
			public T next() {
				Node<T> currNode = indexNode;
				indexNode = indexNode.next;
				index++;
				return currNode.data;
			}
		};
	}
	
	/**
	 * Main method.
	 * @param args command line arguments.
	 */
	public static void main(String[] args) {
		UniqueList<String> names = new UniqueList<>();
		
		if(names.append("Fred") && names.append("Alex") && !names.append("Fred")) {
			System.out.println("Yay 0");
		}
		if(names.size() == 2 && names.contains("Fred") && names.get("Alex").equals("Alex")) {
			System.out.println("Yay 1");
		}
		
		if(names.remove("Alex") && names.size() == 1 && names.get("Alex") == null) {
			System.out.println("Yay 2");
		}
		
		boolean hasEverything = false;
		for(String name : names) {
			if(hasEverything) {
				hasEverything = false;
				break;
			}
			
			hasEverything = name.equals("Fred");
		}
	
		if(hasEverything) {
			System.out.println("Yay 3");
		}

		/**
		 * Cat class defined a cat.
		 */
		class Cat {
			
			String name;
			
			/**
			 * constructor for cat.
			 * @param name name of the cat.
			 */
			public Cat(String name) { this.name = name; }
			
			/**
			 * checks to see if objects are equal.
			 * @param o object to be compared.
			 * @return boolean stating whether its equal or not.
			 */
			public boolean equals(Object o) {
				if(o instanceof Cat) {
					Cat c = (Cat)o;
					return c.name.equals(this.name);
				}
				return false;
			}
		}
		
		UniqueList<Cat> catSet1 = new UniqueList<>();
		catSet1.append(new Cat("Sammy"));
		catSet1.append(new Cat("Grouchy"));
		UniqueList<Cat> catSet2 = catSet1.clone();
		if(catSet1 != catSet2 && catSet1.size() == catSet2.size()) {
			int matched = 0;
			for(Cat c : catSet1) {
				if(catSet2.get(c) == c) matched++;
			}
			if(matched == 2) {
				System.out.println("Yay 4");
			}
		}
	}
}