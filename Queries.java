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
	
	public String query1(String inputDate) {
		String[] split = inputDate.split("/");
		
		String formatted = split[2] + "/" + split[0] + "/" + split[1];
		
		String retval = "SELECT * FROM mihiry4.customer WHERE "
				+ "cID < " + formatted;
		
		return retval;
	}
	
	/**
	 *  
	 * @return Returns 4 queries for each department separated by comma
	 */
	public String query2() {
		String permit = "SELECT COUNT(aID) FROM mihiry4.appointment WHERE "
				+ "EID = 1 and status = 1;";
		String license = "SELECT COUNT(aID) FROM mihiry4.appointment WHERE "
				+ "EID = 2 and status = 1;";
		String registration = "SELECT COUNT(aID) FROM mihiry4.appointment WHERE "
				+ "EID = 3 and status = 1;";
		String stateID = "SELECT COUNT(aID) FROM mihiry4.appointment WHERE "
				+ "EID = 4 and status = 1;";
		
		return permit + "," + license + "," + registration + "," + stateID;
	}
	
	/**
	 * Converts MM/YYYY to YYYY/MM
	 * 
	 * @param month for query 3 in the format MM/YYYY
	 * @return 
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
	 * 
	 * @param inputMonth : in the form of MM/YYYY
	 * @return 4 queries for each department separated by comma
	 */
	public String query3(String inputMonth) {
		// Split the input MM/YYYY to change the order into YYYY/MM
		String[] splitted = inputMonth.split("/");
		// Start date of the month
		String lowerBound = splitted[1] + "/" + splitted[0] + "/" + "01";
		// End date of the month
		String upperBound = splitted[1] + "/" + splitted[0] + "/" + endDate(splitted[0]);
		
		
		String permitSum = "SELECT COUNT(tID) * 7 FROM mihiry4.transaction WHERE "
				+ "time >= " + lowerBound + " and "
				+ "time <= " + upperBound + " and "
				+ "DID = 1";
		String licenseSum = "SELECT COUNT(tID) * 25 FROM mihiry4.transaction WHERE "
				+ "time >= " + lowerBound + " and "
				+ "time <= " + upperBound + " and "
				+ "DID = 2";
		String registrationSum = "SELECT COUNT(tID) * 100 FROM mihiry4.transaction WHERE "
				+ "time >= " + lowerBound + " and "
				+ "time <= " + upperBound + " and "
				+ "DID = 3";
		String stateIDSum = "SELECT COUNT(tID) * 12 FROM mihiry4.transaction WHERE "
				+ "time >= " + lowerBound + " and "
				+ "time <= " + upperBound + " and "
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
		
		for (int i = 0; i < 4; i++) {
			//System.out.println(t.get(arr.get(i)));
		}
		//System.out.println(t.keySet());
		//
		// #########################################################
		
		return permitSum + "," + licenseSum + "," + registrationSum + "," + stateIDSum;
	}
	
	public String query4(String inputDate) {
		String retval = "SELECT * FROM customer WHERE"
				+ " cID < " + inputDate;
		
		return retval;
	}
	
	
}