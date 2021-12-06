/**
 * 
 */

/**
 * @author mihiryadav
 *
 */
public class deleteQueries {
	
	public static String[] query1(String cid) {
		int cID = Integer.parseInt(cid);
		String query1 = "DELETE FROM yuchan0401.customer "
				+ "WHERE customer.cID = " + cID + ";";
		String query2 = "DELETE FROM yuchan0401.appointment "
				+ "WHERE appointment.CID = " + cID + ";";
		return new String[]{query1,query2};
	}

}
