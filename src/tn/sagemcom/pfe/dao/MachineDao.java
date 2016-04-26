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
import java.util.ArrayList;
import java.util.List;
import tn.sagemcom.pfe.entites.Machine;
import tn.sagemcom.pfe.exception.NotFoundException;
import tn.sagemcom.pfe.utils.MyConnection;

/**
 *
 * @author G507751
 */
public class MachineDao {

    private final Connection conn = MyConnection.getInstance();


    /**
     * createValueObject-method. This method is used when the Dao class needs
     * to create new value object instance. The reason why this method exists
     * is that sometimes the programmer may want to extend also the valueObject
     * and then this method can be overrided to return extended valueObject.
     * NOTE: If you extend the valueObject class, make sure to override the
     * clone() method in it!
     */
    public Machine createValueObject() {
          return new Machine();
    }


    /**
     * getObject-method. This will create and load valueObject contents from database 
     * using given Primary-Key as identifier. This method is just a convenience method 
     * for the real load-method which accepts the valueObject as a parameter. Returned
     * valueObject will be created using the createValueObject() method.
     */
    public Machine getObject(int id, String macaddress) throws NotFoundException, SQLException {

          Machine valueObject = createValueObject();
          valueObject.setId(id);
          valueObject.setMacaddress(macaddress);
          load(valueObject);
          return valueObject;
    }


    /**
     * load-method. This will load valueObject contents from database using
     * Primary-Key as identifier. Upper layer should use this so that valueObject
     * instance is created and only primary-key should be specified. Then call
     * this method to complete other persistent information. This method will
     * overwrite all other fields except primary-key and possible runtime variables.
     * If load can not find matching row, NotFoundException will be thrown.
     *
     * @param conn         This method requires working database connection.
     * @param valueObject  This parameter contains the class instance to be loaded.
     *                     Primary-key field must be set for this to work properly.
     */
    public void load( Machine valueObject) throws NotFoundException, SQLException {

          String sql = "SELECT * FROM machines WHERE (id = ? AND macaddress = ? ) "; 
          PreparedStatement stmt = null;

          try {
               stmt = conn.prepareStatement(sql);
               stmt.setInt(1, valueObject.getId()); 
               stmt.setString(2, valueObject.getMacaddress()); 

               singleQuery( stmt, valueObject);

          } finally {
              if (stmt != null)
                  stmt.close();
          }
    }


    /**
     * LoadAll-method. This will read all contents from database table and
     * build a List containing valueObjects. Please note, that this method
     * will consume huge amounts of resources if table has lot's of rows. 
     * This should only be used when target tables have only small amounts
     * of data.
     *
     * @param conn         This method requires working database connection.
     */
    public List loadAll() throws SQLException {

          String sql = "SELECT * FROM machines ORDER BY macaddress ASC ";
          List searchResults = listQuery(conn.prepareStatement(sql));

          return searchResults;
    }



    /**
     * create-method. This will create new row in database according to supplied
     * valueObject contents. Make sure that values for all NOT NULL columns are
     * correctly specified. Also, if this table does not use automatic surrogate-keys
     * the primary-key must be specified. After INSERT command this method will 
     * read the generated primary-key back to valueObject if automatic surrogate-keys
     * were used. 
     *
     * @param conn         This method requires working database connection.
     * @param valueObject  This parameter contains the class instance to be created.
     *                     If automatic surrogate-keys are not used the Primary-key 
     *                     field must be set for this to work properly.
     */
    public synchronized void create(Machine valueObject) throws SQLException {

          String sql = "";
          PreparedStatement stmt = null;
          ResultSet result = null;

          try {
               sql = "INSERT INTO machines ( id, macaddress, ipaddress, "
               + "_type, device, _date, "
               + "gateway_id) VALUES (?, ?, ?, ?, ?, ?, ?) ON DUPLICATE KEY UPDATE"+
                       " ipaddress=?, _type=? ,device=?,_date=?";
               stmt = conn.prepareStatement(sql);

               stmt.setInt(1, valueObject.getId()); 
               stmt.setString(2, valueObject.getMacaddress()); 
               stmt.setString(3, valueObject.getIpaddress()); 
               stmt.setString(4, valueObject.get_type()); 
               stmt.setString(5, valueObject.getDevice()); 
               stmt.setString(6, valueObject.get_date()); 
               stmt.setInt(7, valueObject.getGateway_id()); 
 stmt.setString(8, valueObject.getIpaddress()); 
               stmt.setString(9, valueObject.get_type()); 
               stmt.setString(10, valueObject.getDevice()); 
               stmt.setString(11, valueObject.get_date()); 
               int rowcount = databaseUpdate( stmt);
               if (rowcount != 1) {
                    //System.out.println("PrimaryKey Error when updating DB!");
                    throw new SQLException("PrimaryKey Error when updating DB!");
               }

          } finally {
              if (stmt != null)
                  stmt.close();
          }


    }


