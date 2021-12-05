CREATE TABLE transaction (
        tID INT(5) Primary Key NOT NULL,
        amount INT NOT NULL,
        DID INT NOT NULL,
        time DATETIME NOT NULL,
        FOREIGN KEY DID REFERENCES department.dID,
);

