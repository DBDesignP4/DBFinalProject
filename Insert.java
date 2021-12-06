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
 *         Class: CSC 460 Database Design
 *         Assignment: Prog3.java
 *         Instructor and TA names: Dr.Lester I. McCann, Sourav Mangla, Justin
 *         doo
 *         Description: Insert.java is the class that is being run before and
 *         after our main
 *         program Prog4.java. The main purpose of Insert.java is when a cutomer
 *         comes
 *         in to get a new order. The customer can be already an existing
 *         customer or
 *         can be completely new customer.
 * 
 *         When you run Insert.java, we ask you, the customer, if you were
 *         already assigned
 *         an ID from the past. If you were then you will input that ID,
 *         otherwise a new customer
 *         ID will be generated for you to keep for the future.
 *
 *         After that you will be asked what type of service you would like and
 *         we will take care
 *         of the rest for you.
 */

public class Insert {

    // global lists that are essential to our functionality.
    ArrayList<Integer> cIDs = new ArrayList<Integer>();
    static int[] eIDs = new int[] { 0, 1, 2, 3, 4 };
    static ArrayList<String> customerList = new ArrayList<String>();
    static ArrayList<String> appointmentList = new ArrayList<String>();
    static ArrayList<String> transactionList = new ArrayList<String>();
    static ArrayList<String> DepartmentList = new ArrayList<String>();
    static ArrayList<String> employeeList = new ArrayList<String>();

    // Counter for every appointment the user wants to attempt.
    static int appointmentCounter = 11;

    public static void main(String[] args) {

        // Default insertions made to better show query results.
        defaultInserts inserts = new defaultInserts();
        populateDefaultData(inserts);

        Scanner scanner = new Scanner(System.in);
        System.out.println("----------Welcome to Program 4 Data Creator----------\n");

        while (1 == 1) {

            System.out.println("\nPress 1 to run the program, 0 to exit");
            String run = scanner.nextLine();

            if (run.equals("0")) {

                System.out.println("Thanks for using Prog4. Exiting...");
                System.exit(0);

            }

            // First ask user if they have an existing account
            System.out.println("Do you have an ID? (Y/N)");
            boolean haveId = false;
            String input = scanner.nextLine().toUpperCase();
            int stat = getStatus();

            // If user has an existing account, then ask then for their ID.
            if (input.equals("Y")) {

                System.out.println("Please enter your cID: ");

                int cID = Integer.parseInt(scanner.nextLine()); // customerID
                int dID = getServiceID(scanner); // departmentID (1, 2, 3, 4)

                String newApointmentSQL = createAppointment(dID, cID, stat);
                appointmentList.add(newApointmentSQL);

            }

            // If user does not have an existing account we want to create a new
            // account for them.
            else if (input.equals("N")) {

                // Get fName
                System.out.println("Enter first name:");
                String fName = scanner.nextLine();

                // Get lName
                System.out.println("Enter last name:");
                String lName = scanner.nextLine();

                // Generate customerID
                int cID = randomCustomerIDGenerator();
                System.out.print("\nYour assigned cID number is:");
                System.out.println(cID);
                System.out.println("(Please save this number for future references)");
                System.out.println();

                // Get departmentID and transactionID
                int dID = getServiceID(scanner);
                int tID = randomTransactionIDGenerator();

                // Create startDate timestamp.
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(new Date());

                // Start date + End Date depending on department
                String startDate = sdf.format(calendar.getTime());
                String endDate = getExpirationDate(dID, sdf, calendar);
                String newCustomerSQL;

                // if status is 1, appointment was successful.
                if (stat == 1)
                    newCustomerSQL = createCustomer(cID, fName, lName, dID, tID, startDate, endDate);

                // if status is 0 then set transactionID to -1. (didn't pass their appointment)
                else
                    newCustomerSQL = createCustomer(cID, fName, lName, dID, -1, startDate, endDate);

                String newApointmentSQL = createAppointment(dID, cID, stat);

                if (stat == 1) {
                    String newTransactionsSQL = createTransaction(dID, tID, startDate);
                    transactionList.add(newTransactionsSQL);
                }

                customerList.add(newCustomerSQL);
                appointmentList.add(newApointmentSQL);

            }

            // Writing too our tables from our SQL files for all of our 
            // different tables depending on the user appoointment.
            WriteToSQLFile("insertCustomers.sql", 1);
            WriteToSQLFile("insertAppointments.sql", 2);
            WriteToSQLFile("insertTransactions.sql", 3);
            WriteToSQLFile("insertDepartments.sql", 4);
            WriteToSQLFile("insertEmployees.sql", 5);

        }
    }

    /**
     * Function called from main when we want to add all of our default inserts 
     * into the table before asking for all of the user input. 
     * 
     * 
     * @param inserts, defaultInserts 
     */
    private static void populateDefaultData(defaultInserts inserts) {

        for (String s : inserts.getDefaultCustomerInserts()) {
            customerList.add(s);
        }

        for (String s : inserts.getDefaultAppointmentInserts()) {
            appointmentList.add(s);
        }

        for (String s : inserts.getDefaultTransactionInserts()) {
            transactionList.add(s);
        }

        for (String s : inserts.getDefaultDepartmentInserts()) {
            DepartmentList.add(s);
        }

        for (String s : inserts.getDefaultEmployeeInserts()) {
            employeeList.add(s);
        }
    }