    /**
     * save-method. This method will save the current state of valueObject to database.
     * Save can not be used to create new instances in database, so upper layer must
     * make sure that the primary-key is correctly specified. Primary-key will indicate
     * which instance is going to be updated in database. If save can not find matching 
     * row, NotFoundException will be thrown.
     *
     * @param conn         This method requires working database connection.
     * @param valueObject  This parameter contains the class instance to be saved.
     *                     Primary-key field must be set for this to work properly.
     */
    public void save( Machine valueObject) 
          throws NotFoundException, SQLException {

          String sql = "UPDATE machines SET ipaddress = ?, _type = ?, device = ?, "
               + "_date = ?, gateway_id = ? WHERE (id = ? AND macaddress = ? ) ";
          PreparedStatement stmt = null;

          try {
              stmt = conn.prepareStatement(sql);
              stmt.setString(1, valueObject.getIpaddress()); 
              stmt.setString(2, valueObject.get_type()); 
              stmt.setString(3, valueObject.getDevice()); 
              stmt.setString(4, valueObject.get_date()); 
              stmt.setInt(5, valueObject.getGateway_id()); 

              stmt.setInt(6, valueObject.getId()); 
              stmt.setString(7, valueObject.getMacaddress()); 

              int rowcount = databaseUpdate(stmt);
              if (rowcount == 0) {
                   //System.out.println("Object could not be saved! (PrimaryKey not found)");
                   throw new NotFoundException("Object could not be saved! (PrimaryKey not found)");
              }
              if (rowcount > 1) {
                   //System.out.println("PrimaryKey Error when updating DB! (Many objects were affected!)");
                   throw new SQLException("PrimaryKey Error when updating DB! (Many objects were affected!)");
              }
          } finally {
              if (stmt != null)
                  stmt.close();
          }
    }


    /**
     * delete-method. This method will remove the information from database as identified by
     * by primary-key in supplied valueObject. Once valueObject has been deleted it can not 
     * be restored by calling save. Restoring can only be done using create method but if 
     * database is using automatic surrogate-keys, the resulting object will have different 
     * primary-key than what it was in the deleted object. If delete can not find matching row,
     * NotFoundException will be thrown.
     *
     * @param conn         This method requires working database connection.
     * @param valueObject  This parameter contains the class instance to be deleted.
     *                     Primary-key field must be set for this to work properly.
     */
    public void delete( Machine valueObject) 
          throws NotFoundException, SQLException {

          String sql = "DELETE FROM machines WHERE (id = ? AND macaddress = ? ) ";
          PreparedStatement stmt = null;

          try {
              stmt = conn.prepareStatement(sql);
              stmt.setInt(1, valueObject.getId()); 
              stmt.setString(2, valueObject.getMacaddress()); 

              int rowcount = databaseUpdate( stmt);
              if (rowcount == 0) {
                   //System.out.println("Object could not be deleted (PrimaryKey not found)");
                   throw new NotFoundException("Object could not be deleted! (PrimaryKey not found)");
              }
              if (rowcount > 1) {
                   //System.out.println("PrimaryKey Error when updating DB! (Many objects were deleted!)");
                   throw new SQLException("PrimaryKey Error when updating DB! (Many objects were deleted!)");
              }
          } finally {
              if (stmt != null)
                  stmt.close();
          }
    }


    /**
     * deleteAll-method. This method will remove all information from the table that matches
     * this Dao and ValueObject couple. This should be the most efficient way to clear table.
     * Once deleteAll has been called, no valueObject that has been created before can be 
     * restored by calling save. Restoring can only be done using create method but if database 
     * is using automatic surrogate-keys, the resulting object will have different primary-key 
     * than what it was in the deleted object. (Note, the implementation of this method should
     * be different with different DB backends.)
     *
     * @param conn         This method requires working database connection.
     */
    public void deleteAll() throws SQLException {

          String sql = "DELETE FROM machines";
          PreparedStatement stmt = null;

          try {
              stmt = conn.prepareStatement(sql);
              int rowcount = databaseUpdate(stmt);
          } finally {
              if (stmt != null)
                  stmt.close();
          }
    }


    /**
     * coutAll-method. This method will return the number of all rows from table that matches
     * this Dao. The implementation will simply execute "select count(primarykey) from table".
     * If table is empty, the return value is 0. This method should be used before calling
     * loadAll, to make sure table has not too many rows.
     *
     * @param conn         This method requires working database connection.
     */
    public int countAll() throws SQLException {

          String sql = "SELECT count(*) FROM machines";
          PreparedStatement stmt = null;
          ResultSet result = null;
          int allRows = 0;

          try {
              stmt = conn.prepareStatement(sql);
              result = stmt.executeQuery();

              if (result.next())
                  allRows = result.getInt(1);
          } finally {
              if (result != null)
                  result.close();
              if (stmt != null)
                  stmt.close();
          }
          return allRows;
    }


