import java.sql.*;
import java.util.ArrayList;

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

public class Furniture {

	private String id;
	private String type;
	private int price;
	private String manufactureID;
	private char[] parts;

	public Furniture(String id, String type, String category, int price, char [] parts) {
		this.id = id;
        this.type = type;
        this.category = category;
        this.price = price;
		//this.manufactureID = manuf_ID;
		this.parts = parts;
    }

	public String getID() {
		return this.id;
	}

	public String getType() {
		return this.type;
	}

	public int getPrice() {
		return this.price;
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
}