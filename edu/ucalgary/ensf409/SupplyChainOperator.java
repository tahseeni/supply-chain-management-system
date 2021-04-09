package edu.ucalgary.ensf409;
import java.util.Scanner;
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
 * @author Stalin D Cunha<a href="mailto:stalin.dcunha@ucalgary.ca">
 *         stalin.dcunha@ucalgary.ca</a>
 *         
 * @version 1.1
 * @since 1.0
 */

/**
 * SupplyChainOperator is the main class, which handles program execution
 * and database connectivity. The class will process the user inputs.
 */
public class SupplyChainOperator {
	private String userItem = "";
	private String userType = "";
	private int userQty;
	
	private DatabaseConnection driver;
	private InventoryHandler inventory;
	private OrderForm receipt;

	/**
	 * Constructor for SupplyChainOperator. Aims to connect to mySQL server.
	 */
	public SupplyChainOperator() {
		this.driver = new DatabaseConnection();
	}
	
	/**
	 * promptUser() will guide the user through a series of menus to determine
	 * the furniture item, type and quantity that they wish to purchase.
	 * Makes use of the Scanner object for user inputs.
	 * No arguments taken, no return value.
	 */
	public void promptUser() {

		Scanner input = new Scanner(System.in);

		try {
			//check for furniture item
			do {
				System.out.println(
						"\nWhat furniture item are you looking for? \n1. Chair\n2. Desk\n3. Lamp\n4. Filing Cabinet"
								+ "\n0. Quit Program");
				System.out.print("\nPlease enter a number based on what is listed below.\n>> ");

				userItem = input.nextLine();
				userItem = isValidItem(userItem);

				if (userItem == null) {
					System.out.print("\nPlease enter a number based on what is listed above.\n\n>>");
				} else if (userItem.equals("0")) {
					System.out.println("Program exiting.");
					System.exit(1);
				}
			} while (this.userItem == null);

			//convert the user input (currently a digit) to a furniture name
			this.setFurnitureItem();

			
			//check for type, based on the furniture item
			do {
			switch(userItem) {
			
				case "CHAIR": 
					System.out.print("\n\nWhat type of Chair? Type out the full name.\n"
							+ "[Kneeling, Task, Mesh, Ergonomic, Executive]\n>> ");
					userType = input.nextLine();
					userType = setFurnitureType(userItem, userType);
					
						break;
						
				case "DESK": 
					System.out.print("\n\nWhat type of Desk? Type out the full name.\n"
							+ "[Standing, Adjustable, Traditional]\n>> ");
					userType = input.nextLine();
					userType = setFurnitureType(userItem, userType);
						break;
						
				case "LAMP": 
					System.out.print("\n\nWhat type of Lamp? Type out the full name.\n"
							+ "[Desk, Study, Swing Arm]\n>> ");
					userType = input.nextLine();
					userType = setFurnitureType(userItem, userType);
						break;
						
				case "FILING": 
					System.out.print("\n\nWhat type of Filing Cabinet? Type out the full name.\n"
							+ "[Small, Medium, Large]\n>> ");
					userType = input.nextLine();
					userType = setFurnitureType(userItem, userType);
						break;
				}
			
			} while(this.userType == null);
			
			
			//check for furniture quantity
			do {
				System.out.print("\nPlease enter the quantity: ");
				userQty = input.nextInt();
				this.userQty = this.setFurnitureQuantity(userQty);
				
			} while(this.userQty < 1);

		} catch (Exception e) {
			System.out.println("Please enter a valid user input.");
			e.printStackTrace();
		}
		
		//close the Scanner object after user inputs have finished processing
		input.close();
	}

	
	/**
	 * createInventoryHandler() initializes the inventory member class, using
	 * the information entered by the user. This is what will be searched for
	 * within the database.
	 * No arguments taken, no return value.
	 */
	public void createInventoryHandler() {
		this.inventory = new InventoryHandler(driver.getFurniture(this.getUserItem(), this.getUserType()));
	}
	
	/**
	 * createOrderForm() initializes the receipt member variable using 
	 * the requested inventory item(s). It will call the OrderForm methods
	 * from the receipt member class to then generate the receipt if the 
	 * order is valid.
	 * No arguments taken, no return value.
	 * 
	 * @throws IOException
	 */
	public void createOrderForm() throws IOException {
		receipt = new OrderForm(this.inventory);
		receipt.createOrderForm(driver, this.getUserQty(), this.getUserType(), this.getUserItem());
	}
	
