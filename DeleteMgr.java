package studentmgr;


//  Include the java SQL classes 
import java.sql.*;
import java.io.*;

import com.sun.org.apache.bcel.internal.util.ClassPath;

public class DeleteMgr
{
	
    private static Connection conn = null;
    
    private static PreparedStatement psUsersSchema;
    private static PreparedStatement psInsertUser;
    private static PreparedStatement selectUser;
    private static PreparedStatement deleteUser;

    public static void main(String[] args) {
    	
    String driver = "org.sqlite.JDBC";
    String dbName= "storage";
    String connectionURL = "jdbc:sqlite:" + dbName + ".db";
       
    BufferedReader stdIn;
        
    //   Loading the driver
    try {
            Class.forName(driver); 
            System.out.println(driver + " loaded. ");
        } catch(java.lang.ClassNotFoundException e) {
            System.err.println(e.getMessage());
            System.out.println("\n    >>> Please check your CLASSPATH variable   <<<\n");
        }

       //  Connecting to the database
       try {
              conn = DriverManager.getConnection(connectionURL);		 
              System.out.println("Connected to database " + dbName);
              stdIn = new BufferedReader(new InputStreamReader(System.in));
              
              // check for schema - if not create
              StringBuilder sb = new StringBuilder();
              
              sb.append("CREATE TABLE USERS (")
              		.append("USERNAME TEXT(100) NOT NULL, ")
              		.append("PASSWORD TEXT(100) NOT NULL, ")
              		.append("STAFF CHAR(1) NOT NULL ")
                .append(");");
              
              try {
                    psUsersSchema = conn.prepareStatement(sb.toString());
                    psUsersSchema.execute();
                  } catch (SQLException e) {
            	  // if the database already exists
                        if (e.getMessage().contains("table USERS already exists")) {
                            System.out.println("Schema found.");
                        }
                  }
              // end check for schema
              
              deleteUser = conn.prepareStatement("DELETE FROM USERS;");
              deleteUser.execute();
              
              System.out.println("All records deleted from USERS");
              
            }  catch (Throwable e)  {   
              System.out.println(e.toString());       
        }            
    	
    }
    
    public static void prepareStatements() {
    
    
    
    }
}

