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

public class InventoryHandlerTest {
	static InventoryHandler test;
	ArrayList<Furniture> f = new ArrayList<>();
	
	//constructor that initializes all of the mesh chair entries
	public InventoryHandlerTest() {
		f.add(new Furniture("C9890", "Mesh", 50, new char[] {'N', 'Y', 'N', 'Y'}));
		f.add(new Furniture("C6748", "Mesh", 75, new char[] {'Y', 'N', 'N', 'N'}));
		f.add(new Furniture("C8138", "Mesh", 75, new char[] {'N', 'N', 'Y', 'N'}));
		f.add(new Furniture("C0942", "Mesh", 175, new char[] {'Y', 'N', 'Y', 'Y'}));
		test = new InventoryHandler(f);
	}
	
	//Test to ensure that combinations are made, ie. not empty ArrayList
	@Test
	public void testGenerateCombinations1() {
		ArrayList<ArrayList<Furniture>> f2 = test.generateCombinations(f);
		assertFalse(f2.isEmpty());
	}
	
	//Test for passing an empty ArrayList into generateCombinations()
	//expect the return value to be empty
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
	
	//removeExcessCheck
	@Test
	public void testRemoveExcessCheck() {
		ArrayList<ArrayList<Furniture>> f6 = test.generateCombinations(f);
	}
	
	//removeExcessIndex
	@Test
	public void testRemoveExcessIndex() {
		
	}
	
	//removeExcessCombinations
	@Test
	public void removeExcessCombinations() {
		
	}
	
	//combinationUsedCheck
	@Test
	public void combinationUsedCheck() {
		
	}
	
	//combinationUsedIndex
	@Test
	public void combinationUsedIndex() {
		
	}
	
	
	//removeUsedCombinations

}
