/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.sagemcom.pfe.entites;

import java.util.Objects;

/**
 *
 * @author Issam BenBelgacem
 */
public class Processus extends Performance{
    private String user;
    private int pid;
    private String perc_cpu;
    private String perc_memoire;
    private String vsz ;
    private String RSS;
    private String tty;
    private String stat;
    private String start;
    private String time;
    private String command;
private int  list;

    public Processus(String user, int pid, String perc_cpu, String perc_memoire, String vsz, String RSS, String tty, String stat, String start, String time, String command) {
        this.user = user;
        this.pid = pid;
        this.perc_cpu = perc_cpu;
        this.perc_memoire = perc_memoire;
        this.vsz = vsz;
        this.RSS = RSS;
        this.tty = tty;
        this.stat = stat;
        this.start = start;
        this.time = time;
        this.command = command;
    }

    public Processus() {
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.user);
        hash = 53 * hash + this.pid;
        hash = 53 * hash + Objects.hashCode(this.command);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Processus other = (Processus) obj;
        if (!Objects.equals(this.user, other.user)) {
            return false;
        }
        if (!Objects.equals(this.command, other.command)) {
            return false;
        }
        return true;
    }

    public String getUser() {
        return user;
    }

    public void setList(int list) {
        this.list = list;
    }

    public int getList() {
        return list;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getPerc_cpu() {
        return perc_cpu;
    }

    public void setPerc_cpu(String perc_cpu) {
        this.perc_cpu = perc_cpu;
    }

    public String getPerc_memoire() {
        return perc_memoire;
    }

    public void setPerc_memoire(String perc_memoire) {
        this.perc_memoire = perc_memoire;
    }

    public String getVsz() {
        return vsz;
    }

    public void setVsz(String vsz) {
        this.vsz = vsz;
    }

    public String getRSS() {
        return RSS;
    }

    public void setRSS(String RSS) {
        this.RSS = RSS;
    }

    public String getTty() {
        return tty;
    }

    public void setTty(String tty) {
        this.tty = tty;
    }

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    @Override
    public String toString() {
        return "Processus{" + "user=" + user + ", pid=" + pid + ", perc_cpu=" + perc_cpu + ", perc_memoire=" + perc_memoire + ", vsz=" + vsz + ", RSS=" + RSS + ", tty=" + tty + ", stat=" + stat + ", start=" + start + ", time=" + time + ", command=" + command + '}';
    }

    @Override
    public int Regle() {
   
         double percentage=Double.parseDouble(String.valueOf(getPerc_cpu()));
    if(percentage<5){
        System.out.println(" Niveau 1 "+percentage+ "%");
        return 1;
    }else if(percentage>=5 && percentage<20)
    {
        System.out.println(" Niveau 2 "+percentage +"%");
        return 2;
    }else{
        System.err.println(" Niveau 3 Alert "+percentage +"%"); 
        return 3;
    }
    }
    
}
