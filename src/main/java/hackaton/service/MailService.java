package hackaton.service;

import hackaton.entity.User;
import org.apache.commons.lang3.text.StrSubstitutor;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Service
public class MailService {
    private String username = "your_email";
    private String password = "your password";
    private String host = "smtp.gmail.com";

    public void send(User user, String baseUrl){
        String to = user.getEmail();

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        Session session = Session.getDefaultInstance(properties, null);

        try{
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            message.setSubject("Welcome to Job Matching!");
            message.setText(getMessage(user, baseUrl + "/#!/activate/" + user.getActivationKey() ), "utf-8", "html");

            // Send message
            Transport transport = session.getTransport("smtp");
            transport.connect(host, username, password);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        }catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }

    private String getMessage(User user, String contextPath){

        Map<String, String> valuesMap = new HashMap<String, String>();
        valuesMap.put("name", user.getNume() );
        valuesMap.put("activation", contextPath);
        String templateString = "Hello ${name}, <br><br> " +
                " Thank you for regestering to Job Matching.<br>" +
                " To activate your account click <a href=\"${activation}\">here</a>";
        StrSubstitutor sub = new StrSubstitutor(valuesMap);
        String resolvedString = sub.replace(templateString);

        return resolvedString;
    }
}
