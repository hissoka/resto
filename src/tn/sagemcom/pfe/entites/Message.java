package tn.sagemcom.pfe.entites;;


import java.io.*;

 /**
  * Message Value Object.
  * This class is value object representing database table Message
  * This class is intented to be used together with associated Dao object.
  */

 


public class Message implements Cloneable, Serializable {

    /** 
     * Persistent Instance variables. This data is directly 
     * mapped to the columns of database table.
     */
    private int id;
    private int personId;
    private int gatewayId;
    private String cause;
    private String source;
    private String _date;
    private String readed;
    private int niveau;



   

    public Message () {

    }

    public Message (int idIn) {

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

    public int getPersonId() {
          return this.personId;
    }
    public void setPersonId(int personIdIn) {
          this.personId = personIdIn;
    }

    public int getGatewayId() {
          return this.gatewayId;
    }
    public void setGatewayId(int gatewayIdIn) {
          this.gatewayId = gatewayIdIn;
    }

    public String getCause() {
          return this.cause;
    }
    public void setCause(String causeIn) {
          this.cause = causeIn;
    }

    public String getSouce() {
          return this.source;
    }
    public void setSouce(String souceIn) {
          this.source = souceIn;
    }

    public String get_date() {
          return this._date;
    }
    public void set_date(String _dateIn) {
          this._date = _dateIn;
    }

    public String getReaded() {
          return this.readed;
    }
    public void setReaded(String readedIn) {
          this.readed = readedIn;
    }

    public int getNiveau() {
          return this.niveau;
    }
    public void setNiveau(int niveauIn) {
          this.niveau = niveauIn;
    }



    /** 
     * setAll allows to set all persistent variables in one method call.
     * This is useful, when all data is available and it is needed to 
     * set the initial state of this object. Note that this method will
     * directly modify instance variales, without going trough the 
     * individual set-methods.
     */

    public void setAll(int idIn,
          int personIdIn,
          int gatewayIdIn,
          String causeIn,
          String souceIn,
          String _dateIn,
          String readedIn,
          int niveauIn) {
          this.id = idIn;
          this.personId = personIdIn;
          this.gatewayId = gatewayIdIn;
          this.cause = causeIn;
          this.source = souceIn;
          this._date = _dateIn;
          this.readed = readedIn;
          this.niveau = niveauIn;
    }


    /** 
     * hasEqualMapping-method will compare two Message instances
     * and return true if they contain same values in all persistent instance 
     * variables. If hasEqualMapping returns true, it does not mean the objects
     * are the same instance. However it does mean that in that moment, they 
     * are mapped to the same row in database.
     */
    public boolean hasEqualMapping(Message valueObject) {

          if (valueObject.getId() != this.id) {
                    return(false);
          }
          if (valueObject.getPersonId() != this.personId) {
                    return(false);
          }
          if (valueObject.getGatewayId() != this.gatewayId) {
                    return(false);
          }
          if (this.cause == null) {
                    if (valueObject.getCause() != null)
                           return(false);
          } else if (!this.cause.equals(valueObject.getCause())) {
                    return(false);
          }
          if (this.source == null) {
                    if (valueObject.getSouce() != null)
                           return(false);
          } else if (!this.source.equals(valueObject.getSouce())) {
                    return(false);
          }
          if (this._date == null) {
                    if (valueObject.get_date() != null)
                           return(false);
          } else if (!this._date.equals(valueObject.get_date())) {
                    return(false);
          }
          if (this.readed == null) {
                    if (valueObject.getReaded() != null)
                           return(false);
          } else if (!this.readed.equals(valueObject.getReaded())) {
                    return(false);
          }
          if (valueObject.getNiveau() != this.niveau) {
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
        StringBuffer out = new StringBuffer(" Sagemcom 2016");
        out.append("\nclass Message, mapping to table Message\n");
        out.append("Persistent attributes: \n"); 
        out.append("id = " + this.id + "\n"); 
        out.append("personId = " + this.personId + "\n"); 
        out.append("gatewayId = " + this.gatewayId + "\n"); 
        out.append("cause = " + this.cause + "\n"); 
        out.append("souce = " + this.source + "\n"); 
        out.append("_date = " + this._date + "\n"); 
        out.append("readed = " + this.readed + "\n"); 
        out.append("niveau = " + this.niveau + "\n"); 
        return out.toString();
    }


    /**
     * Clone will return identical deep copy of this valueObject.
     * Note, that this method is different than the clone() which
     * is defined in java.lang.Object. Here, the retuned cloned object
     * will also have all its attributes cloned.
     */
    public Object clone() {
        Message cloned = new Message();

        cloned.setId(this.id); 
        cloned.setPersonId(this.personId); 
        cloned.setGatewayId(this.gatewayId); 
        if (this.cause != null)
             cloned.setCause(new String(this.cause)); 
        if (this.source != null)
             cloned.setSouce(new String(this.source)); 
        if (this._date != null)
             cloned.set_date(new String(this._date)); 
        if (this.readed != null)
             cloned.setReaded(new String(this.readed)); 
        cloned.setNiveau(this.niveau); 
        return cloned;
    }



}

