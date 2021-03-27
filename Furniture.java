import java.sql.*;
import java.util.ArrayList;

/**
 * 
 * @author 
 *
 */

public class Furniture {
	
	String id; 
    String type;
    int price; 
    String manufactureID;
        
    ArrayList <String> parts = new ArrayList <>();

    public Furniture(ResultSet row) 
    {
    	try {
    		this.id = row.getString("ID");
    		this.type = row.getString("Type");
    		this.price = row.getInt("Price");
    		this.manufactureID = row.getString("ManuID");
    		
    		//parts array initialized
    		
    	}
    	catch(SQLException e) {
    		System.out.println("Unable to extract data.");
    		e.printStackTrace();
    	}
    }
    
    public String getID(){
        return id;
    }

    public String getType(){
        return type;
    }

    public int getPrice(){
        return price; 
    }

    
    public void setID(String id){
        this.id = id;
    }

    public void setType(String type){
        this.type = type;
    }

    public void setPrice(int price){
        this.price = price;
    }
    
    

}