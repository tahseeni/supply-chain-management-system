import java.sql.*;
import java.util.ArrayList;

/**
 * 
 * @author Tahseen Intesar, Gurpartap Sohi
 *
 */
public class Furniture {
	
	String id; 
    String type;
    int price; 
    String manufactureID;
    char[] parts;

    public Furniture(String id, int price, char [] parts) 
    {
    		this.id = id;
            //this.type = type;
            this.price = price;
    		//this.manufactureID = manuf_ID;
    		this.parts = parts;
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

    public char[] getParts(){
        return this.parts;
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