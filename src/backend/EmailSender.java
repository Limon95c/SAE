/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


/**
 *
 * @author jj
 */
public class EmailSender {
    final static String USERNAME = "noreply.sae.server@gmail.com";
    final static String PASS = "sistemadeasistenciaestudiantil";
    public static void SendEmail(String RecipientMail, String Subject,
                                 String mensaje){
        Properties propiedades = new Properties();
        propiedades.put("mail.smtp.auth", "true");
        propiedades.put("mail.smtp.starttls.enable", "true");
        propiedades.put("mail.smtp.host", "smtp.gmail.com");
        propiedades.put("mail.smtp.port", "587");
        Session sesion = Session.getInstance(propiedades, new javax.mail.Authenticator(){
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(USERNAME, PASS);
        }
        });
        try{
            Message mMensaje = new MimeMessage(sesion);
            mMensaje.setFrom(new InternetAddress(USERNAME));
            mMensaje.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(RecipientMail));
            mMensaje.setSubject(Subject);
            mMensaje.setText(mensaje);
            Transport.send(mMensaje);
            
        }catch(MessagingException e){
            System.out.println(e);
            throw new RuntimeException(e);
        }
    }
}
