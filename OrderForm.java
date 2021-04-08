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
 * @version 1.0
 * @since 1.0
 */


//furniture receipt
public class OrderForm {
	
	/** 
	 * 
	 * WORK IN PROGRESS. DO WHATEVER YOU WANT WITH IT RN
	 * 
	 * 
	 * Furniture Order Form
	
	Faculty Name:
	Contact:
	Date:
	
	Original Request: mesh chair, 1
	
	Items Ordered
	ID: C9890
	ID: C0942
	
	Total Price: $150
	
	 */
	
	String outputReceipt = "";
	
	public OrderForm(ArrayList<Furniture> f, String item, String type, int qty) {
		this.generateOrderForm(f, item, type, qty);
	}
	
	public void generateOrderForm(ArrayList<Furniture> f, String item, String type, int qty) {
		outputReceipt += "Furniture Order Form";
		outputReceipt += "\n\nFaculty Name:";
		outputReceipt += "\nContact:";
		outputReceipt += "\nDate:";
		outputReceipt += "\n\nOriginal Request:" + type + " " + item + ", " + qty;
		outputReceipt += "\n\nItems Ordered\n";
	}
}
