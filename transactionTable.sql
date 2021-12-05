CREATE TABLE transaction (
        tID INT(7) Primary Key NOT NULL,
        AID INT,
        amount INT NOT NULL,
        DID INT NOT NULL,
        time INT(6) NOT NULL,
        FOREIGN KEY DID REFERENCES department.dID,
);

