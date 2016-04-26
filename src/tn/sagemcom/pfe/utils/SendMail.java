/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.sagemcom.pfe.utils;

import java.util.*;
import java.util.logging.Level;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import tn.sagemcom.pfe.entites.Gateway;
import tn.sagemcom.pfe.entites.Utilisateur;
import tn.sagemcom.pfe.gui.LoginGUI;

public class SendMail
{private static Session session ;
    private static void connect(){
           final String username = "sagemcom.pfe@gmail.com";
        final String password = "sagemcom123";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		session= Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });
    }
            
   public static synchronized void Sendwatch(Gateway gws)
   {    
		connect();

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("sagemcom.pfe@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
			InternetAddress.parse(LoginGUI.user.getMail()));
			message.setSubject("WATCHDOG TIMEOUT");
			message.setText("Bonjour  "+LoginGUI.user.getName()+","
				+ "\n Ton  Gateway :  !"+ gws.getLabel() +" \t @IP: "
                        +gws.getIpAdress()+" \n WATCHDOG TIMEOUT le "+gws.getLastwatchdog()
                        +"\n\n\n N.B: ceci est un mail automatique merci de ne pas répondre."
                                +" \n\t\t\t\t  @ Sagemcom 2016 @"
                        );

			Transport.send(message);

			MyLogger.log(Level.SEVERE, "Watch Dog Mail Sent Successfuly");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
   }
    public static synchronized void SendMemoire(Gateway gws)
   {    
		connect();

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("sagemcom.pfe@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
			InternetAddress.parse(LoginGUI.user.getMail()));
			message.setSubject("Alert Memoire");
			message.setText("Bonjour  "+LoginGUI.user.getName()+","
				+ "\n Ton  Gateway :  !"+ gws.getLabel() +" \t @IP: "
                        +gws.getIpAdress()+" \n consommation  Memoire depasse 95% le "+ new Date().toString()
                        +"\n\n\n N.B: ceci est un mail automatique merci de ne pas répondre."
                                +" \n\t\t\t\t  @ Sagemcom 2016 @"
                        );

			Transport.send(message);

			MyLogger.log(Level.SEVERE, "Watch Dog Mail Sent Successfuly");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
   }
    public static synchronized void SendFileSystem(Gateway gws)
   {    
		connect();

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("sagemcom.pfe@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
			InternetAddress.parse(LoginGUI.user.getMail()));
			message.setSubject("Alert Systeme Fichier ");
			message.setText("Bonjour  "+LoginGUI.user.getName()+","
				+ "\n Ton  Gateway :  !"+ gws.getLabel() +" \t @IP: "
                        +gws.getIpAdress()+" \n l'utilisation de systeme fichier   depasse 50% le "+ new Date().toString()
                        +"\n\n\n N.B: ceci est un mail automatique merci de ne pas répondre."
                                +" \n\t\t\t\t  @ Sagemcom 2016 @"
                        );

			Transport.send(message);

			MyLogger.log(Level.SEVERE, "Watch Dog Mail Sent Successfuly");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
   }
}