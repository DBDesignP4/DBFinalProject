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
				+ "WHERE customer.cID = " + cID;
		String query2 = "DELETE FROM yuchan0401.appointment "
				+ "WHERE appointment.CID = " + cID;
		return new String[]{query1,query2};
	}
	
	public static String[] query2(String eid) {
		int eID = Integer.parseInt(eid);
		String query1 = "DELETE FROM yuchan0401.appointment "
				+ "WHERE appointment.EID = " + eID;
		String query2 = "DELETE FROM yuchan0401.employee "
				+ "WHERE employee.eID = " + eID;
		return new String[]{query1,query2};
	}
	
	public static String query3(String aid) {
		int aID = Integer.parseInt(aid);
		String query1 = "DELETE FROM yuchan0401.appointment "
				+ "WHERE appointment.aID = " + aID;
		return query1;
	}

	public static String[] query4(String did) {
		// TODO Auto-generated method stub
		int dID = Integer.parseInt(did);
		String query1 = "DELETE FROM yuchan0401.department "
				+ "WHERE department.dID = " + dID;
		String query2 = "UPDATE yuchan0401.transact "
				+ "SET transact.DID = NULL "
				+ "WHERE transact.DID = " + dID;
		String query3 = "UPDATE yuchan0401.employee "
				+ "SET employee.DID = NULL "
				+ "WHERE employee.DID = " + dID;
		String query4 = "UPDATE yuchan0401.customer "
				+ "SET customer.DID = NULL "
				+ "WHERE customer.DID = " + dID;
		return new String[]{query1,query2,query3,query4};
	}
	
	public static String[] query5(String tid) {
		int tID = Integer.parseInt(tid);
		String query1 = "DELETE FROM yuchan0401.customer "
				+ "WHERE customer.TID = " + tID;
		String query2 = "DELETE FROM yuchan0401.transact "
				+ "WHERE transact.tID = " + tID;
		return new String[]{query1,query2};

	}
	

}
