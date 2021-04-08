import java.util.Scanner;

public class SupplyChainOperator {
	String userItem = "";
	String userType = "";
	int userQty;

	public SupplyChainOperator() {
		DatabaseConnection driver = new DatabaseConnection();
	}
	
	public void promptUser() throws Exception {

		Scanner input = new Scanner(System.in);

		try {
			do {
				System.out.println(
						"\nWhat furniture item are you looking for? \n1. Chair\n2. Desk\n3. Lamp\n4. Filing Cabinet"
								+ "\n5. Quit Program");
				System.out.print("\nPlease enter a number based on what is inside of the Square Brackets.\n>> ");

				userItem = input.nextLine();
				userItem = isValidItem(userItem);

				if (userItem == null) {
					System.out.print("\nPlease enter a number based on what is inside of the Square Brackets.\n\n");
				} else if (userItem.equals("5")) {
					System.out.println("Program exiting.");
					System.exit(1);
				}
			} while (this.userItem == null);

			this.setFurnitureItem();

			System.out.println(userItem);
			
			switch(userItem) {
			
			case "CHAIR": 
				System.out.println("\nWhat type of Chair? Type the full name.\n"
						+ "[Kneeling, Task, Mesh, Ergonomic, Executive]");
					break;
					
			case "DESK": 
				System.out.println("\nWhat type of Desk? Type the full name.\n"
						+ "[Standing, Adjustable, Traditional]");
					break;
					
			case "LAMP": 
				System.out.println("\nWhat type of Lamp? Type the full name.\n"
						+ "[Desk, Study, Swing Arm]");
					break;
					
			case "FILING": 
				System.out.println("\nWhat type of Filing Cabinet? Type the full name.\n"
						+ "[Small, Medium, Large]");
					break;
			
			}
			
			/*
			 * switch (userItem) { case "1": System.out.
			 * println("\nWhat type of Chair? [Kneeling, Task, Mesh, Ergonomic, Executive]"
			 * ); userType = input.nextLine(); break;
			 * 
			 * case "2":
			 * System.out.println("\nWhat type of Desk? [Standing, Adjustable, Traditional]"
			 * ); userType = input.nextLine(); break;
			 * 
			 * case "3":
			 * System.out.println("\nWhat type of Lamp? [Desk, Study, Swing Arm]"); userType
			 * = input.nextLine(); break;
			 * 
			 * case "4":
			 * System.out.println("\nWhat type of Filing Cabinet? [Small, Medium, Large]");
			 * userType = input.nextLine(); break; }
			 */

//			do {
//				
//			} while (this.userType == null);
			
			// System.out.println("\nHow many " + userItem + "s would you like?");
			// userQty = input.nextInt();

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
	
	public static void main(String args[]) throws Exception {
		SupplyChainOperator driver = new SupplyChainOperator();

		System.out.println("Welcome to the UofC Furniture Component Reusability System!");
		System.out.println("You will be prompted for a number within the square [] brackets.");

		driver.promptUser();

	}

}
