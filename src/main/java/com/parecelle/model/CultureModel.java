package com.parecelle.model;

import org.springframework.stereotype.Component;
import java.sql.*;
import java.util.*;

import com.parecelle.objects.Tany.*;
import com.parecelle.objects.connect.Connect;

@Component
public class CultureModel {
    public List<Culture> getListCulture()throws Exception{
        Connection connection = Connect.getConnection();
        List<Culture> listCulture = new ArrayList<Culture>();
        Statement state = connection.createStatement();
        String sql = "SELECT * FROM Culture";
        ResultSet results = state.executeQuery(sql);
        while (results.next()) {
            Culture culture = new Culture(results.getInt("idCulture"),results.getString("nomCulture"),results.getInt("prixCulture"));
            listCulture.add(culture);
        }
        state.close();
        connection.close();
        return listCulture;
    }
    public List<Culture> filterListCulture(String sql)throws Exception{
        Connection connection = Connect.getConnection();
        List<Culture> listCulture = new ArrayList<Culture>();
        Statement state = connection.createStatement();
        ResultSet results = state.executeQuery(sql);
        while (results.next()) {
            Culture culture = new Culture(results.getInt("idCulture"),results.getString("nomCulture"),results.getInt("prixCulture"));
            listCulture.add(culture);
        }
        state.close();
        connection.close();
        return listCulture;
    }
}
