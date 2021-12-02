CREATE TABLE transaction (
        tID INT(5) Primary Key NOT NULL,
        amount INT NOT NULL,
        DID INT NOT NULL,
        time INT(6) NOT NULL,
        FOREIGN KEY DID REFERENCES department.dID,
);

