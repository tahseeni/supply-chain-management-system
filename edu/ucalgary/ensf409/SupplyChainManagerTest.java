package edu.ucalgary.ensf409;

import static org.junit.Assert.*;
import org.junit.*;

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
 * @version 1.1
 * @since 1.0
 */

public class SupplyChainManagerTest {
	static SupplyChainManager test;
	
	//ask for database credentials only once, before tests are ran
	@BeforeClass
	public static void setup() {
		test = new SupplyChainManager();
	}
	
	//input of 1 should pass 1, as it is valid
	@Test
	public void testItemValidity1() {
		assertEquals("1", test.isValidItem("1"));
	}
	
	//input of -5 should return null, as it is invalid
	@Test
	public void testItemValidity2() {
		assertEquals(null, test.isValidItem("-5"));
	}
	
	//word input should return null if unexpected furniture choice
	@Test
	public void testItemValidity3() {
		assertEquals(null, test.isValidItem("random"));
	}
	
	//all lower case when choosing the type
	@Test
	public void testSetFurnitureType1() {
		assertEquals("Traditional", test.setFurnitureType("Desk", "traditional"));
	}
	
	//all upper case when choosing the type
	@Test
	public void testSetFurnitureType2() {
		assertEquals("Study", test.setFurnitureType("Lamp", "STUDY"));
	}
	
	//mixed case when choosing the type
	@Test
	public void testSetFurnitureType3() {
		assertEquals("Large", test.setFurnitureType("Filing", "lArGe"));
	}
	
	//any quantity above 0 is valid
	@Test
	public void testSetFurnitureQuantity() {
		assertEquals(2, test.setFurnitureQuantity(2));
	}
	
	//negative quantity input is invalid
	@Test
	public void testSetFurnitureQuantity2() {
		assertEquals(0, test.setFurnitureQuantity(-100));
	}
	
}
