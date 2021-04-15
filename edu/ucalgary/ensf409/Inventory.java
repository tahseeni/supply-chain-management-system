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
 * @version 1.3
 * @since 1.0
 */

/**
 * Inventory Class handles inventory data and creates valid combinations
 * of furniture to be used
 */
public class Inventory{
    private ArrayList <ArrayList<Furniture>> combinations; 

	/**
	 * Inventory constructor to set combinations based on furniture data read from database
     */
    public Inventory(ArrayList<Furniture> furnitureData){
		this.setCombinations((generateCombinations(furnitureData)));
    }

	/**
	 * Inventory constructor to set combinations based on existing combinations
     */
	public Inventory(Inventory inv) {
		this.combinations = new ArrayList <ArrayList<Furniture>>(inv.getCombinations());
	}

	/**
     * Method to generate the combinations of furniture items
     * 
     * @param f - ArrayList with furniture items from a specific category and type
     * @return ArrayList of combinations of furniture
     */
    public ArrayList <ArrayList<Furniture>> generateCombinations(ArrayList <Furniture> f) {
		ArrayList <ArrayList <Furniture>> frnt = new ArrayList<ArrayList <Furniture>>();	//ArrayList that will contain combinations of furniture 
		ArrayList<Furniture> ft;	//ArrayList to hold one combination of furniture 
		List <Integer> ints = new ArrayList<Integer>();	//ArrayList that holds indices of furniture objects
		if(!f.isEmpty()) //check if furniture data from the database has been transferred correctly
		{
			//copy indices of furniture objects to ArrayList of integers
			for(int i = 0; i < f.size(); i++) {
				 ints.add(i);
			}
			
			List <List<Integer>> in = new ArrayList<>(); //ArrayList to hold combinations of integers
	
			//loop will run n times and will generate combinations of indices
			//combinations will have a minimum size of 1 and a maximum size of n 
			for(int i = 0; i < f.size(); i++) {
				//array that is used to store a combination of integers
				int[] temp = new int[i+1];
				
				//call function to create combinations of indices that are of size i + 1
				combineInts(ints, temp, 0, ints.size() - 1, 0, i + 1, in);
			}
	
			//create combinations of furniture based on combinations of indices 
			for(int i = 0; i < in.size(); i++) {
				ft = new ArrayList<Furniture>(); //create new furniture combination
				for(int j = 0; j < in.get(i).size(); j++) {
					ft.add(f.get(in.get(i).get(j))); //add furniture object at specified index to an ArrayList holding one combination
				}
				frnt.add(ft);	//add combination to an ArrayList holding all combinations
			}	
		}
		return frnt;
    }

	/**
     * Method to return index of cheapest combination from an 
     * input ArrayList containing the valid combinations
     * 
     * @param frnt - ArrayList with all valid combinations of furniture
     * @return index of cheapest combination
     */	
	public int findCheapest(ArrayList <ArrayList <Furniture>> frnt)
	{
		int min = 0;	//default index starting at first element
		int p1 = 0;		//price of current cheapest item
		int p2 = 0;		//price of current element

		//default minimum value starting at first element 
		for(int j = 0; j < frnt.get(0).size(); j++)
		{
			p1 += frnt.get(0).get(j).getPrice();
		}

		//iterate through ArrayList to find cheapest price 
		for(int j = 0; j < frnt.size(); j++)
		{
			for(int k = 0; k < frnt.get(j).size(); k++)
			{
				p2 += frnt.get(j).get(k).getPrice(); //find price of current element
			}
			if(p2 == p1)	//if two elements have the same cheapest price
			{
				if(frnt.get(j).size() < frnt.get(min).size()) //check if one element is smaller than the other
				{
					min = j;	//change index of cheapest element to the element with the smaller size
					p1 = 0;
					for(int l = 0; l < frnt.get(min).size(); l++)
					{
						p1 += frnt.get(min).get(l).getPrice(); //calculate price of the new cheapest element
					}
				}
			}
			else if(p2 < p1)	//if current element's price is lower than the cheapest price
			{
				min = j;	//change index to the cheaper element's index
				p1 = 0;
				for(int l = 0; l < frnt.get(min).size(); l++)
				{
					p1 += frnt.get(min).get(l).getPrice();	//calculate price of the new cheapest element
				}
			}
			p2 = 0;	//reset price of current element to be calculated again
		}
		return min;	//return index of cheapest element in the ArrayList
	}

