import java.sql.*;

/**
 * 
 * @author 
 *
 */

public class DatabaseConnection {

	private final String DBUSER; //scm
	private final String DBPASS; //ensf409
	private final String DBURL;	 //jdbc:mysql://localhost/inventory
	
	private Connection dbConnect;	//connection between database and program
	private ResultSet line;			//pointer for database table row
	
	public DatabaseConnection(String user, String pass, String url) {
		this.DBUSER = user;
		this.DBPASS = pass;
		this.DBURL = url;
	}
	
	public void initializeConnection() { 
		try {
			this.dbConnect = DriverManager.getConnection(this.DBURL, this.DBUSER, this.DBPASS);
		}
		catch(SQLException e) {
			e.printStackTrace();
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
