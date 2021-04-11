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

public class FurnitureTest {
	
	
	public FurnitureTest() {
	}
	
	@Test
	public void getIDTest() {
		Furniture f = new Furniture("300", "mesh", "chair", 75, new char[] {'Y', 'N', 'Y'});
		assertEquals("300", f.getID());
	}
	
	@Test
	public void getTypeTest() {
		Furniture f = new Furniture("300", "mesh", "chair", 75, new char[] {'Y', 'N', 'Y'});
		assertEquals("mesh", f.getType());
	}
	
	@Test
	public void getTypeTest2() {
		Furniture f = new Furniture("300", "mesh", "chair", 75, new char[] {'Y', 'N', 'Y'});
		assertNotEquals("fridge", f.getType());
	}
	
	@Test
	public void getPriceTest() {
		Furniture f = new Furniture("300", "mesh", "chair", 75, new char[] {'Y', 'N', 'Y'});
		assertEquals(75, f.getPrice());
	}
	
	@Test
	public void getPartsTest() {
		char parts[] = new char[] {'Y', 'N', 'Y'};
		Furniture f = new Furniture("300", "mesh", "chair", 75, new char[] {'Y', 'N', 'Y'});
		assertEquals(parts, f.getParts());
	}
	
	@Test
	public void getPartsTest2() {
		char parts[] = new char[] {'Y', 'N', 'Y', 'N'};
		Furniture f = new Furniture("300", "mesh", "chair", 75, new char[] {'Y', 'N', 'Y'});
		assertNotEquals(parts, f.getParts());
	}
	
}
