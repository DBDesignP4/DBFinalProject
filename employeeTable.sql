CREATE TABLE employee (
        eID numeric(5) Primary Key NOT NULL,
        fName VARCHAR(25) NOT NULL,
        lName VARCHAR(25) NOT NULL,
        DID INT,
        FOREIGN KEY (DID) REFERENCES department(dID)
);

