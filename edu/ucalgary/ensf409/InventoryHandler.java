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
 * @version 1.1
 * @since 1.0
 */

/**
 * InventoryHandler class handles inventory data and creates valid combinations
 * of furniture to be used
 */
public class InventoryHandler {
	private ArrayList<ArrayList<Furniture>> combinations = new ArrayList<ArrayList<Furniture>>();;

	/**
	 * InventoryHandler constructor to set combinations based on furniture data read
	 * from database
	 */
	public InventoryHandler(ArrayList<Furniture> furnitureData) {
		this.setCombinations(removeExcessCombinations(generateCombinations(furnitureData)));
	}

	/**
	 * InventoryHandler constructor to set combinations based on existing
	 * combinations
	 */
	public InventoryHandler(InventoryHandler inv) {
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
	public ArrayList <ArrayList<Furniture>> generateCombinations(ArrayList <Furniture> f)
    {
		ArrayList <ArrayList <Furniture>> frnt = new ArrayList<ArrayList <Furniture>>();	//ArrayList that will contain combinations of furniture (each combination is an individual ArrayList consisting of furniture)
		ArrayList<Furniture> ft;	//ArrayList to hold one combination of furniture 
		if(!f.isEmpty()){	//check if input ArrayList is empty
			int n = f.get(0).getParts().length;	//get the number of parts that the furniture in the ArrayList will have 
			if(n == 1)	//if the furniture is only made of one part
			{
				for(int i = 0; i < f.size(); i++) //iterate through ArrayList of furniture
				{		
					if(f.get(i).getParts()[n-1] == 'Y')	//check if furniture item is valid with 1 total item
					{							ft = new ArrayList<Furniture>(); //create new ArrayList to hold one combination of furniture
						ft.add(f.get(i));	//add furniture item to an ArrayList
						frnt.add(ft);	//add the ArrayList consisting of one combination to the combinations ArrayList
					}
				}
				
			}
			else if(n == 2)	//if the furniture is made up of two parts
			{
				for(int i = 0; i < f.size(); i++) 
				{
					if(f.get(i).getParts()[n-2] == 'Y' && f.get(i).getParts()[n-1] == 'Y') //check if furniture is valid with 1 total item
					{
						ft = new ArrayList<Furniture>(); //create new ArrayList to hold one combination of furniture
						ft.add(f.get(i));	//add furniture item to an ArrayList
						frnt.add(ft);	//add the ArrayList consisting of one combination to the combinations ArrayList
					}
					else //if furniture item is not valid on its own, check if it can be combined with a second item
					{
						for(int j = i + 1; j < f.size(); j++) 
						{
							if((f.get(i).getParts()[n-2] == 'Y' || f.get(j).getParts()[n-2] == 'Y') //check if furniture combination is valid with 2 total items
							&& (f.get(i).getParts()[n-1] == 'Y' || f.get(j).getParts()[n-1] == 'Y')) 
							{
								ft = new ArrayList<Furniture>(); //create new ArrayList to hold one combination of furniture
								ft.add(f.get(i)); 	//add furniture item to an ArrayList holding one combination
								ft.add(f.get(j));	//add furniture item to an ArrayList holding one combination
								frnt.add(ft);	//add combination to combinations ArrayList
							}
						}
					}
				}
			}
			else if(n == 3) //if furniture item is made up of 3 parts
			{
				for(int i = 0; i < f.size(); i++) 
				{
					if(f.get(i).getParts()[n-3] == 'Y' && f.get(i).getParts()[n-2] == 'Y' && f.get(i).getParts()[n-1] == 'Y') //check if furniture combination is valid with 1 total item
					{
						ft = new ArrayList<Furniture>(); //create new ArrayList to hold one combination of furniture
						ft.add(f.get(i));	//add furniture item to an ArrayList holding one combination
						frnt.add(ft);	//add combination to combinations ArrayList
					}
					else //if furniture item is not valid on its own, check if it can be combined with a second item
					{
						if(i < i + 1) 
						{
							for(int j = i + 1; j < f.size(); j++) 
							{
								if((f.get(i).getParts()[n-3] == 'Y' || f.get(j).getParts()[n-3] == 'Y') //check if furniture combination is valid with 2 total items
								&& (f.get(i).getParts()[n-2] == 'Y' || f.get(j).getParts()[n-2] == 'Y')
								&& (f.get(i).getParts()[n-1] == 'Y' || f.get(j).getParts()[n-1] == 'Y'))
								{
									ft = new ArrayList<Furniture>();	//create new ArrayList to hold one combination of furniture
									ft.add(f.get(i));	//add furniture item to an ArrayList holding one combination
									ft.add(f.get(j));	//add furniture item to an ArrayList holding one combination
									frnt.add(ft);	//add combination to combinations ArrayList
								}
								else //if furniture combination is not valid with 2 total items, check if it can be combined with a third item
								{
									if(j < i + 2) 
									{
										for(int k = i + 2; k < f.size(); k++) 
										{
											if((f.get(i).getParts()[n-3] == 'Y' || f.get(j).getParts()[n-3] == 'Y' || f.get(k).getParts()[n-3] == 'Y') //check if furniture combination is valid with 3 total items
											&& (f.get(i).getParts()[n-2] == 'Y' || f.get(j).getParts()[n-2] == 'Y'|| f.get(k).getParts()[n-2] == 'Y')
											&& (f.get(i).getParts()[n-1] == 'Y' || f.get(j).getParts()[n-1] == 'Y'|| f.get(k).getParts()[n-1] == 'Y'))
											{
												ft = new ArrayList<Furniture>();	//create new ArrayList to hold one combination of furniture
												ft.add(f.get(i));	//add furniture item to an ArrayList holding one combination
												ft.add(f.get(j));	//add furniture item to an ArrayList holding one combination
												ft.add(f.get(k));	//add furniture item to an ArrayList holding one combination
												frnt.add(ft);	//add combination to combinations ArrayList
											}
										}
									}
								}
							}
						}
					}
				}
			}
			else if(n == 4) //if furniture is made up of 4 parts
			{
				for(int i = 0; i < f.size(); i++) 
				{
					if(f.get(i).getParts()[n-4] == 'Y' && f.get(i).getParts()[n-3] == 'Y' && f.get(i).getParts()[n-2] == 'Y' && f.get(i).getParts()[n-1] == 'Y') //check if furniture combination is valid with 1 total item
					{
						ft = new ArrayList<Furniture>();	//create new ArrayList to hold one combination of furniture
						ft.add(f.get(i));	//add furniture item to an ArrayList holding one combination
						frnt.add(ft);	//add combination to combinations ArrayList
					}
					else //if furniture item is not valid on its own, check if it can be combined with a second item
					{
						if(i < i + 1) 
						{
							for(int j = i + 1; j < f.size(); j++) 
							{
								if((f.get(i).getParts()[n-4] == 'Y' || f.get(j).getParts()[n-4] == 'Y') //check if furniture combination is valid with 2 total items
									&& (f.get(i).getParts()[n-3] == 'Y' || f.get(j).getParts()[n-3] == 'Y')
									&& (f.get(i).getParts()[n-2] == 'Y' || f.get(j).getParts()[n-2] == 'Y') 
									&& (f.get(i).getParts()[n-1] == 'Y' || f.get(j).getParts()[n-1] == 'Y'))
								{
									ft = new ArrayList<Furniture>();	//create new ArrayList to hold one combination of furniture
									ft.add(f.get(i));	//add furniture item to an ArrayList holding one combination
									ft.add(f.get(j));	//add furniture item to an ArrayList holding one combination
									frnt.add(ft);	//add combination to combinations ArrayList
								}
								else  //if furniture item is not valid in combination with 2 total items, check if it can be combined with a third item
								{
									if(j < i + 2) 
									{
										for(int k = i + 2; k < f.size(); k++) 
										{
											if((f.get(i).getParts()[n-4] == 'Y' || f.get(j).getParts()[n-4] == 'Y' || f.get(k).getParts()[n-4] == 'Y') //check if furniture combination is valid with 3 total items
												&& (f.get(i).getParts()[n-3] == 'Y' || f.get(j).getParts()[n-3] == 'Y'|| f.get(k).getParts()[n-3] == 'Y')
												&& (f.get(i).getParts()[n-2] == 'Y' || f.get(j).getParts()[n-2] == 'Y' || f.get(k).getParts()[n-2] == 'Y')
												&& (f.get(i).getParts()[n-1] == 'Y' || f.get(j).getParts()[n-1] == 'Y' || f.get(k).getParts()[n-1] == 'Y'))
											{
												ft = new ArrayList<Furniture>();	//create new ArrayList to hold one combination of furniture
												ft.add(f.get(i));	//add furniture item to an ArrayList holding one combination
												ft.add(f.get(j));	//add furniture item to an ArrayList holding one combination
												ft.add(f.get(k));	//add furniture item to an ArrayList holding one combination
												frnt.add(ft);	//add combination to combinations ArrayList
											}
											else //if furniture item is not valid in combination with 3 total items, check if it can be combined with a fourth item
											{
												if(k < i + 3)
												{
													for(int l = i + 3; l < f.size(); l++)
													{
														if((f.get(i).getParts()[n-4] == 'Y' || f.get(j).getParts()[n-4] == 'Y' || f.get(k).getParts()[n-4] == 'Y' || f.get(l).getParts()[n-4] == 'Y') //check if furniture combination is valid with 4 total items
															&& (f.get(i).getParts()[n-3] == 'Y' || f.get(j).getParts()[n-3] == 'Y'|| f.get(k).getParts()[n-3] == 'Y' || f.get(l).getParts()[n-3] == 'Y')
															&& (f.get(i).getParts()[n-2] == 'Y' || f.get(j).getParts()[n-2] == 'Y' || f.get(k).getParts()[n-2] == 'Y' || f.get(l).getParts()[n-2] == 'Y')
															&& (f.get(i).getParts()[n-1] == 'Y' || f.get(j).getParts()[n-1] == 'Y' || f.get(k).getParts()[n-1] == 'Y' || f.get(l).getParts()[n-1] == 'Y'))
														{
															ft = new ArrayList<Furniture>();	//create new ArrayList to hold one combination of furniture
															ft.add(f.get(i));	//add furniture item to an ArrayList holding one combination
															ft.add(f.get(j));	//add furniture item to an ArrayList holding one combination
															ft.add(f.get(k));	//add furniture item to an ArrayList holding one combination	
															ft.add(f.get(l));	//add furniture item to an ArrayList holding one combination
															frnt.add(ft);	//add combination to combinations ArrayList
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
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
	 * Method to check if combinations ArrayList has excess/invalid combinations
	 * 
	 * @param frnt ArrayList with all of the combinations
	 * @return boolean depending on whether the ArrayList has excess/invalid
	 *         combinations
	 */
	public boolean removeExcessCheck(ArrayList<ArrayList<Furniture>> frnt) {
		int k = 0;
		for (int i = 0; i < frnt.size(); i++) {
			for (int j = 0; j < frnt.size(); j++) {
				if (frnt.get(i).size() < frnt.get(j).size()) {
					for (k = 0; k < frnt.get(i).size(); k++) {
						if (!frnt.get(j).contains(frnt.get(i).get(k))) {
							break;
						}
					}
					if (k == frnt.get(i).size()) {
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * Method to return index of excess/invalid combination
	 * 
	 * @param frnt ArrayList with all of the combinations
	 * @return index of excess/invalid combination
	 */
	public int removeExcessIndex(ArrayList<ArrayList<Furniture>> frnt) {
		int k;
		int index = 0;
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
		while (removeExcessCheck(fList)) {
			fList.remove(removeExcessIndex(fList));
		}
		return fList;
	}

	/**
	 * Method to check if combination has been used in an order
	 * 
	 * @param source - ArrayList with all of the combinations
	 * @param order - ArrayList with all of the combinations used in an order
	 * @return boolean depending on whether combination has been used in an order or
	 *         not
	 */
	public boolean combinationUsedCheck(ArrayList<ArrayList<Furniture>> source, ArrayList<ArrayList<Furniture>> order) {
		for (int i = 0; i < source.size(); i++) {
			for (int j = 0; j < order.size(); j++) {
				for (int k = 0; k < order.get(j).size(); k++) {
					if (source.get(i).contains(order.get(j).get(k))) {
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * Method to return index of combination that has been used in an order
	 * 
	 * @param source - ArrayList with all of the combinations
	 * @param order - ArrayList with all of the combinations used in an order
	 * @return index of combination that has been used in order
	 */
	public int combinationUsedIndex(ArrayList<ArrayList<Furniture>> source, ArrayList<ArrayList<Furniture>> order) {
		int index = 0;
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
	 * @param order - ArrayList with all of the combinations used in an order
	 * @return ArrayList without combinations used in an order
	 */
	public ArrayList<ArrayList<Furniture>> removeUsedCombinations(ArrayList<ArrayList<Furniture>> source,
			ArrayList<ArrayList<Furniture>> order) {
		ArrayList<ArrayList<Furniture>> fList = new ArrayList<ArrayList<Furniture>>(source);
		while (combinationUsedCheck(fList, order) == true) {
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
	
} //end of class declaration, InventoryHandler
