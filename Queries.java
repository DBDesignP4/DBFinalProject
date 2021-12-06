import java.util.ArrayList;
import java.util.TreeMap;


/**
 * 
 * Usage : 
 * 1. Construct Queries object
 * --> Queries q = new Queries();
 * 
 * 2. Call the right function according to the user input
 * --> ex) User types 1 for the query
 * -->     String sqlQueryToBePassedIntoTheJDBCConnection = q.query1(inputDate);
 *
 */
public class Queries {
	
	public Queries() {
	}
	

	/**
	 * Constructs the sql statement as a String for query1.
	 * 
	 * Query 1 takes in inputDate in the form of MM/DD/YYYY and checks
	 * in the database 
	 * @param inputDate
	 * @return

	/**
	 * Function called that returns the string of the query1 to be ran. 
	 * @param inputDate
	 * @return String
	 */
	public String query1(String inputDate) {
		String[] split = inputDate.split("/");
		
		String formatted = split[2] + "-" + split[0] + "-" + split[1];
		
		String retval = "SELECT * FROM yuchan0401.customer WHERE "
				+ "endDate < TO_DATE('" + formatted + "', 'YYYY/MM/DD')";
		
		return retval;
	}
	

	/**
	 *  Function called that returns the string of the query2 to be ran
	 * 
	 * @return Returns 4 queries for each department separated by comma
	 */
	public String query2() {
		String permit = "SELECT COUNT(aID) FROM yuchan0401.appointment WHERE "
				+ "EID = 1 and status = 1";
		String license = "SELECT COUNT(aID) FROM yuchan0401.appointment WHERE "
				+ "EID = 2 and status = 1";
		String registration = "SELECT COUNT(aID) FROM yuchan0401.appointment WHERE "
				+ "EID = 3 and status = 1";
		String stateID = "SELECT COUNT(aID) FROM yuchan0401.appointment WHERE "
				+ "EID = 4 and status = 1";
		
		
		
		return permit + "," + license + "," + registration + "," + stateID;
	}
	

	/**
	 * Converts MM/YYYY to YYYY/MM
	 * 
	 * @param month for query 3 in the format MM/YYYY
	 * @return String
	 */
	private String endDate(String month) {
		int m = Integer.parseInt(month);
		if (m == 1 || m == 3 || m == 5 || m == 7 || m == 8 || m == 10 || m == 12) {
			return "31";
		} else if (m == 4 || m == 6 || m == 9 || m == 11) {
			return "30";
		} else {
			return "28";
		}
	}


	/**
	 * Function called that returns the string of the query3 to be ran
	 * 
	 * @param inputMonth : in the form of MM/YYYY
	 * @return 4 queries for each department separated by comma
	 */
	public String query3(String inputMonth) {
		// Split the input MM/YYYY to change the order into YYYY/MM
		String[] splitted = inputMonth.split("/");
		// Start date of the month
		String lowerBound = splitted[1] + "-" + splitted[0] + "-" + "01";
		// End date of the month
		String upperBound = splitted[1] + "-" + splitted[0] + "-" + endDate(splitted[0]);
		
		
		String permitSum = "SELECT COUNT(tID) * 7 FROM yuchan0401.transact WHERE "
				+ "time >= TO_DATE('" + lowerBound + "', 'YYYY/MM/DD') and "
				+ "time <= TO_DATE('" + upperBound + "', 'YYYY/MM/DD') and "
				+ "DID = 1";
		String licenseSum = "SELECT COUNT(tID) * 25 FROM yuchan0401.transact WHERE "
				+ "time >= TO_DATE('" + lowerBound + "', 'YYYY/MM/DD') and "
				+ "time <= TO_DATE('" + upperBound + "', 'YYYY/MM/DD') and "
				+ "DID = 2";
		String registrationSum = "SELECT COUNT(tID) * 100 FROM yuchan0401.transact WHERE "
				+ "time >= TO_DATE('" + lowerBound + "', 'YYYY/MM/DD') and "
				+ "time <= TO_DATE('" + upperBound + "', 'YYYY/MM/DD') and "
				+ "DID = 3";
		String stateIDSum = "SELECT COUNT(tID) * 12 FROM yuchan0401.transact WHERE "
				+ "time >= TO_DATE('" + lowerBound + "', 'YYYY/MM/DD') and "
				+ "time <= TO_DATE('" + upperBound + "', 'YYYY/MM/DD') and "
				+ "DID = 4";
		
		// #########################################################
		// Part below will sort the results by their sum
		// This should be moved to the Prog4.java after the data interaction code
		// to handle the results returned.
		TreeMap<Integer, String> t = new TreeMap<Integer, String>();
		
		// Put the sums instead of the integers below
		t.put(31, "1");
		t.put(50, "2");
		t.put(28, "3");
		t.put(83, "4");
		
		ArrayList<Integer> arr = new ArrayList<Integer>();
		arr.addAll(t.keySet());
		
		return permitSum + "#" + licenseSum + "#" + registrationSum + "#" + stateIDSum;
	}
	
	
	/**
	 * Function called that returns the string of the query4 to be ran. 
	 * @return String. 
	 */
	public String query4(String inputDID) {
		String retval = "select fName, lName " +
		"from yuchan0401.Customer, yuchan0401.Appointment, yuchan0401.Department " +
		"where yuchan0401.Customer.cID = yuchan0401.Appointment.CID and " +
		"yuchan0401.Appointment.status = 1 and yuchan0401.Department.dID = yuchan0401.Customer.DID " +
		"and yuchan0401.Department.dID = " + inputDID;
		
		return retval;
	}
}