    /**
     * Helper function called from main to execute our writing to SQL file 
     * that is being used to put data into our already created and default
     * inserted tables. 
     * 
     * @param SqlFileName, String
     * @param table, int
     */
    private static void WriteToSQLFile(String SqlFileName, int table) {
        File SQL = new File(SqlFileName);
        prevFileRemover(SQL);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(SQL);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ;
        insertIntoSqlFile(fos, table);

    }


    /**
     * Function called from the function WriteToSQLFile(). 
     * @param fos, FileOutputSteam being writen. 
     * @param input, int
     */
    private static void insertIntoSqlFile(FileOutputStream fos, int input) {

        // Initialize an array to insert. 
        ArrayList<String> ar = null;

        // Find what type of list to input. 
        if (input == 1) {
            ar = customerList;
        } else if (input == 2) {
            ar = appointmentList;
        } else if (input == 3) {
            ar = transactionList;
        } else if (input == 4) {
            ar = DepartmentList;
        } else {
            ar = employeeList;
        }

        // Set our BufferedWriter and then write our strings to the writer. 
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

        for (String s : ar) {
            try {
                s += "\n";
                bw.write(s);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * prevFileRemover: removes a file if it exists already
     * 
     * @param file
     */
    private static void prevFileRemover(File file) {
        if (file.exists())
            file.delete();
    }

    /**
     * Function called from main after we have all of our customer information for a brand 
     * new customer. Function is not called when a customer already has a customer ID. 
     * 
     * What we do is just join the string up that we then send to to our SQL file. 
     * @param cID, int
     * @param fName, String
     * @param lName, String
     * @param dID, int
     * @param tID, int
     * @param startDate, String
     * @param endDate, String
     * @return String
     */
    private static String createCustomer(int cID, String fName, String lName,
            int dID, int tID, String startDate, String endDate) {

        String insertString = "INSERT INTO customer VALUES (";
        insertString = insertString + cID + ", '" + fName + "', '" + lName + "', " +
                dID + ", ";
        if (tID != -1) {
            insertString += tID;
        } else {
            insertString += "NULL";
        }
        insertString += ", TO_DATE('" + startDate + "','YYYY/MM/DD'), TO_DATE('" + endDate;
        insertString += "','YYYY/MM/DD'));";
        return insertString;
    }

    /**
     * Function called from main both when a user has an account and when creating a new user. 
     * What we do in this function is just create our string that is being sent to our 
     * SQL file to throw that appointment into our appointment table. 
     * 
     * @param dID, int
     * @param cID, int
     * @param statusInt, int
     * @return String
     */
    private static String createAppointment(int dID, int cID, int statusInt) {
        String insertString = "INSERT INTO appointment VALUES (";
        int apptId = AppointmentIDGenerator();
        insertString = insertString + apptId + ", "
                + cID + ", " + eIDs[dID] + ", " + statusInt;
        insertString += ");";

        return insertString;
    }

    /**
     * Function called from main when creating a new user. 
     * What we do in this function is just create our string that is being sent to our 
     * SQL file to throw that transaction into our transaction table. 
     * 
     * @param dID, int
     * @param tID, int
     * @param startDate, String
     * @return String 
     */
    private static String createTransaction(int dID, int tID, String startDate) {

        int amount = 0;

        // Set amount to be charged depending on the department. 
        if (dID == 1) {
            amount = 7;
        } else if (dID == 2) {
            amount = 25;
        } else if (dID == 3) {
            amount = 100;
        } else if (dID == 4) {
            amount = 12;
        }

        // Create and return string. 
        String insertString = "INSERT INTO transact VALUES (";
        insertString = insertString + tID + ", " + amount + ", " + dID + ", TO_DATE('"
                + startDate;
        insertString += "','YYYY/MM/DD'));";
        return insertString;

    }

    /**
     * 
     * Helper function called from main when we have the starting date of a appointment 
     * and we need to calculate the expiration date depending on the start date and the 
     * specific department. 
     * 
     * @param dID, int
     * @param sdf, SimpleDateFormat
     * @param calendar, Calender
     * @return String
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
     * Helper function called from main to get the type of service that a user 
     * wants. Just asking 1, 2, 3, 4 depending on the department. 
     * 
     * @param scanner, Scanner
     * @return int, departmentID
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
     * @return int
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
     * @return int
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
     * 
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
     * returns one plus the curret appointment number. 
     * 
     * @return int
     */
    private static int AppointmentIDGenerator() {

        appointmentCounter ++; return appointmentCounter; 
    }

    /**
     * Helper function called from main to get a random number to decide whether
     * or not a customer passed their appointment. Since we are deciding whether
     * they
     * will pass their appointment or not, they way we went about implementation is:
     * 
     * We want to give all the users a 80 percent chance of passing their tests.
     * So we are going to get a random number form (0,10) and if the number is less
     * than
     * 8 than the appointment is successful. Other wise they will fail their tests.
     * 
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
