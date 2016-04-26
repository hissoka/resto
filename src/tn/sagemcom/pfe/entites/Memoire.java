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
public class Memoire extends Performance implements Comparable<Memoire> {
    private int total;
    private int used;
    private int free;
    private int buffers;
    
    public Memoire(int total, int used, int free, int buffers,String date) {
        this.total = total;
        this.used = used;
        this.free = free;
        this.buffers = buffers;
    }
    public Memoire() {
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getUsed() {
        return used;
    }

    public void setUsed(int used) {
        this.used = used;
    }

   
    public int getFree() {
        return free;
    }

    public void setFree(int free) {
        this.free = free;
    }

    public int getBuffers() {
        return buffers;
    }

    public void setBuffers(int buffers) {
        this.buffers = buffers;
    }

    @Override
    public String toString() {
        return "Memoire{" + "total=" + total + ", used=" + used + ", free=" + free + ", buffers=" + buffers + ", _Date=" + this.getDate() + '}';
    }

    @Override
    public int compareTo(Memoire t) {
        if(
        used>t.used)return 1;
        if(used==t.used)return 0;
        return -1;
    }
    @Override
public int Regle(){
    double percentage=(((double)getUsed()*100)/(double)getTotal());
    if(percentage<75){
        System.out.println(" Niveau 1 "+percentage+ "%");
        return 1;
    }else if(percentage>=75 && percentage<95)
    {
        System.out.println(" Niveau 2 "+percentage +"%");
        return 2;
    }else{
        System.err.println(" Niveau 3 Alert "+percentage +"%"); 
        return 3;
    }
}
}
