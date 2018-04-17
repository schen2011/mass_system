
create table admin_users
(
   name varchar(255) not null,
   password varchar(255) not null,
   primary key(name)
);


CREATE TABLE IF NOT EXISTS Type (ID INT PRIMARY KEY, NAME VARCHAR(255));

CREATE TABLE IF NOT EXISTS Vehicle (ID INT PRIMARY KEY, NAME VARCHAR(255));

CREATE TABLE IF NOT EXISTS Road (
	ID INT PRIMARY KEY, 
	NAME VARCHAR(255), 
	LENGTH DOUBLE,
	SPEED INT,
	TRAFFICSTATUS INT
);

CREATE TABLE IF NOT EXISTS Stop (
	ID INT PRIMARY KEY, 
	NAME VARCHAR(255), 
	X INT,
	Y INT,
	TypeID INT,
);

CREATE TABLE IF NOT EXISTS Route (
	ID INT PRIMARY KEY, 
	NAME VARCHAR(255), 
	NO INT(255), 
	X INT,
	Y INT,
	TypeID INT,
);

CREATE TABLE IF NOT EXISTS RouteStop (
	ID INT PRIMARY KEY, 
	ROUTEID INT,
	STOPID INT
);

CREATE TABLE IF NOT EXISTS StopRoad (
	ID INT PRIMARY KEY, 
	STOPID INT,
	ROADID INT
);


CREATE TABLE IF NOT EXISTS Rider (
	ID INT PRIMARY KEY, 
	NAME VARCHAR(255), 
	CURR_STOP_ID INT,
	DEST_STOP_ID INT,
);