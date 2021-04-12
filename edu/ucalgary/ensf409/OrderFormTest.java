package edu.ucalgary.ensf409;
import org.junit.*;
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

public class OrderFormTest {
	ArrayList<Furniture> furnitureData = new ArrayList<Furniture>();
	
	//orderedItems
	@Test
	public void testOrderedItems() {
		furnitureData.add(new Furniture("C3000", "Mesh", 75, new char[] {'Y', 'Y', 'Y'}));
		OrderForm test = new OrderForm(new InventoryHandler(furnitureData));
		assertEquals("ID: C3000\n", test.orderedItems(furnitureData));
	}
	
	//generateOrder
	@Test
	public void testGeneratorOrder() {
		
		//arbitrary data entries
		furnitureData.add(new Furniture("C3000", "Mesh", 50, new char[] {'Y', 'N', 'N'}));
		furnitureData.add(new Furniture("C3005", "Mesh", 75, new char[] {'N', 'Y', 'Y'}));
		furnitureData.add(new Furniture("C3010", "Mesh", 100, new char[] {'Y', 'Y', 'Y'}));
		
		OrderForm test = new OrderForm(new InventoryHandler(furnitureData));
		ArrayList<ArrayList<Furniture>> combinations = new ArrayList<ArrayList<Furniture>>();
		combinations.add(furnitureData);
		
		assertEquals("Expected size should be 1", 1, test.generateOrder(1, combinations).size());
	}
	
	//calculateOrderTotal
	@Test
	public void testCalculateOrderTotal() {
		
		//arbitrary data entries 
		furnitureData.add(new Furniture("C3020", "Mesh", 50, new char[] {'Y', 'N', 'N'}));
		furnitureData.add(new Furniture("C3030", "Mesh", 100, new char[] {'N', 'Y', 'Y'}));
		
		OrderForm test = new OrderForm(new InventoryHandler(furnitureData));
		assertEquals(150, test.calculateOrderTotal(furnitureData));
	}
	
	//Test to convert the total entries into an item list
	@Test
	public void testConvertToItemsList() {
		ArrayList<ArrayList<Furniture>> f = new ArrayList<ArrayList<Furniture>>();
		ArrayList<Furniture> f2 = new ArrayList<Furniture>();
		
		furnitureData.add(new Furniture("C3000", "Mesh", 50, new char[] {'Y', 'N', 'N'}));
		furnitureData.add(new Furniture("C3005", "Mesh", 75, new char[] {'N', 'Y', 'Y'}));
		
		f2.add(new Furniture("C3010", "Mesh", 100, new char[] {'Y', 'Y', 'Y'}));
		f.add(furnitureData);
		f.add(f2);
		
		OrderForm test = new OrderForm(new InventoryHandler(furnitureData));
		assertEquals(3, test.convertToItemsList(f).size());
	}
	
	//Test the setter and getter methods for the OrderForm
	@Test
	public void testGetAndSetOrder() {
		ArrayList<ArrayList<Furniture>> f3 = new ArrayList<ArrayList<Furniture>>();
		furnitureData.add(new Furniture("C3000", "Mesh", 50, new char[] {'Y', 'N', 'N'}));
		furnitureData.add(new Furniture("C3005", "Mesh", 75, new char[] {'N', 'Y', 'Y'}));
		
		f3.add(furnitureData);
		OrderForm test = new OrderForm(new InventoryHandler(furnitureData));
		
		test.setOrder(f3);
		assertEquals(f3, test.getOrder());
	}
	
}
