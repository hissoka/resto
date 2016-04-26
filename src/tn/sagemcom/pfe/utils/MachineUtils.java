/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.sagemcom.pfe.utils;
import tn.sagemcom.pfe.entites.Machine;
/**
 *
 * @author G507751
 */
public class MachineUtils {
    public static boolean IsValid(Machine machine){
        if(machine.get_date()==null)return false;
        
        return true;
    }
}
