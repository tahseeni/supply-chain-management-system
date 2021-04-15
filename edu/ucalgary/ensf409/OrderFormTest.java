package edu.ucalgary.ensf409;
import org.junit.*;
import static org.junit.Assert.*;

import java.util.ArrayList;

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
 * @version 1.0
 * @since 1.0
 */

/**
 * Class to test the methods within the OrderForm Class
 */
public class OrderFormTest {
	ArrayList<Furniture> furnitureData = new ArrayList<Furniture>();
	
	//test if the orderedItems returns the correct string
	@Test
	public void testOrderedItems() {
		furnitureData.add(new Furniture("C3000", "Mesh", 75, new char[] {'Y', 'Y', 'Y'}));
		OrderForm test = new OrderForm(new Inventory(furnitureData));
		assertEquals("The printed IDs should match.\n",
				"ID: C3000\n", test.orderedItems(furnitureData));
	}
	
	//test for getting the price of an order
	@Test
	public void testCalculateOrderTotal() {
		//arbitrary data entries 
		furnitureData.add(new Furniture("C3020", "Mesh", 50, new char[] {'Y', 'N', 'N'}));
		furnitureData.add(new Furniture("C3030", "Mesh", 100, new char[] {'N', 'Y', 'Y'}));
		
		OrderForm test = new OrderForm(new Inventory(furnitureData));
		assertEquals("Prices should match.", 150, test.calculateOrderTotal(furnitureData));
	}
	
	//Test the setter and getter methods for the OrderForm
	@Test
	public void testGetAndSetOrderCombinations() {
		ArrayList<ArrayList<Furniture>> f3 = new ArrayList<ArrayList<Furniture>>();
		furnitureData.add(new Furniture("C3000", "Mesh", 50, new char[] {'Y', 'N', 'N'}));
		furnitureData.add(new Furniture("C3005", "Mesh", 75, new char[] {'N', 'Y', 'Y'}));
		
		f3.add(furnitureData);
		OrderForm test = new OrderForm(new Inventory(furnitureData));
		
		test.setOrderCombinations(f3);
		assertEquals("Test should pass because both parameters are equal.",
				f3, test.getOrderCombinations());
	}
	
	//Testing the setter and getter methods for the OrderForm
	@Test
	public void testGetAndSetOrder1() {
		//the char arrays are different from the test before
		furnitureData.add(new Furniture("C3000", "Mesh", 50, new char[] {'Y', 'N', 'N'}));
		furnitureData.add(new Furniture("C3005", "Mesh", 75, new char[] {'N', 'Y', 'Y'}));
		
		OrderForm test = new OrderForm(new Inventory(furnitureData));
		ArrayList<ArrayList<Furniture>> f3 = new ArrayList<ArrayList<Furniture>>(test.getInventory().generateCombinations(furnitureData));
		
		test.setOrder(test.getInventory().findCheapest(f3), test.getInventory().getValidCombinations(f3, 1));
		assertFalse("Test should pass because the resulting ArrayList is empty.", test.getOrder().isEmpty());
	}
	
	//Testing the setter and getter methods for the Order
	@Test
	public void testGetAndSetOrder2() {
		//the char arrays are different from the test before
		furnitureData.add(new Furniture("C3000", "Mesh", 50, new char[] {'Y', 'N', 'N'}));
		furnitureData.add(new Furniture("C3050", "Mesh", 75, new char[] {'N', 'N', 'Y'}));
		
		OrderForm test = new OrderForm(new Inventory(furnitureData));
		ArrayList<ArrayList<Furniture>> f3 = new ArrayList<ArrayList<Furniture>>(test.getInventory().generateCombinations(furnitureData));
		
		test.setOrder(test.getInventory().findCheapest(f3), test.getInventory().getValidCombinations(f3, 1));
		assertTrue(test.getOrder().isEmpty());
	}
	
}
