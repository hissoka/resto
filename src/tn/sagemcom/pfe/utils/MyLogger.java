/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.sagemcom.pfe.utils;

import java.io.IOException;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 *
 * @author G507751
 */
public class MyLogger {
     private static  Logger logger;
    private  Handler fileHandler;
    private Formatter plainText;

    private  MyLogger() throws IOException{
        //instance the logger
        logger = Logger.getLogger(MyLogger.class.getName());
        //instance the filehandler
        Date date=new Date();
        String filename="./"+date.getDay()+"-"+date.getMonth()+"-"+date.getYear()+"log.txt";
     
        fileHandler = new FileHandler(filename,true);
        //instance formatter, set formatting, and handler
        plainText = new SimpleFormatter();
        fileHandler.setFormatter(plainText);
        logger.addHandler(fileHandler);

    }
    private static Logger getLogger(){
    if(logger == null){
        try {
            new MyLogger();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    return logger;
}
    public static void log(Level level, String msg){
    getLogger().log(level, msg);
  }
}
