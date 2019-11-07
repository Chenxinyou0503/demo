package com.xinyou.dome.rocketmq;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @Author ：chenxinyou.
 * @Title :
 * @Date ：Created in 2019/3/18 11:41
 * @Description: 顺序消费 设置Consumer第一次启动是从队列头部开始消费还是队列尾部开始消费<br>
 * 如果非第一次启动，那么按照上次消费的位置继续消费
 */

public class OrderConsumer {
    public static void main(String[] args) throws Exception {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("order_Consumer");//指定消费者组
        consumer.setNamesrvAddr("d1122.comidware.yunzong:9876;d1125.comidware.yunzong:9876");//指定nameserver
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);//指定从哪里开始消费
        consumer.subscribe("yz_order", "*");//指定订阅topic和tag
        consumer.registerMessageListener(new MessageListenerOrderly() {//顺序消费要注册顺序消费监听器
            AtomicLong consumeTimes = new AtomicLong(0);

            @Override
            public ConsumeOrderlyStatus consumeMessage(List<MessageExt> msgs, ConsumeOrderlyContext context) {
                context.setAutoCommit(false);
                for (MessageExt ext : msgs) {
                    System.out.println(ext + ",内容：" + new String(ext.getBody()));
                }
                this.consumeTimes.incrementAndGet();
                if ((this.consumeTimes.get() % 2) == 0) {
                    System.out.println("消费成功");
                    return ConsumeOrderlyStatus.SUCCESS;
                } else if ((this.consumeTimes.get() % 5) == 0) {
                    context.setSuspendCurrentQueueTimeMillis(3000);
                    System.out.println("等一会在消费");
                    return ConsumeOrderlyStatus.SUSPEND_CURRENT_QUEUE_A_MOMENT;
                }
//                try {
////                    TimeUnit.SECONDS.sleep(1L);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                return ConsumeOrderlyStatus.SUCCESS;//表示消费成功，SUSPEND_CURRENT_QUEUE_A_MOMENT表示稍后消费
            }
        });
        consumer.start();
        System.out.println("Consumer1 Started.");
    }
}
