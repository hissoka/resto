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
import tn.sagemcom.pfe.dao.Event_gwsDao;
import tn.sagemcom.pfe.dao.FileSystemDao;
import tn.sagemcom.pfe.dao.GatewayDao;
import tn.sagemcom.pfe.dao.MachineDao;
import tn.sagemcom.pfe.dao.MemoireDao;
import tn.sagemcom.pfe.dao.MessageDao;
import tn.sagemcom.pfe.dao.ProcessusDao;
import tn.sagemcom.pfe.entites.Events_Gws;
import tn.sagemcom.pfe.entites.FileSystem;
import tn.sagemcom.pfe.entites.Gateway;
import tn.sagemcom.pfe.entites.Machine;
import tn.sagemcom.pfe.entites.Memoire;
import tn.sagemcom.pfe.entites.Message;
import tn.sagemcom.pfe.entites.Processus;
import tn.sagemcom.pfe.gui.LoginGUI;

/**
 *
 * @author Issam BenBelgacem
 */
public class FileThreadTime extends Thread implements Runnable {

    private String FileName;
    private Object[] ac = null;
    private int size = 0;
    private Gateway gateway;
    private static int type = 1;
    private GatewayDao gwsdao;
    private MachineDao machineDao;
    private MessageDao messageDao;
    private Event_gwsDao event_gwsDao;
    private Memoire temp;
    Machine machine = new Machine();
    private int list = 0;

    public FileThreadTime(String filename, Gateway gateway, int type) {
        this.FileName = filename;
        this.gateway = gateway;
        this.messageDao = new MessageDao();
    }

    public FileThreadTime(String filename) {
        this.FileName = filename;
        this.gateway = new Gateway();
    }

    @Override
    public void run() {
        Todo();

    }

    public static synchronized Memoire Memoire_Extraction(String ab) {
        Memoire mm = new Memoire();
        try {
            if (type == 1) {
                String test = ab.split(":")[3].split(",")[0];
                mm.setTotal(Integer.parseInt(test.split("k")[0].trim()));
                test = ab.split(":")[3].split(",")[1];
                mm.setUsed(Integer.parseInt(test.split("k")[0].trim()));
                test = ab.split(":")[3].split(",")[2];
                mm.setFree(Integer.parseInt(test.split("k")[0].trim()));
                test = ab.split(":")[3].split(",")[3];
                mm.setBuffers(Integer.parseInt(test.split("k")[0].trim()));
                test = ab.split("]")[0].replace("[", "");
                mm.setDate(test.split("k")[0].trim());
            } else {
                String test;
//        mm.setTotal(Integer.parseInt(test.split("K")[0].trim()));
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
            System.out.println(" Memoire_Extraction :" + ex.getMessage()
            );
        }
        return mm;
    }

    public static synchronized FileSystem FileSystem_Extraction(String ab) {
        FileSystem fileSystem = new FileSystem();
        try {

            int v = 0;
            String aux;
            System.err.println("------");

            String[] abc = new String[6];
            abc = ab.split("]")[1].split("\\s+");
            if (abc[0].equals("")) {
                v = 1;
            }
            fileSystem.setLabel(abc[0 + v]);
            fileSystem.setSize(abc[1 + v]);
            fileSystem.setUtilise(abc[2 + v]);
            fileSystem.setDisponible(abc[3 + v]);
            fileSystem.setPerc_utilisie(Integer.parseInt(abc[4 + v].replace("%", "")));
            fileSystem.setMonted_in(abc[5 + v]);
            System.err.println("------");
            aux = ab.split("]")[0].replace("[", "");
            fileSystem.setDate(aux);
            return fileSystem;
        } catch (Exception ex) {
            System.err.println(ex.getMessage() + " at FileThread.FileSystem_Extraction");
            return fileSystem;
        }
    }

    public static synchronized Processus Process_Extraction(String ab) {
        int index = 0;
        Processus p = new Processus();
        try {
            String[] values = ab.split("]")[1].split("\\s+");
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

                p.setDate(getDatefromLine(ab));
            }
            return p;

        } catch (ArrayIndexOutOfBoundsException arrexp) {
            return null;
        }

    }

