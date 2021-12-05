CREATE TABLE customer (
	cID numeric(5) Primary Key NOT NULL,
	fName VARCHAR(25) NOT NULL,
	lName VARCHAR(25) NOT NULL,
	DID INT,
	TID INT,
	startDate DATE NOT NULL,
	endDate DATE NOT NULL,
	FOREIGN KEY (DID) REFERENCES department(dID),
	FOREIGN KEY (TID) REFERENCES transaction(tID)
);

