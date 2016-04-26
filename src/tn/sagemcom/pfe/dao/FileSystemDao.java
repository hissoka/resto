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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import tn.sagemcom.pfe.entites.FileSystem;
import tn.sagemcom.pfe.exception.NotFoundException;
import tn.sagemcom.pfe.utils.MyLogger;

/**
 *
 * @author Issam BenBelgacem
 */
public class FileSystemDao {

    private Connection connection = MyConnection.getInstance();

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
    public FileSystem createValueObject() {
        return new FileSystem();
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
    public FileSystem getObject(int id) throws NotFoundException, SQLException {
        FileSystem valueObject = createValueObject();
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
    public void load(FileSystem valueObject) throws NotFoundException, SQLException {

        String sql = "SELECT * FROM FileSystem WHERE (id = ? ) ";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, valueObject.getId());

            singleQuery(stmt, valueObject);

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
        return listQuery(connection.prepareStatement("SELECT * FROM FileSystem ORDER BY id ASC "));
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
    public synchronized void create(FileSystem valueObject) throws SQLException {

        String sql = "";
        PreparedStatement stmt = null;

        try {
            sql = "INSERT INTO FileSystem ( id, label, _date, "
                    + "size, utilise, monted_in, "
                    + "perc_utilisie, gateway_id, disponible) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) ";
            stmt = connection.prepareStatement(sql);

            stmt.setInt(1, valueObject.getId());
            stmt.setString(2, valueObject.getLabel());
            stmt.setString(3, valueObject.getDate());
            stmt.setString(4, valueObject.getSize());
            stmt.setString(5, valueObject.getUtilise());
            stmt.setString(6, valueObject.getMonted_in());
            stmt.setInt(7, valueObject.getPerc_utilisie());
            stmt.setLong(8, valueObject.getGateway_id());
            stmt.setString(9, valueObject.getDisponible());

            int rowcount = databaseUpdate(stmt);
            if (rowcount != 1) {
                throw new SQLException("PrimaryKey Error when updating DB!");
            }

        } finally {
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
    public void save(FileSystem valueObject)
            throws NotFoundException, SQLException {

        String sql = "UPDATE FileSystem SET label = ?, _date = ?, size = ?, "
                + "utilise = ?, monted_in = ?, perc_utilisie = ?, "
                + "gateway_id = ?, disponible = ? WHERE (id = ? ) ";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, valueObject.getLabel());
            stmt.setString(2, valueObject.getDate());
            stmt.setString(3, valueObject.getSize());
            stmt.setString(4, valueObject.getUtilise());
            stmt.setString(5, valueObject.getMonted_in());
            stmt.setInt(6, valueObject.getPerc_utilisie());
            stmt.setLong(7, valueObject.getGateway_id());
            stmt.setString(8, valueObject.getDisponible());

            stmt.setInt(9, valueObject.getId());

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
    public void delete(FileSystem valueObject)
            throws NotFoundException, SQLException {

        String sql = "DELETE FROM FileSystem WHERE (id = ? ) ";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, valueObject.getId());

            int rowcount = databaseUpdate(stmt);
            if (rowcount == 0) {
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

        String sql = "DELETE FROM FileSystem";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            int rowcount = databaseUpdate(stmt);
            MyLogger.log(Level.INFO, rowcount + " Rows Deleted from FileSystem Table");
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

        String sql = "SELECT count(*) FROM FileSystem";
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
    public List searchMatching(FileSystem valueObject) throws SQLException {

        List searchResults;

        boolean first = true;
        StringBuilder sql = new StringBuilder("SELECT * FROM FileSystem WHERE 1=1 ");

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

        if (valueObject.getDate() != null) {
            if (first) {
                first = false;
            }
            sql.append("AND _date LIKE '").append(valueObject.getDate()).append("%' ");
        }

        if (valueObject.getSize() != null) {
            if (first) {
                first = false;
            }
            sql.append("AND size LIKE '").append(valueObject.getSize()).append("%' ");
        }

        if (valueObject.getUtilise() != null) {
            if (first) {
                first = false;
            }
            sql.append("AND utilise LIKE '").append(valueObject.getUtilise()).append("%' ");
        }

        if (valueObject.getMonted_in() != null) {
            if (first) {
                first = false;
            }
            sql.append("AND monted_in LIKE '").append(valueObject.getMonted_in()).append("%' ");
        }

        if (valueObject.getPerc_utilisie() != 0) {
            if (first) {
                first = false;
            }
            sql.append("AND perc_utilisie LIKE '").append(valueObject.getPerc_utilisie()).append("%' ");
        }

        if (valueObject.getGateway_id() != 0) {
            if (first) {
                first = false;
            }
            sql.append("AND gateway_id LIKE '").append(valueObject.getGateway_id()).append("%' ");
        }

        if (valueObject.getDisponible() != null) {
            if (first) {
                first = false;
            }
            sql.append("AND disponible LIKE '").append(valueObject.getDisponible()).append("%' ");
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
    protected void singleQuery(PreparedStatement stmt, FileSystem valueObject)
            throws NotFoundException, SQLException {

        try (ResultSet result = stmt.executeQuery()) {

            if (result.next()) {

                valueObject.setId(result.getInt("id"));
                valueObject.setLabel(result.getString("label"));
                valueObject.setDate(result.getString("_date"));
                valueObject.setSize(result.getString("size"));
                valueObject.setUtilise(result.getString("utilise"));
                valueObject.setMonted_in(result.getString("monted_in"));
                valueObject.setPerc_utilisie(result.getInt("perc_utilisie"));
                valueObject.setGateway_id(result.getLong("gateway_id"));
                valueObject.setDisponible(result.getString("disponible"));

            } else {
                //System.out.println("FileSystem Object Not Found!");
                throw new NotFoundException("FileSystem Object Not Found!");
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
                FileSystem temp = createValueObject();

                temp.setId(result.getInt("id"));
                temp.setLabel(result.getString("label"));
                temp.setDate(result.getString("_date"));
                temp.setSize(result.getString("size"));
                temp.setUtilise(result.getString("utilise"));
                temp.setMonted_in(result.getString("monted_in"));
                temp.setPerc_utilisie(result.getInt("perc_utilisie"));
                temp.setGateway_id(result.getLong("gateway_id"));
                temp.setDisponible(result.getString("disponible"));

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
