use DB29

DROP TABLE Java_Agores;
DROP TABLE Java_Customers;
DROP TABLE Java_Offered;

CREATE TABLE Java_Customers 
			(Name VARCHAR(30),
			Mail VARCHAR(50),
			PRIMARY KEY(Name,Mail));

CREATE TABLE Java_Agores
			(Name VARCHAR(30),
			Mail VARCHAR(50),
			MonthId INT,
			Fees DECIMAL(7,2),
			FOREIGN KEY (Name,Mail) REFERENCES Java_Customers);
CREATE TABLE Java_Offered
			(Name VARCHAR(30),
			Mail VARCHAR(50),
			PRIMARY KEY(Name));


INSERT INTO Java_Customers VALUES('Ioanna Martini','ioannamartini@yahoo.gr');
INSERT INTO Java_Customers VALUES('Niki Boura','nikiboura@gmail.com');
INSERT INTO Java_Customers VALUES('Antonis Kuriakou','AntonisK@hotmail.com');
INSERT INTO Java_Customers VALUES('Melina Papakosta','MelinaPap@hotmail.com');
INSERT INTO Java_Customers VALUES('Georgios Papadopoulos','GeorgeP@gmail.com');

INSERT INTO Java_Agores VALUES('Ioanna Martini','ioannamartini@yahoo.gr',1,850.5);
INSERT INTO Java_Agores VALUES('Ioanna Martini','ioannamartini@yahoo.gr',2,868.78);
INSERT INTO Java_Agores VALUES('Ioanna Martini','ioannamartini@yahoo.gr',3,797);
INSERT INTO Java_Agores VALUES('Ioanna Martini','ioannamartini@yahoo.gr',4,833.64);
INSERT INTO Java_Agores VALUES('Ioanna Martini','ioannamartini@yahoo.gr',5,814.85);
INSERT INTO Java_Agores VALUES('Ioanna Martini','ioannamartini@yahoo.gr',6,803);
INSERT INTO Java_Agores VALUES('Ioanna Martini','ioannamartini@yahoo.gr',7,605.6);
INSERT INTO Java_Agores VALUES('Ioanna Martini','ioannamartini@yahoo.gr',8,742);
INSERT INTO Java_Agores VALUES('Ioanna Martini','ioannamartini@yahoo.gr',9,892.5);
INSERT INTO Java_Agores VALUES('Ioanna Martini','ioannamartini@yahoo.gr',10,861.2);
INSERT INTO Java_Agores VALUES('Ioanna Martini','ioannamartini@yahoo.gr',11,860);
INSERT INTO Java_Agores VALUES('Ioanna Martini','ioannamartini@yahoo.gr',12,883.6);

INSERT INTO Java_Agores VALUES('Antonis Kuriakou','AntonisK@hotmail.com',2,520.5);
INSERT INTO Java_Agores VALUES('Antonis Kuriakou','AntonisK@hotmail.com',3,489.65);
INSERT INTO Java_Agores VALUES('Antonis Kuriakou','AntonisK@hotmail.com',4,568.9);
INSERT INTO Java_Agores VALUES('Antonis Kuriakou','AntonisK@hotmail.com',5,477.7);
INSERT INTO Java_Agores VALUES('Antonis Kuriakou','AntonisK@hotmail.com',6,536);
INSERT INTO Java_Agores VALUES('Antonis Kuriakou','AntonisK@hotmail.com',7,601.5);
INSERT INTO Java_Agores VALUES('Antonis Kuriakou','AntonisK@hotmail.com',9,596);
INSERT INTO Java_Agores VALUES('Antonis Kuriakou','AntonisK@hotmail.com',10,458.2);
INSERT INTO Java_Agores VALUES('Antonis Kuriakou','AntonisK@hotmail.com',11,597.3);
INSERT INTO Java_Agores VALUES('Antonis Kuriakou','AntonisK@hotmail.com',12,405.8);

