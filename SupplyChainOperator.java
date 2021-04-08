import java.util.Scanner;

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

public class SupplyChainOperator {
	String userItem = "";
	String userType = "";
	int userQty;
	private DatabaseConnection driver;
	private InventoryHandler inventory;
	private OrderForm receipt;

	public SupplyChainOperator() {
		this.driver = new DatabaseConnection();
	}
	
	public void promptUser() throws Exception {

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
					System.out.print("\nPlease enter a number based on what is listed above.\n\n");
				} else if (userItem.equals("0")) {
					System.out.println("Program exiting.");
					System.exit(1);
				}
			} while (this.userItem == null);

			this.setFurnitureItem();

			
			//check for type, based on the furniture item
			do {
			switch(userItem) {
			
				case "CHAIR": 
					System.out.println("\nWhat type of Chair? Type out the full name.\n"
							+ "[Kneeling, Task, Mesh, Ergonomic, Executive]");
					userType = input.nextLine();
					userType = setFurnitureType(userItem, userType);
					
						break;
						
				case "DESK": 
					System.out.println("\nWhat type of Desk? Type out the full name.\n"
							+ "[Standing, Adjustable, Traditional]");
					userType = input.nextLine();
					userType = setFurnitureType(userItem, userType);
						break;
						
				case "LAMP": 
					System.out.println("\nWhat type of Lamp? Type out the full name.\n"
							+ "[Desk, Study, Swing Arm]");
					userType = input.nextLine();
					userType = setFurnitureType(userItem, userType);
						break;
						
				case "FILING": 
					System.out.println("\nWhat type of Filing Cabinet? Type out the full name.\n"
							+ "[Small, Medium, Large]");
					userType = input.nextLine();
					userType = setFurnitureType(userItem, userType);
						break;
				}
			
			} while(this.userType == null);
			
			
			//check for quantity
			do {
				System.out.print("\nPlease enter the quantity: ");
				userQty = input.nextInt();
				this.userQty = this.setFurnitureQuantity(userQty);
				
			} while(this.userQty < 1);

		} catch (Exception e) {
			System.out.println("Please enter a valid user input.");
			e.printStackTrace();
		}
		
		//user inputs complete, now initialize inventory manager
		this.createInventoryHandler();
		
		//inventory.displayCombinations(inventory.getCombinations());
		//inventory.displayCheapestCombination(inventory.getCombinations());
		
		
		
		//check for availability
		//this.inventory.displayCheapestCombination(f);

		input.close();
	}

	public void createInventoryHandler() {
		this.inventory = new InventoryHandler(driver.getFurniture(this.getUserItem(), this.getUserType()));
	}
	
	public void createOrderForm() {
		
	}
	
	public String isValidItem(String itemChoice) {
		// (userItem = input.nextLine()).length() > 1 &&

		// if input is greater than 1 character or is null, return false
		if (itemChoice.length() > 1 || itemChoice == null) {
			return null;
		}

		char temp = itemChoice.charAt(0);

		// if it is not a number, return false
		if (!Character.isDigit(temp)) {
			return null;
		} else if (temp < '0' || temp > '4') {
			return null;
		}

		return itemChoice;
	}

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
	
	public String setFurnitureType(String item, String typeChoice) {
		
		//this allows the typeChoice to be mixed case
		typeChoice = typeChoice.substring(0,1).toUpperCase() + typeChoice.substring(1).toLowerCase();
		
		if(driver.checkFurnitureType(item, typeChoice)) {
			System.out.println("This furniture type is available in the database.");
		}
		else {
			System.out.println("Please input one of the furniture types listed above.");
			typeChoice = null;
		}
		return typeChoice;
	}
	
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
	
	public String getUserItem() {
		return this.userItem;
	}
	
	public String getUserType() {
		return this.userType;
	}
	
	public int getUserQty() {
		return this.userQty;
	}
	
	public static void main(String args[]) throws Exception {
		SupplyChainOperator operator = new SupplyChainOperator();

		System.out.println("==================================================================");
		System.out.println("Welcome to the UofC Furniture Component Reusability System!");
		System.out.println("You will be prompted for a number from a given list.");

		operator.promptUser();
	}

}
