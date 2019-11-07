package com.xinyou.dome.rocketmq;

import com.xinyou.dome.util.DataUtil;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

/**
 * @Author ：chenxinyou.
 * @Title :
 * @Date ：Created in 2019/3/18 10:41
 * @Description: 普通消息生产者
 */
public class Producer {
    public static void main(String[] args) throws Exception {

        new Thread(() -> {
            try {
                DefaultMQProducer producer = new DefaultMQProducer("yunzong_producer_new");
                producer.setNamesrvAddr("d1122.comidware.yunzong:9876;d1125.comidware.yunzong:9876");//指定NameServer
                producer.start(); //启动生产者
                String tag[] = new String[]{"yunzong_a", "yunzong_b", "yunzong_c", "yunzong_d"};//设置tag
                System.out.println(1);
                for (int i = 0; i < 100000; i++) {
                    Message message = new Message("Yunzong_Topic_new", tag[i % tag.length],//设置消息topic，tag和内容
                            ("Hello RocketMQ:" + DataUtil.formatDate(System.currentTimeMillis()) + ":" + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
                    SendResult sendResult = producer.send(message);// 发送消息
                    System.out.println(sendResult.toString() + i);// 打印发送结果
                }
                producer.shutdown();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }).start();


    }

}
