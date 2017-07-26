CREATE TABLE kayttajat (
"userID" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
"name" VARCHAR(30),
"password" VARCHAR(30)
);

CREATE TABLE automaatti (
"ID" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
"cityID" INTEGER,
"city" VARCHAR(30),
"address" VARCHAR(30),
"name" VARCHAR(30),
"lat" FLOAT,
"lng" FLOAT,
"aukiolo" VARCHAR(50)
);

CREATE TABLE paketti (
"ID" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
"luokka",
"sisalto" VARCHAR(20),
"lahtopaikka",
"paatepaikka",
"userID"CHAR(30) NOT NULL,
FOREIGN KEY (userid) references kayttajat(userID),
FOREIGN KEY (luokka) references luokkalabel(luokka),
FOREIGN KEY (lahtopaikka) references automaatti(ID),
FOREIGN KEY (paatepaikka) references automaatti(ID)
);

CREATE TABLE luokka (
"luokkalabel" INTEGER PRIMARY KEY NOT NULL,
"sarkyvyys" INTEGER,
"etaisyys" VARCHAR (10),
"nopeus" INTEGER,
"korkeus" INTEGER,
"leveys" INTEGER,
"syvyys" INTEGER
);
