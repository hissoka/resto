/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.sagemcom.pfe.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import tn.sagemcom.pfe.entites.Events_Gws;
import tn.sagemcom.pfe.utils.MyConnection;

/**
 *
 * @author G507751
 */
public class Event_gwsDao {
    
        private Connection conn = MyConnection.getInstance();

       public synchronized void create(Events_Gws valueObject) throws SQLException {

          String sql = "";
          PreparedStatement stmt = null;
          ResultSet result = null;

          try {
               sql = "INSERT INTO Event_gws ( id, gateway_id, label, "
               + "_date, descr) VALUES (?, ?, ?, ?, ?) ";
               stmt = conn.prepareStatement(sql);

               stmt.setInt(1, valueObject.getId()); 
               stmt.setInt(2, valueObject.getGateway_id()); 
               stmt.setString(3, valueObject.getLabel()); 
               stmt.setString(4, valueObject.get_date()); 
               stmt.setString(5, valueObject.getDescr()); 

               int rowcount = databaseUpdate(stmt);
               if (rowcount != 1) {
                    //System.out.println("PrimaryKey Error when updating DB!");
                    throw new SQLException("PrimaryKey Error when updating DB!");
               }

          } finally {
              if (stmt != null)
                  stmt.close();
          }


    }

protected int databaseUpdate(PreparedStatement stmt) throws SQLException {

          int result = stmt.executeUpdate();

          return result;
    }
}
