/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.sagemcom.pfe.dao;

/**
 *
 * @author Issam BenBelgacem
 */

import tn.sagemcom.pfe.utils.MyConnection;
import tn.sagemcom.pfe.entites.Memoire;
import tn.sagemcom.pfe.exception.NotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemoireDao {
/**
 * Connection.  This Proprety 
 * 
 *     
    */
 private Connection connection= MyConnection.getInstance();

    /**
     * createValueObject-method. This method is used when the Dao class needs
     * to create new value object instance. The reason why this method exists
     * is that sometimes the programmer may want to extend also the valueObject
     * and then this method can be overrided to return extended valueObject.
     * NOTE: If you extend the valueObject class, make sure to override the
     * clone() method in it!
     */
    public Memoire createValueObject() {
          return new Memoire();
    }


    /**
     * getObject-method. This will create and load valueObject contents from database 
     * using given Primary-Key as identifier. This method is just a convenience method 
     * for the real load-method which accepts the valueObject as a parameter. Returned
     * valueObject will be created using the createValueObject() method.
     */
    public Memoire getObject(int id) throws NotFoundException, SQLException {

          Memoire valueObject = createValueObject();
          valueObject.setId(id);
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
     * 
     * @param valueObject  This parameter contains the class instance to be loaded.
     *                     Primary-key field must be set for this to work properly.
     */
    public void load( Memoire valueObject) throws NotFoundException, SQLException {

          String sql = "SELECT * FROM Memoire WHERE (id = ? ) "; 
          PreparedStatement stmt = null;

          try {
               stmt = connection.prepareStatement(sql);
               stmt.setInt(1, valueObject.getId()); 

               singleQuery(stmt, valueObject);

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
     * @param connection         This method requires working database connectionection.
     */
    public List loadAll() throws SQLException {

          String sql = "SELECT * FROM Memoire ORDER BY id ASC ";
          List searchResults = listQuery(connection.prepareStatement(sql));

          return searchResults;
    }
/**
     * LoadAll-method.(id) This will read all contents from database table and
     * build a List containing valueObjects related to the gateway id . Please note, that this method
     * will consume huge amounts of resources if table has lot's of rows. 
     * This should only be used when target tables have only small amounts
     * of data.
     *
     * @param id         This method requires id of gateway.
     * @return           This method will returnd all gateway saved memoire
     * @throws java.sql.SQLException 
     */
    public List<Memoire> loadAll(int id) throws SQLException {

         String sql = "SELECT * FROM Memoire where gateway_id= ? ORDER BY id ASC";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, id);
        List searchResults = listQuery(stmt);

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
     * @param valueObject  This parameter contains the class instance to be created.
     *                     If automatic surrogate-keys are not used the Primary-key 
     *                     field must be set for this to work properly.
     * @throws java.sql.SQLException
     */
    public synchronized void create( Memoire valueObject) throws SQLException {

          String sql = null;
          PreparedStatement stmt = null;
          ResultSet result = null;

          try {
               sql = "INSERT INTO Memoire (label, _date, "
               + "total, used, free, "
               + "buffers, gateway_id) VALUES (?, ?, ?, ?, ?, ?, ?) ";
               stmt = connection.prepareStatement(sql);

               //stmt.setInt(1, valueObject.getId()); 
               stmt.setString(1, valueObject.getLabel()); 
               stmt.setString(2, valueObject.getDate()); 
               stmt.setInt(3, valueObject.getTotal()); 
               stmt.setInt(4, valueObject.getUsed()); 
               stmt.setInt(5, valueObject.getFree()); 
               stmt.setInt(6, valueObject.getBuffers()); 
               stmt.setLong(7, valueObject.getGateway_id()); 

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


    /**
     * save-method. This method will save the current state of valueObject to database.
     * Save can not be used to create new instances in database, so upper layer must
     * make sure that the primary-key is correctly specified. Primary-key will indicate
     * which instance is going to be updated in database. If save can not find matching 
     * row, NotFoundException will be thrown.
     *
     * @param valueObject  This parameter contains the class instance to be saved.
     *                     Primary-key field must be set for this to work properly.
     */
    public void save( Memoire valueObject) 
          throws NotFoundException, SQLException {

          String sql = "UPDATE Memoire SET label = ?, _date = ?, total = ?, "
               + "used = ?, free = ?, buffers = ?, "
               + "gateway_id = ? WHERE (id = ? ) ";
          
              PreparedStatement stmt = null;

          try {
              stmt = connection.prepareStatement(sql);
              stmt.setString(1, valueObject.getLabel()); 
              stmt.setString(2, valueObject.getDate()); 
              stmt.setInt(3, valueObject.getTotal()); 
              stmt.setInt(4, valueObject.getUsed()); 
              stmt.setInt(5, valueObject.getFree()); 
              stmt.setInt(6, valueObject.getBuffers()); 
              stmt.setLong(7, valueObject.getGateway_id()); 

              stmt.setInt(8, valueObject.getId()); 

              int rowcount = databaseUpdate(stmt);
              if (rowcount == 0) {
                   throw new NotFoundException("Object could not be saved! (PrimaryKey not found)");
              }
              if (rowcount > 1) {
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
     * @param valueObject  This parameter contains the class instance to be deleted.
     *                     Primary-key field must be set for this to work properly.
     */
    public void delete( Memoire valueObject) 
          throws NotFoundException, SQLException {

          String sql = "DELETE FROM Memoire WHERE (id = ? ) ";
          PreparedStatement stmt = null;

          try {
              stmt = connection.prepareStatement(sql);
              stmt.setInt(1, valueObject.getId()); 

              int rowcount = databaseUpdate(stmt);
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
     */
    public void deleteAll() throws SQLException {

          String sql = "DELETE FROM Memoire";
          PreparedStatement stmt = null;

          try {
              stmt = connection.prepareStatement(sql);
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
     * @return 
     * @throws java.sql.SQLException
     */
    public int countAll() throws SQLException {

          String sql = "SELECT count(*) FROM Memoire";
          PreparedStatement stmt = null;
          ResultSet result = null;
          int allRows = 0;

          try {
              stmt = connection.prepareStatement(sql);
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
     * @param valueObject  This parameter contains the class instance where search will be based.
     *                     Primary-key field should not be set.
     * @return 
     * @throws java.sql.SQLException
     */
    public List searchMatching( Memoire valueObject) throws SQLException {

          List searchResults;

          boolean first = true;
          StringBuilder sql = new StringBuilder("SELECT * FROM Memoire WHERE 1=1 ");

          if (valueObject.getId() != 0) {
              if (first) { first = false; }
              sql.append("AND id = ").append(valueObject.getId()).append(" ");
          }

          if (valueObject.getLabel() != null) {
              if (first) { first = false; }
              sql.append("AND label LIKE '").append(valueObject.getLabel()).append("%' ");
          }

          if (valueObject.getDate() != null) {
              if (first) { first = false; }
              sql.append("AND _date LIKE '").append(valueObject.getDate()).append("%' ");
          }

          if (valueObject.getTotal() != 0) {
              if (first) { first = false; }
              sql.append("AND total = ").append(valueObject.getTotal()).append(" ");
          }

          if (valueObject.getUsed() != 0) {
              if (first) { first = false; }
              sql.append("AND used = ").append(valueObject.getUsed()).append(" ");
          }

          if (valueObject.getFree() != 0) {
              if (first) { first = false; }
              sql.append("AND free = ").append(valueObject.getFree()).append(" ");
          }

          if (valueObject.getBuffers() != 0) {
              if (first) { first = false; }
              sql.append("AND buffers = ").append(valueObject.getBuffers()).append(" ");
          }

          if (valueObject.getGateway_id() != 0) {
              if (first) { first = false; }
              sql.append("AND gateway_id LIKE '").append(valueObject.getGateway_id()).append("%' ");
          }


          sql.append("ORDER BY id ASC ");

          // Prevent accidential full table results.
          // Use loadAll if all rows must be returned.
          if (first)
               searchResults = new ArrayList();
          else
               searchResults = listQuery(connection.prepareStatement(sql.toString()));

          return searchResults;
    }


    

    /**
     * databaseUpdate-method. This method is a helper method for internal use. It will execute
     * all database handling that will change the information in tables. SELECT queries will
     * not be executed here however. The return value indicates how many rows were affected.
     * This method will also make sure that if cache is used, it will reset when data changes.
     *
     * @param stmt         This parameter contains the SQL statement to be excuted.
     * @return 
     * @throws java.sql.SQLException 
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
     * @param stmt         This parameter contains the SQL statement to be excuted.
     * @param valueObject  Class-instance where resulting data will be stored.
     * @throws tn.sagemcom.pfe.exception.NotFoundException
     * @throws java.sql.SQLException
     */
    protected void singleQuery( PreparedStatement stmt, Memoire valueObject) 
          throws NotFoundException, SQLException {

          try (ResultSet result = stmt.executeQuery()) {

              if (result.next()) {

                   valueObject.setId(result.getInt("id")); 
                   valueObject.setLabel(result.getString("label")); 
                   valueObject.setDate(result.getString("_date")); 
                   valueObject.setTotal(result.getInt("total")); 
                   valueObject.setUsed(result.getInt("used")); 
                   valueObject.setFree(result.getInt("free")); 
                   valueObject.setBuffers(result.getInt("buffers")); 
                   valueObject.setGateway_id(result.getLong("gateway_id")); 

              } else {
                    //System.out.println("Memoire Object Not Found!");
                    throw new NotFoundException("Memoire Object Not Found!");
              }
          } 
    }


    /**
     * databaseQuery-method. This method is a helper method for internal use. It will execute
     * all database queries that will return multiple rows. The resultset will be converted
     * to the List of valueObjects. If no rows were found, an empty List will be returned.
     *
     * @param stmt         This parameter contains the SQL statement to be excuted.
     */
    protected List listQuery(PreparedStatement stmt) throws SQLException {

          ArrayList searchResults = new ArrayList();
          ResultSet result = null;

          try {
              result = stmt.executeQuery();

              while (result.next()) {
                   Memoire temp = createValueObject();

                   temp.setId(result.getInt("id")); 
                   temp.setLabel(result.getString("label")); 
                   temp.setDate(result.getString("_date")); 
                   temp.setTotal(result.getInt("total")); 
                   temp.setUsed(result.getInt("used")); 
                   temp.setFree(result.getInt("free")); 
                   temp.setBuffers(result.getInt("buffers")); 
                   temp.setGateway_id(result.getLong("gateway_id")); 

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
