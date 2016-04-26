/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.sagemcom.pfe.entites;

/**
 *
 * @author G507751
 */
public class SettingAlerts {
    private final int proc_lvl1_val_default = 25;
    private final int proc_lvl2_val_default = 75;
    private final int proc_lvl3_val_default = 95;

    private final int memory_lvl1_val_default = 25;
    private final int memory_lvl2_val_default = 75;
    private final int memory_lvl3_val_default = 95;

    private final int file_lvl1_val_default = 15;
    private final int file_lvl2_val_default = 25;
    private final int file_lvl3_val_default = 50;

    private final boolean watchdog_val_default = true;
    private final boolean uptime_val_default = true;
    private final boolean reboot_default = true;
    private final boolean recive_default = false;
   
    private int proc_lvl1_val;
    private int proc_lvl2_val;
    private int proc_lvl3_val;

    private int memory_lvl1_val;
    private int memory_lvl2_val;
    private int memory_lvl3_val;

    private int file_lvl1_val;
    private int file_lvl2_val;
    private int file_lvl3_val;

    private boolean watchdog_val;
    private boolean uptime_val;
    private boolean reboot;
    private boolean recive;

    public int getProc_lvl1_val_default() {
        return proc_lvl1_val_default;
    }

    public int getProc_lvl2_val_default() {
        return proc_lvl2_val_default;
    }

    public int getProc_lvl3_val_default() {
        return proc_lvl3_val_default;
    }

    public int getMemory_lvl1_val_default() {
        return memory_lvl1_val_default;
    }

    public int getMemory_lvl2_val_default() {
        return memory_lvl2_val_default;
    }

    public int getMemory_lvl3_val_default() {
        return memory_lvl3_val_default;
    }

    public int getFile_lvl1_val_default() {
        return file_lvl1_val_default;
    }

    public int getFile_lvl2_val_default() {
        return file_lvl2_val_default;
    }

    public int getFile_lvl3_val_default() {
        return file_lvl3_val_default;
    }

    public boolean isWatchdog_val_default() {
        return watchdog_val_default;
    }

    public boolean isUptime_val_default() {
        return uptime_val_default;
    }

    public boolean isReboot_default() {
        return reboot_default;
    }

    public boolean isRecive_default() {
        return recive_default;
    }

    public int getProc_lvl1_val() {
        return proc_lvl1_val;
    }

    public void setProc_lvl1_val(int proc_lvl1_val) {
        this.proc_lvl1_val = proc_lvl1_val;
    }

    public int getProc_lvl2_val() {
        return proc_lvl2_val;
    }

    public void setProc_lvl2_val(int proc_lvl2_val) {
        this.proc_lvl2_val = proc_lvl2_val;
    }

    public int getProc_lvl3_val() {
        return proc_lvl3_val;
    }

    public void setProc_lvl3_val(int proc_lvl3_val) {
        this.proc_lvl3_val = proc_lvl3_val;
    }

    public int getMemory_lvl1_val() {
        return memory_lvl1_val;
    }

    public void setMemory_lvl1_val(int memory_lvl1_val) {
        this.memory_lvl1_val = memory_lvl1_val;
    }

    public int getMemory_lvl2_val() {
        return memory_lvl2_val;
    }

    public void setMemory_lvl2_val(int memory_lvl2_val) {
        this.memory_lvl2_val = memory_lvl2_val;
    }

    public int getMemory_lvl3_val() {
        return memory_lvl3_val;
    }

    public void setMemory_lvl3_val(int memory_lvl3_val) {
        this.memory_lvl3_val = memory_lvl3_val;
    }

    public int getFile_lvl1_val() {
        return file_lvl1_val;
    }

    public void setFile_lvl1_val(int file_lvl1_val) {
        this.file_lvl1_val = file_lvl1_val;
    }

    public int getFile_lvl2_val() {
        return file_lvl2_val;
    }

    public void setFile_lvl2_val(int file_lvl2_val) {
        this.file_lvl2_val = file_lvl2_val;
    }

    public int getFile_lvl3_val() {
        return file_lvl3_val;
    }

    public void setFile_lvl3_val(int file_lvl3_val) {
        this.file_lvl3_val = file_lvl3_val;
    }

    public boolean isWatchdog_val() {
        return watchdog_val;
    }

    public void setWatchdog_val(boolean watchdog_val) {
        this.watchdog_val = watchdog_val;
    }

    public boolean isUptime_val() {
        return uptime_val;
    }

    public void setUptime_val(boolean uptime_val) {
        this.uptime_val = uptime_val;
    }

    public boolean isReboot() {
        return reboot;
    }

    public void setReboot(boolean reboot) {
        this.reboot = reboot;
    }

    public boolean isRecive() {
        return recive;
    }

    public void setRecive(boolean recive) {
        this.recive = recive;
    }
    private SettingAlerts(){
        
    }
   
   
}
