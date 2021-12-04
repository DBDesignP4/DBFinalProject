CREATE TABLE appointment (
	aID INT Primary Key NOT NULL,
        CID INT,
        EID INT,
        status INT NOT NULL DEFAULT 0,
        FOREIGN KEY CID REFERENCES customer.cID,
        FOREIGN KEY EID REFERENCES employee.eID
);