	/**
     * Helper method called by generateCombinations() to 
     * generate combinations of integers of size k
     * 
	 * @param source -	input ArrayList of integers
	 * @param temp	 -	array used to store combinations temporarily
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

        if (index == k) //if index is equal to the size of the combination requested
        {
			int i = 0;
			while(i < k) //iterate through an array containing a combination of integers
			{
				copy.add(temp[i]); //copy each integer in the combination 
				i++;
			}
			it.add(copy); //add combination to ArrayList containing all integer combinations
            return;
        }

		int j = s;
		while((j <= t) && (t - j + 1 >= k - index))
		{
			temp[index] = source.get(j); 
            combineInts(source, temp, j + 1, t, index + 1, k, it); //recursively call function to create all combinations of integers
			j++;
		}
	}

	/**
     * Method to check whether a combination of furniture is valid.
     * It does so by checking if a combination contains all of the required parts,
     * accounting for the leftover parts of multiple entries
     * 
     * @param n 	- number of items requested in an order
	 * @param parts	- ArrayList containing all of the parts in a combination of furniture items
     * @return 		- true if combination is valid, otherwise false if combination is invalid
	 */
	public boolean isValid(int n, ArrayList<char[]> parts) {
        int[] stock = new int[parts.get(0).length]; //array containing quantity of each part in stock
		
        for(int i = 0; i < parts.size(); i++) //iterate through entire combination
		{
            for (int j = 0; j < parts.get(i).length; j++) //iterate through each part of combination
			{
            	//check if part is valid
                if(parts.get(i)[j] == 'Y')
				{
                    stock[j] += 1;	//increase quantity of part in stock by 1
                }
            }
        }

		int k;
        for (k = 0; k < stock.length; k++) //iterate through array containing quantity of each part
		{
            if (!(stock[k] >= n)) //check if the quantity of each part in stock is not greater than or equal than the number of items requested
			{
                break;	//break loop if the requested quantity of parts are not in stock
            }
        }

        //check if requested quantity of parts are in stock
		if(k == stock.length) {
			return true;	//return true if requested quantity of parts are in stock
		}
		else {
			return false;	//return false if requested quantity of parts are not in stock
		}
    }

	/**
     * Method to return all valid combinations of furniture items
     * These combinations will contain all of the requested parts
     * 
     * @param f2  -	ArrayList with all possible combinations of furniture items
	 * @param n	  -	number of items requested
     * @return ArrayList containing all of the valid combination
     */	
	public ArrayList<ArrayList<Furniture>> getValidCombinations(ArrayList<ArrayList<Furniture>> f2, int n) {
		ArrayList<ArrayList<Furniture>> f3 = new ArrayList<ArrayList<Furniture>>(); //temporary ArrayList
		
		if(!f2.isEmpty())	//check if combinations exist
		{
			//iterate through all combinations
			for(int i = 0; i < f2.size(); i++)	 {
				ArrayList<char[]> ch = new ArrayList<char[]>();	//used to hold the parts of each furniture item in a combination
				for(int j = 0; j < f2.get(i).size(); j++)	//iterate through each combination's parts
				{
					ch.add(f2.get(i).get(j).getParts());	//add all parts of a furniture item used in a combination
				}
				if(isValid(n, ch))	//check if current combination is valid
				{
					f3.add(f2.get(i));	//add to an ArrayList containing all valid combinations if current combination is valid
				}
			}
		}
		return f3;
	}

	/**
     * Method to return ArrayList with combinations
     * @return ArrayList with combinations
     */
    public ArrayList <ArrayList <Furniture>> getCombinations() {
        return this.combinations;
    }

	/**
     * Method to set an ArrayList with all combinations
	 * @param fr ArrayList with all combinations
     */
	public void setCombinations(ArrayList <ArrayList <Furniture>> fr) {
        this.combinations = new ArrayList <ArrayList <Furniture>>(fr);
    }
}// end of class declaration, InventoryHandler
