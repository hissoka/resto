/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.sagemcom.pfe.utils;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;
import tn.sagemcom.pfe.dao.FileSystemDao;
import tn.sagemcom.pfe.dao.GatewayDao;
import tn.sagemcom.pfe.dao.MemoireDao;
import tn.sagemcom.pfe.dao.ProcessusDao;
import tn.sagemcom.pfe.entites.FileSystem;
import tn.sagemcom.pfe.entites.Gateway;
import tn.sagemcom.pfe.entites.Memoire;
import tn.sagemcom.pfe.entites.Processus;

/**
 *
 * @author Issam BenBelgacem
 */
public class FileThread extends Thread implements Runnable {

    private String fileName;
    private Object[] ac = null;
    private int size = 0;
    private Gateway gateway;
    private static int type = 1;
   private  GatewayDao gwsdao;
private int list;
    public FileThread(String filename, Gateway gateway, int type) {
        this.fileName = filename;
        this.gateway = gateway;
        this.type = type;
    }

    public FileThread(String filename) {
        this.fileName = filename;
        this.gateway = new Gateway();
    }

    @Override
    public void run() {
        Todo();
    }

    public static synchronized Memoire MemoireExtraction(String ab) {
        
        MyLogger.log(Level.INFO,"------------- Memoire_Extraction Debut----------------" );
        
        Memoire mm = new Memoire();
        try {
            if (type == 1) {
                String test = ab.split(":")[1].split(",")[0];
                mm.setTotal(Integer.parseInt(test.split("k")[0].trim()));
                test = ab.split(":")[1].split(",")[1];
                mm.setUsed(Integer.parseInt(test.split("k")[0].trim()));
                test = ab.split(":")[1].split(",")[2];
                mm.setFree(Integer.parseInt(test.split("k")[0].trim()));
                test = ab.split(":")[1].split(",")[3];
                mm.setBuffers(Integer.parseInt(test.split("k")[0].trim()));
                mm.setDate(new Date().toString());
            } else {
                String test;

                test = ab.split(":")[3].split(",")[0];
                mm.setUsed(Integer.parseInt(test.split("K")[0].trim()));
                test = ab.split(":")[3].split(",")[1];
                mm.setFree(Integer.parseInt(test.split("K")[0].trim()));
                test = ab.split(":")[3].split(",")[4];
                mm.setBuffers(Integer.parseInt(test.split("K")[0].trim()));
                test = ab.split("]")[0].replace("[", "");
                mm.setDate(test.split("K")[0].trim());
                mm.setTotal(mm.getUsed() + mm.getFree());
            }
        } catch (Exception ex) {
           MyLogger.log(Level.WARNING," Memoire_Extraction :" + ex.getMessage()
            );
        }
                MyLogger.log(Level.INFO,"------------- Memoire_Extraction End----------------" );
        return mm;
    }

    public static synchronized FileSystem FileSystem_Extraction(String ab) {
        FileSystem fileSystem = new FileSystem();
        try {

            int v = 0;
            String aux;
            System.err.println("------");

            String[] abc = new String[6];
          //  abc = ab.split("]")[1].split("\\s+");
                        abc = ab.split("\\s+");

            if (abc[0].equals("")) {
                v = 1;
            }
            if(abc.length>5){
            fileSystem.setLabel(abc[0 + v]);
            fileSystem.setSize(abc[1 + v]);
            fileSystem.setUtilise(abc[2 + v]);
            fileSystem.setDisponible(abc[3 + v]);
            fileSystem.setPerc_utilisie(Integer.parseInt(abc[4 + v].replace("%", "")));
            fileSystem.setMonted_in(abc[5 + v]);
            System.err.println("------");
           // aux = ab.split("]")[0].replace("[", "");
            fileSystem.setDate(new Date().toString());}
            return fileSystem;
        } catch (Exception ex) {
             MyLogger.log(Level.WARNING,ex.toString() + " \n at FileThread.FileSystem_Extraction");
            return fileSystem;
        }
    }

