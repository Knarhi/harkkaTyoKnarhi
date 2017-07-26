CREATE TABLE kayttajat (
"userID" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
"name" VARCHAR(30),
"password" VARCHAR(30),
"active" BOOLEAN CHECK(active = 0 OR active = 1)
);

CREATE TABLE automaatti (
"ID" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
"cityID" INTEGER,
"city" VARCHAR(30),
"address" VARCHAR(30),
"name" VARCHAR(30),
"lat" REAL,
"lng" REAL,
"aukiolo" VARCHAR(50)
);

CREATE TABLE lahetys (
"lahetysID" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
"lahtopaikka" VARCHAR,
"paatepaikka" VARCHAR,
"pakettiID" INTEGER,
"status" VARCHAR(20),
"matka" REAL,
FOREIGN KEY (pakettiID) references paketti(pakettiID),
FOREIGN KEY (lahtopaikka) references automaatti(name),
FOREIGN KEY (paatepaikka) references automaatti(name)
);

CREATE TABLE paketti (
"pakettiID" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
"userID" INTEGER,
"esineID" INTEGER,
"luokkalabel" INTEGER,
FOREIGN KEY (userID) references kayttajat(userID),
FOREIGN KEY (esineID) references esine(esineID),
FOREIGN KEY (luokkalabel) references luokka(luokkalabel)
);

CREATE TABLE luokka (
"luokkalabel" INTEGER PRIMARY KEY NOT NULL,
"sarkyvyys" INTEGER,
"paino" INTEGER,
"etaisyys" REAL,
"nopeus" INTEGER,
"korkeus" INTEGER,
"leveys" INTEGER,
"syvyys" INTEGER
);

CREATE TABLE esine (
"esineID" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
"name" VARCHAR(30),
"paino" INTEGER,
"korkeus" INTEGER,
"leveys" INTEGER,
"syvyys" INTEGER,
"sarkyvyys" INTEGER
);

CREATE TABLE logs (
"logID" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
"userID" INTEGER NOT NULL,
Timestamp DATETIME DEFAULT CURRENT_TIMESTAMP,
"log" VARCHAR,
FOREIGN KEY (userID) references kayttajat(userID)
);

