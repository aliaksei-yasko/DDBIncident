CREATE TABLE Incident(
	RegistrationNumber INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
	IncidentDate DATE,
	Description VARCHAR(10000),
	Decision VARCHAR(50)
);

CREATE TABLE CriminalCase(
	CriminalNumber INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
	CriminalDate DATE,
	CriminalNote VARCHAR(10000),
        RegistrationNumber INTEGER
);

ALTER TABLE CriminalCase ADD FOREIGN KEY (RegistrationNumber)
REFERENCES Incident (RegistrationNumber) ON DELETE CASCADE;

CREATE TABLE Person(
	PersonNumber INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
	FirstName VARCHAR(20),
	LastName VARCHAR(20),
	PassportNumber INTEGER,
	Address VARCHAR(100),
	BurnDate DATE,
	CourtRate INTEGER
);

CREATE TABLE Status(
	StatusNumber INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
	StatusName VARCHAR(50)
);

CREATE TABLE Article(
	ArticleNumber INTEGER PRIMARY KEY,
	ArticleName VARCHAR(500),
	Discription VARCHAR(10000)
);

CREATE TABLE ArticleApply(
	ArticleNumber INTEGER,
	CriminalNumber INTEGER
);

ALTER TABLE ArticleApply ADD FOREIGN KEY (ArticleNumber)
REFERENCES Article (ArticleNumber) ON DELETE CASCADE;
ALTER TABLE ArticleApply ADD FOREIGN KEY (CriminalNumber)
REFERENCES CriminalCase (CriminalNumber) ON DELETE CASCADE;

CREATE TABLE IncidentInvolvment(
	PersonNumber INTEGER,
	RegistrationNumber INTEGER,
	StatusNumber INTEGER
);

ALTER TABLE IncidentInvolvment ADD FOREIGN KEY (PersonNumber)
REFERENCES Person (PersonNumber) ON DELETE CASCADE;
ALTER TABLE IncidentInvolvment ADD FOREIGN KEY (RegistrationNumber)
REFERENCES Incident(RegistrationNumber) ON DELETE CASCADE;
ALTER TABLE IncidentInvolvment ADD FOREIGN KEY (StatusNumber)
REFERENCES Status (StatusNumber) ON DELETE CASCADE;