import java.util.Scanner;

public class SupplyChainOperator {
	String userItem = "";
	String userType = "";
	int userQty;
	
	public void promptUser() throws Exception {
		
		Scanner input = new Scanner(System.in);
		
		try {						
			
			System.out.println("\nWhat furniture item are you looking for? [Chair, Desk, Lamp, Filing]");
			while((userItem = input.nextLine()).length() < 4) {
				System.out.println("\nPlease enter an input based on what is inside of the Square Brackets.");
				System.out.println("What furniture item are you looking for? [Chair, Desk, Lamp, Filing]");
			}
			
			if(userItem.equals("Chair") || userItem.equals("chair")) {
				System.out.println("\nWhat type of Chair? [Kneeling, Task, Mesh, Ergonomic, Executive]");
				userType = input.nextLine();	
			}		
			else if(userItem.equals("Desk") || userItem.equals("desk")) {
				System.out.println("\nWhat type of Desk? [Standing, Adjustable, Traditional]");
				userType = input.nextLine();	
			}
			else if(userItem.equals("Lamp") || userItem.equals("lamp")) {
				System.out.println("\nWhat type of Lamp? [Desk, Study, Swing Arm]");
				userType = input.nextLine();	
			}
			else if(userItem.equals("Filing") || userItem.equals("filing")) {
				System.out.println("\nWhat type of Filing Cabinet? [Small, Medium, Large]");
				userType = input.nextLine();	
			}
			
			System.out.println("\nHow many " + userItem + "s would you like?");
			userQty = input.nextInt();
		}
		catch(Exception e) {
			System.out.println("Please enter a valid user input.");
			e.printStackTrace();
		}
		
		input.close();
	}
	
	public static void main(String args[]) throws Exception {
		SupplyChainOperator driver = new SupplyChainOperator();
		
		//Database initialization before welcome screen.

		
		System.out.println("Welcome to the UofC Furniture Component Reusability System!");
		System.out.println("You will be prompted with valid answers in the square [] brackets.");
		
		driver.promptUser();
	}
	
	
	
}
