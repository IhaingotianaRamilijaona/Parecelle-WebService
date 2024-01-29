create database parecelle;
\c parecelle;

create table Users(
    idUser serial primary key,
    nomUser varchar(15),
    motDePasse varchar(15)
);

create table Terrain(
    idTerrain serial primary key,
    nombreParecelles int,
    idUser int,
    longitude decimal(9,6),
    latitude decimal(9,6),
    dateValidation DATE,
    descriptions TEXT,
    foreign key ( idUser ) references Users( idUser )
);

create table Culture(
    idCulture serial primary key,
    nomCulture varchar(15),
    prixCulture int 
);

create table Parecelle(
    idParecelle serial primary key,
    idTerrain int,
    taille int,
    rendement int,
    foreign key (idTerrain) references Terrain(idTerrain)
);

create table TerrainPhotos(
    idTerrain int,
    photoUrl varchar(30),
     foreign key (idTerrain) references Terrain(idTerrain)
);

create table ParecelleCulture(
    idParecelle int,
    idCulture int,
    foreign key (idParecelle) references Parecelle(idParecelle),
    foreign key (idCulture) references Culture(idCulture)
);

create table Historique(
    idParecelle int,
    idCulture int,
    nombrePlantation int,
    datePlantation DATE,
    nombreRecolte int,
    dateRecolte DATE,
    foreign key (idParecelle) references Parecelle(idParecelle),
    foreign key (idCulture) references Culture(idCulture)
);

create table messages(
    idEnvoyeur int,
    idReceveur int,
    contenu TEXT,
    foreign key (idEnvoyeur) references Users(idUser),
    foreign key (idReceveur) references Users(idUser)
);

create or replace view TerrainView as 
select 
t.*,
u.nomUser,
u.motDePasse,
p.idParecelle,
p.taille,
p.rendement
from Terrain t 
JOIN Users u on t.idUser = u.idUser
JOIN Parecelle p on p.idTerrain = t.idTerrain;

create or replace view HistoriqueView as 
select 
h.*,
p.taille,
p.rendement,
c.nomCulture,
c.prixCulture
from Historique h 
JOIN parecelle p ON h.idParecelle = p.idParecelle
JOIN Culture c ON h.idCulture = c.idCulture;

create or replace view ParecelleCultureView as 
select
tv.idUser,
tv.nomUser,
tv.motDePasse,
c.*,
p.*
from ParecelleCulture pc 
JOIN parecelle p ON pc.idParecelle = p.idParecelle
JOIN Culture c ON pc.idCulture = c.idCulture
JOIN TerrainView tv ON pc.idParecelle = tv.idParecelle;

create or replace view MessageView as 
select 
u1.nomUser as nomEnvoyeur,
u1.motDePasse as motDePasseEnvoyeur,
u2.nomUser as nomReceveur,
u2.motDePasse as motDePasseReceveur,
m.*
from messages m 
JOIN Users u1 on u1.idUser = m.idEnvoyeur
JOIN Users u2 on u2.idUser = m.idReceveur;

INSERT INTO Users (nomUser, motDePasse) VALUES
    ('John', 'password123'),
    ('Alice', 'abc123'),
    ('Bob', 'pass456');


INSERT INTO Culture (nomCulture, prixCulture) VALUES
    ('Blé', 10000),
    ('Maïs', 12000),
    ('Riz', 15000),
    ('Soja', 11000),
    ('Avoine', 13000);

INSERT INTO Terrain (idUser,nombreParecelles , longitude, latitude, dateValidation, descriptions) VALUES
    (1,4, 12.30, 56.70, '2023-01-01', 'Parcelle de terrain plat, idéal pour la construction résidentielle.'),
    (2,4, 23.40, 67.80, '2023-02-15', 'Terrain agricole avec sol fertile, adapté à la culture de céréales.'),
    (3,4, 34.60, 78.90, '2023-03-30', 'Zone industrielle avec accès facile aux principales voies de transport.');

INSERT INTO Parecelle (idTerrain, taille, rendement) VALUES

    (1, 120, 90),
    (1, 150, 110),
    (1, 100, 80),
    (1, 130, 95),

    (2, 140, 100),
    (2, 110, 85),
    (2, 160, 120),
    (2, 130, 90),

    (3, 130, 100),
    (3, 120, 95),
    (3, 170, 110),
    (3, 140, 105);

    
    INSERT INTO ParecelleCulture (idParecelle, idCulture) VALUES
    (1, 1),
    (1, 2),

    
    (2, 2),
    (2, 3),
    (2, 4),

    
    (3, 1),
    (3, 3),
    (3, 5),

    
    (4, 1),
    (4, 2),
    (4, 4),

    
    (5, 2),
    (5, 3),
    (5, 5),

    
    (6, 1),
    (6, 4),
    (6, 5),

        
    (7, 3),
    (7, 4),
    (7, 5),

    
    (8, 1),
    (8, 2),
    (8, 3),

    
    (9, 2),
    (9, 3),
    (9, 4),

    
    (10, 1),
    (10, 3),
    (10, 5),

    (11, 1),
    (11, 4),
    (11, 5),

    (12, 2),
    (12, 4),
    (12, 5);

INSERT INTO Historique (idParecelle, idCulture, nombrePlantation, datePlantation, nombreRecolte, dateRecolte) VALUES
    
    (1, 1, 500, '2023-03-15', 300, '2023-06-30'),
    (1, 2, 600, '2023-07-15', 400, '2023-10-31'),

    (2, 2, 700, '2023-04-20', 500, '2023-08-15'),
    (2, 3, 800, '2023-08-25', 600, '2023-11-30'),
    (2, 4, 900, '2024-01-10', 700, '2024-04-15'),

    (3, 1, 400, '2023-02-10', 200, '2023-05-20'),
    (3, 3, 500, '2023-06-01', 300, '2023-09-10'),
    (3, 5, 600, '2023-10-15', 400, '2024-01-31'),

    (4, 1, 450, '2023-03-01', 250, '2023-06-15'),
    (4, 2, 550, '2023-07-10', 350, '2023-10-25'),
    (4, 4, 650, '2023-11-05', 450, '2024-03-10'),

    (5, 2, 700, '2023-04-05', 400, '2023-08-20'),
    (5, 3, 800, '2023-08-15', 500, '2023-12-05'),
    (5, 5, 900, '2024-01-05', 600, '2024-04-25'),

    (6, 1, 600, '2023-02-25', 350, '2023-06-10'),
    (6, 4, 700, '2023-06-20', 450, '2023-10-05'),
    (6, 5, 800, '2023-10-15', 550, '2024-02-20');