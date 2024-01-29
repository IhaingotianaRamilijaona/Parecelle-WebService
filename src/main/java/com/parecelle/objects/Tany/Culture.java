package com.parecelle.objects.Tany;
import java.sql.*;

import com.parecelle.objects.connect.Connect;

public class Culture {
    int idCulture;
    String nomCulture;
    int prix;

    public Culture(){}
    
    public Culture(int idCulture, String nomCulture, int prix) {
        this.idCulture = idCulture;
        this.nomCulture = nomCulture;
        this.prix = prix;
    }

    public int getIdCulture() {
        return idCulture;
    }
    public void setIdCulture(int idCulture) {
        this.idCulture = idCulture;
    }
    public String getNomCulture() {
        return nomCulture;
    }
    public void setNomCulture(String nomCulture) {
        this.nomCulture = nomCulture;
    }
    public int getPrix() {
        return prix;
    }
    public void setPrix(int prix) {
        this.prix = prix;
    }

    public void insert()throws Exception {
        Connection connection = Connect.getConnection();
        String sql = "INSERT INTO Culture (nomCulture,prixCulture) values ('"+this.nomCulture+"','"+this.prix+"') ";
        Statement state = connection.createStatement();
        state.executeUpdate(sql);
        state.close();
        connection.close();
    }
}
