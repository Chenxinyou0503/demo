package com.xinyou.dome.rocketmq;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * @Author ：chenxinyou.
 * @Title :
 * @Date ：Created in 2019/3/18 11:01
 * @Description: 普通消费 push 消费模式
 */
public class Consumer {
    public static void main(String[] args) throws Exception {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("yunzong_consumer");//设置消费者组
        consumer.setNamesrvAddr("d1122.comidware.yunzong:9876;d1125.comidware.yunzong:9876");//指定NameServer
        String tag = "*";//设置tag标签 只取标签为yunzong_b的信息
        consumer.subscribe("Yunzong_Topic_new", tag);//设置订阅topic
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);//指定最开始的位置开始消费，
//        consumer.setConsumeTimeout(1553767490000L);
        consumer.registerMessageListener(new MessageListenerConcurrently() {//设置消息监听事件，如果有消息则触发消费功能
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs,
                                                            ConsumeConcurrentlyContext context) {
                for (MessageExt messageExt : msgs) {
                    System.out.println(new String(messageExt.getBody()) + ":" + messageExt.getMsgId());
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;//返回消费成功，RECONSUME_LATER
            }
        });
        consumer.start();//启动消费者服务
        System.out.printf("Consumer Started.%n");
    }
}
