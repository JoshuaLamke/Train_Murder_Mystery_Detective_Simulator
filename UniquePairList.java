/**
 * UniquePairList class that represents a list that holds unique key-value pairs.
 * @param <K> The key for the item.
 * @param <V> The value for the item.
 */
class UniquePairList<K,V> {

	/**
	 * Pair class that represents a key-value pair.
	 * @param <K> The key for the pair.
 	 * @param <V> The value for the pair.
	 */
	private static class Pair<K,V> {
		
		/**
		 * The key for the pair.
		 */
		private K key;
		
		/**
		 * The value stored in the pair.
		 */
		private V value;

		/**
		 * Constructor for the pair class.
		 * @param key The key for the pair used for matching.
		 * @param value The value stored in the pair.
		 */
		public Pair(K key, V value) {
			this.key = key;
			this.value = value;
		}
		
		/**
		 * Checks to see if two pair are equal by checking to see if their keys are equal.
		 * @param o The object to be compared.
		 * @return boolean stating wether the pair is equal or not.
		 */
		@SuppressWarnings("unchecked")
		public boolean equals(Object o) {
			//Two pairs are equal ONLY if their KEYS are
			//equal. Values don't matter for equality.
			//O(1)
			if(o instanceof Pair){
				Pair<K,V> match = (Pair<K,V>) o;
				if(this.key.equals(match.getKey())){
					return true;
				}
			}
			else{
				if(this.key.equals(o)){
					return true;
				}
			}
			
			return false;
		}
		
		/**
		 * ToString for the pair class. Prints out a pair's key and value with a comma seperating them.
		 * @return A string representation of the pair.
		 */
		public String toString() {
			//this method is done for you
			return "<" + getKey() + "," + getValue() + ">";
		}
		
		/**
		 * Gets the key for the pair.
		 * @return The key of this pair.
		 */
		public K getKey() {
			//returns the key from the pair
			//O(1)
			return key;
		}
		
		/**
		 * Gets the value of the pair.
		 * @return The value of this pair.
		 */
		public V getValue() {
			//returns the value from the pair
			//O(1)
			return value;
		}
	}
	
	/**
	 * Main method.
	 * @param args command line arguements.
	 */
	public static void main(String[] args) {
		Pair<String,Integer> p1 = new Pair<>("Fred", 1);
		Pair<String,Integer> p2 = new Pair<>("Alex", 1);
		Pair<String,Integer> p3 = new Pair<>("Fred", 2);
		
		if(p1.getKey().equals("Fred") && p1.getValue() == 1 && p1.equals(p3)) {
			System.out.println("Yay 1");
		}
		
		if(!p1.equals(p2)) {
			System.out.println("Yay 2");
		}
		
		//this is actually a test of UniqueList, not UniquePairList
		UniqueList<Pair<String,Integer>> set = new UniqueList<>();
		set.append(p1);
		
		//get the value from the set that is _equal to_ p3 (in this case, p1)
		Pair<String,Integer> p1fromSet = set.get(p3);
		if(p1fromSet.getValue() == 1) {
			System.out.println("Yay 3");
		}
	}
	
	//*****************************************************************/
	//****************** DO NOT EDIT BELOW THIS LINE ******************/
	//********************* EXCEPT TO ADD JAVADOCS ********************/
	//*****************************************************************/
	/**
	 * UniqueList made up of Pairs.
	 */
	private UniqueList<Pair<K,V>> set = new UniqueList<>();
	
	/**
	 * Appends a pair to the end of the list if it is unique.
	 * @param key The key for the pair.
	 * @param value The value of the pair.
	 * @return Boolean stating whether the pair was added or not.
	 */
	public boolean append(K key, V value) {
		Pair<K,V> pair = new Pair<>(key, value);
		return set.append(pair);
	}
	
	/**
	 * Either adds or deletes a pair from the list depending on whether it already exists or not.
	 * @param key The key for the pair.
	 * @param value The value for the pair.
	 * @return Boolean stating whether the pair was added or deleted.
	 */
	public boolean update(K key, V value) {
		Pair<K,V> pair = new Pair<>(key, value);
		if(!remove(key)) {
			return false;
		}
		return set.append(pair);
	}
	
	/**
	 * Removes a pair from the list.
	 * @param key the key used to search for the removed pair.
	 * @return boolean stating whether or not the pair was removed.
	 */
	public boolean remove(K key) {
		Pair<K,V> pair = new Pair<>(key, null);
		return set.remove(pair);
	}
	
	/**
	 * Gets the value stored in the pair with a certain key.
	 * @param key The key to the pair with the value to be returned.
	 * @return The value of the pair that matched the key.
	 */
	public V getValue(K key) {
		Pair<K,V> pair = new Pair<>(key, null);
		return set.get(pair).getValue();
	}
	
	/**
	 * Gets the keys of the pairs in the list.
	 * @return A cloned list of keys.
	 */
	public UniqueList<K> getKeys() {
		UniqueList<K> keySet = new UniqueList<>();
		for(Pair<K,V> p : set) {
			keySet.append(p.getKey());
		}
		return keySet.clone();
	}
	
	/**
	 * Gets the size of the list.
	 * @return The size of the list.
	 */
	public int size() {
		return set.size();
	}
}