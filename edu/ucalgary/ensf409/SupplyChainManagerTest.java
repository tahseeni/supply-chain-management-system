package edu.ucalgary.ensf409;

import static org.junit.Assert.*;
import org.junit.*;

/** GROUP 32
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

/**
 * Class to test the methods within the SupplyChainManager Class
 */
public class SupplyChainManagerTest {
	static SupplyChainManager test;
	
	//ask for database credentials only once, before tests are ran
	@BeforeClass
	public static void setup() {
		test = new SupplyChainManager();
	}
	
	//test for valid input for furniture choice (expecting a digit 0-4)
	@Test
	public void testItemValidity1() {
		String k = "4";
		assertEquals("Test will pass if k is " + k, k, test.isValidItem(k));
	}
	
	//test for negative input for furniture choice (expecting a digit 0-4)
	@Test
	public void testItemValidity2() {
		assertEquals("Negative input choice will return null.", null, test.isValidItem("-5"));
	}
	
	//test for word input for furniture choice (expecting a digit 0-4)
	@Test
	public void testItemValidity3() {
		assertEquals("A word input choice will return null.", null, test.isValidItem("randomTextHere"));
	}
	
	//test for all lower case when choosing the type
	@Test
	public void testSetFurnitureType1() {
		assertEquals("Lowercase input will return the same string with corrected "
				+ "format.", "Traditional", test.setFurnitureType("Desk", "traditional"));
	}
	
	//test for all upper case input when choosing the type
	@Test
	public void testSetFurnitureType2() {
		assertEquals("Uppercase input will return the same string with corrected "
				+ "format.", "Study", test.setFurnitureType("Lamp", "STUDY"));
	}
	
	//test for mixed case input when choosing the type
	@Test
	public void testSetFurnitureType3() {
		assertEquals("Mixed case input will return the same string with corrected "
				+ "format.", "Large", test.setFurnitureType("Filing", "lArGe"));
	}
	
	//test for any furniture quantity above 0
	@Test
	public void testSetFurnitureQuantity() {
		int k = 4; //constraint : k > 0
		assertEquals("Positive input will result in that "
				+ "same input being returned.", k, test.setFurnitureQuantity(k));
	}
	
	//test for negative furniture quantity input
	@Test
	public void testSetFurnitureQuantity2() {
		assertEquals("Negative input will result in "
				+ "a 0 being returned.", 0, test.setFurnitureQuantity(-100));
	}
	
}
