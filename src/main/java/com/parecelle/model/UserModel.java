package com.parecelle.model;

import org.springframework.stereotype.Component;
import java.sql.*;
import java.util.*;

import com.parecelle.objects.connect.Connect;
import com.parecelle.objects.information.*;

@Component
public class UserModel {
    public User getOneUser(String nom,String mdp)throws Exception {
        Connection connection = Connect.getConnection();
        String sql = "SELECT * FROM Users where nomUser = '"+nom+"' AND motDePasse = '"+mdp+"'";
        Statement state = connection.createStatement();
        ResultSet results = state.executeQuery(sql);
        results.next();
        User user = new User(results.getInt("idUser"),results.getString("nomUser"),results.getString("motDePasse"));
        state.close();
        connection.close();
        return user;
    }

    public List<Message> getUserMessages(int idUser)throws Exception{
        Connection connection = Connect.getConnection();
        List<Message> listMessage = new ArrayList<Message>();
        Statement state = connection.createStatement();
        String sql = "SELECT * FROM MessageView where idEnvoyeur = "+idUser+" OR idReceveur = "+idUser;
        ResultSet results = state.executeQuery(sql);
        while (results.next()) {
            User envoyeur = new User(results.getInt("idEnvoyeur"),results.getString("nomEnvoyeur"),results.getString("motDePasseEnvoyeur"));
            User destinataire = new User(results.getInt("idReceveur"),results.getString("nomReceveur"),results.getString("motDePasseReceveur"));
            Message message = new Message(envoyeur,destinataire,results.getString("contenu"));
            listMessage.add(message);
        }
        state.close();
        connection.close();
        return listMessage;
    };
}
