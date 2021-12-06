import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * 
 * @author Mihir yadav, Zachary Florez, Rubin Yang, Gerry Guardiola
 * Class: CSC 460 Database Design 
 * Assignment: Prog3.java
 * Instructor and TA names: Dr.Lester I. McCann, Sourav Mangla, Justin doo
 * Description: 
 */
public class Prog4 {
	
	public static class Pair implements Comparable{
		int index;
		int value;
		public Pair(int i, int v) {
			index = i;
			value = v;
		}






		@Override
		public int compareTo(Object o) {
			Prog4.Pair temp = (Pair)o;
			// TODO Auto-generated method stub
			return Integer.valueOf(value).compareTo(temp.value);
		}

	}

	/**
	 * main : Connects to database and prompts users on 4 questions. 
	 * and the user selects which questions need to be answered
	 * @param args
	 */
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
		Scanner inputOption = new Scanner(System.in);

		System.out.println("----------Welcome to Program 4----------\n");
		System.out.println("Please read the options below to get info. from the database.\n"
				+ "Enter input number to get results\n");
		
		System.out.println(
				"(1) Write a query that displays the user details whose Ids will expire given a date in format MM/DD/YYYY\n" + 
				"(given by the user). The result should display the user id, user name, id issued date, id expiry date and\n" + 
				"type of id.\n"
				+ "(2) To get a licence, the user has to clear some tests, and there are other conditions to other types of ids. So,\n" + 
				"not every appointment is successful. Your work is to write a query for the previous month count every\n" + 
				"type of appointment and check how many of them got successful their IDs. For example: in October,\n" + 
				"DMV got 50 appointments for driving licence and 30 appointments for vehicle registration but 40 got\n" + 
				"approved for the licence and 25 for registration. Assume all work is done in one day.\n"
				+ "(3) Write a query that displays the collected fee amount for every department for a given month in the format\n" + 
				"MM/YYYY (given by the user). The result should display the amount and department information and\n" + 
				"sort the result on amount in descending order.\n"
				+ "(4) Our Query. \n\n");
		
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
			System.out.println("Choose a number from 1-4. Any other number to quit");
			String s = inputOption.next();
			if (!isNumeric(s)) {
				System.out.println("Input wasn't correct format. Please re run!");
				System.exit(-1);
			}
			int option = Integer.parseInt(s);
			
			if (option > 4 || option < 1) {
				System.out.println("Goodbye!");
				System.exit(-1);
			}
			
			Queries q = new Queries();
			
			// option 1
			while (1 == 1) {
				stmt = dbconn.createStatement();
				
				if (option == 1) {
					System.out.println("Enter the date to be checked");
					
					s = inputOption.next();
					
					String query = q.query1(s);
					
					ResultSet set = stmt.executeQuery(query);
					System.out.println("#####cID---First Name-----Last Name---ID issue date---Expiry Date---Department ID#####");	
					while (set.next()) {
						System.out.println(String.format("%1$8s",set.getString(1)) + String.format("%1$12s", set.getString(2)) + String.format("%1$15s", set.getString(3)) + String.format("%1$17s",set.getString(6).substring(0, 11)) +  String.format("%1$14s", set.getString(7).substring(0, 11)) +  String.format("%1$15s", set.getString(5)));
					}
				} else if (option == 2) {
					String[] queries = q.query2().split(",");
				
					ResultSet set1 = stmt.executeQuery(queries[0]);
				
					System.out.println("Successful Appointments Per Depeartment");

					System.out.print("Permit               : ");
					while (set1.next()) { 
						System.out.println(String.format("%1$8s", set1.getString(1)));
					}
					
					System.out.print("License              : ");
					ResultSet set2 = stmt.executeQuery(queries[1]);
					while (set2.next()) {
						System.out.println(String.format("%1$8s", set2.getString(1)));
					}

					System.out.print("Vehicle Registration : ");
					ResultSet set3 = stmt.executeQuery(queries[2]);
					while (set3.next()) {
                                                System.out.println(String.format("%1$8s", set3.getString(1)));
                                        }

					System.out.print("State ID             : ");
					ResultSet set4 = stmt.executeQuery(queries[1]);
					while (set4.next()) {
                                                System.out.println(String.format("%1$8s", set4.getString(1)));
                                        }

				} else if (option == 3) {
					System.out.println("Enter the Month to be checked");
					
					s = inputOption.next();
					
					String[] queries = q.query3(s).split("#");
					
					int sum1 = 0;
					int sum2 = 0;
					int sum3 = 0;
					int sum4 = 0;

					ResultSet set1 = stmt.executeQuery(queries[0]);
					while (set1.next()) {
						sum1 = Integer.parseInt(set1.getString(1));
					}
					
					ResultSet set2 = stmt.executeQuery(queries[1]);
					while (set2.next()) {
                                                sum2 = Integer.parseInt(set2.getString(1));
                                        }

					ResultSet set3 = stmt.executeQuery(queries[2]);
                                        while (set3.next()) {
                                                sum3 = Integer.parseInt(set3.getString(1));
                                        }

                                        ResultSet set4 = stmt.executeQuery(queries[3]);
                                        while (set4.next()) {
                                                sum4 = Integer.parseInt(set4.getString(1));
                                        }
					
					Pair[] arr = new Pair[4];
					arr[0] = new Pair(1, sum1);
					arr[1] = new Pair(2, sum2);
					arr[2] = new Pair(3, sum3);
					arr[3] = new Pair(4, sum4);
					// Put the sums instead of the integers below
//					t.put(sum1, "1");
//					t.put(sum2, "2");
//					t.put(sum3, "3");
//					t.put(sum4, "4");
					
//					ArrayList<Integer> arr = new ArrayList<Integer>();
//					arr.addAll(t.keySet());
					
					
					Arrays.sort(arr);
					
					for (int i = 3; i >=0; i--) {
						System.out.println("Dep : " + arr[i].index + " Sum : $" + arr[i].value);
					}
				} else if (option == 4) {
					System.out.println("Enter the Department Number");
					System.out.println("1 for Permit");
					System.out.println("2 for License");
					System.out.println("3 for Vehicle Registration");
					System.out.println("4 for State ID");
					
					s = inputOption.next();
					
					String query = q.query4(s);
					
					ResultSet set = stmt.executeQuery(query);
					
					while (set.next()) {
						System.out.println("First Name : " + set.getString(1) + " Last Name : " + set.getString(2));
					}
				}
				System.out.println("Choose a number from 1-4. Any other number to quit");
				s = inputOption.next();
				if (!isNumeric(s)) {
					System.out.println("Input wasn't correct format. Please re run!");
					System.exit(-1);
				}
				option = Integer.parseInt(s);
				

				
				if (option > 4 || option < 1) {
					System.out.println("Goodbye!");
					System.exit(-1);
				}
			}
		}  catch (SQLException e) {
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
