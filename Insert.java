import java.security.Timestamp; 
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;
import java.util.Date;
import java.util.Map;

/**
* 
* @author Mihir yadav, Zachary Florez, Rubin Yang, Gerry Guardiola
* Class: CSC 460 Database Design 
* Assignment: Prog3.java
* Instructor and TA names: Dr.Lester I. McCann, Sourav Mangla, Justin doo
* Description: 
*/


public class Insert {

    ArrayList<Integer> cIDs = new ArrayList<Integer>();
    static int[] eIDs = new int[]{0,1,2,3,4};
    
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("----------Welcome to Program 4 Data Creator----------\n");
        System.out.println("Do you have an ID? (Y/N)");

        boolean haveId = false; 
        String input = scanner.nextLine().toUpperCase();

        if (input.equals("Y")) {

            System.out.println("Please enter your cID: ");
            int cID = Integer.parseInt(scanner.nextLine());
            
            int dID = getServiceID(scanner);

            String newApointmentSQL = createAppointment(dID, cID, false);

           
        } else if (input.equals("N")) {
            System.out.println("Enter first name:");
            String fName = scanner.nextLine(); 

            System.out.println("Enter last name:");
            String lName = scanner.nextLine(); 
            
            int cID = randomCustomerIDGenerator();
            System.out.print("\nYour assigned cID number is:");
            System.out.println(cID);
            System.out.println("(Please save this number for future references)");
            System.out.println();

            int dID = getServiceID(scanner);

            int tID = randomTransactionIDGenerator();

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());

            // Start date + End Date depending on department
            String startDate = sdf.format(calendar.getTime());
            String endDate = getExpirationDate(dID, sdf, calendar);
            
            
            String newCustomerSQL = createCustomer(cID, fName, lName, dID, tID ,startDate, endDate);
            String newApointmentSQL = createAppointment(dID, cID, false);

            System.out.println(newCustomerSQL);
            System.out.println(newApointmentSQL);

            
            // can run an infinite loop and store all the strings in a set
            // at the end of while loop write all strings from set to a sql file
            
            // need to write SQL queires to a new sql file using buffered reader/writer
            
        }
    }


    private static String createCustomer(int cID, String fName, String lName, 
    		int dID, int tID, String startDate, String endDate) {
    	String insertString = "INSERT INTO customerTable VALUES (";
    	insertString = insertString + cID + ", '" + fName + "', '" + lName + "', " +
    					dID + ", " + tID + ", " + startDate + ", " + endDate;
    	insertString += ");";
		return insertString;
	}


	private static String createAppointment(int dID, int cID, boolean status) {
    	int statusInt = 0;
    	if(status == true)
    		statusInt = 1;
    	String insertString = "INSERT INTO appointmentTable VALUES (";
    	int apptId = randomAppointmentIDGenerator();
    	insertString = insertString + apptId + ", "
    			+ cID + ", " + eIDs[dID] + ", " + statusInt;
    	insertString += ");";
    	
		return insertString;
	}


	/**
     * 
     * TODO
     * 
     * @param dID
     * @param sdf
     * @param calendar
     * @return
     */
    private static String getExpirationDate(int dID, SimpleDateFormat sdf, Calendar calendar) {

        // Format for adding days to a date. 
        // c.add(Calendar.DATE, 120);

        String endDate; 

        // Permit, add 1 year.
        if (dID == 1) {
            calendar.add(Calendar.DATE, 365); 
            endDate = sdf.format(calendar.getTime());
        } 
        
        // License, add 12 years.
        else if (dID == 2) {
            calendar.add(Calendar.DATE, 4380);
            endDate = sdf.format(calendar.getTime()); 
        } 
        
        // Vehicle Registration, add 1 year.
        else if (dID == 3) {
            calendar.add(Calendar.DATE, 365);
            endDate = sdf.format(calendar.getTime());
        } 
        
        // State ID, add 20 years.
        else {
            calendar.add(Calendar.DATE, 7300); 
            endDate = sdf.format(calendar.getTime());
        }

        return endDate; 
    }


    /**
     *  
     * TODO 
     * 
     * 
     * @param scanner
     * @return
     */
    private static int getServiceID(Scanner scanner) {


        System.out.println("(1) Permit (2) License (3) Vehicle Registration (4) State ID");
        System.out.println("\nPlease enter your type of appointment (1/2/3/4):");

        return Integer.parseInt(scanner.nextLine()); 
    }


    /**
     * TODO
     * 
     * @return
     */
    private static int randomCustomerIDGenerator() {
        
        // TODO 
        return -1; 

    }
    

    /**
     * TODO
     * 
     * @return
     */
    private static int randomTransactionIDGenerator() {

        // TODO
        return -1; 
    }
    
    /**
     * TODO
     * 
     * @return
     */
    private static int randomAppointmentIDGenerator() {
        
        // TODO 
        return -1; 

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
