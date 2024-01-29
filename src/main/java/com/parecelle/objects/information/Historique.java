package com.parecelle.objects.information;

import java.sql.*;
import java.sql.Date;

import com.parecelle.objects.Tany.Culture;
import com.parecelle.objects.connect.Connect;

public class Historique {
    int nombrePlantation;
    Culture culture;
    Date datePlantation;
    int nombreRecolte;
    Date dateRecolte;

    public Historique( int nombrePlantation, Culture culture, Date datePlantation,
            int nombreRecolte, Date dateRecolte) {
        this.nombrePlantation = nombrePlantation;
        this.culture = culture;
        this.datePlantation = datePlantation;
        this.nombreRecolte = nombreRecolte;
        this.dateRecolte = dateRecolte;
    }

    public Historique(){}

    public int getNombrePlantation() {
        return nombrePlantation;
    }
    public void setNombrePlantation(int nombrePlantation) {
        this.nombrePlantation = nombrePlantation;
    }
    public Culture getCulture() {
        return culture;
    }
    public void setCulture(Culture culture) {
        this.culture = culture;
    }
    public Date getDatePlantation() {
        return datePlantation;
    }
    public void setDatePlantation(Date datePlantation) {
        this.datePlantation = datePlantation;
    }
    public int getNombreRecolte() {
        return nombreRecolte;
    }
    public void setNombreRecolte(int nombreRecolte) {
        this.nombreRecolte = nombreRecolte;
    }
    public Date getDateRecolte() {
        return dateRecolte;
    }
    public void setDateRecolte(Date dateRecolte) {
        this.dateRecolte = dateRecolte;
    }

    public void insert()throws Exception {
        Connection connection = Connect.getConnection();
        String sql = "INSERT INTO Historique values ("+this.culture.getIdCulture()+","+this.nombrePlantation+",TO_DATE('"+this.datePlantation+"', 'YYYY-MM-DD'),"+nombreRecolte+",TO_DATE('"+this.dateRecolte+"', 'YYYY-MM-DD'))";
        Statement state = connection.createStatement();
        state.executeQuery(sql);
        state.close();
        connection.close();
    }
}
