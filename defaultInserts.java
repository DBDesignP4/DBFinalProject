import java.util.ArrayList;
import java.util.List;

public class defaultInserts {

	public ArrayList<String> getDefaultCustomerInserts() {

		// "INSERT INTO customerTable VALUES (cID,fName,lName,dID,tID,startDate,endDate);",

		ArrayList<String> res = new ArrayList<>(
				List.of(
						"INSERT INTO customerTable VALUES (10000,'Amy','apple',1,1000001,2021-12-04,2033-12-01);",
						"INSERT INTO customerTable VALUES (10001,'Bob','branch',1,1000002,2021-12-04,2033-12-01);",
						"INSERT INTO customerTable VALUES (10001,'Bob','branch',2,1000003,2021-12-04,2033-12-01);",
						"INSERT INTO customerTable VALUES (10002,'Camila','carrot',3,1000004,2021-12-04,2033-12-01);",
						"INSERT INTO customerTable VALUES (10003,'Dylan','duck',4,1000005,2021-12-04,2033-12-01);",
						"INSERT INTO customerTable VALUES (10004,'Emily','eagle',1,1000006,2021-12-04,2033-12-01);",
						"INSERT INTO customerTable VALUES (10005,'Fran','fruit',3,1000007,2021-12-04,2033-12-01);",
						"INSERT INTO customerTable VALUES (10006,'Ginny','Grape',1,1000008,2021-12-04,2033-12-01);",
						"INSERT INTO customerTable VALUES (10007,'Hannah','Hat',4,1000009,2021-12-04,2033-12-01);",
						"INSERT INTO customerTable VALUES (10008,'Isabel','Ice',1,NULL,2021-12-04,2033-12-01);",
						"INSERT INTO customerTable VALUES (10009,'Janet','Jar',3,NULL,2021-12-04,2033-12-01);"));
		return res;
	}

	public ArrayList<String> getDefaultAppointmentInserts() {


		ArrayList<String> res = new ArrayList<>(
				List.of(
						"INSERT INTO appointmentTable VALUES (1, 10000, 1, 1);",
						"INSERT INTO appointmentTable VALUES (2, 10001, 1, 1);",
						"INSERT INTO appointmentTable VALUES (3, 10001, 2, 1);",
						"INSERT INTO appointmentTable VALUES (4, 10002, 3, 1);",
						"INSERT INTO appointmentTable VALUES (5, 10003, 4, 1);",
						"INSERT INTO appointmentTable VALUES (6, 10004, 1, 1);",
						"INSERT INTO appointmentTable VALUES (7, 10005, 3, 1);",
						"INSERT INTO appointmentTable VALUES (8, 10006, 1, 1);",
						"INSERT INTO appointmentTable VALUES (9, 10007, 4, 1);",
						"INSERT INTO appointmentTable VALUES (10, 10008, 1, 0);",
						"INSERT INTO appointmentTable VALUES (11, 10009, 3, 0);"));
		return res;
	}

	public ArrayList<String> getDefaultTransactionInserts() {
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

	public ArrayList<String> getDefaultDepartmentInserts() {
		ArrayList<String> res = new ArrayList<>(
				List.of(
						"INSERT INTO departmentTable VALUES (1,'Permit');",
						"INSERT INTO departmentTable VALUES (2,'License');",
						"INSERT INTO departmentTable VALUES (3,'VehicleReg');",
						"INSERT INTO departmentTable VALUES (4,'StateID');"));
		return res;
	}

	public ArrayList<String> getDefaultEmployeeInserts() {
		ArrayList<String> res = new ArrayList<>(
				List.of(
						"INSERT INTO employeeTable VALUES (1,'Gerry','G',1);",
						"INSERT INTO employeeTable VALUES (2,'Rubin','Y',2);",
						"INSERT INTO employeeTable VALUES (3,'Mihir','Y',3);",
						"INSERT INTO employeeTable VALUES (4,'Zach','F',4);"));
		return res;
	}

}
