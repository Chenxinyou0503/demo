package com.xinyou.dome.rocketmq;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.selector.SelectMessageQueueByHash;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

/**
 * @Author ：chenxinyou.
 * @Title :
 * @Date ：Created in 2019/3/18 11:41
 * @Description: 生产顺序消息
 */
public class OrderProducer {
    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("yz_order_producer");//设置消费者组
        producer.setNamesrvAddr("d1122.comidware.yunzong:9876;d1125.comidware.yunzong:9876");// 设置NameSever
        producer.start();//启动生产者
        int orderId = 10001;//设置orderId
        int orderId2 = 10002;//设置orderId
        for (int i = 0; i < 100; i++) {
            Message msg = new Message("yz_order", "Order_1", "KEY" + i,//设置topic，tag, 关键字
                    ("Hello RocketMQ ：" + i).getBytes(RemotingHelper.DEFAULT_CHARSET));        // 和消息内容
            SendResult sendResult = producer.send(msg, new SelectMessageQueueByHash(), orderId);// 默认选择hash算法发送消息
            System.out.printf("%s%n", sendResult);
        }
//        for (int i = 0; i < 5; i++) {
//            Message msg = new Message("Yunzong_Order", "Order_2", "KEY" + i,//设置topic，tag, 关键字
//                    ("Hello RocketMQ " + i).getBytes(RemotingHelper.DEFAULT_CHARSET));        // 和消息内容
//            SendResult sendResult = producer.send(msg, new SelectMessageQueueByHash(), orderId2);// 默认选择hash算法发送消息
//            System.out.printf("%s%n", sendResult);
//        }
        producer.shutdown();
    }
}
