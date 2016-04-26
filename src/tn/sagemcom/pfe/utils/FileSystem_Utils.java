/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.sagemcom.pfe.utils;

import tn.sagemcom.pfe.entites.FileSystem;

/**
 *
 * @author Issam BenBelgacem
 */
public class FileSystem_Utils {


    private  FileSystem_Utils() {
    }

    public static boolean IsValid(FileSystem fileSystem) {
       
            if (fileSystem == null) {
                return false;
            } else if (fileSystem.getMonted_in() == null || fileSystem.getMonted_in().equals("")) {
                return false;
            } else if (fileSystem.getSize() == null) {
                return false;
            } else if (fileSystem.getUtilise() == null) {
                return false;
            }
               return true;

    }
}
