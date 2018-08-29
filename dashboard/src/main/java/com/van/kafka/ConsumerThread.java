package com.van.kafka;

import com.van.bean.Msg;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;
import java.util.Properties;

public class ConsumerThread implements Runnable {
    public static Msg message;
    private static KafkaConsumer<String,String> kafkaConsumer;
    private final String topic;

    public ConsumerThread(String topic,Msg message){

        Properties props = new Properties();
        props.put("bootstrap.servers", "ubuntu:9092, ubuntu2:9092");
        props.put("group.id", "hello-group");
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("session.timeout.ms", "30000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");


        //KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        //Properties properties = buildKafkaProperty(brokers,groupId);
        this.topic = topic;
        this.kafkaConsumer = new KafkaConsumer<String, String>(props);
        this.kafkaConsumer.subscribe(Arrays.asList(this.topic));
        this.message = message;
    }


    @Override
    public void run() {
        while (true){
            ConsumerRecords<String,String> consumerRecords = kafkaConsumer.poll(100);
            for(ConsumerRecord<String,String> record : consumerRecords){
                message = new Msg();
                message.setContent(record.value());
                System.out.println("Consumer Message:"+record.value()+",Partition:"+record.partition()+"Offset:"+record.offset());
            }
        }
    }
}
