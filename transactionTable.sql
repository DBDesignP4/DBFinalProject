CREATE TABLE transaction (
        tID numeric(7) Primary Key NOT NULL,
        amount INT NOT NULL,
        DID INT NOT NULL,
        time DATE NOT NULL,
        FOREIGN KEY (DID) REFERENCES department(dID)
);

