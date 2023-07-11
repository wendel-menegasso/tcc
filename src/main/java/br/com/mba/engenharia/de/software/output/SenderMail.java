package br.com.mba.engenharia.de.software.output;

import br.com.mba.engenharia.de.software.negocio.usuarios.Usuario;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.Date;

public class SenderMail
{
    public static boolean sendEmail(Usuario usuario){
        final String toEmail = usuario.getEmail();
        final String fromEmail = "wsmenegasso"; //requires valid gmail id
        final String password = "djntsxtwfeuxclav"; // correct password for gmail id

        System.out.println("TLSEmail Start");
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
        props.put("mail.smtp.port", "587"); //TLS Port
        props.put("mail.smtp.auth", true); //enable authentication
        props.put("mail.smtp.starttls.enable", true); //enable STARTTLS
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");

        Authenticator auth = new Authenticator() {
            //override the getPasswordAuthentication method
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        };

        Session session = Session.getDefaultInstance(props, auth);
        System.out.println("Session created");
        return sendEmail(session, toEmail,"Token", "Token para ativação:" + usuario.getToken());
    }
    public static boolean sendEmail(Session session, String toEmail, String subject, String body){
        try
        {
            MimeMessage msg = new MimeMessage(session);
            //set message headers
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            msg.addHeader("format", "flowed");
            msg.addHeader("Content-Transfer-Encoding", "8bit");

            msg.setFrom(new InternetAddress("wsmenegasso@gmail.com", "NoReply-JD"));

            msg.setReplyTo(InternetAddress.parse("wsmenegasso@gmail.com", false));

            msg.setSubject(subject, "UTF-8");

            msg.setText(body, "UTF-8");

            msg.setSentDate(new Date());

            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
            System.out.println("Message is ready");
            Transport.send(msg);

            System.out.println("EMail Sent Successfully!!");
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
