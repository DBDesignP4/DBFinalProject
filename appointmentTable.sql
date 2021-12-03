CREATE TABLE appointment (
	aID INT Primary Key NOT NULL,
        CID INT,
        EID INT,
        status BOOL DEFAULT 'f',
        FOREIGN KEY CID REFERENCES customer.cID,
        FOREIGN KEY EID REFERENCES employee.eID
);

