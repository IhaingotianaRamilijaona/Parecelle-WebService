create database parecelle;
\c parecelle;

create table User(
    idUser serial primary key,
    nomUser varchar(15),
    motDePasse varchar(15)
);

create table Terrain(
    idTerrain serial primary key,
    longitude decimal(2,2),
    longitude decimal(2,2),
    dateValidation DATE,
    descriptions TEXT
);

create table Culture(
    idCulture serial primary key,
    nomCulture varchar(15),
    prixCulture int 
);

create table Parecelle(
    idParecelle serial primary key,
    taille int,
    rendement int
);

create table UserTerrain(
    idUser int,
    idTerrain int,
    foreign key (idUser) references User(idUser),
    foreign key (idTerrain) references Terrain(idTerrain)
);

create table TerrainParecelle(
    idTerrain int,
    idParecelle int,
    foreign key (idTerrain) references Terrain(idTerrain),
    foreign key (idParecelle) references Parecelle(idParecelle)
);

create table TerrainPhotos{
    idTerrain int,
    photoUrl varchar(30),
     foreign key (idTerrain) references Terrain(idTerrain)
};

create table ParecelleCulture(
    idParecelle int,
    idCulture int,
    foreign key (idParcelle) references Parcelle(idParcelle),
    foreign key (idCulture) references Culture(idCulture)
);

create table Historique{
    idParcelle int,
    idCulture int,
    nombrePlantation int,
    datePlantation DATE,
    nombreRecolte int,
    dateRecolte DATE,
    foreign key (idParcelle) references Parcelle(idParcelle),
    foreign key (idCulture) references Culture(idCulture)
};

create table messages{
    idEnvoyeur int,
    idReceveur int,
    foreign key (idEnvoyeur) references User(idEnvoyeur),
    foreign key (idReceveur) references User(idReceveur)
}