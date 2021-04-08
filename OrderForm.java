package edu.ucalgary.ensf409;

import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;

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
 * @version 1.0
 * @since 1.0
 */

//furniture receipt
public class OrderForm {
	private InventoryHandler inventory;
	private ArrayList<ArrayList<Furniture>> order;

	public OrderForm(InventoryHandler inv) {
		this.inventory = new InventoryHandler(inv);
		this.order = new ArrayList<ArrayList<Furniture>>();
	}

	public ArrayList<ArrayList<Furniture>> getOrder() {
		return this.order;
	}

	public InventoryHandler getInventory() {
		return this.inventory;
	}

	public void setOrder(ArrayList<ArrayList<Furniture>> fr_Order) {
		this.order = fr_Order;
	}

	public void displayOrderCombinations() {
		for (int i = 0; i < this.getOrder().size(); i++) {
			System.out.print("Item Combination " + (i + 1) + ": ");
			for (int j = 0; j < this.getOrder().get(i).size(); j++) {
				System.out.print(this.getOrder().get(i).get(j).getID() + " ");
				System.out.print("$" + this.getOrder().get(i).get(j).getPrice() + " ");
			}
			System.out.println();
		}
	}

	public String displayOrderedItems(ArrayList<Furniture> itemsList) {
		// System.out.print("Items Ordered: ");
		String line = "";
		for (int i = 0; i < itemsList.size(); i++) {
			line += ("ID: " + itemsList.get(i).getID() + "\n");
		}
		return line;
		// System.out.println("\nOrder total: $" + this.calculateOrderTotal(itemsList));
	}

	public ArrayList<ArrayList<Furniture>> generateOrder(int n, ArrayList<ArrayList<Furniture>> f) {
		ArrayList<ArrayList<Furniture>> f2 = new ArrayList<ArrayList<Furniture>>(f);
		ArrayList<ArrayList<Furniture>> f3 = new ArrayList<ArrayList<Furniture>>();
		int i;

		for (i = 0; i < n; i++) {
			if (!f2.isEmpty()) {
				int index = this.inventory.findCheapest(f2);
				f3.add(f2.get(index));
				f2 = this.inventory.removeUsedCombinations(f2, f3);
			} else {
				break;
			}
		}
		return f3;
	}

	public void createOrderForm(int userQty, String userType, String userItem) {
		System.out.println("User has selected: " + userQty + " " + userType + " " + userItem);
		this.setOrder(generateOrder(userQty, this.getInventory().getCombinations()));
		if (this.getOrder().size() == userQty) {
			System.out.println("Order is valid. It will now be processed.");
			System.out.println("Generating order information...");
			System.out.println("Order information:");
			this.displayOrderCombinations();
			// this.displayOrderedItems(this.convertToItemsList(this.getOrder()));
		} else {
			System.out.println("Invalid Order. It will not be processed.");
		}
	}

	public void generateReceipt(String item, String type, int quantity) throws IOException {
		FileWriter write = new FileWriter("orderform.txt");
		
		write.write("Furniture Order Form\n\n");
		write.write("Faculty Name:\n");
		write.write("Contact:\n");
		write.write("Date:\n\n");
		write.write("Original Request: " + type + " " + item + ", " + quantity + "\n\n");
		write.write("Items Ordered\n");
		write.write(this.displayOrderedItems(this.convertToItemsList(this.getOrder())));
		write.write("\nTotal Price: $" + this.calculateOrderTotal(this.convertToItemsList(this.getOrder())));
		write.close();
	}

	public int calculateOrderTotal(ArrayList<Furniture> itemsList) {
		int total = 0;
		for (int i = 0; i < itemsList.size(); i++) {
			total += itemsList.get(i).getPrice();
		}
		return total;
	}

	public ArrayList<Furniture> convertToItemsList(ArrayList<ArrayList<Furniture>> itemCombinations) {
		ArrayList<Furniture> items = new ArrayList<Furniture>();
		for (int i = 0; i < itemCombinations.size(); i++) {
			for (int j = 0; j < itemCombinations.get(i).size(); j++) {
				items.add(itemCombinations.get(i).get(j));
			}
		}
		return items;
	}
}
