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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import tn.sagemcom.pfe.entites.Processus;
import tn.sagemcom.pfe.exception.NotFoundException;
import tn.sagemcom.pfe.utils.MyLogger;
public class ProcessusDao {

 private Connection connection= MyConnection.getInstance();


    /**
     * createValueObject-method. This method is used when the Dao class needs
     * to create new value object instance. The reason why this method exists
     * is that sometimes the programmer may want to extend also the valueObject
     * and then this method can be overrided to return extended valueObject.
     * NOTE: If you extend the valueObject class, make sure to override the
     * clone() method in it!
     */
    public Processus createValueObject() {
          return new Processus();
    }


    /**
     * getObject-method. This will create and load valueObject contents from database 
     * using given Primary-Key as identifier. This method is just a convenience method 
     * for the real load-method which accepts the valueObject as a parameter. Returned
     * valueObject will be created using the createValueObject() method.
     */
    public Processus getObject(int id) throws NotFoundException, SQLException {

          Processus valueObject = createValueObject();
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
     * @param conn         This method requires working database connection.
     * @param valueObject  This parameter contains the class instance to be loaded.
     *                     Primary-key field must be set for this to work properly.
     */
    public void load( Processus valueObject) throws NotFoundException, SQLException {

          String sql = "SELECT * FROM Processus WHERE (id = ? ) "; 
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
     * @param conn         This method requires working database connection.
     */
    public List loadAll() throws SQLException {

          String sql = "SELECT * FROM Processus ORDER BY id ASC ";
          List searchResults = listQuery(connection.prepareStatement(sql));

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
     */
    public synchronized void create(Processus valueObject) throws SQLException {

          String sql = "";
          PreparedStatement stmt = null;
          ResultSet result = null;

          try {
               sql = "INSERT INTO Processus ( id, label, _date, "
               + "user, pid, perc_cpu, "
               + "perc_memoire, gateway_id, vsz, "
               + "rss, tty, stat, "
               + "start, time, command,list) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?) ";
               stmt = connection.prepareStatement(sql);

               stmt.setInt(1, valueObject.getId()); 
               stmt.setString(2, valueObject.getLabel()); 
               stmt.setString(3, valueObject.getDate()); 
               stmt.setString(4, valueObject.getUser()); 
               stmt.setInt(5, valueObject.getPid()); 
               stmt.setString(6, valueObject.getPerc_cpu()); 
               stmt.setString(7, valueObject.getPerc_memoire()); 
               stmt.setLong(8, valueObject.getGateway_id()); 
               stmt.setString(9, valueObject.getVsz()); 
               stmt.setString(10, valueObject.getRSS()); 
               stmt.setString(11, valueObject.getTty()); 
               stmt.setString(12, valueObject.getStat()); 
               stmt.setString(13, valueObject.getStart()); 
               stmt.setString(14, valueObject.getTime()); 
               stmt.setString(15, valueObject.getCommand()); 
               stmt.setInt(16, valueObject.getList()); 

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
    public void save(Processus valueObject) 
          throws NotFoundException, SQLException {

          String sql = "UPDATE Processus SET label = ?, _date = ?, user = ?, "
               + "pid = ?, perc_cpu = ?, perc_memoire = ?, "
               + "gateway_id = ?, vsz = ?, rss = ?, "
               + "tty = ?, stat = ?, start = ?, "
               + "time = ?, command = ? , list=? WHERE (id = ? ) ";
          PreparedStatement stmt = null;

          try {
              stmt = connection.prepareStatement(sql);
              stmt.setString(1, valueObject.getLabel()); 
              stmt.setString(2, valueObject.getDate()); 
              stmt.setString(3, valueObject.getUser()); 
              stmt.setInt(4, valueObject.getPid()); 
              stmt.setString(5, valueObject.getPerc_cpu()); 
              stmt.setString(6, valueObject.getPerc_memoire()); 
              stmt.setLong(7, valueObject.getGateway_id()); 
              stmt.setString(8, valueObject.getVsz()); 
              stmt.setString(9, valueObject.getRSS()); 
              stmt.setString(10, valueObject.getTty()); 
              stmt.setString(11, valueObject.getStat()); 
              stmt.setString(12, valueObject.getStart()); 
              stmt.setString(13, valueObject.getTime()); 
              stmt.setString(14, valueObject.getCommand()); 
              stmt.setInt(15, valueObject.getList()); 

              stmt.setInt(16, valueObject.getId()); 

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
     * @param valueObject  This parameter contains the class instance to be deleted.
     *                     Primary-key field must be set for this to work properly.
     */
    public void delete(Processus valueObject) 
          throws NotFoundException, SQLException {

          String sql = "DELETE FROM Processus WHERE (id = ? ) ";
          PreparedStatement stmt = null;

          try {
              stmt = connection.prepareStatement(sql);
              stmt.setInt(1, valueObject.getId()); 

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
     */
    public void deleteAll() throws SQLException {

          String sql = "DELETE FROM Processus";
          PreparedStatement stmt = null;

          try {
              stmt = connection.prepareStatement(sql);
              int rowcount = databaseUpdate( stmt);
              MyLogger.log(Level.SEVERE, rowcount+" Rows deleted from  Processus  Table");
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

          String sql = "SELECT count(*) FROM Processus";
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
    public List searchMatching(Processus valueObject) throws SQLException {

          List searchResults;

          boolean first = true;
          StringBuilder sql = new StringBuilder("SELECT * FROM Processus WHERE 1=1 ");

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

          if (valueObject.getUser() != null) {
              if (first) { first = false; }
              sql.append("AND user LIKE '").append(valueObject.getUser()).append("%' ");
          }

          if (valueObject.getPid() != 0) {
              if (first) { first = false; }
              sql.append("AND pid LIKE '").append(valueObject.getPid()).append("%' ");
          }

          if (valueObject.getPerc_cpu() != null) {
              if (first) { first = false; }
              sql.append("AND perc_cpu LIKE '").append(valueObject.getPerc_cpu()).append("%' ");
          }

          if (valueObject.getPerc_memoire() != null) {
              if (first) { first = false; }
              sql.append("AND perc_memoire LIKE '").append(valueObject.getPerc_memoire()).append("%' ");
          }

          if (valueObject.getGateway_id() != 0) {
              if (first) { first = false; }
              sql.append("AND gateway_id LIKE '").append(valueObject.getGateway_id()).append("%' ");
          }

          if (valueObject.getVsz() != null) {
              if (first) { first = false; }
              sql.append("AND vsz LIKE '").append(valueObject.getVsz()).append("%' ");
          }

          if (valueObject.getRSS() != null) {
              if (first) { first = false; }
              sql.append("AND rss LIKE '").append(valueObject.getRSS()).append("%' ");
          }

          if (valueObject.getTty() != null) {
              if (first) { first = false; }
              sql.append("AND tty LIKE '").append(valueObject.getTty()).append("%' ");
          }

          if (valueObject.getStat() != null) {
              if (first) { first = false; }
              sql.append("AND stat LIKE '").append(valueObject.getStat()).append("%' ");
          }

          if (valueObject.getStart() != null) {
              if (first) { first = false; }
              sql.append("AND start LIKE '").append(valueObject.getStart()).append("%' ");
          }

          if (valueObject.getTime() != null) {
              if (first) { first = false; }
              sql.append("AND time LIKE '").append(valueObject.getTime()).append("%' ");
          }

          if (valueObject.getCommand() != null) {
              if (first) { first = false; }
              sql.append("AND command LIKE '").append(valueObject.getCommand()).append("%' ");
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
    protected void singleQuery(PreparedStatement stmt, Processus valueObject) 

            throws NotFoundException, SQLException {


        try (ResultSet result = stmt.executeQuery()) {

              if (result.next()) {

                   valueObject.setId(result.getInt("id")); 
                   valueObject.setLabel(result.getString("label")); 
                   valueObject.setDate(result.getString("_date")); 
                   valueObject.setUser(result.getString("user")); 
                   valueObject.setPid(result.getInt("pid")); 
                   valueObject.setPerc_cpu(result.getString("perc_cpu")); 
                   valueObject.setPerc_memoire(result.getString("perc_memoire")); 
                   valueObject.setGateway_id(result.getLong("gateway_id")); 
                   valueObject.setVsz(result.getString("vsz")); 
                   valueObject.setRSS(result.getString("rss")); 
                   valueObject.setTty(result.getString("tty")); 
                   valueObject.setStat(result.getString("stat")); 
                   valueObject.setStart(result.getString("start")); 
                   valueObject.setTime(result.getString("time")); 
                   valueObject.setCommand(result.getString("command")); 
                   valueObject.setList(result.getInt("list")); 

              } else {
                    //System.out.println("Processus Object Not Found!");
                    throw new NotFoundException("Processus Object Not Found!");
              }
          } finally {
              if (stmt != null)
                  stmt.close();
          }
    }


    /**
     * databaseQuery-method. This method is a helper method for internal use. It will execute
     * all database queries that will return multiple rows. The resultset will be converted
     * to the List of valueObjects. If no rows were found, an empty List will be returned.
     *
     * @param stmt         This parameter contains the SQL statement to be excuted.
     * @return 
     * @throws java.sql.SQLException
     */
    protected List listQuery(PreparedStatement stmt) throws SQLException {

          ArrayList searchResults = new ArrayList();
          try (ResultSet result = stmt.executeQuery()) {

              while (result.next()) {
                   Processus temp = createValueObject();

                   temp.setId(result.getInt("id")); 
                   temp.setLabel(result.getString("label")); 
                   temp.setDate(result.getString("_date")); 
                   temp.setUser(result.getString("user")); 
                   temp.setPid(result.getInt("pid")); 
                   temp.setPerc_cpu(result.getString("perc_cpu")); 
                   temp.setPerc_memoire(result.getString("perc_memoire")); 
                   temp.setGateway_id(result.getLong("gateway_id")); 
                   temp.setVsz(result.getString("vsz")); 
                   temp.setRSS(result.getString("rss")); 
                   temp.setTty(result.getString("tty")); 
                   temp.setStat(result.getString("stat")); 
                   temp.setStart(result.getString("start")); 
                   temp.setTime(result.getString("time")); 
                   temp.setCommand(result.getString("command")); 
                   temp.setList(result.getInt("list")); 

                   searchResults.add(temp);
              }

          } finally {
              if (stmt != null)
                  stmt.close();
          }

          return (List)searchResults;
    }


}

