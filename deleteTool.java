import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * 
 */

/**
 * @author mihiryadav
 *
 */
public class deleteTool {

		
		public static void main(String[] args) {

			final String oracleURL = // Magic lectura -> aloe access spell
					"jdbc:oracle:thin:@aloe.cs.arizona.edu:1521:oracle";

			String username = null, // Oracle DBMS username
					password = null; // Oracle DBMS password

			if (args.length == 2) { // get username/password from cmd line args
				username = args[0];
				password = args[1];
			} else {
				System.out.println("\nUsage:  java JDBC <username> <password>\n"
						+ "    where <username> is your Oracle DBMS" + " username,\n    and <password> is your Oracle"
						+ " password (not your system password).\n");
				System.exit(-1);
			}

			// load the (Oracle) JDBC driver by initializing its base
			// class, 'oracle.jdbc.OracleDriver'.
			try {

				Class.forName("oracle.jdbc.OracleDriver");

			} catch (ClassNotFoundException e) {

				System.err.println("*** ClassNotFoundException:  " + "Error loading Oracle JDBC driver.  \n"
						+ "\tPerhaps the driver is not on the Classpath?");
				System.exit(-1);

			}

			// make and return a database connection to the user's
			// Oracle database

			Connection dbconn = null;

			try {
				dbconn = DriverManager.getConnection(oracleURL, username, password);

			} catch (SQLException e) {

				System.err.println("*** SQLException:  " + "Could not open JDBC connection.");
				System.err.println("\tMessage:   " + e.getMessage());
				System.err.println("\tSQLState:  " + e.getSQLState());
				System.err.println("\tErrorCode: " + e.getErrorCode());
				System.exit(-1);

			}

			// Send the query to the DBMS, and get and display the results

			Statement stmt = null;
			ResultSet result = null;
			Scanner input = new Scanner(System.in);

			System.out.println("----------Welcome to Program 4 Deletion tool----------\n");
	        System.out.println("What table would you like to delete from? \n");
			System.out.println("1.) Customer 2.) Employee 3.) Appointment 4.) Department 5.) Transaction\n");


			try {
				dbconn = DriverManager.getConnection(oracleURL, username, password);
			} catch (SQLException e) {

				System.err.println("*** SQLException:  " + "Could not open JDBC connection.");
				System.err.println("\tMessage:   " + e.getMessage());
				System.err.println("\tSQLState:  " + e.getSQLState());
				System.err.println("\tErrorCode: " + e.getErrorCode());
				System.exit(-1);

			}

			try {
				System.out.println("Choose a number from 1-5. Any other number to quit");
				String s = input.next();
				if (!isNumeric(s)) {
					System.out.println("Input wasn't correct format. Please re run!");
					System.exit(-1);
				}
				int option = Integer.parseInt(s);
				String query = "";
				
				while (1 == 1) {
					stmt = dbconn.createStatement();
					// option 1
					if (option == 1) {
						System.out.println("Enter CID of the customer you want to delete:");
						String cid = input.next();
						String[] res = deleteQueries.query1(cid);
						stmt.executeQuery(res[0]);
						stmt.executeQuery(res[1]);
					// option 2
					} else if (option == 2) {
						break;
					// option 3
					} else if (option == 3) {
						break;
					//option 4
					} else if (option == 4) {
						break;
					//optioni 5
					} else if(option == 5){
						break;
					} else {
						break;
					}
					System.out.println("Choose a number from 1-5. Any other number to quit");
					option = Integer.parseInt(input.next());
				}
			} catch (SQLException e) {
				System.err.println("*** SQLException:  " + "Could not fetch query results.");
				System.err.println("\tMessage:   " + e.getMessage());
				System.err.println("\tSQLState:  " + e.getSQLState());
				System.err.println("\tErrorCode: " + e.getErrorCode());
				System.exit(-1);
			}

		}
       
	
		/**
		 * isNumeric: checks if a string is numeric
		 * @param str
		 * @return boolean
		 */
		public static boolean isNumeric(String str) {
			try {
				Integer.parseInt(str);
				return true;
			} catch (NumberFormatException e) {
				return false;
			}
		}

}
