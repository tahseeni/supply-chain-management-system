package edu.ucalgary.ensf409;

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
import static org.junit.Assert.*;
import java.util.*;

public class SQLConnectorTest {
	static SQLConnector test;
	
	@BeforeClass
	public static void init() {
		Scanner databasePrompter = new Scanner(System.in);
		test = new SQLConnector(databasePrompter);
	}
	
	//Test if all mesh chair entries are returned from the database, based on ID
	@Test
	public void testGetValidFurniture1() {
		ArrayList<Furniture> f = new ArrayList<>();
		
		f.add(new Furniture("C0942", "Mesh", 175, new char[] {'Y', 'N', 'Y', 'Y'}));
		f.add(new Furniture("C6748", "Mesh", 75, new char[] {'Y', 'N', 'N', 'N'}));
		f.add(new Furniture("C8138", "Mesh", 75, new char[] {'N', 'N', 'Y', 'N'}));
		f.add(new Furniture("C9890", "Mesh", 50, new char[] {'N', 'Y', 'N', 'Y'}));
		
		assertEquals(f.get(0).getID(), test.getFurniture("CHAIR", "Mesh").get(0).getID());
	}
	
	//Test if all adjustable desk entries are returned, based on the parts array
	@Test
	public void testGetValidFurniture2() {
		ArrayList<Furniture> f = new ArrayList<>();
		char parts1[] = {'N', 'Y', 'N'};
		char parts2[] = {'Y', 'N', 'Y'};
		
		f.add(new Furniture("D1030", "Adjustable", 150, parts1));
		f.add(new Furniture("D2746", "Adjustable", 250, parts2));
		
		assertArrayEquals(f.get(0).getParts(), test.getFurniture("DESK", "Adjustable").get(0).getParts());
	}
	
	//Test for non-existent type within category (old, chair)
	@Test
	public void testInvalidGetFurniture1() {
		assert(test.getFurniture("chair", "old").isEmpty());
	}
	
	//Test to ensure that an arbitrary furniture ArrayList is not
	//equal to the ArrayList returned by the getFurniture() method
	@Test
	public void testInvalidGetFurniture2() {
		ArrayList<Furniture> f = new ArrayList<>();
		
		char parts1[] = {'Y', 'N', 'Y', 'Y'};
		char parts2[] = {'N', 'Y', 'N', 'Y'};
		
		f.add(new Furniture("C0942", "mesh", 175, parts1));
		f.add(new Furniture("C9890", "mesh", 50, parts2));
		
		assertArrayEquals(f.get(0).getParts(), test.getFurniture("chair", "mesh").get(0).getParts());
	}
	
	//Test for manufacturers for chairs
	@Test
	public void testGetManufacturers1() {
		ArrayList<String> f = new ArrayList<>();
		
		f.add("Office Furnishings");
		f.add("Chairs R Us");
		f.add("Furniture Goods");
		f.add("Fine Office Supplies");
		
		assertEquals(f, test.getManufacturers("Chair"));
	}
	
	//Test for manufacturers for lamps
	@Test
	public void testGetManufacturers2() {
		ArrayList<String> f = new ArrayList<>();
		
		f.add("Office Furnishings");
		f.add("Furniture Goods");
		f.add("Fine Office Supplies");
		
		assertEquals(f, test.getManufacturers("Lamp"));
	}
	
	//Test for manufacturers from non-existent category
	@Test
	public void testGetManufacturers3() {
		assertTrue("This category doesn't exist.", test.getManufacturers("Fridge").isEmpty());
	}
	
	//Remove furniture entry, and test for different size of the arrays
	@Test
	public void testRemoveFurniture() {
		ArrayList<Furniture> f = new ArrayList<>();
		
		f.add(new Furniture("C9890", "Mesh", 50, new char[] {'N', 'Y', 'N', 'Y'}));
		test.removeFurniture(f, "Chair");
		
		ArrayList<Furniture> g = test.getFurniture("Chair", "Mesh");
		ArrayList<Furniture> h = new ArrayList<Furniture>();
		
		h.add(new Furniture("C0942", "Mesh", 175, new char[] {'Y', 'N', 'Y', 'Y'}));
		h.add(new Furniture("C6748", "Mesh", 75, new char[] {'Y', 'N', 'N', 'N'}));
		h.add(new Furniture("C8138", "Mesh", 75, new char[] {'N', 'N', 'Y', 'N'}));
		h.add(new Furniture("C9890", "Mesh", 50, new char[] {'N', 'Y', 'N', 'Y'}));
		
		//g should be 3, h should be 4
		assertNotEquals(g.size(), h.size());
	}
	
}
