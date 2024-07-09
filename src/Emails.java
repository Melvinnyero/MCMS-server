import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;
import javax.mail.Authenticator;

public class Emails {

  public static void main(String[] args) throws Exception {

    Properties properties = new Properties();
    properties.put("mail.smtp.auth", "true");
    properties.put("mail.smtp.starttls.enable", "true");
    properties.put("mail.smtp.host", "smtp.gmail.com");
    properties.put("mail.smtp.port", "587");

    Session session = Session.getInstance(properties, new Authenticator() {
      @Override
      protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication("senderEmail@gmail.com", "Password");
      }
    });

    Message message = new MimeMessage(session);
    message.setFrom(new InternetAddress("senderEmail@gmail.com"));
    message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("emailThatGetsEmail@gmail.com"));
    message.setSubject("Email from my Java Program");

    // Create a multipart message
    Multipart multipart = new MimeMultipart();

    // Add a body part for the message content
    MimeBodyPart messageBodyPart = new MimeBodyPart();
    messageBodyPart.setContent("<h1>Email from Mathematics Challenge</h1>", "text/html");
    multipart.addBodyPart(messageBodyPart);

    // Add an attachment part if needed (example path provided)
    MimeBodyPart attachmentPart = new MimeBodyPart();
    attachmentPart.attachFile("path/to/file"); // Provide the actual file path here
    multipart.addBodyPart(attachmentPart);

    // Set the multipart message to the email message
    message.setContent(multipart);

    // Send the message
    Transport.send(message);

    System.out.println("Email sent successfully");
  }
}