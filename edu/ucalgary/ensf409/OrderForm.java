package edu.ucalgary.ensf409;

import java.util.ArrayList;
import java.io.FileWriter;

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

/**
 * OrderForm class handles order data and creates form/receipt
 */
public class OrderForm {

	private Inventory inventory;
    private ArrayList <ArrayList<Furniture>> orderCombinations;
    private ArrayList <Furniture> order;
    
    /**
    * OrderForm constructor to intilialize InventoryHandler object and create order object
    */
    public OrderForm(Inventory inv)
    {
        this.setInventoryHandler(inv);
        this.orderCombinations = new ArrayList <ArrayList<Furniture>>();
        this.order = new ArrayList <Furniture>();
    }

    /**
     * Method to return orderCombinations
     * 
     * @return orderCombinations
     */
    public ArrayList <ArrayList <Furniture>> getOrderCombinations()
	{
		return this.orderCombinations;
	}

    /**
     * Method to return a valid order
     * 
     * @return order
     */
    public ArrayList <Furniture> getOrder()
	{
		return this.order;
	}

    /**
     * Method to return InventoryHandler object
     * 
     * @return InventoryHandler object
     */
    public Inventory getInventory(){
        return this.inventory;
    }

    /**
     * Method to set orderCombinations equal an ArrayList 
     * containing all valid combinations of furniture items
     * 
     * @param orderCombos - ArrayList containing valid combinations of furniture items (contain all required parts)
     */
	public void setOrderCombinations(ArrayList <ArrayList <Furniture>> orderCombos) {
        this.orderCombinations = new ArrayList <ArrayList<Furniture>>(orderCombos);
	}

    /**
     * Method to set order equal to an ArrayList containing all 
     * furniture items from the cheapest combination
     * 
     * @param index       -  index of cheapest combination of furniture items
     * @param orderCombos -  ArrayList containing all valid combinations of furniture items (contain all required parts)
     */
    public void setOrder(int index, ArrayList <ArrayList <Furniture>> orderCombos) {
    	if(!orderCombos.isEmpty()) {
    		this.order = new ArrayList <Furniture>(orderCombos.get(index));
    	}
    	else {
    		this.order = new ArrayList <Furniture>();
    	}
    }

    /**
     * Method to set InventoryHandler object equal to input InventoryHandler object
     * @param inv - InventoryHandler object containing combinations of furniture read from database file
     */
    public void setInventoryHandler(Inventory inv) {
        this.inventory = new Inventory(inv);
    }

    /**
     * Method to return a String containing all furniture items that were ordered
     * 
     * @param - itemsList ArrayList containing all ordered furniture items
     * @return String containing all furniture items that were ordered
     */
    public String orderedItems(ArrayList<Furniture> itemsList) {
        String line = "";
        for (int i = 0; i < itemsList.size(); i++) {
            line += ("ID: " + itemsList.get(i).getID() + "\n");
        }
        return line;
    }
    
    /**
     * Method to create an order form and generate a receipt for a valid order.
     * Prints messages to the console for the user, confirming that the
     * order form has been generated, or that it is unable to fulfill the order.
     * The method will print the suggested manufacturers if the order is invalid.
     * No return value.
     * 
     * @param db	   - Database containing the inventory
     * @param userQty  - Quantity of item(s) ordered
     * @param userType - Type of item ordered
     * @param userItem - Category of item ordered
     */
    public void createOrderForm(SQLConnector db, int userQty, String userType, String userItem) {
        System.out.println("\nUser has selected: " + userQty + " " + userType + " " + userItem);
        this.setOrderCombinations(this.inventory.getValidCombinations(this.getInventory().getCombinations(), userQty));
        
        if (!this.getOrderCombinations().isEmpty()) {
            System.out.println("\n\nOrder is valid. It will now be processed.");
            this.setOrder(this.inventory.findCheapest(this.getOrderCombinations()), this.getOrderCombinations());
            
            System.out.println("Generating order information...");
            this.generateReceipt(userItem, userType, userQty);
            System.out.println("The order receipt has been generated.");
            
            //uncomment this later
            db.removeFurniture(this.getOrder(), userItem);
            
            System.out.println("\n\nThank you for using our service!");
            
        } else {
            System.out.println("The order cannot be fulfilled based on the current inventory.");
    		this.printSuggestedManufacturers(db, userItem);
    		System.out.println("\nPlease check again when the inventory has been restocked.");
    		System.out.println("\n\nThank you for using our service.");
        }
    }

    /**
     * Method to generate order receipt, using FileWriter objects.
     * Called by createOrderForm(), no return value.
     * 
     * @param item - Category of item ordered
     * @param type - Type of item ordered
     * @param quantity - Quantity of item(s) ordered
     */
    public void generateReceipt(String item, String type, int quantity) {
    	try {
    		FileWriter writeReceipt = new FileWriter("orderform.txt");
    		
    		String message = "Furniture Order Form\n\nFaculty Name:\nContact:\n";
    		message += "Date:\n\nOriginal Request: " + type + " " + item + ", " + quantity + "\n\n";
    		message += "Items Ordered\n";
    		message += this.orderedItems(this.getOrder());
    		message += "\nTotal Price: $" + this.calculateOrderTotal(this.getOrder());
    		
            writeReceipt.write(message);
            writeReceipt.close();
    	}
    	catch(Exception e) {
    		System.out.println("An error occurred when generating the receipt. Please run the program again.");
    	}
    }
    
    /**
     * Helper method used to print the suggested manufacturers from the database.
     * Called by createOrderForm(), no return value.
     * 
     * @param db - DatabaseConnector object passed 
     * @param item - Category of item ordered
     */
    public void printSuggestedManufacturers(SQLConnector db, String item) {
    	ArrayList<String> manufacturers = new ArrayList<String>();
    	manufacturers = db.getManufacturers(item);
    	
    	//print message 
    	String message = "\nSuggested manufacturers are ";
    	for(int i = 0; i < manufacturers.size(); i++) {
    		message += manufacturers.get(i);
    		
    		if(i == manufacturers.size() - 2) {
    			message += " and ";
    		}
    		else if(i != manufacturers.size() - 1) {
    			message += ", ";
    		}
    	}
    	System.out.println(message + ".");
    }

    /**
     * Method to calculate total price of an order
     * 
     * @param itemList - ArrayList containing all ordered items
     * @return total price of an order
     */
    public int calculateOrderTotal(ArrayList<Furniture> itemsList) {
        int total = 0;
        for (int i = 0; i < itemsList.size(); i++) {
            total += itemsList.get(i).getPrice();
        }
        return total;
    }

} //end of class declaration, OrderForm
