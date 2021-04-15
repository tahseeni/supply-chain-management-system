package edu.ucalgary.ensf409;

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

import org.junit.*;
import static org.junit.Assert.*;

/**
 * Class to test the methods within the Furniture Class
 */
public class FurnitureTest {
	
	//Test to compare the ID of the Furniture object
	@Test
	public void getIDTest() {
		Furniture f = new Furniture("C3001", "mesh", 75, new char[] {'Y', 'N', 'Y'});
		assertEquals("Test should pass if the IDs are equal.", "C3001", f.getID());
	}
	
	//Test to compare the existent type of Furniture object
	@Test
	public void getTypeTest() {
		Furniture f2 = new Furniture("C3002", "mesh", 75, new char[] {'Y', 'N', 'Y'});
		assertEquals("Test should pass if the types are equal.", "mesh", f2.getType());
	}
	
	//Test to compare the non-existent type of Furniture object
	@Test
	public void getTypeTest2() {
		Furniture f3 = new Furniture("C3003", "mesh", 75, new char[] {'Y', 'N', 'Y'});
		assertNotEquals("Test should pass because the type is non-existent.", "fridge", f3.getType());
	}
	
	//Test to compare the price of the Furniture object
	@Test
	public void getPriceTest() {
		Furniture f4 = new Furniture("C3004", "mesh", 75, new char[] {'Y', 'N', 'Y'});
		assertEquals("Test should pass if the prices are equal.", 75, f4.getPrice());
	}
	
	//Test to compare the parts of the Furniture object
	@Test
	public void getPartsTest() {
		char parts[] = new char[] {'Y', 'N', 'Y'};
		Furniture f = new Furniture("C3005", "mesh", 75, new char[] {'Y', 'N', 'Y'});
		assertArrayEquals("Test should pass since the parts arrays match.", parts, f.getParts());
	}
	
	//Test for invalid parts comparison
	@Test
	public void getPartsTest2() {
		char parts[] = new char[] {'N', 'N', 'Y'};
		Furniture f = new Furniture("C3006", "mesh", 75, new char[] {'Y', 'N', 'Y'});
		assertNotEquals("Test should pass since the parts arrays do not match.", parts, f.getParts());
	}
	
}
