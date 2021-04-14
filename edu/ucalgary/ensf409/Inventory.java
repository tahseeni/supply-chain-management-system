package edu.ucalgary.ensf409;

import java.util.*;

/**
 * @author Gurpartap Sohi <a href="mailto:gurpartap.sohi@ucalgary.ca">
 *         gurpartap.sohi@ucalgary.ca</a>
 * 
 * @author Tahseen Intesar <a href="mailto:tahseen.intesar@ucalgary.ca">
 *         tahseen.intesar@ucalgary.ca</a>
 * 
 * @author Nabeel Amjad<a href="mailto:nabeel.amjad@ucalgary.ca">
 *         nabeel.amjad@ucalgary.ca</a>
 * 
 * @author Stalin D Cunha<a href="mailto:stalin.dcunha@ucalgary.ca">
 *         stalin.dcunha@ucalgary.ca</a>
 * 
 * @version 1.2
 * @since 1.0
 */

/**
 * Inventory class handles inventory data and creates valid combinations
 * of furniture to be used
 */
public class Inventory {
	private ArrayList<ArrayList<Furniture>> combinations = new ArrayList<ArrayList<Furniture>>();;

	/**
	 * InventoryHandler constructor to set combinations based on furniture data read
	 * from database
	 */
	public Inventory(ArrayList<Furniture> furnitureData) {
		this.setCombinations(removeInvalidCombinations(generateCombinations(furnitureData)));
	}

	/**
	 * InventoryHandler constructor to set combinations based on existing
	 * combinations
	 */
	public Inventory(Inventory inv) {
		this.combinations = inv.getCombinations();
	}

	/**
     * Method to generate combinations for furniture
     * @param f is an ArrayList with furniture items from a specific category and type
     * @return ArrayList of combinations of furniture
     */
    public ArrayList <ArrayList<Furniture>> generateCombinations(ArrayList <Furniture> f)
    {
		ArrayList <ArrayList <Furniture>> frnt = new ArrayList<ArrayList <Furniture>>();	//ArrayList that will contain combinations of furniture 
		ArrayList<Furniture> ft;	//ArrayList to hold one combination of furniture 
		List <Integer> ints = new ArrayList<Integer>();	//ArrayList that holds indices of furniture objects
		if(!f.isEmpty()) //check if furniture data from the database has been transferred correctly
		{
			int n = f.get(0).getParts().length;	//number of parts 

			//copy indices of furniture objects to ArrayList of integers
			for(int i = 0; i < f.size(); i++) {
				 ints.add(i);
			}
			
			List <List<Integer>> in = new ArrayList<>();	//ArrayList to hold combinations of integers
	
			//loop will run n times and will generate combinations of indices
			//combinations will have a minimum size of 1 and a maximum size of n 
			for(int i = 0; i < n; i ++)	
			{
				int[] temp = new int[i+1]; //array that is used to store a combination of integers
				combineInts(ints, temp, 0, n - 1, 0, i + 1, in); //call function to create combinations of indices that are of size i + 1
			}
	
			//create combinations of furniture based on combinations of indices 
			for(int i = 0; i < in.size(); i++)
			{
				ft = new ArrayList<Furniture>();	//create new furniture combination
				for(int j = 0; j < in.get(i).size(); j++)	
				{
					ft.add(f.get(in.get(i).get(j)));	//add furniture object at specified index to an ArrayList holding one combination
				}
				frnt.add(ft);	//add combination to an ArrayList holding all combinations
			}	
		}
		return frnt;
    }

	/**
	 * Method to return index of cheapest combination within an ArrayList with all
	 * valid combinations of furniture
	 * 
	 * @param frnt ArrayList with all valid combinations of furniture
	 * @return return index of cheapest combination
	 */
	public int findCheapest(ArrayList<ArrayList<Furniture>> frnt) {
		int min = 0; // default index starting at first element
		int p1 = 0; // price of current cheapest item
		int p2 = 0; // price of current element

		// default minimum value starting at first element
		for (int j = 0; j < frnt.get(0).size(); j++) {
			p1 += frnt.get(0).get(j).getPrice();
		}

		// iterate through ArrayList to find cheapest price
		for (int j = 0; j < frnt.size(); j++) {
			for (int k = 0; k < frnt.get(j).size(); k++) {
				p2 += frnt.get(j).get(k).getPrice(); // find price of current element
			}
			if (p2 == p1) // if two elements have the same cheapest price
			{
				if (frnt.get(j).size() < frnt.get(min).size()) // check if one element is smaller than the other
				{
					min = j; // change index of cheapest element to the element with the smaller size
					p1 = 0;
					for (int l = 0; l < frnt.get(min).size(); l++) {
						p1 += frnt.get(min).get(l).getPrice(); // calculate price of the new cheapest element
					}
				}
			} else if (p2 < p1) // if current element's price is lower than the cheapest price
			{
				min = j; // change index to the cheaper element's index
				p1 = 0;
				for (int l = 0; l < frnt.get(min).size(); l++) {
					p1 += frnt.get(min).get(l).getPrice(); // calculate price of the new cheapest element
				}
			}
			p2 = 0; // reset price of current element to be calculated again
		}
		return min; // return index of cheapest element
	}

