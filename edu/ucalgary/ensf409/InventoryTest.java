package edu.ucalgary.ensf409;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.*;

/**GROUP 32
 * @author Tahseen Intesar <a href="mailto:tahseen.intesar@ucalgary.ca">
 *         tahseen.intesar@ucalgary.ca</a>
 *         
 * @author Gurpartap Sohi <a href="mailto:gurpartap.sohi@ucalgary.ca">
 *         gurpartap.sohi@ucalgary.ca</a>
 *         
 * @author Nabeel Amjad<a href="mailto:nabeel.amjad@ucalgary.ca">
 *         nabeel.amjad@ucalgary.ca</a>  
 *         
 * @author Stalin D Cunha<a href="mailto:stalin.dcunha@ucalgary.ca">
 *         stalin.dcunha@ucalgary.ca</a>
 *         
 * @version 1.0
 * @since 1.0
 */

/**
 * Class to test the methods within the Inventory Class
 */
public class InventoryTest {
	static Inventory test;
	ArrayList<Furniture> f = new ArrayList<>();
	
	//constructor that initializes all of the mesh chair entries
	public InventoryTest() {
		f.add(new Furniture("C0942", "Mesh", 175, new char[] {'Y', 'N', 'Y', 'Y'}));
		f.add(new Furniture("C6748", "Mesh", 75, new char[] {'Y', 'N', 'N', 'N'}));
		f.add(new Furniture("C8138", "Mesh", 75, new char[] {'N', 'N', 'Y', 'N'}));
		f.add(new Furniture("C9890", "Mesh", 50, new char[] {'N', 'Y', 'N', 'Y'}));
		test = new Inventory(f);
	}
	
	//Test to ensure that combinations are made, ie. not empty ArrayList
	@Test
	public void testGenerateCombinations1() {
		ArrayList<ArrayList<Furniture>> f2 = test.generateCombinations(f);
		assertFalse(f2.isEmpty());
	}
	
	/*
	 * Test for passing an empty ArrayList into generateCombinations()
	 * expect the returned array to be empty
	 */
	@Test
	public void testGenerateCombinations2() {
		ArrayList<Furniture> f3 = new ArrayList<Furniture>();
		ArrayList<ArrayList<Furniture>> f4 = test.generateCombinations(f3);
		assertTrue(f4.isEmpty());
	}
	
	//Test for finding the cheapest item index in the furniture list
	@Test
	public void testFindCheapest() {
		ArrayList<ArrayList<Furniture>> f5 = new ArrayList<ArrayList<Furniture>>();
		ArrayList<Furniture> f6 = new ArrayList<Furniture>();
			
		//valid combination one
		f6.add(f.get(0));
		f6.add(f.get(3));
		f5.add(f6);
		
		//valid combination two
		f6 = new ArrayList<Furniture>();
		f6.add(f.get(1));
		f6.add(f.get(2));
		f6.add(f.get(3));
		f5.add(f6);
		
		assertEquals("Cheapest index for a mesh chair is at 1.", 1, test.findCheapest(f5));
	}
	
	//check for ordering 1 mesh chair, should return true
	@Test
	public void testIsValid1() {
		ArrayList<char[]> parts = new ArrayList<char[]>();
		
		for(int i = 0; i < f.size(); i++) {
			parts.add(f.get(i).getParts());
		}
		
		assertTrue(test.isValid(1, parts));
	}
	
	//check for ordering 2 mesh chairs, should return false
	@Test
	public void testIsValid2() {
		ArrayList<char[]> parts = new ArrayList<char[]>();
		
		for(int i = 0; i < f.size(); i++) {
			parts.add(f.get(i).getParts());
		}
		
		assertFalse(test.isValid(2, parts));
	}
	
	//check for an invalid combination (2 mesh chairs)
	@Test
	public void testGetValidCombinations1() {
		//generate all combinations possible
		ArrayList<ArrayList<Furniture>> f7 = new ArrayList<ArrayList<Furniture>>(test.generateCombinations(f));
		
		//generate all valid combinations, if any
		ArrayList<ArrayList<Furniture>> f8 = test.getValidCombinations(f7, 2);
		
		//no valid combinations for 2 mesh chairs
		assertTrue(f8.isEmpty());
	}
	
	//check for a valid combination (1 mesh chair)
	@Test
	public void testGetValidCombinations2() {
		//generate all combinations possible
		ArrayList<ArrayList<Furniture>> f9 = new ArrayList<ArrayList<Furniture>>(test.generateCombinations(f));
		
		//generate all valid combinations, if any
		ArrayList<ArrayList<Furniture>> f10 = test.getValidCombinations(f9, 1);
		
		//there exists at least 1 valid combination for 1 mesh chair
		assertFalse(f10.isEmpty());
	}
	
	/**
	 * Test to check if combinations of integers of size k.
	 *	Requires that size of each combination is less than or equal to number
	 *	of elements in the input ArrayList
	 */
	@Test
	public void testCombineInts() {
		List <Integer> ints = new ArrayList<Integer>(); //input ArrayList indices
		List <List<Integer>> it = new ArrayList<>(); //holds all combinations
		
		int n = 4; //number of indices in the ArrayList (any value)
		int k = 3; //size of the combinations, requires that k <= n
		
		for(int i = 0; i < n; i++) {
			ints.add(i);
		}
		
		int temp[] = new int[k]; //hold combinations of size k;
		
		//find combinations of the indices of furniture objects stored in ArrayList f
		test.combineInts(ints, temp, 0, ints.size() - 1, 0, k, it);
		
		assertEquals("Size of each combination should be " + k, k, it.get(0).size());
	}
	
	//Test for getting the combinations
	@Test
	public void testGetCombinations() {
		//The returned ArrayList will not be empty for this given ArrayList f
		assertFalse(test.getCombinations().isEmpty());
	}
}
