import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * 
 * @author Tahseen Intesar, Gurpartap Sohi
 *
 */

public class DatabaseConnection {

	private final String DBUSER; //scm
	private final String DBPASS; //ensf409
	private final String DBURL = "jdbc:mysql://localhost/inventory";
	
	private Connection dbConnect;	//connection between database and program
	private ResultSet line;			//pointer for database table row
	
	public DatabaseConnection() {
		
		//UNCOMMENT SCANNER AND TRY/CATCH FOR THE FINAL DEMO/HANDING IN
		//SET USERNAME AND PASSWORD TO ""
		
		
		//first, get the database login credentials
		//Scanner databasePrompter = new Scanner(System.in);
		String username = "scm";
		String password = "ensf409";
		
		/*
		try {
			System.out.println("\nPlease enter the database username.");
			username = databasePrompter.nextLine();
			
			System.out.println("Please enter database password.");
			password = databasePrompter.nextLine();
			
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("Unable to obtain mySQL login credentials. Please restart the program.");
			System.exit(1);
		}
		*/
		this.DBUSER = username;
		this.DBPASS = password;
		
		//after getting the credentials, connect to the database
		this.initializeConnection();
		
		//HAVE TO CLOSE AFTER FINISHED USING DATABASE
		//databasePrompter.close();
	}
	
	public void initializeConnection() { 
		try {
			this.dbConnect = DriverManager.getConnection(this.DBURL, this.DBUSER, this.DBPASS);
			System.out.println("Connection successful!\n\n");
		}
		catch(SQLException e) {
			e.printStackTrace();
			System.out.println("Unable to Connect to mySQL. Check if the login credentials are correct.");
			System.exit(1);
		}
	}
	
	public ArrayList <Furniture> getFurniture(String category, String type){
        ArrayList <Furniture> furniture = new ArrayList<Furniture>();
        try {
            String query = String.format("SELECT * FROM %s WHERE Type = ? ORDER BY price ASC", category);
            PreparedStatement stmt = dbConnect.prepareStatement(query);
            
            stmt.setString(1, type);

            ResultSet r = stmt.executeQuery();
            while(r.next()) {
                String ID = r.getString("ID");
                int price = r.getInt("Price");
                int partsNum = r.getMetaData().getColumnCount() - 4;
                char[] parts = new char[partsNum];
                for(int i = 0; i < partsNum; i++) {
					parts[i] = r.getString(i + 3).charAt(0);
                }
                Furniture furn = new Furniture(ID, price, parts);
                furniture.add(furn);
            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return furniture;
    }
	
	public void removeFurniture(ArrayList<Furniture> furniture, String category) {
        try {
            String query = String.format("DELETE FROM %s WHERE ID IN ('", category);
			for(int i = 0; i < furniture.size(); i++)
			{
				if(i != furniture.size() - 1)
				{
					query += furniture.get(i).getID() + "', '";
				}
				else
				{
					query += furniture.get(i).getID() + "')";
				}
			}
            Statement stmt = dbConnect.createStatement();
            stmt.executeUpdate(query); 
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	public boolean checkFurnitureType(String item, String type) {
		try {
			String query = "SELECT * FROM " + item + " WHERE Type = ?";
			PreparedStatement ps = dbConnect.prepareStatement(query);
			
			ps.setString(1, type);
			this.line = ps.executeQuery();
			
			return line.next();
		}
		catch(SQLException e) {
			return false;
		}
	}
	
	public void close() {
        try {
            this.line.close();
            this.dbConnect.close();
        } catch (Exception e) {
            System.out.println("Error closing the Connection and ResultSet objects.");
            e.printStackTrace();
        }
    }
	
}
