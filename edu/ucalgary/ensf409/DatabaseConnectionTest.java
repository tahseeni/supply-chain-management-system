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

import java.sql.*;
import java.util.*;

public class DatabaseConnectionTest {
	static DatabaseConnection test;
	
	@BeforeClass
	public static void init() {
		test = new DatabaseConnection();
	}
	
	//Test if all mesh chair entries are returned from the database
	@Test
	public void getFurnitureTestValid1() {
		ArrayList<Furniture> f = new ArrayList<>();
		
		f.add(new Furniture("C9890", "Mesh", "CHAIR", 50, new char[] {'N', 'Y', 'N', 'Y'}));
		f.add(new Furniture("C6748", "Mesh", "CHAIR", 75, new char[] {'Y', 'N', 'N', 'N'}));
		f.add(new Furniture("C8138", "Mesh", "CHAIR", 75, new char[] {'N', 'N', 'Y', 'N'}));
		f.add(new Furniture("C0942", "Mesh", "CHAIR", 175, new char[] {'Y', 'N', 'Y', 'Y'}));
		
		assertEquals(f, test.getFurniture("CHAIR", "Mesh"));
	}
	
	//Test if all adjustable desk entries are returned
	@Test
	public void getFurnitureTestValid2() {
		ArrayList<Furniture> f = new ArrayList<>();
		char parts1[] = {'N', 'N', 'Y'};
		char parts2[] = {'Y', 'Y', 'N'};
		
		f.add(new Furniture("D3682", "Adjustable", "DESK", 50, parts1));
		f.add(new Furniture("D7373", "Adjustable", "DESK", 350, parts2));
		
		assertEquals(f, test.getFurniture("Desk", "Adjustable"));
		
	}
	
	//Test for non-existent type within category (old, chair)
	@Test
	public void getFurnitureTestInvalid1() {
		assert(test.getFurniture("chair", "old").isEmpty());
	}
	
	//Test to ensure that the  (mesh, chair)
	@Test
	public void getFurnitureTestInvalid2() {
		ArrayList<Furniture> f = new ArrayList<>();
		
		char parts1[] = {'Y', 'N', 'Y', 'Y'};
		char parts2[] = {'N', 'Y', 'N', 'Y'};
		
		f.add(new Furniture("C0942", "mesh", "CHAIR", 175, parts1));
		f.add(new Furniture("C9890", "mesh", "CHAIR", 50, parts2));
		
		
		assertNotEquals(f, test.getFurniture("chair", "mesh"));
	}
	
	//Test for manufacturers for chairs
	@Test
	public void getManufacturersTest1() {
		ArrayList<String> f = new ArrayList<>();
		
		f.add("Office Furnishings");
		f.add("Chairs R Us");
		f.add("Furniture Goods");
		f.add("Fine Office Supplies");
		
		assertEquals(f, test.getManufacturers("Chair"));
	}
	
	//Test for manufacturers for lamps
	@Test
	public void getManufacturersTest2() {
		ArrayList<String> f = new ArrayList<>();
		
		f.add("Office Furnishings");
		f.add("Furniture Goods");
		f.add("Fine Office Supplies");
		
		assertEquals(f, test.getManufacturers("Lamp"));
	}
	
	//Test for manufacturers from non-existent category
	@Test
	public void getManufacturersTest3() {
		assertTrue("Category doesn't exist", test.getManufacturers("Fridge").isEmpty());
	}
	
	
	//Remove furniture entry, and test if it is empty
	
}
