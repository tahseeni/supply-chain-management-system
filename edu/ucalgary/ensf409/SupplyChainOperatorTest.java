package edu.ucalgary.ensf409;

import static org.junit.Assert.*;
import java.util.ArrayList;
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

public class SupplyChainOperatorTest {
	SupplyChainOperator test = new SupplyChainOperator();
	
	//isValidItem -> input of 1 should pass 1
	@Test
	public void testItemValidity1() {
		assertEquals("1", test.isValidItem("1"));
	}
	
	//isValidItem -> input of -5 should return null
	@Test
	public void testItemValidity2() {
		assertEquals(null, test.isValidItem("-5"));
	}
	
	//isValidItem -> word input should return null
	@Test
	public void testItemValidity3() {
		assertEquals(null, test.isValidItem("random"));
	}
	
	//setFurnitureType -> all lower case
	@Test
	public void testSetFurnitureType1() {
		assertEquals("Traditional", test.setFurnitureType("Desk", "traditional"));
	}
	
	//setFurnitureType -> all upper case
	@Test
	public void testSetFurnitureType2() {
		assertEquals("Study", test.setFurnitureType("Lamp", "STUDY"));
	}
	
	//setFurnitureType -> all lower case
	@Test
	public void testSetFurnitureType3() {
		assertEquals("Large", test.setFurnitureType("Filing", "lArGe"));
	}
	
	//setFurnitureQuantity -> valid input
	@Test
	public void testSetFurnitureQuantity() {
		assertEquals(2, test.setFurnitureQuantity(2));
	}
	
	//setFurnitureQuantity -> invalid input
	@Test
	public void testSetFurnitureQuantity2() {
		assertEquals(0, test.setFurnitureQuantity(-100));
	}
	
}
