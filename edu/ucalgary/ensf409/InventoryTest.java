package edu.ucalgary.ensf409;

import static org.junit.Assert.*;
import java.util.ArrayList;

/**
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

import org.junit.*;

public class InventoryTest {
	static Inventory test;
	ArrayList<Furniture> f = new ArrayList<>();
	
	//constructor that initializes all of the mesh chair entries
	public InventoryTest() {
		f.add(new Furniture("C9890", "Mesh", 50, new char[] {'N', 'Y', 'N', 'Y'}));
		f.add(new Furniture("C6748", "Mesh", 75, new char[] {'Y', 'N', 'N', 'N'}));
		f.add(new Furniture("C8138", "Mesh", 75, new char[] {'N', 'N', 'Y', 'N'}));
		f.add(new Furniture("C0942", "Mesh", 175, new char[] {'Y', 'N', 'Y', 'Y'}));
		test = new Inventory(f);
	}
	
	//Test to ensure that combinations are made, ie. not empty ArrayList
	@Test
	public void testGenerateCombinations1() {
		ArrayList<ArrayList<Furniture>> f2 = test.generateCombinations(f);
		assertFalse(f2.isEmpty());
	}
	
	//Test for passing an empty ArrayList into generateCombinations()
	//expect the return value to be empty
	@Test
	public void testGenerateCombinations2() {
		ArrayList<Furniture> f3 = new ArrayList<Furniture>();
		ArrayList<ArrayList<Furniture>> f4 = test.generateCombinations(f3);
		assertTrue(f4.isEmpty());
	}
	
	//Test for finding the cheapest combination index
	@Test
	public void testFindCheapest() {
		ArrayList<ArrayList<Furniture>> f5 = test.generateCombinations(f);
		assertEquals("Cheapest index for a mesh chair is at 0.", 0, test.findCheapest(f5));
		
	}
	
	//removeExcessIndex test
	@Test
	public void testRemoveExcessIndex() {
		ArrayList<ArrayList<Furniture>> f6 = new ArrayList<ArrayList<Furniture>>();
		
		//generate arbitrary combinations
		ArrayList<Furniture> f7 = new ArrayList<Furniture>();
		f7.add(f.get(0));
		f7.add(f.get(3));
		
		ArrayList<Furniture> f8 = new ArrayList<Furniture>();
		f8.add(f.get(0));
		f8.add(f.get(1));
		f8.add(f.get(3));
		
		f6.add(f7);
		f6.add(f8);
		
		assertEquals("Excess index is at 1.", 1, test.removeExcessIndex(f6));
	}
	
	//removeExcessCombinations
	@Test
	public void testRemoveExcessCombinations() {
		ArrayList<ArrayList<Furniture>> f9 = new ArrayList<ArrayList<Furniture>>();
		
		//generate arbitrary combinations
		ArrayList<Furniture> f10 = new ArrayList<Furniture>();
		f10.add(f.get(0));
		f10.add(f.get(3));
		
		ArrayList<Furniture> f11 = new ArrayList<Furniture>();
		f11.add(f.get(0));
		f11.add(f.get(1));
		f11.add(f.get(3));
		
		f9.add(f10);
		f9.add(f11);
		
		assertEquals("Size of ArrayList after removing excess combinations is 1.", 1, test.removeInvalidCombinations(f9).size());
	}
	
	//combinationUsedIndex
	@Test
	public void testCombinationUsedIndex() {
		ArrayList<ArrayList<Furniture>> order = new ArrayList<ArrayList<Furniture>>();
		ArrayList<Furniture> f12 = new ArrayList<Furniture>();
		
		f12.add(f.get(0));
		f12.add(f.get(3));
		order.add(f12);
		
		assertEquals("Used combination index is at index 0.", 0, test.combinationUsedIndex(test.getCombinations(), order));
	}
	
	//removeUsedCombinations
	@Test
	public void testRemoveUsedCombinations() {
		ArrayList<ArrayList<Furniture>> order = new ArrayList<ArrayList<Furniture>>();
		ArrayList<Furniture> f13 = new ArrayList<Furniture>();
		
		f13.add(f.get(0));
		f13.add(f.get(3));
		order.add(f13);
		
		assertEquals("The size of the ArrayList after removing used combinations is 0.", 0, test.removeUsedCombinations(test.getCombinations(), order).size());
	}
	
	//test for getting the combinations
	@Test
	public void testGetCombinations() {
		//we expect this to not be empty 
		assertFalse(test.getCombinations().isEmpty());
	}
}
