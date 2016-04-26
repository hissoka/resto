/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.sagemcom.pfe.utils;

import tn.sagemcom.pfe.entites.Memoire;
/**
 *
 * @author G507751
 */
public class Memoire_Utils {

    private  Memoire_Utils() {
    }
   
  public static boolean IsValid(Memoire memoire){
       
            if(memoire.getTotal()==0)return false;
           else if(memoire.getUsed()==0)return false;
            
           return true;
     
  }
}
