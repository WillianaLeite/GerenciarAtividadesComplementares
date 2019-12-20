package Models;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;
import javax.mail.Address;
 
import javax.mail.Authenticator;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Part;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;



/**
 *
 * @author willi
 */
public class Email {

	//Insira neste arquivo as informações do email fictício que você criou	
	
    // SMTP info
    private String host = "smtp.gmail.com";
    private String port = "587";
    private String mailFrom = "SEUEMAIL@gmail.com";
    private String password = "SUASENHA";

    // message info
    private String mailTo = "SEUDESTINATARIO@gmail.com";
    private String subject = "Relatório de Atividades Complementares";
    private String message = "Segue em anexo, relatório de atividades complementares solicitado. \nAtenciosamente, Secretaria de Cursos UFC - Campus Russas.";

    //MODIFIQUE PARA O SEU CAMINHO
    private String salvarDirect = "C:\\Users\\willi\\OneDrive\\Documentos\\NetBeansProjects\\GerenciarAtividadeComplementar-master\\src\\RelatoriosRecebidos"; // directory to save the downloaded documents
    
    public void setDestinatario(String dest){
        if(dest != null){
            this.mailTo = dest;
        }
    }
    
    public void setSalvarDirect(String path){
        if(path != null){
            this.salvarDirect = path;
        }
    }
    
    public void enviarRelatório(String emailPara){
        
        setDestinatario(emailPara);
        
        String[] attachFiles = new String[1];
        //MODIFIQUE PARA O SEU CAMINHO
        attachFiles[0] = "C:\\Users\\willi\\OneDrive\\Documentos\\NetBeansProjects\\GerenciarAtividadeComplementar-master\\src\\Relatorios\\relatorio.pdf";
        
        try {
            sendEmailWithAttachments(this.host, this.port, this.mailFrom, this.password, this.mailTo, this.subject, this.message, attachFiles);
            System.out.println("Email sent.");
        } catch (Exception ex) {
            System.out.println("Infelizmente o email não pode ser enviado.");
            ex.printStackTrace();
        }
        
    }
    
    
    public void receberEmails(){
    
        this.downloadEmailAttachments("pop.gmail.com","995", "SEUEMAIL@gmail.com", "SUASENHA");
    
    }
    
    private void sendEmailWithAttachments(String host, String port,
            final String userName, final String password, String toAddress,
            String subject, String message, String[] attachFiles)
            throws AddressException, MessagingException {
        // sets SMTP server properties
        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.user", userName);
        properties.put("mail.password", password);
 
        // creates a new session with an authenticator
        Authenticator auth = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, password);
            }
        };
        Session session = Session.getInstance(properties, auth);
 
        // creates a new e-mail message
        Message msg = new MimeMessage(session);
 
        msg.setFrom(new InternetAddress(userName));
        InternetAddress[] toAddresses = { new InternetAddress(toAddress) };
        msg.setRecipients(Message.RecipientType.TO, toAddresses);
        msg.setSubject(subject);
        msg.setSentDate(new Date());
 
        // creates message part
        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent(message, "application/pdf");
 
        // creates multi-part
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
 
        // adds attachments
        if (attachFiles != null && attachFiles.length > 0) {
            for (String filePath : attachFiles) {
                MimeBodyPart attachPart = new MimeBodyPart();
 
                try {
                    attachPart.attachFile(filePath);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
 
                multipart.addBodyPart(attachPart);
            }
        }
 
        // sets the multi-part as e-mail's content
        msg.setContent(multipart);
 
        // sends the e-mail
        Transport.send(msg);
 
    }
    
    private void downloadEmailAttachments(String host, String port, String userName, String password) {
		Properties properties = new Properties();

		// server setting
		properties.put("mail.pop3.host", host);
		properties.put("mail.pop3.port", port);

		// SSL setting
		properties.setProperty("mail.pop3.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		properties.setProperty("mail.pop3.socketFactory.fallback", "false");
		properties.setProperty("mail.pop3.socketFactory.port", String.valueOf(port));

		Session session = Session.getDefaultInstance(properties);

		try {
			// connects to the message store
			Store store = session.getStore("pop3");
			store.connect(userName, password);

			// opens the inbox folder
			Folder folderInbox = store.getFolder("INBOX");
			folderInbox.open(Folder.READ_ONLY);

			// fetches new messages from server
			Message[] arrayMessages = folderInbox.getMessages();

			for (int i = 0; i < arrayMessages.length; i++) {
				Message message = arrayMessages[i];
				Address[] fromAddress = message.getFrom();
				/*String from = fromAddress[0].toString();
				String subject = message.getSubject();
				String sentDate = message.getSentDate().toString();*/

				String contentType = message.getContentType();
				String messageContent = "";

				// store attachment file name, separated by comma
				String attachFiles = "";

				if (contentType.contains("multipart")) {
					// content may contain attachments
					Multipart multiPart = (Multipart) message.getContent();
					int numberOfParts = multiPart.getCount();
					for (int partCount = 0; partCount < numberOfParts; partCount++) {
						MimeBodyPart part = (MimeBodyPart) multiPart.getBodyPart(partCount);
						if (Part.ATTACHMENT.equalsIgnoreCase(part.getDisposition())) {
							// this part is attachment
							String fileName = part.getFileName();
							attachFiles += fileName + ", ";
							part.saveFile(this.salvarDirect + File.separator + fileName);
						} else {
							// this part may be the message content
							messageContent = part.getContent().toString();
						}
					}

					if (attachFiles.length() > 1) {
						attachFiles = attachFiles.substring(0, attachFiles.length() - 2);
					}
				} else if (contentType.contains("text/plain") || contentType.contains("text/html")) {
					Object content = message.getContent();
					if (content != null) {
						messageContent = content.toString();
					}
				}

				/*print out details of each message
				System.out.println("Message #" + (i + 1) + ":");
				System.out.println("\t From: " + from);
				System.out.println("\t Subject: " + subject);
				System.out.println("\t Sent Date: " + sentDate);
				System.out.println("\t Message: " + messageContent);
				System.out.println("\t Attachments: " + attachFiles);*/
			}

			// disconnect
			folderInbox.close(false);
			store.close();
		} catch (NoSuchProviderException ex) {
			System.out.println("No provider for pop3.");
			ex.printStackTrace();
			//log.error(ex);
		} catch (MessagingException ex) {
			System.out.println("Could not connect to the message store");
			ex.printStackTrace();
			//log.error(ex);
		} catch (IOException ex) {
			ex.printStackTrace();
			//log.error(ex);
		}
	}
}
