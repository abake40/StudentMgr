package studentmgr;


//  Include the java SQL classes 
import java.sql.*;
import java.io.*;

import com.sun.org.apache.bcel.internal.util.ClassPath;

public class SelectMgr
{
	
    private static Connection conn = null;
    
    private static PreparedStatement psUsersSchema;
    private static PreparedStatement psInsertUser;
    private static PreparedStatement selectUser;

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
              
              System.out.println("All records in table USERS");
              
              selectUser = conn.prepareStatement("SELECT * FROM USERS;");
              ResultSet results = selectUser.executeQuery();
              
              int i = 0;
              
              while (results.next()) {
                  i++;
                  System.out.println(i + "-");
            	  System.out.println("Username = " + results.getString("USERNAME"));
            	  System.out.println("Password = " + results.getString("PASSWORD"));
            	  System.out.println("Is a Staff Member? = " + results.getString("STAFF"));
              }
              
              System.out.println("-");
              System.out.println("Total users: " + i);
                   
            }  catch (Throwable e)  {   
                System.out.println(e.toString());       
        }
	
    }
    
    public static void prepareStatements() {
    
    
    
    }
}