	/**
	 * Method to return index of excess/redundant combinations. For example, if
	 * combinations [C1 C2 C3] and [C1 C3] are valid, the method will remove [C1 C2
	 * C3] from the combinations ArrayList
	 * 
	 * @param frnt - ArrayList with all of the combinations
	 * @return index of excess combinations; returns -1 if not found
	 */
	public int excessIndex(ArrayList<ArrayList<Furniture>> frnt) {
		int k;
		int index = -1;
		for (int i = 0; i < frnt.size(); i++) {
			for (int j = 0; j < frnt.size(); j++) {
				if (frnt.get(i).size() < frnt.get(j).size()) {
					for (k = 0; k < frnt.get(i).size(); k++) {
						if (!frnt.get(j).contains(frnt.get(i).get(k))) {
							break;
						}
					}
					if (k == frnt.get(i).size()) {
						return j;
					}
				}
			}
		}
		return index;
	}

	/**
	 * Method to return index of invalid combinations that do not
	 * contain all of the parts required to make a complete furniture item.
	 * 
	 * @param frnt - ArrayList with all of the combinations
	 * @return index of invalid combinations; returns -1 if not found
	 */
	public int invalidIndex(ArrayList<ArrayList<Furniture>> frnt) {
		int count = 0;
		int n = frnt.get(0).get(0).getParts().length;
		for (int i = 0; i < frnt.size(); i++) {
			char[] part = new char[n];
			for (int j = 0; j < frnt.get(i).size(); j++) {
				for (int k = 0; k < frnt.get(i).get(j).getParts().length; k++) {
					if (frnt.get(i).get(j).getParts()[k] == 'Y') {
						part[k] = 'Y';
					}
				}
			}

			for (int a = 0; a < part.length; a++) {
				if (part[a] == 'Y') {
					count++;
				}
			}
			if (count != n) {
				return i;
			}
			count = 0;
		}
		return -1;
	}

	/**
	 * Method to return an ArrayList without excess/invalid combinations
	 * 
	 * @param f ArrayList with all of the combinations
	 * @return ArrayList without excess/invalid combinations
	 */
	public ArrayList<ArrayList<Furniture>> removeInvalidCombinations(ArrayList<ArrayList<Furniture>> f) {
		ArrayList<ArrayList<Furniture>> fList = new ArrayList<ArrayList<Furniture>>(f);

		while (invalidIndex(fList) != -1) {
			fList.remove(invalidIndex(fList));
		}

		while (excessIndex(fList) != -1) {
			fList.remove(excessIndex(fList));
		}
		return fList;
	}

	/**
	 * Helper method to return index of combination that has been used in the order.
	 * 
	 * @param source - ArrayList with all of the combinations
	 * @param order  - ArrayList with all of the combinations used in an order
	 * @return index of combination that has been used in order, returns -1 if not
	 *         found
	 */ 
	public int usedIndex(ArrayList<ArrayList<Furniture>> source, ArrayList<ArrayList<Furniture>> order) {
		int index = -1;
		for (int i = 0; i < source.size(); i++) {
			for (int j = 0; j < order.size(); j++) {
				for (int k = 0; k < order.get(j).size(); k++) {
					if (source.get(i).contains(order.get(j).get(k))) {
						return i;
					}
				}
			}
		}
		return index;
	}

	/**
	 * Method to return an ArrayList containing combinations that haven't been used in an order
	 * 
	 * @param source - ArrayList with all of the combinations in the inventory
	 * @param order  - ArrayList with all of the combinations used in an order
	 * @return ArrayList containing combinations that haven't been used in an order
	 */
	public ArrayList<ArrayList<Furniture>> removeFromInventory(ArrayList<ArrayList<Furniture>> source,
			ArrayList<ArrayList<Furniture>> order) {
		ArrayList<ArrayList<Furniture>> fList = new ArrayList<ArrayList<Furniture>>(source);
		while (usedIndex(fList, order) != -1) {
			fList.remove(usedIndex(fList, order));
		}
		return fList;
	}

	/**
     * Method to generate combinations of integers of size k
     * 
	 * @param source -	input ArrayList of integers
	 * @param temp	 -	array used to store a combination of integers temporarily
	 * @param s 	 -	starting index of source
	 * @param t		 -	ending index of source
	 * @param index	 -	current index
	 * @param k		 -	size of combination
	 * @param it	 -	ArrayList that will hold combinations of integers
     */	
	public void combineInts(List <Integer> source, int[] temp, int s,
                                int t, int index, int k, List <List<Integer>> it)
    {
		List <Integer> copy = new ArrayList <Integer>(); //create an ArrayList to hold a combination of integers 

		//if index is equal to the size of the combination requested
        if (index == k) {
			int i = 0;
			//iterate through an array containing a combination of integers
			while(i < k) {
				copy.add(temp[i]); //copy each integer in the combination 
				i++;
			}
			it.add(copy); //add combination to ArrayList containing all integer combinations
            return;
        }

		int j = s;
		while((j <= t) && (t - j + 1 >= k - index)) {
			temp[index] = source.get(j); 
			
			//recursively call function to create all combinations of integers
            combineInts(source, temp, j + 1, t, index + 1, k, it);
			j++;
		}
	}		

	/**
	 * Method to return ArrayList with combinations
	 * 
	 * @return ArrayList with combinations
	 */
	public ArrayList<ArrayList<Furniture>> getCombinations() {
		return this.combinations;
	}

	/**
	 * Method to set an ArrayList with all combinations
	 * 
	 * @param fr - ArrayList with all combinations
	 */
	public void setCombinations(ArrayList<ArrayList<Furniture>> fr) {
		this.combinations = new ArrayList<ArrayList<Furniture>>(fr);
	}
} // end of class declaration, InventoryHandler
