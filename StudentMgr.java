
//  Include the java SQL classes 
import java.sql.*;
import java.io.*;

import com.sun.org.apache.bcel.internal.util.ClassPath;

public class StudentMgr
{
	
    private static Connection conn = null;
    
    private static PreparedStatement psUsersSchema;
    private static PreparedStatement psInsertUser;
    private static PreparedStatement selectUser;

    public static void main(String[] args) {
    	
        String driver = "org.sqlite.JDBC";
        String dbName="storage";
        String connectionURL = "jdbc:sqlite:" + dbName + ".db";


        
        BufferedReader stdIn;
        
        //   Loading the driver
        try {
            	Class.forName(driver); 
            	System.out.println(driver + " loaded. ");
        } catch(java.lang.ClassNotFoundException e)     {
            System.err.println(e.getMessage());
            System.out.println("\n    >>> Please check your CLASSPATH variable   <<<\n");
        }

      //  Connecting to the database
       try {
              conn = DriverManager.getConnection(connectionURL);		 
              System.out.println("Connected to database " + dbName);
              stdIn = new BufferedReader(new InputStreamReader(System.in));

              // Create the Schema
              
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
            	  if (e.getMessage().contains("table USERS already exists"))
            	  {
            		  System.out.println("Schema found.");
            	  }

              }
              
              
              System.out.println(sb.toString());
              
              psInsertUser = conn.prepareStatement("INSERT INTO USERS VALUES(?,?,?);");
              selectUser = conn.prepareStatement("SELECT * FROM USERS;");
              
              // Enter the first user
              
              System.out.print("Enter your staff username: ");
              
              String staff = new String();
              while ((staff = stdIn.readLine()).isEmpty()) {
            	  System.out.print("Enter your staff username: ");
              }
              
              psInsertUser.setString(1, staff);
              psInsertUser.setString(2, "default");
              psInsertUser.setString(3, "Y");
              
              psInsertUser.execute();
              
              ResultSet results = selectUser.executeQuery();
              
              while (results.next()) {
            	  System.out.println("Username = " + results.getString("USERNAME"));
            	  System.out.println("Password = " + results.getString("PASSWORD"));
            	  System.out.println("Is a Staff Member? = " + results.getString("STAFF"));
              }
              
              System.out.print("Enter your staff username: ");
              
              while (!stdIn.readLine().equalsIgnoreCase("exit")) {
            	  // Do Stuff Here
            	  
              }
              
              
          }  catch (Throwable e)  {   
             System.out.println(e.toString());
          }
    	
    }
    
    public static void prepareStatements() {
    
    
    
    }
}