    public synchronized void Todo() {
        MemoireDao memoireDao = new MemoireDao();
        FileSystemDao fileSystemDao = new FileSystemDao();
        ProcessusDao processusDao = new ProcessusDao();
        System.out.println(gateway);
        gwsdao = new GatewayDao();

        while (true) {
            try (Stream<String> stream = Files.lines(Paths.get(FileName), Charset.forName("ISO-8859-1"))) {
                ac = stream.toArray();
                System.out.println("length " + ac.length);
                gateway.setLastinfo(new Date().toString());
                gwsdao.save(gateway);
                if (size != ac.length) {
                    for (int i = size; i < ac.length; i++) {
                        //System.out.println("traitment Ligne N° : "+i);
                        String line = String.valueOf(ac[i]);

                        if (line.contains("% /opt/filesystem1")) {
                            try {
                                FileSystem fileSystem = FileSystem_Extraction(line);
                                fileSystem.setGateway_id(gateway.getId());
                                // System.out.println(fileSystem  +" ");
                                if (FileSystem_Utils.IsValid(fileSystem)) {
                                    fileSystemDao.create(fileSystem);
                                    if (fileSystem.Regle() == 3) {
                                        Message m = new Message();
                                        m.setAll(0, LoginGUI.user.getId(), gateway.getId(), "depassement des seuils Niveau 3",
                                                "System Fichier", fileSystem.getDate(), "No", 3);
                                        messageDao.create(m);
                                    } else if (fileSystem.Regle() == 2) {
                                        Message m = new Message();
                                        m.setAll(0, LoginGUI.user.getId(), gateway.getId(), "depassement des seuils Niveau 2",
                                                "System Fichier", fileSystem.getDate(), "No", 2);
                                        messageDao.create(m);
                                    }
                                }
                            } catch (Exception ex) {
                                MyLogger.log(Level.WARNING, ex.toString());

                            }
                        }

                        if (line.contains("# ps aux")) {

                            MyLogger.log(Level.WARNING, " Processus Extraction Started ");
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
                                        if (k.Regle() == 3) {
                                            Message m = new Message();
                                            m.setAll(0, LoginGUI.user.getId(), gateway.getId(), "depassement des seuils Niveau 3",
                                                    "Processus ", k.getDate(), "No", 3);
                                            messageDao.create(m);

                                        } else if (k.Regle() == 2) {
                                            Message m = new Message();
                                            m.setAll(0, LoginGUI.user.getId(), gateway.getId(), "depassement des seuils Niveau 2",
                                                    "Processus ", k.getDate(), "No", 2);
                                            messageDao.create(m);

                                        }
                                        MyLogger.log(Level.INFO, "------------- Processus_Extraction Added Successufly----------------");

                                    }
                                } catch (Exception ex) {
                                    MyLogger.log(Level.WARNING, ex.toString());
                                }
                            }
                            list++;
                        }
                        if (line.contains(" Mem:")) {
                            try {
                                temp = Memoire_Extraction(line);
                                gateway.getMemoires().add(temp);
                                temp.setGateway_id(gateway.getId());
                                if (Memoire_Utils.IsValid(temp)) {
                                    memoireDao.create(temp);
                                    if (temp.Regle() == 3) {
                                        Message m = new Message();
                                        m.setAll(0, LoginGUI.user.getId(), gateway.getId(), "depassement des seuils Niveau 3",
                                                "Memoire", temp.getDate(), "No", 3);
                                        messageDao.create(m);

                                    } else if (temp.Regle() == 2) {
                                        Message m = new Message();
                                        m.setAll(0, LoginGUI.user.getId(), gateway.getId(), "depassement des seuils Niveau 2",
                                                "Memoire", temp.getDate(), "No", 2);
                                        messageDao.create(m);

                                    }
                                }

                            } catch (Exception ex) {
                                MyLogger.log(Level.WARNING, ex.toString());
                            }
                        }
                        if (line.contains("WATCHDOG TIMEOUT")) {
                            gateway.setLastwatchdog(getDatefromLine(line));
                            gateway.setNbrwatchdog(gateway.getNbrwatchdog() + 1);
                            GatewayDao dao = new GatewayDao();

                            Message m = new Message();
                            m.setAll(0, LoginGUI.user.getId(), gateway.getId(), "WATCHDOG TIMEOUT Niveau 3",
                                    "WATCHDOG TIMEOUT", getDatefromLine(line), "No", 3);
                            messageDao.create(m);
 event_gwsDao=new Event_gwsDao();
                            Events_Gws evnt=new Events_Gws();
                            evnt.setAll(0, gateway.getId(), "WATCHDOG TIMEOUT",getDatefromLine(line), line);
                            event_gwsDao.create(evnt);
                            dao.save(gateway);
                            try {
                                SendMail.Sendwatch(gateway);
                            } catch (Exception ex) {
                                MyLogger.log(Level.SEVERE, ex.toString());
                            }
                        }
                        if (line.contains("# arp -a")) {
                            line = String.valueOf(ac[++i]);
                            machineDao = new MachineDao();
                            while (!line.contains("root@")) {
                                machine = MachineArp_Extraction(line);
                                line = String.valueOf(ac[i++]);

                                try {
                                    machine.setGateway_id(gateway.getId());
                                    if (MachineUtils.IsValid(machine)) {
                                        machineDao.create(machine);
                                    }
                                    MyLogger.log(Level.INFO, "------------- Machine  Added Successufly----------------");

                                } catch (Exception ex) {
                                    MyLogger.log(Level.WARNING, ex.toString());
                                }
                            }
                        }
                        if (line.contains("HELO")) {
                           
                            Message m = new Message();
                            m.setAll(0, LoginGUI.user.getId(), gateway.getId(), "Reboot Niveau 3",
                                    "Reboot", getDatefromLine(line),"No", 3);
                            messageDao.create(m);
                            gateway.setNbreboot(gateway.getNbreboot()+1);
                            gateway.setLastreboot(getDatefromLine(line));
                         new GatewayDao().save(gateway);

                        }
                        if(line.contains("uptime")){
                             line = String.valueOf(ac[++i]);
                            event_gwsDao=new Event_gwsDao();
                            Events_Gws evnt=new Events_Gws();
                            evnt.setAll(0, gateway.getId(), "uptime",getDatefromLine(line), line);
                            event_gwsDao.create(evnt);
                            gateway.setLastuptime(line);
                            new GatewayDao().save(gateway);
                        }
                    }
                }
                size = ac.length;

            } catch (Exception e) {
              Logger.getLogger(FileThreadTime.class.getName()).log(Level.SEVERE, null, e);

            }
            try {
                this.wait(6000);
            } catch (InterruptedException ex) {
                Logger.getLogger(FileThreadTime.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static String getDatefromLine(String line) {
        return line.split("]")[0].replace("[", "");
    }

    public static synchronized Machine MachineArp_Extraction(String line) {

        Machine machine = new Machine();
        try {
            int index = 0;
            String[] values = line.split("]")[1].split("\\s+");
            for (String value1 : values) {
                if (!value1.equals("") && !value1.equals(" ")
                        && !value1.equals("e:/#") && values.length >= 5) {
                    switch (index) {
                        case 0:
                            machine.setIpaddress(value1);
                            break;
                        case 1:
                            machine.set_type(value1);
                            break;
                        case 2:
                            break;
                        case 3:
                            machine.setMacaddress(value1);
                            break;
                        case 4:
                            break;
                        case 5:
                            machine.setDevice(value1);
                            break;

                    }
                    index++;
                    machine.set_date(getDatefromLine(line));
                }
            }
        } catch (Exception ex) {
            MyLogger.log(Level.WARNING, ex.toString());
            return null;
        }

        MyLogger.log(Level.INFO, "Machine details extracted " + machine.toString());
        return machine;
    }

    public static void CommandResult(String ab) {

    }
}