    public static Processus Process_Extraction(String ab) {
        int index = 0;
                MyLogger.log(Level.INFO,"-------------PRocessus_Extraction Start----------------" );

        Processus p = new Processus();
        try {
           // String[] values = ab.split("]")[1].split(" ");
          String[] values=  ab.split("\\s+");
            for (String value1 : values) {
                if (!value1.equals("") && !value1.equals(" ")
                        && !value1.equals("e:/#") && values.length > 10) {
                    switch (index) {
                        case 0:
                            p.setUser(value1);
                            break;
                        case 1:
                            try {
                                p.setPid(Integer.parseInt(value1));
                            } catch (Exception ex) {
                                p.setPid(-1);
                            }
                            ;
                            break;
                        case 2:
                            p.setPerc_cpu(value1);
                            break;
                        case 3:
                            p.setPerc_memoire(value1);
                            break;
                        case 4:
                            p.setVsz(value1);
                            break;
                        case 5:
                            p.setRSS(value1);
                            break;
                        case 6:
                            p.setTty(value1);
                            break;
                        case 7:
                            p.setStat(value1);
                            break;
                        case 8:
                            p.setStart(value1);
                            break;
                        case 9:
                            p.setTime(value1);
                            break;
                        case 10:
                            p.setCommand(value1.replace("[", ""));
                            break;
                        default:
                            p.setCommand(p.getCommand() + " " + value1);

                    }
                    index++;

                }
               // String aux = ab.split("]")[0].replace("[", "");
               
                p.setDate(new Date().toString());
            }
                            MyLogger.log(Level.INFO,"------------- Processus_Extraction ENDed----------------" );

            return p;

        } catch (ArrayIndexOutOfBoundsException arrexp) {
             MyLogger.log(Level.WARNING,"Processus Exception : "+arrexp.getMessage());
            return null;
        }

    }

    public synchronized void Todo() {
        MemoireDao memoireDao = new MemoireDao();
        FileSystemDao fileSystemDao = new FileSystemDao();
        ProcessusDao processusDao = new ProcessusDao();
        gwsdao=new GatewayDao();
        System.out.println(gateway);
        
        while (true) {
            try (Stream<String> stream = Files.lines(Paths.get(fileName), Charset.forName("ISO-8859-1"))) {
                ac = stream.toArray();
                System.out.println("length " + ac.length);
                gateway.setLastinfo(new Date().toString());
        gwsdao.save(gateway);
                if (size != ac.length) {
                    for (int i = size; i < ac.length; i++) {
                        System.out.println("traitment Ligne N° : " + i);
                        String line = ac[i] + "";

                        if (line.contains("% /opt/filesystem1")) {
                            try {
                                FileSystem fileSystem = FileSystem_Extraction(line);
                                fileSystem.setGateway_id(gateway.getId());
                                if (FileSystem_Utils.IsValid(fileSystem)) {
                                    
                                    fileSystemDao.create(fileSystem);
                       MyLogger.log(Level.INFO,"------------- FileSystem_Extraction Added Successufly----------------" );
                               }
                            } catch (Exception ex) {
                                MyLogger.log(Level.WARNING," File System Exception"+ex.getMessage());
                            }
                        }
                        if (line.contains("# ps aux")){
                            
              MyLogger.log(Level.WARNING," Processus Extraction Started ");
line = String.valueOf(ac[++i]);
        while (!line.contains("root@")) {
                                Processus k = Process_Extraction(line);
line = String.valueOf(ac[i++]);

                                try {

                                    k.setGateway_id(gateway.getId());
                                    k.setList(list);
                                    //  System.out.println(k);
                                    if (Processus_Utils.IsValid(k)) {
                                        //  System.out.println(k);
                                        processusDao.create(k);
                   MyLogger.log(Level.INFO,"------------- Processus_Extraction Added Successufly----------------" );
                                    }

                                } catch (Exception ex) {
                                   MyLogger.log(Level.WARNING,ex.toString());
                                }
                            }
        list++;
                        }
                        if (line.contains("Mem:")) {

                            try {
                                Memoire temp = MemoireExtraction(line);
                                gateway.getMemoires().add(temp);
                                temp.setGateway_id(gateway.getId());
                                if (Memoire_Utils.IsValid(temp)) {
                                    memoireDao.create(temp);
       MyLogger.log(Level.INFO,"------------- Memoire Value Added Successufly----------------" );
                                    // temp.Regle();
                                }

                            } catch (Exception e) {
                                MyLogger.log(Level.WARNING,"Mem : " + e.toString());
                            }
                        }
                            if(line.contains("WATCHDOG TIMEOUT")){
                                gateway.setLastwatchdog(new Date().toString());
                                gateway.setNbrwatchdog(gateway.getNbrwatchdog()+1);
                                gwsdao.save(gateway);
                                try{
                                    
                                    SendMail.Sendwatch(gateway);
                                }catch(Exception ex 
                                        )
                                {
                                    MyLogger.log(Level.SEVERE, ex.toString());
                                }
                            }
                    }
                }
                size = ac.length;

            } catch (Exception e) {
                 MyLogger.log(Level.WARNING,e.getMessage());
            }
            try {
                this.wait(6000);
            } catch (InterruptedException ex) {
                 MyLogger.log(Level.WARNING, ex.getMessage());
            }
        }
    }

    
    
    public static void CommandResult(String ab) {

    }
}
