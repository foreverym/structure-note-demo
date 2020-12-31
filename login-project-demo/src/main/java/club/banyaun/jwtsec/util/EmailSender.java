package club.banyaun.jwtsec.util;

import club.banyaun.jwtsec.dao.UserMapper;
import club.banyaun.jwtsec.entity.EmailSendMessage;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * @author : WangYB
 * @time: 2020/12/23  18:58
 */
@Component
public class EmailSender {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String email;

    public void sendEmail(String uuid, EmailSendMessage emailSendMessage) {
        StringBuilder sb = new StringBuilder();//内容修饰
        sb.append("<html>");
        sb.append("<body>");
        sb.append("<h3>");
        sb.append("欢迎注册,"+emailSendMessage.getName());
        sb.append("<a href='http://127.0.0.1:8090/jwtsec/email/activeEmail?id=" + emailSendMessage.getId() + "&check=" + uuid + "'" + ">点击激活</a>");
        sb.append("</h3>");
        sb.append("</body>");
        sb.append("</html>");

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom(email);//发送人
            helper.setTo("\n" +
                    emailSendMessage.getEmail());//接收人
            helper.setSubject("注册激活");//标题
            helper.setText(sb.toString(), true);//发送的内容
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}
