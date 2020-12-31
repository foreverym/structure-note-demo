package club.banyaun.jwtsec.rabbitmq;

import club.banyaun.jwtsec.constant.RedisConstant;
import club.banyaun.jwtsec.entity.EmailSendMessage;
import club.banyaun.jwtsec.entity.User;
import club.banyaun.jwtsec.util.EmailSender;
import club.banyaun.jwtsec.util.RedisUtil;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Created by jiangyunxiong on 2018/5/29.
 */
@Service
public class MQReceiver {

    @Autowired
    private EmailSender emailSender;
    @Autowired
    private RedisUtil redisUtil;

    private static Logger log = LoggerFactory.getLogger(MQReceiver.class);

    @RabbitListener(queues=MQConfig.QUEUE)
    public void receive(String message){
        EmailSendMessage emailSendMessage = JSON.parseObject(message, EmailSendMessage.class);
        log.info(">>>>>>>>>>message:{}", emailSendMessage);
        if (message != null) {
            String uuid = UUID.randomUUID().toString().replace("-", "");
            emailSender.sendEmail(uuid, emailSendMessage);
            redisUtil.saveValue(RedisConstant.REDIS_ACTIVE_CODE + emailSendMessage.getId(), uuid);
        }
    }

    @RabbitListener(queues = MQConfig.TOPIC_QUEUE1)
    public void receiveTopic1(String message) {
        log.info(" topic  queue1 message:" + message);
    }


}
