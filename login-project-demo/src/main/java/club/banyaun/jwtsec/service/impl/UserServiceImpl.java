package club.banyaun.jwtsec.service.impl;

import club.banyaun.jwtsec.dao.UserMapper;
import club.banyaun.jwtsec.entity.EmailSendMessage;
import club.banyaun.jwtsec.entity.LoginDetail;
import club.banyaun.jwtsec.entity.User;
import club.banyaun.jwtsec.rabbitmq.MQSender;
import club.banyaun.jwtsec.service.UserService;
import club.banyaun.jwtsec.util.EmailSender;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author : WangYB
 * @time: 2020/12/23  16:55
 */
@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserMapper userMapper;
    @Autowired
    private EmailSender emailSender;
    @Autowired
    private MQSender mqSender;

    @Override
    public void register(User user) {
        //插入数据库
        EmailSendMessage emailSendMessage = new EmailSendMessage();
        userMapper.insertUser(user);
        BeanUtils.copyProperties(user, emailSendMessage);
        mqSender.sendEmailMessage(emailSendMessage);
        //emailSender.sendEmail(user.getEmail());
    }

    @Override
    public int updateToActive(Integer id) {
        return userMapper.updateToActive(id);
    }

    @Override
    public LoginDetail queryLoginDetail(String name) {
        return userMapper.queryLoginDetail(name);
    }

}
