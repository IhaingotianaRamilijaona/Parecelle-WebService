package com.parecelle.objects.information;

import java.sql.*;

import com.parecelle.objects.connect.Connect;

public class User {
    int idUser;
    String nomUser;
    String motDePasse;
    
    public User(){}
    
    public User(int idUser, String nomUser, String motDePasse) {
        this.idUser = idUser;
        this.nomUser = nomUser;
        this.motDePasse = motDePasse;
    }
    public String getMotDePasse() {
        return motDePasse;
    }
    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }
    public int getIdUser() {
        return idUser;
    }
    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
    public String getNomUser() {
        return nomUser;
    }
    public void setNomUser(String nomUser) {
        this.nomUser = nomUser;
    }

    Connect connex;

    public void insert()throws Exception {
        Connection connection = Connect.getConnection();
        String sql = "INSERT INTO User (nomUser,motDePasse) values ('"+this.nomUser+"','"+this.motDePasse+"') ";
        Statement state = connection.createStatement();
        state.executeQuery(sql);
        state.close();
        connection.close();
    }
}
