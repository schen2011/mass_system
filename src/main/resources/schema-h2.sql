CREATE TABLE IF NOT EXISTS admin_users
(
   NAME VARCHAR(255) NOT NULL PRIMARY KEY,
   PASSWORD VARCHAR(255) NOT NULL,
);

CREATE TABLE IF NOT EXISTS BusRoute (
	ID INT NOT NULL PRIMARY KEY, 
	No INT, 
	NAME VARCHAR(255), 
);

CREATE TABLE IF NOT EXISTS TrainRoute (
	ID INT NOT NULL PRIMARY KEY, 
	No INT, 
	NAME VARCHAR(255), 
);


CREATE TABLE IF NOT EXISTS Bus (
	ID INT auto_increment PRIMARY KEY, 
    RouteID INT	REFERENCES busRoute(ID),
    CurrentStop INT,
    Passengers INT,
    Capacity INT,
    Speed INT,
    Direction VARCHAR(255),
);

CREATE TABLE IF NOT EXISTS Train (
	ID INT auto_increment PRIMARY KEY, 
    RouteID INT	REFERENCES trainRoute(ID),
    CurrentStop INT,
    Passengers INT,
    Capacity INT,
    Speed INT,
    Direction VARCHAR(255),
);

/*stop is solely location-wise, regardless whehter it is a train stop or bus stop. you can find that information out by checking which route has a stop at here*/

CREATE TABLE IF NOT EXISTS BusStop (
	ID INT auto_increment PRIMARY KEY, 
	NAME INT,
	Riders INT,
	X FLOAT,
	Y FLOAT,
	LENGTH FLOAT,
	SPEED FLOAT,
	TRAFFICSTATUS FLOAT,
    RouteID INT	REFERENCES busRoute(ID)
);

CREATE TABLE IF NOT EXISTS TrainStop (
	ID INT auto_increment PRIMARY KEY, 
	NAME INT, 
	Riders INT,
	X FLOAT,
	Y FLOAT,
	LENGTH FLOAT,
	SPEED INT,
	TRAFFICSTATUS INT,
    RouteID INT	REFERENCES trainRoute(ID)
);



CREATE TABLE IF NOT EXISTS Rider (
	ID INT auto_increment NOT NULL PRIMARY KEY, 
	NAME VARCHAR(255), 
	CURR_STOP_ID INT,
	DEST_STOP_ID INT,
);