INSERT INTO Java_Agores VALUES('Niki Boura','nikiboura@gmail.com',1,802.5);
INSERT INTO Java_Agores VALUES('Niki Boura','nikiboura@gmail.com',2,798);
INSERT INTO Java_Agores VALUES('Niki Boura','nikiboura@gmail.com',3,775.8);
INSERT INTO Java_Agores VALUES('Niki Boura','nikiboura@gmail.com',4,764);
INSERT INTO Java_Agores VALUES('Niki Boura','nikiboura@gmail.com',5,684.6);
INSERT INTO Java_Agores VALUES('Niki Boura','nikiboura@gmail.com',6,872);
INSERT INTO Java_Agores VALUES('Niki Boura','nikiboura@gmail.com',7,760.6);
INSERT INTO Java_Agores VALUES('Niki Boura','nikiboura@gmail.com',8,634);
INSERT INTO Java_Agores VALUES('Niki Boura','nikiboura@gmail.com',9,503.6);
INSERT INTO Java_Agores VALUES('Niki Boura','nikiboura@gmail.com',10,648);
INSERT INTO Java_Agores VALUES('Niki Boura','nikiboura@gmail.com',11,862.5);
INSERT INTO Java_Agores VALUES('Niki Boura','nikiboura@gmail.com',12,725.1);

INSERT INTO Java_Agores VALUES('Melina Papakosta','MelinaPap@hotmail.com',1,305.5);
INSERT INTO Java_Agores VALUES('Melina Papakosta','MelinaPap@hotmail.com',2,365);
INSERT INTO Java_Agores VALUES('Melina Papakosta','MelinaPap@hotmail.com',3,590.3);
INSERT INTO Java_Agores VALUES('Melina Papakosta','MelinaPap@hotmail.com',5,458.2);
INSERT INTO Java_Agores VALUES('Melina Papakosta','MelinaPap@hotmail.com',6,354.2);
INSERT INTO Java_Agores VALUES('Melina Papakosta','MelinaPap@hotmail.com',8,411.48);
INSERT INTO Java_Agores VALUES('Melina Papakosta','MelinaPap@hotmail.com',9,395.5);
INSERT INTO Java_Agores VALUES('Melina Papakosta','MelinaPap@hotmail.com',10,451);
INSERT INTO Java_Agores VALUES('Melina Papakosta','MelinaPap@hotmail.com',11,388.8);
INSERT INTO Java_Agores VALUES('Melina Papakosta','MelinaPap@hotmail.com',12,305.9);

INSERT INTO Java_Agores VALUES('Georgios Papadopoulos','GeorgeP@gmail.com',1,321);
INSERT INTO Java_Agores VALUES('Georgios Papadopoulos','GeorgeP@gmail.com',2,289.2);
INSERT INTO Java_Agores VALUES('Georgios Papadopoulos','GeorgeP@gmail.com',3,365.2);
INSERT INTO Java_Agores VALUES('Georgios Papadopoulos','GeorgeP@gmail.com',4,158.3);
INSERT INTO Java_Agores VALUES('Georgios Papadopoulos','GeorgeP@gmail.com',5,258.6);
INSERT INTO Java_Agores VALUES('Georgios Papadopoulos','GeorgeP@gmail.com',6,148.2);
INSERT INTO Java_Agores VALUES('Georgios Papadopoulos','GeorgeP@gmail.com',7,452);
INSERT INTO Java_Agores VALUES('Georgios Papadopoulos','GeorgeP@gmail.com',9,269.4);
INSERT INTO Java_Agores VALUES('Georgios Papadopoulos','GeorgeP@gmail.com',10,328.2);
INSERT INTO Java_Agores VALUES('Georgios Papadopoulos','GeorgeP@gmail.com',11,365.45);
INSERT INTO Java_Agores VALUES('Georgios Papadopoulos','GeorgeP@gmail.com',12,297.67);
