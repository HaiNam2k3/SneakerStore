/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message; 
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author hoanh
 */
public class XEmail {

    public void sendMail(String emailToAddress, String textMessage) {
        try {

            // Cấu hình Properties
            Properties properties = new Properties();
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.starttls.enable", "true");
            properties.put("mail.smtp.host", "smtp-mail.outlook.com");
            properties.put("mail.smtp.port", "587");
            properties.put("mail.smtp.ssl.trust", "none");
            properties.put("mail.smtp.ssl.protocols", "TLSv1.2");
            properties.put("mail.smtp.ssl.trust", "*");

            String username = "hoanhtran26@outlook.com";
            String password = "26092000aA";

            // Tạo đối tượng Session
            Session session = Session.getInstance(properties,
                    new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });

            // Tạo đối tượng MimeMessage
            Message message = new MimeMessage(session);

            // Địa chỉ người gửi
            message.setFrom(new InternetAddress(username));

            // Địa chỉ người nhận
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailToAddress));

            // Chủ đề email
            message.setSubject("Bảo Mật");

            message.setContent("Mật khẩu đăng nhập hệ thống là: " + textMessage, "text/html; charset=utf-8");

            // Tạo dãy số ngẫu nhiên
            //  String randomNumbers = generateRandomNumbers();
            // Nội dung email
            //message.setText("Mã OTP của bạn là: " + randomNumbers);
            // Gửi email
            try {
                // Gửi email
                Transport.send(message);

                // Hiển thị thông báo thành công
                MsgBox.alert(null, "Đã gửi email thành công: " + emailToAddress);
            } catch (MessagingException e) {
                // Hiển thị thông báo lỗi
                e.printStackTrace();
                MsgBox.alert(null, "Lỗi khi gửi email: " + e.getMessage());
            }

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}
