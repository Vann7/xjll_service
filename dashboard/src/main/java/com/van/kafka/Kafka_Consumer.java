package com.van.kafka;


import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.van.bean.Msg;
import com.van.dashboard.SocketUtil;
import org.apache.kafka.clients.consumer.ConsumerRebalanceListener;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;

import java.util.Collection;
import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.TimeUnit;


public class Kafka_Consumer {

    private static KafkaConsumer<String, String> kafka_consumer;

    private static SocketIOServer socket_server;

    public static Msg message;

    public static void main(String[] args){
        kafka_consumer =  init();
        socket_server = SocketUtil.init();
        socket_server.start();
        send("result");

    }


    public static KafkaConsumer<String, String> init(){
        Properties props = new Properties();
        props.put("bootstrap.servers", "ubuntu:9092, ubuntu2:9092");
        props.put("group.id", "hello-group");
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("session.timeout.ms", "30000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);

        return consumer;
    }

    public static void send(String topic) {

        kafka_consumer.subscribe(Collections.singletonList(topic), new ConsumerRebalanceListener() {
            @Override
            public void onPartitionsRevoked(Collection<TopicPartition> partitions) {}

            @Override
            public void onPartitionsAssigned(Collection<TopicPartition> partitions) {}
        });
        while (true) {
            ConsumerRecords<String, String> records = kafka_consumer.poll(1000);
            for (ConsumerRecord<String, String> record : records) {
                message = new Msg();
                String value = record.value();
                System.out.println(value);
                message.setContent(value);
                String[] list = value.split(",");
                if (list.length < 1) {
                    message.setTo("null");
                    message.setFrom("null");
                } else {

                }



                Collection<SocketIOClient> clients = socket_server.getAllClients();
                for(SocketIOClient client : clients) {
                    client.sendEvent("OnMSG",message);
                }

                //System.out.printf("offset = %d, key = %s, value = %s \n", record.offset(), record.key(), record.value());
            }
            try {
                TimeUnit.MICROSECONDS.sleep(60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }




 }