    /** 
     * searchMatching-Method. This method provides searching capability to 
     * get matching valueObjects from database. It works by searching all 
     * objects that match permanent instance variables of given object.
     * Upper layer should use this by setting some parameters in valueObject
     * and then  call searchMatching. The result will be 0-N objects in a List, 
     * all matching those criteria you specified. Those instance-variables that
     * have NULL values are excluded in search-criteria.
     *
     * @param conn         This method requires working database connection.
     * @param valueObject  This parameter contains the class instance where search will be based.
     *                     Primary-key field should not be set.
     */
    public List searchMatching( Machine valueObject) throws SQLException {

          List searchResults;

          boolean first = true;
          StringBuffer sql = new StringBuffer("SELECT * FROM machines WHERE 1=1 ");

          if (valueObject.getId() != 0) {
              if (first) { first = false; }
              sql.append("AND id = ").append(valueObject.getId()).append(" ");
          }

          if (valueObject.getMacaddress() != null) {
              if (first) { first = false; }
              sql.append("AND macaddress = ").append(valueObject.getMacaddress()).append(" ");
          }

          if (valueObject.getIpaddress() != null) {
              if (first) { first = false; }
              sql.append("AND ipaddress LIKE '").append(valueObject.getIpaddress()).append("%' ");
          }

          if (valueObject.get_type() != null) {
              if (first) { first = false; }
              sql.append("AND _type LIKE '").append(valueObject.get_type()).append("%' ");
          }

          if (valueObject.getDevice() != null) {
              if (first) { first = false; }
              sql.append("AND device LIKE '").append(valueObject.getDevice()).append("%' ");
          }

          if (valueObject.get_date() != null) {
              if (first) { first = false; }
              sql.append("AND _date LIKE '").append(valueObject.get_date()).append("%' ");
          }

          if (valueObject.getGateway_id() != 0) {
              if (first) { first = false; }
              sql.append("AND gateway_id = ").append(valueObject.getGateway_id()).append(" ");
          }


          sql.append("ORDER BY macaddress ASC ");

          // Prevent accidential full table results.
          // Use loadAll if all rows must be returned.
          if (first)
               searchResults = new ArrayList();
          else
               searchResults = listQuery( conn.prepareStatement(sql.toString()));

          return searchResults;
    }


  

    /**
     * databaseUpdate-method. This method is a helper method for internal use. It will execute
     * all database handling that will change the information in tables. SELECT queries will
     * not be executed here however. The return value indicates how many rows were affected.
     * This method will also make sure that if cache is used, it will reset when data changes.
     *
     * @param conn         This method requires working database connection.
     * @param stmt         This parameter contains the SQL statement to be excuted.
     */
    protected int databaseUpdate(PreparedStatement stmt) throws SQLException {

          int result = stmt.executeUpdate();

          return result;
    }



    /**
     * databaseQuery-method. This method is a helper method for internal use. It will execute
     * all database queries that will return only one row. The resultset will be converted
     * to valueObject. If no rows were found, NotFoundException will be thrown.
     *
     * @param conn         This method requires working database connection.
     * @param stmt         This parameter contains the SQL statement to be excuted.
     * @param valueObject  Class-instance where resulting data will be stored.
     */
    protected void singleQuery( PreparedStatement stmt, Machine valueObject) 
          throws NotFoundException, SQLException {

          ResultSet result = null;

          try {
              result = stmt.executeQuery();

              if (result.next()) {

                   valueObject.setId(result.getInt("id")); 
                   valueObject.setMacaddress(result.getString("macaddress")); 
                   valueObject.setIpaddress(result.getString("ipaddress")); 
                   valueObject.set_type(result.getString("_type")); 
                   valueObject.setDevice(result.getString("device")); 
                   valueObject.set_date(result.getString("_date")); 
                   valueObject.setGateway_id(result.getInt("gateway_id")); 

              } else {
                    //System.out.println("Machine Object Not Found!");
                    throw new NotFoundException("Machine Object Not Found!");
              }
          } finally {
              if (result != null)
                  result.close();
              if (stmt != null)
                  stmt.close();
          }
    }


    /**
     * databaseQuery-method. This method is a helper method for internal use. It will execute
     * all database queries that will return multiple rows. The resultset will be converted
     * to the List of valueObjects. If no rows were found, an empty List will be returned.
     *
     * @param conn         This method requires working database connection.
     * @param stmt         This parameter contains the SQL statement to be excuted.
     */
    protected List listQuery( PreparedStatement stmt) throws SQLException {

          ArrayList searchResults = new ArrayList();
          ResultSet result = null;

          try {
              result = stmt.executeQuery();

              while (result.next()) {
                   Machine temp = createValueObject();

                   temp.setId(result.getInt("id")); 
                   temp.setMacaddress(result.getString("macaddress")); 
                   temp.setIpaddress(result.getString("ipaddress")); 
                   temp.set_type(result.getString("_type")); 
                   temp.setDevice(result.getString("device")); 
                   temp.set_date(result.getString("_date")); 
                   temp.setGateway_id(result.getInt("gateway_id")); 
                   searchResults.add(temp);
              }

          } finally {
              if (result != null)
                  result.close();
              if (stmt != null)
                  stmt.close();
          }

          return (List)searchResults;
    }


}