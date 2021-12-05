import java.util.ArrayList;
import java.util.List;

public class defaultInserts {
	
	
	public ArrayList<String> geDefaultCustomerInserts(){
		ArrayList<String> res = new ArrayList<>(
	            List.of(
	            		"INSERT INTO customerTable VALUES (10000,'Amy','apple',1,999901,2021-12-04,2033-12-01);",
	            		"INSERT INTO customerTable VALUES (10001,'Bob','branch',1,999902,DATEFROM,2033-12-01);",
	            		"INSERT INTO customerTable VALUES (10001,'Bob','branch',2,999903,DATEFROM,2033-12-01);",
	            		"INSERT INTO customerTable VALUES (10002,'Camila','carrot',3,999904,DATEFROM,2033-12-01);",
	            		"INSERT INTO customerTable VALUES (10003,'Dylan','duck',4,999905,DATEFROM,2033-12-01);",
	            		"INSERT INTO customerTable VALUES (10004,'Emily','eagle',1,999906,DATEFROM,2033-12-01);",
	            		"INSERT INTO customerTable VALUES (10005,'Fran','fruit',3,999907,DATEFROM,2033-12-01);",
	            		"INSERT INTO customerTable VALUES (10006,'Ginny','Grape',1,999908,DATEFROM,2033-12-01);",
	            		"INSERT INTO customerTable VALUES (10007,'Hannah','Hat',4,999909,DATEFROM,2033-12-01);",
	            		"INSERT INTO customerTable VALUES (10008,'Isabel','Ice',1,999910,DATEFROM,2033-12-01);",
	            		"INSERT INTO customerTable VALUES (10009,'Janet','Jar',3,999911,DATEFROM,2033-12-01);"));
		return res;
	}
	
	public ArrayList<String> getDefaultAppointmentInserts(){
		ArrayList<String> res = new ArrayList<>(
	            List.of(
	            		"INSERT INTO appointmentTable VALUES (1, 10000, 1, 1);",
	            		"INSERT INTO appointmentTable VALUES (2, 10001, 1, 1);",
	            		"INSERT INTO appointmentTable VALUES (3, 10002, 2, 1);",
	            		"INSERT INTO appointmentTable VALUES (4, 10003, 3, 1);",
	            		"INSERT INTO appointmentTable VALUES (5, 10004, 4, 1);",
	            		"INSERT INTO appointmentTable VALUES (6, 10005, 1, 1);",
	            		"INSERT INTO appointmentTable VALUES (7, 10006, 3, 1);",
	            		"INSERT INTO appointmentTable VALUES (8, 10007, 1, 1);",
	            		"INSERT INTO appointmentTable VALUES (9, 10008, 4, 1);",
	            		"INSERT INTO appointmentTable VALUES (10, 10009, 1, 0);",
	            		"INSERT INTO appointmentTable VALUES (11, 10010, 3, 0);"));
		return res;
	}
	
	public ArrayList<String> geDefaultTransactionInserts(){
		ArrayList<String> res = new ArrayList<>(
	            List.of(
	            		"INSERT INTO transactionTable VALUES (1000001,7,1,2021-12-04);",
	            		"INSERT INTO transactionTable VALUES (1000002,7,1,2021-12-04);",
	            		"INSERT INTO transactionTable VALUES (1000003,25,2,2021-12-04);",
	            		"INSERT INTO transactionTable VALUES (1000004,100,3,2021-12-04);",
	            		"INSERT INTO transactionTable VALUES (1000005,12,4,2021-12-04);",
	            		"INSERT INTO transactionTable VALUES (1000006,7,1,2021-12-04);",
	            		"INSERT INTO transactionTable VALUES (1000007,100,3,2021-12-04);",
	            		"INSERT INTO transactionTable VALUES (1000008,7,1,2021-12-04);",
	            		"INSERT INTO transactionTable VALUES (1000009,12,4,2021-12-04);"));
		return res;
	}
	
	public ArrayList<String> geDefaultDepartmentInserts(){
		ArrayList<String> res = new ArrayList<>(
	            List.of(
	            		"INSERT INTO departmentTable VALUES (1,'Permit');",
	            		"INSERT INTO departmentTable VALUES (2,'License');",
	            		"INSERT INTO departmentTable VALUES (3,'VehicleReg');",
	            		"INSERT INTO departmentTable VALUES (4,'StateID');"));
		return res;
	}
	
	public ArrayList<String> geDefaultEmployeeInserts(){
		ArrayList<String> res = new ArrayList<>(
	            List.of(
	            		"INSERT INTO employeeTable VALUES (1,'Gerry','G',1);",
	            		"INSERT INTO employeeTable VALUES (2,'Rubin','Y',2);",
	            		"INSERT INTO employeeTable VALUES (3,'Mihir','Y',3);",
	            		"INSERT INTO employeeTable VALUES (4,'Zach','F',4);"));
		return res;
	}
	
	
	

}
