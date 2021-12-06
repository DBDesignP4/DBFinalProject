CREATE TABLE department (
        dID numeric(1) Primary Key NOT NULL,
        dName VARCHAR(25) NOT NULL
);

CREATE TABLE transact (
        tID numeric(7) Primary Key NOT NULL,
        amount INT NOT NULL,
        DID INT NOT NULL,
        time DATE NOT NULL,
        FOREIGN KEY (DID) REFERENCES department(dID)
);

CREATE TABLE employee (
        eID numeric(5) Primary Key NOT NULL,
        fName VARCHAR(25) NOT NULL,
        lName VARCHAR(25) NOT NULL,
        DID INT,
        FOREIGN KEY (DID) REFERENCES department(dID)
);

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

CREATE TABLE appointment (
	aID INT Primary Key NOT NULL,
        CID INT,
        EID INT,
        status INT DEFAULT 0,
        FOREIGN KEY (CID) REFERENCES customer(cID),
        FOREIGN KEY (EID) REFERENCES employee(eID)
);

