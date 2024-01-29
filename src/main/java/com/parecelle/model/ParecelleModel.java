package com.parecelle.model;

import org.springframework.stereotype.Component;
import java.sql.*;
import java.util.*;

import com.parecelle.objects.Tany.*;
import com.parecelle.objects.connect.Connect;
import com.parecelle.objects.information.User;

@Component
public class ParecelleModel {

    public List<Parecelle> getListParecelles()throws Exception{
        Connection connection = Connect.getConnection();
        List<Parecelle> listParecelle = new ArrayList<Parecelle>();
        Statement state = connection.createStatement();
        String sql = "SELECT idUser,nomUser,motDePasse,idParecelle,taille,rendement from TerrainView";
        ResultSet results = state.executeQuery(sql);
        while (results.next()) {
            User user =  new User(results.getInt("idUser"),results.getString("nomUser"),results.getString("motDePasse"));
            Parecelle parecelle = new Parecelle(results.getInt("idParecelle"),user, results.getInt("taille"), results.getInt("rendement"));
            parecelle.setCultureOfParecelle();
            parecelle.setHistoriquesOfParecelle();
            listParecelle.add(parecelle);
        }
        return listParecelle;
    }

    public Parecelle getParecellesById(int idParecelle)throws Exception{
        Connection connection = Connect.getConnection();
        Statement state = connection.createStatement();
        String sql = "SELECT idUser,nomUser,motDePasse,idParecelle,taille,rendement from TerrainView where idParecelle ="+idParecelle;
        ResultSet results = state.executeQuery(sql);
        results.next();
        User user =  new User(results.getInt("idUser"),results.getString("nomUser"),results.getString("motDePasse"));
        Parecelle parecelle = new Parecelle(results.getInt("idParecelle"),user, results.getInt("taille"), results.getInt("rendement"));
        parecelle.setCultureOfParecelle();
        parecelle.setHistoriquesOfParecelle();
        return parecelle;
    }

    public List<Parecelle> getParecellesByTerrain(int idTerrain)throws Exception{
        Connection connection = Connect.getConnection();
        List<Parecelle> listParecelle = new ArrayList<Parecelle>();
        Statement state = connection.createStatement();
        String sql = "SELECT idUser,nomUser,motDePasse,idParecelle,taille,rendement from TerrainView where idTerrain = "+idTerrain;
        ResultSet results = state.executeQuery(sql);
        while (results.next()) {
            User user =  new User(results.getInt("idUser"),results.getString("nomUser"),results.getString("motDePasse"));
            Parecelle parecelle = new Parecelle(results.getInt("idParecelle"),user, results.getInt("taille"), results.getInt("rendement"));
            parecelle.setCultureOfParecelle();
            parecelle.setHistoriquesOfParecelle();
            listParecelle.add(parecelle);
        }
        return listParecelle;
    }

    public List<Parecelle> getParecellesByCulture(int idCulture)throws Exception{
        Connection connection = Connect.getConnection();
        List<Parecelle> listParecelle = new ArrayList<Parecelle>();
        Statement state = connection.createStatement();
        String sql = "SELECT idUser,nomUser,motDePasse,idParecelle,taille,rendement FROM ParecelleCultureView where idCulture = "+idCulture;
        ResultSet results = state.executeQuery(sql);
        while (results.next()) {
            User user =  new User(results.getInt("idUser"),results.getString("nomUser"),results.getString("motDePasse"));
            Parecelle parecelle = new Parecelle(results.getInt("idParecelle"),user, results.getInt("taille"), results.getInt("rendement"));
            parecelle.setCultureOfParecelle();
            parecelle.setHistoriquesOfParecelle();
            listParecelle.add(parecelle);
        }
        return listParecelle;
    }

    public List<Parecelle> getParecellesByUser(int idUser)throws Exception{
        Connection connection = Connect.getConnection();
        List<Parecelle> listParecelle = new ArrayList<Parecelle>();
        Statement state = connection.createStatement();
        String sql = "SELECT idUser,nomUser,motDePasse,idParecelle,taille,rendement FROM TerrainView where idUser = "+idUser;
        ResultSet results = state.executeQuery(sql);
        while (results.next()) {
            User user =  new User(results.getInt("idUser"),results.getString("nomUser"),results.getString("motDePasse"));
            Parecelle parecelle = new Parecelle(results.getInt("idParecelle"),user, results.getInt("taille"), results.getInt("rendement"));
            parecelle.setCultureOfParecelle();
            parecelle.setHistoriquesOfParecelle();
            listParecelle.add(parecelle);
        }
        return listParecelle;
    }
}
