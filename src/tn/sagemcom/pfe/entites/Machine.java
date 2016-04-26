package tn.sagemcom.pfe.entites;


import java.io.*;
import java.sql.*;
import java.util.*;
import java.math.*;

 /**
  * Machine Value Object.
  * This class is value object representing database table machines
  * This class is intented to be used together with associated Dao object.
  */





public class Machine implements Cloneable, Serializable {

    /** 
     * Persistent Instance variables. This data is directly 
     * mapped to the columns of database table.
     */
    private int id;
    private String macaddress;
    private String ipaddress;
    private String _type;
    private String device;
    private String _date;
    private int gateway_id;



    /** 
     * Constructors. DaoGen generates two constructors by default.
     * The first one takes no arguments and provides the most simple
     * way to create object instance. The another one takes one
     * argument, which is the primary key of the corresponding table.
     */

    public Machine () {

    }

    public Machine (int idIn, String macaddressIn) {

          this.id = idIn;
          this.macaddress = macaddressIn;

    }


    /** 
     * Get- and Set-methods for persistent variables. The default
     * behaviour does not make any checks against malformed data,
     * so these might require some manual additions.
     */

    public int getId() {
          return this.id;
    }
    public void setId(int idIn) {
          this.id = idIn;
    }

    public String getMacaddress() {
          return this.macaddress;
    }
    public void setMacaddress(String macaddressIn) {
          this.macaddress = macaddressIn;
    }

    public String getIpaddress() {
          return this.ipaddress;
    }
    public void setIpaddress(String ipaddressIn) {
          this.ipaddress = ipaddressIn;
    }

    public String get_type() {
          return this._type;
    }
    public void set_type(String _typeIn) {
          this._type = _typeIn;
    }

    public String getDevice() {
          return this.device;
    }
    public void setDevice(String deviceIn) {
          this.device = deviceIn;
    }

    public String get_date() {
          return this._date;
    }
    public void set_date(String _dateIn) {
          this._date = _dateIn;
    }

    public int getGateway_id() {
          return this.gateway_id;
    }
    public void setGateway_id(int gateway_idIn) {
          this.gateway_id = gateway_idIn;
    }



    /** 
     * setAll allows to set all persistent variables in one method call.
     * This is useful, when all data is available and it is needed to 
     * set the initial state of this object. Note that this method will
     * directly modify instance variales, without going trough the 
     * individual set-methods.
     */

    public void setAll(int idIn,
          String macaddressIn,
          String ipaddressIn,
          String _typeIn,
          String deviceIn,
          String _dateIn,
          int gateway_idIn) {
          this.id = idIn;
          this.macaddress = macaddressIn;
          this.ipaddress = ipaddressIn;
          this._type = _typeIn;
          this.device = deviceIn;
          this._date = _dateIn;
          this.gateway_id = gateway_idIn;
    }


    /** 
     * hasEqualMapping-method will compare two Machine instances
     * and return true if they contain same values in all persistent instance 
     * variables. If hasEqualMapping returns true, it does not mean the objects
     * are the same instance. However it does mean that in that moment, they 
     * are mapped to the same row in database.
     */
    public boolean hasEqualMapping(Machine valueObject) {

          if (valueObject.getId() != this.id) {
                    return(false);
          }
          if (valueObject.getMacaddress() != this.macaddress) {
                    return(false);
          }
          if (this.ipaddress == null) {
                    if (valueObject.getIpaddress() != null)
                           return(false);
          } else if (!this.ipaddress.equals(valueObject.getIpaddress())) {
                    return(false);
          }
          if (this._type == null) {
                    if (valueObject.get_type() != null)
                           return(false);
          } else if (!this._type.equals(valueObject.get_type())) {
                    return(false);
          }
          if (this.device == null) {
                    if (valueObject.getDevice() != null)
                           return(false);
          } else if (!this.device.equals(valueObject.getDevice())) {
                    return(false);
          }
          if (this._date == null) {
                    if (valueObject.get_date() != null)
                           return(false);
          } else if (!this._date.equals(valueObject.get_date())) {
                    return(false);
          }
          if (valueObject.getGateway_id() != this.gateway_id) {
                    return(false);
          }

          return true;
    }



    /**
     * toString will return String object representing the state of this 
     * valueObject. This is useful during application development, and 
     * possibly when application is writing object states in textlog.
     */
    public String toString() {
        StringBuffer out = new StringBuffer("SagemCom Monitoring V1.0");
        out.append("\nclass Machine, mapping to table machines\n");
        out.append("Persistent attributes: \n"); 
        out.append("id = " + this.id + "\n"); 
        out.append("macaddress = " + this.macaddress + "\n"); 
        out.append("ipaddress = " + this.ipaddress + "\n"); 
        out.append("_type = " + this._type + "\n"); 
        out.append("device = " + this.device + "\n"); 
        out.append("_date = " + this._date + "\n"); 
        out.append("gateway_id = " + this.gateway_id + "\n"); 
        return out.toString();
    }


    /**
     * Clone will return identical deep copy of this valueObject.
     * Note, that this method is different than the clone() which
     * is defined in java.lang.Object. Here, the retuned cloned object
     * will also have all its attributes cloned.
     */
    public Object clone() {
        Machine cloned = new Machine();

        cloned.setId(this.id); 
        cloned.setMacaddress(this.macaddress); 
        if (this.ipaddress != null)
             cloned.setIpaddress(this.ipaddress); 
        if (this._type != null)
             cloned.set_type(this._type); 
        if (this.device != null)
             cloned.setDevice(this.device); 
        if (this._date != null)
             cloned.set_date(this._date); 
        cloned.setGateway_id(this.gateway_id); 
        return cloned;
    }



}