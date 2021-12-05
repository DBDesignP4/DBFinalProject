CREATE TABLE transaction (
        tID INT(7) Primary Key NOT NULL,
        amount INT NOT NULL,
        DID INT NOT NULL,
        time DATETIME NOT NULL,
        FOREIGN KEY DID REFERENCES department.dID,
);

