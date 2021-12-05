import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
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
    static ArrayList<String> customerList = new ArrayList<String>();
    static ArrayList<String> appointmentList = new ArrayList<String>();

    
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("----------Welcome to Program 4 Data Creator----------\n");

        while(1==1) {
        	System.out.println("\nPress 1 to run the program, 0 to exit");
        	String run = scanner.nextLine();
        	if(run.equals("0")) {
        		System.out.println("Thanks for using Prog4. Exiting...");
        		System.exit(0);
        	} 
            System.out.println("Do you have an ID? (Y/N)");
	        boolean haveId = false; 
	        String input = scanner.nextLine().toUpperCase();
	
	        if (input.equals("Y")) {
	
	            System.out.println("Please enter your cID: ");
	            int cID = Integer.parseInt(scanner.nextLine());
	            
	            int dID = getServiceID(scanner);
	
	            String newApointmentSQL = createAppointment(dID, cID, false);
	            
	            System.out.println(newApointmentSQL);
	           
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
	
	            customerList.add(newCustomerSQL);
	            appointmentList.add(newApointmentSQL);
	            
	            
	            
	        }
	        
	        File customersSQL = new File("insertCustomers.sql");
		    prevFileRemover(customersSQL);
	        FileOutputStream fosCSQL = null;
	        try {
	        	fosCSQL = new FileOutputStream(customersSQL);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			};
	        insertIntoSqlFile(fosCSQL,1);

	        
	        File appointmentsSQL = new File("insertAppointment.sql");
		    prevFileRemover(appointmentsSQL);
	        FileOutputStream fosASQL = null;
	        try {
	        	fosASQL = new FileOutputStream(appointmentsSQL);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			};
	        insertIntoSqlFile(fosASQL,2);

	        
        }
    }


    private static void insertIntoSqlFile(FileOutputStream fos, int input) {
		// TODO Auto-generated method stub
    	ArrayList<String> ar = null;
    	if(input == 1) {
    		ar = customerList;
    	}else if(input == 2) {
    		ar = appointmentList;
    	}
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
    	for(String s:ar) {
			try {
				s += "\n";
				bw.write(s);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
		try {
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
    
	/**
	 * prevFileRemover: removes a file if it exists already
	 * @param file
	 */
	private static void prevFileRemover(File file) {
		 if(file.exists())
	        	file.delete();
	}
	


	/**
     * 
     * @param cID
     * @param fName
     * @param lName
     * @param dID
     * @param tID
     * @param startDate
     * @param endDate
     * @return
     */
    private static String createCustomer(int cID, String fName, String lName, 
    		int dID, int tID, String startDate, String endDate) {
    	String insertString = "INSERT INTO customerTable VALUES (";
    	insertString = insertString + cID + ", '" + fName + "', '" + lName + "', " +
    					dID + ", " + tID + ", " + startDate + ", " + endDate;
    	insertString += ");";
		return insertString;
	}


    /**
     * 
     * @param dID
     * @param cID
     * @param status
     * @return
     */
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
