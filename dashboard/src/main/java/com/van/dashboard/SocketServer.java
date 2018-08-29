package com.van.dashboard;

import com.corundumstudio.socketio.*;
import com.van.bean.Msg;
import org.apache.kafka.clients.consumer.KafkaConsumer;


//@org.springframework.context.annotation.Configuration
public class SocketServer {

    private static Msg msg;

    private static KafkaConsumer<String, String> kafka_consumer;

    //@Bean
    public SocketIOServer init() {

        Configuration config = new Configuration();
        config.setHostname("localhost");
        config.setPort(8087);

        SocketConfig socketConfig = new SocketConfig();
        socketConfig.setReuseAddress(true);
        socketConfig.setTcpNoDelay(true);
        socketConfig.setSoLinger(0);
        config.setSocketConfig(socketConfig);

        SocketIOServer server = new SocketIOServer(config);

        //创建连接
        server.addConnectListener((client -> {
            while (true) {

                //msg = Kafka_Consumer.message;
                //Thread thread = new Thread(new ConsumerThread("OnMSG",msg));
                //thread.start();


                if (msg != null){
                    System.out.println(msg);
                    client.sendEvent("OnMSG",msg);
                }else {
                    msg = new Msg();
                    msg.setContent("it's null");
                    client.sendEvent("OnMSG",msg);
                    System.out.println("null");
                }

                try {
                        Thread.sleep(400);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
            }

        }));


        //接受处理消息
        server.addEventListener("OnMSG",Msg.class,  (SocketIOClient client, Msg msg, AckRequest ackRequest) -> {
            System.out.printf("收到消息-from: %s to %s \n",msg.getFrom(), msg.getTo());
            client.sendEvent("OnMSG", msg);
        });

        //关闭连接
        server.addDisconnectListener(OnDisconnect -> System.out.println("关闭连接"));



        server.start();
        System.out.println("启动正常");
        return server;
    }


}
