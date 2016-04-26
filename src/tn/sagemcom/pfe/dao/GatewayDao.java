/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.sagemcom.pfe.dao;

import tn.sagemcom.pfe.utils.MyConnection;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import tn.sagemcom.pfe.exception.NotFoundException;
import tn.sagemcom.pfe.entites.Gateway;
import tn.sagemcom.pfe.utils.MyLogger;

/**
 * Gateway Data Access Object (DAO). This class contains all database handling
 * that is needed to permanently store and retrieve Gateway object instances.
 */
public class GatewayDao {

    private final Connection connection = MyConnection.getInstance();

    /**
     * createValueObject-method. This method is used when the Dao class needs to
     * create new value object instance. The reason why this method exists is
     * that sometimes the programmer may want to extend also the valueObject and
     * then this method can be overrided to return extended valueObject. NOTE:
     * If you extend the valueObject class, make sure to override the clone()
     * method in it!
     *
     * @return
     */
    public Gateway createValueObject() {
        return new Gateway();
    }

    /**
     * getObject-method. This will create and load valueObject contents from
     * database using given Primary-Key as identifier. This method is just a
     * convenience method for the real load-method which accepts the valueObject
     * as a parameter. Returned valueObject will be created using the
     * createValueObject() method.
     *
     * @param id
     * @return
     * @throws tn.sagemcom.pfe.exception.NotFoundException
     * @throws java.sql.SQLException
     */
    public Gateway getObject(int id) throws NotFoundException, SQLException {

        Gateway valueObject = createValueObject();
        valueObject.setId(id);
        load(valueObject);
        return valueObject;
    }

    /**
     * load-method. This will load valueObject contents from database using
     * Primary-Key as identifier. Upper layer should use this so that
     * valueObject instance is created and only primary-key should be specified.
     * Then call this method to complete other persistent information. This
     * method will overwrite all other fields except primary-key and possible
     * runtime variables. If load can not find matching row, NotFoundException
     * will be thrown.
     *
     * @param valueObject This parameter contains the class instance to be
     * loaded. Primary-key field must be set for this to work properly.
     * @throws tn.sagemcom.pfe.exception.NotFoundException
     * @throws java.sql.SQLException
     */
    public void load(Gateway valueObject) throws NotFoundException, SQLException {

        String sql = "SELECT * FROM gateway WHERE (id = ? ) ";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, valueObject.getId());

