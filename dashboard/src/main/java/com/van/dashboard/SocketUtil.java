package com.van.dashboard;

import com.corundumstudio.socketio.*;
import com.van.bean.Msg;
import com.van.kafka.Kafka_Consumer;
import com.van.socket.SocketIOClientCache;
import org.apache.kafka.clients.consumer.KafkaConsumer;


public class SocketUtil {

    private static KafkaConsumer<String, String> kafka_consumer;

    private static SocketIOServer socket_server;

    private static Msg msg;

    public static void main(String[] args){
        kafka_consumer = Kafka_Consumer.init();

        socket_server = init();
        socket_server.start();

    }

    public static SocketIOServer init() {

        SocketIOClientCache clientCache = new SocketIOClientCache();
        Configuration config = new Configuration();
        config.setHostname("localhost");
        config.setPort(8087);

        SocketConfig socketConfig = new SocketConfig();
        socketConfig.setReuseAddress(true);
        socketConfig.setTcpNoDelay(true);
        socketConfig.setSoLinger(0);
        config.setSocketConfig(socketConfig);

        SocketIOServer server = new SocketIOServer(config);
        return server;
    }


    public static void listener(SocketIOServer server) {
        //接受处理消息
        server.addEventListener("OnMSG",Msg.class,  (SocketIOClient client, Msg msg, AckRequest ackRequest) -> {
            client.sendEvent("OnMSG", msg);
        });

        server.addConnectListener((client -> {
            if (msg == null){
                msg = new Msg();
                msg.setContent("haha");
            }
            System.out.println(msg);
            client.sendEvent("OnMSG",msg);
        }));



        server.start();
        System.out.println("启动正常");
    }



}
