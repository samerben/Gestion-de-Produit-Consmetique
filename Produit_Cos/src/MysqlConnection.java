import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;



public class MysqlConnection {
	
	
	 private static String driver = "com.mysql.jdbc.Driver";
	    private static String connection = "jdbc:mysql://localhost:3306/produit"; //'produit' is your database name
	    private static String user = "root";                  //'root' is username
	    private static String password = "";        //'pass' is password


	    private static Connection con = null;
	    private static Statement state = null;
	    private static ResultSet result;
	    private static PreparedStatement pstate;
	    
	    
	    public static void Show() {
        	String s="SELECT * FROM categorie ";

	    	 try{
					MysqlConnection co=new MysqlConnection();
					co.mysqlConnect();
					
		            state = con.createStatement();
		            result = state.executeQuery(s);
		            while(result.next()){
		             
		            	System.out.println(result.getInt(1) + result.getString(2));
		                
		                }
		            }
		        catch(SQLException e){
		            System.err.println("Query error.");
		            }
		        catch(NullPointerException e){
		            System.err.println("Element not found.");
		            }
	    }
	    
	    
	    public static void mysqlConnect(){
	        try{
	            Class.forName(driver);
	            con = DriverManager.getConnection(connection, user, password);
	            System.out.println("Successfully connected to database.");
	            }
	        catch(ClassNotFoundException e){
	            System.err.println("Couldn't load driver.");
	            }
	        catch(SQLException e){
	            System.err.println("Couldn't connect to database.");
	            }
	        }
	    
	    
	    public  void test() {
	    	
	    	
	    }
	    
	    
	    //
	   
	    
	    

	    public static void closeConnection(){
	        try{
	            if(!con.isClosed()){
	                con.close();
	                System.out.println("Database closed successfully.");
	                }
	            }
	        catch(NullPointerException e){
	            System.err.println("Couldn't load driver.");
	            }
	        catch(SQLException e){
	            System.err.println("Couldn't close database.");
	            }
	        }

	    public static void main(String args[])/* throws Exception*/{


	    	mysqlConnect();
	    	MysqlConnection s=new MysqlConnection();
           	       s.test();
	    	
	    	
	    }

}
