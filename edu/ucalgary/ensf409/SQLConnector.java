package edu.ucalgary.ensf409;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

/**GROUP 32
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
 * @version 1.2
 * @since 1.0
 */

/**
 * SQLConnector establishes the connection to the mySQL database. The
 * class is also responsible for extracting and removing entries from the
 * database.
 */
public class SQLConnector {

	private final String DBUSER;
	private final String DBPASS;
	private final String DBURL = "jdbc:mysql://localhost/inventory";

	private Connection dbConnect; // connection between database and program
	private ResultSet line; // pointer for database table row

	/**
	 * constructor for DatabaseConnection(), the database username and password will
	 * be requested using a Scanner object. Calls the initializeConnection() method
	 * afterwards. No return value.
	 * 
	 * @param databasePrompter - Scanner object with System's input stream used to
	 *                         retrieve database login credentials
	 */
	public SQLConnector(Scanner databasePrompter) {
		// first, get the database login credentials
		String username = "scm";
		String password = "ensf409";

		try {
			System.out.println("\nPlease enter the database username.");
			username = databasePrompter.nextLine();

			System.out.println("\nPlease enter database password.");
			password = databasePrompter.nextLine();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Unable to obtain mySQL login credentials. Please restart the program.");
			System.exit(1);
		}
		
		this.DBUSER = username;
		this.DBPASS = password;

		// after getting the credentials, connect to the database
		this.initializeConnection();
	}

	/**
	 * initializeConnection() is a helper method called by the constructor. The
	 * connection to the mySQL database is attempted using the user inputs. If the
	 * login fails, an SQLException is caught and the program exits. 
	 * No arguments taken, no return value.
	 */
	public void initializeConnection() {
		try {
			this.dbConnect = DriverManager.getConnection(this.DBURL, this.DBUSER, this.DBPASS);
			System.out.println("Connection successful!\n\n");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Unable to Connect to mySQL. Check if the login credentials are correct.");
			System.exit(1);
		}
	}

	/**
	 * getFurniture() reads the mySQL database using PreparedStatement objects if
	 * the database entries match what is requested from the parameters, then they
	 * will be added to an ArrayList.
	 * 
	 * @param category - furniture item/category
	 * @param type     - furniture type
	 * @return ArrayList of furniture data from the database
	 */
	public ArrayList<Furniture> getFurniture(String category, String type) {
		ArrayList<Furniture> furniture = new ArrayList<Furniture>();
		try {
			String query = String.format("SELECT * FROM %s WHERE Type = ?", category);
			PreparedStatement stmt = dbConnect.prepareStatement(query);

			stmt.setString(1, type);

			line = stmt.executeQuery();
			while (line.next()) {
				String ID = line.getString("ID");
				int price = line.getInt("Price");
				int partsNum = line.getMetaData().getColumnCount() - 4;
				char[] parts = new char[partsNum];
				for (int i = 0; i < partsNum; i++) {
					parts[i] = line.getString(i + 3).charAt(0);
				}

				// parse into a Furniture item
				Furniture newItem = new Furniture(ID, type, price, parts);
				furniture.add(newItem);
			}
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("There was an issue with getting the furniture from the table. Exiting program.");
			System.exit(1);
		}
		return furniture;
	}

	/**
	 * removeFurniture() is the method that removes the found entries from the
	 * database after they have been sent to the order form receipt. No return
	 * value.
	 * 
	 * @param furniture - ArrayList of furniture entry to be removed
	 * @param category  - furniture item/category to delete from
	 */
	public void removeFurniture(ArrayList<Furniture> furniture, String category) {
		try {
			String query = String.format("DELETE FROM %s WHERE ID IN ('", category);
			for (int i = 0; i < furniture.size(); i++) {
				if (i != furniture.size() - 1) {
					query += furniture.get(i).getID() + "', '";
				} else {
					query += furniture.get(i).getID() + "')";
				}
			}
			Statement stmt = dbConnect.createStatement();
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method called by the OrderForm class to retrieve the suggested manufacturers
	 * from the database. Uses Statement and ResultSet objects.
	 * 
	 * @param category - Item category that is ordered
	 * @return ArrayList of manufacturers
	 */
	public ArrayList<String> getManufacturers(String category) {
		ArrayList<String> m = new ArrayList<String>();

		try {
			// get all the unique manuIDs (the foreign key in each category)
			ArrayList<String> manuIDs = new ArrayList<String>();
			String query = String.format("SELECT ManuID FROM %s", category);
			Statement s = dbConnect.createStatement();
			this.line = s.executeQuery(query);

			while (line.next()) {
				String idToAdd = line.getString("ManuID"); // extract manuID from database
				if (!manuIDs.contains(idToAdd)) {
					manuIDs.add(idToAdd); // add if not already in the list
				}
			}

			// create new query based on the manuIDs ArrayList
			query = "SELECT Name FROM MANUFACTURER WHERE ManuID IN (" + manuIDs.get(0);
			if (manuIDs.size() > 1) {
				for (int i = 0; i < manuIDs.size(); i++) {
					query += ", " + manuIDs.get(i);
				}
			}
			query += ")";

			// have to create new ResultSet and Statement objects for new query,
			// so now we just get the names and add them to the ArrayList
			Statement s2 = dbConnect.createStatement();
			ResultSet line2 = s2.executeQuery(query);
			while (line2.next()) {
				String name = line2.getString("Name");
				m.add(name);
			}

			// now that all the data is collected, close all of the resources
			line2.close();
			s2.close();
			s.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("There was an error when getting the suggested manufacturers.");
		}

		return m;
	}

	/**
	 * checkFunitureType() checks if the furniture type specified by the user is
	 * available for its respective furniture item.
	 * 
	 * @param item - furniture item/category to check against
	 * @param type - type of furniture item to be searched for
	 * @return true if found, false if not found
	 */
	public boolean checkFurnitureType(String item, String type) {
		try {
			String query = "SELECT * FROM " + item + " WHERE Type = ?";
			PreparedStatement ps = dbConnect.prepareStatement(query);

			ps.setString(1, type);
			this.line = ps.executeQuery();
			return line.next();
		} catch (SQLException e) {
			return false;
		}
	}

	/**
	 * close() closes the database, ensuring no resource leaks occur. No arguments
	 * taken, no return value.
	 */
	public void close() {
		try {
			this.line.close();
			this.dbConnect.close();
		} catch (Exception e) {
			System.out.println("Error closing the Connection and ResultSet objects.");
			e.printStackTrace();
		}
	}

} // end of class declaration, DatabaseConnection
