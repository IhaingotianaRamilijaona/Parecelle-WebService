package com.parecelle.model;

import org.springframework.stereotype.Component;
import java.sql.*;
import java.sql.Date;
import java.util.*;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.parecelle.objects.Tany.*;
import com.parecelle.objects.connect.Connect;
import com.parecelle.objects.connect.ConnectMongoDb;
import com.parecelle.objects.information.*;

@Component
public class TerrainModel {

    public List<Terrain> getListTerrainNonValideByUser(int idUser)throws Exception{      
        MongoClient mongoClient = ConnectMongoDb.getConnection();
        MongoDatabase mongoDatabase = mongoClient.getDatabase("parecelle");
        MongoCollection<Terrain> collection = mongoDatabase.getCollection("terrain", Terrain.class);
        List<Terrain> listTerrain = new ArrayList<Terrain>();
        MongoCursor<Terrain> cursor = collection.find(Filters.eq("proprietaire.idUser" , idUser)).iterator();
        while (cursor.hasNext()) {
            Terrain terrain = cursor.next();
            listTerrain.add(terrain);
        }
        mongoClient.close();
        return listTerrain;
    };

    public List<Terrain> getListTerrainNonValide()throws Exception{      
        MongoClient mongoClient = ConnectMongoDb.getConnection();
        MongoDatabase mongoDatabase = mongoClient.getDatabase("parecelle");
        MongoCollection<Terrain> collection = mongoDatabase.getCollection("terrain", Terrain.class);
        List<Terrain> listTerrain = new ArrayList<Terrain>();
        MongoCursor<Terrain> cursor = collection.find().iterator();
        while (cursor.hasNext()) {
            Terrain terrain = cursor.next();
            listTerrain.add(terrain);
        }
        mongoClient.close();
        return listTerrain;
    };

    public List<Terrain> getListTerrainValideByUser(int idUser)throws Exception{
        Connection connection = Connect.getConnection();
        List<Terrain> listTerrain = new ArrayList<Terrain>();
        Statement state = connection.createStatement();
        String sql = "SELECT idUser,nomUser,motDePasse,idTerrain,nombreParecelles,longitude,latitude,dateValidation,descriptions FROM TerrainView where idUser = "+idUser+" group by idUser,nomUser,motDePasse,idTerrain,nombreParecelles,longitude,latitude,dateValidation,descriptions";
        ResultSet results = state.executeQuery(sql);
        while (results.next()) {
            Terrain terrain = new Terrain();
            User user =  new User(results.getInt("idUser"),results.getString("nomUser"),results.getString("motDePasse"));
            terrain.setProprietaire(user);
            terrain.setNombreParecelles(results.getInt("nombreParecelles"));
            terrain.setDescription(results.getString("descriptions"));
            terrain.setIdTerrain(results.getInt("idTerrain"));
            terrain.setLatitude(results.getDouble("longitude"));
            terrain.setLongitude(results.getDouble("latitude"));
            terrain.setDateValidaton(Date.valueOf(results.getString("dateValidation")));
            terrain.setParecellesOfTerrain();
            terrain.setPhotoOfTerrain();
            listTerrain.add(terrain);
        }
        state.close();
        connection.close();
        return listTerrain;        
    };

    public List<Terrain> getListTerrainValide()throws Exception{
        Connection connection = Connect.getConnection();
        List<Terrain> listTerrain = new ArrayList<Terrain>();
        Statement state = connection.createStatement();
        String sql = "SELECT idUser,nomUser,motDePasse,idTerrain,nombreParecelles,longitude,latitude,dateValidation,descriptions FROM TerrainView group by idUser,nomUser,motDePasse,idTerrain,nombreParecelles,longitude,latitude,dateValidation,descriptions";
        ResultSet results = state.executeQuery(sql);
        while (results.next()) {
            Terrain terrain = new Terrain();
            User user =  new User(results.getInt("idUser"),results.getString("nomUser"),results.getString("motDePasse"));
            terrain.setProprietaire(user);
            terrain.setNombreParecelles(results.getInt("nombreParecelles"));
            terrain.setDescription(results.getString("descriptions"));
            terrain.setIdTerrain(results.getInt("idTerrain"));
            terrain.setLatitude(results.getDouble("longitude"));
            terrain.setLongitude(results.getDouble("latitude"));
            terrain.setDateValidaton(Date.valueOf(results.getString("dateValidation")));
            terrain.setParecellesOfTerrain();
            terrain.setPhotoOfTerrain();
            listTerrain.add(terrain);
        }
        state.close();
        connection.close();
        return listTerrain;        
    };


    public List<Terrain> getListTerrainValideById(int idTerrain)throws Exception{
        Connection connection = Connect.getConnection();
        List<Terrain> listTerrain = new ArrayList<Terrain>();
        Statement state = connection.createStatement();
        String sql = "SELECT idUser,nomUser,motDePasse,idTerrain,nombreParecelles,longitude,latitude,dateValidation,descriptions FROM TerrainView where idTerrain = "+idTerrain+" group by idUser,nomUser,motDePasse,idTerrain,nombreParecelles,longitude,latitude,dateValidation,descriptions";
        ResultSet results = state.executeQuery(sql);
        while (results.next()) {
            Terrain terrain = new Terrain();
            User user =  new User(results.getInt("idUser"),results.getString("nomUser"),results.getString("motDePasse"));
            terrain.setProprietaire(user);
            terrain.setNombreParecelles(results.getInt("nombreParecelles"));
            terrain.setDescription(results.getString("descriptions"));
            terrain.setIdTerrain(results.getInt("idTerrain"));
            terrain.setLatitude(results.getDouble("longitude"));
            terrain.setLongitude(results.getDouble("latitude"));
            terrain.setDateValidaton(Date.valueOf(results.getString("dateValidation")));
            terrain.setParecellesOfTerrain();
            terrain.setPhotoOfTerrain();
            listTerrain.add(terrain);
        }
        state.close();
        connection.close();
        return listTerrain;        
    };
    
}