	/**
	 * isValidTerm() is a helper method called by promptUser(). It will
	 * determine if the user's furniture item choice is a valid number
	 * between 0 to 4, and return the initial item choice parameter.
	 * If the item choice is invalid, a null string is returned.
	 * 
	 * @param itemChoice - User's inputed option
	 * @return null if invalid user input, itemChoice parameter otherwise
	 */
	public String isValidItem(String itemChoice) {
		// if input is greater than 1 character or is null, return false
		if (itemChoice.length() > 1 || itemChoice == null) {
			return null;
		}
		
		//create a temporary variable to store the string as a character
		char temp = itemChoice.charAt(0);

		// if it is not a number, return false
		if (!Character.isDigit(temp)) {
			return null;
		} else if (temp < '0' || temp > '4') {
			return null;
		}

		//all the checks have passed, therefore we can return the initial input
		return itemChoice;
	}

	/**
	 * setFurniture() is a helper method that converts the userItem variable
	 * to it's respective furniture item. This method makes the assumption that
	 * the database categories are consistent.
	 * No arguments taken, no return value.
	 */
	public void setFurnitureItem() {
		switch (this.userItem) {
		case "1":
			this.userItem = "CHAIR"; break;
		case "2":
			this.userItem = "DESK"; break;
		case "3":
			this.userItem = "LAMP"; break;
		case "4":
			this.userItem = "FILING"; break;
		}
	}
	
	/**
	 * setFurnitureType() is a method called by promptUser() to check if the
	 * furniture type requested by the user is available in the database.
	 * This method should not be confused with the OrderForm method
	 * createOrderForm(), which checks if the user's order is valid.
	 * 
	 * @param item - The furniture item chosen by the user
	 * @param typeChoice - The type of furniture item as chosen by the user
	 * @return returns null if type not found in the database, otherwise
	 * 		   returns the correctly formatted typeChoice
	 */
	public String setFurnitureType(String item, String typeChoice) {
		
		//formats the user's input to start with an upper case letter
		//followed by all lower case letters
		typeChoice = typeChoice.substring(0,1).toUpperCase() + typeChoice.substring(1).toLowerCase();
		
		if(driver.checkFurnitureType(item, typeChoice)) {
			System.out.println("\nThis furniture type is available in the database.");
		}
		else {
			System.out.println("Please input one of the furniture types listed above.");
			typeChoice = null;
		}
		return typeChoice;
	}
	
	/**
	 * setFurnitureQuantity() is a method called by promptUser() to check
	 * for a valid user input for the furniture quantity.
	 * 
	 * @param quantity - User specified quantity
	 * @return the user specified quantity if it is valid, otherwise 0
	 */
	public int setFurnitureQuantity(int quantity) {
		try {
			if(quantity > 0) {
				return quantity;
			}
			else {
				System.out.println("Quantity must be greater than 0. Please try again.");
				return 0;
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("Invalid quantity entry. Please try again.");
			return 0;
		}
	}
	
	/**
	 * Getter method for the user's furniture choice
	 * @return this.userItem
	 */
	public String getUserItem() {
		return this.userItem;
	}
	
	/**
	 * Getter method for the user's furniture type
	 * @return this.userType
	 */
	public String getUserType() {
		return this.userType;
	}
	
	/**
	 * Getter method for the user's furniture quantity
	 * @return this.userQty
	 */
	public int getUserQty() {
		return this.userQty;
	}

	/**
	 * main() method to execute program. Sets up the SupplyChainOperator()
	 * class, and initializes the InventoryHandler() and OrderForm() classes
	 * from within the SupplyChainOperator class.
	 * 
	 * @param args - Optional command line arguments
	 * @throws Exception
	 */
	public static void main(String args[]) throws Exception {
		SupplyChainOperator operator = new SupplyChainOperator();

		System.out.println("==================================================================");
		System.out.println("Welcome to the Furniture Component Reusability System!");
		System.out.println("You will be prompted for a number from a given list.");

		operator.promptUser();
		operator.createInventoryHandler();
		operator.createOrderForm();
	}

}
