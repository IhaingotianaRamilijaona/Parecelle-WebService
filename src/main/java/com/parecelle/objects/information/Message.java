package com.parecelle.objects.information;

import java.sql.*;

import com.parecelle.objects.connect.Connect;

public class Message {
    User envoyeur;
    User destinataire;
    String contenu;
    
    public Message(User envoyeur, User destinataire, String contenu) {
        this.envoyeur = envoyeur;
        this.destinataire = destinataire;
        this.contenu = contenu;
    }

    public User getEnvoyeur() {
        return envoyeur;
    }

    public void setEnvoyeur(User envoyeur) {
        this.envoyeur = envoyeur;
    }

    public User getDestinataire() {
        return destinataire;
    }

    public void setDestinataire(User destinataire) {
        this.destinataire = destinataire;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public Message(){}

    public void insert ()throws Exception{
        Connection connection = Connect.getConnection(); 
        Statement state = connection.createStatement();
        String sql = "INSERT INTO Message VALUES ("+this.envoyeur.getIdUser()+","+this.destinataire.getIdUser()+",'"+this.contenu+"') ";
        state.executeQuery(sql);
    }
}
