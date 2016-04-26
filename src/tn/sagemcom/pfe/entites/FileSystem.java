/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.sagemcom.pfe.entites;

/**
 *
 * @author Issam BenBelgacem
 */
public class FileSystem extends Performance{
    private String size;
    private String utilise;
    private String monted_in;
    private int perc_utilisie;
    private String disponible;

    public void setDisponible(String disponible) {
        this.disponible = disponible;
    }

    public String getDisponible() {
        return disponible;
    }
    

    public FileSystem() {
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getUtilise() {
        return utilise;
    }

    public void setUtilise(String utilise) {
        this.utilise = utilise;
    }

    public String getMonted_in() {
        return monted_in;
    }

    public void setMonted_in(String monted_in) {
        this.monted_in = monted_in;
    }

    public int getPerc_utilisie() {
        return perc_utilisie;
    }

    public void setPerc_utilisie(int  perc_utilisie) {
        this.perc_utilisie = perc_utilisie;
    }
    @Override
    public String toString() {
        return "FileSystem{" + "size=" + size + ", utilise=" + utilise + ", monted_in=" + monted_in + ", perc_utilisie=" + perc_utilisie + ", disponible=" + disponible + '}';
    }

    @Override
    public int Regle() {
        
         double percentage=Double.parseDouble(String.valueOf(getPerc_utilisie()));
    if(percentage<50){
        System.out.println(" Niveau 1 "+percentage+ "%");
        return 1;
    }else if(percentage>=50 && percentage<75)
    {
        System.out.println(" Niveau 2 "+percentage +"%");
        return 2;
    }else{
        System.err.println(" Niveau 3 Alert "+percentage +"%"); 
        return 3;
    }
    }
    
}
