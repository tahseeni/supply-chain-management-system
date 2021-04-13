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
		this.setCombinations(removeExcessCombinations(removeInvalidCombination(generateCombinations(furnitureData))));
	}

	/**
	 * InventoryHandler constructor to set combinations based on existing
	 * combinations
	 */
	public Inventory(Inventory inv) {
		this.combinations = inv.getCombinations();
	}

	/**
	 * Method to generate combinations for furniture that can be combined to make a
	 * full item to be sold to the user
	 * 
	 * @param f is an ArrayList with furniture data from a specific category and
	 *          type
	 * @return ArrayList of combinations of furniture
	 */
	public ArrayList<ArrayList<Furniture>> generateCombinations(ArrayList<Furniture> f) {
		// ArrayList that will contain combinations of furniture 
		// (each combination is an individual ArrayList consisting of furniture)
		ArrayList<ArrayList<Furniture>> frnt = new ArrayList<ArrayList<Furniture>>();

		ArrayList<Furniture> ft; // ArrayList to hold one combination of furniture
		if (!f.isEmpty()) { // check if input ArrayList is empty
			int[] ind = new int[f.size()];
			for (int i = 0; i < f.size(); i++) {
				ind[i] = i;
			}
			List<int[]> in = new ArrayList<>(this.all(f.get(0).getParts().length, ind));
			for (int i = 0; i < in.size(); i++) {
				ft = new ArrayList<Furniture>();
				for (int j = 0; j < in.get(i).length; j++) {
					ft.add(f.get(in.get(i)[j]));
				}
				frnt.add(ft);
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
	public int removeExcessIndex(ArrayList<ArrayList<Furniture>> frnt) {
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
	 * Method to return an ArrayList without excess/invalid combinations
	 * 
	 * @param f ArrayList with all of the combinations
	 * @return ArrayList without excess/invalid combinations
	 */
	public ArrayList<ArrayList<Furniture>> removeExcessCombinations(ArrayList<ArrayList<Furniture>> f) {
		ArrayList<ArrayList<Furniture>> fList = new ArrayList<ArrayList<Furniture>>(f);
		while (removeExcessIndex(fList) >= 0) {
			fList.remove(removeExcessIndex(fList));
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
	public int combinationUsedIndex(ArrayList<ArrayList<Furniture>> source, ArrayList<ArrayList<Furniture>> order) {
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
	 * Method to return an ArrayList without used combinations
	 * 
	 * @param source - ArrayList with all of the combinations
	 * @param order  - ArrayList with all of the combinations used in an order
	 * @return ArrayList without combinations used in an order
	 */
	public ArrayList<ArrayList<Furniture>> removeUsedCombinations(ArrayList<ArrayList<Furniture>> source,
			ArrayList<ArrayList<Furniture>> order) {
		ArrayList<ArrayList<Furniture>> fList = new ArrayList<ArrayList<Furniture>>(source);
		while (combinationUsedIndex(fList, order) >= 0) {
			fList.remove(combinationUsedIndex(fList, order));
		}
		return fList;
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

	public int[] getSubset(int[] input, int[] subset) {
		int[] result = new int[subset.length];
		for (int i = 0; i < subset.length; i++) {
			result[i] = input[subset[i]];
		}
		return result;
	}

	public List<int[]> all(int n, int[] input) {
		List<int[]> subsets = new ArrayList<>();

		for (int j = 0; j < n; j++) {
			int k = j + 1;
			int[] s = new int[k]; // store indices here
			// pointing to elements in input array

			if (k <= input.length) {
				// first index sequence: 0, 1, 2, ...
				for (int i = 0; (s[i] = i) < k - 1; i++);
				subsets.add(getSubset(input, s));
				while(true) {
					int i;
					// find position of item that can be incremented
					for (i = k - 1; i >= 0 && s[i] == input.length - k + i; i--);
					if (i < 0) {
						break;
					}
					s[i]++; // increment this item's value
					while (++i < k) { 
						// fill up remaining items
						s[i] = s[i - 1] + 1;
					}
					subsets.add(getSubset(input, s));
				}
			}
		}
		return subsets;
	}

	public int indexInvalid(ArrayList<ArrayList<Furniture>> frnt) {
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

	public ArrayList<ArrayList<Furniture>> removeInvalidCombination(ArrayList<ArrayList<Furniture>> frnt) {
		ArrayList<ArrayList<Furniture>> fList = new ArrayList<ArrayList<Furniture>>(frnt);
		while (indexInvalid(fList) != -1) {
			fList.remove(indexInvalid(fList));
		}
		return fList;
	}

} // end of class declaration, InventoryHandler
