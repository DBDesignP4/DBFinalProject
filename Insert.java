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
import java.util.Random;

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
    static ArrayList<String> transactionList = new ArrayList<String>();
    static ArrayList<String> DepartmentList = new ArrayList<String>();
    static ArrayList<String> employeeList = new ArrayList<String>();

    static int appointmentCounter = 11; 
    
    public static void main(String[] args) {
    	defaultInserts inserts = new defaultInserts();
    	populateDefaultData(inserts);

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
            int stat = getStatus();
	        if (input.equals("Y")) {
	
	            System.out.println("Please enter your cID: ");
	            int cID = Integer.parseInt(scanner.nextLine());
	            
	            int dID = getServiceID(scanner);

                // TODO 
                //      create global Hashmap <int, List<int>> {10000: [0, 0, 0, 0]}
                //      if dID returns 2 
                //          check 


                // TODO 
                //      updating when license expires in Hashmap 
	
	            String newApointmentSQL = createAppointment(dID, cID, stat);
	            
	            System.out.println(newApointmentSQL);

                appointmentList.add(newApointmentSQL);
	           
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
	            String newCustomerSQL;
	            if(stat==1)
	            	newCustomerSQL = createCustomer(cID, fName, lName, dID, tID ,startDate, endDate);
	            else 
	            	newCustomerSQL = createCustomer(cID, fName, lName, dID, -1 ,startDate, endDate);

	            String newApointmentSQL = createAppointment(dID, cID, stat);
	            
	            if(stat==1) {
	            	String newTransactionsSQL = createTransaction(dID, tID, startDate);
		            System.out.println(newTransactionsSQL);
		            transactionList.add(newTransactionsSQL);
	            }
	
	            System.out.println(newCustomerSQL);
	            System.out.println(newApointmentSQL);
	            
	            customerList.add(newCustomerSQL);
	            appointmentList.add(newApointmentSQL);
	            
	  
	        }
	        
	        WriteToSQLFile("insertCustomers.sql",1);
	        WriteToSQLFile("insertAppointments.sql",2);
	        WriteToSQLFile("insertTransactions.sql",3);
	        WriteToSQLFile("insertDepartments.sql",4);
	        WriteToSQLFile("insertEmployees.sql",5);


	    
	        
        }
    }


    private static void populateDefaultData(defaultInserts inserts) {
		// TODO Auto-generated method stub
		for(String s:inserts.getDefaultCustomerInserts()) {
			customerList.add(s);
		}
		
		for(String s:inserts.getDefaultAppointmentInserts()) {
			appointmentList.add(s);
		}
		
		for(String s:inserts.getDefaultTransactionInserts()) {
			transactionList.add(s);
		}
		
		for(String s:inserts.getDefaultDepartmentInserts()) {
			DepartmentList.add(s);
		}
		
		for(String s:inserts.getDefaultEmployeeInserts()) {
			employeeList.add(s);
		}
	}
    
    
    private static void WriteToSQLFile(String SqlFileName, int table) {
    	 File SQL = new File(SqlFileName);
		    prevFileRemover(SQL);
	        FileOutputStream fos = null;
	        try {
	        	fos = new FileOutputStream(SQL);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			};
	        insertIntoSqlFile(fos,table);

    }


	private static void insertIntoSqlFile(FileOutputStream fos, int input) {
		// TODO Auto-generated method stub
    	ArrayList<String> ar = null;
    	if(input == 1) {
    		ar = customerList;
    	}else if(input == 2) {
    		ar = appointmentList;
    	}else if(input == 3) {
    		ar = transactionList;
    	}else if(input == 4) {
    		ar = DepartmentList;
    	}else {
    		ar = employeeList;
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
    		
    	String insertString = "INSERT INTO customer VALUES (";
    	insertString = insertString + cID + ", '" + fName + "', '" + lName + "', " +
    					dID + ", "; 
    	if(tID != -1) {
    		insertString += tID;
    	} else {
    		insertString += "NULL";
    	}
    	insertString += ", TO_DATE('" + startDate + "','YYYY/MM/DD'), TO_DATE('" + endDate;
    	insertString += "','YYYY/MM/DD'));";
		return insertString;
	}


    /**
     * 
     * @param dID
     * @param cID
     * @param status
     * @return
     */
	private static String createAppointment(int dID, int cID, int statusInt) {
    	String insertString = "INSERT INTO appointment VALUES (";
    	int apptId = AppointmentIDGenerator();
    	insertString = insertString + apptId + ", "
    			+ cID + ", " + eIDs[dID] + ", " + statusInt;
    	insertString += ");";
    	
		return insertString;
	}
	
	
	private static String createTransaction(int dID, int tID, String startDate) {
		int amount = 0;
		if (dID == 1){
			amount = 7;
		} else if (dID == 2) {
			amount = 25;
		} else if(dID == 3) {
			amount = 100;
		} else if(dID == 4) {
			amount = 12;
		}
    	String insertString = "INSERT INTO transact VALUES (";
    	insertString = insertString + tID + ", " + amount + ", " + dID + ", TO_DATE('"
    			+ startDate;
    	insertString += "','YYYY/MM/DD'));";
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
     * randomCustomerIDGenerator: Creates a random 5 digit number
     * for customerID (cID)
     * 
     * @return
     */
    private static int randomCustomerIDGenerator() {
        Random r = new Random();
        int lowBound = 10000;
        int highBound = 100000;
        
        int result = r.nextInt(highBound - lowBound) + lowBound;
    	

        return result;
    }
    

    /**
     * randomTransactionIDGenerator: 7 digit ID
     * 
     * @return
     */
    private static int randomTransactionIDGenerator() {
        Random r = new Random();
        int lowBound = 1000000;
        int highBound = 10000000;
        
        int result = r.nextInt(highBound - lowBound) + lowBound;
    	

        return result;
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
    
    
    /**
     * TODO
     * 
     * @return
     */
    private static int AppointmentIDGenerator() {
        
        return appointmentCounter + 1; 

    }


    /**
     * Helper function called from main to get a random number to decide whether
     * or not a customer passed their appointment. Since we are deciding whether they 
     * will pass their appointment or not, they way we went about implementation is:
     *     
     *      We want to give all the users a 80 percent chance of passing their tests. 
     *      So we are going to get a random number form (0,10) and if the number is less than 
     *      8 than the appointment is successful. Other wise they will fail their tests. 
     * @return
     */
    private static int getStatus() {

           Random r = new Random();
           int highBound = 10; 
           int result = r.nextInt(highBound); 

           // O - 7 == PASSED TEST
           if (result < 7) {
               return 1; 
           } 
           
           // 8 & 9 == FAILED TEST
           else {
               return 0; 
           }
    }
    
}
