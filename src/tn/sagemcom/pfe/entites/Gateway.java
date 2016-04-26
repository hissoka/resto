/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.sagemcom.pfe.entites;


import java.io.*;
import java.util.ArrayList;
import java.util.List;

 /**
  * Gateway Value Object.
  * This class is value object representing database table gateway
  * This class is intented to be used together with associated Dao object.
  */




public class Gateway implements Cloneable, Serializable {

    /** 
     * Persistent Instance variables. This data is directly 
     * mapped to the columns of database table.
     */
    private int id;
    private String label;
    private String ipAdress;
    private String filepath;
    private int user_id;
    private int nbrwatchdog;
    private String lastwatchdog;
     private int nbreboot;
    private String lastreboot;
    private String lastuptime;
    private String lastinfo;
    private List<Memoire> memoires;
    private List<FileSystem> fileSystems=new ArrayList<>();
    private List<Processus> processuses=new ArrayList<>();
    public List<Memoire> getMemoires() {
        return memoires;
    }

    public void setLastinfo(String lastinfo) {
        this.lastinfo = lastinfo;
    }

    public String getLastinfo() {
        return lastinfo;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setProcessuses(List<Processus> processuses) {
        this.processuses = processuses;
    }

    public void setLastwatchdog(String lastwatchdog) {
        this.lastwatchdog = lastwatchdog;
    }

    public void setNbrwatchdog(int nbrwatchdog) {
        this.nbrwatchdog = nbrwatchdog;
    }

    
    public int getNbrwatchdog() {
        return nbrwatchdog;
    }

    public String getLastwatchdog() {
        return lastwatchdog;
    }

    public int getNbreboot() {
        return nbreboot;
    }

    public void setNbreboot(int nbreboot) {
        this.nbreboot = nbreboot;
    }

    public String getLastreboot() {
        return lastreboot;
    }

    public void setLastreboot(String lastwreboot) {
        this.lastreboot = lastwreboot;
    }

    public String getLastuptime() {
        return lastuptime;
    }

    public void setLastuptime(String lastuptime) {
        this.lastuptime = lastuptime;
    }

    public List<Processus> getProcessuses() {
        return processuses;
    }

    public void setMemoires(List<Memoire> memoires) {
        this.memoires = memoires;
    }

    public void setFileSystems(List<tn.sagemcom.pfe.entites.FileSystem> fileSystems) {
        this.fileSystems = fileSystems;
    }

    public List<tn.sagemcom.pfe.entites.FileSystem> getFileSystems() {
        return fileSystems;
    }



    
    public Gateway () {
memoires=new ArrayList<Memoire>();
    }

    public Gateway (int idIn) {
memoires=new ArrayList<Memoire>();
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

    public String getLabel() {
          return this.label;
    }
    public void setLabel(String labelIn) {
          this.label = labelIn;
    }

    public String getIpAdress() {
          return this.ipAdress;
    }
    public void setIpAdress(String ipAdressIn) {
          this.ipAdress = ipAdressIn;
    }

    public String getFilepath() {
          return this.filepath;
    }
    public void setFilepath(String filepathIn) {
          this.filepath = filepathIn;
    }



    /** 
     * setAll allows to set all persistent variables in one method call.
     * This is useful, when all data is available and it is needed to 
     * set the initial state of this object. Note that this method will
     * directly modify instance variales, without going trough the 
     * individual set-methods.
     */

    public void setAll(int idIn,
          String labelIn,
          String ipAdressIn,
          String filepathIn) {
          this.id = idIn;
          this.label = labelIn;
          this.ipAdress = ipAdressIn;
          this.filepath = filepathIn;
    }


    /** 
     * hasEqualMapping-method will compare two Gateway instances
     * and return true if they contain same values in all persistent instance 
     * variables. If hasEqualMapping returns true, it does not mean the objects
     * are the same instance. However it does mean that in that moment, they 
     * are mapped to the same row in database.
     */
    public boolean hasEqualMapping(Gateway valueObject) {

          if (valueObject.getId() != this.id) {
                    return(false);
          }
          if (this.label == null) {
                    if (valueObject.getLabel() != null)
                           return(false);
          } else if (!this.label.equals(valueObject.getLabel())) {
                    return(false);
          }
          if (this.ipAdress == null) {
                    if (valueObject.getIpAdress() != null)
                           return(false);
          } else if (!this.ipAdress.equals(valueObject.getIpAdress())) {
                    return(false);
          }
          if (this.filepath == null) {
                    if (valueObject.getFilepath() != null)
                           return(false);
          } else if (!this.filepath.equals(valueObject.getFilepath())) {
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
        StringBuffer out = new StringBuffer("");
        out.append("\nclass Gateway, mapping to table gateway\n");
        out.append("Persistent attributes: \n"); 
        out.append("id = " + this.id + "\n"); 
        out.append("label = " + this.label + "\n"); 
        out.append("ipAdress = " + this.ipAdress + "\n"); 
        out.append("filepath = " + this.filepath + "\n"); 
        return out.toString();
    }


    /**
     * Clone will return identical deep copy of this valueObject.
     * Note, that this method is different than the clone() which
     * is defined in java.lang.Object. Here, the retuned cloned object
     * will also have all its attributes cloned.
     */
    public Object clone() {
        Gateway cloned = new Gateway();

        cloned.setId(this.id); 
        if (this.label != null)
             cloned.setLabel(new String(this.label)); 
        if (this.ipAdress != null)
             cloned.setIpAdress(new String(this.ipAdress)); 
        if (this.filepath != null)
             cloned.setFilepath(new String(this.filepath)); 
        return cloned;
    }
/**
     * GetLastStat will return the last value of memory captured.
     * Note, that this method is different than the clone() which
     * is defined in java.lang.Object. Here, the retuned cloned object
     * will also have all its attributes cloned.
     * @return 
     */
public Memoire GetLastMemmoryStat(){
   return this.memoires.get(memoires.size()-1);
}
public FileSystem GetLastFileSystemStat(){
   return this.fileSystems.get(fileSystems.size()-1);
}
public Memoire getLastMemoireAlert(){
    Memoire last=null;
    for (Memoire memoire : memoires) {
        if(memoire.Regle()==3)last=memoire;
    }
    return last;
}
  

}
