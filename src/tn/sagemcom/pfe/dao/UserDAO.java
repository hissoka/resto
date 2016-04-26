/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.sagemcom.pfe.dao;

import tn.sagemcom.pfe.utils.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import tn.sagemcom.pfe.entites.Utilisateur;
import tn.sagemcom.pfe.utils.MyLogger;

/**
 *
 * @author Issam BenBelgacem
 */
public class UserDAO {
     private Connection connection= MyConnection.getInstance();
    public Utilisateur findAdminbyLogin(String login,String password){
   Utilisateur admin = null;
     String requete = "select * from person where username=? and password= ?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setString(1, login);
             ps.setString(2, password);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next())
            {
                admin=new Utilisateur();
                admin.setId(resultat.getInt("id"));
              admin.setUsername(resultat.getString("username"));
              admin.setPassword(resultat.getString("password"));
              admin.setName(resultat.getString("name"));
              admin.setMail(resultat.getString("email"));
            }
                        MyLogger.log(Level.INFO," User : "+login +" connected successfully ");
            return admin;

        } catch (SQLException ex) {
            MyLogger.log(Level.INFO,ex.toString());
            return null;
        }
    }
}
