package com.parecelle.objects.Tany;

import java.sql.*;
import java.sql.Date;
import java.util.*;

import com.parecelle.objects.connect.Connect;
import com.parecelle.objects.information.Historique;
import com.parecelle.objects.information.User;

public class Parecelle {
    int idParecelle;
    User proprietaire;
    List<Culture> cultures;
    int taille;
    List<Historique> historiques;
    int rendement;

    public Parecelle(int idParecelle, User proprietaire, List<Culture> cultures, int taille,
            List<Historique> historiques, int rendement) {
        this.idParecelle = idParecelle;
        this.proprietaire = proprietaire;
        this.cultures = cultures;
        this.taille = taille;
        this.historiques = historiques;
        this.rendement = rendement;
    }

    public Parecelle(){}

    public Parecelle(int idParecelle,User proprietaire, int taille, int rendement) {
        this.idParecelle = idParecelle;
        this.proprietaire = proprietaire;
        this.taille = taille;
        this.rendement = rendement;
    }

    public int getIdParecelle() {
        return idParecelle;
    }

    public void setIdParecelle(int idParecelle) {
        this.idParecelle = idParecelle;
    }

    public User getProprietaire() {
        return proprietaire;
    }

    public void setProprietaire(User proprietaire) {
        this.proprietaire = proprietaire;
    }

    public List<Culture> getCultures() {
        return cultures;
    }

    public void setCultures(List<Culture> cultures) {
        this.cultures = cultures;
    }

    public int getTaille() {
        return taille;
    }

    public void setTaille(int taille) {
        this.taille = taille;
    }

    public List<Historique> getHistoriques() {
        return historiques;
    }

    public void setHistoriques(List<Historique> historiques) {
        this.historiques = historiques;
    }

    public int getRendement() {
        return rendement;
    }

    public void setRendement(int rendement) {
        this.rendement = rendement;
    }

    Connect connex;

    public void insert(int idTerrain)throws Exception {
        Connection connection = Connect.getConnection();
        String sql = "INSERT INTO Parecelle (idTerrain,taille,rendement) values ("+idTerrain+","+this.taille+","+this.rendement+") ";
        Statement state = connection.createStatement();
        state.executeUpdate(sql);

        sql = "SELECT MAX(idParecelle) as idParecelle FROM parecelle";
        Statement state1 = connection.createStatement();
        ResultSet result = state1.executeQuery(sql);
        result.next();
        this.setIdParecelle(result.getInt("idParecelle"));

        Statement state2= connection.createStatement();
        for (Culture culture : cultures) {
            sql = "INSERT INTO ParecelleCulture values ("+this.idParecelle+",'"+culture.getIdCulture()+"') ";
            state2.executeUpdate(sql);   
        }

        state.close();
        state1.close();
        state2.close();
        connection.close();
    }

    public void setCultureOfParecelle()throws Exception{
        Connection connection = Connect.getConnection();
        List<Culture> listCulture = new ArrayList<Culture>();
        Statement state = connection.createStatement();
        String sql = "SELECT * FROM ParecelleCultureView where idParecelle = "+this.idParecelle;
        ResultSet results = state.executeQuery(sql);
        while (results.next()) {
            Culture culture = new Culture(results.getInt("idCulture"),results.getString("nomCulture"),results.getInt("prixCulture"));
            listCulture.add(culture);
        }
        this.setCultures(listCulture);
        state.close();
        connection.close();
    }

    public void setHistoriquesOfParecelle()throws Exception{
        Connection connection = Connect.getConnection();
        List<Historique> listHistorique = new ArrayList<Historique>();
        Statement state = connection.createStatement();
        String sql = "SELECT * FROM HistoriqueView where idParecelle = "+this.idParecelle;
        ResultSet results = state.executeQuery(sql);
        while (results.next()) {
            Historique historique = new Historique();
            historique.setCulture(new  Culture(results.getInt("idCulture"),results.getString("nomCulture"),results.getInt("prixCulture")));
            historique.setDatePlantation(Date.valueOf(results.getString("datePlantation")));
            historique.setDateRecolte(Date.valueOf(results.getString("dateRecolte")));
            historique.setNombrePlantation(results.getInt("nombrePlantation"));
            historique.setNombreRecolte(results.getInt("nombreRecolte"));
            listHistorique.add(historique);
        }
        this.setHistoriques(listHistorique);
        state.close();
        connection.close();
    }

}
