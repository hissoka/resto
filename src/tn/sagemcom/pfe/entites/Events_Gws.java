/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.sagemcom.pfe.entites;

/**
 *
 * @author G507751
 */
public class Events_Gws {
 /** 
     * Persistent Instance variables. This data is directly 
     * mapped to the columns of database table.
     */
    private int id;
    private int gateway_id;
    private String label;
    private String _date;
    private String descr;



    /** 
     * Constructors. DaoGen generates two constructors by default.
     * The first one takes no arguments and provides the most simple
     * way to create object instance. The another one takes one
     * argument, which is the primary key of the corresponding table.
     */

    public Events_Gws () {

    }

    public Events_Gws (int idIn) {

          this.id = idIn;

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

    public int getGateway_id() {
          return this.gateway_id;
    }
    public void setGateway_id(int gateway_idIn) {
          this.gateway_id = gateway_idIn;
    }

    public String getLabel() {
          return this.label;
    }
    public void setLabel(String labelIn) {
          this.label = labelIn;
    }

    public String get_date() {
          return this._date;
    }
    public void set_date(String _dateIn) {
          this._date = _dateIn;
    }

    public String getDescr() {
          return this.descr;
    }
    public void setDescr(String descrIn) {
          this.descr = descrIn;
    }



    /** 
     * setAll allows to set all persistent variables in one method call.
     * This is useful, when all data is available and it is needed to 
     * set the initial state of this object. Note that this method will
     * directly modify instance variales, without going trough the 
     * individual set-methods.
     */

    public void setAll(int idIn,
          int gateway_idIn,
          String labelIn,
          String _dateIn,
          String descrIn) {
          this.id = idIn;
          this.gateway_id = gateway_idIn;
          this.label = labelIn;
          this._date = _dateIn;
          this.descr = descrIn;
    }


    /** 
     * hasEqualMapping-method will compare two Events_Gws instances
     * and return true if they contain same values in all persistent instance 
     * variables. If hasEqualMapping returns true, it does not mean the objects
     * are the same instance. However it does mean that in that moment, they 
     * are mapped to the same row in database.
     */
    public boolean hasEqualMapping(Events_Gws valueObject) {

          if (valueObject.getId() != this.id) {
                    return(false);
          }
          if (valueObject.getGateway_id() != this.gateway_id) {
                    return(false);
          }
          if (this.label == null) {
                    if (valueObject.getLabel() != null)
                           return(false);
          } else if (!this.label.equals(valueObject.getLabel())) {
                    return(false);
          }
          if (this._date == null) {
                    if (valueObject.get_date() != null)
                           return(false);
          } else if (!this._date.equals(valueObject.get_date())) {
                    return(false);
          }
          if (this.descr == null) {
                    if (valueObject.getDescr() != null)
                           return(false);
          } else if (!this.descr.equals(valueObject.getDescr())) {
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
        StringBuffer out = new StringBuffer("Sagemcom 2016");
        out.append("\nclass Events_Gws, mapping to table Event_gws\n");
        out.append("Persistent attributes: \n"); 
        out.append("id = " + this.id + "\n"); 
        out.append("gateway_id = " + this.gateway_id + "\n"); 
        out.append("label = " + this.label + "\n"); 
        out.append("_date = " + this._date + "\n"); 
        out.append("descr = " + this.descr + "\n"); 
        return out.toString();
    }


    /**
     * Clone will return identical deep copy of this valueObject.
     * Note, that this method is different than the clone() which
     * is defined in java.lang.Object. Here, the retuned cloned object
     * will also have all its attributes cloned.
     */
    public Object clone() {
        Events_Gws cloned = new Events_Gws();

        cloned.setId(this.id); 
        cloned.setGateway_id(this.gateway_id); 
        if (this.label != null)
             cloned.setLabel(new String(this.label)); 
        if (this._date != null)
             cloned.set_date(new String(this._date)); 
        if (this.descr != null)
             cloned.setDescr(new String(this.descr)); 
        return cloned;
    }


}