            singleQuery(stmt, valueObject);

        }
    }

    /**
     * load-method. This will load valueObject contents from database using
     * Primary-Key as identifier. Upper layer should use this so that
     * valueObject instance is created and only primary-key should be specified.
     * Then call this method to complete other persistent information. This
     * method will overwrite all other fields except primary-key and possible
     * runtime variables. If load can not find matching row, NotFoundException
     * will be thrown.
     *
     * @param id This parameter contains the class instance to be loaded.
     * Primary-key field must be set for this to work properly.
     * @return
     * @throws tn.sagemcom.pfe.exception.NotFoundException
     * @throws java.sql.SQLException
     */
    public boolean IsExsit(long id) throws NotFoundException, SQLException {

        String sql = "SELECT * FROM gateway WHERE (id = ? ) ";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);

            return stmt.executeQuery().first();

        }
    }

    /**
     * LoadAll-method. This will read all contents from database table and build
     * a List containing valueObjects. Please note, that this method will
     * consume huge amounts of resources if table has lot's of rows. This should
     * only be used when target tables have only small amounts of data.
     *
     * @return
     * @throws java.sql.SQLException
     */
    public List loadAll() throws SQLException {

        String sql = "SELECT * FROM gateway ORDER BY id ASC ";
        List searchResults = listQuery(connection.prepareStatement(sql));

        return searchResults;
    }

    /**
     * create-method. This will create new row in database according to supplied
     * valueObject contents. Make sure that values for all NOT NULL columns are
     * correctly specified. Also, if this table does not use automatic
     * surrogate-keys the primary-key must be specified. After INSERT command
     * this method will read the generated primary-key back to valueObject if
     * automatic surrogate-keys were used.
     *
     * @param valueObject This parameter contains the class instance to be
     * created. If automatic surrogate-keys are not used the Primary-key field
     * must be set for this to work properly.
     * @throws java.sql.SQLException
     */
    public synchronized void create(Gateway valueObject) throws SQLException {

        String sql = "";
        PreparedStatement stmt = null;
        ResultSet result = null;

        try {
            sql = "INSERT INTO gateway ( label, ipAdress, filepath,person_id) VALUES (?, ?, ?,?) ";
            stmt = connection.prepareStatement(sql);

            stmt.setString(1, valueObject.getLabel());
            stmt.setString(2, valueObject.getIpAdress());
            stmt.setString(3, valueObject.getFilepath());
            stmt.setInt(4, valueObject.getUser_id());
            int rowcount = databaseUpdate(stmt);
            if (rowcount != 1) {
                //System.out.println("PrimaryKey Error when updating DB!");
                throw new SQLException("PrimaryKey Error when updating DB!");
            }

        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }

        /**
         * The following query will read the automatically generated primary key
         * back to valueObject. This must be done to make things consistent for
         * upper layer processing logic.
         */
        sql = "SELECT last_insert_id()";

        try {
            stmt = connection.prepareStatement(sql);
            result = stmt.executeQuery();

            if (result.next()) {

                valueObject.setId((int) result.getLong(1));

            } else {
                //System.out.println("Unable to find primary-key for created object!");
                throw new SQLException("Unable to find primary-key for created object!");
            }
        } finally {
            if (result != null) {
                result.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }

    }

    /**
     * save-method. This method will save the current state of valueObject to
     * database. Save can not be used to create new instances in database, so
     * upper layer must make sure that the primary-key is correctly specified.
     * Primary-key will indicate which instance is going to be updated in
     * database. If save can not find matching row, NotFoundException will be
     * thrown.
     *
     * @param valueObject This parameter contains the class instance to be
     * saved. Primary-key field must be set for this to work properly.
     * @throws tn.sagemcom.pfe.exception.NotFoundException
     * @throws java.sql.SQLException
     */
    public void save(Gateway valueObject)
            throws NotFoundException, SQLException {

        String sql = "UPDATE gateway SET label = ?, ipAdress = ?, filepath = ?"
                + ",person_id = ? ,lastwatchdog = ?, nbrwatchdog = ?,lastinfo=? ,"
                + "nbreboot=?,lastreboot=?,lastuptime=? WHERE (id = ? ) ";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, valueObject.getLabel());
            stmt.setString(2, valueObject.getIpAdress());
            stmt.setString(3, valueObject.getFilepath());
            stmt.setInt(4, valueObject.getUser_id());
            stmt.setString(5, valueObject.getLastwatchdog());
            stmt.setInt(6, valueObject.getNbrwatchdog());
            stmt.setString(7, valueObject.getLastinfo());
            
stmt.setInt(8, valueObject.getNbreboot());
            stmt.setString(9, valueObject.getLastreboot());

            stmt.setString(10, valueObject.getLastuptime());
            stmt.setInt(11, valueObject.getId());
            int rowcount = databaseUpdate(stmt);
            if (rowcount == 0) {
                //System.out.println("Object could not be saved! (PrimaryKey not found)");
                throw new NotFoundException("Object could not be saved! (PrimaryKey not found)");
            }
            if (rowcount > 1) {
                //System.out.println("PrimaryKey Error when updating DB! (Many objects were affected!)");
                throw new SQLException("PrimaryKey Error when updating DB! (Many objects were affected!)");
            }
        }
    }

    /**
     * delete-method. This method will remove the information from database as
     * identified by by primary-key in supplied valueObject. Once valueObject
     * has been deleted it can not be restored by calling save. Restoring can
     * only be done using create method but if database is using automatic
     * surrogate-keys, the resulting object will have different primary-key than
     * what it was in the deleted object. If delete can not find matching row,
     * NotFoundException will be thrown.
     *
     * @param valueObject This parameter contains the class instance to be
     * deleted. Primary-key field must be set for this to work properly.
     * @throws tn.sagemcom.pfe.exception.NotFoundException
     * @throws java.sql.SQLException
     */
    public void delete(Gateway valueObject)
            throws NotFoundException, SQLException {

        String sql = "DELETE FROM gateway WHERE (id = ? ) ";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, valueObject.getId());

            int rowcount = databaseUpdate(stmt);
            if (rowcount == 0) {
                MyLogger.log(Level.WARNING, "Gateway could not be deleted! (PrimaryKey not found)");
                throw new NotFoundException("Object could not be deleted! (PrimaryKey not found)");
            }
            if (rowcount > 1) {
                throw new SQLException("PrimaryKey Error when updating DB! (Many objects were deleted!)");
            }
        }
    }

    /**
     * deleteAll-method. This method will remove all information from the table
     * that matches this Dao and ValueObject couple. This should be the most
     * efficient way to clear table. Once deleteAll has been called, no
     * valueObject that has been created before can be restored by calling save.
     * Restoring can only be done using create method but if database is using
     * automatic surrogate-keys, the resulting object will have different
     * primary-key than what it was in the deleted object. (Note, the
     * implementation of this method should be different with different DB
     * backends.)
     *
     * @throws java.sql.SQLException
     */
    public void deleteAll() throws SQLException {

        String sql = "DELETE FROM gateway";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            int rowcount = databaseUpdate(stmt);
            MyLogger.log(Level.INFO, rowcount + " Rows Deleted from Gateway Table");

        }
    }

    /**
     * coutAll-method. This method will return the number of all rows from table
     * that matches this Dao. The implementation will simply execute "select
     * count(primarykey) from table". If table is empty, the return value is 0.
     * This method should be used before calling loadAll, to make sure table has
     * not too many rows.
     *
     * @return
     * @throws java.sql.SQLException
     */
    public int countAll() throws SQLException {

        String sql = "SELECT count(*) FROM gateway";
        PreparedStatement stmt = null;
        ResultSet result = null;
        int allRows = 0;

        try {
            stmt = connection.prepareStatement(sql);
            result = stmt.executeQuery();

            if (result.next()) {
                allRows = result.getInt(1);
            }
        } finally {
            if (result != null) {
                result.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }
        return allRows;
    }

    /**
     * searchMatching-Method. This method provides searching capability to get
     * matching valueObjects from database. It works by searching all objects
     * that match permanent instance variables of given object. Upper layer
     * should use this by setting some parameters in valueObject and then call
     * searchMatching. The result will be 0-N objects in a List, all matching
     * those criteria you specified. Those instance-variables that have NULL
     * values are excluded in search-criteria.
     *
     * @param valueObject This parameter contains the class instance where
     * search will be based. Primary-key field should not be set.
     * @return
     * @throws java.sql.SQLException
     */
    public List searchMatching(Gateway valueObject) throws SQLException {

        List searchResults;

        boolean first = true;
        StringBuilder sql = new StringBuilder("SELECT * FROM gateway WHERE 1=1 ");

        if (valueObject.getId() != 0) {
            if (first) {
                first = false;
            }
            sql.append("AND id = ").append(valueObject.getId()).append(" ");
        }

        if (valueObject.getLabel() != null) {
            if (first) {
                first = false;
            }
            sql.append("AND label LIKE '").append(valueObject.getLabel()).append("%' ");
        }

        if (valueObject.getIpAdress() != null) {
            if (first) {
                first = false;
            }
            sql.append("AND ipAdress LIKE '").append(valueObject.getIpAdress()).append("%' ");
        }

        if (valueObject.getFilepath() != null) {
            if (first) {
                first = false;
            }
            sql.append("AND filepath LIKE '").append(valueObject.getFilepath()).append("%' ");
        }
        if (valueObject.getUser_id() > 0) {
            if (first) {
                first = false;
            }
            sql.append("AND person_id LIKE '").append(valueObject.getUser_id()).append("%' ");
        }

        sql.append("ORDER BY id ASC ");

        // Prevent accidential full table results.
        // Use loadAll if all rows must be returned.
        if (first) {
            searchResults = new ArrayList();
        } else {
            searchResults = listQuery(connection.prepareStatement(sql.toString()));
        }

        return searchResults;
    }

    /**
     * databaseUpdate-method. This method is a helper method for internal use.
     * It will execute all database handling that will change the information in
     * tables. SELECT queries will not be executed here however. The return
     * value indicates how many rows were affected. This method will also make
     * sure that if cache is used, it will reset when data changes.
     *
     * @param stmt This parameter contains the SQL statement to be excuted.
     * @return
     * @throws java.sql.SQLException
     */
    protected int databaseUpdate(PreparedStatement stmt) throws SQLException {

        int result = stmt.executeUpdate();

        return result;
    }

    /**
     * databaseQuery-method. This method is a helper method for internal use. It
     * will execute all database queries that will return only one row. The
     * resultset will be converted to valueObject. If no rows were found,
     * NotFoundException will be thrown.
     *
     * @param stmt This parameter contains the SQL statement to be excuted.
     * @param valueObject Class-instance where resulting data will be stored.
     * @throws tn.sagemcom.pfe.exception.NotFoundException
     * @throws java.sql.SQLException
     */
    protected void singleQuery(PreparedStatement stmt, Gateway valueObject)
            throws NotFoundException, SQLException {

        try (ResultSet result = stmt.executeQuery()) {

            if (result.next()) {
                valueObject.setId(result.getInt("id"));
                valueObject.setLabel(result.getString("label"));
                valueObject.setIpAdress(result.getString("ipAdress"));
                valueObject.setFilepath(result.getString("filepath"));
                valueObject.setUser_id(result.getInt("person_id"));
            } else {
                //System.out.println("Gateway Object Not Found!");
                throw new NotFoundException("Gateway Object Not Found!");
            }
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    /**
     * databaseQuery-method. This method is a helper method for internal use. It
     * will execute all database queries that will return multiple rows. The
     * resultset will be converted to the List of valueObjects. If no rows were
     * found, an empty List will be returned.
     *
     * @param stmt This parameter contains the SQL statement to be excuted.
     * @return
     * @throws java.sql.SQLException
     */
    protected List listQuery(PreparedStatement stmt) throws SQLException {

        ArrayList searchResults = new ArrayList();
        try (ResultSet result = stmt.executeQuery()) {

            while (result.next()) {
                Gateway temp = createValueObject();

                temp.setId(result.getInt("id"));
                temp.setLabel(result.getString("label"));
                temp.setIpAdress(result.getString("ipAdress"));
                temp.setFilepath(result.getString("filepath"));
                temp.setUser_id(result.getInt("person_id"));

                searchResults.add(temp);
            }

        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }

        return (List) searchResults;
    }

}
