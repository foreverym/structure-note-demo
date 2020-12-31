package club.banyaun.jwtsec.rabbitmq;

import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author : WangYB
 * @time: 2020/12/24  20:47
 */
@Component
public class RabbitTemplateConfig implements RabbitTemplate.ConfirmCallback,RabbitTemplate.ReturnsCallback {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostConstruct
    public void init() {
        rabbitTemplate.setConfirmCallback(this);//指定 ConfirmCallback
        rabbitTemplate.setReturnCallback(this);
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        System.out.println("消息唯一标识：" + correlationData);
        System.out.println("确认结果：" + ack);
        System.out.println("失败原因：" + cause);
    }

    @Override
    public void returnedMessage(ReturnedMessage returnedMessage) {

    }

}