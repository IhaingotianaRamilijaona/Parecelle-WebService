package com.parecelle.objects.Tany;

import java.sql.*;
import java.util.*;
import java.util.Date;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.google.gson.Gson;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.parecelle.objects.connect.Connect;
import com.parecelle.objects.connect.ConnectMongoDb;
import com.parecelle.objects.information.User;

public class Terrain {
    int idTerrain;
    List<Parecelle> parecelles;
    User proprietaire;
    int nombreParecelles;
    String Description;
    Double latitude;
    Double longitude;
    Date dateValidaton;
    List<String> UrlPhoto;

    public Terrain(){}

    public Terrain(int idTerrain, User proprietaire, int nombreParecelles, String description, Double latitude,
            Double longitude) {
        this.idTerrain = idTerrain;
        this.proprietaire = proprietaire;
        this.nombreParecelles = nombreParecelles;
        this.Description = description;
        this.latitude = latitude;
        this.longitude = longitude;
    }
    public Terrain(int idTerrain, List<Parecelle> parecelles, User proprietaire, int nombreParecelles,
        String description, Double latitude, Double longitude, Date dateValidaton, List<String> urlPhoto) {
        this.idTerrain = idTerrain;
        this.parecelles = parecelles;
        this.proprietaire = proprietaire;
        this.nombreParecelles = nombreParecelles;
        this.Description = description;
        this.latitude = latitude;
        this.longitude = longitude;
        this.dateValidaton = dateValidaton;
        this.UrlPhoto = urlPhoto;
    }

    public int getIdTerrain() {
        return idTerrain;
    }

    public void setIdTerrain(int idTerrain) {
        this.idTerrain = idTerrain;
    }

    public List<Parecelle> getParecelles() {
        return parecelles;
    }

    public void setParecelles(List<Parecelle> parecelles) {
        this.parecelles = parecelles;
    }

    public User getProprietaire() {
        return proprietaire;
    }

    public void setProprietaire(User proprietaire) {
        this.proprietaire = proprietaire;
    }

    public int getNombreParecelles() {
        return nombreParecelles;
    }

    public void setNombreParecelles(int nombreParecelles) {
        this.nombreParecelles = nombreParecelles;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public List<String> getUrlPhoto() {
        return UrlPhoto;
    }

    public void setUrlPhoto(List<String> urlPhoto) {
        UrlPhoto = urlPhoto;
    }

    public Date getDateValidaton() {
        return dateValidaton;
    }

    public void setDateValidaton(Date dateValidaton) {
        this.dateValidaton = dateValidaton;
    }

    public void valider()throws Exception {

        MongoClient mongoClient = ConnectMongoDb.getConnection();
        MongoDatabase mongoDatabase = mongoClient.getDatabase("parecelle");
        MongoCollection<Terrain> collection = mongoDatabase.getCollection("terrain", Terrain.class);
        // Gson gson = new Gson();
        // String json = gson.toJson(this);
        // Bson bson = Filters.eq(json);

        Bson filter = Filters.and(
            Filters.eq("longitude", this.longitude),
            Filters.eq("latitude", this.latitude),
            Filters.eq("description", this.Description),
            Filters.eq("nombreParecelles", this.nombreParecelles),
            Filters.eq("proprietaire.idUser", this.proprietaire.getIdUser())
        );

        collection.deleteOne(filter);
        mongoClient.close();

        Connection connection = Connect.getConnection();

        String sql = "INSERT INTO Terrain (longitude,latitude,descriptions,nombreparecelles,iduser,datevalidation) values ("+this.longitude+","+this.latitude+",'"+this.Description+"',"+this.nombreParecelles+","+this.proprietaire.getIdUser()+",NOW() );";
        Statement state = connection.createStatement();
        state.executeUpdate(sql);

        sql = "SELECT MAX(idTerrain) as idTerrain FROM Terrain";
        Statement state1 = connection.createStatement();
        ResultSet result = state1.executeQuery(sql);
        result.next();
        this.setIdTerrain(result.getInt("idTerrain"));

        for (Parecelle parecelle : parecelles) {
            parecelle.insert(this.idTerrain);
        }

        Statement state2 = connection.createStatement();
        for (String photo : UrlPhoto) {
            sql = "INSERT INTO TerrainPhotos values ("+this.idTerrain+",'"+photo+"') ";
            state2.executeUpdate(sql);   
        }


        state.close();
        state1.close();
        state2.close();

        connection.close();
    }

    public void insert()throws Exception {
        MongoClient mongoClient = ConnectMongoDb.getConnection();
        MongoDatabase mongoDatabase = mongoClient.getDatabase("parecelle");
        MongoCollection<Terrain> collection = mongoDatabase.getCollection("terrain", Terrain.class);
        collection.insertOne(this);
        mongoClient.close();
    }

    public void setParecellesOfTerrain()throws Exception{
        Connection connection = Connect.getConnection();
        List<Parecelle> listParecelle = new ArrayList<Parecelle>();
        Statement state = connection.createStatement();
        String sql = "SELECT * FROM TerrainView where idTerrain = "+this.idTerrain;
        ResultSet results = state.executeQuery(sql);
        while (results.next()) {
            Parecelle parecelle = new Parecelle();
            parecelle.setProprietaire(this.proprietaire);
            parecelle.setIdParecelle(results.getInt("idParecelle"));
            parecelle.setTaille(results.getInt("taille"));
            parecelle.setRendement(results.getInt("rendement"));
            parecelle.setHistoriquesOfParecelle();
            parecelle.setCultureOfParecelle();
            listParecelle.add(parecelle);
        }
        state.close();
        connection.close();
        this.setParecelles(listParecelle);
    }

    public void setPhotoOfTerrain()throws Exception{
        Connection connection = Connect.getConnection();
        List<String> listPhotos = new ArrayList<String>();
        Statement state = connection.createStatement();
        String sql = "SELECT * FROM TerrainPhotos where idTerrain = "+this.idTerrain;
        ResultSet results = state.executeQuery(sql);
        while (results.next()) {
            listPhotos.add(results.getString("photoUrl"));
        }
        state.close();
        connection.close();
        this.setUrlPhoto(listPhotos);
    }
}
