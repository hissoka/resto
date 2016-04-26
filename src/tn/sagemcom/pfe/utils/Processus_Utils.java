/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.sagemcom.pfe.utils;

import tn.sagemcom.pfe.entites.Processus;

/**
 *
 * @author Issam BenBelgacem
 */
public class Processus_Utils {


    private Processus_Utils() {
    }

    public static boolean IsValid(Processus p) {
       
            if (p == null) {
                return false;
            } else if (p.getPid() < 0) {
                return false;
            } else if (p.getUser() == null) {
                return false;
            } else if (p.getCommand() == null) {
                return false;
            } else if (p.getPerc_cpu() == null) {
                return false;
            }else if (p.getDate() == null) {
                return false;
            }

       
        return true;
    }

}
