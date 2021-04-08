import java.util.Scanner;

public class SupplyChainOperator {
	String userItem = "";
	String userType = "";
	int userQty;
	private DatabaseConnection driver;

	public SupplyChainOperator() {
		this.driver = new DatabaseConnection();
	}
	
	public void promptUser() throws Exception {

		Scanner input = new Scanner(System.in);

		try {
			do {
				System.out.println(
						"\nWhat furniture item are you looking for? \n1. Chair\n2. Desk\n3. Lamp\n4. Filing Cabinet"
								+ "\n5. Quit Program");
				System.out.print("\nPlease enter a number based on what is listed below.\n>> ");

				userItem = input.nextLine();
				userItem = isValidItem(userItem);

				if (userItem == null) {
					System.out.print("\nPlease enter a number based on what is listed above.\n\n");
				} else if (userItem.equals("5")) {
					System.out.println("Program exiting.");
					System.exit(1);
				}
			} while (this.userItem == null);

			this.setFurnitureItem();

			System.out.println(userItem);
			
			do {
				
			switch(userItem) {
			
				case "CHAIR": 
					System.out.println("\nWhat type of Chair? Type the full name.\n"
							+ "[Kneeling, Task, Mesh, Ergonomic, Executive]");
					userType = input.nextLine();
					userType = setFurnitureType(userItem, userType);
					
						break;
						
				case "DESK": 
					System.out.println("\nWhat type of Desk? Type the full name.\n"
							+ "[Standing, Adjustable, Traditional]");
					userType = input.nextLine();
					userType = setFurnitureType(userItem, userType);
						break;
						
				case "LAMP": 
					System.out.println("\nWhat type of Lamp? Type the full name.\n"
							+ "[Desk, Study, Swing Arm]");
					userType = input.nextLine();
					userType = setFurnitureType(userItem, userType);
						break;
						
				case "FILING": 
					System.out.println("\nWhat type of Filing Cabinet? Type the full name.\n"
							+ "[Small, Medium, Large]");
					userType = input.nextLine();
					userType = setFurnitureType(userItem, userType);
						break;
				}
			
			}while(this.userType == null);
			
			do {
				userQty = this.setFurnitureQuantity();
			}while(this.userQty < 0);
			
			
			//System.out.println("Program should be here. Type is : " + this.userType);
			System.out.print("\nPlease enter the quantity: ");
			userQty = input.nextInt();

		} catch (Exception e) {
			System.out.println("Please enter a valid user input.");
			e.printStackTrace();
		}

		input.close();
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
		} else if (temp <= '0' || temp > '5') {
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
			System.out.println("The furniture type is available in the database.");
		}
		else {
			System.out.println("Please input a valid furniture type.");
			typeChoice = null;
		}
		return typeChoice;
	}
	
	public int setFurnitureQuantity() {
		
		return 0;
	}
	
	public static void main(String args[]) throws Exception {
		SupplyChainOperator driver = new SupplyChainOperator();

		System.out.println("==================================================================");
		System.out.println("Welcome to the UofC Furniture Component Reusability System!");
		System.out.println("You will be prompted for a number from a given list.");

		driver.promptUser();

	}

}
