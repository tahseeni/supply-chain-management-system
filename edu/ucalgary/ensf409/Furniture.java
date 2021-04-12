package edu.ucalgary.ensf409;

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
 * 
 * Furniture class to store entries from the database
 * 
 */
public class Furniture {

	//member variables to parse the data into
	private String id;
	private String type;
	private int price;
	private char[] parts;

	/**
	 * Constructor for Furniture class, stores the variable
	 * into the member variables
	 * 
	 * @param id - ID of the entry
	 * @param type - type of furniture item
	 * @param price - price of the furniture item
	 * @param parts - array of parts available parts to create the full furniture item
	 */
	public Furniture(String id, String type, int price, char[] parts) {
		this.id = id;
		this.type = type;
		this.price = price;
		this.parts = parts;
	}

	public String getID() {
		return id;
	}

	public String getType() {
		return type;
	}

	public int getPrice() {
		return price;
	}

	public char[] getParts() {
		return this.parts;
	}

	public void setID(String id) {
		this.id = id;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setPrice(int price) {
		this.price = price;
	}
} // end of class declaration, Furniture
