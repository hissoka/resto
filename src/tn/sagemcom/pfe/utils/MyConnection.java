/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.sagemcom.pfe.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Issam BenBelgacem
 */
public class MyConnection {
    
     

private final  String url="jdbc:mysql://10.64.16.181:3306/testdb?autoReconnect=true";
private final  String userName="root";
private final  String password="root";
    
   private static Connection connection;
   
   
   private MyConnection()
   {
       try {
           Class.forName("com.mysql.jdbc.Driver");
           connection=DriverManager.getConnection(url, userName,password);
       } catch (SQLException ex) {
           System.out.println(ex);
       } catch (ClassNotFoundException ex) {
        Logger.getLogger(MyConnection.class.getName()).log(Level.SEVERE, null, ex);
    }
   }
   public static synchronized Connection getInstance()
   {
       if(connection==null)
           new MyConnection();
       
       return connection;
           
   }
   
    
}
