package club.banyaun.jwtsec.rabbitmq;

import club.banyaun.jwtsec.entity.EmailSendMessage;
import com.alibaba.fastjson.JSON;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.rmi.MarshalException;

/**
 * Created by jiangyunxiong on 2018/5/29.
 */
@Service
public class MQSender {

    private static Logger log = LoggerFactory.getLogger(MQSender.class);

    @Autowired
    AmqpTemplate amqpTemplate;

    /**
     * 直连模式发送消息
     * @param message
     */
    public void sendEmailMessage(EmailSendMessage message) {
        String emailJsonStr = JSON.toJSONString(message);
        log.info(">>>>>>>>>>>message:{}", emailJsonStr);
        amqpTemplate.convertAndSend(MQConfig.QUEUE, emailJsonStr);
    }


    public void sendTopic(Object message) {
        log.info("send topic message:" + message);
        amqpTemplate.convertAndSend(MQConfig.TOPIC_EXCHANGE, "topic.key1", message + "1");
        amqpTemplate.convertAndSend(MQConfig.TOPIC_EXCHANGE, "topic.key2", message + "2");
    }


}
