
import java.beans.Statement;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Sam
 */

public class Connexion {

	
	 private static String driver = "com.mysql.jdbc.Driver";
	    private static String connection = "jdbc:mysql://localhost:3306/crypto"; //'produit' is your database name
	    private static String user = "root";                  //'root' is username
	    private static String password = "";        //'pass' is password


	    private static Connection con = null;
	    private static Statement state = null;
	    private static ResultSet result;
	    private static PreparedStatement pstate;
	    
	      
	    
	    public static Connection mysqlConnect(){
	        try{
	            Class.forName(driver);
	            con = DriverManager.getConnection(connection, user, password);
	            System.out.println("Successfully connected to database.");
                    
	            }
	        catch(ClassNotFoundException e){
	            System.err.println(e);
	            }
	        catch(SQLException e){
	            System.err.println(e);
	            }
	                        return con;
    
            }
	   
	    
	    

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


}
	  
	   
	   
