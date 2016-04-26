package tn.sagemcom.pfe.exception;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Issam BenBelgacem
 */
public class NotFoundException extends Exception {

   /**
    * Constructor for NotFoundException. The input message is
    * returned in toString() message.
     * @param msg
    */
    public NotFoundException(String msg) {
           super(msg);
    }

}

